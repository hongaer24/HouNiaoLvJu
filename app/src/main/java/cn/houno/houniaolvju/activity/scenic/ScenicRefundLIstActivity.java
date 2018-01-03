package cn.houno.houniaolvju.activity.scenic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.OrderDetailActivity;
import cn.houno.houniaolvju.adapter.ScenicRefundListAdapter;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.view.InnerListView;

public class ScenicRefundLIstActivity extends AppCompatActivity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
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
    private String userid;
    private String orderNo;
    private String[] refundType = {"行程变更", "价格不优惠", "门票预订错误", "未收到入园凭证", "景区闭园", "其他原因"};
    private ScenicRefundListAdapter mAdapter;
    private ScenicRefundLIstActivity mAcitivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_refund_list);
        ButterKnife.bind(this);
        mAcitivity = ScenicRefundLIstActivity.this;
        initData();
        initEvent();
    }


    private void initData() {
        userid = PrefUtils.getString(ScenicRefundLIstActivity.this, "userid", "");
       /* Intent intent = getIntent();
        orderNo = intent.getStringExtra("orderno");*/
        if (mAdapter == null) {
            mAdapter = new ScenicRefundListAdapter(mAcitivity,refundType);
             lvPassengers.setAdapter(mAdapter);
        }else {
            mAdapter.setDatas(refundType);
        }


    }

    private void initEvent() {


    }
/*
    private void applyRefund() {
        final ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在提交退款申请");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
        RequestParams params = new RequestParams(Constants.APPLY_REFUND);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("orderno", orderNo);
        params.addBodyParameter("causeType", causeType);
        params.addBodyParameter("causeContent", causeContent);

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
                    Toast.makeText(ScenicRefundLIstActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
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
    }*/
}
