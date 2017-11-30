package cn.houno.houniaolvju.activity;

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
import cn.houno.houniaolvju.activity.scenic.SingleDatePickerActivity;
import cn.houno.houniaolvju.fragment.orderpage.IngOrderPager;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 抢购填写订单
 * Created by Administrator on 2017/1/12.
 */

public class FillInFlashSaleOrderActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @Bind(R.id.iv_home)
    ImageView ivHome;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.tv_flash_sale_order_title)
    TextView tvFlashSaleOrderTitle;
    @Bind(R.id.tv_flash_sale_order_price)
    TextView tvFlashSaleOrderPrice;
    @Bind(R.id.ll_flash_sale_order_price)
    LinearLayout llFlashSaleOrderPrice;
    @Bind(R.id.tv_yw)
    TextView tvYw;
    @Bind(R.id.tv_days_selector)
    TextView tvDaysSelector;
    @Bind(R.id.btn_adult_sub)
    Button btnAdultSub;
    @Bind(R.id.tv_adult_num)
    TextView tvAdultNum;
    @Bind(R.id.btn_adult_add)
    Button btnAdultAdd;
    @Bind(R.id.btn_child_sub)
    Button btnChildSub;
    @Bind(R.id.tv_child_num)
    TextView tvChildNum;
    @Bind(R.id.btn_child_add)
    Button btnChildAdd;
    @Bind(R.id.tv_yk)
    TextView tvYk;
    @Bind(R.id.et_jd_name)
    EditText etJdName;
    @Bind(R.id.tv_jd_phone)
    TextView tvJdPhone;
    @Bind(R.id.et_jd_phone)
    EditText etJdPhone;
    @Bind(R.id.et_flash_sale_memo)
    EditText etFlashSaleMemo;
    @Bind(R.id.tv_ddje)
    TextView tvDdje;
    @Bind(R.id.tv_order_amount)
    TextView tvOrderAmount;
    @Bind(R.id.btn_submit_order)
    Button btnSubmitOrder;

    private String userid;
    private String userName;
    private String phone;

    private int price = 0;  //单价
    private int adultPrice = 0; //成人总价
    private int childPrice = 0; //儿童总价
    private int totalPrice = 0; //总价

    private int intAdultNum = 1;
    private int intAdultNumEst = 1000;
    private int intChildNum = 0;
    private int intChildNumEst = 1000;

    private FillInFlashSaleOrderActivity mActivity;
    private String title;
    private String mCheckIn;
    private String mCheckInWeek;

    private ProgressDialog mProgressDialog;
    private String mId;

    private boolean isLogined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_fillin_flashsale_order);
        ButterKnife.bind(this);
        mActivity = FillInFlashSaleOrderActivity.this;
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
        title = intent.getStringExtra("title");
        mId = intent.getStringExtra("id");
        tvFlashSaleOrderTitle.setText(title);
        price = Integer.valueOf(MyText2Utils.getIntPrice(
                intent.getStringExtra("price")));
        totalPrice = adultPrice = price;
        setTotalAmount();
        MyText2Utils.formatYuanPrice(mActivity, tvFlashSaleOrderPrice, adultPrice + "");
        mCheckIn = DateUtil.getNowTime(DateUtil.DATE_SMALL_STR);
        mCheckInWeek = DateUtil.getWeek(mCheckIn);
        tvDaysSelector.setText(mCheckIn + "  " + mCheckInWeek);
    }

    private void initView() {

    }

    private void initEvent() {
        btnAdultAdd.setOnClickListener(new AdultClick());
        btnAdultSub.setOnClickListener(new AdultClick());
        btnChildAdd.setOnClickListener(new ChildClick());
        btnChildSub.setOnClickListener(new ChildClick());
    }

    private void getDataFromServer() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在提交订单");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
        Map<String, String> params = new HashMap<>();
        params.put("userid", userid);
        params.put("id", mId);
        params.put("info[num]", tvAdultNum.getText().toString().trim());
        params.put("info[childnum]", tvChildNum.getText().toString().trim());
        params.put("info[phone]", etJdPhone.getText().toString().trim());
        params.put("info[username]", etJdName.getText().toString().trim());

        params.put("info[checkin]", mCheckIn);

        OkHttpClientManager.postAsync(Constants.FLASH_SALE_ADD_ORDER, params, null, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
                switch (msg.what) {
                    case R.id.doSucceed:
                        String succeedResult = msg.obj.toString();
                        try {
                            JSONObject obj = new JSONObject(succeedResult);
                            if (obj.getInt("status") == 0) {
                                Intent intent = new Intent();
                                intent.putExtra("type", "activity");
                                intent.putExtra("orderno", obj.getString("orderno"));
                                intent.putExtra("title", title);
                                intent.putExtra("price", totalPrice);
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


    @OnClick({R.id.iv_back, R.id.iv_home, R.id.tv_days_selector, R.id.btn_submit_order})
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
                startActivityForResult(new Intent(mActivity, SingleDatePickerActivity.class), 301);
                break;
            case R.id.btn_submit_order:
                checkInputAndGetData();
                break;
        }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 300 && requestCode == 301) {
            Bundle extras = data.getExtras();
            mCheckIn = extras.getString("dateIn");
            mCheckInWeek = extras.getString("dateInWeek");

            tvDaysSelector.setText(mCheckIn + " " + mCheckInWeek);
        }
    }


    private class AdultClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.btn_adult_sub) {
                if (intAdultNum > 1) {
                    intAdultNum--;
                    tvAdultNum.setText(intAdultNum + "");
                }
            } else if (i == R.id.btn_adult_add) {
                if (intAdultNum < intAdultNumEst) {
                    intAdultNum++;
                    tvAdultNum.setText(intAdultNum + "");
                }
            }
            adultPrice = price * intAdultNum;
            setTotalAmount();
        }
    }

    private void setTotalAmount() {
        totalPrice = adultPrice + childPrice;
        MyText2Utils.formatTicketPrice(mActivity, tvOrderAmount, totalPrice + "");
    }

    private class ChildClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.btn_child_sub) {
                if (intChildNum > 0) {
                    intChildNum--;
                    tvChildNum.setText(intChildNum + "");
                }
            } else if (i == R.id.btn_child_add) {
                if (intChildNum < intChildNumEst) {
                    intChildNum++;
                    tvChildNum.setText(intChildNum + "");
                }
            }
            childPrice = price / 2 * intChildNum;
            setTotalAmount();
        }
    }
}
