package cn.houno.houniaolvju.activity.train;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.OrderDetailActivity;
import cn.houno.houniaolvju.bean.OrderListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.pay.alipay.PayResult;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.iwgang.countdownview.CountdownView;

/**
 * 火车订单支付页
 */
public class TrainOrderPayActivity extends Activity {

    @Bind(R.id.ll_count_down)
    LinearLayout llCountDown;


    //订单号
    @Bind(R.id.tv_order)
    TextView tvOrderNo;

    //订单总价格
    @Bind(R.id.tv_total_mount)
    TextView tvTotalPrice;

    //商品名称
    @Bind(R.id.tv_journey)
    TextView tvJourney;

    //价格明细
    @Bind(R.id.tv_price_detail)
    TextView tvPriceDetail;

    //乘车站
    @Bind(R.id.tv_dep_station)
    TextView tvDepStation;

    //目的站
    @Bind(R.id.tv_arr_station)
    TextView tvArrStation;

    //乘车日期
    @Bind(R.id.tv_dep_date)
    TextView tvDepDate;


    //列车车次
    @Bind(R.id.tv_train_no)
    TextView tvTrainNo;

    //联系电话
    @Bind(R.id.tv_link_phone)
    TextView tvPhone;

    //订单支付剩余时间
    @Bind(R.id.cdv_thirty_minutes)
    CountdownView mCountdownView;

    //乘车人列表
    @Bind(R.id.lv_order_passengers)
    ListView listView;

    //隐藏的区域
    @Bind(R.id.ll_detail)
    LinearLayout llDetail;

    private List<TrainPassengersBean> list;


    private int PAY_TYPE = 0;   //支付类型
    private static final int ALI_PAY = 201;   //支付宝
    private static final int WX_PAY = 202;    //微信支付
    private static final int UNION_PAY = 203;    //银联支付

    private String type = "train";
    private static final String PARTNER = "2088321007217320";
    private static final String SERVICE = "mobile.securitypay.pay";

    private static final int SDK_PAY_FLAG = 1;

    String orderno;//订单号

    TrainOrderBean orderBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_order_pay);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(TrainOrderPayActivity.this, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        if (getIntent().getBooleanExtra("isFromDetail", false)) {
            mCountdownView = null;
            llCountDown.setVisibility(View.GONE);
            return;
        }
        mCountdownView.start(30 * 60 * 1000);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            orderBean = (TrainOrderBean) intent.getSerializableExtra("orderBean");

            if (!TextUtils.isEmpty(orderBean.getOrderno())) {
                orderno = orderBean.getOrderno();
                tvOrderNo.setText(orderBean.getOrderno());
            }
            if (!TextUtils.isEmpty(orderBean.getPrice())) {
                tvTotalPrice.setText(orderBean.getPrice());
            }
            if (!TextUtils.isEmpty(orderBean.getLinkphone())) {
                tvPhone.setText(MyText2Utils.replaceSubString(orderBean.getLinkphone(), 3, 6));
            }


            tvJourney.setText(orderBean.getFrom_station_name() + " － " + orderBean.getTo_station_name());
            tvDepStation.setText(orderBean.getFrom_station_name() + "(" + orderBean.getStart_time() + ")");
            tvArrStation.setText(orderBean.getTo_station_name() + "(" + orderBean.getArrive_time() + ")");
            String date = orderBean.getTrain_date();
            if (!date.contains("-")) {
                date = MyText2Utils.insertString(date, "-", 4);
                date = MyText2Utils.insertString(date, "-", 7);
            }
            tvDepDate.setText(date);
            tvTrainNo.setText(orderBean.getTrain_code());
        }

        list = orderBean.getPassengerInfo();
        if (list != null && list.size() > 0) {
            listView.setAdapter(new TrainPassengersAdapter(this, list, TrainPassengersAdapter.FROM_ORDER_PAY));
        }

        if (!TextUtils.isEmpty(orderBean.getSeatPrice())) {
            tvPriceDetail.setText(orderBean.getSeatName() + "，价格：¥" + orderBean.getSeatPrice() + "，共" + list.size() + "张");
        } else {
            StringBuilder sb = null;
            for (int i = 0; i < list.size(); i++) {
                sb = new StringBuilder();
                sb.append(list.get(i).getZwname() + "，价格：¥" + list.get(i).getPrice() + "，共" + list.size() + "张");
            }
            tvPriceDetail.setText(sb.toString());
        }
    }


    private void initEvent() {
    }

    @OnClick({R.id.ll_pay_ali, R.id.ll_pay_wx, R.id.iv_back, R.id.iv_home, R.id.ll_more, R.id.ll_pay_union})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_pay_ali: {
                //支付宝支付
                PAY_TYPE = ALI_PAY;
                postParamsToServer(Constants.A_LI_PAY);
                break;
            }

            case R.id.ll_pay_wx: {
                PAY_TYPE = WX_PAY;
                postParamsToServer(Constants.WX_PAY);
                break;
            }

            case R.id.ll_pay_union: {
                PAY_TYPE = UNION_PAY;
                postParamsToServer(Constants.UNION_PAY);
                break;
            }


            case R.id.iv_back: {
                finish();
                break;
            }

            case R.id.iv_home: {
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.ll_more: {
                if (llDetail.getVisibility() == View.GONE) {
                    llDetail.setVisibility(View.VISIBLE);
                } else {
                    llDetail.setVisibility(View.GONE);
                }
                break;
            }


            default:
                break;
        }
    }


    private void postParamsToServer(String url) {
        Map<String, String> params = new HashMap<>();
        if (Constants.A_LI_PAY.equals(url)) {
            params.put("partner", PARTNER);
            params.put("service", SERVICE);
        }
        params.put("userid", PrefUtils.getString(this, "userid", ""));
        params.put("type", type);
        params.put("orderno", orderno);
        System.out.println("partner=" + PARTNER + ",service=" + SERVICE + ",userid=" + PrefUtils.getString(this, "userid", "") + ",orderno=" + orderno.replace("\"", "") + ",type=" + type);

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
                                    aliPay(obj.getString("data"));
                                } else if (PAY_TYPE == WX_PAY) {
                                    wxPay(obj);
                                } else if (PAY_TYPE == UNION_PAY) {
                                    unionPay(obj.getString("tn"));
                                }
                            } else {
                                Toast.makeText(TrainOrderPayActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.e("PAY_GET", "异常：" + e.getMessage());
                            Toast.makeText(TrainOrderPayActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case R.id.doFail:
                        Toast.makeText(TrainOrderPayActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
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
                PayTask alipay = new PayTask(TrainOrderPayActivity.this);
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
                    Log.e("支付宝", resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        // Toast.makeText(TrainOrderPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        checkOrderIsPayed();
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        Toast.makeText(TrainOrderPayActivity.this, "支付宝支付取消", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(TrainOrderPayActivity.this, "支付宝支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    private void checkOrderIsPayed() {
        Map<String, String> params = new HashMap<>();
        params.put("userid", PrefUtils.getString(this, "userid", ""));
        params.put("orderno", orderno);
        params.put("type", type);
        System.out.println("userid=" + PrefUtils.getString(this, "userid", "") + ",orderno=" + orderno + ",type=" + type);
        OkHttpClientManager.postAsync(Constants.GET_PAY_STATUS, params, null, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case R.id.doSucceed:
                        Log.e("进入", "doSuccessed");
                        String succeedResult = msg.obj.toString();
                        Log.e("进入", succeedResult);
                        Log.e("进入", msg.obj.toString());
                        try {
                            JSONObject obj = new JSONObject(succeedResult);
                            int status = obj.getInt("status");
                            if (status == 0) {
                                Log.e("进入支付成功回调接口", "status=" + status);
                                System.out.println("打印回调接口信息:" + msg.obj.toString());
                                if ("0".equals(obj.getString("paystatus"))) {
                                    Toast.makeText(TrainOrderPayActivity.this, "支付异常", Toast.LENGTH_SHORT).show();
                                } else if ("1".equals(obj.getString("paystatus"))) {
                                    Toast.makeText(TrainOrderPayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                                    //返回主页面
                                    System.out.println("返回主页面");
                                    PrefUtils.setBoolean(TrainOrderPayActivity.this, "paystatus", true);
                                    Intent intent = new Intent(TrainOrderPayActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(TrainOrderPayActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.doFail:
                        Toast.makeText(TrainOrderPayActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                }
            }
        }, R.id.doSucceed, R.id.doFail);

    }

    /*
      * 微信支付
      * */
    private void wxPay(JSONObject obj) {
        System.out.println("进入微信支付");
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
            Toast.makeText(TrainOrderPayActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /*
    * 银联支付
    * */

    private String unionMode = "00";

    private void unionPay(String tn) {
        UPPayAssistEx.startPayByJAR(this, PayActivity.class, null, null, tn, unionMode);
    }

    @Override
    protected void onResume() {
        System.out.println("onResume");
        super.onResume();
        int wxstatus = PrefUtils.getInt(TrainOrderPayActivity.this, "wxpaystatus", -3);
        if (wxstatus == 0) {
            checkOrderIsPayed();
        } else if (wxstatus == -2) {
            Toast.makeText(TrainOrderPayActivity.this, "微信支付取消", Toast.LENGTH_SHORT).show();
        } else if (wxstatus == -1) {
            Toast.makeText(TrainOrderPayActivity.this, "微信支付失败", Toast.LENGTH_SHORT).show();
        }
        PrefUtils.setInt(TrainOrderPayActivity.this, "wxpaystatus", -3);
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
            Toast.makeText(TrainOrderPayActivity.this, "银联支付失败", Toast.LENGTH_SHORT).show();
        } else if ("cancel".equalsIgnoreCase(str)) {
            Toast.makeText(TrainOrderPayActivity.this, "银联支付取消", Toast.LENGTH_SHORT).show();
        }

    }


}
