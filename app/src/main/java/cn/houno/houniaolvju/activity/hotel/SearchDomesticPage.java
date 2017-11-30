package cn.houno.houniaolvju.activity.hotel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.AmbitusHotelActivity;
import cn.houno.houniaolvju.activity.CitySelectActivity;
import cn.houno.houniaolvju.activity.DatePickerActivity;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.LocationHelper;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.view.PriceLevelPopupWindow;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店搜索-国内/港澳台
 * 创建人：qzc
 * 创建时间：2016/12/19 14:27
 * 修改人：qzc
 * 修改时间：2016/12/19 14:27
 * 修改备注：
 */
public class SearchDomesticPage extends Fragment {

    @Bind(R.id.tv_city)
    TextView mTvCity;
    @Bind(R.id.ll_search_loc)
    LinearLayout mLlSearchLoc;
    @Bind(R.id.tv_check_in)
    TextView mTvCheckIn;
    @Bind(R.id.tv_check_out)
    TextView mTvCheckOut;
    @Bind(R.id.ll_date_check)
    LinearLayout mLlDateCheck;
    @Bind(R.id.rl_location)
    RelativeLayout mRlLocation;
    @Bind(R.id.rl_price_level)
    RelativeLayout mRlPriceLevel;
    @Bind(R.id.tv_search_hotel)
    TextView mTvSearchHotel;
    //    @Bind(R.id.ll_history_collection)
//    LinearLayout mLlHistoryCollection;
//    @Bind(R.id.ll_hotel_order)
//    LinearLayout mLlHotelOrder;
    @Bind(R.id.tv_check_in_week)
    TextView mTvCheckInWeek;
    @Bind(R.id.tv_check_out_week)
    TextView mTvCheckOutWeek;
    @Bind(R.id.tv_total_days)
    TextView mTvTotalDays;
    @Bind(R.id.tv_level_price)
    TextView mTvLevelPrice;
    @Bind(R.id.et_search_keyword)
    EditText mEtSearchKeyword;
    @Bind(R.id.tv_ambitus_hotel)
    TextView tvAmbitusHotel;

    private Activity mActivity;
    private String mCityId;
    private String mCityName;
    private PriceLevelPopupWindow mPopupWindow;
    private String inday;
    private String outday;
    private String days = "1";

    //定位相关
//    private LocationClient mLocationClient;
//    private MyLocationListener mLocationListener;//定位监听器
    private double mLatitude;   //纬度
    private double mLongitude;  //经度
    private String mAddrStr;    //定位地址
    private boolean isFirstLoc = true; //第一次定位是自动定位，第二次是用户点击我的位置定位
    private boolean isLoc = false; //用户是否点击我的位置，如果是则传经纬度，不是则传CityId

    private int mPrice;
    private String mPriceStr;
    private TreeSet<String> mLevel = new TreeSet<>(); //自动排序不重复
    private String mLevelStr = "不限"; //自动排序不重复

    LocationHelper locationHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_search_domestic, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        mActivity = getActivity();
        Intent intent = mActivity.getIntent();
        mCityId = intent.getStringExtra("cityId");
        mCityName = intent.getStringExtra("cityName");
        if (TextUtils.isEmpty(mCityId)) {
            locationHelper = LocationHelper.getInstance();
            locationHelper.start();
            locationHelper.setCallBack(new LocationHelper.LocationCallBack() {
                @Override
                public void callBack(BDLocation bdLocation) {
                    mLatitude = bdLocation.getLatitude();
                    mLongitude = bdLocation.getLongitude();
                    getCityId();
                }
            });
        }
        inday = DateUtil.getNowTime(DateUtil.DATE_SMALL_STR);
        outday = DateUtil.getTomorrowTime(inday, DateUtil.DATE_SMALL_STR);
        MyText2Utils.formatSearchDate(getActivity(), mTvCheckIn, DateUtil.getOtherDateStr(inday));
        MyText2Utils.formatSearchDate(getActivity(), mTvCheckOut, DateUtil.getOtherDateStr(outday));
        mTvCheckInWeek.setText(DateUtil.getWeek(inday));
        mTvCheckOutWeek.setText(DateUtil.getWeek(outday));
        mTvCity.setText(mCityName);
        //初始化定位

    }

    private void getCityId() {
        RequestParams params = new RequestParams(Constants.GET_CITY_ID);
        params.addBodyParameter("lat", mLatitude + "");
        params.addBodyParameter("lng", mLongitude + "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getInt("status") == 0) {
                        mCityName = object.getString("name");
                        mTvCity.setText(mCityName);
                        mCityId = object.getString("id");
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

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    @OnClick({R.id.tv_city, R.id.ll_search_loc, R.id.ll_date_check
            , R.id.rl_price_level, R.id.tv_search_hotel, R.id.tv_ambitus_hotel})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_city:
                intent.putExtra("from", "home");
                intent.setClass(mActivity, CitySelectActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.ll_search_loc:
                locationHelper = LocationHelper.getInstance();

                locationHelper.start();
                locationHelper.setCallBack(new LocationHelper.LocationCallBack() {
                    @Override
                    public void callBack(BDLocation bdLocation) {
                        mLatitude = bdLocation.getLatitude();
                        mLongitude = bdLocation.getLongitude();
                        Address address = bdLocation.getAddress();
                        mAddrStr = address.province + address.city + address.district + address.street + address.streetNumber;
                        if (null != address.city) {
                            mCityName = address.city;
                            if (mAddrStr.length() > 10) {
                                mTvCity.setTextSize(18);
                            }
                            mTvCity.setText(mAddrStr);
                            isLoc = true;
                        }
                    }
                });
                break;
            case R.id.ll_date_check:
                intent.setClass(mActivity, DatePickerActivity.class);
                startActivityForResult(intent, 299);
                break;

            case R.id.rl_price_level:
                showPopupWindow();
                break;
            case R.id.tv_search_hotel:
                intent.setClass(mActivity, HotelListActivity.class);
                intent.putExtra("checkIn", inday);
                intent.putExtra("checkOut", outday);
                intent.putExtra("isLoc", isLoc);
                if (isLoc) {
                    intent.putExtra("lat", mLatitude);
                    intent.putExtra("lng", mLongitude);
                } else {
                    intent.putExtra("cityId", mCityId);
                }
                intent.putExtra("cityName", mCityName);
                intent.putExtra("days", days);
                intent.putExtra("keyword", mEtSearchKeyword.getText().toString().trim());
                intent.putExtra("price", mPrice);
                intent.putExtra("level", new ArrayList<>(mLevel));
                startActivity(intent);
                break;
            case R.id.tv_ambitus_hotel:
                startActivity(new Intent(mActivity, AmbitusHotelActivity.class));
                break;
        }
    }

    /*
    * 星级价格弹窗
    * */
    private void showPopupWindow() {
        mPriceStr = "";
        mLevelStr = "";
        mPopupWindow = new PriceLevelPopupWindow(mActivity);
        mPopupWindow.showAtLocation(mRlLocation, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        mPopupWindow.setOnCheckClickListener(new PriceLevelPopupWindow.OnCheckClickListener() {
            @Override
            public void OnCheckClick(int price, TreeSet<String> level) {
                mPrice = price;
                mLevel = level;
                System.out.println(mPrice + "," + level.toString());
                setPriceLevelTxt(price, level);
                mTvLevelPrice.setTextColor(getResources().getColor(R.color.black_txt));
                mTvLevelPrice.setText(mPriceStr + "  " + mLevelStr);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 299 && resultCode == 300) {
            Bundle extras = data.getExtras();
            inday = extras.getString("dateIn");
            outday = extras.getString("dateOut");
            System.out.println("SearchDomesticPage:" + inday);
            days = extras.getString("days");
            mTvTotalDays.setText(days + "晚");
            MyText2Utils.formatSearchDate(getActivity(), mTvCheckIn, DateUtil.getOtherDateStr(inday));
            MyText2Utils.formatSearchDate(getActivity(), mTvCheckOut, DateUtil.getOtherDateStr(outday));
            mTvCheckInWeek.setText(extras.getString("dateInWeek"));
            mTvCheckOutWeek.setText(extras.getString("dateOutWeek"));
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            Bundle bundle = data.getBundleExtra("bundle"); //city即为回传的值
            mCityName = bundle.getString("city");
            mCityId = bundle.getString("cityId");
            isLoc = false;    //选择城市后设置为非定位
            mTvCity.setText(mCityName);
        }
    }

    private void setPriceLevelTxt(int price, Set<String> level) {
        switch (price) {
            case 0:
                mPriceStr = "不限";
                break;
            case 1:
                mPriceStr = "0-200";
                break;
            case 2:
                mPriceStr = "200-300";
                break;
            case 3:
                mPriceStr = "300-400";
                break;
            case 4:
                mPriceStr = "400-500";
                break;
            case 5:
                mPriceStr = "500以上";
                break;
        }
        for (int i = 0; i < level.size(); i++) {
            Object[] objects = level.toArray();
            if (level.size() != 1) {
                if (i == level.size() - 1) {
                    mLevelStr += setLevelString(objects[i]);
                } else {
                    mLevelStr += setLevelString(objects[i]) + "、";
                }
            } else {
                mLevelStr = setLevelString(level.toArray()[0]);
            }
        }
    }

    public String setLevelString(Object object) {
        String str = "";
        if (object.equals("0")) {
            str = "不限";
        } else if (object.equals("1")) {
            str = "二星/经济";
        } else if (object.equals("2")) {
            str = "三星/舒适";
        } else if (object.equals("3")) {
            str = "四星/高档";
        } else if (object.equals("4")) {
            str = "五星/豪华";
        }
        return str;
    }


    @Override
    public void onStop() {
        super.onStop();
        if (locationHelper != null) {
            locationHelper.stop();
        }
    }
}
