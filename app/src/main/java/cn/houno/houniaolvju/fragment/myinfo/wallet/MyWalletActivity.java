package cn.houno.houniaolvju.fragment.myinfo.wallet;

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
import cn.houno.houniaolvju.fragment.myinfo.card.CardAndWalletRechargeActivity;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：我的钱包
 * 创建人：qzc
 * 创建时间：2016/12/7 15:38
 * 修改人：qzc
 * 修改时间：2016/12/7 15:38
 * 修改备注：
 */
public class MyWalletActivity extends Activity implements View.OnClickListener {

    private ImageView ivBack;

    private ImageView ivHeadImg;
    private TextView tvNick;

    private TextView tvBackamount;
    private TextView tvBalance;

    private TextView btnRecharge;
    private TextView btnWithdrawal;

    private TextView tvRechargeRecode;
    private TextView tvWithdrawalRecode;
    private TextView tvBackRecode;

    private String userId;
    ImageOptions options;
    private String balanceStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_my_wallet);
        StatusBarUtils.setWindowStatusBarColor(MyWalletActivity.this, R.color.app_theme_green);
        initFindView();
        initData();
        initEvent();
    }

    private void initFindView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivHeadImg = (ImageView) findViewById(R.id.iv_head_img);
        tvNick = (TextView) findViewById(R.id.tv_nick);
        tvBackamount = (TextView) findViewById(R.id.tv_backamount);
        tvBalance = (TextView) findViewById(R.id.tv_balance);
        btnRecharge = (TextView) findViewById(R.id.btn_recharge);
        btnWithdrawal = (TextView) findViewById(R.id.btn_withdrawal);

        tvRechargeRecode = (TextView) findViewById(R.id.tv_recharge_recode);
        tvWithdrawalRecode = (TextView) findViewById(R.id.tv_withdrawal_recode);
        tvBackRecode = (TextView) findViewById(R.id.tv_back_record);
    }

    private void initEvent() {
        ivBack.setOnClickListener(this);
        btnRecharge.setOnClickListener(this);
        btnWithdrawal.setOnClickListener(this);
        tvRechargeRecode.setOnClickListener(this);
        tvWithdrawalRecode.setOnClickListener(this);
        tvBackRecode.setOnClickListener(this);
    }

    private void initData() {
        userId = PrefUtils.getString(MyWalletActivity.this, "userid", "");
        options = new ImageOptions.Builder()
                .setCircular(true)
                .build();
        String imgUrl = PrefUtils.getString(MyWalletActivity.this, "headimg", "");
        x.image().bind(ivHeadImg, imgUrl, options);
        tvNick.setText(PrefUtils.getString(MyWalletActivity.this, "nick", ""));

        getBurseIndex();
    }

    private void getBurseIndex() {
        RequestParams params = new RequestParams(Constants.WALLET_INDEX);
        params.addBodyParameter("userid", userId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        JSONObject data = obj.getJSONObject("data");
                        tvNick.setText(data.getString("username"));
                        tvBackamount.setText(data.getString("backamount"));
                        balanceStr = data.getString("balance");
                        tvBalance.setText(data.getString("balance"));
                    } else {
                        Log.e("getBurseIndex-ERROR", obj.getString("msg"));
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_recharge: //充值
                intent.setClass(MyWalletActivity.this,CardAndWalletRechargeActivity.class);
                intent.putExtra("from","wallet");
                intent.putExtra("balance", balanceStr);
                startActivity(intent);
                break;
            case R.id.btn_withdrawal:
                intent.setClass(MyWalletActivity.this,WalletWithDrawActivity.class);
                startActivityForResult(intent,101);
                break;
            case R.id.tv_recharge_recode:   //充值列表
                intent.setClass(MyWalletActivity.this,WalletRecodeActivity.class);
                intent.putExtra("from","recharge");
                startActivity(intent);
                break;
            case R.id.tv_withdrawal_recode: //提现列表
                intent.setClass(MyWalletActivity.this,WalletRecodeActivity.class);
                intent.putExtra("from","drawals");
                startActivity(intent);
                break;
            case R.id.tv_back_record:   //返现列表
                intent.setClass(MyWalletActivity.this,WalletRecodeActivity.class);
                intent.putExtra("from","back");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==101&&resultCode==201){
            getBurseIndex();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PrefUtils.getBoolean(MyWalletActivity.this, "paystatus", true)) {
            getBurseIndex();
            PrefUtils.setBoolean(MyWalletActivity.this, "paystatus", false);
        }
    }
}
