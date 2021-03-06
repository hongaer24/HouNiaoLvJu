package cn.houno.houniaolvju.activity.scenic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.andview.refreshview.XRefreshView;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.OrderDetailActivity;
import cn.houno.houniaolvju.bean.OrderListBean;
import cn.houno.houniaolvju.fragment.orderpage.IngOrderPager;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.pay.alipay.PayResult;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.CustomDialog;

public class ScenicOrderDetailActivity extends Activity implements OnItemClickListener {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.tv_return_message)
    TextView tvReturnMessage;
    @Bind(R.id.tv_order_title_txt)
    TextView tvOrderTitleTxt;
    @Bind(R.id.tv_order_number)
    TextView tvOrderNumber;
    @Bind(R.id.tv_orderno_title)
    TextView tvOrdernoTitle;
    @Bind(R.id.tv_order_name)
    TextView tvOrderName;
    @Bind(R.id.tv_order_time)
    TextView tvOrderTime;
    @Bind(R.id.tv_count)
    TextView tvCount;
    /*@Bind(R.id.tv_order_price1)
    TextView tvOrderPrice1;*/
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_pay)
    Button tvPay;
    @Bind(R.id.tv_order_cancel)
    Button tvOrderCancel;
    @Bind(R.id.tv_return_ticket)
    Button tvReturnTicket;
    @Bind(R.id.tv_order_person_info)
    TextView tvOrderPersonInfo;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_rule)
    TextView tvRule;
    @Bind(R.id.tv_order_money)
    TextView tvOrderMoney;
    @Bind(R.id.tv_order_info)
    TextView tvOrderInfo;
    @Bind(R.id.tv_order_data)
    TextView tvOrderData;
    @Bind(R.id.tv_order_date)
    TextView getTvOrderDate;
    /* @Bind(R.id.tv_custom_phone)
     TextView tvPayWay;*/
    @Bind(R.id.tv_custom_phone)
    TextView tvCustomPhone;
    @Bind(R.id.ll_others)
    LinearLayout llOthers;
    @Bind(R.id.ll_detail_content)
    LinearLayout llDetailContent;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.sv_order_detail)
    ScrollView svOrderDetail;
    @Bind(R.id.iv_title_go)
    ImageView ivTitleGo;
   /* @Bind(R.id.rfv_ing_order)
    XRefreshView rfvIngOrder;*/
    private String userid;
    private String orderNo;
    private String qxid;
    private String type;

    private int PAY_TYPE = 0;   //支付类型
    private static final int ALI_PAY = 201;   //支付宝
    private static final int WX_PAY = 202;    //微信支付
    private static final int UNION_PAY = 203;    //银联支付
    private static final String PARTNER = "2088321007217320";
    private static final String SERVICE = "mobile.securitypay.pay";

    private int mPayStatusInt;
    private int mOrderStatusInt;
    private String mStatusStr;
    private AlertView mAlertView;
    private String productname;
    private String scenicName;
    private String mRoomCount;
    private String mCheckInDate;
    private String mTotalPrice;
    private String mCheckName;
    private String mCheckPhone;
    private ScenicOrderDetailActivity mActivity;
    private String address;
    private String addTime;
    private String result;
    private String mCanCancel;
    private String rid;
    private String orderNo1;
    public static boolean refresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_datail_ing2);
        mActivity = ScenicOrderDetailActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        ButterKnife.bind(this);
        initData();
        //initEvent();
        //rfvIngOrder.startRefresh();
    }


    private void initData() {
        userid = PrefUtils.getString(ScenicOrderDetailActivity.this, "userid", "");
        Intent intent = getIntent();
        OrderListBean.DataBean dataBean = (OrderListBean.DataBean) intent.getSerializableExtra("data");
       /* address=  intent.getStringExtra("address");
        addTime=  intent.getStringExtra("addtime");*/
        orderNo = dataBean.getOrderno();
        qxid = dataBean.getId();
        type = dataBean.getType();
        addTime = intent.getStringExtra("addtime");
        rid = intent.getStringExtra("rid");
        result = formatData("yyyy-MM-dd HH:mm:ss", Long.valueOf(addTime));
        getDataFromServer();

    }
  /*  private void initEvent() {
        rfvIngOrder.setPullLoadEnable(true);
        rfvIngOrder.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                getDataFromServer();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }*/


    public String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }


    private void showPayDialogs() {
        mAlertView = new AlertView("请选择支付方式", null, "取消", null,
                new String[]{"支付宝支付", "微信支付", "银联支付"}, ScenicOrderDetailActivity.this,
                AlertView.Style.ActionSheet, this);
        mAlertView.setCancelable(true);
        mAlertView.show();
    }

    @Override
    public void onItemClick(Object o, int position) {
        switch (position) {
            case 0:
                //支付宝支付
                PAY_TYPE = ALI_PAY;
                postParamsToServer(Constants.A_LI_PAY);
                break;
            case 1:
                //微信支付
                PAY_TYPE = WX_PAY;
                postParamsToServer(Constants.WX_PAY);
                break;
            case 2:
                //银联支付
                PAY_TYPE = UNION_PAY;
                postParamsToServer(Constants.UNION_PAY);
                break;
            case -1:
                break;
        }
    }


    public void getDataFromServer() {
        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        map.put("orderno", orderNo);
        if (type.equals("toursscenic")) {
            type = "tuniuscenic";
        }
        map.put("type", type);
        Log.i("66", "getDataFromServer:=== " + map);
        Log.i("66", "getDataFromServer:=== " + Constants.ORDER_DETAIL_URL);

      /*  RequestParams params = new RequestParams(Constants.ORDER_DETAIL_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("orderno", orderNo);
        params.addBodyParameter("type","tuniuscenic");*/
     /*   x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    System.out.println("订单详情" + result);
                    if (status == 0) {
                        Log.i("111", "result==="+result);
                        parserData(result);
                        setText();
                        setStatus();
                       *//* Intent intent = new Intent();
                        intent.putExtra("type", "tuniuscenic");
                        intent.putExtra("price", allPrice);
                        intent.putExtra("orderno", obj.getString("orderno"));
                        intent.putExtra("title", mScenicTitle + " - " + mTicketTitle);
                        intent.setClass(mActivity, OrderDetailActivity.class);
                        startActivity(intent);*//*
                        //parseData(result);

                    } else {
                       // Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
               *//* pbLoading.setVisibility(View.GONE);
                tvLoading.setText("加载失败");*//*
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                //rfvScenicDetail.stopRefresh();
            }
        });*/

        OkHttpClientManager.postAsync(Constants.ORDER_DETAIL_URL, map, null, handler, R.id.doSucceed, R.id.doFail);

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case R.id.doSucceed:
                    llLoading.setVisibility(View.GONE);
                    llDetailContent.setVisibility(View.VISIBLE);
                    String result = msg.obj.toString();
                    parserData(result);
                    System.out.println("订单详情" + result);
                    setText();
                    setStatus();
                    break;
                case R.id.doFail:
                    tvLoading.setText("加载失败");
                    break;
            }
        }
    };

    private void parserData(String result) {
        try {
            JSONObject json = new JSONObject(result);
            if (json.getInt("status") == 0) {
                //tvLoading.setVisibility(View.GONE);
                //llDetailContent.setVisibility(View.VISIBLE);
                //llLoading.setVisibility(View.GONE);
                //rlBottomBar.setVisibility(View.VISIBLE);
                //      System.out.println("订单详情json" + json);
                // mHotelName = json.getJSONObject("data").getJSONObject("detail").getString("title").trim();
                // mHotelImg = json.getJSONObject("data").getJSONObject("detail").getString("img");

                orderNo = json.getJSONObject("data").getString("orderno");
                /*mStatusStr = json.getJSONObject("data").getString("paymentMessage")
                        + "/" + json.getJSONObject("data").getString("returnMessage");*/

                mOrderStatusInt = Integer.parseInt(json.getJSONObject("data").getString("status"));
                mPayStatusInt = Integer.parseInt(json.getJSONObject("data").getString("pay_status"));
                if ("tuniuscenic".equalsIgnoreCase(type)) {
                    //mRoomName = json.getJSONObject("data").getJSONObject("detail").getString("roomname");
                    productname = json.getJSONObject("data").getString("productname");
                    scenicName = json.getJSONObject("data").getString("scenicName");
                    mRoomCount = json.getJSONObject("data").getString("booknumber");
                    mCheckInDate = json.getJSONObject("data").getString("starttime");
                    mTotalPrice = json.getJSONObject("data").getString("price");
                    mCheckName = json.getJSONObject("data").getJSONObject("contact").getString("name").trim();
                    mCheckPhone = json.getJSONObject("data").getJSONObject("contact").getString("tel").trim();
                    address = json.getJSONObject("data").getString("scenicaddress");
                    mCanCancel = json.getJSONObject("data").getJSONObject("canRefunds").getJSONObject("data").getString("canCancel").trim();

                    //mCheckPhone = json.getJSONObject("data").getJSONObject("contact").getString("tel").trim();
                    // mCheckOutDate = json.getJSONObject("data").getString("checkout");
                    // mHotelAddress = json.getJSONObject("data").getJSONObject("detail").getString("address").trim();
                    //mTotalPrice = Double.parseDouble(json.getJSONObject("data").getString("price")) + "";
                    //mCheckName = json.getJSONObject("data").getString("username");
                    //mCheckPhone = json.getJSONObject("data").getString("phone");
                    //mOtherInfo = json.getJSONObject("data").getString("memo");
                    //iscomment = json.getJSONObject("data").getString("iscomment");

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void setText() {
      /*  tvTabTitle.setText(mHotelName);     //图片文字
        x.image().bind(ivTabImg, mHotelImg);    //图片
        tvOrderNo.setText("订单编号：" + orderNo);     //订单编号*/

        if ("tuniuscenic".equals(type)) {
            //type = "tuniuscenic";
            tvOrdernoTitle.setText(scenicName);
            tvOrderName.setText(productname);
            tvCount.setText("门票数量: " + mRoomCount + "张");
            tvOrderTime.setText("出游时间: " + mCheckInDate);
            tvAddress.setText("地址： " + address);
            getTvOrderDate.setText(result);
            //tvTime.setText(Html.fromHtml("预定时间：<font color=\"#009A44\">" + mCheckInDate + "</font>"));
            //tvAddress.setText(address);
            // tvOther.setVisibility(View.GONE);
            tvName.setText("取票人：" + mCheckName);
            tvPhone.setText("手机号：" + mCheckPhone);

        }
        tvOrderNumber.setText(orderNo);
        tvOrderMoney.setText("¥" + mTotalPrice);
        //tvTotalPrice.setText(Html.fromHtml("订单金额：<font color=\"#009A44\">¥" + mTotalPrice + "</font>"));

    }

    private void setStatus() {

        switch (mPayStatusInt) {
          /*  case 0:
                //"待支付";
                tvCashPay.setVisibility(View.GONE);
                tvOrderPay.setVisibility(View.VISIBLE);
                if (mHotelPayfs == 1) {
                    //前台现付
                    tvOrderPay.setVisibility(View.GONE);
                    tvCashPay.setVisibility(View.VISIBLE);
                } else {
                    //支付订单
                }
                break;*/
            case 1:
                // "已支付";
                //tvOrderCancel.setVisibility(View.GONE);
                //tvOrderPay.setVisibility(View.GONE);
                break;
            case 2:
                //"前台支付";
                // tvCashPay.setVisibility(View.GONE);
                //tvOrderCancel.setVisibility(View.GONE);
                //tvOrderPay.setVisibility(View.GONE);
                break;
        }


        switch (mOrderStatusInt) {
            case 0:
                //"待确认";
                break;
            case 1:
                //"已确认";
                break;
            case 2:
                //"已取消";
                //tvCashPay.setVisibility(View.GONE);
                //tvOrderCancel.setVisibility(View.GONE);
                break;
            case 4:
                //"已退款";
                break;
            case 6:
                //"退款中";
                break;
            case 7:
                //"拒单";
                break;
        }
        if (mOrderStatusInt == 2 && mPayStatusInt == 0 || mOrderStatusInt == 0 && mPayStatusInt == 0) {
            tvReturnMessage.setText("待支付");
            tvOrderCancel.setVisibility(View.VISIBLE);
            tvPay.setVisibility(View.VISIBLE);
            //tvReturnTicket.setVisibility(View.VISIBLE);
            //tvPay.setBackgroundColor(Color.parseColor("#CC0000"));
            //tvOrderStatu.setText("待支付");
        } else if (mOrderStatusInt == 3 && mPayStatusInt == 1) {
            tvReturnMessage.setText("出票中");
            // tvOrderStatu.setText("出票中");
            //tvOrderStatu.setText("正在出票，请耐心等待");
        } else if (mOrderStatusInt == 1 && mPayStatusInt == 0 || mOrderStatusInt == 10 && mPayStatusInt == 0) {
            tvReturnMessage.setText("已取消");
            tvOrderCancel.setVisibility(View.GONE);
            tvPay.setVisibility(View.GONE);
        } else if (mOrderStatusInt == 4 && mPayStatusInt == 1) {
            if (mCanCancel.equals("1")) {
                tvReturnTicket.setVisibility(View.VISIBLE);
                tvReturnMessage.setText("已出票");
            } else {
                tvReturnTicket.setVisibility(View.GONE);
                tvReturnMessage.setText("已退票");
            }
           /* tvOrderCancel.setVisibility(View.GONE);
            tvPay.setVisibility(View.GONE);*/
        } else if (mOrderStatusInt == 6 && mPayStatusInt == 1) {
            tvReturnMessage.setText("退票中");
            tvReturnTicket.setVisibility(View.GONE);
        } else if (mOrderStatusInt == 17 && mPayStatusInt == 1) {
            tvReturnMessage.setText("已退票");
        }
    }

    /*
 * 提交订单到服务器,返回标准订单数据然后进行支付
 * */
    private void postParamsToServer(String url) {
        PrefUtils.setString(ScenicOrderDetailActivity.this, "orderno", orderNo);
        Map<String, String> params = new HashMap<>();
        if (Constants.A_LI_PAY.equals(url)) {
            params.put("partner", PARTNER);
            params.put("service", SERVICE);
        }
        params.put("userid", userid);
        params.put("orderno", orderNo);
        params.put("type", type);
        OkHttpClientManager.postAsync(url, params, null, mHandler, R.id.doSucceed, R.id.doFail);
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
                            Toast.makeText(ScenicOrderDetailActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.e("PAY_GET", "异常：" + e.getMessage());
                        Toast.makeText(ScenicOrderDetailActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.doFail:
                    Toast.makeText(ScenicOrderDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /*
  * 支付宝支付
  * */
    private void aliPay(String str) {

        final String orderInfo = str;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ScenicOrderDetailActivity.this);
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
                        // Toast.makeText(IngOrderDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        checkOrderIsPayed();
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        Toast.makeText(ScenicOrderDetailActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ScenicOrderDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(ScenicOrderDetailActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
        params.put("orderno", orderNo);
        params.put("type", type);
        OkHttpClientManager.postAsync(Constants.GET_PAY_STATUS, params, null, checkHandler, R.id.doSucceed, R.id.doFail);

    }

    private Handler checkHandler = new Handler() {
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
                                Toast.makeText(ScenicOrderDetailActivity.this, "支付异常", Toast.LENGTH_SHORT).show();
                            } else if ("1".equals(obj.getString("paystatus"))) {
                                //返回订单页面
                                IngOrderPager.refresh = true;
                                finish();
                            }
                        } else {
                            Toast.makeText(ScenicOrderDetailActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.doFail:
                    Toast.makeText(ScenicOrderDetailActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    /*
 * 取消订单弹窗
 * */
    private void showCancelOrderDialog() {
        CustomDialog.Builder callDialog = new CustomDialog.Builder(this);
        callDialog.setMessage("确定要取消订单吗？");
        callDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                cancelOrder();
            }
        });

        callDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        callDialog.create().show();
    }

    /*
* 取消订单
* */
    private void cancelOrder() {
        RequestParams params = new RequestParams(Constants.CANCEL_SCENICORDER_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("orderno", orderNo);
        //params.addBodyParameter("id", qxid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    String msg = json.getString("msg");
                    IngOrderPager.refresh = true;
                    Toast.makeText(ScenicOrderDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                finish();
            }
        });
    }

    private void showApplyRefundDialog() {
        CustomDialog.Builder callDialog = new CustomDialog.Builder(this);
        callDialog.setMessage("是否确定申请退款");
        callDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(mActivity, ScenicRefundLIstActivity.class);
                intent.putExtra("orderno", orderNo);
                intent.putExtra("price", mTotalPrice);
                startActivityForResult(intent, 888);
                //applyRefund();
            }
        });

        callDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        callDialog.create().show();
    }

    private void applyRefund() {
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在提交退款申请");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
        RequestParams params = new RequestParams(Constants.APPLY_REFUND);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("orderno", orderNo);
        params.addBodyParameter("causeType", orderNo);
        params.addBodyParameter("causeContent", orderNo);

        Log.e("hotel_refund", "userid=" + userid + "&orderno=" + orderNo);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int status = jsonObject.getInt("status");
                    if (status == 0) {
                        //tvRefund.setVisibility(View.GONE);
                        //tvOrderStatus.setText("已支付/退款中");
                    }
                    Toast.makeText(ScenicOrderDetailActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
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
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        Bundle b = data.getExtras();
        String str = b.getString("pay_result");
        if ("success".equalsIgnoreCase(str)) {
            checkOrderIsPayed();
        } else if ("fail".equalsIgnoreCase(str)) {
            Toast.makeText(ScenicOrderDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
        } else if ("cancel".equalsIgnoreCase(str)) {
            Toast.makeText(ScenicOrderDetailActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
        }

       /* if (requestCode == 888 && resultCode == 887) {
            orderNo1 = data.getStringExtra("orderNo");
            getDataFromServer();
        }*/


    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        int wxstatus = PrefUtils.getInt(ScenicOrderDetailActivity.this, "wxpaystatus", -3);
        if (wxstatus == 0) {
            checkOrderIsPayed();
        } else if (wxstatus == -2) {
            Toast.makeText(ScenicOrderDetailActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
        } else if (wxstatus == -1) {
            Toast.makeText(ScenicOrderDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
        }
        PrefUtils.setInt(ScenicOrderDetailActivity.this, "wxpaystatus", -3);

    }

    @OnClick({R.id.iv_back, R.id.tv_pay_way, R.id.tv_order_price, R.id.tv_pay, R.id.tv_order_cancel, R.id.tv_return_ticket, R.id.iv_title_go})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_order_cancel:
                showCancelOrderDialog();
                break;
            case R.id.tv_return_ticket:
                showApplyRefundDialog();
                break;
            case R.id.tv_pay:
                Intent intent = new Intent();
                //price=Integer.parseInt(mTotalPrice.trim());
                intent.putExtra("type", "tuniuscenic");
                intent.putExtra("mprice", mTotalPrice);
                intent.putExtra("orderno", orderNo);
                intent.putExtra("title", scenicName + " - " + productname);
                intent.setClass(mActivity, OrderDetailActivity.class);
                startActivity(intent);
                break;

            case R.id.iv_title_go:
                Intent intent1 = new Intent();
                intent1.putExtra("scenicid", rid);
                intent1.setClass(mActivity, ScenicDetailActivity.class);
                startActivity(intent1);
                break;


        }


    }
}
