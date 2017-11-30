package cn.houno.houniaolvju.activity.flight;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.FlightSeatAdapter;
import cn.houno.houniaolvju.bean.FlightCity;
import cn.houno.houniaolvju.bean.FlightListBean;
import cn.houno.houniaolvju.bean.FlightListBean.DataBean.FlightsBean;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 机票列表详情
 * Created by qzc on 2017/2/15.
 */

public class FlightSeatActivity extends Activity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_dep_city)
    TextView tvDepCity;
    @Bind(R.id.tv_arr_city)
    TextView tvArrCity;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.iv_went)
    ImageView ivWent;
    @Bind(R.id.tv_dep_date)
    TextView tvDepDate;
    @Bind(R.id.tv_dep_week)
    TextView tvDepWeek;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.iv_time_line)
    ImageView ivTimeLine;
    @Bind(R.id.tv_dep_time)
    TextView tvDepTime;
    @Bind(R.id.tv_arr_time)
    TextView tvArrTime;
    @Bind(R.id.tv_dep_name)
    TextView tvDepName;
    @Bind(R.id.tv_arr_name)
    TextView tvArrName;
    @Bind(R.id.tv_total_time)
    TextView tvTotalTime;
    @Bind(R.id.tv_avi_name)
    TextView tvAviName;
    @Bind(R.id.lv_seat)
    ListView lvSeat;

    FlightSeatActivity mActivity;

    private FlightListBean mFlightListBean;
    private int listPosition;
    private FlightsBean mFlightsBean;

    private FlightCity mDepCity;
    private FlightCity mArrCity;
    private String mDepDate;


    private FlightSeatAdapter mSeatAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//绑定竖屏
        StatusBarUtils.setWindowStatusBarColor(FlightSeatActivity.this, R.color.app_theme_green);
        setContentView(R.layout.activity_flight_list_detail);
        mActivity = FlightSeatActivity.this;
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mFlightsBean = (FlightsBean) intent.getSerializableExtra("data");
        mDepCity = (FlightCity) intent.getSerializableExtra("dep_city");
        mArrCity = (FlightCity) intent.getSerializableExtra("arr_city");
        mDepDate = intent.getStringExtra("dep_date");

        tvDepCity.setText(mDepCity.getAreaname());
        tvArrCity.setText(mArrCity.getAreaname());
        tvDepDate.setText(mDepDate.substring(5));
        tvDepWeek.setText(DateUtil.getEWeek(mDepDate));
        tvCity.setText(mDepCity.getAreaname() + "-" + mArrCity.getAreaname());
        tvDepTime.setText(mFlightsBean.getDepTime());
        tvArrTime.setText(mFlightsBean.getArriTime());
        tvDepName.setText(mFlightsBean.getDepName());
        tvArrName.setText(mFlightsBean.getArrName());
        tvAviName.setText(mFlightsBean.getAviName() + "\n" + mFlightsBean.getFlightNo());

        DateUtil.DateBean differenceTime = DateUtil.getDifferenceTime(mDepDate + " " + mFlightsBean.getDepTime()
                , mFlightsBean.getParam1() + " " + mFlightsBean.getArriTime());

        tvTotalTime.setText(differenceTime.getHour() + "h" + differenceTime.getMin() + "m");
        mSeatAdapter = new FlightSeatAdapter(mActivity, mFlightsBean.getSeatItems());
        mSeatAdapter.setParams(mFlightsBean, mDepCity, mArrCity, mDepDate);
        lvSeat.setAdapter(mSeatAdapter);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
