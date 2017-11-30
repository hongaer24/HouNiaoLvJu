package cn.houno.houniaolvju.activity.hotel;

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
import cn.houno.houniaolvju.activity.DatePickerActivity;
import cn.houno.houniaolvju.activity.OrderDetailActivity;
import cn.houno.houniaolvju.fragment.orderpage.IngOrderPager;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 酒店填写订单
 * Created by qzc on 2017/1/9.
 */

public class FillInHotelOrderActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @Bind(R.id.iv_home)
    ImageView ivHome;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.tv_hotel_order_title)
    TextView tvHotelOrderTitle;
    @Bind(R.id.tv_hotel_order_address)
    TextView tvHotelOrderAddress;
    @Bind(R.id.tv_hotel_order_price)
    TextView tvHotelOrderPrice;
    @Bind(R.id.ll_hotel_order_price)
    LinearLayout llHotelOrderPrice;
    @Bind(R.id.tv_tg_rule)
    TextView tvTgRule;
    @Bind(R.id.tv_yw)
    TextView tvYw;
    @Bind(R.id.tv_days_selector)
    TextView tvDaysSelector;
    @Bind(R.id.tv_days_total)
    TextView tvDaysTotal;
    @Bind(R.id.btn_refer_sub)
    Button btnReferSub;
    @Bind(R.id.tv_refer_num)
    TextView tvReferNum;
    @Bind(R.id.btn_refer_add)
    Button btnReferAdd;
    @Bind(R.id.et_rzr_name)
    EditText etRzrName;
    @Bind(R.id.et_jd_name)
    EditText etJdName;
    @Bind(R.id.tv_jd_phone)
    TextView tvJdPhone;
    @Bind(R.id.et_jd_phone)
    EditText etJdPhone;
    @Bind(R.id.tv_zxzf_money)
    TextView tvZxzfMoney;
    @Bind(R.id.btn_jd_tjdd)
    Button btnJdTjdd;
    @Bind(R.id.et_hotel_memo)
    EditText etMemo;
    private FillInHotelOrderActivity mActivity;


    private String userid ;

    private int intRoomNum = 1;
    private int roomNumEst = 1000;
    private int price;  //单价
    private int allPrice;    //总价
    private int mIntDays = 1;

    private String mHotelTitle;
    private String mHotelAddress;
    private String mHid;
    private String mRid;
    private String mModel;
    private String mType;
    private String mOuid;
    private String mRoom;
    private String mPayfs;  //支付方式
    //住店日期选择
    private String mCheckIn;
    private String mCheckOut;
    private String mStrDays = "1";
    private String mCheckInWeek;

    private String mCheckOutWeek;
    private ProgressDialog mProgressDialog;

    private boolean isLogined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_fillin_hotel_order);
        ButterKnife.bind(this);
        mActivity = FillInHotelOrderActivity.this;
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
        mHotelTitle = intent.getStringExtra("title");
        tvTopbarTitle.setText(mHotelTitle);
        mHotelAddress = intent.getStringExtra("address");
        mPayfs = intent.getStringExtra("payfs");
        tvHotelOrderAddress.setText(mHotelAddress);
        mHid = intent.getStringExtra("hid");
        mRid = intent.getStringExtra("rid");
        mModel = intent.getStringExtra("model");
        mType = intent.getStringExtra("type");

        if (mType.equals("2")) {
            //显示团购提示
            tvTgRule.setVisibility(View.VISIBLE);

            tvTgRule.setText("提示：您预订的房间是多间成团补贴房型" +
                    "，活动有效日期为：【" + intent.getStringExtra("starttime") +
                    "】至【" + intent.getStringExtra("endtime") + "】" +
                    "，有效期内预订的间数当日达到规则间数，将实行定额补贴优惠" +
                    "，补贴金额3~5个工作日自动充值到您账户的“钱包”中。请查询、使用。");
        }
        mOuid = intent.getStringExtra("ouid");
        mRoom = intent.getStringExtra("room");
        tvHotelOrderTitle.setText(mRoom);
        price = intent.getIntExtra("price", 0);
        allPrice = price;
        MyText2Utils.formatYuanPrice(mActivity, tvHotelOrderPrice, price + "");
        setPrice();

        mCheckIn = intent.getStringExtra("checkin");
        mCheckOut = intent.getStringExtra("checkout");

        mCheckInWeek = DateUtil.getEWeek(mCheckIn);
        mCheckOutWeek = DateUtil.getEWeek(mCheckOut);
        setDate();
    }

    private void initEvent() {
        btnReferSub.setOnClickListener(new FillInHotelOrderActivity.RoomClick());
        btnReferAdd.setOnClickListener(new FillInHotelOrderActivity.RoomClick());
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
        params.put("info[phone]", etJdPhone.getText().toString().trim());

        params.put("info[checkin]", mCheckIn);
        params.put("info[checkout]", mCheckOut);
        params.put("info[ruzhuname]", etRzrName.getText().toString().trim());
        params.put("info[username]", etJdName.getText().toString().trim());

        params.put("rid", mRid);
        params.put("model", mModel);
        params.put("info[memo]", etMemo.getText().toString().trim());
        params.put("id", mHid);
        params.put("ouid", mOuid);
        params.put("type", mType);
        params.put("info[roomnum]", intRoomNum + "");

        OkHttpClientManager.postAsync(Constants.HOTEL_ORDER_URL, params
                , null, new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                        switch (msg.what) {
                            case R.id.doSucceed:
                                String succeedResult = msg.obj.toString();
                                Log.i("order",succeedResult);
                                try {
                                    JSONObject obj = new JSONObject(succeedResult);
                                    if (obj.getInt("status") == 0) {
                                        Intent intent = new Intent();
                                        intent.putExtra("type", "hotel");
                                        if (mType.equals("2")){
                                            intent.putExtra("istg", true);
                                        }else {
                                            intent.putExtra("istg", false);
                                        }
                                        intent.putExtra("orderno", obj.getString("orderno"));
                                        intent.putExtra("title", mHotelTitle + " - " + mRoom);
                                        intent.putExtra("price", allPrice);
                                        intent.putExtra("payfs", mPayfs);
                                        intent.setClass(mActivity, OrderDetailActivity.class);
                                        IngOrderPager.refresh = true;
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
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_home:
                //返回主页面
                Intent intent = new Intent(mActivity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_days_selector:
                startActivityForResult(new Intent(mActivity, DatePickerActivity.class), 301);
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
            mCheckOut = extras.getString("dateOut");
            mStrDays = extras.getString("days");
            mCheckInWeek = extras.getString("dateInWeek");
            mCheckOutWeek = extras.getString("dateOutWeek");
            mIntDays = Integer.parseInt(mStrDays);
            setDate();
            setPrice();
        }
    }

    private void setDate() {
        tvDaysSelector.setText("入住 " + mCheckIn + "  " + mCheckInWeek + "\n" + "离开 " + mCheckOut + "  " + mCheckOutWeek);
        tvDaysTotal.setText("共" + mStrDays + "天");
    }

    private void checkInputAndGetData() {
        if (TextUtils.isEmpty(etRzrName.getText().toString().trim())) {
            Toast.makeText(mActivity, "入住人不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etJdName.getText().toString().trim())) {
            Toast.makeText(mActivity, "联系人不能为空", Toast.LENGTH_SHORT).show();
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
}
