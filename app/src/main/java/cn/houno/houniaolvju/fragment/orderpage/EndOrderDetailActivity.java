package cn.houno.houniaolvju.fragment.orderpage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.OrderListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.CustomDialog;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class EndOrderDetailActivity extends Activity {

   /* @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;*/
   /* @Bind(R.id.iv_tab_img)
    ImageView ivTabImg;
    @Bind(R.id.tv_tab_title)
    TextView tvTabTitle;
    @Bind(R.id.tv_order_no)
    TextView tvOrderNo;*/
   /* @Bind(R.id.tv_order_title)
    TextView tvOrderTitle;
    @Bind(R.id.tv_count)
    TextView tvCount;
    @Bind(R.id.tv_time)
    TextView tvTime;*/
   /* @Bind(R.id.tv_address_txt)
    TextView tvAddressTxt;*/
   /* @Bind(R.id.tv_address)
    TextView tvAddress;*/
   /* @Bind(R.id.ll_address)
    LinearLayout llAddress;*/
   /* @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.tv_order_info)
    TextView tvOrderInfo;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_phone)
    TextView tvPhone;*/
  /*  @Bind(R.id.tv_other)
    TextView tvOther;*/

  /*  @Bind(R.id.sv_order_detail)
    ScrollView svOrderDetail;*/
  /*  @Bind(R.id.tv_loading)
    TextView tvLoading;*/

    /* @Bind(R.id.iv_circle)
     ImageView ivCircle;
     @Bind(R.id.tv_order_status)
     TextView tvOrderStatus;
     @Bind(R.id.tv_order_comment)
     TextView tvOrderComment;
     @Bind(R.id.rl_bottom_bar)
     RelativeLayout rlBottomBar;
     @Bind(R.id.tv_order_statu)
     TextView tvOrderStatu;
     @Bind(R.id.tv_countdowntime)
     TextView tvCountdowntime;
     @Bind(R.id.btn_order_pay)
     Button btnOrderPay;
     @Bind(R.id.btn_order_cacel)
     Button btnOrderCacel;
     @Bind(R.id.tv_order_type)
     TextView tvOrderType;
     @Bind(R.id.tv_orderno_number)
     TextView tvOrdernoNumber;
     @Bind(R.id.tv_pay_status)
     TextView tvPayStatus;*/
   /* @Bind(R.id.tv_orderno_title)
    TextView tvOrdernoTitle;*/
    /*@Bind(R.id.iv_number_person)
    ImageView ivNumberPerson;*/
 /*   @Bind(R.id.tv_order_name)
    TextView tvOrderName;
    @Bind(R.id.tv_order_time)
    TextView tvOrderTime;*/
    /*   @Bind(R.id.tv_use_way)
       TextView tvUseWay;*/

/*    @Bind(R.id.tv_order_person_info)
    TextView tvOrderPersonInfo;
    @Bind(R.id.tv_order_data)
    TextView tvOrderData;*/
    /*@Bind(R.id.tv_status)
    TextView tvStatus;*/

/*    @Bind(R.id.tv_order_price)
    TextView tvOrderPrice;
    @Bind(R.id.tv_count)
    TextView tvCount;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_phone)
    TextView tvPhone;*/
    /*   @Bind(R.id.pb_loading)
       ProgressBar pbLoading;*/
  /*  @Bind(R.id.ll_loading)
    LinearLayout llLoading;*/
  /*  @Bind(R.id.rl_pay)
    RelativeLayout rlPay;*/
 /*   @Bind(R.id.tv_order_price1)
    TextView tvOrderPrice1;*/

 /*   @Bind(R.id.tv_order_text)
    TextView tvOrderText;*/
  /*  @Bind(R.id.tv_order_again)
    TextView tvOrderAgain;*/
   /* @Bind(R.id.ll_cancel)
    LinearLayout llCancel;*/

  /*  @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.tv_return_message)
    TextView tvReturnMessage;
    @Bind(R.id.tv_order_title_txt)
    TextView tvOrderTitleTxt;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_pay)
    Border2TextView tvPay;
    @Bind(R.id.tv_order_cancel)
    Border2TextView tvOrderCancel;
    @Bind(R.id.tv_return_ticket)
    Border2TextView tvReturnTicket;
    @Bind(R.id.ll_detail_content)
    LinearLayout llDetailContent;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.ll_others)
    LinearLayout llOthers;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.ll_address)
    LinearLayout llAddress;
    @Bind(R.id.tv_order_number)
    TextView tvOrderNumber;
    @Bind(R.id.tv_rule)
    TextView tvRule;
    @Bind(R.id.tv_order_info)
    TextView tvOrderInfo;
    @Bind(R.id.tv_pay_way)
    TextView tvPayWay;
    @Bind(R.id.tv_custom_phone)
    TextView tvCustomPhone;*/

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.iv_tab_img)
    ImageView ivTabImg;
    @Bind(R.id.tv_tab_title)
    TextView tvTabTitle;
    @Bind(R.id.tv_order_no)
    TextView tvOrderNo;
    @Bind(R.id.tv_order_title)
    TextView tvOrderTitle;
    @Bind(R.id.tv_count)
    TextView tvCount;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_address_txt)
    TextView tvAddressTxt;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.ll_address)
    LinearLayout llAddress;
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.tv_order_info)
    TextView tvOrderInfo;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tv_other)
    TextView tvOther;
    @Bind(R.id.sv_order_detail)
    ScrollView svOrderDetail;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.iv_circle)
    ImageView ivCircle;
    @Bind(R.id.tv_order_status)
    TextView tvOrderStatus;
    @Bind(R.id.tv_order_comment)
    TextView tvOrderComment;
    @Bind(R.id.rl_bottom_bar)
    RelativeLayout rlBottomBar;

    private String mHotelName;
    private String mHotelImg;

    private String mRoomName;
    private String mRoomCount;
    private String mCheckInDate;
    private String mCheckOutDate;
    private String mHotelday;
    private String mHotelAddress;
    private String mTotalPrice;
    private String mCheckName;
    private String mCheckPhone;
    private String mOtherInfo;
    private String mStatusStr;

    private String mPayfsStr;
    private String qxid;
    private String userid;
    private String orderNo;
    private String type;
    private OrderListBean.DataBean dataBean;
    private int mPayStatusInt;
    private int mOrderStatusInt;
    private String productname;
    private String scenicName;
    private EndOrderDetailActivity mActivity;
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_order_detail_end);
        ButterKnife.bind(this);
        mActivity = EndOrderDetailActivity.this;
        StatusBarUtils.setWindowStatusBarColor(EndOrderDetailActivity.this, R.color.app_theme_green);
        initData();

    }

    private void initData() {
        userid = PrefUtils.getString(EndOrderDetailActivity.this, "userid", "");
        Intent intent = getIntent();
        dataBean = (OrderListBean.DataBean) intent.getSerializableExtra("data");
        orderNo = dataBean.getOrderno();
        type = dataBean.getType();
        getDataFromServer();
    }
    private void setOrderStatus() {
        switch (mPayStatusInt) {
            case 0:
                // "待支付";
                break;
            case 1:
                // "已支付";
                break;
            case 2:
                // "前台支付";
                break;
        }


        switch (mOrderStatusInt) {
            case 0:
                // "待确认";
                break;
            case 1:
                //"已确认";
                break;
            case 2:
                // "已取消";
                break;
            case 3:
                // "已完成";
                break;
            case 4:
                // "已退款";
                break;
        }


        tvOrderStatus.setText(mStatusStr);
    }
 /*   private void setOrderStatus() {
        switch (mPayStatusInt) {
            case 0:
                // "待支付";
                break;
            case 1:
                // "已支付";
                break;
            case 2:
                // "前台支付";
                break;
        }


        switch (mOrderStatusInt) {
            case 0:
                // "待确认";
                break;
            case 1:
                //"已确认";
                break;
            case 2:
                // "已取消";
                break;
            case 3:
                // "已完成";
                break;
            case 4:
                // "已退款";
                break;
        }


        if (mOrderStatusInt == 1 && mPayStatusInt == 0 || mOrderStatusInt == 10 && mPayStatusInt == 0) {
            tvReturnMessage.setText("已取消");
            //tvOrderStatu.setText("已取消");
            //tvPayStatus.setText("已取消");
            //tvStatus.setText("您的订单已取消，欢迎重新预定");
            //tvOrderPrice1.setText("¥0");
            //rlPay.setVisibility(View.GONE);
            //llCancel.setVisibility(View.GONE);
        } else if (mOrderStatusInt == 4 && mPayStatusInt == 1) {
            tvReturnMessage.setText("已出票");
            tvReturnTicket.setVisibility(View.VISIBLE);
            tvReturnTicket.setVisibility(View.VISIBLE);
            //tvOrderStatu.setText("已出票");
            //tvStatus.setText("预定成功，祝您出游愉快");
            //rlPay.setVisibility(View.GONE);
            //btnOrderCacel.setVisibility(View.GONE);
        }
    }*/


    public void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.ORDER_DETAIL_URL);
        params.addBodyParameter("userid", userid);
        if(type.equals("toursscenic")){
               type="tuniuscenic";
        }
        Log.d("555", "getDataFromServer:===== "+type);
        params.addBodyParameter("type", type);
        params.addBodyParameter("orderno", orderNo);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //llDetailContent.setVisibility(View.VISIBLE);
                //llLoading.setVisibility(View.GONE);
                parserData(result);
                setText();
                setOrderStatus();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                tvLoading.setText("加载失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void parserData(String result) {
        try {
            JSONObject json = new JSONObject(result);

            if (json.getInt("status")==0) {
                tvLoading.setVisibility(View.GONE);
                svOrderDetail.setVisibility(View.VISIBLE);
                rlBottomBar.setVisibility(View.VISIBLE);
                mHotelName = json.getJSONObject("data").getJSONObject("detail").getString("title");
                mHotelImg = json.getJSONObject("data").getJSONObject("detail").getString("img");
                orderNo = json.getJSONObject("data").getString("orderno");

                mStatusStr = json.getJSONObject("data").getString("paymentMessage")
                        + "/" + json.getJSONObject("data").getString("returnMessage");
                mOrderStatusInt = Integer.parseInt(json.getJSONObject("data").getString("status"));
                mPayStatusInt = Integer.parseInt(json.getJSONObject("data").getString("pay_status"));

                if ("Acti".equalsIgnoreCase(type)) {
                    mRoomCount = json.getJSONObject("data").getString("num");
                    mCheckInDate = json.getJSONObject("data").getString("checkin");
                    mTotalPrice = json.getJSONObject("data").getString("price");
                    mCheckName = json.getJSONObject("data").getString("username");
                    mCheckPhone = json.getJSONObject("data").getString("phone");
                    mOtherInfo = json.getJSONObject("data").getString("kefumemo");
                } else if ("scenic".equalsIgnoreCase(type)) {
                    String isComment = json.getJSONObject("data").getString("iscomment");
                    if (mOrderStatusInt == 3 && mPayStatusInt == 1) {
                        if ("0".equals(isComment)) {
                            tvOrderComment.setVisibility(View.VISIBLE);
                        } else {
                            tvOrderComment.setVisibility(View.GONE);
                        }
                    }
                    mRoomName = json.getJSONObject("data").getJSONObject("detail").getString("roomname");
                    mRoomCount = json.getJSONObject("data").getString("ticketnum");
                    mCheckInDate = json.getJSONObject("data").getString("checkin");
                    mCheckOutDate = json.getJSONObject("data").getString("checkout");
                    mHotelAddress = json.getJSONObject("data").getJSONObject("detail").getString("address").trim();
                    mTotalPrice = json.getJSONObject("data").getString("price");
                    mCheckName = json.getJSONObject("data").getString("username");
                    mCheckPhone = json.getJSONObject("data").getString("phone");
                    mOtherInfo = json.getJSONObject("data").getString("memo");
                } else {
                    mRoomName = json.getJSONObject("data").getJSONObject("detail").getString("roomname");
                    mRoomCount = json.getJSONObject("data").getString("roomnum");
                    mCheckInDate = json.getJSONObject("data").getString("checkin");
                    mCheckOutDate = json.getJSONObject("data").getString("checkout");
                    if ("hotel".equalsIgnoreCase(type)) {
                        mHotelday = json.getJSONObject("data").getString("days");
                        String isComment = json.getJSONObject("data").getString("iscomment");
                        if ((mPayStatusInt == 1 || mPayStatusInt == 2) && mOrderStatusInt == 3) {
                            if ("0".equals(isComment)) {
                                tvOrderComment.setVisibility(View.VISIBLE);
                            } else {
                                tvOrderComment.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        mHotelday = json.getJSONObject("data").getString("rzdays");
                    }
                    mHotelAddress = json.getJSONObject("data").getJSONObject("detail").getString("address").trim();
                    mTotalPrice = json.getJSONObject("data").getString("price");
                    mCheckName = json.getJSONObject("data").getString("username");
                    mCheckPhone = json.getJSONObject("data").getString("phone");
                    mOtherInfo = json.getJSONObject("data").getString("memo");
                    mTotalPrice = json.getJSONObject("data").getString("price");
                }

            }else {
                tvLoading.setText("加载失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void setText() {

        tvTabTitle.setText(mHotelName);     //图片文字
        x.image().bind(ivTabImg, mHotelImg);    //图片
        tvOrderNo.setText("订单编号：" + orderNo);     //订单编号

        if ("hotel".equals(type) || "ydhotel".equals(type) || "groupon".equals(type)) {
            tvOrderTitle.setText("房源名称：" + mRoomName);
            tvCount.setText("预订房间：" + mRoomCount + "间");

            tvTime.setText(Html.fromHtml("住宿时间：<font color=\"#009A44\">" + mCheckInDate
                    + "</font>至<font color=\"#009A44\">" + mCheckOutDate + "</font> 共<font color=\"#009A44\">"
                    + mHotelday + "</font>天"));
            tvAddressTxt.setText("地　　址：");
            tvAddress.setText(mHotelAddress);
            tvOther.setText("其他需求：" + mOtherInfo);
        } else if ("Acti".equals(type)) {
            tvOrderTitle.setText("活动名称：" + mHotelName);
            tvCount.setText("参加人数：" + mRoomCount + "人");
            tvTime.setText(Html.fromHtml("预定时间：<font color=\"#009A44\">" + mCheckInDate + "</font>"));
            llAddress.setVisibility(View.GONE);
            tvOther.setText("其他需求：" + mOtherInfo);
        } else if ("Scenic".equals(type)) {
            type = "scenic";
            tvOrderTitle.setText("景点名称：" + mHotelName);
            tvCount.setText("预定数量：" + mRoomCount + "人");
            tvTime.setText(Html.fromHtml("预定时间：<font color=\"#009A44\">" + mCheckInDate + "</font>"));
            tvAddress.setText(mHotelAddress);
            tvOther.setVisibility(View.GONE);
        }

        tvName.setText("姓　　名：" + mCheckName);
        tvPhone.setText("手　　机：" + mCheckPhone);
        tvTotalPrice.setText(Html.fromHtml("订单金额：<font color=\"#009A44\">¥" + mTotalPrice + "</font>"));


    }
  /*  private void parserData(String result) {
        try {
            JSONObject json = new JSONObject(result);

            if (json.getInt("status") == 0) {
                //tvLoading.setVisibility(View.GONE);
                //llLoading.setVisibility(View.GONE);
                //svOrderDetail.setVisibility(View.VISIBLE);
                //rlBottomBar.setVisibility(View.VISIBLE);
              *//*  mHotelName = json.getJSONObject("data").getJSONObject("detail").getString("title");
                mHotelImg = json.getJSONObject("data").getJSONObject("detail").getString("img");*//*

                orderNo = json.getJSONObject("data").getString("orderno");
                mStatusStr = json.getJSONObject("data").getString("paymentMessage")
                        + "/" + json.getJSONObject("data").getString("returnMessage");
                mOrderStatusInt = Integer.parseInt(json.getJSONObject("data").getString("status"));
                mPayStatusInt = Integer.parseInt(json.getJSONObject("data").getString("pay_status"));

                if ("Acti".equalsIgnoreCase(type)) {
                    mRoomCount = json.getJSONObject("data").getString("num");
                    mCheckInDate = json.getJSONObject("data").getString("checkin");
                    mTotalPrice = json.getJSONObject("data").getString("price");
                    mCheckName = json.getJSONObject("data").getString("username");
                    mCheckPhone = json.getJSONObject("data").getString("phone");
                    mOtherInfo = json.getJSONObject("data").getString("kefumemo");
                } else if ("tuniuscenic".equals(type)) {
                    *//*String isComment = json.getJSONObject("data").getString("iscomment");
                    if (mOrderStatusInt == 3 && mPayStatusInt == 1) {
                        if ("0".equals(isComment)) {
                            tvOrderComment.setVisibility(View.VISIBLE);
                        } else {
                            tvOrderComment.setVisibility(View.GONE);
                        }
                    }*//*
                   *//* mRoomName = json.getJSONObject("data").getJSONObject("detail").getString("roomname");
                    mCheckOutDate = json.getJSONObject("data").getString("checkout");
                    mHotelAddress = json.getJSONObject("data").getJSONObject("detail").getString("address").trim();*//*
                    productname = json.getJSONObject("data").getString("productname");
                    scenicName = json.getJSONObject("data").getString("scenicName");
                    mRoomCount = json.getJSONObject("data").getString("booknumber");
                    mCheckInDate = json.getJSONObject("data").getString("starttime");
                    mTotalPrice = json.getJSONObject("data").getString("price");
                    mCheckName = json.getJSONObject("data").getJSONObject("contact").getString("name").trim();
                    mCheckPhone = json.getJSONObject("data").getJSONObject("contact").getString("tel").trim();
                    //mOtherInfo = json.getJSONObject("data").getString("memo");
                } else if("hotel".equals(type)){
                    Log.d("556", "getDataFromServer:===== "+type);
                    mRoomName = json.getJSONObject("data").getJSONObject("detail").getString("roomname");
                    title= json.getJSONObject("data").getJSONObject("detail").getString("title");
                    mRoomCount = json.getJSONObject("data").getString("roomnum");
                    mCheckInDate = json.getJSONObject("data").getString("checkin");
                    mCheckOutDate = json.getJSONObject("data").getString("checkout");
                    if ("hotel".equalsIgnoreCase(type)) {
                        mHotelday = json.getJSONObject("data").getString("days");
                        String isComment = json.getJSONObject("data").getString("iscomment");
                        if ((mPayStatusInt == 1 || mPayStatusInt == 2) && mOrderStatusInt == 3) {
                            if ("0".equals(isComment)) {
                                // tvOrderComment.setVisibility(View.VISIBLE);
                            } else {
                                //tvOrderComment.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        mHotelday = json.getJSONObject("data").getString("rzdays");
                    }
                    mHotelAddress = json.getJSONObject("data").getJSONObject("detail").getString("address").trim();
                    mTotalPrice = json.getJSONObject("data").getString("price");
                    mCheckName = json.getJSONObject("data").getString("username");
                    mCheckPhone = json.getJSONObject("data").getString("phone");
                    mOtherInfo = json.getJSONObject("data").getString("memo");
                    mTotalPrice = json.getJSONObject("data").getString("price");
                }

            } else {
                //tvLoading.setText("加载失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

   /* private void setText() {

    *//*    tvTabTitle.setText(mHotelName);     //图片文字
        x.image().bind(ivTabImg, mHotelImg);    //图片
        tvOrderNo.setText("订单编号：" + orderNo);     //订单编号
*//*
        if ("hotel".equals(type) || "ydhotel".equals(type) || "groupon".equals(type)) {
            tvOrdernoTitle.setText( mRoomName);
            tvOrderName.setText(title);
            tvCount.setText("预订房间：" + mRoomCount + "间");
            tvOrderTime.setText(Html.fromHtml("住宿时间：<font color=\"#009A44\">" + mCheckInDate
                    + "</font>至<font color=\"#009A44\">" + mCheckOutDate + "</font> 共<font color=\"#009A44\">"
                    + mHotelday + "</font>天"));
            //tvAddressTxt.setText("地　　址：");
            tvAddress.setText("地址:"+      mHotelAddress);
            tvName.setText("入住人姓名：" +  mCheckName);
            tvPhone.setText("手机号：" +    mCheckPhone);
            llOthers.setVisibility(View.GONE);
        } *//*else if ("Acti".equals(type)) {
            tvOrderTitle.setText("活动名称：" + mHotelName);
            tvCount.setText("参加人数：" + mRoomCount + "人");
            tvTime.setText(Html.fromHtml("预定时间：<font color=\"#009A44\">" + mCheckInDate + "</font>"));
            llAddress.setVisibility(View.GONE);
            tvOther.setText("其他需求：" + mOtherInfo);
        }*//* else if ("tuniuscenic".equals(type)) {
           *//* type = "tuniuscenic";
            tvOrderTitle.setText(scenicName);
            tvCount.setText("预定数量：" + mRoomCount + "人");
            tvTime.setText(Html.fromHtml("预定时间：<font color=\"#009A44\">" + mCheckInDate + "</font>"));
            tvAddress.setText(mHotelAddress);
            tvOther.setVisibility(View.GONE);*//*
           // type = "tuniuscenic";
            tvOrdernoTitle.setText(scenicName);
            tvOrderName.setText(productname);
            tvCount.setText("门票数量：" + mRoomCount + "张");
            tvOrderTime.setText("出游时间" + mCheckInDate);
            tvName.setText("取票人：" + mCheckName);
            tvPhone.setText("手机号：" + mCheckPhone);
        }
        tvOrderNumber.setText(orderNo);
        tvOrderPrice.setText("订单总额¥" + mTotalPrice);
       *//* tvName.setText("姓　　名：" + mCheckName);
        tvPhone.setText("手　　机：" + mCheckPhone);
        tvTotalPrice.setText(Html.fromHtml("订单金额：<font color=\"#009A44\">¥" + mTotalPrice + "</font>"));*//*


    }*/

    private void showApplyRefundDialog() {
        CustomDialog.Builder callDialog = new CustomDialog.Builder(this);
        callDialog.setMessage("是否确定申请退款");
        callDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                applyRefund();
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
                    Toast.makeText(EndOrderDetailActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
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

  /*  @OnClick({R.id.iv_back, R.id.tv_return_ticket})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_return_ticket:
                showApplyRefundDialog();
                break;
        }
    }
*/
  @OnClick({R.id.iv_back, R.id.tv_order_comment})
  public void onClick(View view) {
      switch (view.getId()) {
          case R.id.iv_back:
              finish();
              break;
          case R.id.tv_order_comment:
              goToComment();
              break;
      }
  }

    private void goToComment() {
        Intent intent = new Intent();
        intent.setClass(EndOrderDetailActivity.this, CommentDetailActivity.class);
        intent.putExtra("userid", userid);
        intent.putExtra("type", type);
        intent.putExtra("orderno", orderNo);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 101) {
            tvOrderComment.setVisibility(View.GONE);
        }
    }


}
