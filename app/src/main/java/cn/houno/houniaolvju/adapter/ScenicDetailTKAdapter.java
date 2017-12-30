package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.ScenicDetailBean;

/**
 * Created by 123 on 2017/12/18.
 */

public class ScenicDetailTKAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {


    private  String mid;
    private  List<ScenicDetailBean.DataBean.OtherBean> mOtherScenicList;
    private LinearLayout mLlSame;
    private String scenicAddress;
    private Context context;
    private ArrayList<Fragment> mFragments;
    private String mTitle;
    private LayoutInflater inflate;
    private String mScenicdetail;
    private List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean> mTicketList;
    private String[] tabList = new String[]{"门票预定", "景点介绍"};

    public ScenicDetailTKAdapter(Context context, FragmentManager fragmentManager, ArrayList<Fragment> fragments/*, LinearLayout llsame*/) {
        super(fragmentManager);
        this.context = context;
        this.mFragments = fragments;
        this.inflate = LayoutInflater.from(context);

       // Log.i("11", "id===" + id);

        //this.mLlSame = llsame;
    }

    public ScenicDetailTKAdapter(Context context, FragmentManager fragmentManager, ArrayList<Fragment> fragments, List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean> ticketList, String scenicdetail, String scenicTitle, String scenicAddress, List<ScenicDetailBean.DataBean.OtherBean> otherScenicList,String id) {
        super(fragmentManager);
        this.context = context;
        this.mFragments = fragments;
        inflate = LayoutInflater.from(context);
        this.mTicketList = ticketList;
        this.mScenicdetail = scenicdetail;
        this.mTitle = scenicTitle;
        this.scenicAddress = scenicAddress;
        this.mOtherScenicList=otherScenicList;
        this.mid=id;
    }

    @Override
    public int getCount() {
        return tabList == null ? 0 : tabList.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.tab_hs_top, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(tabList[position]);
        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        Fragment fragment;
        Bundle args = new Bundle();
        if (position == 0) {
            fragment = mFragments.get(0);
            args.putSerializable("data", (Serializable) mTicketList);
            args.putString("title", mTitle);
            args.putString("address", scenicAddress);
            args.putString("id", mid);
            Log.i("33", "id===" +  mTitle);

            args.putSerializable("others", (Serializable) mOtherScenicList);

        } else {
            fragment = mFragments.get(1);
            args.putString("info", mScenicdetail);
            // mLlSame.setVisibility(View.GONE);
        }
        fragment.setArguments(args);
        return fragment;
    }

}
