package cn.houno.houniaolvju.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.flight.FlightNewActivity;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 侧边栏关于界面
 * Created by qzc on 2016/9/27.
 */
public class AboutActivity extends Activity {

    private String version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtils.setWindowStatusBarColor(AboutActivity.this, R.color.app_theme_green);
        setContentView(R.layout.activity_about_leftmenu);
        ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
        TextView tvVersion = (TextView) findViewById(R.id.tv_version);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //版本跟新
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            //本地版本号
            version = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tvVersion.setText("版本号 " + version);
    }
}
