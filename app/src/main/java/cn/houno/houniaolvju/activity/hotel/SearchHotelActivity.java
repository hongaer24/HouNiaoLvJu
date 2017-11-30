package cn.houno.houniaolvju.activity.hotel;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.SearchDFAdapter;
import cn.houno.houniaolvju.adapter.TopPicAdapter;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.CustomViewPager;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店搜索
 * 创建人：qzc
 * 创建时间：2016/12/19 11:43
 * 修改人：qzc
 * 修改时间：2016/12/19 11:43
 * 修改备注：
 */
public class SearchHotelActivity extends FragmentActivity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.ly_top_bar)
    RelativeLayout mLyTopBar;
    @Bind(R.id.indicatorview)
    ScrollIndicatorView mIndicatorView;
    @Bind(R.id.viewpager)
    CustomViewPager mViewpager;
    @Bind(R.id.rpv_search)
    RollPagerView mRpvSearch;

    private SearchHotelActivity mActivity;

    private ArrayList<String> topImagesList = new ArrayList<>();    //图片列表

    private TopPicAdapter mTopPicAdapter;

    private SearchDFAdapter mDOAdapter;
    private IndicatorViewPager mIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_search);
        mActivity = SearchHotelActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mRpvSearch.setHintView(new ColorPointHintView(mActivity, Color.parseColor("#009A44"), Color.WHITE));
        mTopPicAdapter = new TopPicAdapter(mRpvSearch);
        mRpvSearch.setAdapter(mTopPicAdapter);

        getDataFromServer();

        mIndicatorView.setScrollBar(new ColorBar(mActivity, getResources().getColor(R.color.app_theme_green), 5));
        float unSelectSize2 = 18;
        //float selectSize = unSelectSize * 1.2f;
        float selectSize2 = unSelectSize2;
        int selectColor2 = getResources().getColor(R.color.app_theme_green);
        int unSelectColor2 = getResources().getColor(R.color.black_txt);
        mIndicatorView.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(selectColor2, unSelectColor2).setSize(selectSize2, unSelectSize2));
        mIndicator = new IndicatorViewPager(mIndicatorView, mViewpager);
        mDOAdapter = new SearchDFAdapter(mActivity, getSupportFragmentManager());
        mIndicator.setAdapter(mDOAdapter);
    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.SEARCH_TOP_PIC);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        setTopPicture(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void setTopPicture(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            JSONArray data = obj.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                topImagesList.add(data.getJSONObject(i).getString("img"));
            }
            if (mTopPicAdapter == null) {
                mTopPicAdapter = new TopPicAdapter(mRpvSearch);
            } else {
                mTopPicAdapter.setImgs(topImagesList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
