package cn.houno.houniaolvju.fragment.orderpage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.train.TrainOrderDetailActivity;
import cn.houno.houniaolvju.adapter.IngOrderAdapter;
import cn.houno.houniaolvju.bean.OrderListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class IngOrderPager extends Fragment {

    @Bind(R.id.lv_ing_list)
    ListView lvIngList;
    @Bind(R.id.rfv_ing_order)
    XRefreshView rfvIngOrder;
    @Bind(R.id.ll_no_order)
    LinearLayout llNoOrder;


    private MainActivity mActivity;

    private String userid;
    private List<OrderListBean.DataBean> mIngOrderList = new ArrayList<>();
    private IngOrderAdapter ingListAdapter;

    public static boolean refresh = false;
    private String info;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ing_order, null, false);
        mActivity = (MainActivity) getActivity();
        ButterKnife.bind(this, view);
        initData();
        initEvent();
        //自动刷新
        rfvIngOrder.startRefresh();
        return view;
    }


    private void initEvent() {
        rfvIngOrder.setPullLoadEnable(true);
        rfvIngOrder.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                getMoreDataFromServer();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });

        lvIngList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent();
                String type = mIngOrderList.get(position).getType();
                String address=mIngOrderList.get(position).getDetail().getAddress();
                String addTime=mIngOrderList.get(position).getAdd_time();


                intent.putExtra("data", (Serializable) mIngOrderList.get(position));
                intent.putExtra("address", address);
                intent.putExtra("addtime", addTime);

                if (TextUtils.equals(type, "Flight")) {
                    intent.setClass(mActivity, FlightOrderActivity.class);
                } else if (TextUtils.equals(type, "Train")) {
                    intent.putExtra("orderno", mIngOrderList.get(position).getOrderno());
                    intent.setClass(mActivity, TrainOrderDetailActivity.class);
                } else if (TextUtils.equals(type, "")) {
                    intent.putExtra("orderno", mIngOrderList.get(position).getOrderno());
                    intent.setClass(mActivity, TrainOrderDetailActivity.class);
                }
                else {
                    intent.setClass(mActivity, IngOrderDetailActivity.class);
                }
                startActivity(intent);

            }
        });
    }


    private void initData() {

    /*    info = PrefUtils.getString(mActivity, "info", "");
        wvScenicDetailInfo.loadDataWithBaseURL(null,    info, "text/html", "utf-8", null);*/


    }


    private int page;

    //下拉刷新
    public void getDataFromServer() {
        page = 1;
        userid = PrefUtils.getString(mActivity, "userid", "");

        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        map.put("page", page + "");
        map.put("status", "0");

        OkHttpClientManager.postAsync(Constants.MYORDER_URL, map, null, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                rfvIngOrder.stopRefresh();
                switch (msg.what) {
                    case R.id.doSucceed:
                        String result = msg.obj.toString();
                        System.out.println("订单列表=" + result);
                        try {
                            JSONObject json = new JSONObject(result);
                            int status = json.getInt("status");
                            if (status == 1) {
                                //Toast.makeText(mActivity, "没有订单...", Toast.LENGTH_SHORT).show();
                                rfvIngOrder.setVisibility(View.GONE);
                               // llNoOrder.setVisibility(View.VISIBLE);
                            } else if (status == 0) {
                                llNoOrder.setVisibility(View.GONE);
                                rfvIngOrder.setVisibility(View.VISIBLE);
                                parserData(result, false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.doFail:
                        break;
                }
            }
        }, R.id.doSucceed, R.id.doFail);

    }

    //上拉加载
    public void getMoreDataFromServer() {
        page++;
        userid = PrefUtils.getString(mActivity, "userid", "");

        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        map.put("page", String.valueOf(page));
        // map.put("page", String.valueOf(page));
        map.put("pagesize", String.valueOf(10));
        map.put("status", String.valueOf(0));

        OkHttpClientManager.postAsync(Constants.MYORDER_URL, map, null, mHandler, R.id.doSucceed, R.id.doFail);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            rfvIngOrder.stopLoadMore();
            switch (msg.what) {
                case R.id.doSucceed:
                    String result = msg.obj.toString();
                    try {
                        JSONObject json = new JSONObject(result);
                        int sta = json.getInt("status");
                        if (sta == 1) {
                            Toast.makeText(mActivity, "没有更多订单啦...", Toast.LENGTH_SHORT).show();
                        } else if (sta == 0) {
                            parserData(result, true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.doFail:
                    page--;
                    break;
            }

        }
    };

    private void parserData(String result, boolean isMore) {
        Gson gson = new Gson();
        try {
            OrderListBean orderListBean = gson.fromJson(result, OrderListBean.class);
            if (!isMore) {
                mIngOrderList = orderListBean.getData();

            } else {
                mIngOrderList.addAll(orderListBean.getData());
            }
        } catch (Exception e) {
            Log.e("ProgressPager", e.getMessage());
        }

        if (ingListAdapter == null) {
            ingListAdapter = new IngOrderAdapter(mActivity, mIngOrderList);
            lvIngList.setAdapter(ingListAdapter);
        } else {
            ingListAdapter.setData(mIngOrderList);
        }
       // info = PrefUtils.getString(mActivity, "info", "");
        //wvScenicDetailInfo.loadDataWithBaseURL(null,    info, "text/html", "utf-8", null);

    }


    @Override
    public void onResume() {
        super.onResume();
        System.out.println("IngOrderPage:onResume");
      /*  if (refresh) {
            refresh = false;
            getDataFromServer();
        }*/
        getDataFromServer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
