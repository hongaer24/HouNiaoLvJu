package cn.houno.houniaolvju.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.TextHintView;

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
import cn.houno.houniaolvju.adapter.TopPicAdapter;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：分权详情
 * 创建人：qzc
 * 创建时间：2016/12/29 19:33
 * 修改人：qzc
 * 修改时间：2016/12/29 19:33
 * 修改备注：
 */
public class FenQuanDetailActivity extends Activity {


    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.rpv_fenquan_detail)
    RollPagerView mRpvFenQuanDetail;
    @Bind(R.id.tv_order_title_txt)
    TextView mTvTitle;
//    @Bind(R.id.tv_price)
//    TextView mTvPrice;
    @Bind(R.id.tv_info)
    TextView mTvInfo;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    @Bind(R.id.tv_phone)
    TextView mTvPhone;
    @Bind(R.id.wv_fq_detail)
    WebView mWvFqDetail;
    @Bind(R.id.rl_rvp)
    RelativeLayout mRlRvp;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.ll_content)
    LinearLayout llContent;

    private FenQuanDetailActivity mActivity;
    private String mId;

    private ArrayList<String> topImagesList = new ArrayList<>();    //图片列表
    private TopPicAdapter mTopPicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_fenquan_detail);
        mActivity = FenQuanDetailActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");

        //轮播图
        TextHintView textHintView = new TextHintView(mActivity);
        mRpvFenQuanDetail.setHintView(textHintView);
        mTopPicAdapter = new TopPicAdapter(mRpvFenQuanDetail);
        mRpvFenQuanDetail.setAdapter(mTopPicAdapter);

        getDataFromServer();
    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.FENQUAN_DETAIL);
        params.addBodyParameter("id", mId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 1) {
                        Toast.makeText(mActivity, "没有数据", Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        setDetailData(result);
                        llLoading.setVisibility(View.GONE);
                        llContent.setVisibility(View.VISIBLE);
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

    private void setDetailData(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            JSONObject infoObj = obj.getJSONObject("data").getJSONObject("info");
            mTvTitle.setText(infoObj.getString("title"));
          //  MyText2Utils.formatFenQuanDate(mActivity, mTvPrice, infoObj.getString("price"));
            mTvInfo.setText("物业类型：" + infoObj.getString("property_type")
                    + "\n开盘时间：" + infoObj.getString("kaipan_time")
                    + "\n入住时间：" + infoObj.getString("check_time")
                    + "\n建筑面积：" + infoObj.getString("built_area")
                    + "\n装修状况：" + infoObj.getString("renovation")
                    + "\n现房\\期房：" + infoObj.getString("xianfang_qifang")
                    + "\n开发商：" + infoObj.getString("developes"));
            mTvAddress.setText(infoObj.getString("address"));
            mTvPhone.setText("电话联系：" + infoObj.getString("tel"));
            mWvFqDetail.loadDataWithBaseURL(null, infoObj.getString("info"), "text/html", "utf-8", null);

            JSONArray picture = infoObj.getJSONArray("picture");
            for (int i = 0; i < picture.length(); i++) {
                topImagesList.add(picture.getJSONObject(i).getString("img"));
            }
            if (mTopPicAdapter == null) {
                mTopPicAdapter = new TopPicAdapter(mRpvFenQuanDetail);
            } else {
                mTopPicAdapter.setImgs(topImagesList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}