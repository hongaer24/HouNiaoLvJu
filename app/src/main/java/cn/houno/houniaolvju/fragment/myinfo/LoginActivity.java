package cn.houno.houniaolvju.fragment.myinfo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.HashMap;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.fragment.orderpage.IngOrderPager;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private LinearLayout llTopBar;
    private ImageView ivbackhome;
    private EditText etphone;
    private EditText etpsw;
    private Button btnlogin;
    private TextView tvzc;
    private TextView tvbackpsw;
    private ImageView ivQQLogin;
    private ImageView ivWXLogin;

    private String loginphone;
    private String loginpsw;

    private RelativeLayout rlLoginBackground;


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
        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);

        ShareSDK.initSDK(this);
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

        ivbackhome.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
        tvzc.setOnClickListener(this);
        tvbackpsw.setOnClickListener(this);
        ivQQLogin.setOnClickListener(this);
        ivWXLogin.setOnClickListener(this);

        //点击登录背景图关闭软键盘
        rlLoginBackground.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View arg0, MotionEvent arg1) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }

    private void initView() {
        llTopBar = (LinearLayout) findViewById(R.id.ll_top_bar);
        ivbackhome = (ImageView) findViewById(R.id.iv_back_home);
        etphone = (EditText) findViewById(R.id.et_login_phone);
        etpsw = (EditText) findViewById(R.id.et_login_psw);
        btnlogin = (Button) findViewById(R.id.btn_my_login);
        tvzc = (TextView) findViewById(R.id.tv_register);
        tvbackpsw = (TextView) findViewById(R.id.tv_back_psw);
        rlLoginBackground = (RelativeLayout) findViewById(R.id.rl_login_bg);
        ivQQLogin = (ImageView) findViewById(R.id.iv_qq_login);
        ivWXLogin = (ImageView) findViewById(R.id.iv_wx_login);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.iv_back_home:
                finish();
                break;
            case R.id.btn_my_login:
                login();
                break;
            case R.id.tv_register:
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_back_psw:
                intent.setClass(LoginActivity.this, BackPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_qq_login:
                otherLogin(QQ.NAME);
                break;
            case R.id.iv_wx_login:
                otherLogin(Wechat.NAME);
                break;
        }
    }

    /*
    * QQ登录
    * */
    private void otherLogin(String loginType) {

        Platform platform = ShareSDK.getPlatform(loginType);
        if (platform.isAuthValid()) {
            platform.removeAccount(true);
            //return  这一步在官方Demo里是没有的,
            //当用户已经授权后,直接return.不会调起授权页面,感觉体验不太好.
        }

        platform.SSOSetting(true);
        platform.setPlatformActionListener(new MyPAListener());

        //关闭SSO授权
        platform.SSOSetting(false);
        platform.showUser(null);
    }


    public class MyPAListener implements PlatformActionListener {

        @Override
        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
            if (i == Platform.ACTION_USER_INFOR) {
                System.out.println(hashMap);
                PlatformDb platformDb = platform.getDb();

                final String qUserName = platformDb.getUserName();
                final String qUserId;
                final String qUserIcon;
                if (TextUtils.equals("Wechat", platform.getName())) {
                    qUserId = hashMap.get("unionid").toString();
                    qUserIcon = hashMap.get("headimgurl").toString();
                } else {
                    qUserId = platformDb.getUserId();
                    qUserIcon = hashMap.get("figureurl_qq_2").toString();    //QQ 100x100头像
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        thirdParLogin(qUserId, qUserName, qUserIcon);
                    }
                });

            }
        }

        @Override
        public void onError(Platform platform, int i, Throwable throwable) {
            Log.e("PlatformActionListenEr", throwable.getMessage());
        }

        @Override
        public void onCancel(Platform platform, int i) {
            Log.e("PlatformActionListenCA", "cancel");
        }
    }


    /*
    * 第三方登录
    * */
    private void thirdParLogin(String userId, String userName, String userHeadImg) {

        final ProgressDialog p3Dialog = new ProgressDialog(LoginActivity.this);

        p3Dialog.setMessage("正在登录...");
        p3Dialog.setCanceledOnTouchOutside(false);
        p3Dialog.setCancelable(true);
        p3Dialog.show();


        RequestParams params = new RequestParams(Constants.THIRD_PAR_LOGIN);
        params.addBodyParameter("openid", userId);
        params.addBodyParameter("username", userName);
        params.addBodyParameter("headimgurl", userHeadImg);
        params.addBodyParameter("deviceid", "Android");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getInt("status") == 0) {
                        JSONObject info = object.getJSONObject("info");
                        PrefUtils.setString(LoginActivity.this, "nick", info.getString("nick"));
                        PrefUtils.setString(LoginActivity.this, "headimg", info.getString("img"));
                        PrefUtils.setString(LoginActivity.this, "userid", info.getString("userid"));
                        PrefUtils.setString(LoginActivity.this, "mobile", info.getString("mobile"));
                        PrefUtils.setBoolean(LoginActivity.this, "isLogined", true);
                        PrefUtils.setBoolean(LoginActivity.this, "check_my", true);
                        boolean isCard;
                        if (TextUtils.equals("0", info.getString("iscard"))) {
                            isCard = false;
                        } else {
                            isCard = true;
                        }
                        PrefUtils.setBoolean(LoginActivity.this, "isCard", isCard);
                        PrefUtils.setBoolean(LoginActivity.this, "myfragmentreset", true);
                        IngOrderPager.refresh = true;
                        Toast.makeText(LoginActivity.this, "欢迎来到候鸟旅居网", Toast.LENGTH_SHORT).show();
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
                if (p3Dialog != null) {
                    if (p3Dialog.isShowing()) {
                        p3Dialog.dismiss();
                    }
                }
            }
        });
    }


    private void login() {

        loginphone = etphone.getText().toString().trim();
        loginpsw = etpsw.getText().toString().trim();
        boolean isTel = true;
        //判断输入的用户名是否是电话号码
        if (etphone.getText().toString().length() == 11) {
            for (int i = 0; i < etphone.getText().toString().length(); i++) {
                char c = etphone.getText().toString().charAt(i);
                if (!Character.isDigit(c)) {
                    isTel = false;
                    break;//只要有一位不符合要求退出循环
                }
            }
        } else {
            isTel = false;
        }
        if (TextUtils.isEmpty(etphone.getText())) {
            Toast.makeText(LoginActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else if (!isTel) {
            Toast.makeText(LoginActivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etpsw.getText())) {
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else {

            final ProgressDialog pDialog = new ProgressDialog(this);

            pDialog.setMessage("正在登录...");
            pDialog.setCanceledOnTouchOutside(false);
            pDialog.setCancelable(true);
            pDialog.show();

            RequestParams params = new RequestParams(Constants.LOGIN_URL);
            params.addBodyParameter("phone", loginphone);
            params.addBodyParameter("pass", loginpsw);
            params.addBodyParameter("deviceid", "Android");
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println(result);
                    try {
                        JSONObject json = new JSONObject(result);
                        System.out.println(result);
                        int status = json.getInt("status");
                        if (status == 1) {
                            Toast.makeText(LoginActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        } else if (status == 0) {
                            String rand = json.getJSONObject("info").getString("rand");
                            String userid = json.getJSONObject("info").getString("userid");
                            String nick = json.getJSONObject("info").getString("nick");
                            String img = json.getJSONObject("info").getString("img");
                            String mobile = json.getJSONObject("info").getString("mobile");
                            String isCardStr = json.getJSONObject("info").getString("iscard");
                            boolean isCard;
                            if ("0".equals(isCardStr)) {
                                isCard = false;
                            } else {
                                isCard = true;
                            }
                            PrefUtils.setString(LoginActivity.this, "nick", nick);
                            PrefUtils.setString(LoginActivity.this, "headimg", img);
                            PrefUtils.setString(LoginActivity.this, "userid", userid);
                            PrefUtils.setString(LoginActivity.this, "rand", rand);
                            PrefUtils.setString(LoginActivity.this, "mobile", mobile);
                            PrefUtils.setBoolean(LoginActivity.this, "isLogined", true);
                            PrefUtils.setBoolean(LoginActivity.this, "check_my", true);
                            PrefUtils.setBoolean(LoginActivity.this, "isCard", isCard);
                            PrefUtils.setBoolean(LoginActivity.this, "myfragmentreset", true);
                            IngOrderPager.refresh = true;
                            Toast.makeText(LoginActivity.this, "欢迎来到候鸟旅居网", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    System.out.println(ex.getMessage());
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {
                    if (pDialog != null) {
                        if (pDialog.isShowing()) {
                            pDialog.dismiss();
                        }
                    }
                }
            });
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(this);
    }

}
