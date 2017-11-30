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

import java.util.ArrayList;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.ActiScenicBean;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.TgHotelBean;
import cn.houno.houniaolvju.fragment.home.ActivityFragment;
import cn.houno.houniaolvju.fragment.myinfo.coupon.CouponListPage;

/**
 * 项目名称：Houniaolvju
 * 类描述：首页-酒店拼团, 景点门票
 * 创建人：qzc
 * 创建时间：2016/12/13 17:19
 * 修改人：qzc
 * 修改时间：2016/12/13 17:19
 * 修改备注：
 */
public class CouponListPageAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private Context context;
    private LayoutInflater inflate;
    private String[] tabList = new String[]{"已使用", "未使用/已失效"};


    public CouponListPageAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
        inflate = LayoutInflater.from(context);
    }

    @Override
    public int getItemPosition(Object object) {
        //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
        // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
        return PagerAdapter.POSITION_UNCHANGED;
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
        fragment = new CouponListPage();
        args.putInt("tag", position);
        fragment.setArguments(args);
        return fragment;
    }
}
