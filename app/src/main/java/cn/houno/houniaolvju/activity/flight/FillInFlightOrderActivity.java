package cn.houno.houniaolvju.activity.flight;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.PassengersAdapter;
import cn.houno.houniaolvju.bean.FlightCity;
import cn.houno.houniaolvju.bean.FlightListBean.DataBean.FlightsBean;
import cn.houno.houniaolvju.bean.FlightListBean.DataBean.FlightsBean.SeatItemsBean;
import cn.houno.houniaolvju.bean.FlightModifyRefundBean;
import cn.houno.houniaolvju.bean.FlightModifyRefundBean.DataBean.RuleInfosBean.RuleFeeListBean;
import cn.houno.houniaolvju.bean.PassengersListBean;
import cn.houno.houniaolvju.fragment.orderpage.IngOrderPager;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.RegexUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.CustomDialog;
import cn.houno.houniaolvju.view.InnerListView;

/**
 * 添加item
 * Created by qzc on 2017/2/7.
 */

public class FillInFlightOrderActivity extends Activity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.iv_home)
    ImageView ivHom;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @Bind(R.id.tv_ticket_title)
    TextView tvTicketTitle;
    @Bind(R.id.tv_ticket_dep_time)
    TextView tvTicketDepTime;
    @Bind(R.id.tv_ticket_dep_airport)
    TextView tvTicketDepAirport;
    @Bind(R.id.tv_ticket_total_time)
    TextView tvTicketTotalTime;
    @Bind(R.id.tv_ticket_arr_time)
    TextView tvTicketArrTime;
    @Bind(R.id.tv_add_one_day)
    TextView tvAddOneDay;
    @Bind(R.id.tv_ticket_arr_airport)
    TextView tvTicketArrAirport;
    @Bind(R.id.tv_ticket_adult)
    TextView tvTicketAdult;
    @Bind(R.id.tv_ticket_adult_price)
    TextView tvTicketAdultPrice;
    @Bind(R.id.tv_fuel_adult)
    TextView tvFuelAdult;
    @Bind(R.id.tv_fuel_adult_price)
    TextView tvFuelAdultPrice;
    @Bind(R.id.iv_icon_go)
    ImageView ivIconGo;
    @Bind(R.id.tv_back_alter_ticket)
    TextView tvBackAlterTicket;
    @Bind(R.id.iv_order_passengers)
    ImageView ivOrderPassengers;
    @Bind(R.id.tv_passengers)
    TextView tvPassengers;
    @Bind(R.id.tv_add_passengers)
    TextView tvAddPassengers;
    @Bind(R.id.lv_passengers)
    InnerListView lvPassengers;
    @Bind(R.id.iv_order_phone)
    ImageView ivOrderPhone;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_total)
    TextView tvTotal;
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.btn_submit_order)
    Button btnSubmitOrder;
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;
    @Bind(R.id.tv_prompt_carry)
    TextView tvPromptCarry;
    @Bind(R.id.et_linkman)
    EditText etLinkman;

    private FillInFlightOrderActivity mActivity;
    private String userid;

    private String mDepDate;
    private String mDepWeek;
    private String mArrDate;
    private FlightCity mDepCity;
    private FlightCity mArrCity;
    private FlightsBean mFlightsBean;
    private SeatItemsBean mSeatItemsBean;
    private int mPrice; //单张
    private int mFuelPrice; //机建燃油费
    private int mTotalPrice;    //总额

    private List<PassengersListBean.DataBean> mPassnergersList = new ArrayList<>();
    private PassengersAdapter mAdapter;

    private ProgressDialog mProgressDialog;
    //签转退票信息
    //退改签说明

    private StringBuilder refundInfo, ModifyInfo;

    private String refundModifyDetailInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtils.setWindowStatusBarColor(FillInFlightOrderActivity.this, R.color.app_theme_green);
        setContentView(R.layout.activity_fillin_flight_order);
        mActivity = FillInFlightOrderActivity.this;
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    private void initEvent() {
        cbAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mPassnergersList.size() != 0) {
                        btnSubmitOrder.setBackgroundResource(R.drawable.selector_orgs_btn);
                        btnSubmitOrder.setClickable(true);
                    } else {
                        btnSubmitOrder.setClickable(false);
                        btnSubmitOrder.setBackgroundColor(Color.parseColor("#dddddd"));
                    }
                } else {
                    btnSubmitOrder.setClickable(false);
                    btnSubmitOrder.setBackgroundColor(Color.parseColor("#dddddd"));
                }
            }
        });

        mAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                mTotalPrice = mPassnergersList.size() * (mPrice + mFuelPrice);
                tvTotalPrice.setText("¥" + mTotalPrice);
                if (mPassnergersList.size() != 0) {
                    if (cbAgree.isChecked()) {
                        btnSubmitOrder.setBackgroundResource(R.drawable.selector_orgs_btn);
                        btnSubmitOrder.setClickable(true);
                    }
                } else {
                    btnSubmitOrder.setClickable(false);
                    btnSubmitOrder.setBackgroundColor(Color.parseColor("#dddddd"));
                }
            }
        });

    }


    private void initData() {

        userid = PrefUtils.getString(mActivity, "userid", "");
        Intent intent = getIntent();
        mDepDate = intent.getStringExtra("dep_date");
        mDepWeek = DateUtil.getEWeek(mDepDate);
        mArrDate = intent.getStringExtra("arr_date");
        mDepCity = (FlightCity) intent.getSerializableExtra("dep_city");
        mArrCity = (FlightCity) intent.getSerializableExtra("arr_city");
        mFlightsBean = (FlightsBean) intent.getSerializableExtra("flight_bean");
        mSeatItemsBean = (SeatItemsBean) intent.getSerializableExtra("flight_seat");

        tvTopbarTitle.setText(mDepCity.getAreaname() + "—" + mArrCity.getAreaname());
        tvTicketTitle.setText(mDepDate + " " + mDepWeek + "  " + mFlightsBean.getAviName() + " " + mFlightsBean.getFlightNo());
        tvTicketDepTime.setText(mFlightsBean.getDepTime());
        if (TextUtils.isEmpty(mFlightsBean.getOrgJetquay())) {
            tvTicketDepAirport.setText(mFlightsBean.getDepAirport());
        } else {
            tvTicketDepAirport.setText(mFlightsBean.getDepAirport() + " " + mFlightsBean.getOrgJetquay());
        }
        tvTicketArrTime.setText(mFlightsBean.getArriTime());
        if (!TextUtils.equals(mDepDate, mFlightsBean.getParam1())) {
            tvAddOneDay.setVisibility(View.VISIBLE);
        }
        tvTicketArrAirport.setText(mFlightsBean.getArrAirport() + " " + mFlightsBean.getDstJetquay());

        DateUtil.DateBean differenceTime = DateUtil.getDifferenceTime(mDepDate + " " + mFlightsBean.getDepTime()
                , mFlightsBean.getParam1() + " " + mFlightsBean.getArriTime());

        tvTicketTotalTime.setText(differenceTime.getHour() + "时" + differenceTime.getMin() + "分");
        if (mSeatItemsBean.getPrice() > mSeatItemsBean.getParPrice()) {
            mPrice = mSeatItemsBean.getPrice();
        } else {
            mPrice=mSeatItemsBean.getParPrice();
        }
        tvTicketAdultPrice.setText(mPrice + "");
        mFuelPrice = mFlightsBean.getAirportTax();
        tvFuelAdultPrice.setText(mFuelPrice + "");
        //获取退改签说明数据
        getModifyAndRefund();
        //乘客列表
        mAdapter = new PassengersAdapter(mActivity, mPassnergersList);
        lvPassengers.setAdapter(mAdapter);
        //禁止提交订单
        btnSubmitOrder.setClickable(false);
        btnSubmitOrder.setBackgroundColor(Color.parseColor("#dddddd"));

    }

    private void showCancelOrderDialog() {
        CustomDialog.Builder callDialog = new CustomDialog.Builder(this);
        callDialog.setMessage("您的支付尚未完成，是否取消？");
        callDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
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
    @OnClick({R.id.iv_back, R.id.iv_home, R.id.tv_back_alter_ticket, R.id.tv_add_passengers
            , R.id.tv_prompt_carry, R.id.btn_submit_order})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.iv_back:
                showCancelOrderDialog();
                break;
            case R.id.iv_home:
                //返回主页面
                intent.setClass(mActivity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_back_alter_ticket:
                //退改签弹窗说明
                showBackAlterDialog();
                break;
            case R.id.tv_add_passengers:
                intent.setClass(mActivity, SelectPassengersActivity.class);
                startActivityForResult(intent, 221);
                break;
            case R.id.tv_prompt_carry:
                showPromptCarryDialog();
                break;
            case R.id.btn_submit_order:
                checkPhone();
                break;
            default:
                break;
        }
    }

    /*
    * 退改签说明
    * */
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
            tvDetail.setText(refundModifyDetailInfo);
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

    /*
    *  《关于民航乘客携带锂电池及危险品乘机公告》
    * */
    private void showPromptCarryDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.PromptCarryDialog);
        LayoutInflater inflater = getLayoutInflater();
        builder.setTitle("《关于民航旅客行李中携带锂电池规定的公告》");
        View layout = inflater.inflate(R.layout.view_dialog_textview, null);//获取自定义布局
        builder.setView(layout);
        TextView tvDialog = (TextView) layout.findViewById(R.id.tv);
        tvDialog.setText(R.string.promptMessage);
        final AlertDialog dialog = builder.create();
        dialog.show();
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /*
    * 检查手机号码输入
    * */
    private void checkPhone() {
        if (!RegexUtils.isMobileExact(etPhone.getText().toString().trim())) {
            Toast.makeText(mActivity, "请填写正确的手机号码", Toast.LENGTH_SHORT).show();
        } else {
            submitOrder();
        }
    }


    private void getModifyAndRefund() {
        Map<String, String> params = new HashMap<>();
        params.put("info[depCity]", mDepCity.getSajm());
        params.put("info[arrCity]", mArrCity.getSajm());
        params.put("info[depDate]", mDepDate);
        params.put("info[flightNo]", mFlightsBean.getFlightNo());
        params.put("info[parPrice]", mSeatItemsBean.getParPrice() + "");
        params.put("info[price]", mSeatItemsBean.getPrice() + "");
        params.put("info[cabinCode]", mSeatItemsBean.getCabinCode());
        params.put("info[vendorPolicyId]", mSeatItemsBean.getVendorPolicyId() == null ? "" : mSeatItemsBean.getVendorPolicyId());
        params.put("info[externalInfo]", mSeatItemsBean.getExternalInfo() == null ? "" : mSeatItemsBean.getExternalInfo());
        params.put("info[solutionId]", mSeatItemsBean.getSolutionId() + "");
        params.put("info[fullPrice]", mSeatItemsBean.getFullPrice() + "");


        OkHttpClientManager.postAsync(Constants.GET_MODIFY_REFUND, params
                , null, new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case R.id.doSucceed:
                                try {
                                    JSONObject object = new JSONObject(msg.obj.toString());
                                    System.out.println(msg.obj.toString());
                                    int status = object.getInt("status");
                                    if (status == 0) {
                                        parseModifyRefundData(msg.obj.toString());
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case R.id.doFail:
                                break;
                        }

                    }
                }, R.id.doSucceed, R.id.doFail);

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
                    FlightModifyRefundBean flightModifyRefundBean = new Gson().fromJson(result, FlightModifyRefundBean.class);
                    List<RuleFeeListBean> refundRuleFeeList = flightModifyRefundBean.getData().getRuleInfos().get(0).getRuleFeeList();

                    refundInfo = new StringBuilder();
                    for (int i = 0; i < refundRuleFeeList.size(); i++) {
                        refundInfo.append(i + 1).append("）").append(refundRuleFeeList.get(i).getName()).append("：").append(refundRuleFeeList.get(i).getValue());
                        if (i != refundRuleFeeList.size() - 1) {
                            refundInfo.append("\n");
                        }
                    }
                    ModifyInfo = new StringBuilder();
                    List<RuleFeeListBean> modifyRuleFeeList = flightModifyRefundBean.getData().getRuleInfos().get(1).getRuleFeeList();
                    for (int i = 0; i < modifyRuleFeeList.size(); i++) {
                        ModifyInfo.append(i + 1).append("）").append(modifyRuleFeeList.get(i).getName()).append("：").append(modifyRuleFeeList.get(i).getValue());
                        if (i != modifyRuleFeeList.size() - 1) {
                            ModifyInfo.append("\n");
                        }
                    }
                    //考虑退票和改期是否为空的情况判断
                    String refundInfoDetail = "";
                    if (flightModifyRefundBean.getData().getRuleInfos().get(0).getRuleRemark() != null) {
                        refundInfoDetail = "退票：" + flightModifyRefundBean.getData().getRuleInfos().get(0).getRuleRemark();
                    }
                    String modifyInfoDetail = "";
                    if (flightModifyRefundBean.getData().getRuleInfos().get(1).getRuleRemark() != null) {
                        modifyInfoDetail = "改期：" + flightModifyRefundBean.getData().getRuleInfos().get(1).getRuleRemark();
                    }
                    if (!refundInfoDetail.isEmpty() && !modifyInfoDetail.isEmpty()) {
                        refundModifyDetailInfo = refundInfoDetail + "\n" + modifyInfoDetail;
                    } else if (refundInfoDetail.isEmpty() && !modifyInfoDetail.isEmpty()) {
                        refundModifyDetailInfo = modifyInfoDetail;
                    } else if (!refundInfoDetail.isEmpty() && modifyInfoDetail.isEmpty()) {
                        refundModifyDetailInfo = refundInfoDetail;
                    } else {
                        refundModifyDetailInfo = "-";
                    }
                    Log.e("refund_modify_info", refundInfo + "\n" + ModifyInfo + "\n" + refundModifyDetailInfo);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /*
    * 提交订单
    * */
    private void submitOrder() {

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在提交订单");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();


        RequestParams params = new RequestParams(Constants.FLIGHT_SUBMIT_ORDER);

        params.addBodyParameter("userid", userid);

        for (int i = 0; i < mPassnergersList.size(); i++) {
            params.addBodyParameter("passengers[" + i + "][passengerName]", mPassnergersList.get(i).getName());
            params.addBodyParameter("passengers[" + i + "][personType]", "1");
            params.addBodyParameter("passengers[" + i + "][birthday]", mPassnergersList.get(i).getBirthday());
            params.addBodyParameter("passengers[" + i + "][tel]", mPassnergersList.get(i).getPhone());
            params.addBodyParameter("passengers[" + i + "][identityNo]", mPassnergersList.get(i).getIdentityno());
            params.addBodyParameter("passengers[" + i + "][identityType]", "1");
        }

        params.addBodyParameter("segment[flightNo]", mFlightsBean.getFlightNo());

        params.addBodyParameter("segment[departCityCode]", mFlightsBean.getSeatItems().get(0).getDepartCityIataCode());
        //params.addBodyParameter("segment[depCode]", mFlightsBean.getSeatItems().get(0).getDepartCityIataCode());

        params.addBodyParameter("segment[arriveCityCode]", mFlightsBean.getSeatItems().get(0).getArriveCityIataCode());
        //params.addBodyParameter("segment[arrCode]", mFlightsBean.getSeatItems().get(0).getArriveCityIataCode());
        params.addBodyParameter("segment[seatClass]", mSeatItemsBean.getSeatCode());
         params.addBodyParameter("segment[departDate]", mDepDate);
         //params.addBodyParameter("segment[depDate]", mDepDate);
        params.addBodyParameter("segment[depTime]", mFlightsBean.getDepTime());
        params.addBodyParameter("segment[arrTime]", mFlightsBean.getArriTime());
        params.addBodyParameter("segment[planeModel]", mFlightsBean.getPlaneType());
        params.addBodyParameter("info[parPrice]", mSeatItemsBean.getParPrice() + "");
        params.addBodyParameter("info[airportTax]", mFlightsBean.getAirportTax() + "");
        params.addBodyParameter("info[totalPrice]", mTotalPrice + "");
        params.addBodyParameter("info[solutionId]", mSeatItemsBean.getSolutionId() + "");
         params.addBodyParameter("info[name]", etLinkman.getText().toString().trim());
        params.addBodyParameter("info[tel]", etPhone.getText().toString().trim());
        //params.addBodyParameter("info[linkmobile]", etPhone.getText().toString().trim());

        params.addBodyParameter("info[depName]", mDepCity.getAreaname());
        params.addBodyParameter("info[arrName]", mArrCity.getAreaname());
        params.addBodyParameter("info[seatMsg]", mSeatItemsBean.getSeatMsg());
        params.addBodyParameter("info[arrDate]", mFlightsBean.getParam1());
        params.addBodyParameter("info[orgJetquay]", mFlightsBean.getDepAirport() + mFlightsBean.getOrgJetquay());
        params.addBodyParameter("info[dstJetquay]", mFlightsBean.getArrAirport() + mFlightsBean.getDstJetquay());
        params.addBodyParameter("info[meal]", "");
        params.addBodyParameter("info[airlines]", mSeatItemsBean.getAirlineCompany());
        params.addBodyParameter("info[resId]", mSeatItemsBean.getResId());
        params.addBodyParameter("info[vendorPolicyId]", mSeatItemsBean.getVendorPolicyId() == null ? "" : mSeatItemsBean.getVendorPolicyId());
        params.addBodyParameter("info[externalInfo]", mSeatItemsBean.getExternalInfo() == null ? "" : mSeatItemsBean.getExternalInfo());
        params.addBodyParameter("info[cabinCode]", mSeatItemsBean.getCabinCode() == null ? "" : mSeatItemsBean.getCabinCode());
        params.addBodyParameter("info[fullPrice]", mSeatItemsBean.getFullPrice() + "");
        params.addBodyParameter("info[dPrice]", mSeatItemsBean.getPrice() + "");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("submitOrder", result);
                try {
                    JSONObject object = new JSONObject(result);
                    int status = object.getInt("status");
                    if (status == 0) {
                        Intent intent = new Intent();
                        intent.putExtra("from", "fill_in");
                        intent.putExtra("order_no", object.getString("orderno"));
                        intent.putExtra("dep_date", mDepDate);
                        intent.putExtra("arr_date", mFlightsBean.getParam1());
                        intent.putExtra("dep_city", mDepCity);
                        intent.putExtra("arr_city", mArrCity);
                        intent.putExtra("link_man", etLinkman.getText().toString().trim());
                        intent.putExtra("link_phone", etPhone.getText().toString().trim());
                        intent.putExtra("flight_bean", mFlightsBean);
                        intent.putExtra("flight_seat", mSeatItemsBean);
                        intent.putExtra("passengers", (Serializable) mPassnergersList);
                        intent.putExtra("total_price", mTotalPrice);
                        intent.setClass(mActivity, FlightOrderPayActivity.class);
                        IngOrderPager.refresh = true;
                        startActivity(intent);
                    } else if (status == 9000003) {
                        changeParPrice(object);
                    } else {
                        Toast.makeText(mActivity, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("submitOrderE", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("submitOrderC", "cancel");


            }

            @Override
            public void onFinished() {
                Log.e("submitOrderF", "finish");
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        });
    }

    private void changeParPrice(final JSONObject object) {
        try {
            mSeatItemsBean.setParPrice(object.getInt("parPrice"));
            submitOrder();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 221 && resultCode == RESULT_OK) {
                mPassnergersList = (ArrayList<PassengersListBean.DataBean>) data.getSerializableExtra("list");
                mAdapter.setData(mPassnergersList);
            }
        }
    }


  /*  @Override
    protected void onStop() {
        super.onStop();
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }*/
}
