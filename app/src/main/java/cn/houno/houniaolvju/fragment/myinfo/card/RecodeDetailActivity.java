package cn.houno.houniaolvju.fragment.myinfo.card;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：充值/消费记录详情
 * 创建人：qzc
 * 创建时间：2016/11/25 14:09
 * 修改人：qzc
 * 修改时间：2016/11/25 14:09
 * 修改备注：
 */
public class RecodeDetailActivity extends Activity {

    private String from;
    private String tradeno;
    private String time;
    private String type;
    private String moneylast;
    private String moneychange;

    private ImageView ivBack;
    private TextView tvTitle;

    private TextView tvMoneyTxt;
    private TextView tvMoneyChange; //消费或者充值
    private TextView tvOrderNo;
    private TextView tvTime;
    private TextView tvPayTxt;
    private TextView tvPayType;
    private TextView tvMoneyLast;   //余额

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_recode_detail);
        StatusBarUtils.setWindowStatusBarColor(RecodeDetailActivity.this, R.color.app_theme_green);
        initFindViewById();
        initData();
        initEvent();
    }

    private void initFindViewById() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_topbar_title);

        tvMoneyTxt = (TextView) findViewById(R.id.tv_money_txt);
        tvMoneyChange = (TextView) findViewById(R.id.tv_money_change);
        tvOrderNo = (TextView) findViewById(R.id.tv_orderno);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvPayTxt = (TextView) findViewById(R.id.tv_pay_txt);
        tvPayType = (TextView) findViewById(R.id.tv_paytype);
        tvMoneyLast = (TextView) findViewById(R.id.tv_money_last);
    }

    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        from = bundle.getString("from");
        tradeno = bundle.getString("tradeno");
        time = bundle.getString("time");
        type = bundle.getString("type");
        moneylast = bundle.getString("moneylast");
        moneychange = bundle.getString("moneychange");
        if ("recharge".equals(from)) {
            tvTitle.setText("充值详情");
            tvMoneyTxt.setText("入账金额");
            tvPayTxt.setText("充值方式");
            tvTime.setText(DateUtil.timeStamp2Date(time, null));
            tvMoneyChange.setText("+" + Double.valueOf(moneychange) + "");
        } else if ("consume".equals(from)) {
            tvTitle.setText("消费详情");
            tvMoneyTxt.setText("消费金额");
            tvPayTxt.setText("消费项目");
            tvTime.setText(time);
            tvMoneyChange.setTextColor(Color.parseColor("#ff0000"));
            tvMoneyChange.setText("-" + Double.valueOf(moneychange) + "");
        }

        tvOrderNo.setText(tradeno);

        tvPayType.setText(type);
        tvMoneyLast.setText(Double.valueOf(moneylast) + "");


    }
}
