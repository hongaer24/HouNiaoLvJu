package cn.houno.houniaolvju.activity.scenic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XScrollView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.zxl.library.DropDownMenu;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.CitySelectActivity;
import cn.houno.houniaolvju.adapter.ScenicListAdapter;
import cn.houno.houniaolvju.bean.ScenicListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerListView;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：景点列表
 * 创建人：qzc
 * 创建时间：2017/1/4 9:46
 * 修改人：qzc
 * 修改时间：2017/1/4 9:46
 * 修改备注：
 */
public class ScenicListActivity extends Activity {
    /*@Bind(R.id.iv_back)
    ImageView ivBack;*/
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.ll_scenic_loc)
    LinearLayout llScenicLoc;
    /*  @Bind(R.id.et_search)
      EditText etSearch;*/
    @Bind(R.id.lv_scenic_list)
    InnerListView lvScenicList;
    @Bind(R.id.sv_scenic_index)
    XScrollView svScenicIndex;
    @Bind(R.id.rfv_scenic_index)
    XRefreshView rfvScenicIndex;
    @Bind(R.id.ll_content)
    LinearLayout llContent;

/*
    @BindView(R.id.iv_back)
    ImageView ivBack;*/

    private ScenicListActivity mActivity;

    //private String mCityId = "";
    private String mType = "";
    private String mKeyword = "";
    private ImageView ivBack;
    private EditText etSearch;
    private static final int LOC = 1;
    private static final int INDEX = 2;

    private DropDownMenu ddmScenicList;
    private String headers[] = {"分类", "星级", "排序"};

    private int[] types = new int[]{DropDownMenu.TYPE_LIST_CITY
            , DropDownMenu.TYPE_LIST_CITY, DropDownMenu.TYPE_LIST_CITY};
    private String[] cates = new String[]{};  //分类
    private String[] stars = {"全部", "5A", "4A", "3A", "其他"};  //星级
    private String[] sorts = {"默认排序", "价格由低到高", "价格由高到低"};  //排序
    //选中结果
    private int cate = 0;
    private int star = 0;
    private int sort = 0;

    //列表页数
    private int page = 1;
    //private XRefreshView mRefreshView;
    //private ListView mListView;
    private ScenicListAdapter mAdapter;
    private List<ScenicListBean.DataBean> mList = new ArrayList<>();
    private String userid;

    //=================定位相关==============
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;//定位监听器
    private double mLatitude = 0;   //纬度
    private double mLongitude = 0;  //经度
    private String cityName;
    private String cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_list);
        ButterKnife.bind(this);
        // ButterKnife.bind(this);
        mActivity = ScenicListActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        userid = PrefUtils.getString(mActivity, "userid", "");
        Log.i("666", "id===== " + userid);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        etSearch = (EditText) findViewById(R.id.et_search);
        // ddmScenicList = (DropDownMenu) findViewById(R.id.ddm_scenic_list);
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

        Intent intent = getIntent();
        mKeyword = intent.getStringExtra("keyword");
        cate = intent.getIntExtra("cate", 0);
        cates = intent.getStringArrayExtra("cates");
        cityName = intent.getStringExtra("cityname");
         tvCity.setText(cityName);

        if (TextUtils.isEmpty(mKeyword)) {
            cityId = intent.getStringExtra("cityid");
            mType = intent.getStringExtra("type");
        } else {
            etSearch.setText(mKeyword);
        }
        //getMyLoc();
        getDataFromServer(INDEX);
        // initContentView();
        rfvScenicIndex.startRefresh();
    }

    private void initEvent() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rfvScenicIndex.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                getDataFromServer(INDEX);
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

           /*
        * 点击搜索事件监听
        * */
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String input = etSearch.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(mActivity, "请输入关键字", Toast.LENGTH_SHORT).show();
                } else {
                    etSearch.clearFocus();
                    getDataFromServer(INDEX);
                }
                return true;
            }
        });

        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //编辑框获得焦点

                } else {
                    //编辑框取消焦点

                    //收起键盘
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                }
            }
        });

        /*
        * 取消编辑框焦点
        * */
        lvScenicList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etSearch.isFocused()) {
                    etSearch.clearFocus();
                }
                return false;
            }
        });

        lvScenicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(mActivity, ScenicDetailActivity.class);
                intent.putExtra("scenicid", mList.get(position).getScenicid());
                startActivity(intent);
            }
        });
    }

    /*
* 获取当前定位
* */
    private void getMyLoc() {
        mLocationClient.start();    //开始定位
    }

  /*  private void initContentView() {
       // View contentView = getLayoutInflater().inflate(R.layout.layout_sceniclist_contentview, null);
        mRefreshView = (XRefreshView) contentView.findViewById(R.id.rfv_scenic_list);
        mRefreshView.setPullLoadEnable(true);
        mListView = (ListView) contentView.findViewById(R.id.lv_content);
        //ddmScenicList.setDropDownMenu(Arrays.asList(headers), initViewData(), contentView);

        mAdapter = new ScenicListAdapter(this);
        mListView.setAdapter(mAdapter);
        getDataFromServer();
        //该监听回调只监听默认类型，如果是自定义view请自行设置，参照demo
      *//*  ddmScenicList.addMenuSelectListener(new DropDownMenu.OnDefultMenuSelectListener() {
            @Override
            public void onSelectDefaultMenu(int index, int pos, String clickstr) {
                //index:点击的tab索引，pos：单项菜单中点击的位置索引，clickstr：点击位置的字符串
                if (etSearch.isFocused()) {
                    etSearch.clearFocus();
                }
                switch (index) {
                    case 0: //分类
                        cate = pos;
                        break;
                    case 1: //等级
                        if (pos == 0) {
                            star = 0;
                        } else if (pos == 1) {
                            star = 5;
                        } else if (pos == 2) {
                            star = 4;
                        } else if (pos == 3) {
                            star = 3;
                        } else if (pos == 4) {
                            star = 1;
                        }
                        break;
                    case 2: //排序
                        sort = pos;
                        break;
                }

                getDataFromServer();
            }
        });*//*
    }*/

    private void getDataFromServer(final int type) {
        RequestParams params = null;
        rfvScenicIndex.setPullLoadEnable(true);

        if (type == LOC) {
            params = new RequestParams(Constants.GET_CITY_ID);
            params.addBodyParameter("lat", String.valueOf(mLatitude));
            params.addBodyParameter("lng", String.valueOf(mLongitude));
        } else if (type == INDEX) {
            params = new RequestParams(Constants.SCENIC_LIST);
            params.addBodyParameter("city", cityId);
            params.addBodyParameter("keyword", etSearch.getText().toString().trim());
            // params.addBodyParameter("cate", cate + "");
            //params.addBodyParameter("xji", star + "");
            params.addBodyParameter("UserID", userid);
            params.addBodyParameter("sort", sort + "");
            params.addBodyParameter("p", page + "");
            //params.addBodyParameter("type", mType);
        }

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (type == INDEX) {
                    rfvScenicIndex.stopRefresh();
                    llContent.setVisibility(View.VISIBLE);
                    llLoading.setVisibility(View.GONE);
                }
                page = 1;
                try {
                    Log.i("666", "id===== " + result);
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        if (type == LOC) {
                            //定位获取城市ID
                            cityId = obj.getJSONObject("data").getString("id");
                            //根据城市id获取城市景点信息
                            cityName = obj.getJSONObject("data").getString("name");
                            tvCity.setText(cityName);
                            //mTvScenicCity.setText(cityName + "景点");
                            getIndexScenic();
                        } else if (type == INDEX) {
                            parseData(result, false);
                        }


                    } else {
                        rfvScenicIndex.setPullLoadEnable(false);
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
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
                rfvScenicIndex.stopRefresh();
            }
        });
    }

    private void getMoreDataFromServer() {
        page++;
        RequestParams params = new RequestParams(Constants.SCENIC_LIST);
        params.addBodyParameter("city", cityId);
        params.addBodyParameter("keyword", etSearch.getText().toString().trim());
        params.addBodyParameter("cate", cate + "");
        params.addBodyParameter("xji", star + "");
        params.addBodyParameter("sort", sort + "");
        params.addBodyParameter("p", page + "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, true);
                    } else {
                        rfvScenicIndex.setPullLoadEnable(false);
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                page--;
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                rfvScenicIndex.stopLoadMore();
            }
        });
    }

    @OnClick({R.id.iv_back,R.id.ll_scenic_loc })
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_scenic_loc:
                intent.setClass(mActivity, CitySelectActivity.class);
                startActivityForResult(intent, 302);
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
 * 解析处理数据
 * */
    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();
        ScenicListBean scenicListBean = gson.fromJson(result, ScenicListBean.class);
        if (!isMore) {  //下拉刷新数据
            mList = scenicListBean.getData();
        } else {
            mList.addAll(scenicListBean.getData());
        }
        if (mAdapter == null) {
            mAdapter = new ScenicListAdapter(this);
            lvScenicList.setAdapter(mAdapter);
        } else {
            mAdapter.setDatas(mList);
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

    /*
  * 根据城市Id获取城市景点信息
  * */
    private void getIndexScenic() {
        if (llLoading.getVisibility() == View.VISIBLE) {
            tvLoading.setText("正在获取" + cityName + "景点信息...");
        }
        getDataFromServer(INDEX);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 302) {
            Bundle bundle = data.getBundleExtra("bundle"); //city即为回传的值
            cityName = bundle.getString("city");
            cityId = bundle.getString("cityId");
            Log.d("889", "onActivityResult: ====" + cityId);
            tvCity.setText(cityName);
            //mTvScenicCity.setText(cityName + "景点");
            //getScenicTop();
            getIndexScenic();
        }
    }

    private List<HashMap<String, Object>> initViewData() {
        List<HashMap<String, Object>> viewDatas = new ArrayList<>();
        HashMap<String, Object> map;
        for (int i = 0; i < headers.length; i++) {
            map = new HashMap<>();
            map.put(DropDownMenu.KEY, types[i]);
            switch (i) {
                case 0:
                    map.put(DropDownMenu.VALUE, cates);
                    if (cate != 0) {
                        map.put(DropDownMenu.SELECT_POSITION, cate);
                    }
                    break;
                case 1:
                    map.put(DropDownMenu.VALUE, stars);
                    // map.put(DropDownMenu.SELECT_POSITION, 0);
                    break;
                case 2:
                    map.put(DropDownMenu.VALUE, sorts);
                    //   map.put(DropDownMenu.SELECT_POSITION, 0);
                    break;
                default:
                    //
                    break;
            }
            viewDatas.add(map);
        }
        return viewDatas;
    }


    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (ddmScenicList.isShowing()) {
            ddmScenicList.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}
