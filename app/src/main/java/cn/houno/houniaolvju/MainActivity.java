package cn.houno.houniaolvju;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zxing.activity.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.FileUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.activity.AboutActivity;
import cn.houno.houniaolvju.activity.ScanPromptActivity;
import cn.houno.houniaolvju.fragment.HomeFragment;
import cn.houno.houniaolvju.fragment.MyFragment;
import cn.houno.houniaolvju.fragment.OrderFragment;
import cn.houno.houniaolvju.fragment.SurroundingFragment;
import cn.houno.houniaolvju.fragment.myinfo.LoginActivity;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.CustomDialog;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.fl_content)
    FrameLayout flContent;
    private RadioGroup radioGroup;
    private RadioButton rbHome;
    private RadioButton rbSurrounding;
    private RadioButton rbOrder;
    private RadioButton rbMy;

    private HomeFragment homeFragment;
    private SurroundingFragment surroundingFragment;
    private OrderFragment orderFragment;
    private MyFragment myFragment;

    private SlidingMenu mLeftMenu;

    private static final int OPEN_CAMERA_SCAN = 101;

    private static final int TO_LOGIN = 103;

    int rbPosition = 0; //上次点击的底部菜单
    private boolean isLogined;

    private final static String DISK_CACHE_DIR_NAME = "xUtils_img";
    private final static String DISK_THUMB_CACHE_DIR_NAME = "xUtils_img_thumb";

    private PackageInfo packageInfo;
    private String version;
    private String serverVersion;
    private String downloadpath;
    private int mode = 1;
    public static final int SHOW_UPDATE_DIALOG = 1;
    public static final int ERROR = 2;
    private ProgressDialog pd;
    private final int OPEN_CAMERA_PERMISSION = 11;    //打开相机权限请求码

    /**
     * 新版本apk的下载路径
     */
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_UPDATE_DIALOG:// 显示应用更新对话框
                    String desc = (String) msg.obj;
                    showUpdateDialog(desc);
                    break;
                case ERROR:
                    //Toast.makeText(SplashActivity.this, "错误码-" + msg.obj, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {       //防止fragment重影
            savedInstanceState.putParcelable("android:support:fragments", null);
        }
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(MainActivity.this, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
        select(0);
        versionUpdate();
        onViewClicked( homeFragment);
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbSurrounding = (RadioButton) findViewById(R.id.rb_call);
        rbOrder = (RadioButton) findViewById(R.id.rb_order);
        rbMy = (RadioButton) findViewById(R.id.rb_my);
        initSlidingMenu();
    }

    private void initSlidingMenu() {
        // configure the SlidingMenu
        mLeftMenu = new SlidingMenu(this);
        mLeftMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        mLeftMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mLeftMenu.setShadowWidthRes(R.dimen.shadow_width);

//        menu.setShadowDrawable(R.drawable.shadow);

        // 设置滑动菜单视图的宽度
        //mLeftMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        mLeftMenu.setFadeDegree(0.35f);
        mLeftMenu.setBehindWidthRes(R.dimen.behind_width);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        mLeftMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        mLeftMenu.setMenu(R.layout.layout_left_menu);

        mLeftMenu.findViewById(R.id.ll_about).setOnClickListener(new leftMenuClick());
        mLeftMenu.findViewById(R.id.ll_share).setOnClickListener(new leftMenuClick());
        mLeftMenu.findViewById(R.id.ll_scan).setOnClickListener(new leftMenuClick());
        mLeftMenu.findViewById(R.id.ll_clear).setOnClickListener(new leftMenuClick());
        mLeftMenu.findViewById(R.id.ll_update).setOnClickListener(new leftMenuClick());
       // mLeftMenu.addIgnoredView(flContent);

    }

    private void initEvent() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void initData() {

    }

    public void onViewClicked(Fragment fragment) {
        // TODO Auto-generated method stub
      homeFragment = (HomeFragment) fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment).commit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              getLeftMenu().showContent();
            }
        },100);

    }
    private void select(int i) {
        FragmentManager fm = getSupportFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务

        hideFragment(ft);   //先隐藏 Fragment
        isLogined = PrefUtils.getBoolean(MainActivity.this, "isLogined", false);
        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.fl_content, homeFragment);
                } else {
                    ft.show(homeFragment);
                    rbPosition = 0;
                }
                break;
            case 1:
                if (isLogined) {
                    if (orderFragment == null) {
                        orderFragment = new OrderFragment();
                        ft.add(R.id.fl_content, orderFragment);
                    } else {
                        ft.show(orderFragment);
                        rbPosition = 1;
                    }
                } else {
                    Intent intent = new Intent();
                    rbHome.setChecked(true);
                    intent.setClass(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                break;
            case 2:
                if (isLogined) {
                    if (myFragment == null) {
                        myFragment = new MyFragment();
                        ft.add(R.id.fl_content, myFragment);
                    } else {
                        ft.show(myFragment);
                        rbPosition = 2;
                    }
                } else {
                    Intent intent = new Intent();
                    rbHome.setChecked(true);
                    intent.setClass(MainActivity.this, LoginActivity.class);
                    startActivityForResult(intent, TO_LOGIN);
                    return;
                }
                break;
        }
        ft.commit();   //提交事务
    }


    //隐藏所有Fragment
    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (surroundingFragment != null) {
            fragmentTransaction.hide(surroundingFragment);
        }
        if (orderFragment != null) {
            fragmentTransaction.hide(orderFragment);
        }
        if (myFragment != null) {
            fragmentTransaction.hide(myFragment);
        }
    }


    /*
  * 拨打电话预订
  * */
    private void showDialogCallTheNumber() {
        CustomDialog.Builder callDialog = new CustomDialog.Builder(this);
        callDialog.setMessage("是否拨打客服电话 0371-55328666");
        callDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4000301679"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        callDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        callDialog.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case OPEN_CAMERA_SCAN:
                if (data != null) {
                    String result = data.getExtras().getString("result");
                    Intent intent = new Intent();
                    intent.putExtra("result", result);
                    intent.setClass(MainActivity.this, ScanPromptActivity.class);
                    startActivity(intent);
                }
                break;
            default:
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case OPEN_CAMERA_PERMISSION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    openCameraScan();
//                } else {
//                    Log.i("OPEN_CAMERA_PERMISSION", "拒绝打开相机授权");
//                }
//                break;
//        }
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                mLeftMenu.toggle(true);
                break;
            case KeyEvent.KEYCODE_BACK:
                if (mLeftMenu.isMenuShowing()) {
                    mLeftMenu.toggle();
                } else {
                    exit();
                    return false;
                }
                break;
            default:
                break;
        }
        return false;
    }

    private long exitTime = 0;

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    public SlidingMenu getLeftMenu() {
        return mLeftMenu;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                select(0);
                break;
            case R.id.rb_call:
                showDialogCallTheNumber();
                switch (rbPosition) {
                    case 0://主页
                        rbHome.setChecked(true);
                        break;
                    case 1://订单
                        rbOrder.setChecked(true);
                        break;
                    case 2://我的
                        rbMy.setChecked(true);
                        break;
                }
                break;
            case R.id.rb_order:
                select(1);
                break;
            case R.id.rb_my:
                select(2);
                break;
        }
    }

    private class leftMenuClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_about:
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                    break;
                case R.id.ll_share:
                    shareThisApp();
                    break;
                case R.id.ll_scan:
//                    if (ContextCompat.checkSelfPermission(
//                            MainActivity.this, Manifest.permission.CAMERA)
//                            != PackageManager.PERMISSION_GRANTED) {
//                        ActivityCompat.requestPermissions(MainActivity.this
//                                , new String[]{Manifest.permission.CAMERA}, OPEN_CAMERA_PERMISSION);
//                    } else {
                    openCameraScan();
                    //       }
                    break;
                case R.id.ll_clear:
                    showClearCacheDialog();
                    break;
                case R.id.ll_update:
                    mode = 2;
                    versionUpdate();
                    break;
            }
        }
    }

    /*
  * 分享
  * */
    private void shareThisApp() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("候鸟旅居网");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://wx.houno.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("全球候鸟人之家");
        oks.setImageUrl("http://a1.qpic.cn/psb?/V12UKhhx2HXvEt/l0em0ffRGYVyoMpHg.uNSpu2QfLKuutgaoqpYm8EqfQ!/b/dLEAAAAAAAAA&bo=gAKAAgAAAAADByI!&rf=viewer_4");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://wx.houno.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本setComment");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://wx.houno.cn");
        // 启动分享GUI
        oks.show(this);
    }

    /*
 * 打开相机扫描二维码
 * */
    private void openCameraScan() {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, OPEN_CAMERA_SCAN);
    }

    /*
   * 清除缓存对话框
   * */
    private void showClearCacheDialog() {
        File cacheDir = FileUtil.getCacheDir(DISK_CACHE_DIR_NAME);
        File thumbCacheDir = FileUtil.getCacheDir(DISK_THUMB_CACHE_DIR_NAME);
        long cacheSize = (FileUtil.getFileOrDirSize(cacheDir) + FileUtil.getFileOrDirSize(thumbCacheDir)) / 1024 / 1024;

        CustomDialog.Builder clearDialog = new CustomDialog.Builder(this);
        clearDialog.setMessage("发现 " + cacheSize + "M 缓存，是否需要清理");
        clearDialog.setPositiveButton("清理", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //  x.image().clearMemCache();    //清除内存
                x.image().clearCacheFiles();    //清除存储卡的缓存文件
                Toast.makeText(MainActivity.this, "清理成功", Toast.LENGTH_SHORT).show();
            }
        });

        clearDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        clearDialog.create().show();

    }


    //版本更新
    private void versionUpdate() {

        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            //本地版本号
            version = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // 检查sp里面的状态,看自动更新是否开启
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        boolean update = sp.getBoolean("update", true);
        if (update) {
            // 开启子线程获取服务器的版本信息
            CheckVersionTask checkVersionTask = new CheckVersionTask();
            new Thread(checkVersionTask).start();
        }
    }

    /**
     * 获取服务器配置的最新版本号
     */
    private class CheckVersionTask implements Runnable {
        @Override
        public void run() {
            final Message msg = Message.obtain();
//            final long startTime = System.currentTimeMillis();
            RequestParams params = new RequestParams(Constants.VERSION_URL);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    System.out.println("版本更新" + result);
                    try {
                        JSONObject json = new JSONObject(result);
                        serverVersion = json.getJSONObject("data").getString("version");
                        downloadpath = json.getJSONObject("data").getString("url");
                        if (version.equals(serverVersion)) {
                            if (mode != 1) {
                                Toast.makeText(MainActivity.this, "当前为最新版本", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            msg.what = SHOW_UPDATE_DIALOG;

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        msg.what = ERROR;
                        msg.obj = "code:409";
                    } finally {
                        // 计算代码走到这花费的时间
//                        long endTime = System.currentTimeMillis();
//                        long dTime = endTime - startTime;
//                        if (dTime > 2000) {
//
//                        } else {
//                            SystemClock.sleep(2000 - dTime);
//                        }
                        handler.sendMessage(msg);
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    if (mode != 1) {
                        Toast.makeText(MainActivity.this, "请求更新失败", Toast.LENGTH_SHORT).show();
                    }
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

    /**
     * 显示自动更新的对话框
     *
     * @param desc 描述
     */
    protected void showUpdateDialog(String desc) {
        CustomDialog.Builder upDataDialog = new CustomDialog.Builder(this);
        upDataDialog.setMessage("发现新版本，是否现在更新");
        upDataDialog.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int which) {
                dialog.dismiss();
                pd = new ProgressDialog(MainActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            pd.dismiss();
                        }
                        return false;
                    }
                });
                pd.setCanceledOnTouchOutside(false);
                pd.show();
                String sdDir = Environment.getExternalStorageDirectory().getPath() + File.separator;
                String savePath = sdDir + ".apk";
                RequestParams paramsurl = new RequestParams(downloadpath);
                paramsurl.setSaveFilePath(savePath);

                if (Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    x.http().get(paramsurl, new Callback.ProgressCallback<File>() {
                        @Override
                        public void onWaiting() {

                        }

                        @Override
                        public void onStarted() {

                        }

                        @Override
                        public void onLoading(long total, long current, boolean isDownloading) {
                            pd.setMessage("正在下载中...");
                            pd.setMax((int) total / 1024);
                            pd.setProgress((int) current / 1024);

                        }

                        @Override
                        public void onSuccess(File result) {
                            Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            // 覆盖安装apk文件
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            intent.addCategory("android.intent.category.DEFAULT");
                            intent.setDataAndType(
                                    Uri.fromFile(result),
                                    "application/vnd.android.package-archive");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivityForResult(intent, 0);// 如果用户取消安装的话,
                            // 会返回结果,回调方法onActivityResult
                            Process.killProcess(Process.myPid());
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            Toast.makeText(MainActivity.this, "下载失败，请检查网络和SD卡", Toast.LENGTH_SHORT).show();
                            pd.dismiss();
                            dialog.dismiss();
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "sd卡不可用,无法自动更新", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        upDataDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        upDataDialog.create().show();
    }


    public RadioButton getRadioButton() {
        return rbMy;
    }

    public RadioButton getRBtn() {
        return rbHome;
    }

    public RadioButton getRbOrder() {
        return rbOrder;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //修改密码成功返回切换到我的
        if (PrefUtils.getBoolean(MainActivity.this, "check_my", false)) {
            rbHome.setChecked(true);
            rbMy.setChecked(true);
            PrefUtils.setBoolean(MainActivity.this, "check_my", false);
        }
        //订单支付成功跳转订单界面
        if (PrefUtils.getBoolean(MainActivity.this, "paystatus", false)) {
            rbOrder.setChecked(true);
            PrefUtils.setBoolean(MainActivity.this, "paystatus", false);
        }
    }
}
