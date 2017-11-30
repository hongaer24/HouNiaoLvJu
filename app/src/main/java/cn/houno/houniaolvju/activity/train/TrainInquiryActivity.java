package cn.houno.houniaolvju.activity.train;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerGridView;

/**
 * 查询火车票入口界面
 */
public class TrainInquiryActivity extends Activity {

    //出发城市
    @Bind(R.id.tv_dep_city)
    TextView tvDepCity;

    //目的城市
    @Bind(R.id.tv_arr_city)
    TextView tvArrCity;


    @Bind(R.id.iv_back)
    ImageView ivBack;

    @Bind(R.id.btn_change)
    ImageView btnChange;

    @Bind(R.id.ll_select_date)
    LinearLayout linearLayout;

    @Bind(R.id.btn_query)
    TextView tvQuery;

    @Bind(R.id.tv_select_date)
    TextView tvSelectDate;

    @Bind(R.id.tv_select_week)
    TextView tvSelectWeek;

    @Bind(R.id.gv_special_train)
    InnerGridView gvSpecialTrain;

    String date;
    TrainCityBean depCityBean, arrCityBean;

    public static final int REQUEST_CODE_FROM_QUERY = 1111;
    public static final int REQUEST_CODE_DEP_CITY = 2222;
    public static final int REQUEST_CODE_ARR_CITY = 3333;
    public static final int FROM_CITY=1;
    public static final int TO_CITY=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_inquiry);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(TrainInquiryActivity.this, R.color.app_theme_green);
        initView();
    }


    private void initView() {
        //默认设置系统当前日期为出发日期格式为 yyyy-MM-dd
        date = DateUtil.getNowTime("yyyy-MM-dd");
        tvSelectDate.setText(date.split("-")[1] + "月" + date.split("-")[2]);//设置月和日
        tvSelectWeek.setText(DateUtil.getEWeek(date));//设置今天或明天或周几

        //从本地拿默认的出发城市(海口)和目的城市(北京)，如果用户已选过其他城市，则存到本地
        tvDepCity.setText(getCityNameFromPrefs(FROM_CITY));
        tvArrCity.setText(getCityNameFromPrefs(TO_CITY));

        System.out.println("查询的城市信息：" + getCityNameFromPrefs(FROM_CITY) + "," + getCityCodeFromPrefs(FROM_CITY)
                            + "," + getCityNameFromPrefs(TO_CITY) + "," + getCityCodeFromPrefs(TO_CITY)
                            +",出发日期:"+date+",周几:"+DateUtil.getEWeek(date));

    }


    @OnClick({R.id.tv_dep_city, R.id.tv_arr_city, R.id.btn_change, R.id.ll_select_date, R.id.tv_select_date, R.id.btn_query, R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dep_city: {
                startActivityForResult(new Intent(this, TrainCitySelectActivity.class), REQUEST_CODE_DEP_CITY);
                break;
            }
            case R.id.tv_arr_city: {
                startActivityForResult(new Intent(this, TrainCitySelectActivity.class), REQUEST_CODE_ARR_CITY);
                break;
            }
            case R.id.btn_change: {
                String depCityName = getCityNameFromPrefs(1);
                String depCityCode = getCityCodeFromPrefs(1);
                String arrCityName = getCityNameFromPrefs(2);
                tvDepCity.setText(arrCityName);
                tvArrCity.setText(depCityName);
                setCityDataToPrefs(1, arrCityName, getCityCodeFromPrefs(2));
                setCityDataToPrefs(2, depCityName, depCityCode);
                break;
            }

            case R.id.ll_select_date:{
                startActivityForResult(new Intent(TrainInquiryActivity.this, TrainDatePickerActivity.class), REQUEST_CODE_FROM_QUERY);
                break;
            }

            case R.id.btn_query: {
                Intent intent = new Intent(TrainInquiryActivity.this, TrainListActivity1.class);
                Bundle bundle = new Bundle();
                depCityBean = new TrainCityBean(getCityNameFromPrefs(1), getCityCodeFromPrefs(1));
                arrCityBean = new TrainCityBean(getCityNameFromPrefs(2), getCityCodeFromPrefs(2));
                bundle.putSerializable("depCity", depCityBean);
                bundle.putSerializable("arrCity", arrCityBean);
                intent.putExtras(bundle);
                intent.putExtra("date", date);
                startActivity(intent);
                break;
            }

            case R.id.iv_back: {
                finish();
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == REQUEST_CODE_FROM_QUERY) {
                date = data.getStringExtra("date");
                tvSelectDate.setText(date.split("-")[1] + "月" + date.split("-")[2]);
                tvSelectWeek.setText(DateUtil.getEWeek(date));
            }
            if (requestCode == REQUEST_CODE_DEP_CITY) {
                depCityBean = (TrainCityBean) data.getSerializableExtra("city");
                if (depCityBean != null) {
                    tvDepCity.setText(depCityBean.getName());
                    setCityDataToPrefs(1, depCityBean.getName(), depCityBean.getCode());
                }

            } else if (requestCode == REQUEST_CODE_ARR_CITY) {
                arrCityBean = (TrainCityBean) data.getSerializableExtra("city");
                if (arrCityBean != null) {
                    tvArrCity.setText(arrCityBean.getName());
                    setCityDataToPrefs(2, arrCityBean.getName(), arrCityBean.getCode());
                }

            }
            System.out.println("查询的城市信息：" + getCityNameFromPrefs(FROM_CITY) + "," + getCityCodeFromPrefs(FROM_CITY)
                    + "," + getCityNameFromPrefs(TO_CITY) + "," + getCityCodeFromPrefs(TO_CITY)
                    +",出发日期:"+date+",周几:"+DateUtil.getEWeek(date));
        }
    }

    /**
     * @param flag 1 代表出发城市，2 代表到达城市
     * @return 城市名
     */

    private String getCityNameFromPrefs(int flag) {
        String cityName;
        if (flag == 1) {
            cityName = PrefUtils.getString(this, "depCity", "海口");
        } else {
            cityName = PrefUtils.getString(this, "arrCity", "北京");
        }
        return cityName;
    }


    /**
     * @param flag 1 代表出发城市，2 代表到达城市
     * @return 城市代码
     */
    private String getCityCodeFromPrefs(int flag) {
        String cityCode;
        if (flag == 1) {
            cityCode = PrefUtils.getString(this, "depCityCode", "VUQ");
        } else {
            cityCode = PrefUtils.getString(this, "arrCityCode", "BJP");
        }
        return cityCode;
    }


    private void setCityDataToPrefs(int flag, String cityName, String cityCode) {
        if (flag == 1) {
            PrefUtils.setString(this, "depCity", cityName);
            PrefUtils.setString(this, "depCityCode", cityCode);
        } else {
            PrefUtils.setString(this, "arrCity", cityName);
            PrefUtils.setString(this, "arrCityCode", cityCode);
        }
    }

}
