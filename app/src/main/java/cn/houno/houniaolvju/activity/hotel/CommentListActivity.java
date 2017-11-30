package cn.houno.houniaolvju.activity.hotel;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;

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
import cn.houno.houniaolvju.adapter.HotelCommentListAdapter;
import cn.houno.houniaolvju.adapter.ScenicCommentListAdapter;
import cn.houno.houniaolvju.bean.HotelCommentListBean;
import cn.houno.houniaolvju.bean.ScenicCommentListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StatusBarUtils;

;
;

/**
 * 项目名称：Houniaolvju
 * 类描述：
 * 创建人：qzc
 * 创建时间：2016/12/26 14:50
 * 修改人：qzc
 * 修改时间：2016/12/26 14:50
 * 修改备注：
 */
public class CommentListActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.lv_comment)
    ListView mLvComment;
    @Bind(R.id.rfv_comment)
    XRefreshView mRfvComment;
    @Bind(R.id.pb_loading)
    ProgressBar mPbLoading;
    @Bind(R.id.tv_loading)
    TextView mTvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.tv_topbar_title)
    TextView mTvTopTitle;

    private LayoutInflater inflater;
    private RelativeLayout headerView;
    private TextView mTvCommentRate;
    private TextView mTvCommentCount;

    private String mFrom;

    private CommentListActivity mActivity;
    private String mHid;

    private List<HotelCommentListBean.DataBean> hotelCommentList;
    private HotelCommentListAdapter mHotelAdapter;

    private int page = 2;
    private int mAverage;
    private String mCommentnum;

    //景点
    private String mSid;
    private List<ScenicCommentListBean.DataBean> scenicCommentList;
    private ScenicCommentListAdapter mScenicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_hotel_comment);
        ButterKnife.bind(this);
        mActivity = CommentListActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        mRfvComment.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                getMoreDataFromServer();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    private void initView() {
        inflater = LayoutInflater.from(this);
        headerView = (RelativeLayout) inflater.inflate(R.layout.headerview_comment_list, null);
        mTvCommentRate = (TextView) headerView.findViewById(R.id.tv_comment_rate);
        mTvCommentCount = (TextView) headerView.findViewById(R.id.tv_comment_count);

        mLvComment.addHeaderView(headerView);
    }

    private void initData() {
        Intent intent = getIntent();
        mFrom = intent.getStringExtra("from");
        if (mFrom.equals("hotel")) {
            mHid = intent.getStringExtra("hid");
            mTvTopTitle.setText("酒店评论");
            hotelCommentList = new ArrayList<>();
        } else if (mFrom.equals("scenic")) {
            mSid = intent.getStringExtra("sid");
            scenicCommentList = new ArrayList<>();
            mTvTopTitle.setText("景点评论");
        }
        mAverage = intent.getIntExtra("average", -1);
        mCommentnum = intent.getStringExtra("commentnum");
        mTvCommentCount.setText("共" + mCommentnum + "人点评");
        mTvCommentRate.setText(mAverage + "%");
        getDataFromServer();
    }

    private void getDataFromServer() {
        RequestParams params = null;
        if (mFrom.equals("hotel")) {
            params = new RequestParams(Constants.HOTEL_COMMENT_LIST);
            params.addBodyParameter("hid", mHid);
        } else if (mFrom.equals("scenic")) {
            params = new RequestParams(Constants.SCENIC_COMMENT_LIST);
            params.addBodyParameter("id", mSid);
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, false);
                    } else {
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        //没有评论数据
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
                mRfvComment.stopRefresh();
            }
        });
    }

    private void getMoreDataFromServer() {
        RequestParams params = null;
        if (mFrom.equals("hotel")) {
            params = new RequestParams(Constants.HOTEL_COMMENT_LIST);
            params.addBodyParameter("hid", mHid);
            params.addBodyParameter("page", page + "");
        } else if (mFrom.equals("scenic")) {
            params = new RequestParams(Constants.SCENIC_COMMENT_LIST);
            params.addBodyParameter("id", mSid);
            params.addBodyParameter("p", page + "");
        }

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, true);
                    } else {
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        //没有评论数据
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
                mRfvComment.stopLoadMore();
            }
        });
    }

    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();
        if (mLlLoading.getVisibility() == View.VISIBLE) {
            mLlLoading.setVisibility(View.GONE);
        }
        if (mLvComment.getVisibility() == View.GONE) {
            mLvComment.setVisibility(View.VISIBLE);
        }

        if (mFrom.equals("hotel")) {
            HotelCommentListBean hotelCommentListBean = gson.fromJson(result, HotelCommentListBean.class);

            if (isMore) {
                hotelCommentList.addAll(hotelCommentListBean.getData());
                page++;
            } else {
                hotelCommentList = hotelCommentListBean.getData();
                if (!mRfvComment.getPullLoadEnable()) {
                    mRfvComment.setPullLoadEnable(true);
                }
                page = 2;
            }

            if (mHotelAdapter == null) {
                mHotelAdapter = new HotelCommentListAdapter(this, hotelCommentList);
                mLvComment.setAdapter(mHotelAdapter);
            } else {
                mHotelAdapter.setDatas(hotelCommentList);
            }
        } else if (mFrom.equals("scenic")) {
            ScenicCommentListBean scenicCommentListBean = gson.fromJson(result, ScenicCommentListBean.class);

            if (isMore) {
                scenicCommentList.addAll(scenicCommentListBean.getData());
                page++;
            } else {
                scenicCommentList = scenicCommentListBean.getData();
                if (!mRfvComment.getPullLoadEnable()) {
                    mRfvComment.setPullLoadEnable(true);
                }
                page = 2;
            }
            if (mScenicAdapter == null) {
                mScenicAdapter = new ScenicCommentListAdapter(this, scenicCommentList);
                mLvComment.setAdapter(mScenicAdapter);
            } else {
                mScenicAdapter.setDatas(scenicCommentList);
            }
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
