package cn.houno.houniaolvju.fragment.myinfo.card;

import android.app.Activity;
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

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：会员卡修改密码
 * 创建人：qzc
 * 创建时间：2016/11/28 8:54
 * 修改人：qzc
 * 修改时间：2016/11/28 8:54
 * 修改备注：
 */
public class ModifyCardPassword  extends Activity{

    private LinearLayout llTopbar;
    private RelativeLayout rlBackGround;
    private ImageView ivBack;

    private EditText etOldPwd;
    private EditText etNewPwd;
    private EditText etNewPwdConfirm;

    private Button btnModifyPwd;

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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_modify_card_password);
        initFindViewById();
        initData();
        initEvent();
    }

    private void initFindViewById() {
        llTopbar = (LinearLayout) findViewById(R.id.ll_top_bar);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        rlBackGround = (RelativeLayout) findViewById(R.id.rl_set_pwd);
        etOldPwd = (EditText) findViewById(R.id.et_old_psw);
        etNewPwd = (EditText) findViewById(R.id.et_new_psw);
        etNewPwdConfirm = (EditText) findViewById(R.id.et_qr_new_psw);
        btnModifyPwd = (Button) findViewById(R.id.btn_set_psw);
    }

    private void initEvent() {
        rlBackGround.setOnTouchListener(new View.OnTouchListener() {

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

        btnModifyPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyPassword();
            }
        });
    }



    private void modifyPassword() {
        if (TextUtils.isEmpty(etOldPwd.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "原密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etNewPwd.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "新密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etNewPwdConfirm.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "确认密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (etNewPwd.getText().toString().length() < 6
                || etNewPwd.getText().toString().length() > 16) {
            Toast.makeText(getApplicationContext(), "密码长度为6-16位", Toast.LENGTH_SHORT).show();
        } else if (etNewPwdConfirm.getText().toString().length() < 6
                || etNewPwdConfirm.getText().toString().length() > 16) {
            Toast.makeText(getApplicationContext(), "密码长度为6-16位", Toast.LENGTH_SHORT).show();
        } else if (!etNewPwd.getText().toString().equals(etNewPwdConfirm.getText().toString())) {
            Toast.makeText(getApplicationContext(), "密码不一致", Toast.LENGTH_SHORT).show();
        } else {
            String userid = PrefUtils.getString(ModifyCardPassword.this, "userid", "");
            String oldpsw = etOldPwd.getText().toString().trim();
            String qrpsw = etNewPwdConfirm.getText().toString().trim();
            RequestParams params = new RequestParams(Constants.MODIFY_CARD_PWD);
            params.addBodyParameter("userid", userid);
            params.addBodyParameter("oldpwd", oldpsw);
            params.addBodyParameter("password", qrpsw);
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject json = new JSONObject(result);
                        System.out.println(result);
                        int status = json.getInt("status");
                        String msg = json.getString("msg");
                        if (status == 1) {
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                        } else if (status == 0) {
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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

    private void initData() {
        //获取状态栏高度
        mStatusBarHeight = -1;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            mStatusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            llTopbar.setPadding(10, mStatusBarHeight + 10, 10, 10);
        }
    }
}
