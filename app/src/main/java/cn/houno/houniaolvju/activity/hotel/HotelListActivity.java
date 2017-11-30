package cn.houno.houniaolvju.activity.hotel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.zxl.library.DropDownMenu;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.CitySelectActivity;
import cn.houno.houniaolvju.activity.DatePickerActivity;
import cn.houno.houniaolvju.adapter.HotelListAdapter;
import cn.houno.houniaolvju.bean.HotelListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.RadioGroup;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店列表
 * 创建人：qzc
 * 创建时间：2016/12/14 11:37
 * 修改人：qzc
 * 修改时间：2016/12/14 11:37
 * 修改备注：
 */
public class HotelListActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    private static final int RESULT_CODE = 300;
    private static final int DATE_REQUEST_CODE = 301;


    private ImageView mIvBack;
    private LinearLayout llCitySelect;
    private TextView mTvTopBarTitle;
    private TextView mTvCheckIn;
    private TextView mTvCheckOut;
    private TextView mTvTotalDays;
    private LinearLayout mLlDateCheck;
    private EditText etSearch;

    private DropDownMenu mDdmHotellist;

    private HotelListActivity mActivity;

    private String headers[] = {"排序", "价格星级"};
    private int[] types = new int[]{DropDownMenu.TYPE_LIST_CITY, DropDownMenu.TYPE_CUSTOM};

    private String sorts[] =
            {"候鸟旅居推荐", "价格 低→高", "价格 高→低"};


    private int sort = 0;   //排序选择
    private int price = 0;  //价格单选
    private TreeSet<String> level = new TreeSet<>();

    private double mLatitude = 0;
    private double mLongitude = 0;
    private boolean isLoc;
    private String mCityId = "";
    private String mCityName = "";
    private String mCheckIn = "";
    private String mCheckOut = "";
    private String mDays = "";
    private String mKeyWord = "";
    private int mPrice;
    private List mLevel = new ArrayList<>();
    private HotelListAdapter mAdapter;
    private ArrayList<HotelListBean.DataBean> mList = new ArrayList<>();
    private ListView mListView;

    private XRefreshView mRefreshView;
    private int page = 2;   //列表页数
    private CheckBox mCbLevel0;
    private CheckBox mCbLevel1;
    private CheckBox mCbLevel2;
    private CheckBox mCbLevel3;
    private CheckBox mCbLevel4;

    private RadioButton mRbPrice0;
    private RadioButton mRbPrice1;
    private RadioButton mRbPrice2;
    private RadioButton mRbPrice3;
    private RadioButton mRbPrice4;
    private RadioButton mRbPrice5;

    private String mFrom;   //国内或者国际
    private String mUrl;

    private XRefreshView rfvForeign;
    private ListView lvForeign;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_hotel_list);
        mActivity = HotelListActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        llCitySelect = (LinearLayout) findViewById(R.id.ll_city_select);
        mTvTopBarTitle = (TextView) findViewById(R.id.tv_topbar_title);
        etSearch = (EditText) findViewById(R.id.et_search);
        mTvCheckIn = (TextView) findViewById(R.id.tv_check_in);
        mTvCheckOut = (TextView) findViewById(R.id.tv_check_out);
        mTvTotalDays = (TextView) findViewById(R.id.tv_total_days);
        mLlDateCheck = (LinearLayout) findViewById(R.id.ll_date_check);
        mDdmHotellist = (DropDownMenu) findViewById(R.id.ddm_hotellist);
    }

    private void initEvent() {
        mIvBack.setOnClickListener(this);
        llCitySelect.setOnClickListener(this);
        mLlDateCheck.setOnClickListener(this);
        mRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                getMoreDataFromServer();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });

        /*
        * 点击搜索事件监听
        * */
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String input = etSearch.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(mActivity, "请输入关键字", Toast.LENGTH_SHORT).show();
                } else {
                    mKeyWord = input;
                    getDataFromServer();
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                }
                return true;
            }
        });

        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //编辑框获得焦点

                } else {
                    //编辑框取消焦点

                    //收起键盘
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                }
            }
        });

        /*
        * 取消编辑框焦点
        * */
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etSearch.isFocused()) {
                    etSearch.clearFocus();
                }
                return false;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(mActivity, HotelDetailActivity.class);
                intent.putExtra("from", "home");
                intent.putExtra("hid", mList.get(position).getId());
                intent.putExtra("checkIn", mCheckIn);
                intent.putExtra("checkOut", mCheckOut);
                intent.putExtra("days", mDays);
                startActivity(intent);
            }
        });
    }


    private void initData() {
        initContentView();
        Intent intent = getIntent();

        isLoc = intent.getBooleanExtra("isLoc", false);
        if (isLoc) {
            mLatitude = intent.getDoubleExtra("lat", 0);
            mLongitude = intent.getDoubleExtra("lng", 0);
        } else {
            mCityId = intent.getStringExtra("cityId");
        }
        mCityName = intent.getStringExtra("cityName");
        mTvTopBarTitle.setText(mCityName);

        mPrice = intent.getIntExtra("price", 0);
        price = mPrice;
        mLevel = intent.getStringArrayListExtra("level");
        setPriceLevelData(mPrice, mLevel);
        if (mLevel != null) {
            for (int i = 0; i < mLevel.size(); i++) {
                level.add((String) mLevel.get(i));
            }
        }

        mCheckIn = intent.getStringExtra("checkIn");
        if (TextUtils.isEmpty(mCheckIn)) {
            mCheckIn = DateUtil.getNowTime(DateUtil.DATE_SMALL_STR);
        }
        mCheckOut = intent.getStringExtra("checkOut");
        if (TextUtils.isEmpty(mCheckOut)) {
            mCheckOut = DateUtil.getTomorrowTime(mCheckIn, DateUtil.DATE_SMALL_STR);
        }
        mDays = intent.getStringExtra("days");
        if (TextUtils.isEmpty(mDays)) {
            mDays = DateUtil.getDays(mCheckIn, mCheckOut) + "";
        }

        mKeyWord = intent.getStringExtra("keyword");

        mTvCheckIn.setText(mCheckIn.substring(5));
        mTvCheckOut.setText(mCheckOut.substring(5));
        mTvTotalDays.setText(mDays + "晚");
        etSearch.setText(mKeyWord);

        mRefreshView.startRefresh();
    }


    private void setPriceLevelData(int price, List level) {
        switch (price) {
            case 0:
                mRbPrice0.setChecked(true);
                break;
            case 1:
                mRbPrice1.setChecked(true);
                break;
            case 2:
                mRbPrice2.setChecked(true);
                break;
            case 3:
                mRbPrice3.setChecked(true);
                break;
            case 4:
                mRbPrice4.setChecked(true);
                break;
            case 5:
                mRbPrice5.setChecked(true);
                break;
        }
        if (level != null) {

            for (int i = 0; i < level.size(); i++) {
                setLevelData(level.get(i));
            }
        }
    }

    private void setLevelData(Object o) {
        if (o.equals("0")) {
            mCbLevel0.setChecked(true);
        }
        if (o.equals("1")) {
            mCbLevel1.setChecked(true);
        }
        if (o.equals("2")) {
            mCbLevel2.setChecked(true);
        }
        if (o.equals("3")) {
            mCbLevel3.setChecked(true);
        }
        if (o.equals("4")) {
            mCbLevel4.setChecked(true);
        }
    }

    private void initContentView() {
        View contentView = getLayoutInflater().inflate(R.layout.layout_hotellist_contentview, null);
        mRefreshView = (XRefreshView) contentView.findViewById(R.id.refreshview);
        mRefreshView.setPullLoadEnable(true);
        mListView = (ListView) contentView.findViewById(R.id.lv_content);
        mDdmHotellist.setDropDownMenu(Arrays.asList(headers), initViewData(), contentView);
        mAdapter = new HotelListAdapter(this);
        mListView.setAdapter(mAdapter);

        //该监听回调只监听默认类型，如果是自定义view请自行设置，参照demo
        mDdmHotellist.addMenuSelectListener(new DropDownMenu.OnDefultMenuSelectListener() {
            @Override
            public void onSelectDefaultMenu(int index, int pos, String clickstr) {
                //index:点击的tab索引，pos：单项菜单中点击的位置索引，clickstr：点击位置的字符串
                switch (pos) {
                    case 0: //候鸟旅居推荐
                        sort = 0;
                        break;
                    case 1: //价格 低→高
                        sort = 1;
                        break;
                    case 2: //价格 高→低
                        sort = 2;
                        break;
                }
                getDataFromServer();
            }
        });


    }

    //价格星级
    private View getPriceLevelView() {
        View vPriceLevel = getLayoutInflater().inflate(R.layout.layout_hotellist_price_level, null);

        RadioGroup mRgPrice = (RadioGroup) vPriceLevel.findViewById(R.id.rg_price);

        mCbLevel0 = (CheckBox) vPriceLevel.findViewById(R.id.cb_level0);
        mCbLevel1 = (CheckBox) vPriceLevel.findViewById(R.id.cb_level1);
        mCbLevel2 = (CheckBox) vPriceLevel.findViewById(R.id.cb_level2);
        mCbLevel3 = (CheckBox) vPriceLevel.findViewById(R.id.cb_level3);
        mCbLevel4 = (CheckBox) vPriceLevel.findViewById(R.id.cb_level4);

        mRbPrice0 = (RadioButton) vPriceLevel.findViewById(R.id.rb_price0);
        mRbPrice1 = (RadioButton) vPriceLevel.findViewById(R.id.rb_price1);
        mRbPrice2 = (RadioButton) vPriceLevel.findViewById(R.id.rb_price2);
        mRbPrice3 = (RadioButton) vPriceLevel.findViewById(R.id.rb_price3);
        mRbPrice4 = (RadioButton) vPriceLevel.findViewById(R.id.rb_price4);
        mRbPrice5 = (RadioButton) vPriceLevel.findViewById(R.id.rb_price5);

        mCbLevel0.setOnCheckedChangeListener(this);
        mCbLevel1.setOnCheckedChangeListener(this);
        mCbLevel2.setOnCheckedChangeListener(this);
        mCbLevel3.setOnCheckedChangeListener(this);
        mCbLevel4.setOnCheckedChangeListener(this);

        TextView mBtnClear = (TextView) vPriceLevel.findViewById(R.id.tv_clear);
        TextView mBtnConfirm = (TextView) vPriceLevel.findViewById(R.id.tv_confirm);
        mRgPrice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_price0:
                        price = 0;
                        break;
                    case R.id.rb_price1:
                        price = 1;
                        break;
                    case R.id.rb_price2:
                        price = 2;
                        break;
                    case R.id.rb_price3:
                        price = 3;
                        break;
                    case R.id.rb_price4:
                        price = 4;
                        break;
                    case R.id.rb_price5:
                        price = 5;
                        break;
                }
                Log.i("RadioGroup", price + "");
            }
        });

        //清空
        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAllCheck();
            }
        });
        //确定
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromServer();
                mDdmHotellist.closeMenu();
            }
        });
        LinearLayout llPriceLevel = (LinearLayout) vPriceLevel.findViewById(R.id.ll_price_level);
        llPriceLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return vPriceLevel;
    }

    /*
    * 星级多选事件监听
    * */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        checkChanged(buttonView, isChecked);
    }

    private void checkChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (id == R.id.cb_level0) {  //不限被选中
            if (isChecked) {
                if (mCbLevel1.isChecked()) {
                    mCbLevel1.setChecked(false);
                }
                if (mCbLevel2.isChecked()) {
                    mCbLevel2.setChecked(false);
                }
                if (mCbLevel3.isChecked()) {
                    mCbLevel3.setChecked(false);
                }
                if (mCbLevel4.isChecked()) {
                    mCbLevel4.setChecked(false);
                }
                mCbLevel0.setChecked(true);
                level.clear();
                level.add("0");
            } else {
                mCbLevel0.setChecked(false);
                level.remove("0");
            }

        } else {
            //移除 不限
            if (mCbLevel0.isChecked()) {
                mCbLevel0.setChecked(false);
                level.remove("0");
            }
            if (id == R.id.cb_level1) {
                if (mCbLevel1.isChecked()) {
                    mCbLevel1.setChecked(true);
                    level.add("1");
                } else {
                    mCbLevel1.setChecked(false);
                    level.remove("1");
                }
            }
            if (id == R.id.cb_level2) {
                if (mCbLevel2.isChecked()) {
                    mCbLevel2.setChecked(true);
                    level.add("3");
                } else {
                    mCbLevel2.setChecked(false);
                    level.remove("3");
                }
            }
            if (id == R.id.cb_level3) {
                if (mCbLevel3.isChecked()) {
                    mCbLevel3.setChecked(true);
                    level.add("4");
                } else {
                    mCbLevel3.setChecked(false);
                    level.remove("4");
                }
            }
            if (id == R.id.cb_level4) {
                if (mCbLevel4.isChecked()) {
                    mCbLevel4.setChecked(true);
                    level.add("5");
                } else {
                    mCbLevel4.setChecked(false);
                    level.remove("5");
                }
            }
            if (mCbLevel1.isChecked() && mCbLevel2.isChecked()
                    && mCbLevel3.isChecked() && mCbLevel4.isChecked()) {
                mCbLevel1.setChecked(false);
                mCbLevel2.setChecked(false);
                mCbLevel3.setChecked(false);
                mCbLevel4.setChecked(false);
                mCbLevel0.setChecked(true);
            }
        }
        Log.i("level", level.toString());
    }

    private void clearAllCheck() {
        if (mRbPrice0.isChecked()) {
            mRbPrice0.setChecked(false);
        }
        if (mRbPrice1.isChecked()) {
            mRbPrice1.setChecked(false);
        }
        if (mRbPrice2.isChecked()) {
            mRbPrice2.setChecked(false);
        }
        if (mRbPrice3.isChecked()) {
            mRbPrice3.setChecked(false);
        }
        if (mRbPrice4.isChecked()) {
            mRbPrice4.setChecked(false);
        }
        if (mRbPrice5.isChecked()) {
            mRbPrice5.setChecked(false);
        }

        if (mCbLevel0.isChecked()) {
            mCbLevel0.setChecked(false);
        }
        if (mCbLevel1.isChecked()) {
            mCbLevel1.setChecked(false);
        }
        if (mCbLevel2.isChecked()) {
            mCbLevel2.setChecked(false);
        }
        if (mCbLevel3.isChecked()) {
            mCbLevel3.setChecked(false);
        }
        if (mCbLevel4.isChecked()) {
            mCbLevel4.setChecked(false);
        }

    }


    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.HOTEL_LIST);

        if (isLoc) {
            params.addBodyParameter("lat", mLatitude + "");
            params.addBodyParameter("lng", mLongitude + "");
        } else {
            params.addBodyParameter("city", mCityId);
        }
        params.addBodyParameter("keyword", etSearch.getText().toString().trim());
        params.addBodyParameter("sort", sort + "");
        params.addBodyParameter("price", price + "");
        params.addBodyParameter("xji", level.toString().substring(1, level.toString().length() - 1));
        Log.e("print:", "latitude:" + mLatitude + ",longitude:" + mLongitude
                + ",city:" + mCityId + ",keyword:" + etSearch.getText().toString().trim()
                + ",sort:" + sort + ",price:" + price + ",xji:" + level.toString().substring(1, level.toString().length() - 1));

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, false);
                        page = 2;
                        if (!mRefreshView.getPullLoadEnable()) {
                            mRefreshView.setPullLoadEnable(true);
                        }
                    } else {
                        mRefreshView.setPullLoadEnable(false);
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
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
                mRefreshView.stopRefresh();
            }
        });
    }

    private void getMoreDataFromServer() {
        RequestParams params = new RequestParams(Constants.HOTEL_LIST);

        if (isLoc) {
            params.addBodyParameter("lat", mLatitude + "");
            params.addBodyParameter("lng", mLongitude + "");
        } else {
            params.addBodyParameter("city", mCityId);
        }
        params.addBodyParameter("keyword", etSearch.getText().toString().trim());
        params.addBodyParameter("sort", sort + "");
        params.addBodyParameter("price", price + "");
        params.addBodyParameter("xji", level.toString().substring(1, level.toString().length() - 1));
        params.addBodyParameter("page", page + "");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, true);
                        page++;
                    } else {
                        mRefreshView.setPullLoadEnable(false);
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
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
                mRefreshView.stopLoadMore();
            }
        });
    }

    /*
    * 解析处理数据
    * */
    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();

        HotelListBean hotelListBean = gson.fromJson(result, HotelListBean.class);
        if (!isMore) {  //下拉刷新数据
            mList = hotelListBean.getData();
        } else {
            mList.addAll(hotelListBean.getData());
        }
        if (mAdapter == null) {
            mAdapter = new HotelListAdapter(this);
        } else {
            mAdapter.setDatas(mList);
        }
    }


    private List<HashMap<String, Object>> initViewData() {
        List<HashMap<String, Object>> viewDatas = new ArrayList<>();
        HashMap<String, Object> map;
        for (int i = 0; i < headers.length; i++) {
            map = new HashMap<>();
            map.put(DropDownMenu.KEY, types[i]);
            switch (types[i]) {
                case DropDownMenu.TYPE_LIST_CITY:
                    map.put(DropDownMenu.VALUE, sorts);
                    map.put(DropDownMenu.SELECT_POSITION, 0);
                    break;
                default:
                    map.put(DropDownMenu.VALUE, getPriceLevelView());
                    break;
            }
            viewDatas.add(map);
        }
        return viewDatas;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CODE && requestCode == DATE_REQUEST_CODE) {
            Bundle extras = data.getExtras();
            mCheckIn = extras.getString("dateIn");
            mCheckOut = extras.getString("dateOut");
            mDays = extras.getString("days");
            mTvCheckIn.setText(mCheckIn.substring(5));//把年份截取掉，只留月份和日期
            mTvCheckOut.setText(mCheckOut.substring(5));
            mTvTotalDays.setText(mDays + "晚");
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            Bundle bundle = data.getBundleExtra("bundle"); //city即为回传的值
            mCityName = bundle.getString("city");
            mCityId = bundle.getString("cityId");
            mTvTopBarTitle.setText(mCityName);
            getDataFromServer();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_city_select:
                Intent intent = new Intent();
                intent.putExtra("from","home");
                intent.setClass(mActivity,CitySelectActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.ll_date_check:
                startActivityForResult(new Intent(HotelListActivity.this, DatePickerActivity.class), DATE_REQUEST_CODE);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDdmHotellist.isShowing()) {
            mDdmHotellist.closeMenu();
        } else {
            super.onBackPressed();
        }
    }


}
