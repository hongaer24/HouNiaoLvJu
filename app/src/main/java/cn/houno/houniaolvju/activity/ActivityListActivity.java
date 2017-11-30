package cn.houno.houniaolvju.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.ActivityListAdapter;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：Houniaolvju
 * 类描述：活动列表
 * 创建人：qzc
 * 创建时间：2016/12/15 17:50
 * 修改人：qzc
 * 修改时间：2016/12/15 17:50
 * 修改备注：
 */
public class ActivityListActivity extends FragmentActivity {

    private ImageView mIvBack;
    private ScrollIndicatorView mSivActivityList;
    private IndicatorViewPager mIvFlashSale;
    private ViewPager mVpActivityList;
    private ActivityListActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_activity_list);
        mActivity = ActivityListActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mSivActivityList = (ScrollIndicatorView) findViewById(R.id.siv_activity_list);
        mVpActivityList = (ViewPager) findViewById(R.id.vp_activity_list);
    }

    private void initEvent() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        mSivActivityList.setScrollBar(new ColorBar(mActivity, getResources().getColor(R.color.app_theme_green), 5));
        float unSelectSize = 15;
        float selectSize = unSelectSize * 1.2f;
        int selectColor = getResources().getColor(R.color.app_theme_green);
        int unSelectColor = getResources().getColor(R.color.black_txt);
        mSivActivityList.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));
        mVpActivityList.setOffscreenPageLimit(3);
        mIvFlashSale = new IndicatorViewPager(mSivActivityList, mVpActivityList);
        mIvFlashSale.setAdapter(new ActivityListAdapter(mActivity, getSupportFragmentManager()));
    }


}
