package cn.houno.houniaolvju.fragment.myinfo.wallet;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：提现详情
 * 创建人：qzc
 * 创建时间：2016/12/10 10:27
 * 修改人：qzc
 * 修改时间：2016/12/10 10:27
 * 修改备注：
 */
public class WalletDrawalsDetailActivity extends Activity {

    private ImageView ivBack;

    private TextView tvDrawMoney; //提现金额
    private TextView tvAmountType;   //提现类型
    private TextView tvDrawType;   //提现方式
    private TextView tvBalance;   //剩余零钱
    private TextView tvDrawTime;   //返现日期

    private String mDrawMoney;
    private String mAmountType;
    private String mDrawType;
    private String mBalance;
    private String mDrawTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_drawals_detail);
        StatusBarUtils.setWindowStatusBarColor(WalletDrawalsDetailActivity.this, R.color.app_theme_green);
        initFindView();
        initData();
        initEvent();
    }

    private void initFindView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);

        tvDrawMoney = (TextView) findViewById(R.id.tv_money_draw);
        tvAmountType = (TextView) findViewById(R.id.tv_amout_type);
        tvDrawType = (TextView) findViewById(R.id.tv_draw_type);
        tvBalance = (TextView) findViewById(R.id.tv_money_last);
        tvDrawTime = (TextView) findViewById(R.id.tv_draw_time);
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        mDrawMoney = bundle.getString("amount");
        mAmountType = bundle.getString("type");
        mDrawType = bundle.getString("drawtype");
        mBalance = bundle.getString("lastmoney");
        mDrawTime = bundle.getString("time");

        tvDrawMoney.setText("-" + mDrawMoney);
        tvAmountType.setText(mAmountType);
        tvDrawType.setText(mDrawType);
        tvBalance.setText(mBalance);
        tvDrawTime.setText(mDrawTime);
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
