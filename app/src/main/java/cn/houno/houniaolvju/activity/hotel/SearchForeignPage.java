package cn.houno.houniaolvju.activity.hotel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.CitySelectActivity;
import cn.houno.houniaolvju.activity.DatePickerActivity;
import cn.houno.houniaolvju.activity.foreignhotel.ForeignHotelListActivity;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店搜索-海外
 * 创建人：qzc
 * 创建时间：2016/12/19 14:27
 * 修改人：qzc
 * 修改时间：2016/12/19 14:27
 * 修改备注：
 */
public class SearchForeignPage extends Fragment {

    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.tv_check_in)
    TextView tvCheckIn;
    @Bind(R.id.tv_check_in_week)
    TextView tvCheckInWeek;
    @Bind(R.id.tv_total_days)
    TextView tvTotalDays;
    @Bind(R.id.tv_check_out)
    TextView tvCheckOut;
    @Bind(R.id.tv_check_out_week)
    TextView tvCheckOutWeek;
    @Bind(R.id.ll_date_check)
    LinearLayout llForeignDateCheck;
    @Bind(R.id.et_search_keyword)
    EditText etSearchKeyword;
    @Bind(R.id.rl_location)
    RelativeLayout rlLocation;
    @Bind(R.id.tv_search_hotel)
    TextView tvSearchHotel;
    @Bind(R.id.tv_destination_txt)
    TextView tvDestinationTxt;

    private Activity mActivity;
    private String mCityName="";
    private String mCityId="";

    private String inday;
    private String outday;
    private String days;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_search_foreign, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        mActivity = getActivity();

        inday = DateUtil.getNowTime(DateUtil.DATE_SMALL_STR);
        outday = DateUtil.getTomorrowTime(inday, DateUtil.DATE_SMALL_STR);
        MyText2Utils.formatSearchDate(getActivity(), tvCheckIn, DateUtil.getOtherDateStr(inday));
        MyText2Utils.formatSearchDate(getActivity(), tvCheckOut, DateUtil.getOtherDateStr(outday));
        tvCheckInWeek.setText(DateUtil.getWeek(inday));
        tvCheckOutWeek.setText(DateUtil.getWeek(outday));
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
            tvTotalDays.setText(days + "晚");
            MyText2Utils.formatSearchDate(mActivity, tvCheckIn, DateUtil.getOtherDateStr(inday));
            MyText2Utils.formatSearchDate(mActivity, tvCheckOut, DateUtil.getOtherDateStr(outday));
            tvCheckInWeek.setText(extras.getString("dateInWeek"));
            tvCheckOutWeek.setText(extras.getString("dateOutWeek"));
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            Bundle bundle = data.getBundleExtra("bundle"); //city即为回传的值
            mCityName = bundle.getString("city");
            mCityId = bundle.getString("cityId");
            tvCity.setText(mCityName);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_city, R.id.ll_date_check, R.id.tv_search_hotel})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_city:
                intent.putExtra("from","foreign");
                intent.setClass(mActivity, CitySelectActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.ll_date_check:
                intent.setClass(mActivity, DatePickerActivity.class);
                startActivityForResult(intent, 299);
                break;
            case R.id.tv_search_hotel:
                intent.setClass(mActivity, ForeignHotelListActivity.class);
                intent.putExtra("checkIn", inday);
                intent.putExtra("checkOut", outday);
                intent.putExtra("cityId", mCityId);
                intent.putExtra("cityName", mCityName);
                intent.putExtra("days", days);
                intent.putExtra("keyword", etSearchKeyword.getText().toString().trim());
                startActivity(intent);
                break;
        }
    }
}
