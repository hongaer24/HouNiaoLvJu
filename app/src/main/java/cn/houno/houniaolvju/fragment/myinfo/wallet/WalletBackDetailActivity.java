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
 * 类描述：我的钱包-返现详情
 * 创建人：qzc
 * 创建时间：2016/12/8 16:45
 * 修改人：qzc
 * 修改时间：2016/12/8 16:45
 * 修改备注：
 */
public class WalletBackDetailActivity extends Activity {

    private ImageView ivBack;

    private TextView tvBackMoney;   //返现金额
    private TextView tvUserName;   //用户名
    private TextView tvBackType;   //返现类型
    private TextView tvPreAmount;   //原有金额
    private TextView tvLastMoney;   //现有金额
    private TextView tvBackTime;   //返现日期
    private String mPreamount;
    private String mTime;
    private String mType;
    private String mAmount;
    private String mLastmoney;
    private String mUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_wallet_back_detail);
        StatusBarUtils.setWindowStatusBarColor(WalletBackDetailActivity.this, R.color.app_theme_green);
        initFindView();
        initData();
        initEvent();
    }

    private void initFindView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvBackMoney = (TextView) findViewById(R.id.tv_money_back);
        tvUserName = (TextView) findViewById(R.id.tv_username);
        tvBackType = (TextView) findViewById(R.id.tv_back_type);
        tvPreAmount = (TextView) findViewById(R.id.tv_premount);
        tvLastMoney = (TextView) findViewById(R.id.tv_money_last);
        tvBackTime = (TextView) findViewById(R.id.tv_back_time);
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        mPreamount = bundle.getString("preamount");
        mUserName = bundle.getString("username");
        mTime = bundle.getString("time");
        mType = bundle.getString("type");
        mAmount = bundle.getString("amount");
        mLastmoney = bundle.getString("lastmoney");

        tvBackMoney.setText("+" + mAmount);
        tvUserName.setText(mUserName);
        tvBackType.setText(mType);
        tvPreAmount.setText(mPreamount);
        tvLastMoney.setText(mLastmoney);
        tvBackTime.setText(mTime);
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
