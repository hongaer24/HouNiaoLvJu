package cn.houno.houniaolvju.activity.train;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;


/**
 * 火车订单填写页
 */
public class TrainOrderFillinActivity extends Activity {

    //添加乘车人
    @Bind(R.id.tv_add_passengers)
    TextView tvAddPassengers;

    //列车车次
    @Bind(R.id.tv_train_code)
    TextView tvTrainCode;

    //出发日期
    @Bind(R.id.tv_ticket_date)
    TextView tvDate;

    //乘车站
    @Bind(R.id.tv_ticket_dep_station)
    TextView tvDepStation;

    //目的站
    @Bind(R.id.tv_ticket_arr_station)
    TextView tvArrStation;

    //开车时间 HH:mm
    @Bind(R.id.tv_ticket_dep_time)
    TextView tvDepTime;

    //到站时间 HH:mm
    @Bind(R.id.tv_ticket_arr_time)
    TextView tvArrTime;

    //列车运行时间
    @Bind(R.id.tv_ticket_total_time)
    TextView tvTotalTime;

    //订单总价格
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;

    //座位类型
    @Bind(R.id.tv_seat_type)
    TextView tvSeatName;

    //座位价格
    @Bind(R.id.tv_seat_price)
    TextView tvSeatPrice;

    //乘车人列表
    @Bind(R.id.lv_passengers)
    ListView listView;

    //提交订单
    @Bind(R.id.btn_submit_order)
    Button btnSubmit;

    //联系电话
    @Bind(R.id.et_phone)
    EditText etPhone;

    //是否接受无座
    @Bind(R.id.cb_agree)
    CheckBox cbAgree;

    TrainPassengersAdapter adapter;

    String mDate;//出发的日期
    String mSeatName;//选择的座位类型
    String mSeatPrice;//选择的座位价格

    String orderNo;//存储返回的订单号
    TrainTicketBean ticket;

    private ProgressDialog mProgressDialog;
    private MyHandler myHandler;

    private Map<String, String> map = new HashMap<>();

    private static final int ORDER_SUCCESS=0;
    private static final int ORDER_FAIL=1;


    //座位与代号按顺序对应
    private String[] seatType = new String[]{"硬座", "软座", "硬卧", "软卧", "高级软卧", "二等座", "一等座", "特等座", "商务座"};
    private String[] seatCode = new String[]{"1", "2", "3", "4", "6", "O", "M", "P", "9"};

    RequestParams params;

    TrainOrderBean orderBean;

    List<TrainPassengersBean> passengersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("My", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_order_fillin);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(TrainOrderFillinActivity.this, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {
        myHandler = new MyHandler(this);
        for (int i = 0; i < seatType.length; i++) {
            map.put(seatType[i], seatCode[i]);
        }
        Intent intent = getIntent();
        if (intent != null) {
            ticket = (TrainTicketBean) intent.getSerializableExtra("ticket");
//            passengersList= (List<TrainPassengersBean>) intent.getSerializableExtra("passengersList");
            if (ticket != null) {
                orderBean=new TrainOrderBean();
                if (!TextUtils.isEmpty(ticket.getTrain_code())) {
                    tvTrainCode.setText(ticket.getTrain_code());
                    orderBean.setTrain_code(ticket.getTrain_code());
                }
                if (!TextUtils.isEmpty(ticket.getFrom_station_name())) {
                    tvDepStation.setText(ticket.getFrom_station_name());
                    orderBean.setFrom_station_name(ticket.getFrom_station_name());
                }
                if (!TextUtils.isEmpty(ticket.getTo_station_name())) {
                    tvArrStation.setText(ticket.getTo_station_name());
                    orderBean.setTo_station_name(ticket.getTo_station_name());
                }
                if (!TextUtils.isEmpty(ticket.getRun_time())) {
                    tvTotalTime.setText(ticket.getRun_time().split(":")[0] + "时" + ticket.getRun_time().split(":")[1] + "分");
                }
                if (!TextUtils.isEmpty(ticket.getStart_time())) {
                    tvDepTime.setText(ticket.getStart_time());
                    orderBean.setStart_time(ticket.getStart_time());
                }
                if (!TextUtils.isEmpty(ticket.getArrive_time())) {
                    tvArrTime.setText(ticket.getArrive_time());
                    orderBean.setArrive_time(ticket.getArrive_time());
                }
            }


            mSeatName=intent.getStringExtra("seatName");
            if (!TextUtils.isEmpty(mSeatName)) {
                orderBean.setSeatName(mSeatName);
                tvSeatName.setText(mSeatName);
            }

            mSeatPrice=intent.getStringExtra("seatPrice");
            if (!TextUtils.isEmpty(mSeatPrice)) {
                orderBean.setSeatPrice(mSeatPrice);
                tvSeatPrice.setText("¥ " + mSeatPrice);
            }


            mDate=intent.getStringExtra("date");
            Log.e("出发日期",mDate);

            if (!TextUtils.isEmpty(mDate)) {
                orderBean.setTrain_date(mDate);
                orderBean.setWeek(DateUtil.getEWeek(mDate));
                tvDate.setText(mDate.split("-")[1] + "-" + mDate.split("-")[2] + " (" + DateUtil.getEWeek(mDate) + ")");
            }
        }

    }

    private void initView() {
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("price.change");
        registerReceiver(myReceiver,intentFilter);
        etPhone.setInputType(InputType.TYPE_CLASS_PHONE);
    }

    @OnClick({R.id.tv_add_passengers, R.id.iv_back, R.id.iv_home, R.id.btn_submit_order})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_passengers: {
                startActivity(new Intent(this, TrainPassengersSelectActivity.class));
                break;

            }
            case R.id.iv_back: {
                finish();
                break;
            }

            case R.id.iv_home: {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            }

            case R.id.btn_submit_order: {
                if(passengersList==null || passengersList.size()==0){
                    Toast.makeText(this,"请添加乘车人",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!InputCheckUtil.isValidPhoneNumber(etPhone.getText().toString())) {
                    Toast.makeText(this, "无效的手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                submitOrder();
                break;
            }
            default:
                break;
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("My", "onNewIntent");
        getDataAndSet(intent);
    }

    private void getDataAndSet(Intent intent) {
        if (intent != null) {
            passengersList = (List<TrainPassengersBean>) intent.getSerializableExtra("passengersList");
            if (passengersList.size() > 0) {
                adapter = new TrainPassengersAdapter(this, passengersList, TrainPassengersAdapter.FROM_ORDER_FILLIN);
                listView.setAdapter(adapter);
                tvTotalPrice.setText("¥" + passengersList.size() * Double.valueOf(mSeatPrice));
                if (cbAgree.isChecked()) {
                    btnSubmit.setBackgroundColor(Color.parseColor("#ff0000"));
                }
            } else {
                listView.setAdapter(null);
            }
        }
    }


    private void submitOrder() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在提交订单");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
       addPassengersDataToRequest();
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result);
                    } else {
                        Toast.makeText(TrainOrderFillinActivity.this,obj.getString("msg"),Toast.LENGTH_SHORT).show();
                        Message msg=new Message();
                        msg.arg1=ORDER_FAIL;
                        myHandler.sendMessageDelayed(msg,1000);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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


    private void parseData(String result) {
        Log.e("TAG", "进入parseData");
        Message msg=null;
        try {
            Log.e("TAG", "开始解析json");
            orderNo = new JsonParser().parse(result).getAsJsonObject().get("orderno").toString().replace("\"","");
            if (!TextUtils.isEmpty(orderNo)) {
                Log.e("TAG", "解析json完毕");
                msg=new Message();
                msg.arg1=ORDER_SUCCESS;
                myHandler.sendMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "解析json出错");
            msg.arg1=ORDER_FAIL;
            myHandler.sendMessage(msg);
        }
    }


    private class MyHandler extends Handler {
        private final WeakReference<TrainOrderFillinActivity> mActivity;

        public MyHandler(TrainOrderFillinActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }

            Log.e("msg.arg1",msg.arg1+"");
            if (msg.arg1 == ORDER_SUCCESS) {
                Log.e("TAG",orderBean.getTrain_date());
                orderBean.setPrice((tvTotalPrice.getText().toString()));
                orderBean.setOrderno(orderNo);
                orderBean.setLinkphone(etPhone.getText().toString());
                orderBean.setPassengerInfo(passengersList);
                Intent intent = new Intent(TrainOrderFillinActivity.this, TrainOrderPayActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("orderBean", (Serializable) orderBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }

    }


    private void addPassengersDataToRequest() {
        if (passengersList != null && passengersList.size() > 0) {
            params = new RequestParams(Constants.TRAIN_ORDER);
            params.addBodyParameter("userid", PrefUtils.getString(this,"userid",""));
            for (int i = 0; i < passengersList.size(); i++) {
                Log.e("smq", "============================");
                TrainPassengersBean passenger = passengersList.get(i);
                params.addBodyParameter("passengers[" + i + "][passengerid]", passenger.getId());
                Log.e("smq", "乘车人Id:"+passenger.getId());
                params.addBodyParameter("passengers[" + i + "][passengersename]", passenger.getPassengersename());
                Log.e("smq", "乘车人姓名:"+passenger.getPassengersename());
                params.addBodyParameter("passengers[" + i + "][piaotype]", passenger.getPiaotype());
                Log.e("smq","乘车人票类型:"+passenger.getPiaotype());
                params.addBodyParameter("passengers[" + i + "][passporttypeseid]", passenger.getPassporttypeseid());
                Log.e("smq", "乘车人证件Id:"+passenger.getPassporttypeseid());
                params.addBodyParameter("passengers[" + i + "][passportseno]", passenger.getPassportseno());
                Log.e("smq", "乘车人证件号:"+passenger.getPassportseno());
                params.addBodyParameter("passengers[" + i + "][price]", mSeatPrice);
                Log.e("smq", "乘车人票价格:"+mSeatPrice);



                //选择无座时的处理，因为无座没有对应的代码，如果用户选择无座就把是否接受无座强制设为yes；
                if(map.get(mSeatName)==null){
                    mSeatName=ticket.getSeat().get(0).getName();
                    params.addBodyParameter("passengers[" + i + "][zwname]", mSeatName);
                    params.addBodyParameter("passengers[" + i + "][zwcode]", map.get(mSeatName));
                    params.addBodyParameter("info[is_accept_standing]","yes");
                    Log.e("smq", "是否接受无座:yes");
                }else{
                    params.addBodyParameter("passengers[" + i + "][zwname]", mSeatName);
                    params.addBodyParameter("passengers[" + i + "][zwcode]", map.get(mSeatName));
                    params.addBodyParameter("info[is_accept_standing]",(cbAgree.isChecked()?"yes":"no"));
                    Log.e("smq", "是否接受无座:"+(cbAgree.isChecked()?"yes":"no"));
                }

                Log.e("smq", "乘车人座位类型名:"+mSeatName);
                Log.e("smq","乘车人座位类型对应的代号:"+ map.get(mSeatName));


            }
            Log.e("smq", "============================");
            params.addBodyParameter("info[price]", tvTotalPrice.getText().toString().replace("¥", ""));
            Log.e("smq", "总价格:"+tvTotalPrice.getText().toString().replace("¥", ""));
            params.addBodyParameter("info[linkmobile]", etPhone.getText().toString());
            Log.e("smq", "手机号:"+etPhone.getText().toString());

            params.addBodyParameter("info[from_station_name]", ticket.getFrom_station_name());
            Log.e("smq", "出发车站名:"+ticket.getFrom_station_name());
            params.addBodyParameter("info[to_station_name]", ticket.getTo_station_name());
            Log.e("smq", "到达车站名:"+ticket.getTo_station_name());
            params.addBodyParameter("info[from_station_code]", ticket.getFrom_station_code());
            Log.e("smq", "出发车站三字码:"+ticket.getFrom_station_code());
            params.addBodyParameter("info[to_station_code]", ticket.getTo_station_code());
            Log.e("smq", "到达车站三字码:"+ticket.getTo_station_code());
            params.addBodyParameter("info[train_date]", mDate);
            Log.e("smq", "乘车日期:"+ticket.getTrain_start_date());
            params.addBodyParameter("info[train_code]", ticket.getTrain_code());
            Log.e("smq", "列车车次:"+ticket.getTrain_code());
            params.addBodyParameter("info[run_time]", ticket.getRun_time());
            Log.e("smq", "列车运行时间:"+ticket.getRun_time());
            params.addBodyParameter("info[arrive_days]", ticket.getArrive_days());
            Log.e("smq", "列车运行天数:"+ticket.getArrive_days());
            params.addBodyParameter("info[start_time]", ticket.getStart_time());
            Log.e("smq", "列车出发时间:"+ticket.getStart_time());
            params.addBodyParameter("info[arrive_time]", ticket.getArrive_time());
            Log.e("smq", "列车到达时间"+ticket.getArrive_time());

        }
    }


    private BroadcastReceiver myReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("price.change")){
                tvTotalPrice.setText("¥" + passengersList.size() * Double.valueOf(mSeatPrice));
            }
        }

    };



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
        myHandler.removeCallbacksAndMessages(null);
        myReceiver=null;
        myHandler=null;
    }
}
