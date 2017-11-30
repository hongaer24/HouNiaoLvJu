package cn.houno.houniaolvju.fragment.myinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class ModifyPasswordActivity extends Activity {


    private LinearLayout llTopBar;
    private ImageView ivBack;
    private RelativeLayout rlSetPwdBg;
    private EditText etoldpsw;
    private EditText etnewpsw;
    private EditText etqrpsw;
    private Button btnsetpsw;

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
        setContentView(R.layout.activity_modify_psw);
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

        rlSetPwdBg.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnsetpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etoldpsw.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "原密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etnewpsw.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "新密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etqrpsw.getText().toString().trim())) {
                    Toast.makeText(getApplicationContext(), "确认密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (etnewpsw.getText().toString().length() < 6
                        || etnewpsw.getText().toString().length() > 16) {
                    Toast.makeText(getApplicationContext(), "密码长度为6-16位", Toast.LENGTH_SHORT).show();
                } else if (etqrpsw.getText().toString().length() < 6
                        || etqrpsw.getText().toString().length() > 16) {
                    Toast.makeText(getApplicationContext(), "密码长度为6-16位", Toast.LENGTH_SHORT).show();
                } else if (!etnewpsw.getText().toString().equals(etqrpsw.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    String userid = PrefUtils.getString(ModifyPasswordActivity.this, "userid", "");
                    String oldpsw = etoldpsw.getText().toString().trim();
                    String qrpsw = etqrpsw.getText().toString().trim();
                    RequestParams params = new RequestParams(Constants.SETPASSWORD_URL);
                    params.addBodyParameter("userid", userid);
                    params.addBodyParameter("oldpwd", oldpsw);
                    params.addBodyParameter("password", qrpsw);
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
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    //返回主页面
                                    PrefUtils.setBoolean(ModifyPasswordActivity.this, "isLogined", false);
                                    PrefUtils.setBoolean(ModifyPasswordActivity.this, "check_my", true);
                                    Intent intent = new Intent(ModifyPasswordActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
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
            }
        });

    }

    private void initView() {
        llTopBar = (LinearLayout) findViewById(R.id.ll_top_bar);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        rlSetPwdBg = (RelativeLayout) findViewById(R.id.rl_set_pwd);
        etoldpsw = (EditText) findViewById(R.id.et_old_psw);
        etnewpsw = (EditText) findViewById(R.id.et_new_psw);
        etqrpsw = (EditText) findViewById(R.id.et_qr_new_psw);
        btnsetpsw = (Button) findViewById(R.id.btn_set_psw);

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

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
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
