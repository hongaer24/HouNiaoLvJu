package cn.houno.houniaolvju.activity.flight;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.FlightOrderPassengersAdapter;
import cn.houno.houniaolvju.bean.FlightCity;
import cn.houno.houniaolvju.bean.FlightListBean.DataBean.FlightsBean;
import cn.houno.houniaolvju.bean.FlightListBean.DataBean.FlightsBean.SeatItemsBean;
import cn.houno.houniaolvju.bean.OrderListBean;
import cn.houno.houniaolvju.bean.PassengersListBean.DataBean;
import cn.houno.houniaolvju.fragment.orderpage.IngOrderPager;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.pay.alipay.PayResult;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerListView;
import cn.iwgang.countdownview.CountdownView;

/**
 * 机票订单支付页面
 * Created by qzc on 2017/2/21.
 */

public class FlightOrderPayActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_home)
    ImageView ivHome;
    @Bind(R.id.ll_count_down)
    LinearLayout llCountDown;
    @Bind(R.id.tv_count_down)
    TextView tvCountDown;
    @Bind(R.id.cdv_thirty_minutes)
    CountdownView cdvThirtyMinutes;
    @Bind(R.id.tv_order)
    TextView tvOrder;
    @Bind(R.id.tv_total_mount)
    TextView tvTotalMount;
    @Bind(R.id.tv_journey)
    TextView tvJourney;
    @Bind(R.id.tv_price_detail)
    TextView tvPriceDetail;
    @Bind(R.id.tv_dep_airport)
    TextView tvDepAirport;
    @Bind(R.id.tv_arr_airport)
    TextView tvArrAirport;
    @Bind(R.id.tv_dep_date)
    TextView tvDepDate;
    @Bind(R.id.tv_flight_no)
    TextView tvFlightNo;
    @Bind(R.id.lv_order_passengers)
    InnerListView lvOrderPassengers;
    @Bind(R.id.tv_link_man)
    TextView tvLinkMan;
    @Bind(R.id.tv_link_phone)
    TextView tvLinkPhone;
    @Bind(R.id.ll_detail)
    LinearLayout llDetail;
    @Bind(R.id.tv_detail)
    TextView tvDetail;

    @Bind(R.id.iv_arrow)
    ImageView ivArrow;
    @Bind(R.id.iv_wx)
    ImageView ivWx;
    @Bind(R.id.tv_wx_pay)
    TextView tvWxPay;
    @Bind(R.id.ll_pay_wx)
    LinearLayout llPayWx;
    @Bind(R.id.iv_ali)
    ImageView ivAli;
    @Bind(R.id.tv_ali_pay)
    TextView tvAliPay;
    @Bind(R.id.ll_pay_ali)
    LinearLayout llPayAli;
    @Bind(R.id.iv_union)
    ImageView ivUnion;
    @Bind(R.id.tv_union_pay)
    TextView tvUnionPay;
    @Bind(R.id.ll_pay_union)
    LinearLayout llPayUnion;
    @Bind(R.id.ll_pay)
    LinearLayout llPay;
    @Bind(R.id.ll_link_man)
    LinearLayout llLinkMan;
    @Bind(R.id.ll_link_phone)
    LinearLayout llLinkPhone;
    @Bind(R.id.ll_more)
    LinearLayout llMore;

    private FlightOrderPayActivity mActivity;

    private int PAY_TYPE = 0;   //支付类型
    private static final int ALI_PAY = 201;   //支付宝
    private static final int WX_PAY = 202;    //微信支付
    private static final int UNION_PAY = 203;    //银联支付

    private static final String PARTNER = "2088321007217320";
    private static final String SERVICE = "mobile.securitypay.pay";

    private String userid;

    private String orderNo;
    private String type;
    private String mDepDate;
    private FlightCity mDepCity;
    private FlightCity mArrCity;
    private FlightsBean mFlightsBean;
    private SeatItemsBean mSeatItemsBean;
    private List<DataBean> mPassnergersList = new ArrayList<>();
    private int mTotalPrice;    //总付金额
    private int mParPrice;  //总票价
    private int mAirportTax;    //总机场建设费
    private FlightOrderPassengersAdapter mAdapter;
    private boolean isOpen = false; //展开详情标记
    private String mFrom;
    private String mLinkMan;
    private String mLinkPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_flight_order_pay);
        ButterKnife.bind(this);
        mActivity = FlightOrderPayActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initData();
    }

    private void initData() {
        userid = PrefUtils.getString(mActivity, "userid", "");
        Intent intent = getIntent();
        mFrom = intent.getStringExtra("from");
        if (TextUtils.equals(mFrom, "fill_in")) {
            type = "tuniuflight";
            orderNo = intent.getStringExtra("order_no");
            mDepDate = intent.getStringExtra("dep_date");
            mLinkMan = intent.getStringExtra("link_man");
            mLinkPhone = intent.getStringExtra("link_phone");
            mDepCity = (FlightCity) intent.getSerializableExtra("dep_city");
            mArrCity = (FlightCity) intent.getSerializableExtra("arr_city");
            mFlightsBean = (FlightsBean) intent.getSerializableExtra("flight_bean");
            mSeatItemsBean = (SeatItemsBean) intent.getSerializableExtra("flight_seat");
            mPassnergersList = (List<DataBean>) intent.getSerializableExtra("passengers");
            int passnergersSize = mPassnergersList.size();
            mTotalPrice = intent.getIntExtra("total_price", 0);

            tvOrder.setText(orderNo);
            tvTotalMount.setText("¥" + mTotalPrice);
            tvJourney.setText(mDepCity.getAreaname() + "-" + mArrCity.getAreaname());
            tvPriceDetail.setText("票价" + mSeatItemsBean.getPrice() * passnergersSize
                    + "+机建费" + mFlightsBean.getAirportTax() * passnergersSize);
            tvDepAirport.setText(mFlightsBean.getDepAirport()
                    + mFlightsBean.getOrgJetquay() + "(" + mFlightsBean.getDepTime() + ")");
            tvArrAirport.setText(mFlightsBean.getArrAirport()
                    + mFlightsBean.getDstJetquay() + "(" + mFlightsBean.getArriTime() + ")");
            tvDepDate.setText(mDepDate);
            tvFlightNo.setText(mFlightsBean.getFlightNo());
            tvLinkMan.setText(mLinkMan);
            tvLinkPhone.setText(MyText2Utils.replaceSubString(mLinkPhone, 3, 6));

            mAdapter = new FlightOrderPassengersAdapter(mActivity, mPassnergersList);
            lvOrderPassengers.setAdapter(mAdapter);

            cdvThirtyMinutes.start(1000 * 60 * 30);
            cdvThirtyMinutes.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                @Override
                public void onEnd(CountdownView cv) {
                    tvCountDown.setTextColor(Color.parseColor("#ff0000"));
                    tvCountDown.setText("订单已过期");
                    cdvThirtyMinutes.setVisibility(View.GONE);
                    ivAli.setImageResource(R.drawable.icon_ali_gray);
                    tvAliPay.setTextColor(Color.parseColor("#dddddd"));
                    ivWx.setImageResource(R.drawable.icon_wx_gray);
                    tvWxPay.setTextColor(Color.parseColor("#dddddd"));
                    ivUnion.setImageResource(R.drawable.icon_union_gray);
                    tvUnionPay.setTextColor(Color.parseColor("#dddddd"));
                    llPayAli.setClickable(false);
                    llPayWx.setClickable(false);
                    llPayUnion.setClickable(false);
                    llPay.setClickable(false);
                }
            });
        } else {
            //订单列表跳转过来的

            llCountDown.setVisibility(View.GONE);
            OrderListBean.DataBean dataBean = (OrderListBean.DataBean) intent.getSerializableExtra("data");

            orderNo = dataBean.getOrderno();
            mDepDate = dataBean.getCheckin();

            for (int i = 0; i < dataBean.getDetail().getPassengerinfo().size(); i++) {
                DataBean pDataBean = new DataBean();
                pDataBean.setName(dataBean.getDetail().getPassengerinfo().get(i).getPassengername());
                pDataBean.setIdentityno(dataBean.getDetail().getPassengerinfo().get(i).getIdentityno());
                mPassnergersList.add(pDataBean);
            }
            if (TextUtils.equals(mFrom, "order")) {
                type = "tuniuflight";
                mTotalPrice = Integer.parseInt(MyText2Utils.getIntPrice(dataBean.getDetail().getPrice()));
                mParPrice = Integer.parseInt(MyText2Utils.getIntPrice(dataBean.getDetail().getTotalparprice()));
                mAirportTax = Integer.parseInt(MyText2Utils.getIntPrice(dataBean.getDetail().getTotalairporttax()));
                tvPriceDetail.setText("票价" + mParPrice + "+机建费" + mAirportTax);

            } else {
                type = "change";
                mTotalPrice = Integer.parseInt(MyText2Utils.getIntPrice(intent.getStringExtra("changeprice")));
                tvPriceDetail.setText("改签费" + mTotalPrice);
            }
            tvOrder.setText(orderNo);

            tvTotalMount.setText("¥" + mTotalPrice);
            tvJourney.setText(dataBean.getDetail().getDepname() + "-" + dataBean.getDetail().getArrname());

            tvDepAirport.setText(dataBean.getDetail().getOrgcity() + "(" + MyText2Utils.insertString(dataBean.getDetail().getDeptime(), ":", 2) + ")");
            tvArrAirport.setText(dataBean.getDetail().getDstcity() + "(" + MyText2Utils.insertString(dataBean.getDetail().getArrtime(), ":", 2) + ")");
            tvDepDate.setText(mDepDate);
            tvFlightNo.setText(dataBean.getDetail().getFlightno());

            llLinkMan.setVisibility(View.GONE);
            llLinkPhone.setVisibility(View.GONE);

            mAdapter = new FlightOrderPassengersAdapter(mActivity, mPassnergersList);
            lvOrderPassengers.setAdapter(mAdapter);
        }
    }

    private void toggleDetailInfo() {
        if (isOpen) {
            //展开的情况下的处理
            llDetail.setVisibility(View.GONE);
            tvDetail.setText("展开详情");
            ivArrow.setImageResource(R.drawable.icon_down);
        } else {
            //收缩的情况下的处理
            llDetail.setVisibility(View.VISIBLE);
            tvDetail.setText("收起详情");
            ivArrow.setImageResource(R.drawable.icon_up);
        }
        isOpen = !isOpen;
    }


    @OnClick({R.id.iv_back, R.id.iv_home, R.id.ll_more, R.id.ll_pay_wx, R.id.ll_pay_ali, R.id.ll_pay_union})
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
            case R.id.ll_more:
                //展开详情
                toggleDetailInfo();
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
                PAY_TYPE = UNION_PAY;
                postParamsToServer(Constants.UNION_PAY);
                //银联支付
                break;
        }
    }

    /*
     * 提交订单到服务器,返回标准订单数据然后进行支付
     * */
    private void postParamsToServer(String url) {
        PrefUtils.setString(mActivity, "orderno", orderNo);
        Map<String, String> params = new HashMap<>();
        if (Constants.A_LI_PAY.equals(url)) {
            params.put("partner", PARTNER);
            params.put("service", SERVICE);
        }
        params.put("userid", userid);
        params.put("type", type);
        params.put("orderno", orderNo);
        System.out.println("partner=" + PARTNER +",type="+type+ ",service=" + SERVICE + ",userid=" + userid + ",orderno=" + orderNo);

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
                                Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.e("PAY_GET", "异常：" + e.getMessage());
                            Toast.makeText(mActivity, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case R.id.doFail:
                        Toast.makeText(mActivity, "请求失败", Toast.LENGTH_SHORT).show();
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
                PayTask alipay = new PayTask(mActivity);
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
                        checkOrderIsPayed();
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        Toast.makeText(mActivity, "支付取消", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(mActivity, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
        String checkUrl;
        if (TextUtils.equals(mFrom, "change")) {
            checkUrl = Constants.GET_CHANGE_PAY_STATUS;
        } else {
            checkUrl = Constants.GET_PAY_STATUS;
        }
        params.put("userid", userid);
        params.put("orderno", orderNo);
        params.put("type", type);
        System.out.println("userid=" + userid + ",orderno=" + orderNo + ",type=" + type);
        OkHttpClientManager.postAsync(checkUrl, params, null, new Handler() {
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
                                if (TextUtils.equals(mFrom, "change")) {
                                    if (TextUtils.equals("3", obj.getString("changeStatus"))) {
                                        //返回主页面
                                        PrefUtils.setBoolean(mActivity, "paystatus", true);
                                        //这里为了刷新订单列表的支付状态
                                        IngOrderPager.refresh = true;

                                        Intent intent = new Intent(mActivity, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(mActivity, "支付异常", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    if ("0".equals(obj.getString("paystatus"))) {
                                        Toast.makeText(mActivity, "支付异常", Toast.LENGTH_SHORT).show();
                                    } else if ("1".equals(obj.getString("paystatus"))) {
                                        Toast.makeText(mActivity, "支付成功", Toast.LENGTH_SHORT).show();
                                        //返回主页面
                                        PrefUtils.setBoolean(mActivity, "paystatus", true);
                                        //这里为了刷新订单列表的支付状态
                                        IngOrderPager.refresh = true;
                                        Intent intent = new Intent(mActivity, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            } else {
                                Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.doFail:
                        Toast.makeText(mActivity, "请求失败", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
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
            Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show();
        } else if ("cancel".equalsIgnoreCase(str)) {
            Toast.makeText(mActivity, "支付取消", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        int wxstatus = PrefUtils.getInt(mActivity, "wxpaystatus", -3);
        if (wxstatus == 0) {
            checkOrderIsPayed();
        } else if (wxstatus == -2) {
            Toast.makeText(mActivity, "支付取消", Toast.LENGTH_SHORT).show();
        } else if (wxstatus == -1) {
            Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show();
        }
        PrefUtils.setInt(mActivity, "wxpaystatus", -3);
    }


}
