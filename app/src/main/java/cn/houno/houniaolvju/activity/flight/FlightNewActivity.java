package cn.houno.houniaolvju.activity.flight;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.scenic.SingleDatePickerActivity;
import cn.houno.houniaolvju.adapter.FlightSpecialSaleAdapter;
import cn.houno.houniaolvju.bean.FlightCity;
import cn.houno.houniaolvju.bean.FlightSpecialSaleBean;
import cn.houno.houniaolvju.bean.FlightSpecialSaleBean.DataBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerGridView;

/**
 * 机票预订
 * Created by qzc on 2017/2/9.
 */

public class FlightNewActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_dep_city)
    TextView tvDepCity;
    @Bind(R.id.tv_arr_city)
    TextView tvArrCity;
    @Bind(R.id.btn_change)
    ImageView btnChange;
    @Bind(R.id.tv_select_date)
    TextView tvSelectDate;
    @Bind(R.id.tv_select_week)
    TextView tvSelectWeek;
    @Bind(R.id.ll_select_date)
    LinearLayout llSelectDate;
    @Bind(R.id.btn_query)
    TextView btnQuery;
    @Bind(R.id.gv_special_flight)
    InnerGridView gvSpecialFlight;


    private FlightNewActivity mActivity;

    private FlightCity mDepCity;
    private FlightCity mArrCity;
    private FlightCity changeCity;

    private String mToday;
    private String mDepDate;
    private String mDepDateSub;
    private String mDepWeek;

    private FlightSpecialSaleAdapter saleAdapte;
    private List<DataBean> mSaleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtils.setWindowStatusBarColor(FlightNewActivity.this, R.color.app_theme_green);
        setContentView(R.layout.activity_flight_new);
        mActivity = FlightNewActivity.this;
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    private void initData() {
        initCity();
        mToday = DateUtil.getNowTime(DateUtil.DATE_SMALL_STR);
        mDepDate = DateUtil.getTomorrowTime(mToday);
        mDepWeek = DateUtil.getEWeek(mDepDate);
        mDepDateSub = DateUtil.getOtherDateStr(mDepDate);
        tvSelectDate.setText(mDepDateSub);
        tvSelectWeek.setText(mDepWeek);
        getSpecialFlight();
    }

    private void initCity() {
        mDepCity = new FlightCity("2", "北京", "BJS", "中国", "CN", "ZBAA", "北京首都机场"
                , "BEIJING", "beijing", "bj", "B", "1", "1", "", "2");
        mArrCity = new FlightCity("1", "上海", "SHA", "中国", "CN", "ZSSS", "上海虹桥机场"
                , "SHANGHAI", "shanghai", "sh", "S", "1", "1", "", "1");
        tvDepCity.setText(mDepCity.getAreaname());
        tvArrCity.setText(mArrCity.getAreaname());
    }


    private void initEvent() {
        gvSpecialFlight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataBean dataBean = mSaleList.get(position);
                FlightCity depCity = new FlightCity();
                depCity.setAreaname(dataBean.getDepName());
                depCity.setSajm(dataBean.getDepAirport());
                FlightCity arrCity = new FlightCity();
                arrCity.setAreaname(dataBean.getArrName());
                arrCity.setSajm(dataBean.getArrAirport());
                String depDate = dataBean.getDepDate();
                String depWeek = DateUtil.getEWeek(depDate);

                Intent intent = new Intent();
                intent.setClass(mActivity, FlightListActivity.class);
                intent.putExtra("dep_city", depCity);
                intent.putExtra("arr_city", arrCity);
                intent.putExtra("dep_date", depDate);
                intent.putExtra("dep_week", depWeek);
                startActivity(intent);
            }
        });
    }

    private void getSpecialFlight() {
        Map<String, String> params = new HashMap<>();
        params.put("dep_city", mDepCity.getSajm());
        params.put("dep_date", mDepDate);
        OkHttpClientManager.postAsync(Constants.SPECIAL_FLIGHT, params, null
                , new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case R.id.doSucceed:
                                String result = msg.obj.toString();
                                try {
                                    JSONObject obj = new JSONObject(result);
                                    int status = obj.getInt("status");
                                    if (status == 1) {
                                        Toast.makeText(mActivity, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                                    } else if (status == 0) {
                                        parseData(result);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case R.id.doFail:
                                Toast.makeText(mActivity, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }, R.id.doSucceed, R.id.doFail);
    }

    private void parseData(String result) {
        Gson gson = new Gson();
        FlightSpecialSaleBean specialSaleBean = gson.fromJson(result, FlightSpecialSaleBean.class);
        mSaleList = specialSaleBean.getData();
        if (saleAdapte == null) {
            saleAdapte = new FlightSpecialSaleAdapter(mActivity, mSaleList);
            gvSpecialFlight.setAdapter(saleAdapte);
        }else {
            saleAdapte.setData(mSaleList);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (resultCode == RESULT_OK) {
                switch (requestCode) {
                    case 211:
                        mDepCity = (FlightCity) data.getSerializableExtra(CityPickerActivity.KEY_PICKED_CITY);
                        tvDepCity.setText(mDepCity.getAreaname());
                        System.out.println(mDepCity);
                        getSpecialFlight();
                        break;
                    case 212:
                        mArrCity = (FlightCity) data.getSerializableExtra(CityPickerActivity.KEY_PICKED_CITY);
                        tvArrCity.setText(mArrCity.getAreaname());
                        System.out.println(mArrCity);
                        break;
                }
            } else if (resultCode == 300 && requestCode == 301) {
                Bundle extras = data.getExtras();
                mDepDate = extras.getString("dateIn");
                mDepWeek = DateUtil.getEWeek(mDepDate);
                mDepDateSub = DateUtil.getOtherDateStr(mDepDate);
                tvSelectDate.setText(mDepDateSub);
                tvSelectWeek.setText(mDepWeek);
            }
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_dep_city, R.id.tv_arr_city, R.id.btn_change, R.id.ll_select_date, R.id.btn_query})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_dep_city:
                intent.setClass(mActivity, CityPickerActivity.class);
                startActivityForResult(intent, 211);
                break;
            case R.id.tv_arr_city:
                intent.setClass(mActivity, CityPickerActivity.class);
                startActivityForResult(intent, 212);
                break;
            case R.id.btn_change:
                changeDepArrCity();
                break;
            case R.id.ll_select_date:
                intent.putExtra("from", "flight");
                intent.setClass(mActivity, SingleDatePickerActivity.class);
                startActivityForResult(intent, 301);
                break;
            case R.id.btn_query:
                intent.setClass(mActivity, FlightListActivity.class);
                intent.putExtra("dep_city", mDepCity);
                intent.putExtra("arr_city", mArrCity);
                intent.putExtra("dep_date", mDepDate);
                intent.putExtra("dep_week", mDepWeek);
                startActivity(intent);
                break;
        }
    }

    private void changeDepArrCity() {
        changeCity = mDepCity;
        mDepCity = mArrCity;
        mArrCity = changeCity;
        tvDepCity.setText(mDepCity.getAreaname());
        tvArrCity.setText(mArrCity.getAreaname());
    }
}
