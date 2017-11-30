package cn.houno.houniaolvju.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.houno.houniaolvju.R;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import cn.houno.houniaolvju.fragment.orderpage.EndOrderPager;
import cn.houno.houniaolvju.fragment.orderpage.IngOrderPager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.view.NoScrollViewPager;
/**
 * 项目名称：Houniaolvju
 * 类描述：订单页面
 * 创建人：qzc
 * 创建时间：2016/12/12 15:17
 * 修改人：qzc
 * 修改时间：2016/12/12 15:17
 * 修改备注：
 */
public class OrderFragment extends BaseFragment  implements View.OnClickListener{



    private ImageView ivodtg;
    private TextView rbing;
    private TextView rbend;
    private OrderAdapter orderAdapter;
    private NoScrollViewPager mViewPager;
    private SlidingMenu slidingMenu;

    @Override
    public View initView(LayoutInflater inflater,ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_order,container,false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        ivodtg = (ImageView) view.findViewById(R.id.iv_order_toggle);

        rbing = (TextView) view.findViewById(R.id.rb_ddlb_ing);

        rbend = (TextView) view.findViewById(R.id.rb_ddlb_end);

        mViewPager = (NoScrollViewPager) view.findViewById(R.id.no_vp_order);


    }

    private List<Fragment> mPagerlist = new ArrayList<>();



    @Override
    public void initData() {
        this.slidingMenu = mActivity.getLeftMenu();
        IngOrderPager ingOrderPager = new IngOrderPager();
        EndOrderPager endOrderPager = new EndOrderPager();

        mPagerlist.add(ingOrderPager);
        mPagerlist.add(endOrderPager);

        orderAdapter = new OrderAdapter(getFragmentManager());

        mViewPager.setAdapter(orderAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //获取当前的位置
                int current = mViewPager.getCurrentItem();
                setTab(current);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setTabSelection(0);

    }

    /**
     * 根据点击的位置对应Tab
     *
     * @param intdx
     */
    private void setTabSelection(int intdx) {
        setTab(intdx);
        mViewPager.setCurrentItem(intdx);
    }



    private void setTab(int i) {
        resetText();
        switch (i) {
            case 0:
                rbing.setTextColor(Color.parseColor("#E75343"));
                break;
            case 1:
                rbend.setTextColor(Color.parseColor("#E75343"));
                break;
        }
    }

    private void resetText() {
        rbing.setTextColor(Color.parseColor("#505050"));
        rbend.setTextColor(Color.parseColor("#505050"));
    }

    @Override
    protected void initEvent() {
        ivodtg.setOnClickListener(this);
        rbing.setOnClickListener(this);
        rbend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_order_toggle:
                slidingMenu.toggle();
                break;
            case R.id.rb_ddlb_ing:
                setTabSelection(0);
                break;
            case R.id.rb_ddlb_end:
                setTabSelection(1);
                break;
        }
    }

    class OrderAdapter extends FragmentPagerAdapter {

        public OrderAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return mPagerlist.get(position);
        }

        @Override
        public int getCount() {

            return mPagerlist.size();

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (PrefUtils.getBoolean(mActivity, "orderreset", false)){
            setTabSelection(0);
            PrefUtils.setBoolean(mActivity, "orderreset", false);
        }
    }
}
