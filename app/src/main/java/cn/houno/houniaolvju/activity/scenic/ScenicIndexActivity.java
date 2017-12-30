package cn.houno.houniaolvju.activity.scenic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XScrollView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.CitySelectActivity;
import cn.houno.houniaolvju.adapter.ScenicCateAdapter;
import cn.houno.houniaolvju.adapter.ScenicLikeAdapter;
import cn.houno.houniaolvju.adapter.ScenicLocalAdapter;
import cn.houno.houniaolvju.adapter.TopPicAdapter;
import cn.houno.houniaolvju.bean.ScenicCateBean;
import cn.houno.houniaolvju.bean.ScenicIndexBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerListView;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：景点首页
 * 创建人：qzc
 * 创建时间：2017/1/3 10:51
 * 修改人：qzc
 * 修改时间：2017/1/3 10:51
 * 修改备注：
 */
public class ScenicIndexActivity extends Activity {


    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.pb_loading)
    ProgressBar mPbLoading;
    @Bind(R.id.tv_loading)
    TextView mTvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.ll_scenic_loc)
    LinearLayout mLlScenicLoc;
    @Bind(R.id.et_scenic_search)
    EditText mEtScenicSearch;
    @Bind(R.id.rpv_scenic)
    RollPagerView mRpvScenic;
    /*@Bind(R.id.ll_city_scenic)
    LinearLayout mLlCityScenic;
    @Bind(R.id.ll_surrounding_city)
    LinearLayout mLlSurroundingCity;
    @Bind(R.id.rcv_scenic_index)
    RecyclerView mRcvScenicCate;*/
    @Bind(R.id.ll_content)
    LinearLayout mLlContent;
    @Bind(R.id.sv_scenic_index)
    XScrollView mSvScenicIndex;
    @Bind(R.id.rfv_scenic_index)
    XRefreshView mRfvScenicIndex;
    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.ll_scenic_local)
    LinearLayout mLlScenicLocal;
    /* @Bind(R.id.ll_scenic_like)
     LinearLayout mLlScenicLike;*/
   /* @Bind(R.id.tv_scenic_city)
    TextView mTvScenicCity;*/
    @Bind(R.id.lv_local_scenic)
    InnerListView mLvLocalScenic;
    @Bind(R.id.tv_more_scenic)
    TextView tvMoreScenic;
   /* @Bind(R.id.ll_content)
    LinearLayout llContent;*/
   /* @Bind(R.id.gv_like)
    InnerGridView mGvLike;*/

    private ScenicIndexActivity mActivity;

    private static final int LOC = 1;
    private static final int TOP = 2;
    private static final int CATE = 3;
    private static final int INDEX = 4;

    //=================定位相关==============
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;//定位监听器
    private double mLatitude = 0;   //纬度
    private double mLongitude = 0;  //经度
    private String cityName;
    private String cityId;

    //=================轮播图=================
    private ArrayList<String> topImagesList = new ArrayList<>();    //图片列表
    private ArrayList<String> topUrl = new ArrayList<>();    //地址列表
    private TopPicAdapter mTopPicAdapter;
    //=================特色主题=================
    private List<ScenicCateBean.DataBean> mCateList = new ArrayList<>();
    private ScenicCateAdapter mCateAdapter;
    //=================本地景点=================
    private List<ScenicIndexBean.MainBean> mLocalList = new ArrayList<>();
    private ScenicLocalAdapter mLocalAdapter;

    //=================猜你喜欢=================
    private List<ScenicIndexBean.MainBean> mLikeList = new ArrayList<>();
    private ScenicLikeAdapter mLikeAdapter;
    private String[] mCates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_scenic_index);
        ButterKnife.bind(this);
        mActivity = ScenicIndexActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

    }

    private void initData() {
        //初始化定位
        mLocationClient = new LocationClient(this);
        mLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");   // 设置坐标类型
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);

        mRfvScenicIndex.setMoveForHorizontal(true);

        mRpvScenic.setHintView(new ColorPointHintView(mActivity, Color.parseColor("#009A44"), Color.WHITE));
        mTopPicAdapter = new TopPicAdapter(mRpvScenic);
        mRpvScenic.setAdapter(mTopPicAdapter);


    /*    //特色主题
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        mRcvScenicCate.setHasFixedSize(true);
        mRcvScenicCate.setLayoutManager(gridLayoutManager);*/

        getMyLoc();
        getScenicTop();
        getCate();
    }


    private void initEvent() {
        mRfvScenicIndex.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                // getMyLoc();
                getScenicTop();
                getCate();
                getIndexScenic();
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

        mRpvScenic.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                String sUrl = topUrl.get(position);
                //url拆分出id
                String id = sUrl.split("/id/")[1].split(".p")[0];
                intent.putExtra("scenicid", "464");
                intent.setClass(mActivity, ScenicDetailActivity.class);
                startActivity(intent);
            }
        });


        mLvLocalScenic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("scenicid", mLocalList.get(position).getScenicid());
                intent.setClass(mActivity, ScenicDetailActivity.class);
                startActivity(intent);
            }
        });

       /* mGvLike.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("scenicid", mLikeList.get(position).getScenicid());
                intent.setClass(mActivity, ScenicDetailActivity.class);
                startActivity(intent);
            }
        });
*/
        mEtScenicSearch.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
                    if (!TextUtils.isEmpty(mEtScenicSearch.getText().toString().trim())) {
                        // 先隐藏键盘
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        // 搜索关键字
                        Intent intent = new Intent();
                        intent.putExtra("keyword", mEtScenicSearch.getText().toString().trim());
                        intent.putExtra("cates", mCates);
                        intent.setClass(mActivity, ScenicListActivity.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    /*
  * 获取当前定位
  * */
    private void getMyLoc() {
        mLocationClient.start();    //开始定位
    }

    private void getDataFromServer(final int type) {
        RequestParams params;
        if (type == LOC) {
            params = new RequestParams(Constants.GET_CITY_ID);
            params.addBodyParameter("lat", String.valueOf(mLatitude));
            params.addBodyParameter("lng", String.valueOf(mLongitude));
        } else if (type == TOP) {
            params = new RequestParams(Constants.SCENIC_TOP_PIC);
        } else if (type == CATE) {
            params = new RequestParams(Constants.SCENIC_INDEX_CATE);
        } else {
            params = new RequestParams(Constants.SCENIC_INDEX);
            params.addBodyParameter("cityid", cityId);
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                if (type == INDEX) {
                    mRfvScenicIndex.stopRefresh();
                    mLlLoading.setVisibility(View.GONE);
                    mLlContent.setVisibility(View.VISIBLE);
                }
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        if (type == LOC) {
                            //定位获取城市ID
                            cityId = obj.getJSONObject("data").getString("id");
                            //根据城市id获取城市景点信息
                            cityName = obj.getJSONObject("data").getString("name");
                            mTvCity.setText(cityName);
                            //mTvScenicCity.setText(cityName + "景点");
                            getIndexScenic();
                        } else if (type == TOP) {
                            //轮播图
                            int length = obj.getJSONArray("data").length();
                            topImagesList.clear();
                            topUrl.clear();
                            for (int i = 0; i < 1; i++) {
                                topImagesList.add(obj.getJSONArray("data").getJSONObject(i).getString("img"));
                                topUrl.add(obj.getJSONArray("data").getJSONObject(i).getString("url"));
                            }
                            if (mTopPicAdapter == null) {
                                mTopPicAdapter = new TopPicAdapter(mRpvScenic);
                            } else {
                                mTopPicAdapter.setImgs(topImagesList);
                            }
                        } else if (type == CATE) {
                            //景点分类
                            parseCateData(result);

                        } else if (type == INDEX) {
                            //景点页面
                            parseIndexData(result);
                        }
                    } else {
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("ScenicIndexActivity", ex.getMessage());
                if (ex.getMessage().equalsIgnoreCase("failed to connect")) {
                    mPbLoading.setVisibility(View.GONE);
                    mTvLoading.setText("连接超时");
                }
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
     * 解析处理特色主题数据
     * */
    private void parseCateData(String result) {
        Gson gson = new Gson();
        ScenicCateBean scenicCateBean = gson.fromJson(result, ScenicCateBean.class);
        mCateList.clear();
        mCateList.addAll(scenicCateBean.getData());

        mCates = new String[scenicCateBean.getData().size() + 1];
        mCates[0] = "全部";
        for (int i = 0; i < scenicCateBean.getData().size(); i++) {
            mCates[i + 1] = scenicCateBean.getData().get(i).getName();
        }

        if (mCateAdapter == null) {
            mCateAdapter = new ScenicCateAdapter(mActivity, mCateList);
            //mRcvScenicCate.setAdapter(mCateAdapter);
        } else {
            mCateAdapter.setData(mCateList);
        }

        mCateAdapter.setOnItemClickListener(new ScenicCateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.e("eee", "点击" + position);
                Intent intent = new Intent();
                intent.putExtra("cate", position + 1);    //这里+1是因为分类最全面加了全部 0：全部
                intent.putExtra("cates", mCates);
                intent.putExtra("cityid", cityId);
                intent.setClass(mActivity, ScenicListActivity.class);
                startActivity(intent);
            }
        });
    }


    /*
     * 解析景点页面数据
     * */
    private void parseIndexData(String result) {
        Gson gson = new Gson();
        ScenicIndexBean scenicBean;
        try {
            scenicBean = gson.fromJson(result, ScenicIndexBean.class);
            System.out.println(scenicBean.toString());
            //当地景点
            mLocalList.clear();
            if (scenicBean.getMain().size() != 0) {
                mLlScenicLocal.setVisibility(View.VISIBLE);
                mLocalList = scenicBean.getMain();
            } else {
                mLlScenicLocal.setVisibility(View.GONE);
            }
            if (mLocalAdapter == null) {
                mLocalAdapter = new ScenicLocalAdapter(mActivity, mLocalList);
                mLvLocalScenic.setAdapter(mLocalAdapter);
            } else {
                mLocalAdapter.setData(mLocalList);
            }
            //猜你喜欢
            mLikeList.clear();
            if (scenicBean.getMain().size() != 0) {
                //mLlScenicLike.setVisibility(View.VISIBLE);
                mLikeList = scenicBean.getMain();
            } else {
                //mLlScenicLike.setVisibility(View.GONE);
            }
            if (mLikeAdapter == null) {
                mLikeAdapter = new ScenicLikeAdapter(mActivity, mLikeList);
                //mGvLike.setAdapter(mLikeAdapter);
            } else {
                mLikeAdapter.setData(mLikeList);
            }

        } catch (Exception e) {
            System.out.println("scenicBean     ====   出错");
        }

    }

    /*
     * 根据城市Id获取城市景点信息
     * */
    private void getIndexScenic() {
        if (mLlLoading.getVisibility() == View.VISIBLE) {
            mTvLoading.setText("正在获取" + cityName + "景点信息...");
        }
        getDataFromServer(INDEX);
    }

    /*
     * 获取轮播图
     * */
    private void getScenicTop() {
        getDataFromServer(TOP);
    }

    /*
     * 获取特色主题
     * */
    private void getCate() {
        getDataFromServer(CATE);
    }

    @OnClick({R.id.iv_back, R.id.ll_scenic_loc, R.id.et_scenic_search,R.id.tv_more_scenic/*, R.id.ll_city_scenic, R.id.ll_surrounding_city*/})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_scenic_loc:
                intent.setClass(mActivity, CitySelectActivity.class);
                startActivityForResult(intent, 301);
                break;
            case R.id.et_scenic_search:
                break;
            case R.id.tv_more_scenic:

                //intent.putExtra("cate", position + 1);    //这里+1是因为分类最全面加了全部 0：全部
                intent.putExtra("cates", mCates);
                intent.putExtra("cityid", cityId);
                intent.setClass(mActivity, ScenicListActivity.class);
                startActivity(intent);
                break;
           /* case R.id.ll_city_scenic:
                intent.putExtra("cates", mCates);
                intent.putExtra("cityid", cityId);
                intent.setClass(mActivity, ScenicListActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_surrounding_city:
                intent.putExtra("cates", mCates);
                intent.putExtra("cityid", cityId);
                intent.putExtra("type", "ambitus");
                intent.setClass(mActivity, ScenicListActivity.class);
                startActivity(intent);
                break;*/
        }
    }

    /*
     * 定位监听器
     * */
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null) {
                return;
            }
            //定位回调
            mLatitude = bdLocation.getLatitude();   //经度
            mLongitude = bdLocation.getLongitude(); //维度
            System.out.println(
                    "mLatitude:" + mLatitude + ",mLongitude:" + mLongitude
            );

            if (mLatitude != 0 && mLongitude != 0) {
                getDataFromServer(LOC);
                if (mLocationClient.isStarted()) {
                    mLocationClient.stop();
                }
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 301) {
            Bundle bundle = data.getBundleExtra("bundle"); //city即为回传的值
            cityName = bundle.getString("city");
            cityId = bundle.getString("cityId");
            Log.d("888", "onActivityResult: ===="+cityId);
            mTvCity.setText(cityName);
            //mTvScenicCity.setText(cityName + "景点");
            getScenicTop();
            getIndexScenic();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }
}
