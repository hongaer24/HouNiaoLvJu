package cn.houno.houniaolvju.fragment.myinfo.coupon;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.ActivityListAdapter;
import cn.houno.houniaolvju.adapter.CouponListPageAdapter;
import cn.houno.houniaolvju.adapter.HomeHFAdapter;
import cn.houno.houniaolvju.adapter.HomeHSAdapter;
import cn.houno.houniaolvju.fragment.home.HotelBulkPage;
import cn.houno.houniaolvju.fragment.home.MainHotelFqHousesPage;
import cn.houno.houniaolvju.fragment.home.ScenicTicketPage;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.CustomViewPager;

/**
 * 优惠券列表
 * Created by Administrator on 2017/1/20.
 */

public class CouponListActivity extends FragmentActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.siv_coupon_list)
    ScrollIndicatorView sivCouponList;
    @Bind(R.id.vp_coupon_list)
    ViewPager vpCouponList;

    private CouponListPageAdapter mCLAdapter;
    private IndicatorViewPager ivpCouponList;

    private CouponListActivity mActivity;
    //1未使用 2已使用 0已失效

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtils.setWindowStatusBarColor(CouponListActivity.this, R.color.app_theme_green);
        setContentView(R.layout.activity_coupon_list);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mActivity = CouponListActivity.this;
        sivCouponList.setScrollBar(new ColorBar(mActivity, getResources().getColor(R.color.app_theme_green), 5));
        float unSelectSize = 15;
        float selectSize = unSelectSize * 1.2f;
        int selectColor = getResources().getColor(R.color.app_theme_green);
        int unSelectColor = getResources().getColor(R.color.black_txt);
        sivCouponList.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));
        vpCouponList.setOffscreenPageLimit(3);
        ivpCouponList = new IndicatorViewPager(sivCouponList, vpCouponList);
        ivpCouponList.setAdapter(new CouponListPageAdapter(mActivity, getSupportFragmentManager()));
    }

    private void initView() {

    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
