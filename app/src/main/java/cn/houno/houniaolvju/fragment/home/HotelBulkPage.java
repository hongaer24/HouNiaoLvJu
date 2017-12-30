package cn.houno.houniaolvju.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.x;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.hotel.HotelDetailActivity;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.TgHotelBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：Houniaolvju
 * 类描述：首页-酒店拼团
 * 创建人：qzc
 * 创建时间：2016/12/13 14:08
 * 修改人：qzc
 * 修改时间：2016/12/13 14:08
 * 修改备注：
 */
public class HotelBulkPage extends Fragment {


    public View mView;
    private LinearLayout llView;
    private FrameLayout flTgLabel;
    private ImageView ivHotelImg;
    private TextView tvHotelTitle;
    private TextView tvStreet;
    private TextView tvAverage;
    private TextView tvStar;
    private TextView tvPrice;

    private ImageView ivWifi;
    private ImageView ivPark;
    private ImageView ivBreakfast;

    private String hid;

    TgHotelBean data;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.page_home_bulk, container, false);
        initView(mView);
        initData();
        initEvent();
        return mView;
    }


    private void initView(View view) {
        llView = (LinearLayout) view.findViewById(R.id.ll_view);
        flTgLabel = (FrameLayout) view.findViewById(R.id.fl_hotel_label);
        ivHotelImg = (ImageView) view.findViewById(R.id.iv_hotel_img);
        tvHotelTitle = (TextView) view.findViewById(R.id.tv_hotel_address);
        tvStreet = (TextView) view.findViewById(R.id.tv_hotel_street);
        tvAverage = (TextView) view.findViewById(R.id.tv_hotel_average);
        tvStar = (TextView) view.findViewById(R.id.tv_hotel_star);
        tvPrice = (TextView) view.findViewById(R.id.tv_hotel_price);
        ivWifi = (ImageView) view.findViewById(R.id.iv_hotel_wifi);
        ivPark = (ImageView) view.findViewById(R.id.iv_hotel_park);
        ivBreakfast = (ImageView) view.findViewById(R.id.iv_hotel_breakfast);
    }

    private void initEvent() {
        llView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("from", "home");
                intent.putExtra("hid", hid);
                intent.setClass(getActivity(), HotelDetailActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initData() {
        Bundle arguments = getArguments();
        data = (TgHotelBean) arguments.getSerializable("data");
        if (data != null) {
            setPageData(data);
        }else {
            llView.setVisibility(View.GONE);
        }
    }

    public void setPageData(TgHotelBean data) {
        if (flTgLabel.getVisibility() != View.VISIBLE) {
            flTgLabel.setVisibility(View.VISIBLE);
        }
        if (null != data) {
            llView.setVisibility(View.VISIBLE);
            hid = data.getId();
            x.image().bind(ivHotelImg, data.getImg(), DisplayUtil.getImageOptions());
            tvHotelTitle.setText(data.getTitle());
            tvStreet.setText(data.getStreet());
            String average = data.getAverage();
            if (average != null && !TextUtils.isEmpty(average)) {
                int averageInt = (int) (Double.parseDouble(average) / 5.0 * 100);
                tvAverage.setText(averageInt + "%好评");
            }
            tvStar.setText(MyText2Utils.getStar(data.getStar()));

            MyText2Utils.formatYuanPrice(getActivity(), tvPrice, data.getRoom().getWebprice());

            if (data.getIs_wifi() != null && data.getIs_wifi().equals("1")) {
                ivWifi.setVisibility(View.VISIBLE);
            } else {
                ivWifi.setVisibility(View.GONE);
            }
            if (data.getIs_park() != null && data.getIs_park().equals("1")) {
                ivPark.setVisibility(View.VISIBLE);
            } else {
                ivPark.setVisibility(View.GONE);
            }
            if (data.getIs_breakfast() != null && data.getIs_breakfast().equals("1")) {
                ivBreakfast.setVisibility(View.VISIBLE);
            } else {
                ivBreakfast.setVisibility(View.GONE);
            }
        }else {
            llView.setVisibility(View.GONE);
        }
    }
}



