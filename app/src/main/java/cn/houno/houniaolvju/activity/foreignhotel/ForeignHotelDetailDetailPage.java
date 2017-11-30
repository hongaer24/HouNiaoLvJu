package cn.houno.houniaolvju.activity.foreignhotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.hotel.HotelDetailDetailActivity;
import cn.houno.houniaolvju.bean.ForeignHotelDetailBean;
import cn.houno.houniaolvju.bean.HotelDetailBean;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店详情-酒店详情
 * 创建人：qzc
 * 创建时间：2016/12/23 17:20
 * 修改人：qzc
 * 修改时间：2016/12/23 17:20
 * 修改备注：
 */
public class ForeignHotelDetailDetailPage extends Fragment {

    @Bind(R.id.rl_hotel_detail_info)
    RelativeLayout mRlHotelDetailInfo;
    @Bind(R.id.tv_hotel_open_time)
    TextView mTvHotelOpenTime;
    @Bind(R.id.ll_free_wifi)
    LinearLayout mLlFreeWifi;
    @Bind(R.id.ll_free_parking)
    LinearLayout mLlFreeParking;
    @Bind(R.id.ll_tv)
    LinearLayout mLlTv;
    @Bind(R.id.ll_fitness)
    LinearLayout mLlFitness;
    @Bind(R.id.ll_swimming)
    LinearLayout mLlSwimming;
    @Bind(R.id.ll_pickup)
    LinearLayout mLlPickup;
    @Bind(R.id.ll_meeting)
    LinearLayout mLlMeeting;
    @Bind(R.id.ll_business)
    LinearLayout mLlBusiness;

    private View mView;

    private ForeignHotelDetailBean.DataBean.InfoBean fInfoBean;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.page_hoteldetail_detail, container, false);
        ButterKnife.bind(this, mView);
        initData();
        return mView;
    }

    private void initData() {
        Bundle arguments = getArguments();
        fInfoBean = (ForeignHotelDetailBean.DataBean.InfoBean) arguments.getSerializable("data");
        if (fInfoBean != null) {
            setForeignPageData(fInfoBean);
            mRlHotelDetailInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("title", fInfoBean.getTitle());
                    intent.putExtra("phone", fInfoBean.getTelphone());
                    intent.putExtra("address", fInfoBean.getAddress());
                    intent.putExtra("peitao", fInfoBean.getPeitao().toString());
                    intent.putExtra("info", fInfoBean.getInfo());
                    intent.putExtra("around", fInfoBean.getAround());
                    intent.setClass(getActivity(), HotelDetailDetailActivity.class);
                    startActivity(intent);
                }
            });
        }
    }


    private void setForeignPageData(ForeignHotelDetailBean.DataBean.InfoBean infoBean) {
        mTvHotelOpenTime.setText("开业时间：" + infoBean.getOpentime());
        setPeitaoData(infoBean.getPeitao().toString());
    }

    private void setPeitaoData(String peitao) {
        int num = 0;
        if (peitao.contains("WIFI")) {
            mLlFreeWifi.setVisibility(View.VISIBLE);
            num++;
            if (num == 4) {
                return;
            }

        }
        if (peitao.contains("健身")) {
            mLlFitness.setVisibility(View.VISIBLE);
            num++;
            if (num == 4) {
                return;
            }

        }
        if (peitao.contains("游泳")) {
            mLlSwimming.setVisibility(View.VISIBLE);
            num++;
            if (num == 4) {
                return;
            }

        }
        if (peitao.contains("停车")) {
            mLlFreeParking.setVisibility(View.VISIBLE);
            num++;
            if (num == 4) {
                return;
            }

        }
        if (peitao.contains("电视")) {
            mLlTv.setVisibility(View.VISIBLE);
            num++;
            if (num == 4) {
                return;
            }

        }
        if (peitao.contains("接送")) {
            mLlPickup.setVisibility(View.VISIBLE);
            num++;
            if (num == 4) {
                return;
            }

        }
        if (peitao.contains("会议")) {
            mLlMeeting.setVisibility(View.VISIBLE);
            num++;
            if (num == 4) {
                return;
            }

        }
        if (peitao.contains("商务")) {
            mLlBusiness.setVisibility(View.VISIBLE);
            num++;
            if (num == 4) {
                return;
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
