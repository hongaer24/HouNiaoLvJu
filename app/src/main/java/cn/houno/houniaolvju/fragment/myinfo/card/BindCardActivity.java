package cn.houno.houniaolvju.fragment.myinfo.card;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.NameEditText;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：绑定会员卡界面
 * 创建人：qzc
 * 创建时间：2016/11/14 14:26
 * 修改人：qzc
 * 修改时间：2016/11/14 14:26
 * 修改备注：
 */
public class BindCardActivity extends Activity {

    private static final int GET_CORD = 101;
    private static final int BIND_CARD = 102;

    private String url;

    private ImageView ivBack;

    private RelativeLayout rlBanner;

    private NameEditText etName;
    private EditText etCard;
    private EditText etPhone;
    private EditText etCode;

    private TextView btnGetCord;

    private TextView btnBind;
    private TimeCount time;

    private String userId;
    private String mCardStr;
    private String mPhoneStr;
    private String mNameStr;
    private String mCodeStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_bind_card);
        StatusBarUtils.setWindowStatusBarColor(BindCardActivity.this, R.color.app_theme_green);
        initFindViewById();
        initData();
        initEvent();
    }

    private void initFindViewById() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        rlBanner = (RelativeLayout) findViewById(R.id.rl_banner);
        etName = (NameEditText) findViewById(R.id.et_vip_name);
        etCard = (EditText) findViewById(R.id.et_vip_card);
        etPhone = (EditText) findViewById(R.id.et_phone);
        btnGetCord = (TextView) findViewById(R.id.tv_get_cord);
        btnBind = (TextView) findViewById(R.id.btn_bind);
        etCode = (EditText) findViewById(R.id.et_code);
    }

    private void initData() {
        userId = PrefUtils.getString(BindCardActivity.this, "userid", "");
        time = new TimeCount(60000, 1000);
    }

    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBindCard();
            }
        });

        btnGetCord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGetCode();
            }
        });

        rlBanner.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }


    /*
    * 点击获取验证码
    * */

    private void clickGetCode() {
        boolean isTel = true;
        //判断输入的用户名是否是电话号码
        if (etPhone.getText().toString().length() == 11) {
            for (int i = 0; i < etPhone.getText().toString().length(); i++) {
                char c = etPhone.getText().toString().charAt(i);
                if (!Character.isDigit(c)) {
                    isTel = false;
                    break;//只要有一位不符合要求退出循环
                }
            }
        } else {
            isTel = false;
        }
        if (TextUtils.isEmpty(etCard.getText().toString().trim())) {
            Toast.makeText(BindCardActivity.this, "旅居卡号不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            Toast.makeText(BindCardActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else if (!isTel) {
            Toast.makeText(BindCardActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
        } else {
            mCardStr = etCard.getText().toString().trim();
            mPhoneStr = etPhone.getText().toString().trim();
            GetDataFromServer(GET_CORD);
        }
    }

    /*
    * 点击绑定
    * */
    private void clickBindCard() {
        if (TextUtils.isEmpty(etCard.getText().toString().trim())) {
            Toast.makeText(BindCardActivity.this, "旅居卡号不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etName.getText().toString().trim())) {
            Toast.makeText(BindCardActivity.this, "会员姓名不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            Toast.makeText(BindCardActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etCode.getText().toString().trim())) {
            Toast.makeText(BindCardActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            mCardStr = etCard.getText().toString().trim();
            mNameStr = etName.getText().toString().trim();
            mPhoneStr = etPhone.getText().toString().trim();
            mCodeStr = etCode.getText().toString().trim();
            GetDataFromServer(BIND_CARD);
        }
    }

    private void GetDataFromServer(final int type) {
        RequestParams params = null;
        if (type == GET_CORD) {
            params = new RequestParams(Constants.GET_CARD_CODE);
            params.addBodyParameter("cardno", mCardStr);
            params.addBodyParameter("userid", userId);
            params.addBodyParameter("mobile", mPhoneStr);
        } else if (type == BIND_CARD) {
            params = new RequestParams(Constants.BIND_MEMBER_CARD);
            params.addBodyParameter("userid", userId);
            params.addBodyParameter("cardno", mCardStr);
            params.addBodyParameter("username", mNameStr);
            params.addBodyParameter("mobile", mPhoneStr);
            params.addBodyParameter("code", mCodeStr);
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Log.e("CODE_CARD_SUCCESS", result);
                    JSONObject json = new JSONObject(result);
                    int status = json.getInt("status");
                    if (status == 1) {
                        Toast.makeText(BindCardActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        Toast.makeText(BindCardActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        if (type == GET_CORD) {
                            time.start();
                        } else if (type == BIND_CARD) {
                            PrefUtils.setBoolean(BindCardActivity.this, "isCard", true);
                            finish();
                        }

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

            }
        });

    }

    class TimeCount extends CountDownTimer {


        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            btnGetCord.setClickable(false);
            btnGetCord.setText(millisUntilFinished / 1000 + "秒后重发");
        }

        @Override
        public void onFinish() {
            btnGetCord.setText("重新发送");
            btnGetCord.setClickable(true);

        }
    }
}
