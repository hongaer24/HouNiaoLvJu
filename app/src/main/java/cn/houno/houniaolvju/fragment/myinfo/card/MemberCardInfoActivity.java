package cn.houno.houniaolvju.fragment.myinfo.card;

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
 * 类描述： 会员卡信息
 * 创建人：qzc
 * 创建时间：2016/11/25 16:29
 * 修改人：qzc
 * 修改时间：2016/11/25 16:29
 * 修改备注：
 */
public class MemberCardInfoActivity extends Activity {

    private ImageView ivBack;

    private TextView tvCardNo;
    private TextView tvUserName;
    private TextView tvModifyPwd;
    private TextView tvPhone;
    private TextView tvTelphone;
    private TextView tvCardId;
    private TextView tvSex;
    private TextView tvQQ;
    private TextView tvWX;
    private TextView tvAddress;
    private TextView tvSxDate;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_member_card_info);
        StatusBarUtils.setWindowStatusBarColor(MemberCardInfoActivity.this, R.color.app_theme_green);
        initFindViewById();
        initData();
        initEvent();
    }

    private void initFindViewById() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvCardNo = (TextView) findViewById(R.id.tv_cardno);
        tvUserName = (TextView) findViewById(R.id.tv_card_username);
        tvModifyPwd = (TextView) findViewById(R.id.tv_modify_pwd);
        tvPhone = (TextView) findViewById(R.id.tv_card_phone);
        tvTelphone = (TextView) findViewById(R.id.tv_card_telphone);
        tvCardId = (TextView) findViewById(R.id.tv_card_id);
        tvSex = (TextView) findViewById(R.id.tv_card_sex);
        tvQQ = (TextView) findViewById(R.id.tv_card_qq);
        tvWX = (TextView) findViewById(R.id.tv_card_wx);
        tvAddress = (TextView) findViewById(R.id.tv_card_address);
        tvSxDate = (TextView) findViewById(R.id.tv_card_date);
    }

    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvModifyPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MemberCardInfoActivity.this, ModifyCardPassword.class));
            }
        });
    }

    private void initData() {
        intent = getIntent();
        tvCardNo.setText(getIntentString("cardno"));
        tvUserName.setText(getIntentString("carduser"));
        tvPhone.setText(getIntentString("cardphone"));
        tvTelphone.setText(getIntentString("cardtelphone"));
        tvCardId.setText(getIntentString("cardid"));
        tvSex.setText(getIntentString("cardsex"));
        tvQQ.setText(getIntentString("cardqq"));
        tvWX.setText(getIntentString("cardwx"));
        tvAddress.setText(getIntentString("cardaddress"));
        tvSxDate.setText(getIntentString("cardsxdate"));
    }

    private String getIntentString(String key) {
        if (intent.getStringExtra(key) == null
                || "null".equals(intent.getStringExtra(key))) {
            return "";
        } else {
            return intent.getStringExtra(key);
        }
    }
}
