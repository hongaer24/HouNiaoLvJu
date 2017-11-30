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

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.ScenicDetailBean;
import cn.houno.houniaolvju.bean.ScenicDetailBean.DataBean.InfoBean;

/**
 * 项目名称：Houniaolvju
 * 类描述：景点详情-门票预订、景点详情
 * 创建人：qzc
 * 创建时间：2016/1/4 17:24
 * 修改人：qzc
 * 修改时间：2016/1/4 17:24
 * 修改备注：
 */
public class ScenicTDAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private Context context;
    private LayoutInflater inflate;
    private String[] tabList = new String[]{"门票预订", "景点详情"};
    ArrayList<Fragment> mFragments;
    private ScenicDetailBean scenicDetailBean;
    private InfoBean infoBean;

    public ScenicTDAdapter(Context context, FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
        super(fragmentManager);
        this.context = context;
        inflate = LayoutInflater.from(context);
        mFragments = fragments;
    }


    public void setData(ScenicDetailBean scenicDetailBean, InfoBean infoBean) {
        this.scenicDetailBean = scenicDetailBean;
        this.infoBean = infoBean;
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
            fragment = mFragments.get(position);
            args.putSerializable("data", (Serializable) scenicDetailBean);
        } else {
            fragment = mFragments.get(position);
            args.putSerializable("data", (Serializable) infoBean);
        }

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
