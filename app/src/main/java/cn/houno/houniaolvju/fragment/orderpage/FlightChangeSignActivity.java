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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.OrderListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 机票改签
 * Created by qzc on 2017/2/23.
 */

public class FlightChangeSignActivity extends Activity {

    @Bind(R.id.tv_change_reason)
    TextView tvChangeReason;
    @Bind(R.id.tv_change_date)
    TextView tvChangeDate;
    @Bind(R.id.et_other)
    EditText etOther;
    @Bind(R.id.btn_submit)
    TextView btnSubmit;

    private FlightChangeSignActivity mActivity;
    private String userid;
    private OrderListBean.DataBean dataBean;

    private AlertDialog.Builder builder;
    private String reason;
    private int selectNumber = 0;
    private int clickNumber = 0;

    private String mCheckIn;    //机票日期
    private String mDepTime;
    private int mCheckYear;
    private int mCheckMonth;
    private int mCheckDay;
    private int mCheckHour;
    private int mCheckMinute;
    private String changedStr = ""; //改签日期


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtils.setWindowStatusBarColor(FlightChangeSignActivity.this, R.color.app_theme_green);
        setContentView(R.layout.activity_flight_change_sign);
        mActivity = FlightChangeSignActivity.this;
        ButterKnife.bind(this);
        initData();
    }


    private void initData() {
        userid = PrefUtils.getString(mActivity, "userid", "");
        Intent intent = getIntent();
        dataBean = (OrderListBean.DataBean) intent.getSerializableExtra("data");
        mCheckIn = dataBean.getCheckin();   //2017-06-20
        mDepTime = dataBean.getDetail().getDeptime();   //1430
        String[] checkInSplit = mCheckIn.split("-");
        mCheckYear = Integer.parseInt(checkInSplit[0]);
        mCheckMonth = Integer.parseInt(checkInSplit[1]);
        mCheckDay = Integer.parseInt(checkInSplit[2]);
        mCheckHour = Integer.parseInt(mDepTime.substring(0, 2));
        mCheckMinute = Integer.parseInt(mDepTime.substring(2));
    }

    @OnClick({R.id.iv_back, R.id.ll_change_reason, R.id.ll_change_date, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_change_reason:
                showChangeReasonDialog();
                break;
            case R.id.ll_change_date:
                showChangeDateDialog();
                break;
            case R.id.btn_submit:
                if (TextUtils.equals(tvChangeReason.getText().toString(), "请选择改签原因")) {
                    Toast.makeText(mActivity, "请选择改签原因", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.equals(tvChangeDate.getText().toString(), "请选择改签日期")) {
                    Toast.makeText(mActivity, "请选择改签日期", Toast.LENGTH_SHORT).show();
                } else {
                    getDataFromServer();
                }
                break;
        }
    }

    private void getDataFromServer() {

        btnSubmit.setClickable(false);
        btnSubmit.setText("提交中");
        Map<String, String> params = new HashMap<>();
        params.put("userid", userid);
        params.put("orderno", dataBean.getOrderno());
        params.put("reason", selectNumber + 1 + "");
        params.put("date", changedStr);
        params.put("remark", etOther.getText().toString());
        System.out.println(params);

        OkHttpClientManager.postAsync(Constants.FLIGHT_CHANGE, params, null, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                btnSubmit.setClickable(true);
                switch (msg.what) {
                    case R.id.doSucceed:
                        try {
                            JSONObject object = new JSONObject(msg.obj.toString());
                            System.out.println(msg.obj.toString());
                            int status = object.getInt("status");
                            if (status == 0) {
                                setResult(RESULT_OK);
                                finish();
                            } else {
                                Toast.makeText(mActivity, object.getString("msg"), Toast.LENGTH_SHORT).show();
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


    /**
     * 改签原因
     */
    private void showChangeReasonDialog() {
        final String items[] = {"我要改变行程计划、我要改航班", "选错日期、选错航班", "航班延误或取消、航班时刻变更"};
        builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择"); //设置标题
        builder.setSingleChoiceItems(items, selectNumber, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clickNumber = which;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                selectNumber = clickNumber;
                reason = items[selectNumber];
                tvChangeReason.setText(reason);
            }
        });
        builder.create().show();
    }

    /**
     * 改签日期
     */
    private void showChangeDateDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_date_time_picker, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.dp);
        final TimePicker timePicker = (TimePicker) view.findViewById(R.id.tp);
        datePicker.init(mCheckYear, mCheckMonth - 1, mCheckDay, null);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(mCheckHour);
        timePicker.setCurrentMinute(mCheckMinute);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String year = datePicker.getYear() + "";
                String month = formatPickerDate(datePicker.getMonth() + 1);
                String day = formatPickerDate(datePicker.getDayOfMonth());
                String date = year + "-" + month + "-" + day;
                String time = formatPickerDate(timePicker.getCurrentHour())
                        + ":" + formatPickerDate(timePicker.getCurrentMinute());
                changedStr = date + " " + time;
                tvChangeDate.setText(changedStr);
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String formatPickerDate(int dateInt) {
        if (dateInt < 10) {
            return "0" + dateInt;
        } else {
            return dateInt + "";
        }
    }
}
