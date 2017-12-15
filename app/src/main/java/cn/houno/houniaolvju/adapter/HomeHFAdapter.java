package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.FenQuanListBean;
//import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.FqHousesBean;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.MainHotelBean;
import cn.houno.houniaolvju.fragment.home.MainHotelFqHousesPage;

/**
 * 项目名称：Houniaolvju
 * 类描述：首页-主推酒店、分权楼盘适配器
 * 创建人：qzc
 * 创建时间：2016/12/13 17:19
 * 修改人：qzc
 * 修改时间：2016/12/13 17:19
 * 修改备注：
 */
public class HomeHFAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private String[] tabList = new String[]{"特价酒店", "精品线路"};
    private Context context;
    private LayoutInflater inflate;
    List<MainHotelBean> mMainHotel;
    List<FenQuanListBean.DataBean> mShareProperty;
    List<MainHotelFqHousesPage> mFragments;

    public HomeHFAdapter(Context context, FragmentManager fragmentManager,ArrayList<MainHotelFqHousesPage> fragments) {
        super(fragmentManager);
        this.context = context;
        mFragments = fragments;
        inflate = LayoutInflater.from(context);

    }

    public HomeHFAdapter(Context context, FragmentManager fragmentManager
            , ArrayList<MainHotelFqHousesPage> fragments, ArrayList<MainHotelBean> mainHotel
            , List<FenQuanListBean.DataBean> shareProperty) {
        super(fragmentManager);
        this.context = context;
        inflate = LayoutInflater.from(context);
        mMainHotel = mainHotel;
        mShareProperty = shareProperty;
        mFragments = fragments;
    }

    public void setMainHotelData(List<MainHotelBean> mainHotel) {
        mMainHotel = mainHotel;
//        if (mFragments.size() != 0) {
//            mFragments.clear();
//            mFragments.add(new MainHotelFqHousesPage());
//            mFragments.add(new MainHotelFqHousesPage());
//        }
        notifyDataSetChanged();
    }

    public void setFQHouseData(List<FenQuanListBean.DataBean> shareProperty) {
        mShareProperty = shareProperty;
//        if (mFragments.size() != 0) {
//            mFragments.clear();
//            mFragments.add(new MainHotelFqHousesPage());
//            mFragments.add(new MainHotelFqHousesPage());
//        }
        notifyDataSetChanged();
    }


    public void setData(ArrayList<MainHotelBean> mainHotel, List<FenQuanListBean.DataBean> shareProperty) {
        mMainHotel = mainHotel;
        mShareProperty = shareProperty;
        notifyDataSetChanged();
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
          //  fragment = new MainHotelFqHousesPage();
            fragment =mFragments.get(0);
            args.putString("page", "mainhotel");
            args.putSerializable("data", (Serializable) mMainHotel);

        } else {
           // fragment = new MainHotelFqHousesPage();
            fragment =mFragments.get(1);
            args.putString("page", "fenquan");
            args.putSerializable("data", (Serializable) mShareProperty);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
