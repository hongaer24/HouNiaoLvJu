package cn.houno.houniaolvju.fragment.orderpage;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_order_detail_end);
        ButterKnife.bind(this);
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


    public void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.ORDER_DETAIL_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("type", type);
        params.addBodyParameter("orderno", orderNo);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                parserData(result);
                setText();
                setOrderStatus();
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
