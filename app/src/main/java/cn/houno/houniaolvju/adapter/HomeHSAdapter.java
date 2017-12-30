package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;

import java.io.Serializable;
import java.util.ArrayList;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.HomeIndexDataBean;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.TgHotelBean;

/**
 * 项目名称：Houniaolvju
 * 类描述：首页-酒店拼团, 景点门票
 * 创建人：qzc
 * 创建时间：2016/12/13 17:19
 * 修改人：qzc
 * 修改时间：2016/12/13 17:19
 * 修改备注：
 */
public class HomeHSAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private Context context;
    private LayoutInflater inflate;
    private String[] tabList = new String[]{"酒店拼团", "景点门票"};
    private TgHotelBean mTgHotelBean;
    private HomeIndexDataBean.DataBean.ActiToursScenicBean mActiScenicBean;
    private ArrayList<Fragment> mFragments;

    public HomeHSAdapter(Context context, FragmentManager fragmentManager,
                         ArrayList<Fragment> fragments, TgHotelBean tgHotelBean, HomeIndexDataBean.DataBean.ActiToursScenicBean actiScenicBean) {
        super(fragmentManager);
        this.context = context;
        mTgHotelBean = tgHotelBean;
        mActiScenicBean = actiScenicBean;
        mFragments = fragments;
        inflate = LayoutInflater.from(context);
    }

    public HomeHSAdapter(Context context, FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
        super(fragmentManager);
        this.context = context;
        inflate = LayoutInflater.from(context);
        mFragments = fragments;
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
            args.putSerializable("data",  mTgHotelBean);
        } else {
            fragment = mFragments.get(1);
            args.putSerializable("data", (Serializable) mActiScenicBean);
        }
        fragment.setArguments(args);
        return fragment;
    }
}
