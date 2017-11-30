package cn.houno.houniaolvju.activity.ydhotel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.OrderDetailActivity;
import cn.houno.houniaolvju.fragment.orderpage.IngOrderPager;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 异地养老填写订单
 * Created by qzc on 2017/1/9.
 */

public class FillInYdOrderActivity extends Activity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @Bind(R.id.iv_home)
    ImageView ivHome;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.tv_yd_order_title)
    TextView tvYdOrderTitle;
    @Bind(R.id.tv_yd_order_address)
    TextView tvYdOrderAddress;
    @Bind(R.id.tv_yd_order_price)
    TextView tvYdOrderPrice;
    @Bind(R.id.ll_yd_order_price)
    LinearLayout llYdOrderPrice;
    @Bind(R.id.tv_tg_rule)
    TextView tvTgRule;
    @Bind(R.id.tv_yw)
    TextView tvYw;
    @Bind(R.id.tv_days_selector)
    TextView tvDaysSelector;
    @Bind(R.id.tv_days_total)
    TextView tvDaysTotal;
    @Bind(R.id.btn_day_sub)
    Button btnDaySub;
    @Bind(R.id.tv_day_num)
    TextView tvDayNum;
    @Bind(R.id.btn_day_add)
    Button btnDayAdd;
    @Bind(R.id.btn_refer_sub)
    Button btnReferSub;
    @Bind(R.id.tv_refer_num)
    TextView tvReferNum;
    @Bind(R.id.btn_refer_add)
    Button btnReferAdd;
    @Bind(R.id.tv_yk)
    TextView tvYk;
    @Bind(R.id.et_jd_name)
    EditText etJdName;
    @Bind(R.id.tv_jd_phone)
    TextView tvJdPhone;
    @Bind(R.id.et_jd_phone)
    EditText etJdPhone;
    @Bind(R.id.et_yd_memo)
    EditText etYdMemo;
    @Bind(R.id.tv_zxzf)
    TextView tvZxzf;
    @Bind(R.id.tv_zxzf_money)
    TextView tvZxzfMoney;
    @Bind(R.id.btn_jd_tjdd)
    Button btnJdTjdd;
    private FillInYdOrderActivity mActivity;


    private String userid;

    private int intRoomNum = 1;
    private int roomNumEst = 1000;
    private double price;  //单价
    private double allPrice;    //总价
    private int mIntDays = 1;

    private String mYdTitle;
    private String mYdAddress;
    private String mHid;
    private String mRid;
    private String mModel;
    private String mType;
    private String mOuid;
    private String mRoom;
    //住店日期选择
    private String mCheckIn;
    private String mCheckOut;

    private String mCheckInWeek;
    private String mCheckOutWeek;

    private int mRzMin;    //最小周期
    private int mRzMax;    //最大周期


    private ProgressDialog mProgressDialog;

    private boolean isLogined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_fillin_yd_order);
        ButterKnife.bind(this);
        mActivity = FillInYdOrderActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        isLogined = PrefUtils.getBoolean(mActivity, "isLogined", false);
        if (isLogined) {
            userid = PrefUtils.getString(mActivity, "userid", "");
            etJdName.setText(PrefUtils.getString(mActivity, "nick", ""));
            etJdPhone.setText(PrefUtils.getString(mActivity, "mobile", ""));
        } else {
            userid = Constants.PUBLIC_USER_ID;
        }
        Intent intent = getIntent();
        mYdTitle = intent.getStringExtra("title");
        tvTopbarTitle.setText(mYdTitle);
        mYdAddress = intent.getStringExtra("address");
        tvYdOrderAddress.setText(mYdAddress);

        mHid = intent.getStringExtra("id");
        mRid = intent.getStringExtra("rid");
        mIntDays =  mRzMin = intent.getIntExtra("rzmin", 1);
        mRzMax = intent.getIntExtra("rzmax", 1);
        tvDayNum.setText(mIntDays+"");

        mRoom = intent.getStringExtra("roomtitle");
        tvYdOrderTitle.setText(mRoom);
        allPrice =  price = intent.getDoubleExtra("price", 0);
        MyText2Utils.formatYuanPrice(mActivity, tvYdOrderPrice, price + "");
        setPrice();
        mCheckIn = DateUtil.getNowTime(DateUtil.DATE_SMALL_STR);
        mCheckInWeek = DateUtil.getWeek(mCheckIn);
        mCheckOut = DateUtil.getDate(mRzMin);
        mCheckOutWeek = DateUtil.getWeek(mCheckOut);
        setDate();
    }

    private void initEvent() {


        btnReferSub.setOnClickListener(new RoomClick());
        btnReferAdd.setOnClickListener(new RoomClick());

        btnDaySub.setOnClickListener(new DaysClick());
        btnDayAdd.setOnClickListener(new DaysClick());
    }

    private void initView() {

    }


    private void getDataFromServer() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在提交订单");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
        Map<String, String> params = new HashMap<>();

        params.put("userid", userid);
        params.put("id", mHid);
        params.put("rid", mRid);
        params.put("info[phone]", etJdPhone.getText().toString().trim());
        params.put("info[username]", etJdName.getText().toString().trim());
        params.put("info[memo]", etYdMemo.getText().toString().trim());

        params.put("info[ydroom_name]", mYdTitle);
        params.put("type", "ydhotel");
        params.put("info[checkin]", mCheckIn);
        params.put("info[checkout]", mCheckOut);
        params.put("info[roomnum]", intRoomNum + "");


        OkHttpClientManager.postAsync(Constants.YYHOTEL_ORDER, params
                , null, new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        switch (msg.what) {
                            case R.id.doSucceed:
                                String succeedResult = msg.obj.toString();
                                Log.i("order", succeedResult);
                                try {
                                    JSONObject obj = new JSONObject(succeedResult);
                                    if (obj.getInt("status") == 0) {
                                        Intent intent = new Intent();
                                        intent.putExtra("type", "ydhotel");
                                        intent.putExtra("orderno", obj.getString("orderno"));
                                        intent.putExtra("title", mYdTitle + " - " + mRoom);
                                        intent.putExtra("price", allPrice);
                                        intent.setClass(mActivity, OrderDetailActivity.class);
                                        IngOrderPager.refresh=true;
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                                        //没有酒店数据
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case R.id.doFail:
                                break;
                        }
                    }
                }, R.id.doSucceed, R.id.doFail);
    }

    @OnClick({R.id.iv_back, R.id.iv_home, R.id.tv_days_selector, R.id.btn_jd_tjdd})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_home:
                //返回主页面
                intent.setClass(mActivity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_days_selector:
                intent.setClass(mActivity, YdDatePickerActivity.class);
                intent.putExtra("rzdate", mRzMin);
                startActivityForResult(intent, 301);
                break;
            case R.id.btn_jd_tjdd:
                checkInputAndGetData();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 300 && requestCode == 301) {
            Bundle extras = data.getExtras();
            mCheckIn = extras.getString("dateIn");
            mCheckInWeek = extras.getString("dateInWeek");
            if (mRzMin == 1) {
                mIntDays = Integer.parseInt(extras.getString("days"));
                if (mIntDays>mRzMax){
                    mIntDays = mRzMax;
                    mCheckOut = DateUtil.getTheDate(mCheckIn,mRzMax);
                }else {
                    mCheckOut = extras.getString("dateOut");
                }
                mCheckOutWeek = DateUtil.getWeek(mCheckOut);
            } else {
                mCheckOut = DateUtil.getTheDate(mCheckIn, mRzMin);
                mCheckOutWeek = DateUtil.getWeek(mCheckOut);
                mIntDays = mRzMin;
            }
            tvDayNum.setText(mIntDays+"");
            setDate();
            setPrice();
        }
    }


    private void getData() {
        mCheckOut = DateUtil.getTheDate(mCheckIn,mIntDays);
        mCheckOutWeek = DateUtil.getWeek(mCheckOut);
    }


    private void setDate() {
        tvDaysSelector.setText("入住 " + mCheckIn + "  " + mCheckInWeek + "\n" + "离开 " + mCheckOut + "  " + mCheckOutWeek);
        tvDaysTotal.setText("共" + mIntDays + "天");
    }

    private void checkInputAndGetData() {
        if (TextUtils.isEmpty(etJdName.getText().toString().trim())) {
            Toast.makeText(mActivity, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isTel = true;
        //判断输入的用户名是否是电话号码
        if (etJdPhone.getText().toString().length() == 11) {
            for (int i = 0; i < etJdPhone.getText().toString().length(); i++) {
                char c = etJdPhone.getText().toString().charAt(i);
                if (!Character.isDigit(c)) {
                    isTel = false;
                    break;//只要有一位不符合要求退出循环
                }
            }
        } else {
            isTel = false;
        }

        if (TextUtils.isEmpty(etJdPhone.getText())) {
            Toast.makeText(mActivity, "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else if (!isTel) {
            Toast.makeText(mActivity, "手机号格式不对", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("FillInScenicOrderAct", "success");
            getDataFromServer();
        }
    }

    private class RoomClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.btn_refer_sub) {
                if (intRoomNum > 1) {
                    intRoomNum--;
                    tvReferNum.setText(intRoomNum + "");
                }
            } else if (i == R.id.btn_refer_add) {
                if (intRoomNum < roomNumEst) {
                    intRoomNum++;
                    tvReferNum.setText(intRoomNum + "");
                }
            }
            setPrice();
        }
    }

    private void setPrice() {
        allPrice = price * intRoomNum * mIntDays;
        MyText2Utils.formatTicketPrice(mActivity, tvZxzfMoney, allPrice + "");
    }

    private class DaysClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.btn_day_sub) {
                if (mIntDays > mRzMin) {
                    mIntDays--;
                    tvDayNum.setText(mIntDays + "");
                }
            } else if (i == R.id.btn_day_add) {
                if (mIntDays < mRzMax) {
                    mIntDays++;
                    tvDayNum.setText(mIntDays + "");
                }
            }
            getData();
            setDate();
            setPrice();
        }
    }

}
