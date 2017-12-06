package cn.houno.houniaolvju.activity.scenic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.OrderDetailActivity;
import cn.houno.houniaolvju.fragment.orderpage.IngOrderPager;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：景点-填写订单
 * 创建人：qzc
 * 创建时间：2017/1/6 14:09
 * 修改人：qzc
 * 修改时间：2017/1/6 14:09
 * 修改备注：
 */
public class FillInScenicOrderActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.tv_topbar_title)
    TextView mTvTopbarTitle;
    @Bind(R.id.iv_home)
    ImageView mIvHome;
    @Bind(R.id.ly_top_bar)
    RelativeLayout mLyTopBar;
    @Bind(R.id.tv_scenic_order_title)
    TextView mTvScenicOrderTitle;
    @Bind(R.id.tv_scenic_order_address)
    TextView mTvScenicOrderAddress;
    @Bind(R.id.tv_scenic_order_price)
    TextView mTvScenicOrderPrice;
    @Bind(R.id.ll_scenic_order_price)
    LinearLayout mLlScenicOrderPrice;
    @Bind(R.id.btn_refer_sub)
    Button mBtnReferSub;
    @Bind(R.id.tv_refer_num)
    TextView mTvReferNum;
    @Bind(R.id.btn_refer_add)
    Button mBtnReferAdd;
    @Bind(R.id.tv_yw)
    TextView mTvYw;
    @Bind(R.id.tv_days_selector)
    TextView mTvDaysSelector;
    @Bind(R.id.tv_yk)
    TextView mTvYk;
    @Bind(R.id.et_jd_name)
    EditText mEtJdName;
    @Bind(R.id.tv_jd_phone)
    TextView mTvJdPhone;
    @Bind(R.id.et_jd_phone)
    EditText mEtJdPhone;
    @Bind(R.id.tv_zxzf)
    TextView mTvZxzf;
    @Bind(R.id.tv_zxzf_money)
    TextView mTvZxzfMoney;
    @Bind(R.id.btn_jd_tjdd)
    Button mBtnJdTjdd;
    @Bind(R.id.tv_number_person)
    TextView tvNumberPerson;
    @Bind(R.id.iv_number_person)
    ImageView ivNumberPerson;

    private FillInScenicOrderActivity mActivity;
    private String userid;

    private int intTicketNum = 1;
    private int ticketNumEst = 1000;
    private int price;  //单价
    private int allPrice;    //总价
    private String mScenicTitle;
    private String mScenicAddress;
    private String mTicketTitle;
    private String mScenicId;
    private String mTicketId;
    private String mCheckDate;
    private String mCheckWeek;

    private ProgressDialog mProgressDialog;

    private boolean isLogined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_fillin_scenic_order);
        ButterKnife.bind(this);
        mActivity = FillInScenicOrderActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

    }

    private void initEvent() {
        mBtnReferSub.setOnClickListener(new TicketClick());
        mBtnReferAdd.setOnClickListener(new TicketClick());
    }

    private void initData() {
        isLogined = PrefUtils.getBoolean(mActivity, "isLogined", false);
        if (isLogined) {
            userid = PrefUtils.getString(mActivity, "userid", "");
            mEtJdName.setText(PrefUtils.getString(mActivity, "nick", ""));
            mEtJdPhone.setText(PrefUtils.getString(mActivity, "mobile", ""));
        } else {
            userid = Constants.PUBLIC_USER_ID;
        }

        Intent intent = getIntent();
        mScenicTitle = intent.getStringExtra("scenicTitle");
        mScenicAddress = intent.getStringExtra("scenicAddress");
        mTicketTitle = intent.getStringExtra("ticketTitle");
        mScenicId = intent.getStringExtra("sid");
        mTicketId = intent.getStringExtra("tid");
        price = Integer.parseInt(intent.getStringExtra("price"));

        mTvTopbarTitle.setText(mScenicTitle);
        mTvScenicOrderTitle.setText(mTicketTitle);
        mTvScenicOrderAddress.setText(mScenicAddress);

        MyText2Utils.formatTicketPrice(mActivity, mTvScenicOrderPrice, price + "");

        allPrice = price;
        MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, allPrice + "");
        mCheckDate = DateUtil.getNowTime(DateUtil.DATE_SMALL_STR);
        mCheckWeek = DateUtil.getWeek(mCheckDate);
        mTvDaysSelector.setText(mCheckDate + " " + mCheckWeek);
    }

    @OnClick({R.id.iv_back, R.id.iv_home, R.id.tv_days_selector, R.id.btn_jd_tjdd,R.id.iv_number_person})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_home:
                //返回主页面
                Intent intent = new Intent(mActivity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_days_selector:
                Intent date = new Intent();
                date.putExtra("from", "scenic");
                date.setClass(mActivity, SingleDatePickerActivity.class);
                startActivityForResult(date, 301);
                break;
            case R.id.btn_jd_tjdd:
                checkInputAndGetData();
                break;
            case R.id.iv_number_person:
                Intent intent1 = new Intent(this, PersonsListActivity.class);
                intent1.putExtra("persons", intTicketNum);
                startActivity(intent1);
                finish();
                break;
        }
    }

    private void checkInputAndGetData() {
        if (TextUtils.isEmpty(mEtJdName.getText().toString().trim())) {
            Toast.makeText(mActivity, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isTel = true;
        //判断输入的用户名是否是电话号码
        if (mEtJdPhone.getText().toString().length() == 11) {
            for (int i = 0; i < mEtJdPhone.getText().toString().length(); i++) {
                char c = mEtJdPhone.getText().toString().charAt(i);
                if (!Character.isDigit(c)) {
                    isTel = false;
                    break;//只要有一位不符合要求退出循环
                }
            }
        } else {
            isTel = false;
        }

        if (TextUtils.isEmpty(mEtJdPhone.getText())) {
            Toast.makeText(mActivity, "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else if (!isTel) {
            Toast.makeText(mActivity, "手机号格式不对", Toast.LENGTH_SHORT).show();
        } else {
            Log.i("FillInScenicOrderAct", "success");
            getDataFromServer();
        }
    }

    private void getDataFromServer() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在提交订单");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        //Map<String, String> params = new HashMap<>();
        RequestParams params = new RequestParams(Constants.SCENIC_ORDER_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("info[productId]",  mTicketId);
        params.addBodyParameter("info[startTime]","2017-12-06");
        params.addBodyParameter("info[productname]", mTicketTitle);
        params.addBodyParameter("contact[tel]", mEtJdPhone.getText().toString().trim());
        params.addBodyParameter("contact[name]", mEtJdName.getText().toString().trim());
        params.addBodyParameter("info[bookNumber]", mTvReferNum.getText().toString().trim());
        params.addBodyParameter("info[totalprice]",allPrice+"");


        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");

                    if (status == 0) {
                         Log.i("0101", "result==="+result);
                         Log.i("0101", "result==="+ obj.getString("orderno"));
                        Intent intent = new Intent();
                        intent.putExtra("type", "tuniuscenic");
                        intent.putExtra("price", allPrice);
                        intent.putExtra("orderno", obj.getString("orderno"));
                        intent.putExtra("title", mScenicTitle + " - " + mTicketTitle);
                        intent.setClass(mActivity, OrderDetailActivity.class);
                        startActivity(intent);
                        //parseData(result);


                    } else {
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
               /* pbLoading.setVisibility(View.GONE);
                tvLoading.setText("加载失败");*/
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                //rfvScenicDetail.stopRefresh();
            }
        });

        // params.put("info[checkin]", mCheckDate);

      /*  OkHttpClientManager.postAsync(Constants.SCENIC_ORDER_URL, params, null, new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
                switch (msg.what) {
                    case R.id.doSucceed:
                        String succeedResult = msg.obj.toString();
                        try {
                            JSONObject obj = new JSONObject(succeedResult);
                            Log.i("0101", "id===== "+obj.getInt("status"));
                            if (obj.getInt("status") == 0) {
                                Intent intent = new Intent();
                                intent.putExtra("type", "scenic");
                                intent.putExtra("orderno", obj.getString("orderno"));
                                intent.putExtra("title", mScenicTitle + " - " + mTicketTitle);
                                //intent.putExtra("price", allPrice);
                                IngOrderPager.refresh = true;
                                intent.setClass(mActivity, OrderDetailActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                                //没有酒店数据
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.doFail:
                        break;
                }
            }
        }, R.id.doSucceed, R.id.doFail);*/
    }

    private class TicketClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.btn_refer_sub) {
                if (intTicketNum > 1) {
                    intTicketNum--;
                    mTvReferNum.setText(intTicketNum + "");
                    tvNumberPerson.setText("出游人（需要填写"+intTicketNum+"个出游人）");
                }
            } else if (i == R.id.btn_refer_add) {
                if (intTicketNum < ticketNumEst) {
                    intTicketNum++;
                    mTvReferNum.setText(intTicketNum + "");
                    tvNumberPerson.setText("出游人（需要填写"+intTicketNum+"个出游人）");
                }
            }
            allPrice = price * intTicketNum;
            MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, allPrice + "");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 300 && requestCode == 301) {
            Bundle extras = data.getExtras();
            mCheckDate = extras.getString("dateIn");
            mCheckWeek = extras.getString("dateInWeek");
            mTvDaysSelector.setText(mCheckDate + " " + mCheckWeek);

        }
    }
}
