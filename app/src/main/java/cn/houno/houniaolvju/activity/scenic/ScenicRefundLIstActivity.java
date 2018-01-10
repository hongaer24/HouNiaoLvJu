package cn.houno.houniaolvju.activity.scenic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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
import cn.houno.houniaolvju.adapter.ScenicRefundListAdapter;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerListView;

public class ScenicRefundLIstActivity extends Activity implements ScenicRefundListAdapter.CheckInterface {



    @Bind(R.id.rl_top_bar)
    RelativeLayout rlTopBar;
    @Bind(R.id.tv_orderno_title)
    TextView tvOrdernoTitle;
    @Bind(R.id.tv_order_price)
    TextView tvOrderPrice;
    @Bind(R.id.tv_order_money)
    TextView tvOrderMoney;
    @Bind(R.id.tv_refund_price)
    TextView tvRefundPrice;
    @Bind(R.id.tv_refund_money)
    TextView tvRefundMoney;
    @Bind(R.id.lv_passengers)
    InnerListView lvPassengers;
    @Bind(R.id.sv_order_detail)
    ScrollView svOrderDetail;
    @Bind(R.id.btn_refund)
    Button btnRefund;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    private String userid;
    private String orderNo;
    private String[] refundType = {"行程变更", "价格不优惠", "门票预订错误", "未收到入园凭证", "景区闭园", "其他原因"};
    private ScenicRefundListAdapter mAdapter;
    private ScenicRefundLIstActivity mAcitivity;
    private int causeType;
    private String causeContent;
    private ScenicRefundLIstActivity mActivity;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideWindows();
        setContentView(R.layout.activity_scenic_refund_list);
        mAcitivity = ScenicRefundLIstActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }


    private void initData() {
        userid = PrefUtils.getString(ScenicRefundLIstActivity.this, "userid", "");
        Intent intent = getIntent();
        orderNo = intent.getStringExtra("orderno");
        price = intent.getStringExtra("price");
        tvOrderMoney.setText("¥"+price);
        tvRefundMoney.setText("¥"+price);
        if (mAdapter == null) {
            mAdapter = new ScenicRefundListAdapter(mAcitivity, refundType);
            mAdapter.setCheckInterface(this);
            lvPassengers.setAdapter(mAdapter);
        } else {
            mAdapter.setDatas(refundType);
        }


    }

    private void initEvent() {
        btnRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyRefund();
                finish();

            }
        });

    }

    private void hideWindows() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void CheckType(int position, String type) {
        causeType = position;
        causeContent = type;
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
        params.addBodyParameter("causeType", causeType + "");
        params.addBodyParameter("causeContent", causeContent);

        Log.e("hotel_refund", "userid=" + userid + "&orderno=" + orderNo);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject json = new JSONObject(result);

                    String msg = json.getString("msg");
                  /*  if (status == 0) {
                        //tvRefund.setVisibility(View.GONE);
                        //tvOrderStatus.setText("已支付/退款中");
                    }*/
                    Toast.makeText(ScenicRefundLIstActivity.this, msg, Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
