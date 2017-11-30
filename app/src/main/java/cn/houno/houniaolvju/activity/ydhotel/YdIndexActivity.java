package cn.houno.houniaolvju.activity.ydhotel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XScrollView;
import com.google.gson.Gson;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.HotRegionAdapter;
import cn.houno.houniaolvju.adapter.ScenicCateAdapter;
import cn.houno.houniaolvju.adapter.TopPicAdapter;
import cn.houno.houniaolvju.adapter.YdIndexAdapter;
import cn.houno.houniaolvju.bean.YdIndexBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerListView;

/**
 * 异地养老首页
 * Created by Administrator on 2017/1/14.
 */

public class YdIndexActivity extends Activity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.et_yd_index_search)
    EditText etYdIndexSearch;
    @Bind(R.id.rpv_yd_index)
    RollPagerView rpvYdIndex;
    @Bind(R.id.rcv_hot_region)
    RecyclerView rcvHotRegion;
    @Bind(R.id.lv_yd_index)
    InnerListView lvYdIndex;
    @Bind(R.id.xsv_yd_index)
    XScrollView xsvYdIndex;
    @Bind(R.id.rfv_yd_index)
    XRefreshView rfvYdIndex;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.ll_content)
    LinearLayout llContent;

    private YdIndexActivity mActivity;
    private static final int TOP = 1;
    private static final int INDEX = 2;

    //==================轮播图===============
    private ArrayList<String> topImagesList = new ArrayList<>();    //图片列表
    private ArrayList<String> topUrl = new ArrayList<>();   //轮播图对应房间地址
    private TopPicAdapter mTopPicAdapter;
    //==================首页数据==============
    private List<YdIndexBean.DataBean> ydIndexList = new ArrayList<>();
    private YdIndexAdapter ydIndexAdapter;
    private int page = 2;
    //====================热门地区==============
    private HotRegionAdapter mHotRegionAdapter;
    private List<Integer> mHotDatas;
    private List<String> mHotCityId;
    private List<String> mHotCityName;
    private String mKeyWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_yd_index);
        ButterKnife.bind(this);
        mActivity = YdIndexActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

    }

    private void initData() {
        rfvYdIndex.setMoveForHorizontal(true);
        rpvYdIndex.setHintView(new ColorPointHintView(mActivity, Color.parseColor("#009A44"), Color.WHITE));
        mTopPicAdapter = new TopPicAdapter(rpvYdIndex);
        rpvYdIndex.setAdapter(mTopPicAdapter);
        getDataFromServer(TOP);
        //热门地区
        mHotDatas = new ArrayList<>
                (Arrays.asList(R.drawable.pic_bg_hn, R.drawable.pic_bg_hin, R.drawable.pic_bg_gx, R.drawable.pic_bg_yn));
        mHotCityId = new ArrayList<>(Arrays.asList("418", "6", "57", "56"));
        mHotCityName = new ArrayList<>(Arrays.asList("河南", "海南", "广西", "云南"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvHotRegion.setLayoutManager(linearLayoutManager);
        mHotRegionAdapter = new HotRegionAdapter(mActivity, mHotDatas);
        rcvHotRegion.setAdapter(mHotRegionAdapter);
        getDataFromServer(INDEX);
    }

    private void initEvent() {
        rfvYdIndex.setPullLoadEnable(false);
        rfvYdIndex.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                getDataFromServer(TOP);
                getDataFromServer(INDEX);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });

        rpvYdIndex.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                String sUrl = topUrl.get(position);
                //从url拆分出id
                String id = sUrl.split("/id/")[1].split("/rid/")[0];
                intent.putExtra("id", id);
                intent.setClass(mActivity, YdDetailActivity.class);
                startActivity(intent);
            }
        });

        etYdIndexSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String input = etYdIndexSearch.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    // Toast.makeText(mActivity, "请输入关键字", Toast.LENGTH_SHORT).show();
                } else {
                    mKeyWord = input;
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etYdIndexSearch.getWindowToken(), 0);

                    Intent intent = new Intent();
                    intent.setClass(mActivity, YdListActivity.class);
                    intent.putExtra("keyword", mKeyWord);
                    startActivity(intent);
                }
                return true;
            }
        });

        mHotRegionAdapter.setOnItemClickListener(new ScenicCateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(mActivity, YdListActivity.class);
                intent.putExtra("cityid", mHotCityId.get(position));
                intent.putExtra("cityname", mHotCityName.get(position));
                startActivity(intent);
            }
        });

        lvYdIndex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String yId = ydIndexList.get(position).getId();
                if (yId != null && !TextUtils.isEmpty(yId)) {
                    Intent intent = new Intent();
                    intent.putExtra("id", yId);
                    intent.setClass(mActivity, YdDetailActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /*
    * 下拉刷新
    * */
    private void getDataFromServer(final int type) {
        RequestParams params = null;
        if (type == TOP) {
            params = new RequestParams(Constants.GET_YD_INDEX_TOP);
        } else if (type == INDEX) {
            params = new RequestParams(Constants.GET_YD_INDEX);
        }

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i(type + "-Success", result);
                llContent.setVisibility(View.VISIBLE);
                llLoading.setVisibility(View.GONE);
                parseData(type, result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                pbLoading.setVisibility(View.GONE);
                tvLoading.setText("加载失败");
                Log.e("Error", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                rfvYdIndex.stopRefresh();
            }
        });
    }

    /*
    * 解析数据
    * */
    private void parseData(int type, String result) {
        try {
            JSONObject obj = new JSONObject(result);
            if (obj.getInt("status") == 0) {
                Gson gson = new Gson();
                if (type == TOP) {
                    int dataSize = obj.getJSONArray("data").length();
                    topUrl.clear();
                    topImagesList.clear();  //清除数据,重新添加
                    for (int i = 0; i < dataSize; i++) {
                        topImagesList.add(obj.getJSONArray("data").getJSONObject(i).getString("img"));
                        topUrl.add(obj.getJSONArray("data").getJSONObject(i).getString("url"));
                    }
                    if (mTopPicAdapter == null) {
                        mTopPicAdapter = new TopPicAdapter(rpvYdIndex);
                        rpvYdIndex.setAdapter(mTopPicAdapter);
                    } else {
                        mTopPicAdapter.setImgs(topImagesList);
                    }
                } else if (type == INDEX) {
                    YdIndexBean ydIndexBean = gson.fromJson(result, YdIndexBean.class);
                    ydIndexList = ydIndexBean.getData();
                    if (ydIndexAdapter == null) {
                        ydIndexAdapter = new YdIndexAdapter(mActivity, ydIndexList);
                        lvYdIndex.setAdapter(ydIndexAdapter);
                    } else {
                        ydIndexAdapter.setDatas(ydIndexList);
                    }
                }
            } else {
                Log.i("YdIndexActivity", obj.getString("msg"));

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
