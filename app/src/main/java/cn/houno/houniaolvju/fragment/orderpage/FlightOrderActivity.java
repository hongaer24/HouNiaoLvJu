package cn.houno.houniaolvju.fragment.orderpage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

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
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.flight.FlightOrderPayActivity;
import cn.houno.houniaolvju.activity.scenic.ScenicOrderDetailActivity;
import cn.houno.houniaolvju.adapter.FlightOrderPassengers2Adapter;
import cn.houno.houniaolvju.bean.FlightModifyRefundBean;
import cn.houno.houniaolvju.bean.FlightModifyRefundBean.DataBean.RuleInfosBean.RuleFeeListBean;
import cn.houno.houniaolvju.bean.FlightOrderDetailBean;
import cn.houno.houniaolvju.bean.OrderListBean;
import cn.houno.houniaolvju.bean.OrderListBean.DataBean.DetailBean.PassengerinfoBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.Border2TextView;
import cn.houno.houniaolvju.view.CustomDialog;
import cn.houno.houniaolvju.view.InnerListView;

/**
 * 机票订单详情
 * Created by qzc on 2017/2/23.
 */

public class FlightOrderActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.tv_return_message)
    TextView tvReturnMessage;
    @Bind(R.id.tv_order_title_txt)
    TextView tvTitle;
    @Bind(R.id.tv_dep_time)
    TextView tvDepTime;
    @Bind(R.id.tv_dep_airport)
    TextView tvDepAirport;
    @Bind(R.id.tv_total_time)
    TextView tvTotalTime;
    @Bind(R.id.rl_middle)
    RelativeLayout rlMiddle;
    @Bind(R.id.tv_arr_time)
    TextView tvArrTime;
    @Bind(R.id.tv_add_one_day)
    TextView tvAddOneDay;
    @Bind(R.id.tv_arr_airport)
    TextView tvArrAirport;
    @Bind(R.id.tv_seat_message)
    TextView tvSeatMessage;
    @Bind(R.id.tv_airlines)
    TextView tvAirlines;
    @Bind(R.id.tv_change_refund_rule)
    TextView tvChangeRefundRule;
    @Bind(R.id.tv_Change_sign)
    Button tvChangeSign;
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.tv_orderno)
    TextView tvOrderno;
    @Bind(R.id.lv_passengers)
    InnerListView lvPassengers;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_change_pay)
    Button tvChangePay;
    @Bind(R.id.tv_pay)
    Button tvPay;
    @Bind(R.id.tv_refund)
    Button tvRefund;
    @Bind(R.id.tv_link_man)
    TextView tvLinkMan;
    @Bind(R.id.tv_order_cancel)
    Button tvOrderCancel;


    private final int GET_MODIFY_REFUND_PRICE = 101; //退改签价格说明
    private final int GET_TICKET_INFO = 102;  //获取机票状态
    private final int REFUND_TICKET = 103;  //退票

    private final int CHANGE_SIGN = 201;  //改签
    private final int CHANGE_PAY = 202;  //改签支付

    private FlightOrderActivity mActivity;
    private OrderListBean.DataBean dataBean;


    private String mUserid;
    private String mOrderno;
    private String mType;

    private String mDepTime;
    private String mArrTime;
    private String mDepDate;
    private String mArrDate;
    private double mPrice;
    private double mParPrice;

    private List<PassengerinfoBean> passengerList = new ArrayList<>();

    private FlightOrderPassengers2Adapter passengers2Adapter;


    private StringBuilder refundInfo, ModifyInfo;

    private String refundModifydetailInfo;

    private String mChangePrice;

    private FlightOrderDetailBean flightOrderDetailBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtils.setWindowStatusBarColor(FlightOrderActivity.this, R.color.app_theme_green);
        setContentView(R.layout.activity_flight_order);
        mActivity = FlightOrderActivity.this;
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        mUserid = PrefUtils.getString(mActivity, "userid", "");
        Intent intent = getIntent();
        dataBean = (OrderListBean.DataBean) intent.getSerializableExtra("data");
        mOrderno = dataBean.getOrderno();
        mType = dataBean.getType();
        //获取机票信息
        getDataFromServer(GET_TICKET_INFO);
        mDepDate = dataBean.getCheckin();
        mArrDate = dataBean.getCheckout();
        mPrice = Double.parseDouble(dataBean.getPrice());
        mParPrice = Double.parseDouble(dataBean.getDetail().getParprice());
        //改签说明
        tvTitle.setText(mDepDate + " " + DateUtil.getEWeek(dataBean.getCheckin()) + " "
                + dataBean.getDetail().getDepname() + " - " + dataBean.getDetail().getArrname());
        mDepTime = MyText2Utils.insertString(dataBean.getDetail().getDeptime(), ":", 2);
        mArrTime = MyText2Utils.insertString(dataBean.getDetail().getArrtime(), ":", 2);
        tvDepTime.setText(mDepTime);
        tvArrTime.setText(mArrTime);
        tvDepAirport.setText(dataBean.getDetail().getOrgcity());
        tvArrAirport.setText(dataBean.getDetail().getDstcity());
        DateUtil.DateBean differenceTime = DateUtil.getDifferenceTime(mDepDate + " " + mDepTime
                , mArrDate + " " + mArrTime);
        tvTotalTime.setText(differenceTime.getHour() + "小时" + differenceTime.getMin() + "分钟");
        tvSeatMessage.setText(dataBean.getDetail().getSeatmsg());
        if (TextUtils.equals(dataBean.getDetail().getMeal(), "true")) {
            tvAirlines.setText(dataBean.getDetail().getAirlines()
                    + " " + dataBean.getDetail().getFlightno()
                    + " 机型" + dataBean.getModel() + " 提供餐食");
        } else {
            tvAirlines.setText(dataBean.getDetail().getAirlines()
                    + " " + dataBean.getDetail().getFlightno()
                    + " 机型" + dataBean.getModel() + " 不提供餐食");
        }
        MyText2Utils.formatTicketPrice(mActivity, tvTotalPrice
                , Double.parseDouble(dataBean.getPrice()) + "");
        tvOrderno.setText(dataBean.getOrderno());
        tvPhone.setText(MyText2Utils.replaceSubString(
                dataBean.getDetail().getLinkphone(), 3, 6));//联系手机
        passengerList = dataBean.getDetail().getPassengerinfo();
        passengers2Adapter = new FlightOrderPassengers2Adapter(mActivity, passengerList);
        lvPassengers.setAdapter(passengers2Adapter);
    }

    private void getDataFromServer(final int getDataType) {
        Map<String, String> params = new HashMap<>();
        String connectUrl;
        if (getDataType == REFUND_TICKET) {
            //退票
            params.put("userid", mUserid);
            params.put("orderno", mOrderno);
            connectUrl = Constants.FLIGHT_REFUND;
        } else if (getDataType == GET_MODIFY_REFUND_PRICE) {
            //获取退改签价格
            params.put("info[depCity]", dataBean.getDetail().getDepcode());
            params.put("info[arrCity]", dataBean.getDetail().getArrcode());
            params.put("info[depDate]", mDepDate);
            params.put("info[flightNo]", dataBean.getDetail().getFlightno());
            params.put("info[parPrice]"
                    , MyText2Utils.getIntPrice(dataBean.getDetail().getParprice()) + "");
            params.put("info[price]"
                    , flightOrderDetailBean.getData().getInfo().getdPrice());
            params.put("info[cabinCode]"
                    , flightOrderDetailBean.getData().getInfo().getCabinCode());
            params.put("info[vendorPolicyId]"
                    , flightOrderDetailBean.getData().getInfo().getVendorPolicyId()
                            == null ? "" : flightOrderDetailBean.getData().getInfo().getVendorPolicyId());
            params.put("info[externalInfo]"
                    , flightOrderDetailBean.getData().getInfo().getExternalInfo()
                            == null ? "" : flightOrderDetailBean.getData().getInfo().getExternalInfo());
            params.put("info[solutionId]"
                    , flightOrderDetailBean.getData().getInfo().getSolutionId());
            params.put("info[fullPrice]", flightOrderDetailBean.getData().getInfo().getFullPrice());
            connectUrl = Constants.GET_MODIFY_REFUND;
        } else {
            //获取机票信息
            params.put("userid", mUserid);
            params.put("orderno", mOrderno);
            params.put("type", mType);
            Log.d("222", "userid====" + mUserid + "\n" +
                    "orderno===" + mOrderno + "\n" +
                    "type======" + mType);
            connectUrl = Constants.ORDER_DETAIL_URL;
        }
        System.out.println("userid=" + mUserid + "&orderno=" + mOrderno + "&type=" + mType);
        OkHttpClientManager.postAsync(connectUrl, params, null
                , new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case R.id.doSucceed:
                                String result = msg.obj.toString();
                                Log.e("getDataSuccess-", getDataType + ":" + result);
                                try {
                                    JSONObject object = new JSONObject(result);
                                    int status = object.getInt("status");
                                    if (status == 0) {
                                        switch (getDataType) {
                                            case GET_TICKET_INFO:
                                                parseTicketInfo(result);
                                                break;
                                            case GET_MODIFY_REFUND_PRICE:
                                                parseModifyRefundData(result);
                                                break;
                                            case REFUND_TICKET:
                                                //退票申请成功刷新机票状态
                                                getDataFromServer(GET_TICKET_INFO);
                                                break;
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case R.id.doFail:
                                System.out.println("getDataError:" + msg.obj.toString());
                                break;
                        }
                    }
                }, R.id.doSucceed, R.id.doFail);

    }

    private void parseTicketInfo(String result) {
        hideAllTextView();  //隐藏所有按钮

        flightOrderDetailBean = new Gson().fromJson(result, FlightOrderDetailBean.class);

        //这里可以获取退改签说明
        getDataFromServer(GET_MODIFY_REFUND_PRICE);

        if (TextUtils.equals(dataBean.getDetail().getMeal(), "true")) {
            tvAirlines.setText(dataBean.getDetail().getAirlines()
                    + " " + dataBean.getDetail().getFlightno()
                    + " 机型" + flightOrderDetailBean.getData().getPlanemodel() + " 提供餐食");
        } else {
            tvAirlines.setText(dataBean.getDetail().getAirlines()
                    + " " + dataBean.getDetail().getFlightno()
                    + " 机型" + flightOrderDetailBean.getData().getPlanemodel() + " 不提供餐食");
        }

        tvLinkMan.setText(flightOrderDetailBean.getData().getLinkman());

        String returnMessage = flightOrderDetailBean.getData().getReturnMessage();
        tvReturnMessage.setText(returnMessage);
        String status = flightOrderDetailBean.getData().getStatus();
        String pay_status = flightOrderDetailBean.getData().getPay_status();
        if (TextUtils.equals(pay_status, "0")) {
            if (TextUtils.equals(status, "10")) {
                tvPay.setVisibility(View.GONE);
            } else {
                //未支付，请立即支付
                tvPay.setVisibility(View.VISIBLE);
                tvOrderCancel.setVisibility(View.VISIBLE);
            }
        } else {
            String changestatus = flightOrderDetailBean.getData().getChangestatus();
            if (TextUtils.equals(changestatus, "5")) {
                //改签申请中
            } else if (TextUtils.equals(changestatus, "2")) {
                //改签付款
                //tvChangePay.setVisibility(View.VISIBLE);
                //mChangePrice = flightOrderDetailBean.getData().getChangeprice();
            } else if (TextUtils.equals(changestatus, "3")) {
                //付款成功
            } else {
                if (TextUtils.equals(status, "1")) {
                    //出票失败
                } else if (TextUtils.equals(status, "2")) {
                    //已支付待出票
                } else if (TextUtils.equals(status, "3")) {
                    //已出票
                    // tvChangeSign.setVisibility(View.VISIBLE);
                    tvRefund.setVisibility(View.VISIBLE);
                } else if (TextUtils.equals(status, "4")) {
                    //退票申请中
                } else if (TextUtils.equals(status, "5")) {
                    //退票成功
                } else if (TextUtils.equals(status, "6")) {
                    //退票失败（无退票，有改签）
                    //tvChangeSign.setVisibility(View.VISIBLE);
                } else if (TextUtils.equals(status, "15")) {
                    //退票退款成功
                }
            }
        }

    }

    private void hideAllTextView() {
        if (tvPay.getVisibility() == View.VISIBLE) {
            tvPay.setVisibility(View.GONE);
        }
        if (tvChangePay.getVisibility() == View.VISIBLE) {
            tvChangePay.setVisibility(View.GONE);
        }
        if (tvChangeSign.getVisibility() == View.VISIBLE) {
            tvChangeSign.setVisibility(View.GONE);
        }
        if (tvRefund.getVisibility() == View.VISIBLE) {
            tvRefund.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_change_refund_rule, R.id.tv_change_pay, R.id.tv_pay
            , R.id.tv_refund, R.id.tv_Change_sign,R.id.tv_order_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_change_refund_rule:
                //退改签弹窗说明
                showBackAlterDialog();
                break;
            case R.id.tv_change_pay:
                //改签支付
                Intent payChangeIntent = new Intent();
                payChangeIntent.setClass(mActivity, FlightOrderPayActivity.class);
                payChangeIntent.putExtra("from", "change");
                payChangeIntent.putExtra("data", dataBean);
                payChangeIntent.putExtra("changeprice", mChangePrice);
                startActivity(payChangeIntent);
                break;
            case R.id.tv_pay:
                //支付
                Intent payIntent = new Intent();
                payIntent.setClass(mActivity, FlightOrderPayActivity.class);
                payIntent.putExtra("from", "order");
                payIntent.putExtra("data", dataBean);
                startActivity(payIntent);
                break;
            case R.id.tv_refund:
                //退票
                showRefundDialog();
                break;
            case R.id.tv_Change_sign:
                //改签
                Intent intent = new Intent();
                intent.putExtra("data", dataBean);
                intent.setClass(mActivity, FlightChangeSignActivity.class);
                startActivityForResult(intent, CHANGE_SIGN);
                break;

            case R.id.tv_order_cancel:
                //取消订单
                showCancelOrderDialog();
                break;
        }
    }


    /*
    * 退票窗口
    * */
    private void showRefundDialog() {
        CustomDialog.Builder callDialog = new CustomDialog.Builder(this);
        callDialog.setMessage("退票规定：请查看退改签说明\n" +
                "仅供参考，请以航空公司为准");
        callDialog.setPositiveButton("确定退票", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getDataFromServer(REFUND_TICKET);
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
    * 签转退票说明信息解析
    * */
    private void parseModifyRefundData(String result) {
        try {
            JSONObject object = new JSONObject(result);
            int status = object.getInt("status");
            if (status == 0) {
                JSONObject data = object.getJSONObject("data");
                String externRuleInfo = data.getString("externRuleInfo");
                if (!externRuleInfo.isEmpty()) {
                    FlightModifyRefundBean flightModifyRefundBean = new Gson().fromJson(result
                            , FlightModifyRefundBean.class);
                    List<RuleFeeListBean> refundRuleFeeList =
                            flightModifyRefundBean.getData().getRuleInfos().get(0).getRuleFeeList();

                    refundInfo = new StringBuilder();
                    for (int i = 0; i < refundRuleFeeList.size(); i++) {
                        refundInfo.append(i + 1).append("）").append(
                                refundRuleFeeList.get(i).getName()).append("：").append(
                                refundRuleFeeList.get(i).getValue());
                        if (i != refundRuleFeeList.size() - 1) {
                            refundInfo.append("\n");
                        }
                    }
                    ModifyInfo = new StringBuilder();
                    List<RuleFeeListBean> modifyRuleFeeList =
                            flightModifyRefundBean.getData().getRuleInfos().get(1).getRuleFeeList();
                    for (int i = 0; i < modifyRuleFeeList.size(); i++) {
                        ModifyInfo.append(i + 1).append("）").append(
                                modifyRuleFeeList.get(i).getName()).append("：").append(
                                modifyRuleFeeList.get(i).getValue());
                        if (i != modifyRuleFeeList.size() - 1) {
                            ModifyInfo.append("\n");
                        }
                    }
                    //考虑退票和改期是否为空的情况判断
                    String refundInfoDetail = "";
                    if (flightModifyRefundBean.getData()
                            .getRuleInfos().get(0).getRuleRemark() != null) {
                        refundInfoDetail = "退票：" + flightModifyRefundBean
                                .getData().getRuleInfos().get(0).getRuleRemark();
                    }
                    String modifyInfoDetail = "";
                    if (flightModifyRefundBean.getData()
                            .getRuleInfos().get(1).getRuleRemark() != null) {
                        modifyInfoDetail = "改期：" + flightModifyRefundBean
                                .getData().getRuleInfos().get(1).getRuleRemark();
                    }
                    if (!refundInfoDetail.isEmpty() && !modifyInfoDetail.isEmpty()) {
                        refundModifydetailInfo = refundInfoDetail + "\n" + modifyInfoDetail;
                    } else if (refundInfoDetail.isEmpty() && !modifyInfoDetail.isEmpty()) {
                        refundModifydetailInfo = modifyInfoDetail;
                    } else if (!refundInfoDetail.isEmpty() && modifyInfoDetail.isEmpty()) {
                        refundModifydetailInfo = refundInfoDetail;
                    } else {
                        refundModifydetailInfo = "-";
                    }
                    Log.e("refund_modify_info", refundInfo + "\n"
                            + ModifyInfo + "\n" + refundModifydetailInfo);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void showBackAlterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_back_alrt_ticket, null);//获取自定义布局
        builder.setView(layout);

        TextView tvRefund = (TextView) layout.findViewById(R.id.tv_refund);
        TextView tvModify = (TextView) layout.findViewById(R.id.tv_modify);

        TextView tvConfirm = (TextView) layout.findViewById(R.id.tv_confirm);
        TextView tvDetail = (TextView) layout.findViewById(R.id.tv_dialog_detail);
        if (TextUtils.isEmpty(refundInfo) && TextUtils.isEmpty(ModifyInfo)) {
            tvDetail.setText("改期退票手续费，具体以航空公司为准");
        } else {
            tvRefund.setText(refundInfo);
            tvModify.setText(ModifyInfo);
            tvDetail.setText(refundModifydetailInfo);
        }
        final AlertDialog dialog = builder.show();
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        layout.findViewById(R.id.sv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CHANGE_SIGN) {
                hideAllTextView();
                getDataFromServer(GET_TICKET_INFO);
            }
        }
    }
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
        RequestParams params = new RequestParams(Constants.FLIGHT_CANCEL_ORDER);
        params.addBodyParameter("userid", mUserid);
        params.addBodyParameter("orderno", mOrderno);
       // params.addBodyParameter("type", mType);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    String msg = json.getString("msg");
                    IngOrderPager.refresh = true;
                    Toast.makeText(FlightOrderActivity.this, msg, Toast.LENGTH_SHORT).show();
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
}
