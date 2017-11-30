package cn.houno.houniaolvju.activity.ydhotel;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.TextHintView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.hotel.HotelMapActivity;
import cn.houno.houniaolvju.adapter.TopPicAdapter;
import cn.houno.houniaolvju.adapter.YdRoomPriceAdapter;
import cn.houno.houniaolvju.bean.YdDetailBean;
import cn.houno.houniaolvju.bean.YdDetailBean.DataBean.InfoBean;
import cn.houno.houniaolvju.bean.YdDetailBean.DataBean.PictureBean;
import cn.houno.houniaolvju.bean.YdDetailBean.DataBean.RoomBean;
import cn.houno.houniaolvju.fragment.myinfo.LoginActivity;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerListView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 异养详情
 * Created by qzc on 2017/1/16.
 */

public class YdDetailActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @Bind(R.id.iv_share)
    ImageView ivShare;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.rpv_yd_detail)
    RollPagerView rpvYdDetail;
    @Bind(R.id.tv_yd_detail_title)
    TextView tvYdDetailTitle;
    @Bind(R.id.tv_yd_detail_phone)
    TextView tvYdDetailPhone;
    @Bind(R.id.tv_yd_detail_address)
    TextView tvYdDetailAddress;
    @Bind(R.id.lv_yd_room)
    InnerListView lvYdRoom;
    @Bind(R.id.view_room_list)
    View viewRoomList;
    @Bind(R.id.wv_yd_detail_detail)
    WebView wvYdDetailDetail;
    @Bind(R.id.wv_yd_detail_alert)
    WebView wvYdDetailAlert;
    @Bind(R.id.sv_content)
    ScrollView svContent;
    @Bind(R.id.tv_yd_detail_opentime)
    TextView tvYdDetailOpentime;
    @Bind(R.id.tv_yd_detail_roomnum)
    TextView tvYdDetailRoomnum;
    @Bind(R.id.ll_yd_detail_address)
    LinearLayout llYdDetailAddress;

    private YdDetailActivity mActivity;
    private String mId;
    private String mTitle;
    private String mAddress;
    private String mPhone;
    private String mUrl;
    private String mImg;
    private String mLat;
    private String mLng;
    //轮播图
    private ArrayList<String> topImagesList = new ArrayList<>();    //图片列表
    private TopPicAdapter mTopPicAdapter;
    //房价列表
    private List<RoomBean> groupArray = new ArrayList<>();
    private YdRoomPriceAdapter ydRoomListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_yd_detail);
        ButterKnife.bind(this);
        mActivity = YdDetailActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

    }

    private void initData() {
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        mTitle = intent.getStringExtra("title");
        tvTopbarTitle.setText(mTitle);

        TextHintView textHintView = new TextHintView(this);
        textHintView.setTextColor(Color.parseColor("#ffffff"));
        rpvYdDetail.setHintView(textHintView);
        mTopPicAdapter = new TopPicAdapter(rpvYdDetail);
        rpvYdDetail.setAdapter(mTopPicAdapter);

        //房价列表
        ydRoomListAdapter = new YdRoomPriceAdapter(mActivity, groupArray);

        lvYdRoom.setAdapter(ydRoomListAdapter);

        getDataFromServer();
    }

    private void initEvent() {

    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.GET_YD_DETAIL);
        params.addBodyParameter("id", mId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("yddetail", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        llLoading.setVisibility(View.GONE);
                        svContent.setVisibility(View.VISIBLE);
                        ivShare.setVisibility(View.VISIBLE);
                        parseData(result);
                    } else {
                        //没有异养详情数据
                        pbLoading.setVisibility(View.GONE);
                        tvLoading.setText(obj.getString("msg"));
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

    private void parseData(String result) {
        Gson gson = new Gson();
        YdDetailBean ydDetailBean = gson.fromJson(result, YdDetailBean.class);
        ydDetailBean.getData().getPicture();

        //轮播图
        List<PictureBean> picture = ydDetailBean.getData().getPicture();
        for (int i = 0; i < picture.size(); i++) {
            topImagesList.add(picture.get(i).getImg());
        }
        if (mTopPicAdapter == null) {
            mTopPicAdapter = new TopPicAdapter(rpvYdDetail);
        } else {
            mTopPicAdapter.setImgs(topImagesList);
        }

        //异养信息
        InfoBean infoBean = ydDetailBean.getData().getInfo();
        if (!infoBean.getTitle().equals(mTitle)) {
            mTitle = infoBean.getTitle();
            tvTopbarTitle.setText(mTitle);
            tvYdDetailTitle.setText(mTitle);
        }
        mPhone = infoBean.getTelphone();
        tvYdDetailPhone.setText(mPhone);
        mAddress = infoBean.getAddress();
        tvYdDetailAddress.setText(mAddress);
        mImg = infoBean.getImg();
        tvYdDetailOpentime.setText(infoBean.getOpentime());
        tvYdDetailRoomnum.setText(infoBean.getRoom_num());
        mUrl = infoBean.getUrl();
        if (!MyText2Utils.isEmpty(infoBean.getMap())) {
            String map = infoBean.getMap();
            String[] latAndLng;
            if (map.contains(",")) {
                latAndLng = map.split(",");
                mLng = latAndLng[0];
                mLat = latAndLng[1];
            } else if (map.contains("|")) {
                latAndLng = map.split("\\|");
                mLat = latAndLng[0];
                mLng = latAndLng[1];
            }
        }
        wvYdDetailDetail.loadDataWithBaseURL(null, infoBean.getContent(), "text/html", "utf-8", null);
        wvYdDetailAlert.loadDataWithBaseURL(null, infoBean.getAlert(), "text/html", "utf-8", null);
        //房价
        groupArray = ydDetailBean.getData().getRoom();
        ydRoomListAdapter.setData(groupArray,mTitle,mAddress);
    }


    private void shareTheYdHotel() {

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
        oks.setImageUrl(mImg);
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

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.ll_yd_detail_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                shareTheYdHotel();
                break;
            case R.id.ll_yd_detail_address:
                if (mLat != null && !TextUtils.isEmpty(mLat)) {
                    Intent intent = new Intent();
                    intent.putExtra("title", mTitle);
                    intent.putExtra("address", mAddress);
                    intent.putExtra("lat", mLat);
                    intent.putExtra("lng", mLng);
                    intent.setClass(mActivity, HotelMapActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }


}
