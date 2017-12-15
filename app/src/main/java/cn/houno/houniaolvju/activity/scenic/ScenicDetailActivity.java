package cn.houno.houniaolvju.activity.scenic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
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
import cn.houno.houniaolvju.activity.hotel.CommentListActivity;
import cn.houno.houniaolvju.activity.hotel.HotelMapActivity;
//import cn.houno.houniaolvju.adapter.ScenicDetailCommentAdapter;
import cn.houno.houniaolvju.adapter.ScenicOtherAdapter;
import cn.houno.houniaolvju.adapter.ScenicTicketAdapter;
import cn.houno.houniaolvju.adapter.TopPicAdapter;
import cn.houno.houniaolvju.bean.ScenicDetailBean;
//import cn.houno.houniaolvju.bean.ScenicDetailBean.DataBean.CommentBean;
import cn.houno.houniaolvju.bean.ScenicDetailBean.DataBean.InfoBean;
import cn.houno.houniaolvju.bean.ScenicDetailBean.DataBean.OtherBean;
//import cn.houno.houniaolvju.bean.ScenicDetailBean.DataBean.PictureBean;
//import cn.houno.houniaolvju.bean.ScenicDetailBean.DataBean.TicketBean.TicketDataBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.view.GradationScrollView;
import cn.houno.houniaolvju.view.InnerGridView;
import cn.houno.houniaolvju.view.InnerListView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：景点详情
 * 创建人：qzc
 * 创建时间：2017/1/4 14:30
 * 修改人：qzc
 * 修改时间：2017/1/4 14:30
 * 修改备注：
 */
public class ScenicDetailActivity extends FragmentActivity implements GradationScrollView.ScrollViewListener {


    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.rpv_scenic_detail)
    RollPagerView rpvScenicDetail;
    @Bind(R.id.tv_order_title_txt)
    TextView tvTitle;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.ll_scenic_address)
    LinearLayout llScenicAddress;
    @Bind(R.id.lv_ticket)
    InnerListView lvTicket;
    @Bind(R.id.tv_detail_book)
    TextView tvDetailBook;
    @Bind(R.id.ll_webview_book)
    LinearLayout llWebviewBook;
    @Bind(R.id.wv_scenic_detail_book)
    WebView wvScenicDetailBook;
    @Bind(R.id.tv_detail_info)
    TextView tvDetailInfo;
    @Bind(R.id.ll_webview_info)
    LinearLayout llWebviewInfo;
    @Bind(R.id.wv_scenic_detail_info)
    WebView wvScenicDetailInfo;
    @Bind(R.id.lv_scenic_comment)
    InnerListView lvScenicComment;
    @Bind(R.id.ll_comment)
    LinearLayout llComment;
    @Bind(R.id.gv_scenic_other)
    InnerGridView gvScenicOther;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    @Bind(R.id.sv_scenic)
    GradationScrollView svScenic;
    @Bind(R.id.rfv_scenic_detail)
    XRefreshView rfvScenicDetail;
    @Bind(R.id.rl_scenic_roll)
    RelativeLayout rlScenicRoll;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @Bind(R.id.iv_share)
    ImageView ivShare;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    private ScenicDetailActivity mActivity;
    private String mId;

    private ArrayList<String> topImagesList = new ArrayList<>();    //图片列表
    private TopPicAdapter mTopPicAdapter;
    private String mTitle;
    private String mAddress;
    private String mLat;
    private String mLng;
    //门票
    //private List<TicketDataBean> mTicketList;
    private ScenicTicketAdapter mTicketAdapter;
    //评论
    private LayoutInflater inflater;
    private RelativeLayout headerView;
    private TextView mTvCommentRate;
    private TextView mTvCommentCount;
    private RelativeLayout footerView;
    private TextView mTvCommentMore;
   // private List<CommentBean.CommentDataBean> mCommentList;
   // private ScenicDetailCommentAdapter mCommentAdapter;

    private int mAverage = -1;    //总体满意度
    private String mCommentnum; //评论条数
    //周边
    private List<OtherBean> mOtherScenicList;
    private ScenicOtherAdapter mOtherAdapter;
    private String mUrl;
    private String mImg;

    private int mRollViewPagerHeight;   //轮播图高度
    private int mStatusBarHeight;   //状态栏高度
    private int mTopBarHeight;  //标题栏高度
    private int mTopHeight; //标题栏+状态栏高度+轮播图高度
    private String userid;
    private List<InfoBean.TicketlistBean> mTicketList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_scenic_detail);
        ButterKnife.bind(this);
        mActivity = ScenicDetailActivity.this;
        initData();
        initEvent();
    }

    private void initData() {

        //获取状态栏高度
        mStatusBarHeight = -1;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            mStatusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rlTopBar.setPadding(10, mStatusBarHeight + 10, 10, 10);
        }

        Intent intent = getIntent();
        mId = intent.getStringExtra("scenicid");
        TextHintView textHintView = new TextHintView(this);
        textHintView.setTextColor(Color.parseColor("#ffffff"));
        rpvScenicDetail.setHintView(textHintView);
        mTopPicAdapter = new TopPicAdapter(rpvScenicDetail);
        rpvScenicDetail.setAdapter(mTopPicAdapter);

        inflater = LayoutInflater.from(this);
        headerView = (RelativeLayout) inflater.inflate(R.layout.headerview_comment_list, null);
        mTvCommentRate = (TextView) headerView.findViewById(R.id.tv_comment_rate);
        mTvCommentCount = (TextView) headerView.findViewById(R.id.tv_comment_count);
        lvScenicComment.addHeaderView(headerView);
        footerView = (RelativeLayout) inflater.inflate(R.layout.footerview_comment_list, null);
        mTvCommentMore = (TextView) footerView.findViewById(R.id.tv_comment_more);
        lvScenicComment.addFooterView(footerView);


        getDataFromServer();
    }

    private void getDataFromServer() {
        userid = PrefUtils.getString(mActivity, "userid", "");
        RequestParams params = new RequestParams(Constants.SCENIC_DETAIL);
        params.addBodyParameter("id", mId);
        params.addBodyParameter("UserID",userid);
        Log.i("888", "id==="+mId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                   Log.i("123", "result==="+result);
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result);
                        llLoading.setVisibility(View.GONE);
                        ivShare.setVisibility(View.VISIBLE);
                        rfvScenicDetail.setVisibility(View.VISIBLE);
                        setData();
                        ivShare.setClickable(true);
                    } else {
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
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
                rfvScenicDetail.stopRefresh();
            }
        });
    }

    private void setData() {
        ViewTreeObserver vto = rpvScenicDetail.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //移除视图树监听器
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    rpvScenicDetail.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    rpvScenicDetail.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                mTopBarHeight = rlTopBar.getHeight();
                mRollViewPagerHeight = rpvScenicDetail.getHeight();
                mTopHeight = mRollViewPagerHeight - mTopBarHeight;

                svScenic.setScrollViewListener(mActivity);

            }
        });
    }


    private void parseData(String result) {
        Log.i("222", "result==="+result);
        Gson gson = new Gson();
        ScenicDetailBean scenicDetailBean = gson.fromJson(result, ScenicDetailBean.class);
        //轮播图
      /*  PictureBean topPicBean = scenicDetailBean.getData().getPicture();
        topImagesList.clear();  //清除数据,重新添加
        for (int i = 0; i < topDataSize; i++) {
            topImagesList.add(topPicBean.getData().get(i).getImg());
        }
        if (mTopPicAdapter == null) {
            mTopPicAdapter = new TopPicAdapter(rpvScenicDetail);
        } else {
            mTopPicAdapter.setImgs(topImagesList);
        }*/

        InfoBean infoBean = scenicDetailBean.getData().getInfo();
        //Id = infoBean.getScenicid();
        mTitle = infoBean.getScenicname().trim();
        tvTitle.setText(mTitle);
        tvTopbarTitle.setText(mTitle);
        mAddress = infoBean.getScenicaddress().trim();
        tvAddress.setText(mAddress);
        tvTime.setText(infoBean.getOpentime().trim());
        //mUrl = infoBean.getUrl();
        mImg = infoBean.getDefaultpic();

        topImagesList.add(mImg);
        if (mTopPicAdapter == null) {
            mTopPicAdapter = new TopPicAdapter(rpvScenicDetail);
        } else {
            mTopPicAdapter.setImgs(topImagesList);
        }

        if (!MyText2Utils.isEmpty(infoBean.getMap().get(0))) {
            String map = infoBean.getMap().get(0);
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

        mTicketList = scenicDetailBean.getData().getInfo().getTicketlist();
        if (mTicketAdapter == null) {
            mTicketAdapter = new ScenicTicketAdapter(mActivity, mTicketList, mTitle, mAddress);
            lvTicket.setAdapter(mTicketAdapter);
        } else {
            mTicketAdapter.setData(mTicketList, mTitle, mAddress);
        }
        //wvScenicDetailBook.loadDataWithBaseURL(null, infoBean.getScenicdescription(), "text/html", "utf-8", null);
        wvScenicDetailInfo.loadDataWithBaseURL(null, infoBean.getScenicdescription(), "text/html", "utf-8", null);

        //评论
        /*CommentBean commentBean = scenicDetailBean.getData().getComment();
        mAverage = infoBean.getAverage();
        mCommentnum = commentBean.getCount();
        mTvCommentRate.setText(mAverage + "%");
        mTvCommentCount.setText("共" + mCommentnum + "条点评");

        mCommentList = commentBean.getData();
        if (mCommentList == null || mCommentList.size() == 0) {
            llComment.setVisibility(View.GONE);
        } else {
            if (mCommentList.size() <= 5) {
                lvScenicComment.removeFooterView(footerView);
            }
            mTvCommentMore.setText("查看全部" + commentBean.getCount() + "条评论");
            if (mCommentAdapter == null) {
                mCommentAdapter = new ScenicDetailCommentAdapter(mActivity, mCommentList);
                lvScenicComment.setAdapter(mCommentAdapter);
            } else {
                mCommentAdapter.setDatas(mCommentList);
            }

        }*/
        //周边
        mOtherScenicList = scenicDetailBean.getData().getOther();
        if (mOtherAdapter == null) {
            mOtherAdapter = new ScenicOtherAdapter(mActivity, mOtherScenicList);
            gvScenicOther.setAdapter(mOtherAdapter);
        } else {
            mOtherAdapter.setData(mOtherScenicList);
        }
    }

    private void initEvent() {

        rfvScenicDetail.setPullLoadEnable(false);
        rfvScenicDetail.setMoveForHorizontal(true);
        rfvScenicDetail.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {

                getDataFromServer();
            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {
                if (offsetY > 0) {
                    rlTopBar.setVisibility(View.GONE);
                } else {
                    rlTopBar.setVisibility(View.VISIBLE);
                }
            }
        });


        gvScenicOther.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(mActivity, ScenicDetailActivity.class);
                intent.putExtra("id", mOtherScenicList.get(position).getId());
                startActivity(intent);
            }
        });

        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("from", "scenic");
                intent.putExtra("sid", mId);
                intent.putExtra("average", mAverage);
                intent.putExtra("commentnum", mCommentnum);
                intent.setClass(mActivity, CommentListActivity.class);
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.iv_back, R.id.iv_share, R.id.tv_time, R.id.ll_scenic_address, R.id.ll_webview_book, R.id.ll_webview_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                shareTheScenic();
                break;
            case R.id.tv_time:
                tvTime.setMaxLines(Integer.MAX_VALUE);
                tvTime.setEllipsize(null);
                break;
            case R.id.ll_scenic_address:
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
            case R.id.ll_webview_book:
                unfoldBookWebView();
                break;
            case R.id.ll_webview_info:
                unfoldInfoWebView();
                break;
        }
    }

    private void shareTheScenic() {
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


    private void unfoldBookWebView() {
        if (tvDetailBook.getText().equals("—")) {
            tvDetailBook.setText("+");
            wvScenicDetailBook.setVisibility(View.GONE);
        } else {
            tvDetailBook.setText("—");
            wvScenicDetailBook.setVisibility(View.VISIBLE);
        }
    }

    private void unfoldInfoWebView() {
        if (tvDetailInfo.getText().equals("—")) {
            tvDetailInfo.setText("+");
            wvScenicDetailInfo.setVisibility(View.GONE);
        } else {
            tvDetailInfo.setText("—");
            wvScenicDetailInfo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        //  Log.e("You", "x=" + x + ",y=" + y + ",oldx=" + oldx + ",oldy=" + oldy);

        if (y == 0) {   //设置标题的背景颜色
            rlTopBar.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
            tvTopbarTitle.setTextColor(Color.argb(0, 255, 255, 255));
            ivBack.getBackground().setAlpha(255);
            ivShare.getBackground().setAlpha(255);
        } else if (y > 0 && y <= mTopHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / mTopHeight;
            float alpha = (255 * scale);

            tvTopbarTitle.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            rlTopBar.setBackgroundColor(Color.argb((int) alpha, 0, 154, 68));
            ivBack.getBackground().setAlpha(255 - (int) alpha);
            ivShare.getBackground().setAlpha(255 - (int) alpha);
        } else {    //滑动到banner下面设置普通颜色

            rlTopBar.setBackgroundColor(Color.argb((int) 255, 0, 154, 68));
            tvTopbarTitle.setTextColor(Color.argb(255, 255, 255, 255));
            ivBack.getBackground().setAlpha(0);
            ivShare.getBackground().setAlpha(0);
        }
    }


}
