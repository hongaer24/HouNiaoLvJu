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

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.hotel.SearchDomesticPage;
import cn.houno.houniaolvju.activity.hotel.SearchForeignPage;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店搜索
 * 创建人：qzc
 * 创建时间：2016/12/19 14:29
 * 修改人：qzc
 * 修改时间：2016/12/19 14:29
 * 修改备注：
 */
public class SearchDFAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private String[] tabList = new String[]{"国内·港澳台", "海外酒店"};
    private Context context;
    private LayoutInflater inflate;
   // private String mData;

    public SearchDFAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
        inflate = LayoutInflater.from(context);
    }

//    public SearchDFAdapter(Context context, FragmentManager fragmentManager, String data) {
//        super(fragmentManager);
//        this.context = context;
//        inflate = LayoutInflater.from(context);
//        mData = data;
//    }

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
        if (position == 0) {
            fragment = new SearchDomesticPage();
         //   args.putString("data", mData);

        } else {
            fragment = new SearchForeignPage();
          //  args.putString("data", "");

        }
       // fragment.setArguments(args);
        return fragment;
    }
}