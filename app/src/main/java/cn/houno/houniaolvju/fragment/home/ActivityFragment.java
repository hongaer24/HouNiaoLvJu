package cn.houno.houniaolvju.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.hotel.HotelDetailActivity;
import cn.houno.houniaolvju.activity.scenic.ScenicDetailActivity;
import cn.houno.houniaolvju.adapter.ActiHotelTgListAdapter;
import cn.houno.houniaolvju.adapter.ActiScenicListAdapter;
import cn.houno.houniaolvju.adapter.ActiSpcHotelListAdapter;
import cn.houno.houniaolvju.bean.ActiHotelTgListBean;
import cn.houno.houniaolvju.bean.ActiScenicListBean;
import cn.houno.houniaolvju.bean.ActiSpcHotelListBean;
import cn.houno.houniaolvju.global.Constants;

/**
 * 项目名称：Houniaolvju
 * 类描述：活动列表
 * 创建人：qzc
 * 创建时间：2016/12/15 18:11
 * 修改人：qzc
 * 修改时间：2016/12/15 18:11
 * 修改备注：
 */
public class ActivityFragment extends Fragment {

    private Activity mActivity;
    private int TAG;

    private static final int HOTEL_TG = 0;
    private static final int SPC_HOTEL = 1;
    private static final int SCENIC = 2;

    private String mUrl;
    private int page = 2;

    @Bind(R.id.lv_listview)
    ListView mLvActivity;
    @Bind(R.id.rfv_refreshview)
    XRefreshView mRfvActivity;

    private List<ActiHotelTgListBean.DataBean> mTgHotelList;
    private List<ActiSpcHotelListBean.DataBean> mSpcHotelList;
    private List<ActiScenicListBean.DataBean> mScenicList;

    private ActiHotelTgListAdapter mTgHotelAdapter;
    private ActiSpcHotelListAdapter mSpcHotelAdapter;
    private ActiScenicListAdapter mScenicAdapter;

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
        Bundle arguments = getArguments();
        TAG = arguments.getInt("tag");
        if (TAG == HOTEL_TG) {
            mUrl = Constants.ACTI_HOTEL_TG_LIST;
            mTgHotelList = new ArrayList<>();
            mTgHotelAdapter = new ActiHotelTgListAdapter(mActivity);
            mLvActivity.setAdapter(mTgHotelAdapter);
        } else if (TAG == SPC_HOTEL) {
            mUrl = Constants.ACTI_SPC_HOTEL_LIST;
            mSpcHotelList = new ArrayList<>();
            mSpcHotelAdapter = new ActiSpcHotelListAdapter(mActivity);
            mLvActivity.setAdapter(mSpcHotelAdapter);
        } else if (TAG == SCENIC) {
            mUrl = Constants.ACTI_SCENIC_LIST;
            mScenicList = new ArrayList<>();
            mScenicAdapter = new ActiScenicListAdapter(mActivity);
            mLvActivity.setAdapter(mScenicAdapter);
        }

        mRfvActivity.startRefresh();

    }

    private void initEvent() {
        mRfvActivity.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
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

        mLvActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                if (TAG == HOTEL_TG) {
                    intent.putExtra("from", "home");
                    intent.putExtra("hid", mTgHotelList.get(position).getId());
                    intent.setClass(getActivity(), HotelDetailActivity.class);
                    startActivity(intent);
                } else if (TAG == SPC_HOTEL) {
                    intent.putExtra("from", "home");
                    intent.putExtra("hid", mSpcHotelList.get(position).getId());
                    intent.setClass(getActivity(), HotelDetailActivity.class);
                    startActivity(intent);
                } else if (TAG == SCENIC) {
                    intent.putExtra("scenicid", mScenicList.get(position).getScenicid());
                    intent.setClass(getActivity(), ScenicDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    private void getDataFromServer() {

        RequestParams params = new RequestParams(mUrl);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("SUCCESS" + TAG, result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, false);
                    } else {
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
                mRfvActivity.stopRefresh();
            }
        });

    }

    private void getMoreDataFromServer() {
        RequestParams params = new RequestParams(mUrl);
        params.addBodyParameter("p", page + "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("SUCCESS" + TAG, result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, true);
                    } else {
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
                mRfvActivity.stopLoadMore();
            }
        });
    }

    private void parseData(String result, boolean isLoadMore) {
        Gson gson = new Gson();
        if (TAG == HOTEL_TG) {
            ActiHotelTgListBean actiHotelTgListBean = gson.fromJson(result, ActiHotelTgListBean.class);
            if (isLoadMore) {
                mTgHotelList.addAll(actiHotelTgListBean.getData());
                page++;
            } else {
                mTgHotelList = actiHotelTgListBean.getData();
                page = 2;
            }
            if (mTgHotelAdapter == null) {
                mTgHotelAdapter = new ActiHotelTgListAdapter(mActivity);
                mLvActivity.setAdapter(mTgHotelAdapter);
            } else {
                mTgHotelAdapter.setData(mTgHotelList);
            }
            if (mTgHotelList.size() >= 10) {
                mRfvActivity.setPullLoadEnable(true);
            } else {
                mRfvActivity.setPullLoadEnable(false);
            }
        } else if (TAG == SPC_HOTEL) {
            ActiSpcHotelListBean actiSpcHotelListBean = gson.fromJson(result, ActiSpcHotelListBean.class);

            if (isLoadMore) {
                mSpcHotelList.addAll(actiSpcHotelListBean.getData());
                page++;
            } else {
                mSpcHotelList = actiSpcHotelListBean.getData();
                page = 2;
            }
            if (mSpcHotelAdapter == null) {
                mSpcHotelAdapter = new ActiSpcHotelListAdapter(mActivity);
                mLvActivity.setAdapter(mSpcHotelAdapter);
            } else {
                mSpcHotelAdapter.setData(mSpcHotelList);
            }
            if (mSpcHotelList.size() >= 10) {
                mRfvActivity.setPullLoadEnable(true);
            } else {
                mRfvActivity.setPullLoadEnable(false);
            }
        } else if (TAG == SCENIC) {
            ActiScenicListBean actiScenicListBean = gson.fromJson(result, ActiScenicListBean.class);
            if (isLoadMore) {
                mScenicList.addAll(actiScenicListBean.getData());
                page++;
            } else {
                mScenicList = actiScenicListBean.getData();
                page = 2;
            }
            if (mScenicAdapter == null) {
                mScenicAdapter = new ActiScenicListAdapter(mActivity);
                mLvActivity.setAdapter(mScenicAdapter);
            } else {
                mScenicAdapter.setData(mScenicList);
            }
            if (mScenicList.size() >= 10) {
                mRfvActivity.setPullLoadEnable(true);
            } else {
                mRfvActivity.setPullLoadEnable(false);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
