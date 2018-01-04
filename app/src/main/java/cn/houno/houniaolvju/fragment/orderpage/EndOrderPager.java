package cn.houno.houniaolvju.fragment.orderpage;


import android.content.Intent;
import android.os.Bundle;
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
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.scenic.ScenicOrderDetailActivity;
import cn.houno.houniaolvju.activity.train.TrainOrderDetailActivity;
import cn.houno.houniaolvju.adapter.EndOrderAdapter;
import cn.houno.houniaolvju.bean.OrderListBean;
import cn.houno.houniaolvju.global.Constants;
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
public class EndOrderPager extends Fragment {

    @Bind(R.id.lv_end_list)
    ListView lvEndList;
    @Bind(R.id.rfv_end_order)
    XRefreshView rfvEndOrder;
    @Bind(R.id.ll_no_order)
    LinearLayout llNoOrder;

    private MainActivity mActivity;

    private String userid;
    private EndOrderAdapter endListAdapter;
    private List<OrderListBean.DataBean> mEndOrderList = new ArrayList<>();

    public static boolean refresh = false;
    private String info;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end_order, null, false);
        mActivity = (MainActivity) getActivity();
        ButterKnife.bind(this, view);
        initData();
        initEvent();
        rfvEndOrder.startRefresh();
        return view;
    }

    private void initEvent() {
        rfvEndOrder.setPullLoadEnable(true);
        rfvEndOrder.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
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


        lvEndList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                String type = mEndOrderList.get(position).getType();
                String addTime = mEndOrderList.get(position).getAdd_time();

                intent.putExtra("data", mEndOrderList.get(position));
                intent.putExtra("addtime", addTime);

                if (TextUtils.equals(type, "Train")) {
                    intent.putExtra("orderno", mEndOrderList.get(position).getOrderno());
                    intent.setClass(mActivity, TrainOrderDetailActivity.class);
                } else if (TextUtils.equals(type, "Flight")) {
                    intent.setClass(mActivity, FlightOrderActivity.class);
                } else if (TextUtils.equals(type, "toursscenic")) {
                    intent.putExtra("orderno", mEndOrderList.get(position).getOrderno());
                    intent.setClass(mActivity, ScenicOrderDetailActivity.class);
                } else {
                    intent.setClass(mActivity, EndOrderDetailActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    private void initData() {

    }

    private int page;

    //下拉刷新数据
    public void getDataFromServer() {
        userid = PrefUtils.getString(mActivity, "userid", "");
        page = 1;

        RequestParams params = new RequestParams(Constants.MYORDER_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("page", page + "");
        params.addBodyParameter("status", "1");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Log.e("end_order_pager", result);
                try {
                    JSONObject json = new JSONObject(result);
                    int status = json.getInt("status");
                    if (status == 1) {
                        //Toast.makeText(mActivity, "没有订单...", Toast.LENGTH_SHORT).show();
                        rfvEndOrder.setVisibility(View.GONE);
                        llNoOrder.setVisibility(View.VISIBLE);
                    } else if (status == 0) {
                        llNoOrder.setVisibility(View.GONE);
                        rfvEndOrder.setVisibility(View.VISIBLE);
                        parseData(result, false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                rfvEndOrder.stopRefresh();
            }
        });
    }


    //上拉加载数据
    public void getMoreDataFromServer() {
        page++;

        RequestParams rparams = new RequestParams(Constants.MYORDER_URL);
        rparams.addBodyParameter("userid", userid);
        rparams.addBodyParameter("page", page + "");
        rparams.addBodyParameter("status", "1");
        x.http().post(rparams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    int status = json.getInt("status");
                    if (status == 1) {
                        Toast.makeText(mActivity, "没有更多订单啦...", Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        parseData(result, true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                rfvEndOrder.stopLoadMore();
            }
        });
    }

    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();
        OrderListBean orderListBean = gson.fromJson(result, OrderListBean.class);
        if (!isMore) {
            mEndOrderList = orderListBean.getData();
        } else {
            mEndOrderList.addAll(orderListBean.getData());
        }
        if (endListAdapter == null) {
            endListAdapter = new EndOrderAdapter(mActivity, mEndOrderList);
            lvEndList.setAdapter(endListAdapter);
        } else {
            endListAdapter.setData(mEndOrderList);
        }
   /*    info = PrefUtils.getString(mActivity, "info", "");
        wvScenicDetailInfo.loadDataWithBaseURL(null,    info, "text/html", "utf-8", null);*/
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("EndOrderPage:onResume");
        if (refresh) {
            refresh = false;
            getDataFromServer();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
