package cn.houno.houniaolvju.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.iwgang.countdownview.CountdownView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：抢购详情页面
 * 创建人：qzc
 * 创建时间：2016/12/29 11:53
 * 修改人：qzc
 * 修改时间：2016/12/29 11:53
 * 修改备注：
 */
public class FlashSaleDetailActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.iv_share)
    ImageView mIvShare;
    @Bind(R.id.iv_img)
    ImageView mIvImg;
    @Bind(R.id.tv_order_title_txt)
    TextView mTvTitle;
    @Bind(R.id.tv_price)
    TextView mTvPrice;
    @Bind(R.id.cdv_flash_sale_detail)
    CountdownView mCdvFlashSaleDetail;
    @Bind(R.id.wv_detail)
    WebView mWvDetail;
    @Bind(R.id.tv_add_order)
    TextView mTvAddOrder;
    @Bind(R.id.tv_time)
    TextView mTvTime;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;
    @Bind(R.id.rl_content)
    RelativeLayout rlContent;

    private FlashSaleDetailActivity mActivity;
    private String mId;
    private String mTitle;
    private String mImgUrl;
    private String mPrice;
    private String ksDate;
    private String jsDate;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_flash_sale_detail);
        ButterKnife.bind(this);
        mActivity = FlashSaleDetailActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);

        initData();
        initEvent();
    }

    private void initData() {
        //自适应屏幕
        WebSettings settings = mWvDetail.getSettings();
//        // 设置可以支持缩放
        settings.setSupportZoom(true);
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        getDataFromServer();
    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.FLASH_SALE_DETAIL);
        params.addBodyParameter("id", mId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 1) {
                        pbLoading.setVisibility(View.GONE);
                        tvLoading.setText(obj.getString("msg"));
                    } else if (status == 0) {
                        llLoading.setVisibility(View.GONE);
                        rlContent.setVisibility(View.VISIBLE);
                        mIvShare.setVisibility(View.VISIBLE);
                        setDetailData(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                pbLoading.setVisibility(View.GONE);
                tvLoading.setText("加载失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    private void initEvent() {
    }

    private void setDetailData(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            JSONObject infoObject = obj.getJSONObject("data").getJSONObject("info");
            mTitle = infoObject.getString("title");
            mUrl = infoObject.getString("url");
            mTvTitle.setText(mTitle);
            mImgUrl = infoObject.getString("img");
            x.image().bind(mIvImg, mImgUrl, DisplayUtil.getImageOptions());

            mPrice = infoObject.getString("price");
            if (!TextUtils.isEmpty(mPrice)) {
                MyText2Utils.formatYuanPrice(mActivity, mTvPrice, mPrice);
            } else {
                mTvPrice.setText("暂无价格");
            }
            ksDate = infoObject.getString("ksdate");
            jsDate = infoObject.getString("jsdate");

            String currentDate = infoObject.getString("currentDate");
            boolean isNotEnd = DateUtil.compareDate(currentDate, jsDate);
            if (isNotEnd) {
                long milliSecond = DateUtil.getCompareMilliSecond(currentDate, jsDate);
                mCdvFlashSaleDetail.start(milliSecond);
            } else {
                mTvTime.setText("已结束");
                mTvAddOrder.setText("已结束");
                mCdvFlashSaleDetail.setVisibility(View.GONE);
                mTvAddOrder.setBackgroundColor(Color.parseColor("#8C8C8C"));
                mTvAddOrder.setClickable(false);
            }

            String info = infoObject.getString("info");
            mWvDetail.loadDataWithBaseURL(null, info, "text/html", "utf-8", null);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.tv_add_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                shareTheFlashSale();
                break;
            case R.id.tv_add_order:
                if (!TextUtils.isEmpty(mPrice)) {

                        Intent intent = new Intent();
                        intent.putExtra("id", mId);
                        intent.putExtra("title", mTitle);
                        intent.putExtra("price", mPrice);
                        intent.setClass(mActivity, FillInFlashSaleOrderActivity.class);
                        startActivity(intent);

                }
                break;
        }
    }

    private void shareTheFlashSale() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(mTitle);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(mUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("候鸟旅居网-全球候鸟人之家");
        oks.setImageUrl(mImgUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("评论文本...");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mUrl);
        // 启动分享GUI
        oks.show(this);
    }


}
