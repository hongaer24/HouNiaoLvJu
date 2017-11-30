package cn.houno.houniaolvju.activity.hotel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.TextHintView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

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
import cn.houno.houniaolvju.activity.foreignhotel.ForeignHotelDetailCommentPage;
import cn.houno.houniaolvju.activity.foreignhotel.ForeignHotelDetailDetailPage;
import cn.houno.houniaolvju.adapter.ForeignHotelCDAdapter;
import cn.houno.houniaolvju.adapter.ForeignOtherHotelAdapter;
import cn.houno.houniaolvju.adapter.HotelCDAdapter;
import cn.houno.houniaolvju.adapter.HotelRoomListAdapter;
import cn.houno.houniaolvju.adapter.OtherHotelAdapter;
import cn.houno.houniaolvju.adapter.TopPicAdapter;
import cn.houno.houniaolvju.bean.ForeignHotelDetailBean;
import cn.houno.houniaolvju.bean.HotelDetailBean;
import cn.houno.houniaolvju.bean.HotelDetailBean.DataBean.CommentBean;
import cn.houno.houniaolvju.bean.HotelDetailBean.DataBean.InfoBean;
import cn.houno.houniaolvju.bean.HotelDetailBean.DataBean.InfoBean.AllTodayRoomBean;
import cn.houno.houniaolvju.bean.HotelDetailBean.DataBean.OtherHotelBean;
import cn.houno.houniaolvju.bean.HotelDetailBean.DataBean.PictureBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.CustomViewPager;
import cn.houno.houniaolvju.view.InnerExpandableListView;
import cn.houno.houniaolvju.view.InnerListView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店详情
 * 创建人：qzc
 * 创建时间：2016/12/23 16:51
 * 修改人：qzc
 * 修改时间：2016/12/23 16:51
 * 修改备注：
 */
public class HotelDetailActivity extends FragmentActivity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.tv_topbar_title)
    TextView mTvTopbarTitle;
    @Bind(R.id.iv_share)
    ImageView mIvShare;

    @Bind(R.id.rpv_yd_detail)
    RollPagerView mRpvHotelDetail;
    @Bind(R.id.tv_hotel_address)
    TextView mTvHotelAddress;

    @Bind(R.id.siv_hotel_cd)
    ScrollIndicatorView mSivHotelCd;
    @Bind(R.id.vp_hotel_cd)
    CustomViewPager mVpHotelCd;
    @Bind(R.id.elv_hotel_room)
    InnerExpandableListView mElvHotelRoom;
    @Bind(R.id.lv_other_hotel)
    InnerListView mLvOtherHotel;
    @Bind(R.id.ll_other_hotel)
    LinearLayout mLlOtherHotel;
    @Bind(R.id.rl_hotel_map)
    RelativeLayout mRlHotelMap;
    @Bind(R.id.iv_favor)
    ImageView ivFavor;
    @Bind(R.id.view_room_list)
    View viewRoomList;
    @Bind(R.id.ll_hotel_map)
    LinearLayout llHotelMap;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.sv_content)
    ScrollView svContent;


    private HotelDetailActivity mActivity;
    private String userid;
    //==================轮播图===============
    private ArrayList<String> topImagesList = new ArrayList<>();    //图片列表
    private ArrayList<String> topUrl = new ArrayList<>();   //轮播图对应房间地址
    private TopPicAdapter mTopPicAdapter;
    //住客点评、酒店详情
    private IndicatorViewPager mIvpCD;
    private HotelCDAdapter mCDAdapter;
    //房价列表
    private List<AllTodayRoomBean> groupArray = new ArrayList<>();
    private HotelRoomListAdapter mHotelRoomListAdapter;
    //周边酒店
    private List<OtherHotelBean> mOtherHotelList = new ArrayList<>();
    private OtherHotelAdapter mOtherHotelAdapter;

    private String mFrom;
    private String mUrl;
    private String mCheckIn;
    private String mCheckOut;
    private String mDays;
    private String mHid;
    private String mHotelTitle = "候鸟旅居网";
    private String mHotelImg = "http://a1.qpic.cn/psb?/V12UKhhx2HXvEt/l0em0ffRGYVyoMpHg.uNSpu2QfLKuutgaoqpYm8EqfQ!/b/dLEAAAAAAAAA&bo=gAKAAgAAAAADByI!&rf=viewer_4";
    private String mHotelUrl = "http://wx.houno.cn";
    private String mHotelAddress;   //地址
    private String mLat;
    private String mLng;
    private ArrayList<Fragment> fragments;

    private List<ForeignHotelDetailBean.DataBean.OtherHotelBean> fOtherHotelList = new ArrayList<>();
    private ForeignOtherHotelAdapter fOtherHotelAdapter;   //国际周边
    private ForeignHotelCDAdapter fCDAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_hotel_detail);
        mActivity = HotelDetailActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    private void initEvent() {
        mLvOtherHotel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String hid = null;
                if ("home".equals(mFrom)) {
                    hid = mOtherHotelList.get(position).getId();
                } else if ("foreign".equals(mFrom)) {
                    hid = fOtherHotelList.get(position).getId();
                }
                Intent intent = new Intent();
                intent.putExtra("from", mFrom);
                intent.putExtra("hid", hid);
                intent.setClass(mActivity, HotelDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        userid = PrefUtils.getString(mActivity, "userid", "");
        Intent intent = getIntent();
        mFrom = intent.getStringExtra("from");
        if ("home".equals(mFrom)) {
            mUrl = Constants.HOTEL_DETAIL;
        } else if ("foreign".equals(mFrom)) {
            mUrl = Constants.FOREIGN_HOTEL_DETAIL;
            mIvShare.setVisibility(View.INVISIBLE);
        }else {
            mFrom="home";
            mUrl = Constants.HOTEL_DETAIL;
        }
        mHid = intent.getStringExtra("hid");
        Log.i("hid", mHid);
        mCheckIn = intent.getStringExtra("checkIn");
        mCheckOut = intent.getStringExtra("checkOut");
        mDays = intent.getStringExtra("days");

        if (mCheckIn == null || TextUtils.isEmpty(mCheckIn)) {
            mCheckIn = DateUtil.getNowTime(DateUtil.DATE_SMALL_STR);
        }

        if (mCheckOut == null || TextUtils.isEmpty(mCheckOut)) {
            mCheckOut = DateUtil.getTomorrowTime(mCheckIn, DateUtil.DATE_SMALL_STR);
        }
        if (mDays == null || TextUtils.isEmpty(mDays)) {
            mDays = "1";
        }
        //轮播图
        TextHintView textHintView = new TextHintView(mActivity);

        mRpvHotelDetail.setHintView(textHintView);
        mTopPicAdapter = new TopPicAdapter(mRpvHotelDetail);
        mRpvHotelDetail.setAdapter(mTopPicAdapter);

        //住客点评、酒店详情


        mSivHotelCd.setScrollBar(new ColorBar(mActivity, getResources().getColor(R.color.app_theme_green), 5));
        float unSelectSize2 = 18;
        //float selectSize = unSelectSize * 1.2f;
        float selectSize2 = unSelectSize2;
        int selectColor2 = getResources().getColor(R.color.app_theme_green);
        int unSelectColor2 = getResources().getColor(R.color.black_txt);
        mSivHotelCd.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(selectColor2, unSelectColor2).setSize(selectSize2, unSelectSize2));
        mIvpCD = new IndicatorViewPager(mSivHotelCd, mVpHotelCd);
        if ("home".equals(mFrom)) {
            fragments = new ArrayList<>();
            fragments.add(new HotelDetailCommentPage());
            fragments.add(new HotelDetailDetailPage());
            mCDAdapter = new HotelCDAdapter(mActivity, getSupportFragmentManager(), fragments);
            mIvpCD.setAdapter(mCDAdapter);
        } else {
            fragments = new ArrayList<>();
            fragments.add(new ForeignHotelDetailCommentPage());
            fragments.add(new ForeignHotelDetailDetailPage());
            fCDAdapter = new ForeignHotelCDAdapter(mActivity, getSupportFragmentManager(), fragments);
            mIvpCD.setAdapter(fCDAdapter);
        }

        //房价列表
        mHotelRoomListAdapter = new HotelRoomListAdapter(mActivity, groupArray);
        mHotelRoomListAdapter.setCheckDate(mCheckIn,mCheckOut);
        mElvHotelRoom.setAdapter(mHotelRoomListAdapter);

        getDataFromServer();
    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(mUrl);
        params.addBodyParameter("hid", mHid);
        params.addBodyParameter("userid", userid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        llLoading.setVisibility(View.GONE);
                        svContent.setVisibility(View.VISIBLE);
                        mIvShare.setVisibility(View.VISIBLE);
                        if ("home".equals(mFrom)) {
                            parseData(result);
                        } else if ("foreign".equals(mFrom)) {
                            parseForeignData(result);
                        }
                    } else {
                        //没有酒店数据
                        pbLoading.setVisibility(View.GONE);
                        tvLoading.setText(obj.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                pbLoading.setVisibility(View.GONE);
                tvLoading.setText("加载失败");
                Log.e("HotelDetailActivity", ex.getCause() + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    /*
    * 国内酒店详情数据解析
    * */
    private void parseData(String result) {
        Gson gson = new Gson();
        HotelDetailBean hotelDetailBean = null;
        try {
            hotelDetailBean = gson.fromJson(result, HotelDetailBean.class);
        } catch (Exception e) {
            Log.i("HotelDetailActivity", e.getMessage());
        }
        //轮播图
        List<PictureBean> picture = hotelDetailBean.getData().getPicture();
        for (int i = 0; i < picture.size(); i++) {
            topImagesList.add(picture.get(i).getImg());
        }
        if (mTopPicAdapter == null) {
            mTopPicAdapter = new TopPicAdapter(mRpvHotelDetail);
        } else {
            mTopPicAdapter.setImgs(topImagesList);
        }

        //收藏
        if (hotelDetailBean.getData().getInfo().getIsfavorite() != null) {
            String isFavorite = hotelDetailBean.getData().getInfo().getIsfavorite();    //是否已经收藏
            if (!"0".equals(isFavorite)) {
                ivFavor.setImageResource(R.drawable.icon_after_favor);
            }
        }
        //酒店名称、地址、网址等信息
        InfoBean infoBean = hotelDetailBean.getData().getInfo();
        mHotelTitle = infoBean.getTitle();
        mHotelAddress = infoBean.getAddress();
        if (infoBean.getImg() != null || !TextUtils.isEmpty(infoBean.getImg())) {
            mHotelImg = infoBean.getImg();
        }
        if (infoBean.getUrl() != null || !TextUtils.isEmpty(infoBean.getUrl())) {
            mHotelUrl = infoBean.getUrl();
        }
        mTvTopbarTitle.setText(mHotelTitle);
        mTvHotelAddress.setText(mHotelAddress);
        if (infoBean.getMap().size() == 2) {
            mLat = infoBean.getMap().get(0);
            mLng = infoBean.getMap().get(1);
        } else {
            llHotelMap.setVisibility(View.GONE);
        }
        //住客点评、酒店详情
        List<CommentBean> comment = hotelDetailBean.getData().getComment();
        mCDAdapter.setData(comment, infoBean);

        //房价列表
        groupArray = infoBean.getAllTodayRoom();
        mHotelRoomListAdapter.setData(groupArray, mHotelTitle, mHotelAddress);
        mElvHotelRoom.expandGroup(0);   //默认展开第一组

        //周边酒店
        mOtherHotelList = hotelDetailBean.getData().getOtherHotel();

        if (mOtherHotelList.size() != 0) {
            if (mOtherHotelAdapter == null) {
                mOtherHotelAdapter = new OtherHotelAdapter(mActivity, mOtherHotelList);
                mLvOtherHotel.setAdapter(mOtherHotelAdapter);
            } else {
                mOtherHotelAdapter.setData(mOtherHotelList);
            }
        } else {
            mLlOtherHotel.setVisibility(View.GONE);
        }
    }

    /*
    * 国外酒店详情数据解析
    * */
    private void parseForeignData(String result) {
        Gson gson = new Gson();
        ForeignHotelDetailBean foreignBean = gson.fromJson(result, ForeignHotelDetailBean.class);
        //轮播图
        List<ForeignHotelDetailBean.DataBean.PictureBean> picture = foreignBean.getData().getPicture();
        for (int i = 0; i < picture.size(); i++) {
            topImagesList.add(picture.get(i).getImg());
        }
        if (mTopPicAdapter == null) {
            mTopPicAdapter = new TopPicAdapter(mRpvHotelDetail);
        } else {
            mTopPicAdapter.setImgs(topImagesList);
        }
        //隐藏收藏
        ivFavor.setVisibility(View.GONE);
        //酒店名称、地址、网址等信息
        ForeignHotelDetailBean.DataBean.InfoBean infoBean = foreignBean.getData().getInfo();

        mHotelTitle = infoBean.getTitle();
        mHotelAddress = infoBean.getAddress();
        if (infoBean.getImg() != null || !TextUtils.isEmpty(infoBean.getImg())) {
            mHotelImg = infoBean.getImg();
        }

        mTvTopbarTitle.setText(mHotelTitle);
        mTvHotelAddress.setText(mHotelAddress);
        if (infoBean.getMap().size() == 2) {
            mLat = infoBean.getMap().get(0);
            mLng = infoBean.getMap().get(1);
        } else {
            llHotelMap.setVisibility(View.GONE);
        }
        //住客点评、酒店详情
        fCDAdapter.setData(infoBean);

        //房价列表
        mElvHotelRoom.setVisibility(View.GONE);
        viewRoomList.setVisibility(View.GONE);
        //周边酒店
        fOtherHotelList = foreignBean.getData().getOtherHotel();

        if (fOtherHotelList.size() != 0) {
            if (fOtherHotelAdapter == null) {
                fOtherHotelAdapter = new ForeignOtherHotelAdapter(mActivity, fOtherHotelList);
                mLvOtherHotel.setAdapter(fOtherHotelAdapter);
            } else {
                fOtherHotelAdapter.setData(fOtherHotelList);
            }
        } else {
            mLlOtherHotel.setVisibility(View.GONE);
        }

    }


    @OnClick({R.id.iv_back, R.id.iv_share, R.id.iv_favor})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                shareTheHotel();
                break;
            case R.id.iv_favor:
                postFavorToServer();
                break;
        }
    }

    /*
  * 提交收藏
  * */
    private void postFavorToServer() {
        RequestParams params = new RequestParams(Constants.ADD_FAVOR);
        params.addBodyParameter("model", "room");
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("type", "hotel");
        params.addBodyParameter("aid", mHid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                try {
                    System.out.println(result);
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        ivFavor.setImageResource(R.drawable.icon_after_favor);
                    }
                    Toast.makeText(HotelDetailActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
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

    private void shareTheHotel() {

        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(mHotelTitle);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(mHotelUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("候鸟旅居网-全球候鸟人之家");
        oks.setImageUrl(mHotelImg);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mHotelUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("评论文本...");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mHotelUrl);
        // 启动分享GUI
        oks.show(this);

    }

    @OnClick(R.id.rl_hotel_map)
    public void onClick() {
        if (mLat != null && !TextUtils.isEmpty(mLat)) {
            Intent intent = new Intent();
            intent.putExtra("title", mHotelTitle);
            intent.putExtra("address", mHotelAddress);
            intent.putExtra("lat", mLat);
            intent.putExtra("lng", mLng);
            intent.setClass(mActivity, HotelMapActivity.class);
            startActivity(intent);
        }
    }
}
