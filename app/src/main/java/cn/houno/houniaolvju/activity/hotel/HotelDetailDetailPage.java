package cn.houno.houniaolvju.activity.hotel;

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
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.ForeignHotelDetailBean;
import cn.houno.houniaolvju.bean.HotelDetailBean;
import cn.houno.houniaolvju.bean.HotelDetailBean.DataBean.InfoBean;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店详情-酒店详情
 * 创建人：qzc
 * 创建时间：2016/12/23 17:20
 * 修改人：qzc
 * 修改时间：2016/12/23 17:20
 * 修改备注：
 */
public class HotelDetailDetailPage extends Fragment {

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

    private String mFrom;
    private HotelDetailBean.DataBean.InfoBean hInfoBean;
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
        hInfoBean = (HotelDetailBean.DataBean.InfoBean) arguments.getSerializable("data");
        if (hInfoBean != null) {
            setPageData(hInfoBean);
        }
    }

    private void setPageData(InfoBean infoBean) {
        mTvHotelOpenTime.setText("开业时间：" + infoBean.getOpentime());
        setPeitaoData(infoBean.getPeitao());
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

    @OnClick(R.id.rl_hotel_detail_info)
    public void onClick() {
        Intent intent = new Intent();
        intent.putExtra("title", hInfoBean.getTitle());
        intent.putExtra("phone", hInfoBean.getTelphone());
        intent.putExtra("address", hInfoBean.getAddress());
        intent.putExtra("peitao", hInfoBean.getPeitao());
        intent.putExtra("info", hInfoBean.getInfo());
        intent.putExtra("around", hInfoBean.getAround());
        intent.setClass(getActivity(), HotelDetailDetailActivity.class);
        startActivity(intent);
    }
}
