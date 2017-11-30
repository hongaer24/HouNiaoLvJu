package cn.houno.houniaolvju.activity.flight;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
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
import cn.houno.houniaolvju.adapter.FlightListAdapter;
import cn.houno.houniaolvju.bean.FlightCity;
import cn.houno.houniaolvju.bean.FlightListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 机票列表
 * Created by qzc on 2017/2/14.
 */

public class FlightListActivity extends Activity {

    FlightListActivity mActivity;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_dep_name)
    TextView tvDepName;
    @Bind(R.id.tv_arr_name)
    TextView tvArrName;
    @Bind(R.id.ll_left)
    LinearLayout llLeft;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.rl_middle)
    RelativeLayout rlMiddle;
    @Bind(R.id.ll_right)
    LinearLayout llRight;
    @Bind(R.id.rfv_flight_list)
    XRefreshView rfvFlightList;
    @Bind(R.id.lv_flight_list)
    ListView lvFlightList;
    @Bind(R.id.ll_fail)
    LinearLayout llFail;
    @Bind(R.id.tv_back_home)
    TextView tvBackHome;
    @Bind(R.id.iv_plane)
    ImageView ivPlane;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.tv_sort_price)
    TextView tvSortPrice;
    @Bind(R.id.iv_sort_price)
    ImageView ivSortPrice;
    @Bind(R.id.ll_sort_price)
    LinearLayout llSortPrice;
    @Bind(R.id.tv_sort_time)
    TextView tvSortTime;
    @Bind(R.id.iv_sort_time)
    ImageView ivSortTime;
    @Bind(R.id.ll_sort_time)
    LinearLayout llSortTime;
    private FlightCity mDepCity;   //出发城市
    private FlightCity mArrCity;   //目的城市

    private String mToday = "";
    private String mTodaySub = "";
    private String mDepDate = "";
    private String mDepDateSub = "";
    private String mDepWeek = "";

    private List<FlightListBean.DataBean.FlightsBean> mFlightsList = new ArrayList<>();
    private FlightListAdapter mAdapter;
    private FlightListBean mFlightListBean;

    private boolean isClickble = true; //日期是否可以点击切换


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//绑定竖屏
        StatusBarUtils.setWindowStatusBarColor(FlightListActivity.this, R.color.app_theme_green);
        setContentView(R.layout.activity_flight_list);
        ButterKnife.bind(this);
        mActivity = FlightListActivity.this;
        initData();
        initEvent();
        select(1);
        rfvFlightList.startRefresh();
    }

    private void initEvent() {
        rfvFlightList.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
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

            }
        });

        lvFlightList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(mActivity, FlightSeatActivity.class);
                intent.putExtra("data", mFlightsList.get(position));
                intent.putExtra("dep_date", mDepDate);
                intent.putExtra("dep_city", mDepCity);
                intent.putExtra("arr_city", mArrCity);
                startActivity(intent);
            }
        });
    }


    private void initData() {
        Intent intent = getIntent();
        mToday = DateUtil.getNowTime(DateUtil.DATE_SMALL_STR);

        mDepCity = (FlightCity) intent.getSerializableExtra("dep_city");
        mArrCity = (FlightCity) intent.getSerializableExtra("arr_city");
        tvDepName.setText(mDepCity.getAreaname());
        tvArrName.setText(mArrCity.getAreaname());
        mDepDate = intent.getStringExtra("dep_date");
        mDepWeek = intent.getStringExtra("dep_week");
        if (TextUtils.isEmpty(mDepWeek)) {
            mDepWeek = DateUtil.getEWeek(mDepDate);
        }
        mDepDateSub = mDepDate.substring(5);
        tvDate.setText(mDepDateSub + " " + mDepWeek);
        mAdapter = new FlightListAdapter(mActivity, mFlightsList);
        lvFlightList.setAdapter(mAdapter);
    }


    private void getDataFromServer() {


        setDateClickable();  //请求过程中禁止点击日期切换
        Map<String, String> params = new HashMap<>();
        params.put("dep_city", mDepCity.getSajm());
        params.put("arr_city", mArrCity.getSajm());
        params.put("dep_date", mDepDate);
        params.put("sort", timeState+"");
        params.put("price", priceState+"");

        OkHttpClientManager.postAsync(Constants.FLIGHT_LIST, params, null, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                setDateClickable();//恢复点击日期切换
                switch (msg.what) {
                    case R.id.doSucceed:
                        rfvFlightList.stopRefresh();
                        String successResult = msg.obj.toString();
                        Log.d("RequestSuccessResult:", successResult);
                        try {
                            JSONObject object = new JSONObject(successResult);
                            int status = object.getInt("status");
                            if (status == 0) {
                                //如果显示的时间包含请求的出发时间
                                Log.d("RequestSuccess:", "RequestSuccess");
                                if (tvDate.getText().toString().contains(
                                        object.getJSONObject("data").getString("depDate"))) {
                                    parserData(successResult);
                                }
                            } else {
                                rfvFlightList.setVisibility(View.GONE);
                                llFail.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.doFail:
                        rfvFlightList.stopRefresh();
                        rfvFlightList.setPullLoadEnable(false);
                        String FailResult = msg.obj.toString();
                        System.out.println("getDataFail:" + FailResult);
                        break;
                }
            }
        }, R.id.doSucceed, R.id.doFail);


    }


    private void setDateClickable() {
        if (isClickble) {
            llLeft.setClickable(false);
            llRight.setClickable(false);
            rlMiddle.setClickable(false);

        } else {
            llLeft.setClickable(true);
            llRight.setClickable(true);
            rlMiddle.setClickable(true);
        }
        isClickble = !isClickble;
    }


    private void parserData(String result) {
        Gson gson = new Gson();
        mFlightListBean = gson.fromJson(result, FlightListBean.class);
        mFlightsList = mFlightListBean.getData().getFlights();
        if (mAdapter == null) {
            mAdapter = new FlightListAdapter(mActivity, mFlightsList);
            lvFlightList.setAdapter(mAdapter);
        } else {
            mAdapter.setData(mFlightsList);
        }
    }


    @OnClick({R.id.iv_back, R.id.ll_left, R.id.rl_middle, R.id.ll_right,R.id.ll_sort_price
            , R.id.ll_sort_time, R.id.tv_back_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_left:
                getBeforeDate();
                break;
            case R.id.rl_middle:
                Intent date = new Intent();
                date.putExtra("from", "flight");
                date.setClass(mActivity, SingleDatePickerActivity.class);
                startActivityForResult(date, 301);
                break;
            case R.id.ll_right:
                getNextDate();
                break;
            case R.id.ll_sort_price:
                select(1);
                break;
            case R.id.ll_sort_time:
                select(2);
                break;
            case R.id.tv_back_home:
                finish();
                break;
        }
    }

    private void getNextDate() {
        showListView();
        mDepDate = DateUtil.getTomorrowTime(mDepDate);
        mDepWeek = DateUtil.getEWeek(mDepDate);
        mDepDateSub = mDepDate.substring(5);
        tvDate.setText(mDepDateSub + " " + mDepWeek);
        mFlightsList.clear();
        mAdapter.notifyDataSetChanged();
        rfvFlightList.startRefresh();
    }

    private void getBeforeDate() {
        showListView();
        if (!mDepDate.equals(mToday)) {
            mDepDate = DateUtil.getTheDate(mDepDate, -1);
            mDepWeek = DateUtil.getEWeek(mDepDate);
            mDepDateSub = mDepDate.substring(5);
            tvDate.setText(mDepDateSub + " " + mDepWeek);
            mFlightsList.clear();
            mAdapter.notifyDataSetChanged();
            rfvFlightList.startRefresh();
        }
    }

    private void showListView() {
        if (rfvFlightList.getVisibility() == View.GONE) {
            llFail.setVisibility(View.GONE);
            rfvFlightList.setVisibility(View.VISIBLE);
        }
    }

    private int priceState = 0;    //0默认不选，1是从低到高，2是从高到低
    private int timeState = 0;     //同上
    private int lastSelect = 0;    //0默认不选，1是价格，2是时间


    private void select(int i) {
        if (lastSelect == 0) {
            switch (i) {
                case 1:
                    llSortPrice.setSelected(true);
                    ivSortPrice.setBackgroundResource(R.drawable.ic_sort_up);
                    priceState = 1;
                    break;
                case 2:
                    llSortTime.setSelected(true);
                    ivSortTime.setBackgroundResource(R.drawable.ic_sort_up);
                    timeState = 1;
                    break;
            }
        } else if (lastSelect == 1) {
            switch (i) {
                case 1:
                    llSortPrice.setSelected(true);
                    if (priceState==0){
                        ivSortPrice.setBackgroundResource(R.drawable.ic_sort_up);
                        priceState = 1;
                    }else if (priceState==1){
                        ivSortPrice.setBackgroundResource(R.drawable.ic_sort_down);
                        priceState = 2;
                    }else {
                        llSortPrice.setSelected(false);
                        ivSortPrice.setBackgroundResource(R.drawable.ic_sort_normal);
                        priceState = 0;
                    }
                    break;
                case 2:
                    if (llSortPrice.isSelected()){
                        llSortPrice.setSelected(false);
                        ivSortPrice.setBackgroundResource(R.drawable.ic_sort_normal);
                        priceState = 0;
                    }
                    llSortTime.setSelected(true);
                    ivSortTime.setBackgroundResource(R.drawable.ic_sort_up);
                    timeState = 1;
                    break;
            }
        }else if (lastSelect == 2) {
            switch (i) {
                case 1:
                    if (llSortTime.isSelected()){
                        llSortTime.setSelected(false);
                        ivSortTime.setBackgroundResource(R.drawable.ic_sort_normal);
                        timeState = 0;
                    }
                    llSortPrice.setSelected(true);
                    ivSortPrice.setBackgroundResource(R.drawable.ic_sort_up);
                    priceState = 1;
                    break;
                case 2:
                    llSortTime.setSelected(true);
                    if (timeState==0){
                        ivSortTime.setBackgroundResource(R.drawable.ic_sort_up);
                        timeState = 1;
                    }else if (timeState==1){
                        ivSortTime.setBackgroundResource(R.drawable.ic_sort_down);
                        timeState = 2;
                    }else {
                        llSortTime.setSelected(false);
                        ivSortTime.setBackgroundResource(R.drawable.ic_sort_normal);
                        timeState = 0;
                    }
                    break;
            }
        }

        lastSelect =i;
        rfvFlightList.startRefresh();
      //  getDataFromServer();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 300 && requestCode == 301) {
            showListView();
            Bundle extras = data.getExtras();
            mDepDate = extras.getString("dateIn");
            mDepWeek = DateUtil.getEWeek(mDepDate);
            mDepDateSub = mDepDate.substring(5);
            tvDate.setText(mDepDateSub + " " + mDepWeek);
            mFlightsList.clear();
            mAdapter.notifyDataSetChanged();
            rfvFlightList.startRefresh();

        }
    }


}
