package cn.houno.houniaolvju.fragment.myinfo.card;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.fragment.myinfo.wallet.MyWalletActivity;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.pay.alipay.PayResult;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：旅居卡/钱包 充值页面
 * 创建人：qzc
 * 创建时间：2016/11/18 10:52
 * 修改人：qzc
 * 修改时间：2016/11/18 10:52
 * 修改备注：
 */
public class CardAndWalletRechargeActivity extends Activity implements OnItemClickListener {

    private String userId;

    private int RECHARGE_TYPE = 0;   //充值类型
    private static final int ALI_RECHARGE = 201;   //支付宝
    private static final int WX_RECHARGE = 202;    //微信
    private static final int UNION_RECHARGE = 203;    //银联

    private static final String PARTNER = "2088321007217320";
    private static final String SERVICE = "mobile.securitypay.pay";

    private ImageView ivBack;
    private TextView tvBalance;
    private EditText etMoney;
    private TextView tvRechasege;

    private AlertView mAlertView;
    private String orderno;
    private String from;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_card_recharge);
        StatusBarUtils.setWindowStatusBarColor(CardAndWalletRechargeActivity.this, R.color.app_theme_green);
        initFindViewById();
        initData();
        initEvent();
    }


    private void initFindViewById() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvBalance = (TextView) findViewById(R.id.tv_balance);
        etMoney = (EditText) findViewById(R.id.et_money);
        tvRechasege = (TextView) findViewById(R.id.tv_recharge);
    }

    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvRechasege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertView.show();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        if ("card".equals(from)) {
            type = "card";
        } else if ("wallet".equals(from)) {
            type = "acct";
        }
        String balanceStr = intent.getStringExtra("balance");
        tvBalance.setText(balanceStr);
        userId = PrefUtils.getString(CardAndWalletRechargeActivity.this, "userid", "");
        //初始化充值方式
        mAlertView = new AlertView("请选择充值方式", null, "取消", null,
                new String[]{"支付宝充值", "微信充值", "银联充值"},
                CardAndWalletRechargeActivity.this, AlertView.Style.ActionSheet, this);
        mAlertView.setCancelable(true);
    }


    @Override
    public void onItemClick(Object o, int position) {
        switch (position) {
            case 0:
                //支付宝充值
                RECHARGE_TYPE = ALI_RECHARGE;
                postParamsToServer(Constants.ALI_RECHARGE);
                break;
            case 1:
                //微信充值
                RECHARGE_TYPE = WX_RECHARGE;
                postParamsToServer(Constants.WX_RECHARGE);
                break;
            case 2:
                //银联充值
                RECHARGE_TYPE = UNION_RECHARGE;
                postParamsToServer(Constants.UNION_RECHARGE);
                break;
            case -1:
                break;
        }
    }

    private void postParamsToServer(String payUrl) {
        Map<String, String> params = new HashMap<>();
        params.put("partner", PARTNER);
        params.put("service", SERVICE);
        params.put("userid", userId);
        params.put("type", type);
        params.put("money", etMoney.getText().toString().trim());

        OkHttpClientManager.postAsync(payUrl, params, null, mHandler, R.id.doSucceed, R.id.doFail);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //提交订单号到服务器返回标准的支付参数和数据
            String succeedResult = msg.obj.toString();
            System.out.println(succeedResult);
            switch (msg.what) {
                case R.id.doSucceed:
                    try {
                        JSONObject obj = new JSONObject(succeedResult);
                        Log.e("Recharge", succeedResult);
                        int status = obj.getInt("status");
                        if (status == 0) {
//                            Toast.makeText(CardAndWalletRechargeActivity.this,"提交结果:"+ obj.getString("msg"), Toast.LENGTH_SHORT).show();
                            orderno = obj.getString("orderno");
                            if (RECHARGE_TYPE == ALI_RECHARGE) {
                                aliRecharge(obj.getString("data"));
                            } else if (RECHARGE_TYPE == WX_RECHARGE) {
                                wxRecharge(obj);
                            } else if (RECHARGE_TYPE == UNION_RECHARGE) {
                                unionRecharge(obj.getString("tn"));
                            }
                        } else {
                            Toast.makeText(CardAndWalletRechargeActivity.this, "提交结果:" + obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.e("PAY_GET", "异常：" + e.getMessage());
                        Toast.makeText(CardAndWalletRechargeActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.doFail:
                    Toast.makeText(CardAndWalletRechargeActivity.this, "提交结果:" + "请求失败", Toast.LENGTH_SHORT).show();
            }
        }
    };


    private static final int SDK_RECHARGE_FLAG = 1;

    //支付宝充值
    private void aliRecharge(String data) {
        final String orderInfo = data;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(CardAndWalletRechargeActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_RECHARGE_FLAG;
                msg.obj = result;
                aLiHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler aLiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_RECHARGE_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        // Toast.makeText(OrderDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        checkOrderIsPayed();
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        Toast.makeText(CardAndWalletRechargeActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(CardAndWalletRechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    //微信充值
    private void wxRecharge(JSONObject obj) {

        try {
            String appid = obj.getString("appid");
            IWXAPI api = WXAPIFactory.createWXAPI(this, appid);
            api.registerApp(appid);
            //请求参数
            PayReq payRequest = new PayReq();
            payRequest.appId = appid;
            payRequest.partnerId = obj.getString("partnerid");
            payRequest.prepayId = obj.getString("prepayid");
            payRequest.packageValue = obj.getString("package");
            payRequest.nonceStr = obj.getString("noncestr");
            payRequest.timeStamp = obj.getString("timestamp");
            payRequest.sign = obj.getString("sign");
            payRequest.extData = "app data";
            //发起请求
            api.sendReq(payRequest);
        } catch (JSONException e) {
            Log.e("PAY_GET", "异常：" + e.getMessage());
            Toast.makeText(CardAndWalletRechargeActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //银联充值

    private String unionMode = "00";

    private void unionRecharge(String tn) {
        UPPayAssistEx.startPayByJAR(this, PayActivity.class, null, null, tn, unionMode);
    }

    /*
   * 支付成功后请求服务器校验订单是否真的支付成功
   * */
    private void checkOrderIsPayed() {
        Map<String, String> params = new HashMap<>();
        params.put("userid", userId);
        params.put("orderno", orderno);
        params.put("type", type);
        Log.e("CardAndBurseRecharge", "userid=" + userId + ",orderno=" + orderno);
        OkHttpClientManager.postAsync(Constants.GET_RCHG_STATUS, params, null, checkHandler, R.id.doSucceed, R.id.doFail);

    }

    private Handler checkHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case R.id.doSucceed:
                    String succeedResult = msg.obj.toString();
                    Log.e("CardAndBurseRecharge", "check-==" + succeedResult);
                    System.out.println(succeedResult);
                    try {
                        JSONObject obj = new JSONObject(succeedResult);
                        int status = obj.getInt("status");
                        if (status == 0) {
                            if ("0".equals(obj.getString("paystatus"))) {
                                Toast.makeText(CardAndWalletRechargeActivity.this, "充值异常", Toast.LENGTH_SHORT).show();
                            } else if ("1".equals(obj.getString("paystatus"))) {
                                Toast.makeText(CardAndWalletRechargeActivity.this, "充值成功", Toast.LENGTH_SHORT).show();
                                if ("card".equals(from)) {
                                    //返回会员页面
                                    PrefUtils.setBoolean(CardAndWalletRechargeActivity.this, "paystatus", true);
                                    Intent intent = new Intent(CardAndWalletRechargeActivity.this, MemberCardActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                } else if ("wallet".equals(from)) {
                                    //返回钱包首页
                                    PrefUtils.setBoolean(CardAndWalletRechargeActivity.this, "paystatus", true);
                                    Intent intent = new Intent(CardAndWalletRechargeActivity.this, MyWalletActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        } else {
                            Toast.makeText(CardAndWalletRechargeActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.doFail:
                    Toast.makeText(CardAndWalletRechargeActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        //银联支付完成回调

        Bundle b = data.getExtras();
        String str = b.getString("pay_result");
        if ("success".equalsIgnoreCase(str)) {
            checkOrderIsPayed();
        } else if ("fail".equalsIgnoreCase(str)) {
            Toast.makeText(CardAndWalletRechargeActivity.this, "充值失败", Toast.LENGTH_SHORT).show();
        } else if ("cancel".equalsIgnoreCase(str)) {
            Toast.makeText(CardAndWalletRechargeActivity.this, "充值取消", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int wxstatus = PrefUtils.getInt(CardAndWalletRechargeActivity.this, "wxpaystatus", -3);
        if (wxstatus == 0) {
            checkOrderIsPayed();
        } else if (wxstatus == -2) {
            Toast.makeText(CardAndWalletRechargeActivity.this, "充值取消", Toast.LENGTH_SHORT).show();
        } else if (wxstatus == -1) {
            Toast.makeText(CardAndWalletRechargeActivity.this, "充值失败", Toast.LENGTH_SHORT).show();
        }
        PrefUtils.setInt(CardAndWalletRechargeActivity.this, "wxpaystatus", -3);
    }
}
