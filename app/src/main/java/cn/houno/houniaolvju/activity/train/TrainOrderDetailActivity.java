package cn.houno.houniaolvju.activity.train;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.CustomDialog;



public class TrainOrderDetailActivity extends Activity {

    //订单支付状态
    @Bind(R.id.tv_pay_status)
    TextView tvPayStatus;

    @Bind(R.id.tv_order_title_txt)
    TextView tvTitle;

    //开车时间 HH:mm
    @Bind(R.id.tv_dep_time)
    TextView tvDepTime;
    //列车运行时间  xx小时xx分钟
    @Bind(R.id.tv_total_time)
    TextView tvTotalTime;

    //列车到目的站时间 HH:mm
    @Bind(R.id.tv_arr_time)
    TextView tvArrTime;

    //上车站
    @Bind(R.id.tv_dep_station)
    TextView tvDepStation;

    //目的站
    @Bind(R.id.tv_arr_station)
    TextView tvArrStation;

    //订单总价格
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;

    //订单号
    @Bind(R.id.tv_orderno)
    TextView tvOrder;

    //乘车人列表
    @Bind(R.id.lv_passengers)
    ListView listView;

    //联系手机
    @Bind(R.id.tv_phone)
    TextView tvPhone;

    //立即支付
    @Bind(R.id.tv_pay)
    TextView tvPay;
    //取消订单
    @Bind(R.id.tv_order_cancel)
    TextView tvOrderCancel;
    //退票
    @Bind(R.id.tv_return_ticket)
    TextView tvReturnTicket;

    @Bind(R.id.ll_detail_content)
    LinearLayout llDetailContent;

    @Bind(R.id.ll_loading)
    LinearLayout llLoading;

    @Bind(R.id.tv_loading)
    TextView tvLoading;

    private String orderno;

    TrainOrderBean trainOrderBean;

    MyHandler myHandler;

    String action;//用于标志是申请退票或是取消订单

    String orderStatus;
    private static final int GET_DETAIL_SUCCESS = 00;
    private static final int GET_DETAIL_FAIL = 11;
    private static final int PERFORM_SUCCESS = 22;//取消订单或申请退票成功
    private static final int PERFORM_FAIL = 33;//取消订单或申请退票失败


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_order_detail);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(TrainOrderDetailActivity.this, R.color.app_theme_green);
        initView();
        initData();

    }

    private void getDataFromServer(String url, String orderno, String userid, String type) {
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("orderno", orderno);
        if (!TextUtils.isEmpty(type)) {
            params.addBodyParameter("type", type);
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                Message msg;
                System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result);
                    } else {
                        msg = new Message();
                        msg.what = PERFORM_FAIL;
                        msg.obj = obj.getString("msg");
                        Log.e("xxxx", msg.obj.toString());
                        myHandler.sendMessage(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    msg = new Message();
                    msg.what = GET_DETAIL_FAIL;
                    myHandler.sendMessage(msg);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError");
                System.out.println(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled");
                System.out.println(cex);
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished");
            }
        });
    }

    private void initView() {
    }


    private void initData() {
        myHandler = new MyHandler(this);
        Intent intent = getIntent();
        if (intent != null) {
            orderno = intent.getStringExtra("orderno");
            if (!TextUtils.isEmpty(orderno)) {
                getDataFromServer(Constants.TRAIN_ORDER_DETAIL, orderno, PrefUtils.getString(this, "userid", ""), "train");
            }
        }else{
            llLoading.setVisibility(View.GONE);
            Toast.makeText(this,"请求失败",Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick({R.id.iv_back, R.id.tv_pay, R.id.tv_order_cancel, R.id.tv_return_ticket})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back: {
                finish();
                break;
            }


            //立即支付
            case R.id.tv_pay: {
                payOrder();
                break;
            }

            //取消订单
            case R.id.tv_order_cancel: {
                action = "order_cancel";
                tvLoading.setText("正在取消订单...");
                showDialogForOrderCancel();
                break;
            }

            //申请退票
            case R.id.tv_return_ticket: {
                action = "return_ticket";
                tvLoading.setText("正在申请退票...");
                showDialogForReturnTicket();
                break;
            }
            default:
                break;
        }
    }


    private void payOrder() {
        Intent intent = new Intent(this, TrainOrderPayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("orderBean", trainOrderBean);
        intent.putExtra("isFromDetail", true);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void parseData(String result) {
        Log.e("TAG", "进入parseData");
        Gson gson = new Gson();
        Message msg = new Message();
        try {
            Log.e("TAG", "开始解析json");
            JsonElement jsonElement = new JsonParser().parse(result).getAsJsonObject().get("data");
            if (jsonElement != null) {
                Log.e("xxx", "jsonElement不为空");
                trainOrderBean = gson.fromJson(jsonElement.toString(), TrainOrderBean.class);
                if (trainOrderBean != null) {
                    msg.what = GET_DETAIL_SUCCESS;
                    myHandler.sendMessage(msg);
                }
            } else {
                msg.what = PERFORM_SUCCESS;
                myHandler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "解析json出错");
            msg.what = GET_DETAIL_FAIL;
            myHandler.sendMessage(msg);
        }

    }

    private void showDialogForReturnTicket() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_train_refund_statement, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.show();
        view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                llDetailContent.setVisibility(View.GONE);
                llLoading.setVisibility(View.VISIBLE);
                getDataFromServer(Constants.TRAIN_RETURN_TICKET, orderno, PrefUtils.getString(TrainOrderDetailActivity.this, "userid", ""), "");
            }
        });

        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


    private class MyHandler extends Handler {
        private final WeakReference<TrainOrderDetailActivity> mActivity;

        public MyHandler(TrainOrderDetailActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == GET_DETAIL_SUCCESS) {
                Log.e("xxxx", "get-detail-success");
                setData();
            } else if (msg.what == GET_DETAIL_FAIL) {
                Log.e("xxxx", "get-detail-fail");
                llLoading.setVisibility(View.GONE);
                llDetailContent.setVisibility(View.VISIBLE);
                Toast.makeText(TrainOrderDetailActivity.this, "获得订单详情失败", Toast.LENGTH_SHORT).show();
            } else if (msg.what == PERFORM_SUCCESS) {
                llDetailContent.setVisibility(View.GONE);
                llLoading.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getDataFromServer(Constants.TRAIN_ORDER_DETAIL, orderno, PrefUtils.getString(TrainOrderDetailActivity.this, "userid", ""), "train");
                    }
                }).start();

            } else {
                llLoading.setVisibility(View.GONE);
                llDetailContent.setVisibility(View.VISIBLE);
                Toast.makeText(TrainOrderDetailActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void setData() {
        String status = trainOrderBean.getStatus();
        String payStatus = trainOrderBean.getPay_status();
        tvPayStatus.setText(trainOrderBean.getReturnMessage());
        System.out.println(trainOrderBean.getReturnMessage());
        if (payStatus.equals("0") && !status.equals("1")) {
            tvPay.setVisibility(View.VISIBLE);
            tvOrderCancel.setVisibility(View.VISIBLE);
        } else if (payStatus.equals("1") && (status.equals("3") || status.equals("4"))) {
            tvReturnTicket.setVisibility(View.VISIBLE);
        } else {
            tvPay.setVisibility(View.GONE);
            tvOrderCancel.setVisibility(View.GONE);
            tvReturnTicket.setVisibility(View.GONE);
        }


        String trainCode = trainOrderBean.getTrain_code();//列车车次
        String date = trainOrderBean.getTrain_date();


        tvTitle.setText(trainCode + "   " + date + "  (" + DateUtil.getEWeek(date) + ")");

        trainOrderBean.setWeek(DateUtil.getEWeek(date));


        Log.e("pppp", trainOrderBean.getStart_time());
        String depTime = trainOrderBean.getStart_time().split(" ")[1];//列车开点
        Log.e("pppp", depTime.substring(0, 5));
        tvDepTime.setText(depTime.substring(0, 5));

        Log.e("pppp", trainOrderBean.getArrive_time());
        String arrTime = trainOrderBean.getArrive_time().split(" ")[1];//列车到达时间
        Log.e("pppp", arrTime.substring(0, 5));
        tvArrTime.setText(arrTime.substring(0, 5));


        String runtime = trainOrderBean.getRun_time();

        tvTotalTime.setText(runtime.split(":")[0] + "小时" + runtime.split(":")[1] + "分钟");//历时


        tvDepStation.setText(trainOrderBean.getFrom_station_name());//上车站

        tvArrStation.setText(trainOrderBean.getTo_station_name());//下车站

        tvTotalPrice.setText("¥" + Double.parseDouble(trainOrderBean.getPrice()));//总价格

        orderno = trainOrderBean.getOrderno();
        tvOrder.setText(orderno);//订单号

        tvPhone.setText(MyText2Utils.replaceSubString(trainOrderBean.getLinkphone(),3,6));//联系手机

        llLoading.setVisibility(View.GONE);
        llDetailContent.setVisibility(View.VISIBLE);
        listView.setAdapter(new TrainPassengersAdapter(this, trainOrderBean.getPassengerInfo(), TrainPassengersAdapter.FROM_ORDER_DETAIL));
    }


    private void showDialogForOrderCancel() {
        CustomDialog.Builder callDialog = new CustomDialog.Builder(this);
        callDialog.setMessage("确定要取消订单吗？");
        callDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                llDetailContent.setVisibility(View.GONE);
                llLoading.setVisibility(View.VISIBLE);
                getDataFromServer(Constants.TRAIN_ORDER_CANCEL, orderno, PrefUtils.getString(TrainOrderDetailActivity.this, "userid", ""), "");
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }
}


