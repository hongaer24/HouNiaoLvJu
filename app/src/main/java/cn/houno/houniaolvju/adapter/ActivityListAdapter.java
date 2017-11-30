package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.IndicatorViewPager;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.fragment.home.ActivityFragment;
import cn.houno.houniaolvju.utils.DisplayUtil;

/**
 * 项目名称：Houniaolvju
 * 类描述：限时抢购适配器
 * 创建人：qzc
 * 创建时间：2016/12/15 18:08
 * 修改人：qzc
 * 修改时间：2016/12/15 18:08
 * 修改备注：
 */
public class ActivityListAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private String[] tabList = new String[]{"酒店拼团", "特价酒店", "景点门票"};
    private Context context;
    private LayoutInflater inflate;


    public ActivityListAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
        inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tabList.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.tab_hs_top, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(tabList[position]);
        int witdh = getTextWidth(textView);
        int padding = DisplayUtil.dipToPix(context, 8);
        //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
        //1.3f是根据上面字体大小变化的倍数1.3f设置
        textView.setWidth((int) (witdh * 1.3f) + padding);
        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        Fragment fragment;
        Bundle args = new Bundle();
        fragment = new ActivityFragment();
        args.putInt("tag", position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getItemPosition(Object object) {
        //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
        // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
        return PagerAdapter.POSITION_UNCHANGED;
    }

    private int getTextWidth(TextView textView) {
        if (textView == null) {
            return 0;
        }
        Rect bounds = new Rect();
        String text = textView.getText().toString();
        Paint paint = textView.getPaint();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.left + bounds.width();
        return width;
    }
}