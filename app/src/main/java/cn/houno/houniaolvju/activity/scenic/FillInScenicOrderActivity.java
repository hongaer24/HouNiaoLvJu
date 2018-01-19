package cn.houno.houniaolvju.activity.scenic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.OrderDetailActivity;
import cn.houno.houniaolvju.adapter.PersonInfoAdapter;
import cn.houno.houniaolvju.bean.GetScenicPassengerBean;
import cn.houno.houniaolvju.bean.ScenicDetailBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;
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
    @Bind(R.id.tv_idcard)
    TextView tvIdcard;
    @Bind(R.id.et_idcard)
    EditText etIdcard;
    @Bind(R.id.ll_personinfo)
    LinearLayout llPersoninfo;
    @Bind(R.id.ll_idcard)
    LinearLayout llIdcard;
    @Bind(R.id.lv_person)
    ListView lvPerson;
    @Bind(R.id.btn_data)
    Button btnData;
    @Bind(R.id.btn_data1)
    Button btnData1;
    @Bind(R.id.tv_card)
    TextView tvCard;
   /* @Bind(R.id.btn_data2)
    Button btnData2;*/


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
    private int custInfoLimit;
    private String dataPrice;
    private int position;
    private String nowData;
    private PersonInfoAdapter madapter;
    private boolean isCheck = true;
    private boolean isPress;


    //private List<GetScenicPassengerBean.DataBean> touristDataBean;
    private List<GetScenicPassengerBean.DataBean> mPassnersList = new ArrayList<>();
    //private ArrayList<String> list = new ArrayList<>();
    private OptionsPickerView pvOption;
    private int data1;
    private int data2;
    private int nowPrice;
    private List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean> Pricecalendarlist = new ArrayList<>();
    private String departDate1;
    private String departDate2;
    private Bundle bundle;
    private String moreData;
    private String[] idCard={"22","11","44","55"};

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

    private void initData() {
        isLogined = PrefUtils.getBoolean(mActivity, "isLogined", false);
        // custInfoLimit = Integer.parseInt(intent.getStringExtra("custInfoLimit"));
        if (isLogined) {
            userid = PrefUtils.getString(mActivity, "userid", "");
            mEtJdName.setText(PrefUtils.getString(mActivity, "nick", ""));
            mEtJdPhone.setText(PrefUtils.getString(mActivity, "mobile", ""));
        } else {
            userid = Constants.PUBLIC_USER_ID;
        }
        Intent intent = getIntent();
        custInfoLimit = intent.getIntExtra("custInfoLimit", 0);
        //custInfoLimit = 6;
        if (custInfoLimit == 2 || custInfoLimit == 3 || custInfoLimit == 6 || custInfoLimit == 7) {
            llPersoninfo.setVisibility(View.VISIBLE);
        }
        if (custInfoLimit == 4 || custInfoLimit == 6 || custInfoLimit == 7) {
            llIdcard.setVisibility(View.VISIBLE);
        }
        mScenicTitle = intent.getStringExtra("scenicTitle");
        mScenicAddress = intent.getStringExtra("scenicAddress");
        mTicketTitle = intent.getStringExtra("ticketTitle");
        mScenicId = intent.getStringExtra("sid");
        mTicketId = intent.getStringExtra("tid");
        Pricecalendarlist = (List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean.TicketlistinfoBean.PricecalendarBean>) intent.getSerializableExtra("pricecalendar");
        //获得今天和明天的日期数据
        departDate1 = Pricecalendarlist.get(0).getDepartDate();
        departDate2 = Pricecalendarlist.get(1).getDepartDate();

        data1 = intent.getIntExtra("today", 0);
        data2 = intent.getIntExtra("minday", 0);

        if (data1 == 0) {
            btnData.setText("今天不可定");
            btnData.setEnabled(false);
            btnData.setBackgroundResource(R.drawable.shape_dark_gray);
            //btnData.setBackgroundColor(Color.parseColor("#dddddd"));
        } else {
            btnData.setText("今天¥" + data1);
        }
        if (data2 == 0) {
            btnData1.setText("明天不可定");
            btnData1.setEnabled(false);
            btnData1.setBackgroundResource(R.drawable.shape_dark_gray);
            // btnData1.setBackgroundColor(Color.parseColor("#dddddd"));
        } else {
            btnData1.setText("明天¥" + data2);
        }
        //btnData1.setText("明天¥"+data2);
        price = intent.getIntExtra("price", 0);
        position = intent.getIntExtra("position", 0);

        mTvTopbarTitle.setText(mScenicTitle);
        mTvScenicOrderTitle.setText(mTicketTitle);
        mTvScenicOrderAddress.setText(mScenicAddress);
        // MyText2Utils.formatTicketPrice(mActivity, mTvScenicOrderPrice, price + "");
        //allPrice = price;
        if (isPress) {
            MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, price * intTicketNum + "");
        } else {
            MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, 0 + "");
        }
        mCheckDate = DateUtil.getNowTime(DateUtil.DATE_SMALL_STR);
        mCheckWeek = DateUtil.getWeek(mCheckDate);

        if (moreData != null) {
            MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, nowPrice * intTicketNum + "");
        }
        // mTvDaysSelector.setText(nowData);
        madapter = new PersonInfoAdapter(mActivity, mPassnersList, intTicketNum);
        lvPerson.setAdapter(madapter);



    }

    private void initEvent() {
        mBtnReferSub.setOnClickListener(new TicketClick());
        mBtnReferAdd.setOnClickListener(new TicketClick());
        //btnData.setEnabled(btnData.isClickable());
        if (data1 != 0) {

            btnData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isCheck) {
                        //设置选中后和未选中的按键状态
                        mTvDaysSelector.setBackgroundResource(R.drawable.shape_white_gray);
                        mTvDaysSelector.setTextColor(Color.parseColor("#000000"));
                        btnData.setBackgroundResource(R.drawable.shape_org);
                        btnData.setTextColor(Color.parseColor("#ffffff"));
                        MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, data1 * intTicketNum + "");

                        btnData1.setEnabled(false);
                        btnData1.setBackgroundResource(R.drawable.shape_dark_gray);
                        btnData1.setTextColor(Color.parseColor("#FFAEA9A9"));


                        //btnData1.setBackgroundColor(Color.parseColor("#ffffff"));

                        mTvDaysSelector.setEnabled(false);
                        mTvDaysSelector.setBackgroundResource(R.drawable.shape_dark_gray);
                        mTvDaysSelector.setTextColor(Color.parseColor("#FFAEA9A9"));
                        nowData = departDate1;
                    } else {
                        if (data2 == 0) {

                            btnData.setBackgroundResource(R.drawable.shape_white_gray);
                            btnData.setTextColor(Color.parseColor("#000000"));
                            MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, 0 + "");
                         /*  btnData1.setEnabled(true);
                           btnData1.setBackgroundResource(R.drawable.shape_white_gray);
                           btnData1.setTextColor(Color.parseColor("#000000"));
*/
                            btnData1.setBackgroundResource(R.drawable.shape_dark_gray);
                            //btnData1.setBackgroundColor(Color.parseColor("#ffffff"));
                            mTvDaysSelector.setEnabled(true);
                            mTvDaysSelector.setBackgroundResource(R.drawable.shape_white_gray);
                            mTvDaysSelector.setTextColor(Color.parseColor("#000000"));
                        } else {
                            btnData.setBackgroundResource(R.drawable.shape_white_gray);
                            btnData.setTextColor(Color.parseColor("#000000"));
                            MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, 0 + "");
                            btnData1.setEnabled(true);
                            btnData1.setBackgroundResource(R.drawable.shape_white_gray);
                            btnData1.setTextColor(Color.parseColor("#000000"));

                            //btnData1.setBackgroundColor(Color.parseColor("#ffffff"));
                            mTvDaysSelector.setEnabled(true);
                            mTvDaysSelector.setBackgroundResource(R.drawable.shape_white_gray);
                            mTvDaysSelector.setTextColor(Color.parseColor("#000000"));
                        }


                    }
                    isCheck = !isCheck;
                    isPress = !isPress;
                }
            });

        }


        if (data2 != 0) {
            btnData1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isCheck) {
                        mTvDaysSelector.setBackgroundResource(R.drawable.shape_white_gray);
                        mTvDaysSelector.setTextColor(Color.parseColor("#000000"));

                        btnData1.setBackgroundResource(R.drawable.shape_org);
                        btnData1.setTextColor(Color.parseColor("#ffffff"));
                        MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, data2 * intTicketNum + "");
                        btnData.setEnabled(false);
                        btnData.setBackgroundResource(R.drawable.shape_dark_gray);
                        btnData.setTextColor(Color.parseColor("#FFAEA9A9"));


                        mTvDaysSelector.setEnabled(false);
                        mTvDaysSelector.setBackgroundResource(R.drawable.shape_dark_gray);
                        mTvDaysSelector.setTextColor(Color.parseColor("#FFAEA9A9"));

                        nowData = departDate2;
                    } else {
                        if (data1 == 0) {
                            btnData.setBackgroundResource(R.drawable.shape_dark_gray);
                            //btnData.setEnabled(true);
                            btnData1.setBackgroundResource(R.drawable.shape_white_gray);
                            btnData1.setTextColor(Color.parseColor("#000000"));
                            MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, 0 + "");
                            /*btnData.setEnabled(true);
                            btnData.setBackgroundResource(R.drawable.shape_white_gray);
                            btnData.setTextColor(Color.parseColor("#000000"));*/

                            mTvDaysSelector.setEnabled(true);
                            mTvDaysSelector.setBackgroundResource(R.drawable.shape_white_gray);
                            mTvDaysSelector.setTextColor(Color.parseColor("#000000"));
                            //mTvDaysSelector.setBackgroundResource(R.drawable.shape_dark_gray);

                            //btnData.setTextColor(Color.parseColor("#dddddd"));
                        } else {
                            btnData1.setBackgroundResource(R.drawable.shape_white_gray);
                            btnData1.setTextColor(Color.parseColor("#000000"));

                            MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, 0 + "");

                            btnData.setEnabled(true);
                            btnData.setBackgroundResource(R.drawable.shape_white_gray);
                            btnData.setTextColor(Color.parseColor("#000000"));

                            mTvDaysSelector.setEnabled(true);
                            mTvDaysSelector.setBackgroundResource(R.drawable.shape_white_gray);
                            mTvDaysSelector.setTextColor(Color.parseColor("#000000"));
                        }

                    }
                    isCheck = !isCheck;
                    isPress = !isPress;

                }
            });
        }
        mTvDaysSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheck) {
                    Intent date = new Intent();
                    date.putExtra("position", position);
                    date.putExtra("sid", mScenicId);
                    date.putExtra("pricecalendars", (Serializable) Pricecalendarlist);
                    date.setClass(mActivity, MoreInfoCalendarActivity.class);
                    startActivityForResult(date, 301);
                    //
                   /*  mTvDaysSelector.setBackgroundResource(R.drawable.shape_white_gray);
                     mTvDaysSelector.setTextColor(Color.parseColor("#000000"));*/

                    mTvDaysSelector.setBackgroundResource(R.drawable.shape_org);
                    mTvDaysSelector.setTextColor(Color.parseColor("#ffffff"));
                    MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, data2 + "");
                    btnData.setEnabled(false);
                    btnData.setBackgroundResource(R.drawable.shape_dark_gray);
                    btnData.setTextColor(Color.parseColor("#FFAEA9A9"));

                    btnData1.setEnabled(false);
                    btnData1.setBackgroundResource(R.drawable.shape_dark_gray);
                    btnData1.setTextColor(Color.parseColor("#FFAEA9A9"));
                } else {
                    mTvDaysSelector.setBackgroundResource(R.drawable.shape_white_gray);
                    mTvDaysSelector.setTextColor(Color.parseColor("#000000"));

                    MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, 0 + "");
                    if (data1 == 0) {
                        btnData.setBackgroundResource(R.drawable.shape_dark_gray);
                    } else {
                        btnData.setEnabled(true);
                        btnData.setBackgroundResource(R.drawable.shape_white_gray);
                        btnData.setTextColor(Color.parseColor("#000000"));
                    }

                    if (data2 == 0) {
                        btnData1.setBackgroundResource(R.drawable.shape_dark_gray);
                    } else {
                        btnData1.setEnabled(true);
                        btnData1.setBackgroundResource(R.drawable.shape_white_gray);
                        btnData1.setTextColor(Color.parseColor("#000000"));
                    }
                }
                isCheck = !isCheck;
                isPress = !isPress;

                /* if (ischeck) {
                     //MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, nowPrice + "");
                     btnData1.setEnabled(false);
                     btnData.setEnabled(false);
                 } else {
                     btnData.setBackgroundResource(R.drawable.shape_white_gray);
                     btnData.setTextColor(Color.parseColor("#000000"));
                     MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, 0 + "");
                     btnData1.setEnabled(true);
                     btnData.setEnabled(true);
                 }
                 ischeck = !ischeck;*/
            }
        });
        /*btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnData.setBackgroundResource(R.drawable.selector_orgs_btn);
            }
        });*/
    }


/*
    private List<GetScenicPassengerBean.DataBean> getPassengerList() {
        List<GetScenicPassengerBean.DataBean> passengerList = PassengerStorage.getInstance().getAllData();
        List<GetScenicPassengerBean.DataBean> CheckedPassengerList = new ArrayList<>();
        if (passengerList.size() > 0 && passengerList != null) {
            for (int i = 0; i < passengerList.size(); i++) {
                GetScenicPassengerBean.DataBean passengerBean = passengerList.get(i);
                if (passengerBean.isChoosed()) {
                    CheckedPassengerList.add(passengerBean);
                }
            }
        }
        return CheckedPassengerList;
    }
*/

    @OnClick({R.id.tv_idcard, R.id.iv_back, R.id.iv_home, R.id.tv_days_selector, R.id.btn_jd_tjdd, R.id.iv_number_person})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_idcard:
                showPickerView();
                pvOption.show();
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
                date.putExtra("position", position);
                date.putExtra("sid", mScenicId);
                date.putExtra("pricecalendars", (Serializable) Pricecalendarlist);
                date.setClass(mActivity, MoreInfoCalendarActivity.class);
                startActivityForResult(date, 301);
                break;
            case R.id.btn_jd_tjdd:
                checkInputAndGetData();
                break;
            case R.id.iv_number_person:
                Intent intent1 = new Intent(this, PersonsListActivity.class);
                intent1.putExtra("persons", intTicketNum);
                startActivityForResult(intent1, 666);


                break;
        }
    }

    private void showPickerView() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("身份证");
        list.add("护照");
        list.add("军官证");
        list.add("港澳通行证");
        list.add("台胞证");

        pvOption = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                String sex = list.get(options1);
                tvIdcard.setText(sex);
            }

        }).setTitleText("请选择证件类型")
                .setDividerColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .setContentTextSize(25)
                .build();
        pvOption.setPicker(list);

    }

    private void checkInputAndGetData() {
        if (TextUtils.isEmpty(mEtJdName.getText().toString().trim())) {
            Toast.makeText(mActivity, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isPress) {
            Toast.makeText(mActivity, "请选择出游时期", Toast.LENGTH_SHORT).show();
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

        boolean isID = true;
        //判断输入的用户名是否是电话号码
        if (etIdcard.getText().toString().length() == 18) {
            for (int i = 0; i < etIdcard.getText().toString().length(); i++) {
                char c = etIdcard.getText().toString().charAt(i);
                if (!Character.isDigit(c)) {
                    isID = false;
                    break;//只要有一位不符合要求退出循环
                }
            }
        } else {
            isID = false;
        }
        int num = intTicketNum - mPassnersList.size();
        int num1 = mPassnersList.size() - intTicketNum;

        if (TextUtils.isEmpty(mEtJdPhone.getText())) {
            Toast.makeText(mActivity, "手机号不能为空", Toast.LENGTH_SHORT).show();
        } else if (!isTel) {
            Toast.makeText(mActivity, "手机号格式不对", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(etIdcard.getText()) && (custInfoLimit == 4 || custInfoLimit == 6 || custInfoLimit == 7)) {
            Toast.makeText(mActivity, "身份证号不能为空", Toast.LENGTH_SHORT).show();
        } else if (!isID && (custInfoLimit == 4 || custInfoLimit == 6 || custInfoLimit == 7)) {
            Toast.makeText(mActivity, "身份证号为非法格式", Toast.LENGTH_SHORT).show();
        } else if ( /*mPassnersList.size()==0&&*/mPassnersList.size() < intTicketNum && (custInfoLimit == 2 || custInfoLimit == 3 || custInfoLimit == 6 || custInfoLimit == 7)) {
            Toast.makeText(mActivity, "您还需要填写" + num + "个出游人信息", Toast.LENGTH_SHORT).show();
        } else if (mPassnersList.size() > intTicketNum && (custInfoLimit == 2 || custInfoLimit == 3 || custInfoLimit == 6 || custInfoLimit == 7)) {
            Toast.makeText(mActivity, "您需要删除" + num1 + "个出游人信息", Toast.LENGTH_SHORT).show();
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
        params.addBodyParameter("info[productId]", mTicketId);
        if (moreData != null) {
            params.addBodyParameter("info[startTime]", moreData);
        } else {
            params.addBodyParameter("info[startTime]", nowData);
        }
        params.addBodyParameter("info[productname]", mTicketTitle);
        params.addBodyParameter("contact[tel]", mEtJdPhone.getText().toString().trim());
        params.addBodyParameter("contact[name]", mEtJdName.getText().toString().trim());
        params.addBodyParameter("info[bookNumber]", mTvReferNum.getText().toString().trim());
        params.addBodyParameter("info[totalprice]", price * intTicketNum + "");
        params.addBodyParameter(" info[address]", mScenicAddress);

        //custInfoLimit=2、3、6、7时,传游客资料表
        if (custInfoLimit == 2 || custInfoLimit == 3 || custInfoLimit == 6 || custInfoLimit == 7) {
            for (int i = 0; i < mPassnersList.size(); i++) {
                params.addBodyParameter("touristlist[" + i + "][tel]", mPassnersList.get(i).getPhone());
                params.addBodyParameter("touristlist[" + i + "][psptId]", mPassnersList.get(i).getIdentityno());
                params.addBodyParameter("touristlist[" + i + "][name]", mPassnersList.get(i).getName());
                params.addBodyParameter("touristlist[" + i + "][psptType]", mPassnersList.get(i).getIdentitytype());

            }
        }
        //custInfoLimit=4、6、7时,传identityNo和identityType
        if (custInfoLimit == 4 || custInfoLimit == 6 || custInfoLimit == 7) {
            //params.addBodyParameter("contact[identityType]", mPassnersList.get(i).getPhone());
            params.addBodyParameter("contact[identityNo]", etIdcard.getText().toString().trim());
            String id = tvIdcard.getText().toString();
            if (id.equals("身份证")) {
                params.addBodyParameter("contact[identityType]", "1");
            } else if (id.equals("护照")) {
                params.addBodyParameter("contact[identityType]", "2");
            } else if (id.equals("军官证")) {
                params.addBodyParameter("contact[identityType]", "3");
            } else if (id.equals("港澳通行证")) {
                params.addBodyParameter("contact[identityType]", "4");
            } else if (id.equals("台胞证")) {
                params.addBodyParameter("contact[identityType]", "5");
            }
        }


        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");

                    if (status == 0) {
                        Log.i("0101", "result===" + result);
                        Log.i("0101", "result===" + obj.getString("orderno"));
                        Intent intent = new Intent();
                        intent.putExtra("type", "tuniuscenic");
                        intent.putExtra("mprice", price * intTicketNum + "");
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
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
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
                    tvNumberPerson.setText("出游人（需要填写" + intTicketNum + "个出游人）");
                }
            } else if (i == R.id.btn_refer_add) {
                if (intTicketNum < ticketNumEst) {
                    intTicketNum++;
                    mTvReferNum.setText(intTicketNum + "");
                    tvNumberPerson.setText("出游人（需要填写" + intTicketNum + "个出游人）");
                }
            }
            if (isPress) {
                allPrice = price * intTicketNum;
                MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, allPrice + "");
            }
            //allPrice = price * intTicketNum;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == 300 && requestCode == 301) {
           /* Bundle extras = data.getExtras();
            mCheckDate = extras.getString("dateIn");
            mCheckWeek = extras.getString("dateInWeek");*/
            bundle = data.getBundleExtra("bundle"); //city即为回传的值
            moreData = bundle.getString("nowData");
            nowPrice = Integer.parseInt(bundle.getString("nowPrice"));
            //MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, nowPrice + "");
            // nowData = data.getStringExtra("nowData");
            // nowPrice = data.getStringExtra("nowPrice");
            mTvDaysSelector.setText(moreData + "\n" + "¥" + nowPrice + "起");
            mTvDaysSelector.setBackgroundResource(R.drawable.shape_org);
            mTvDaysSelector.setTextColor(Color.parseColor("#ffffff"));
            //mTvZxzfMoney.setText("¥"+nowData+"起");
        }
        if (requestCode == 666 && resultCode == RESULT_OK) {
            mPassnersList = (ArrayList<GetScenicPassengerBean.DataBean>) data.getSerializableExtra("list");
            madapter.setData(mPassnersList);
            //mTvZxzfMoney.setText(allPrice);
            //MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, data2*intTicketNum+"");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // MyText2Utils.formatTicketPrice(mActivity, mTvZxzfMoney, data2*intTicketNum+"");
        initData();
    }

}





