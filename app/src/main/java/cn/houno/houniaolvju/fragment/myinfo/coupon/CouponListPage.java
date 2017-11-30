package cn.houno.houniaolvju.fragment.myinfo.coupon;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.CouponListAdapter;
import cn.houno.houniaolvju.bean.CouponListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;

/**
 * 优惠券列表
 * Created by Administrator on 2017/1/20.
 */

public class CouponListPage extends Fragment {

    @Bind(R.id.lv_listview)
    ListView lvListview;
    @Bind(R.id.rfv_refreshview)
    XRefreshView rfvRefreshview;
    @Bind(R.id.tv_message)
    TextView tvMessage;
    private Activity mActivity;
    private int TAG;
    private int page;

    List<CouponListBean.DataBean> mCouponList;
    private CouponListAdapter mListAdapter;
    private String userid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.page_activity, container, false);
        ButterKnife.bind(this, view);
        initData();
        initEvent();
        return view;

    }

    private void initData() {
        mActivity = getActivity();
        userid = PrefUtils.getString(mActivity, "userid", "");
        Bundle arguments = getArguments();
        TAG = arguments.getInt("tag");
        mCouponList = new ArrayList<>();
        mListAdapter = new CouponListAdapter(mActivity, mCouponList);
        lvListview.setAdapter(mListAdapter);

        rfvRefreshview.setPullLoadEnable(true);
        rfvRefreshview.startRefresh();
    }

    private void initEvent() {
        rfvRefreshview.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
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
    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.COUPON_LIST);
        params.addBodyParameter("userid", userid);
        if (TAG == 0) {
            params.addBodyParameter("status", "1");
        } else {
            params.addBodyParameter("status", "2"); //或者0
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, false);
                        page = 2;
                        if (!rfvRefreshview.getPullLoadEnable()) {
                            rfvRefreshview.setPullLoadEnable(true);
                        }
                    } else {
                        rfvRefreshview.setVisibility(View.GONE);
                        tvMessage.setText(obj.getString("msg"));
                        tvMessage.setVisibility(View.VISIBLE);
                      //  Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        //没有数据
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("ERROR" + TAG, ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                rfvRefreshview.stopRefresh();
            }
        });
    }

    private void getMoreDataFromServer() {
        RequestParams params = new RequestParams(Constants.COUPON_LIST);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("p", page + "");
        if (TAG == 0) {
            params.addBodyParameter("status", "1");
        } else {
            params.addBodyParameter("status", "2"); //或者0
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("SUCCESS" + TAG, result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, true);
                        page++;
                    } else {
                        rfvRefreshview.setPullLoadEnable(false);
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        //没有数据
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("ERROR" + TAG, ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                rfvRefreshview.stopLoadMore();
            }
        });
    }

    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();
        CouponListBean couponListBean = gson.fromJson(result, CouponListBean.class);
        if (isMore) {
            mCouponList.addAll(couponListBean.getData());
        } else {
            mCouponList = couponListBean.getData();
        }
        if (mListAdapter == null) {
            mListAdapter = new CouponListAdapter(mActivity, mCouponList);
            lvListview.setAdapter(mListAdapter);
        } else {
            mListAdapter.setDatas(mCouponList);
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
