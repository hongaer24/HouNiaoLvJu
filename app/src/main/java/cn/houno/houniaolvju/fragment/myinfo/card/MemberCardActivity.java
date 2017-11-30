package cn.houno.houniaolvju.fragment.myinfo.card;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：会员卡界面
 * 创建人：qzc
 * 创建时间：2016/11/14 9:43
 * 修改人：qzc
 * 修改时间：2016/11/14 9:43
 * 修改备注：
 */
public class MemberCardActivity extends Activity implements View.OnClickListener {

    private String userId;
    private ImageView ivBack;
    private ImageView ivHeadView;
    private TextView tvUserName;    //昵称
    private String userNameStr;
    private TextView tvCardNum;
    private String cardNumStr;
    private TextView tvConsumption; //累计消费
    private String consumptionStr;
    private TextView tvBalance; //可用余额
    private String balanceStr;

    private String cardPhone;
    private String cardTelphone;
    private String cardId;
    private String cardSex;
    private String cardQQ;
    private String cardWX;
    private String cardAddress;
    private String cardSxdate;

    private TextView btnRecharge;

    private TextView tvRechargeRecode;
    private TextView tvConsumeRecode;
    private TextView tvCardInfo;

    private ImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_member_card);
        StatusBarUtils.setWindowStatusBarColor(MemberCardActivity.this, R.color.app_theme_green);
        initFindViewById();
        initData();
        initEvent();
    }

    private void initFindViewById() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivHeadView = (ImageView) findViewById(R.id.civ_head_img);
        tvUserName = (TextView) findViewById(R.id.tv_nick);
        tvCardNum = (TextView) findViewById(R.id.tv_card_num);
        tvConsumption = (TextView) findViewById(R.id.tv_consumption);
        tvBalance = (TextView) findViewById(R.id.tv_balance);
        btnRecharge = (TextView) findViewById(R.id.btn_recharge);
        tvRechargeRecode = (TextView) findViewById(R.id.tv_recharge_recode);
        tvConsumeRecode = (TextView) findViewById(R.id.tv_consume_recode);
        tvCardInfo = (TextView) findViewById(R.id.tv_card_info);
    }

    private void initData() {
        userId = PrefUtils.getString(MemberCardActivity.this, "userid", "");
        options = new ImageOptions.Builder()
                .setCircular(true).setImageScaleType(ImageView.ScaleType.FIT_XY)
                .build();
        x.image().bind(ivHeadView, PrefUtils.getString(MemberCardActivity.this, "headimg", ""),options);
        getDataFromServer();
    }

    private void initEvent() {
        ivBack.setOnClickListener(this);
        btnRecharge.setOnClickListener(this);
        tvRechargeRecode.setOnClickListener(this);
        tvConsumeRecode.setOnClickListener(this);
        tvCardInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_recharge:
                intent.setClass(MemberCardActivity.this, CardAndWalletRechargeActivity.class);
                intent.putExtra("from", "card");
                intent.putExtra("balance", balanceStr);
                startActivity(intent);
                break;
            case R.id.tv_recharge_recode:
                intent.setClass(MemberCardActivity.this, CardRecodeActivity.class);
                intent.putExtra("from", "recharge");
                startActivity(intent);
                break;
            case R.id.tv_consume_recode:
                intent.setClass(MemberCardActivity.this, CardRecodeActivity.class);
                intent.putExtra("from", "consume");
                startActivity(intent);
                break;
            case R.id.tv_card_info:
                intent.setClass(MemberCardActivity.this, MemberCardInfoActivity.class);
                intent.putExtra("cardno", cardNumStr);
                intent.putExtra("carduser", userNameStr);
                intent.putExtra("cardphone", cardPhone);
                intent.putExtra("cardtelphone", cardTelphone);
                intent.putExtra("cardid", cardId);
                intent.putExtra("cardsex", cardSex);
                intent.putExtra("cardqq", cardQQ);
                intent.putExtra("cardwx", cardWX);
                intent.putExtra("cardaddress", cardAddress);
                intent.putExtra("cardsxdate", cardSxdate);
                startActivity(intent);
                break;
        }
    }


    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.MEMBER_CARD_INFO);
        params.addBodyParameter("userid", userId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result);
                    } else {
                        Log.e("MemberCardActivity", obj.getString("msg"));
                        //  Toast.makeText(AttraCionActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
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


    private void parseData(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            JSONObject data = obj.getJSONObject("data");
            userNameStr = data.getString("c_username");
            tvUserName.setText(userNameStr);
            cardNumStr = data.getString("cardno");
            tvCardNum.setText(cardNumStr);
            consumptionStr = data.getString("moneyout");
            tvConsumption.setText(formatMoney(consumptionStr));
            balanceStr = data.getString("c_moneyin");
            tvBalance.setText(formatMoney(balanceStr));

            cardPhone = data.getString("c_phone");
            cardTelphone = data.getString("c_telphone");
            cardId = data.getString("c_cardid");
            cardSex = data.getString("c_sex");
            cardQQ = data.getString("c_qq");
            cardWX = data.getString("c_wxin");
            cardAddress = data.getString("c_address");
            cardSxdate = data.getString("c_sxdate");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String formatMoney(String bStr) {
        //保留小数点后有效值,或者保留后一位
        bStr = Double.valueOf(bStr) + "";
        return bStr;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PrefUtils.getBoolean(MemberCardActivity.this, "paystatus", true)) {
            getDataFromServer();
            PrefUtils.setBoolean(MemberCardActivity.this, "paystatus", false);
        }
    }
}
