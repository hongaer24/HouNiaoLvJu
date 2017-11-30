package cn.houno.houniaolvju.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：扫描二维码后跳转的界面
 * 创建人：qzc
 * 创建时间：2016/10/4 9:54
 * 修改人：qzc
 * 修改时间：2016/10/4 9:54
 * 修改备注：
 */
public class ScanPromptActivity extends Activity {

    private ImageView ivBack;
    private TextView tvContent;
    private LinearLayout llTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_scanprompt);
        StatusBarUtils.setWindowStatusBarColor(ScanPromptActivity.this, R.color.app_theme_green);
        initFindViewById();
        initData();
        initEvent();

    }

    private void initFindViewById() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvContent = (TextView) findViewById(R.id.tv_content);
        llTv = (LinearLayout) findViewById(R.id.ll_tv);

    }

    private void initData() {

        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        if (MyText2Utils.isUrl(result)) {
            Uri uri = Uri.parse(result);
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(it);
        }
        tvContent.setText(result);
    }


    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }


}
