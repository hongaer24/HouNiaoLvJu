package cn.houno.houniaolvju.fragment.myinfo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import cn.houno.houniaolvju.utils.ReadSmsContent;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class RegisterActivity extends Activity implements View.OnClickListener{

    private LinearLayout llTopBar;
    private ImageView ivBack;
    private EditText etregisterphone;
    private TextView tvyzm;
    private EditText etyzcode;
    private Button btnregister;

    private String rgsphone;
    private TimeCount time;
    private String code;
    private EditText etpsw;
    private EditText etqrpsw;

    private RelativeLayout rlRegisterBackGround;


    private int mStatusBarHeight;   //状态栏高度


    private ReadSmsContent readSmsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_register);
        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView();

        initData();
    }

    private void initData() {
        //获取状态栏高度
        mStatusBarHeight = -1;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            mStatusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            llTopBar.setPadding(10, mStatusBarHeight + 10, 10, 10);
        }
        ivBack.setOnClickListener(this);
        tvyzm.setOnClickListener(this);
        btnregister.setOnClickListener(this);

        rlRegisterBackGround.setOnTouchListener(new View.OnTouchListener()
        {

            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        initSmsContent();
    }

    private void initSmsContent() {
        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.
                permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            //没有权限，请求权限
            ActivityCompat.requestPermissions(RegisterActivity.this
                    , new String[]{Manifest.permission.READ_SMS}, 1);
        } else {
            registerSmsContent();
        }
    }

    private void registerSmsContent() {
        readSmsContent = new ReadSmsContent(new Handler(), this, etyzcode);
        //注册短信内容监听
        this.getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, readSmsContent);

    }

    private void initView() {
        time = new TimeCount(60000, 1000);
        llTopBar = (LinearLayout) findViewById(R.id.ll_top_bar);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        rlRegisterBackGround = (RelativeLayout) findViewById(R.id.rl_register_bg);
        etregisterphone = (EditText) findViewById(R.id.et_register_phone);
        tvyzm = (TextView) findViewById(R.id.tv_hq_yzm);
        etyzcode = (EditText) findViewById(R.id.et_yz_code);
        etpsw = (EditText) findViewById(R.id.et_rgst_psw);
        etqrpsw = (EditText) findViewById(R.id.et_qr_psw);

        btnregister = (Button) findViewById(R.id.btn_my_register);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_hq_yzm:
                clickGetCode();
                break;
            case R.id.btn_my_register:
                register();
                break;

        }
    }

    private void register() {
        boolean isTel = true;
        //判断输入的用户名是否是电话号码
        if (etregisterphone.getText().toString().length() == 11) {
            for (int i = 0; i < etregisterphone.getText().toString().length(); i++) {
                char c = etregisterphone.getText().toString().charAt(i);
                if (!Character.isDigit(c)) {
                    isTel = false;
                    break;//只要有一位不符合要求退出循环
                }
            }
        } else {
            isTel = false;
        }

        if (TextUtils.isEmpty(etregisterphone.getText())) {
            Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();

        }else if (!isTel) {
            Toast.makeText(RegisterActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(etyzcode.getText().toString().trim())) {
            Toast.makeText(RegisterActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();

        } else if (!(etyzcode.getText().toString().trim().equals(code))) {
            Toast.makeText(RegisterActivity.this, "验证码不正确", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(etpsw.getText())) {
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(etqrpsw.getText())) {
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();

        } else if (etpsw.getText().toString().length() < 6
                || etpsw.getText().toString().length() > 16) {
            Toast.makeText(RegisterActivity.this, "密码长度为6-16位", Toast.LENGTH_SHORT).show();
        } else if (etqrpsw.getText().toString().length() < 6
                || etqrpsw.getText().toString().length() > 16) {
            Toast.makeText(RegisterActivity.this, "密码长度为6-16位", Toast.LENGTH_SHORT).show();
        }else if (!etpsw.getText().toString().equals(etqrpsw.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();

        } else {
            String qrpsw = etqrpsw.getText().toString().trim();
            String yzcode = etyzcode.getText().toString().trim();
            RequestParams params = new RequestParams(Constants.REGISTER_URL);
            params.addBodyParameter("mobile", rgsphone);
            params.addBodyParameter("pwd", qrpsw);
            params.addBodyParameter("code", yzcode);
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println(result);
                    try {
                        JSONObject json = new JSONObject(result);
                        int status = json.getInt("status");
                        String msg = json.getString("msg");
                        if (status == 0) {
                            Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                        } else if (status == 1){
                            Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                    finish();
                }
            });
        }

    }

    private void clickGetCode() {
        boolean isTel = true;
        //判断输入的用户名是否是电话号码
        if (etregisterphone.getText().toString().length() == 11) {
            for (int i = 0; i < etregisterphone.getText().toString().length(); i++) {
                char c = etregisterphone.getText().toString().charAt(i);
                if (!Character.isDigit(c)) {
                    isTel = false;
                    break;//只要有一位不符合要求退出循环
                }
            }
        } else {
            isTel = false;
        }

        if (TextUtils.isEmpty(etregisterphone.getText())) {
            Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
        }else if (!isTel) {
            Toast.makeText(RegisterActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
        } else {
            rgsphone = etregisterphone.getText().toString().trim();
            postPhonetoServer(rgsphone);


        }

    }

    private void postPhonetoServer(String rgsphone) {
        RequestParams params = new RequestParams(Constants.GETCODE_URL);
        params.addBodyParameter("mobile", rgsphone);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    int status = json.getInt("status");
                    String msg = json.getString("msg");
                    System.out.println(result);
                    if (status == 1) {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        time.start();
                        Toast.makeText(getApplicationContext(), "发送成功，请接收短信", Toast.LENGTH_SHORT).show();
                        parseCode(result);
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

    private void parseCode(String result) {
        try {
            JSONObject jsonO = new JSONObject(result);
            code = jsonO.getString("code");
            System.out.println(code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    class TimeCount extends CountDownTimer {


        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            tvyzm.setClickable(false);
            tvyzm.setText(millisUntilFinished / 1000 +"s重发");
        }

        @Override
        public void onFinish() {
            tvyzm.setText("重新发送");
            tvyzm.setClickable(true);

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    register();
                }else {
                    //拒绝授权
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.getContentResolver().unregisterContentObserver(readSmsContent);
    }
}
