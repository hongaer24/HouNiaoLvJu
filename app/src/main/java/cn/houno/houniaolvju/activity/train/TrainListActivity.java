package cn.houno.houniaolvju.activity.train;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.StatusBarUtils;


/**
 * 火车列表页
 */
public class TrainListActivity extends Activity {
    //出发城市
    @Bind(R.id.tv_dep_name)
    TextView tvDepCity;

    //到达城市
    @Bind(R.id.tv_arr_name)
    TextView tvArrCity;

    //出发日期
    @Bind(R.id.tv_date)
    TextView tvDate;

    //列表项
    @Bind(R.id.lv_train_list)
    ListView listView;

    @Bind(R.id.rfv_flight_list)
    XRefreshView xRefreshView;

    //请求失败或没数据时显示的布局
    @Bind(R.id.ll_fail)
    LinearLayout linearLayout;

    @Bind(R.id.tv_back_home)
    TextView tvBackHome;

    @Bind(R.id.iv_back)
    ImageView ivBack;

    String date;
    private TrainCityBean depCity, arrCity;

    private List<TrainTicketBean> trainTicketList;

    MyHandler myHandler;
    private static final int REQUEST_CODE_FROM_LIST = 5555;

    private static final int REQUEST_FAIL = 1;
    private static final int REQUEST_SUCCESS = 2;
    private static final int PARSE_JSON_FAIL = 3;

    TrainListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("TAG", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(TrainListActivity.this, R.color.app_theme_green);
        initData();
        initEvent();
        xRefreshView.startRefresh();
    }

    private void initData() {
        myHandler = new MyHandler(this);
        Intent intent = getIntent();
        if (intent != null) {
            depCity = ((TrainCityBean) intent.getSerializableExtra("depCity"));
            arrCity = ((TrainCityBean) intent.getSerializableExtra("arrCity"));
            date = intent.getStringExtra("date");
        }
        if (!TextUtils.isEmpty(depCity.getName())) {
            tvDepCity.setText(depCity.getName());
        }
        if (!TextUtils.isEmpty(arrCity.getName())) {
            tvArrCity.setText(arrCity.getName());
        }
        if (!TextUtils.isEmpty(date)) {
            tvDate.setText(date + " " + DateUtil.getEWeek(date));

        }
        System.out.println("查询的城市信息：" + depCity.getName() + "," + depCity.getCode() + "," + arrCity.getName() + "," + arrCity.getCode());
    }

    private void initEvent() {
        xRefreshView.setPullLoadEnable(false);
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                Log.e("TAG", "onRefresh");
                getDataFromServer();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                Log.e("TAG", "onLoadMore");
//                xRefreshView.stopRefresh();
            }

            @Override
            public void onRelease(float direction) {
                Log.e("TAG", "onRelease");
            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {
                Log.e("TAG", "onHeaderMove");
            }
        });

    }

    private void getDataFromServer() {
        Log.e("TAG", depCity.getName());
        Log.e("TAG", arrCity.getName());
        Log.e("TAG", date);
        RequestParams params = new RequestParams(Constants.TRAIN_LIST);
        params.addBodyParameter("dep_city", depCity.getCode());
        params.addBodyParameter("arr_city", arrCity.getCode());
        params.addBodyParameter("dep_date", date.replaceAll("-", ""));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        //开启一个线程解析数据
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                parseData(result);
                            }
                        }).start();
                    } else {
                        Toast.makeText(TrainListActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                        xRefreshView.stopRefresh();

                        Message message = new Message();
                        message.arg1 = REQUEST_FAIL;
                        myHandler.sendMessage(message);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError");
                xRefreshView.stopRefresh();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled");
                xRefreshView.stopRefresh();
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished");
            }
        });
    }


    @OnClick({R.id.iv_back, R.id.tv_back_home, R.id.tv_left, R.id.tv_right, R.id.rl_middle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
            case R.id.tv_back_home:
                finish();
                break;
            case R.id.tv_left: {
                if (xRefreshView.mPullRefreshing) {
                    return;
                }
                if (!DateUtil.compareDate(DateUtil.getNowTime(), date)) {
                    Toast.makeText(this, "选择的日期不能早于今天", Toast.LENGTH_SHORT).show();
                    return;
                }
                date = DateUtil.getYesterdayTime(date);
                tvDate.setText(date + " " + DateUtil.getEWeek(date));
                xRefreshView.startRefresh();
                listView.setAdapter(null);
                break;
            }
            case R.id.tv_right: {
                if (xRefreshView.mPullRefreshing) {
                    return;
                }
                date = DateUtil.getTomorrowTime(date);
                tvDate.setText(date + " " + DateUtil.getEWeek(date));
                xRefreshView.startRefresh();
                listView.setAdapter(null);
                break;
            }

            case R.id.rl_middle: {
                startActivityForResult(new Intent(this, TrainDatePickerActivity.class), REQUEST_CODE_FROM_LIST);
                break;
            }
            default:
                break;
        }
    }


    private void parseData(String result) {
        Gson gson = new Gson();
        Message message = new Message();
        try {
            String jsonStr = new JsonParser().parse(result).getAsJsonObject().get("data").toString();
            trainTicketList = gson.fromJson(jsonStr, new TypeToken<List<TrainTicketBean>>() {
            }.getType());
            message.arg1 = REQUEST_SUCCESS;
            myHandler.sendMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            message.arg1 = PARSE_JSON_FAIL;
            myHandler.sendMessage(message);
        }
    }


    private class MyHandler extends Handler {
        private final WeakReference<TrainListActivity> mActivity;

        public MyHandler(TrainListActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.arg1 == REQUEST_SUCCESS) {
                Log.e("TAG", "请求成功");
                if (trainTicketList != null && trainTicketList.size() > 0) {
                    listAdapter = new TrainListAdapter(TrainListActivity.this, trainTicketList, date);
                    linearLayout.setVisibility(View.GONE);
                    listView.setAdapter(listAdapter);
                    xRefreshView.stopRefresh();
                }
            } else {
                if (msg.arg1 == REQUEST_FAIL) {
                    Log.e("TAG", "请求失败");
                } else {
                    Log.e("TAG", "解析json出错");
                }
            }
        }

    }


    //选择日期后的回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("TAG", "回调");
        if (data != null && requestCode == REQUEST_CODE_FROM_LIST) {
            linearLayout.setVisibility(View.GONE);
            date = data.getStringExtra("date");
            tvDate.setText(date + " " + DateUtil.getEWeek(date));
            listView.setAdapter(null);
            xRefreshView.startRefresh();
        }
    }

}
