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
import cn.houno.houniaolvju.bean.ForeignHotelDetailBean;
import cn.houno.houniaolvju.bean.HotelDetailBean.DataBean.CommentBean;
import cn.houno.houniaolvju.bean.HotelDetailBean.DataBean.InfoBean;

/**
 * 项目名称：Houniaolvju
 * 类描述：国外酒店详情-住客点评、酒店详情
 * 创建人：qzc
 * 创建时间：2016/12/23 19:24
 * 修改人：qzc
 * 修改时间：2016/12/23 19:24
 * 修改备注：
 */
public class ForeignHotelCDAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private Context context;
    private LayoutInflater inflate;
    private String[] tabList = new String[]{"住客点评", "酒店详情"};
    ArrayList<Fragment> mFragments;

    private List<CommentBean> commentList;

    private ForeignHotelDetailBean.DataBean.InfoBean foregnInfoBean;

    public ForeignHotelCDAdapter(Context context, FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
        super(fragmentManager);
        this.context = context;
        inflate = LayoutInflater.from(context);
        mFragments = fragments;
    }


    public void setData(ForeignHotelDetailBean.DataBean.InfoBean foregnInfoBean) {
        this.foregnInfoBean = foregnInfoBean;
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
            args.putSerializable("data", (Serializable) commentList);

        } else {
            fragment = mFragments.get(position);
            args.putSerializable("data", (Serializable) foregnInfoBean);

        }
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
