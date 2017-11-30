package cn.houno.houniaolvju.activity.flight;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.FlightCityListAdapter;
import cn.houno.houniaolvju.adapter.ResultListAdapter;
import cn.houno.houniaolvju.adapter.SearchAdapter;
import cn.houno.houniaolvju.bean.FlightCity;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.SideLetterBar;


/**
 * 项目名称：HouNiaoLvJu
 * 类描述：城市选择
 * 创建人：qzc
 * 创建时间：2016/10/6 9:52
 * 修改人：qzc
 * 修改时间：2016/10/6 9:52
 * 修改备注：
 */
public class CityPickerActivity extends Activity implements View.OnClickListener {


    private CityPickerActivity mActivity;

    public static final String KEY_PICKED_CITY = "picked_city";

    private ListView mListView;
    private ListView mResultListView;
    private SideLetterBar mLetterBar;
    private ProgressBar pbLoading;
    private AutoCompleteTextView searchBox;
    private ImageView clearBtn;
    private ImageView backBtn;
    private ViewGroup emptyView;
    private FlightCityListAdapter mCityAdapter;
    private ResultListAdapter mResultAdapter;
    private List<FlightCity> mHotCities = new ArrayList<>();
    private List<FlightCity> mAllCities = new ArrayList<>();

    private List<String> cityNameList = new ArrayList<>();
    public SearchAdapter searchAdapter;

    Map<String, FlightCity> searchList = new HashMap<>();
    // private DBManager dbManager;

    // private AMapLocationClient mLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//绑定竖屏
        setContentView(R.layout.cp_activity_city_list);
        mActivity = CityPickerActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        mListView = (ListView) findViewById(R.id.listview_all_city);
        searchBox = (AutoCompleteTextView) findViewById(R.id.et_search);
        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
        getCityListFromServer();
        initData();
        initView();
        initEvent();
        // initLocation();
    }


//    private void initLocation() {
//        mLocationClient = new AMapLocationClient(this);
//        AMapLocationClientOption option = new AMapLocationClientOption();
//        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        option.setOnceLocation(true);
//        mLocationClient.setLocationOption(option);
//        mLocationClient.setLocationListener(new AMapLocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation aMapLocation) {
//                if (aMapLocation != null) {
//                    if (aMapLocation.getErrorCode() == 0) {
//                        String city = aMapLocation.getCity();
//                        String district = aMapLocation.getDistrict();
//                        String location = StringUtils.extractLocation(city, district);
//                        mCityAdapter.updateLocateState(LocateState.SUCCESS, location);
//                    } else {
//                        //定位失败
//                        mCityAdapter.updateLocateState(LocateState.FAILED, null);
//                    }
//                }
//            }
//        });
//        mLocationClient.startLocation();
//    }

    private void initView() {
        System.out.println("mHotCities:" + mHotCities + ",mAllCities:" + mAllCities);
        if (mCityAdapter == null) {
            mCityAdapter = new FlightCityListAdapter(mActivity, mHotCities, mAllCities);
            mListView.setAdapter(mCityAdapter);
        }

        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                System.out.println("listview_count:" + mListView.getCount());
                mListView.setSelection(position);
                System.out.println("listview_position:" + position);
            }
        });


        searchBox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city = parent.getItemAtPosition(position).toString();
                FlightCity flightCity = searchList.get(city);
                searchBox.setText(city);
                back(flightCity);
            }
        });
//        searchBox.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String keyword = s.toString();
//                if (TextUtils.isEmpty(keyword)) {
//                    clearBtn.setVisibility(View.GONE);
//                    emptyView.setVisibility(View.GONE);
//                    mResultListView.setVisibility(View.GONE);
//                } else {
//                    clearBtn.setVisibility(View.VISIBLE);
//                    mResultListView.setVisibility(View.VISIBLE);
//                    List<FlightCity> result = new ArrayList<FlightCity>();
//                    if (result == null || result.size() == 0) {
//                        emptyView.setVisibility(View.VISIBLE);
//                    } else {
//                        emptyView.setVisibility(View.GONE);
//                        mResultAdapter.changeData(result);
//                    }
//                }
//            }
//        });

        emptyView = (ViewGroup) findViewById(R.id.empty_view);
        mResultListView = (ListView) findViewById(R.id.listview_search_result);
        mResultListView.setAdapter(mResultAdapter);
        mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                back(mResultAdapter.getItem(position));
            }
        });

        clearBtn = (ImageView) findViewById(R.id.iv_search_clear);
        backBtn = (ImageView) findViewById(R.id.iv_back);

        clearBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }

    private void initData() {
        mResultAdapter = new ResultListAdapter(this, null);
    }

    private void initEvent() {
        mCityAdapter.setOnCityClickListener(new FlightCityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(FlightCity city) {
                back(city);
            }
        });
    }


    /*
   * 获取城市列表
   * */
    private void getCityListFromServer() {
        Map<String, String> params = new HashMap<>();
        params.put("", "");
        OkHttpClientManager.postAsync(Constants.FLIGHT_CITY, params, null, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                pbLoading.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                switch (msg.what) {
                    case R.id.doSucceed:
                        String result = msg.obj.toString();
                        try {
                            JSONObject obj = new JSONObject(result);
                            int status = obj.getInt("status");
                            if (status == 1) {
                            } else if (status == 0) {
                                parseData(result);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        }, R.id.doSucceed);

    }

    /*
    * 解析处理数据
    * */
    private void parseData(String result) {
        try {
            JSONObject object = new JSONObject(result);
            JSONArray hotArray = object.getJSONObject("data").getJSONArray("hot");
            for (int i = 0; i < hotArray.length(); i++) {
                mHotCities.add(setCity(hotArray.getJSONObject(i)));
            }
            JSONObject otherObject = object.getJSONObject("data").getJSONObject("other");
            mAllCities.add(new FlightCity());
            Iterator<?> it = otherObject.keys();
            while (it.hasNext()) {
                String otherKey = it.next().toString();
                int letterSize = otherObject.getJSONArray(otherKey).length();
                for (int j = 0; j < letterSize; j++) {
                    FlightCity city = setCity(otherObject.getJSONArray(otherKey).getJSONObject(j));
                    mAllCities.add(city);
                }
            }

            if (mCityAdapter == null) {
                mCityAdapter = new FlightCityListAdapter(CityPickerActivity.this, mHotCities, mAllCities);
                mListView.setAdapter(mCityAdapter);
            } else {
                mCityAdapter.setData(mHotCities, mAllCities);
            }

            String[] cityListStr = cityNameList.toArray(new String[cityNameList.size()]);
            //自动搜索框
            searchAdapter = new SearchAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line, cityListStr, SearchAdapter.ALL);//速度优先
            searchBox.setAdapter(searchAdapter);//

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("parseData:Error", "这里出错");
        }
    }


    private FlightCity setCity(JSONObject jsonObject) {
        FlightCity city = new FlightCity();
        try {
            city.setId(jsonObject.getString("id"));
            city.setAreaname(jsonObject.getString("areaname"));
            city.setSajm(jsonObject.getString("sajm"));
            city.setCountry(jsonObject.getString("country"));
            city.setCjm(jsonObject.getString("cjm"));
            city.setSijm(jsonObject.getString("sijm"));
            city.setAirport(jsonObject.getString("airport"));
            city.setEnname(jsonObject.getString("enname"));
            city.setAreaen(jsonObject.getString("areaen"));
            city.setSimpleen(jsonObject.getString("simpleen"));
            city.setJianpin(jsonObject.getString("jianpin"));
            city.setIshot(jsonObject.getString("ishot"));
            city.setStatus(jsonObject.getString("status"));
            city.setRemark(jsonObject.getString("remark"));
            city.setRow_number(jsonObject.getString("row_number"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        cityNameList.add(city.getAreaname());
        searchList.put(city.getAreaname(), city);
        return city;
    }


    private void back(FlightCity city) {
        Intent data = new Intent();
        data.putExtra(KEY_PICKED_CITY, city);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_search_clear) {
            searchBox.setText("");
            clearBtn.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
            mResultListView.setVisibility(View.GONE);
        } else if (i == R.id.iv_back) {
            finish();
        }
    }
}
