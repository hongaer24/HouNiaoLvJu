package cn.houno.houniaolvju.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.pay.alipay.PayResult;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 酒店订单详情、支付
 * Created by Administrator on 2017/1/10.
 */

public class OrderDetailActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_home)
    ImageView ivHome;
    @Bind(R.id.tv_order)
    TextView tvOrder;
    @Bind(R.id.tv_order_title_txt)
    TextView tvTitle;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.ll_pay_wx)
    LinearLayout llPayWx;
    @Bind(R.id.ll_pay_ali)
    LinearLayout llPayAli;
    @Bind(R.id.ll_pay_union)
    LinearLayout llPayUnion;
    @Bind(R.id.tv_tg)
    TextView tvTg;
    @Bind(R.id.ll_pay)
    LinearLayout llPay;
    @Bind(R.id.tv_qtxf)
    TextView tvQtxf;

    private OrderDetailActivity mActivity;

    private String orderno; //订单号
    private String title;   //订单标题
    private String price;  //价格
    private String type;    //订单类型
    private String userid = "";
    private boolean isTg;
    private String payfs;

    private int PAY_TYPE = 0;   //支付类型
    private static final int ALI_PAY = 201;   //支付宝
    private static final int WX_PAY = 202;    //微信支付
    private static final int UNION_PAY = 203;    //银联支付

    private static final String PARTNER = "2088321007217320";
    private static final String SERVICE = "mobile.securitypay.pay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_hotel_order_detail);
        mActivity = OrderDetailActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

    }

    private void initEvent() {

    }

    private void initData() {
        userid = PrefUtils.getString(OrderDetailActivity.this, "userid", "");
        Intent intent = getIntent();

        orderno = intent.getStringExtra("orderno");
        title = intent.getStringExtra("title");
        type = intent.getStringExtra("type");

        //酒店
        if (type.equals("hotel")) {
            price = intent.getIntExtra("price", 0)+"";
            //团购
            isTg = intent.getBooleanExtra("istg", false);
            if (isTg) {
                getTgDataFromServer();
            }
            payfs = intent.getStringExtra("payfs");
            System.out.println("payfs:===========>" + payfs);
            if (payfs.equals("到店付")) {
                llPay.setVisibility(View.GONE);
                tvQtxf.setVisibility(View.VISIBLE);
            }
        }else if (type.equals("ydhotel")){
            price = intent.getDoubleExtra("price", 0)+"";
        }else {
            price = intent.getIntExtra("price", 0)+"";
        }


        tvOrder.setText(orderno);
        tvTitle.setText(title);
        tvPrice.setText(price + "元");
    }


    /*
  * 团购订单说明
  * */
    private void getTgDataFromServer() {
        Map<String, String> params = new HashMap<>();

        params.put("userid", userid);
        params.put("orderno", orderno);

        OkHttpClientManager.postAsync(Constants.ORDER_TG, params, null, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String succeedResult = msg.obj.toString();
                System.out.println(succeedResult);
                switch (msg.what) {
                    case R.id.doSucceed:
                        try {
                            JSONObject obj = new JSONObject(succeedResult);
                            int status = obj.getInt("status");
                            if (status == 0) {
                                tvTg.setVisibility(View.VISIBLE);
                                setTgData(succeedResult);
                            } else {
                                Toast.makeText(OrderDetailActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.e("PAY_GET", "异常：" + e.getMessage());
                            // Toast.makeText(OrderDetailActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case R.id.doFail:
                        Toast.makeText(OrderDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }
            }
        }, R.id.doSucceed, R.id.doFail);
    }

    /*
    * 设置团购说明
    * */
    private void setTgData(String succeedResult) {
        try {
            JSONObject json = new JSONObject(succeedResult);
            int tgListSize = json.getJSONArray("tglist").length();
            String str = "预订返现规则:\n";
            for (int i = 0; i < tgListSize; i++) {
                String num = json.getJSONArray("tglist").getJSONObject(i).getString("num");
                String discount = json.getJSONArray("tglist").getJSONObject(i).getString("discount_money");
                str = str + (String.valueOf(i + 1) + "、如当日预订达到" + num + "(含)间夜，每间夜返现" + discount + "元；\n");
            }
            str = str + "您本次预订情况：\n";
            String status = json.getJSONArray("total").getJSONObject(0).getString("status");
            if ("0".equals(status)) {
                String start = json.getJSONArray("total").getJSONObject(0).getString("start");
                String roomnum = json.getJSONArray("total").getJSONObject(0).getString("roomnum");
                String ordernum = json.getJSONArray("total").getJSONObject(0).getString("ordernum");
                String tgnum = json.getJSONArray("total").getJSONObject(0).getString("tgnum");

                str = str + start + "，预订" + roomnum + "间夜，平台累计已预订" + ordernum + "间夜未达" + tgnum + "间夜，未达返现标准；";
            } else {
                String start = json.getJSONArray("total").getJSONObject(0).getString("start");
                String roomnum = json.getJSONArray("total").getJSONObject(0).getString("roomnum");
                String ordernum = json.getJSONArray("total").getJSONObject(0).getString("ordernum");
                String retprice = String.valueOf(json.getJSONArray("total").getJSONObject(0).getInt("retprice"));

                str = str + start + "，预订" + roomnum + "间夜，平台累计已预订" + ordernum + "间夜,可返现" + retprice + "元；";
            }
            tvTg.setText(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
  * 提交订单到服务器,返回标准订单数据然后进行支付
  * */
    private void postParamsToServer(String url) {
        PrefUtils.setString(OrderDetailActivity.this, "orderno", orderno);
        Map<String, String> params = new HashMap<>();
        if (Constants.A_LI_PAY.equals(url)) {
            params.put("partner", PARTNER);
            params.put("service", SERVICE);
        }
        params.put("userid", userid);
        params.put("type", type);
        params.put("orderno", orderno);
        System.out.println("partner=" + PARTNER + ",service=" + SERVICE + ",userid=" + userid + ",orderno=" + orderno);

        OkHttpClientManager.postAsync(url, params, null, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //提交订单号到服务器返回标准的支付参数和数据
                String succeedResult = msg.obj.toString();
                System.out.println(succeedResult);
                switch (msg.what) {
                    case R.id.doSucceed:
                        try {
                            JSONObject obj = new JSONObject(succeedResult);
                            int status = obj.getInt("status");
                            if (status == 0) {
                                if (PAY_TYPE == ALI_PAY) {
                                    Log.i("device", obj.getString("device"));
                                    aliPay(obj.getString("data"));
                                } else if (PAY_TYPE == WX_PAY) {
                                    wxPay(obj);
                                } else if (PAY_TYPE == UNION_PAY) {
                                    unionPay(obj.getString("tn"));
                                }
                            } else {
                                Toast.makeText(OrderDetailActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.e("PAY_GET", "异常：" + e.getMessage());
                            Toast.makeText(OrderDetailActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case R.id.doFail:
                        Toast.makeText(OrderDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }

            }
        }, R.id.doSucceed, R.id.doFail);

    }


    /*
 * 支付宝支付
 * */
    private void aliPay(String str) {

        final String orderInfo = str;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderDetailActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                aLiHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private static final int SDK_PAY_FLAG = 1;

    private Handler aLiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
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
                        Toast.makeText(OrderDetailActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(OrderDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    /*
      * 微信支付
      * */
    private void wxPay(JSONObject obj) {
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
            Toast.makeText(OrderDetailActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /*
    * 银联支付
    * */

    private String unionMode = "00";

    private void unionPay(String tn) {
        UPPayAssistEx.startPayByJAR(this, PayActivity.class, null, null, tn, unionMode);
    }


    /*
    * 支付成功后请求服务器校验订单是否真的支付成功
    * */
    private void checkOrderIsPayed() {
        Map<String, String> params = new HashMap<>();
        params.put("userid", userid);
        params.put("orderno", orderno);
        params.put("type", type);
        System.out.println("userid=" + userid + ",orderno=" + orderno + ",type=" + type);
        OkHttpClientManager.postAsync(Constants.GET_PAY_STATUS, params, null, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case R.id.doSucceed:
                        String succeedResult = msg.obj.toString();
                        System.out.println(succeedResult);
                        try {
                            JSONObject obj = new JSONObject(succeedResult);
                            int status = obj.getInt("status");
                            if (status == 0) {
                                if ("0".equals(obj.getString("paystatus"))) {
                                    Toast.makeText(OrderDetailActivity.this, "支付异常", Toast.LENGTH_SHORT).show();
                                } else if ("1".equals(obj.getString("paystatus"))) {
                                    Toast.makeText(OrderDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                                    //返回主页面
                                    PrefUtils.setBoolean(OrderDetailActivity.this, "paystatus", true);
                                    Intent intent = new Intent(OrderDetailActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(OrderDetailActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.doFail:
                        Toast.makeText(OrderDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }
            }
        }, R.id.doSucceed, R.id.doFail);

    }

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
                    Toast.makeText(OrderDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                } else if ("cancel".equalsIgnoreCase(str)) {
                    Toast.makeText(OrderDetailActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                }


    }

    @Override
    protected void onResume() {
        super.onResume();
        int wxstatus = PrefUtils.getInt(OrderDetailActivity.this, "wxpaystatus", -3);
        if (wxstatus == 0) {
            checkOrderIsPayed();
        } else if (wxstatus == -2) {
            Toast.makeText(OrderDetailActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
        } else if (wxstatus == -1) {
            Toast.makeText(OrderDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
        }
        PrefUtils.setInt(OrderDetailActivity.this, "wxpaystatus", -3);
    }


    @OnClick({R.id.iv_back, R.id.iv_home, R.id.ll_pay_wx, R.id.ll_pay_ali, R.id.ll_pay_union, R.id.tv_qtxf})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_home:
                //返回主页面
                Intent intent = new Intent(mActivity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.ll_pay_wx:
                //微信支付
                PAY_TYPE = WX_PAY;
                postParamsToServer(Constants.WX_PAY);
                break;
            case R.id.ll_pay_ali:
                //支付宝支付
                PAY_TYPE = ALI_PAY;
                postParamsToServer(Constants.A_LI_PAY);
                break;
            case R.id.ll_pay_union:
                //银联支付
                PAY_TYPE = UNION_PAY;
                postParamsToServer(Constants.UNION_PAY);
                break;
            case R.id.tv_qtxf:
                //前台现付,返回主页面
                postSpotPayToServer();
                break;
        }
    }

    private void postSpotPayToServer() {
        RequestParams params = new RequestParams(Constants.RECEPTION_URL);
        params.addBodyParameter("orderno", orderno);
        params.addBodyParameter("userid", userid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        PrefUtils.setBoolean(OrderDetailActivity.this, "paystatus", true);
                        Intent qIntent = new Intent(OrderDetailActivity.this, MainActivity.class);
                        qIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(qIntent);
                        finish();
                    } else {
                        Toast.makeText(OrderDetailActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
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
}
