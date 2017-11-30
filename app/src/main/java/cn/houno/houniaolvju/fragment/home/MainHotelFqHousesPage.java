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
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.FenQuanDetailActivity;
import cn.houno.houniaolvju.activity.hotel.HotelDetailActivity;
import cn.houno.houniaolvju.adapter.FqHousesAdapter;
import cn.houno.houniaolvju.adapter.MainHotelAdapter;
import cn.houno.houniaolvju.bean.FenQuanListBean;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.FqHousesBean;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.MainHotelBean;
import cn.houno.houniaolvju.view.InnerListView;

/**
 * 项目名称：Houniaolvju
 * 类描述：首页-主推酒店
 * 创建人：qzc
 * 创建时间：2016/12/13 14:08
 * 修改人：qzc
 * 修改时间：2016/12/13 14:08
 * 修改备注：
 */
public class MainHotelFqHousesPage extends Fragment {

    private View mView;

    private LinearLayout llView;
    private InnerListView lvMainPush;

    private MainHotelAdapter mMainHotelAdapter;
    private ArrayList<MainHotelBean> mMainHotelList;

    private FqHousesAdapter mFqHousesAdapter;
    private List<FenQuanListBean.DataBean> mFqHousesList;
    private String mPage;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.page_main_push_hotel, container, false);
        mActivity = getActivity();
        initView(mView);
        initData();
        initEvent();
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    private void initEvent() {
        lvMainPush.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                if ("mainhotel".equals(mPage)) {
                    String hotelId = mMainHotelList.get(position).getId();
                    intent.putExtra("from", "home");
                    intent.putExtra("hid", hotelId);
                    intent.setClass(getActivity(), HotelDetailActivity.class);
                } else {
                    String fqId = mFqHousesList.get(position).getId();
                    intent.putExtra("id", fqId);
                    intent.setClass(getActivity(), FenQuanDetailActivity.class);
                }
                startActivity(intent);
            }
        });
    }


    public void initData() {
        Bundle arguments = getArguments();
        mPage = arguments.getString("page");
        if ("mainhotel".equals(mPage)) {
            mMainHotelList = (ArrayList<MainHotelBean>) arguments.getSerializable("data");
            if (mMainHotelList != null) {
                setMainHotelData(mMainHotelList);
                llViewSetVisibility(true);
            }else {
                llViewSetVisibility(false);
            }
        } else {
            mFqHousesList = (List<FenQuanListBean.DataBean>) arguments.getSerializable("data");
            if (mFqHousesList != null) {
                llViewSetVisibility(true);
                setFqData(mFqHousesList);
            }else {
                llViewSetVisibility(false);
            }
        }

    }

    private void llViewSetVisibility(boolean show){
        if (show){
            llView.setVisibility(View.VISIBLE);
        }else {
            llView.setVisibility(View.GONE);
        }
    }


    public void setMainHotelData(ArrayList<MainHotelBean> mainHotelList) {
        if (mainHotelList != null && mainHotelList.size() != 0) {
            llViewSetVisibility(true);
            mMainHotelList = mainHotelList;
            if (mMainHotelAdapter == null) {
                mMainHotelAdapter = new MainHotelAdapter(mActivity, mainHotelList);
                lvMainPush.setAdapter(mMainHotelAdapter);
            } else {
                mMainHotelAdapter.setData(mainHotelList);
            }
        }else {
            llViewSetVisibility(false);
        }
    }

    public void setFqData(List<FenQuanListBean.DataBean> fqHousesList) {
        mFqHousesList = fqHousesList;
        if (mFqHousesAdapter == null) {
            mFqHousesAdapter = new FqHousesAdapter(mActivity, fqHousesList);
            lvMainPush.setAdapter(mFqHousesAdapter);
        } else {
            mFqHousesAdapter.setData(fqHousesList);
        }
    }


    private void initView(View view) {
        llView = (LinearLayout) view.findViewById(R.id.ll_view);
        lvMainPush = (InnerListView) view.findViewById(R.id.lv_mainhotel_fqhouses);
    }

}
