package cn.houno.houniaolvju.activity.foreignhotel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.CitySelectActivity;
import cn.houno.houniaolvju.activity.DatePickerActivity;
import cn.houno.houniaolvju.activity.hotel.HotelDetailActivity;
import cn.houno.houniaolvju.adapter.ForeignHotelListAdapter;
import cn.houno.houniaolvju.bean.ForeignHotelListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.ClearableEditText;

/**
 * 国际酒店列表
 * Created by Administrator on 2017/1/13.
 */

public class ForeignHotelListActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @Bind(R.id.ll_city_select)
    LinearLayout llCitySelect;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.tv_check_in)
    TextView tvCheckIn;
    @Bind(R.id.tv_check_out)
    TextView tvCheckOut;
    @Bind(R.id.tv_total_days)
    TextView tvTotalDays;
    @Bind(R.id.ll_date_check)
    LinearLayout llDateCheck;
    @Bind(R.id.et_search)
    ClearableEditText cetSearch;
    @Bind(R.id.lv_foreign)
    ListView lvForeign;
    @Bind(R.id.rfv_foreign)
    XRefreshView rfvForeign;

    private ForeignHotelListActivity mActivity;
    private String mCheckIn;
    private String mCheckOut;
    private String mCityId;
    private String mCityName;
    private String mDays;
    private String mKeyword;
    private String mKeyWord;

    private int page = 2;

    private List<ForeignHotelListBean.DataBean> foreignList = new ArrayList<>();
    private ForeignHotelListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_foreign_hotel_list);
        mActivity = ForeignHotelListActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    private void initEvent() {
        rfvForeign.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
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
        cetSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String input = cetSearch.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(mActivity, "请输入关键字", Toast.LENGTH_SHORT).show();
                } else {
                    mKeyWord = input;
                    getDataFromServer();
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(cetSearch.getWindowToken(), 0);
                }
                return true;
            }
        });

        cetSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //编辑框获得焦点

                } else {
                    //编辑框取消焦点

                    //收起键盘
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(cetSearch.getWindowToken(), 0);
                }
            }
        });

         /*
        * 取消编辑框焦点
        * */
        lvForeign.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (cetSearch.isFocused()) {
                    cetSearch.clearFocus();
                }
                return false;
            }
        });

        lvForeign.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(mActivity, HotelDetailActivity.class);
                intent.putExtra("hid", foreignList.get(position).getId());
                intent.putExtra("from", "foreign");
                intent.putExtra("checkIn", mCheckIn);
                intent.putExtra("checkOut", mCheckOut);
                intent.putExtra("days", mDays);
                startActivity(intent);
            }
        });
    }


    private void initData() {
        Intent intent = getIntent();

        mCityId = intent.getStringExtra("cityId");
        System.out.println("cityid:=============>"+mCityId);
        mCityName = intent.getStringExtra("cityName");
        if (TextUtils.isEmpty(mCityName)) {
            mCityName = "选择城市";
        }
        tvTopbarTitle.setText(mCityName);
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

        tvCheckIn.setText(mCheckIn.substring(5));
        tvCheckOut.setText(mCheckOut.substring(5));
        tvTotalDays.setText(mDays + "晚");
        cetSearch.setText(mKeyWord);

        rfvForeign.startRefresh();
    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.FOREIGN_HOTEL_LIST);
        params.addBodyParameter("city", mCityId);
        params.addBodyParameter("keyword", cetSearch.getText().toString().trim());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, false);
                        if (!rfvForeign.getPullLoadEnable()) {
                            rfvForeign.setPullLoadEnable(true);
                        }
                    } else {
                        rfvForeign.setPullLoadEnable(false);
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
                rfvForeign.stopRefresh();
            }
        });
    }

    private void getMoreDataFromServer() {
        RequestParams params = new RequestParams(Constants.FOREIGN_HOTEL_LIST);
        params.addBodyParameter("city", mCityId);
        params.addBodyParameter("keyword", cetSearch.getText().toString().trim());
        params.addBodyParameter("p", page + "");
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
                        rfvForeign.setPullLoadEnable(false);
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
                rfvForeign.stopLoadMore();
            }
        });
    }

    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();
        ForeignHotelListBean foreignHotelListBean = gson.fromJson(result, ForeignHotelListBean.class);
        if (!isMore) {  //下拉刷新数据
            foreignList = foreignHotelListBean.getData();
        } else {
            foreignList.addAll(foreignHotelListBean.getData());
        }
        if (mAdapter == null) {
            mAdapter = new ForeignHotelListAdapter(this, foreignList);
            lvForeign.setAdapter(mAdapter);
        } else {
            mAdapter.setDatas(foreignList);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 300 && requestCode == 301) {
            Bundle extras = data.getExtras();
            mCheckIn = extras.getString("dateIn");
            mCheckOut = extras.getString("dateOut");
            mDays = extras.getString("days");
            tvCheckIn.setText(mCheckIn.substring(5));//把年份截取掉，只留月份和日期
            tvCheckOut.setText(mCheckOut.substring(5));
            tvTotalDays.setText(mDays + "晚");
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            Bundle bundle = data.getBundleExtra("bundle"); //city即为回传的值
            mCityName = bundle.getString("city");
            mCityId = bundle.getString("cityId");
            tvTopbarTitle.setText(mCityName);
            getDataFromServer();
        }
    }


    @OnClick({R.id.iv_back, R.id.ll_city_select, R.id.ll_date_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_city_select:
                Intent intent = new Intent();
                intent.putExtra("from", "foreign");
                intent.setClass(mActivity, CitySelectActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.ll_date_check:
                startActivityForResult(new Intent(
                        ForeignHotelListActivity.this, DatePickerActivity.class), 301);
                break;
        }
    }
}
