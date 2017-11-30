package cn.houno.houniaolvju.fragment.myinfo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class BackPasswordActivity extends Activity {

    private LinearLayout llTopBar;
    private ImageView ivBack;
    private EditText etbackphone;
    private TextView tvbackyzm;
    private EditText etbackyzcode;
    private EditText etbackpsw;
    private EditText etqrpsw;
    private Button btnbackpsw;
    private String backphone;
    private TimesCount times;
    private String code;
    private String token;
    private String backyzcode;
    private String etbkqrpsw;
    private RelativeLayout rlBackPwdBg;

    private int mStatusBarHeight;   //状态栏高度

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
        setContentView(R.layout.activity_backpassword);
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

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvbackyzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTel = true;
                //判断输入的用户名是否是电话号码
                if (etbackphone.getText().toString().length() == 11) {
                    for (int i = 0; i < etbackphone.getText().toString().length(); i++) {
                        char c = etbackphone.getText().toString().charAt(i);
                        if (!Character.isDigit(c)) {
                            isTel = false;
                            break;//只要有一位不符合要求退出循环
                        }
                    }
                } else {
                    isTel = false;
                }
                //Toast.makeText(RegisterActivity.this, "验证码正在发送...", Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(etbackphone.getText())) {
                    Toast.makeText(BackPasswordActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (!isTel) {
                    Toast.makeText(BackPasswordActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
                } else {
                    backphone = etbackphone.getText().toString().trim();
                    postPhonetoServer(backphone);

                }
            }
        });

        btnbackpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backpassword();
            }
        });

        rlBackPwdBg.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }


    private void backpassword() {
        boolean isTel = true;
        //判断输入的用户名是否是电话号码
        if (etbackphone.getText().toString().length() == 11) {
            for (int i = 0; i < etbackphone.getText().toString().length(); i++) {
                char c = etbackphone.getText().toString().charAt(i);
                if (!Character.isDigit(c)) {
                    isTel = false;
                    break;//只要有一位不符合要求退出循环
                }
            }
        } else {
            isTel = false;
        }

        if (TextUtils.isEmpty(etbackphone.getText())) {
            Toast.makeText(BackPasswordActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();

        } else if (!isTel) {
            Toast.makeText(BackPasswordActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(etbackyzcode.getText().toString().trim())) {
            Toast.makeText(BackPasswordActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();

        } else if (!(etbackyzcode.getText().toString().trim().equals(code))) {
            Toast.makeText(BackPasswordActivity.this, "验证码不正确", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(etbackpsw.getText())) {
            Toast.makeText(BackPasswordActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(etqrpsw.getText())) {
            Toast.makeText(BackPasswordActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();

        } else if (etbackpsw.getText().toString().length() < 6
                || etbackpsw.getText().toString().length() > 16) {
            Toast.makeText(BackPasswordActivity.this, "密码长度为6-16位", Toast.LENGTH_SHORT).show();
        } else if (etqrpsw.getText().toString().length() < 6
                || etqrpsw.getText().toString().length() > 16) {
            Toast.makeText(BackPasswordActivity.this, "密码长度为6-16位", Toast.LENGTH_SHORT).show();
        } else if (!etbackpsw.getText().toString().equals(etqrpsw.getText().toString())) {
            Toast.makeText(BackPasswordActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
        } else {
            backyzcode = etbackyzcode.getText().toString().trim();
            etbkqrpsw = etqrpsw.getText().toString().trim();
            RequestParams rparams = new RequestParams(Constants.BACKPSW_URL);
            rparams.addBodyParameter("mobile", backphone);
            rparams.addBodyParameter("pwd", etbkqrpsw);
            rparams.addBodyParameter("token", token);
            x.http().post(rparams, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject jsono = new JSONObject(result);
                        String msg = jsono.getString("msg");
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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

    private void postPhonetoServer(String backphone) {
        RequestParams params = new RequestParams(Constants.BACKPSWGETYZM_URL);
        params.addBodyParameter("mobile", backphone);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    int status = json.getInt("status");
                    String msg = json.getString("msg");
                    if (status == 1) {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        times.start();
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
            JSONObject jsons = new JSONObject(result);
            code = jsons.getString("code");
            token = jsons.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        times = new TimesCount(60000, 1000);
        llTopBar = (LinearLayout) findViewById(R.id.ll_top_bar);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        rlBackPwdBg = (RelativeLayout) findViewById(R.id.rl_backpwd_bg);
        etbackphone = (EditText) findViewById(R.id.et_back_phone);
        tvbackyzm = (TextView) findViewById(R.id.tv_back_hq_yzm);
        etbackyzcode = (EditText) findViewById(R.id.et_back_yz_code);
        etbackpsw = (EditText) findViewById(R.id.et_back_psw);
        etqrpsw = (EditText) findViewById(R.id.et_back_qr_psw);
        btnbackpsw = (Button) findViewById(R.id.btn_back_psw);

    }

    class TimesCount extends CountDownTimer {


        public TimesCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvbackyzm.setClickable(false);
            tvbackyzm.setText(millisUntilFinished / 1000 + "s重发");
        }

        @Override
        public void onFinish() {
            tvbackyzm.setText("重新发送");
            tvbackyzm.setClickable(true);
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


}
