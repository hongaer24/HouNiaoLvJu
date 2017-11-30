package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.flight.FillInFlightOrderActivity;
import cn.houno.houniaolvju.bean.FlightCity;
import cn.houno.houniaolvju.bean.FlightListBean.DataBean.FlightsBean;
import cn.houno.houniaolvju.bean.FlightListBean.DataBean.FlightsBean.SeatItemsBean;
import cn.houno.houniaolvju.fragment.myinfo.LoginActivity;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.view.Border2TextView;

/**
 * 机票列表详情（座位）
 * Created by qzc on 2017/2/15.
 */

public class FlightSeatAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private FlightsBean mFlightBean;
    private List<SeatItemsBean> mFlightsList;
    private FlightCity mDepCity;
    private FlightCity mArrCity;
    private String mDepDate;

    public FlightSeatAdapter(Context context, List<SeatItemsBean> flightsList) {
        mContext = context;
        mFlightsList = flightsList;
        mInflater = LayoutInflater.from(context);
    }

    public void setParams(FlightsBean flightsBean, FlightCity depCity
            , FlightCity arrCity, String depDate) {
        mFlightBean = flightsBean;
        mDepCity = depCity;
        mArrCity = arrCity;
        mDepDate = depDate;
    }

    @Override
    public int getCount() {
        return mFlightsList == null ? 0 : mFlightsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFlightsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.listitem_flight_seat, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvSeatMsg.setText(mFlightsList.get(position).getSeatMsg());
        String seatStatus = mFlightsList.get(position).getSeatStatus();
        if (TextUtils.isDigitsOnly(seatStatus)) {
            holder.tvSeatStatus.setVisibility(View.VISIBLE);
            holder.tvSeatStatus.setText("剩余" + seatStatus + "张");
        } else {
            holder.tvSeatStatus.setVisibility(View.INVISIBLE);
        }
        if (mFlightsList.get(position).getPrice() > mFlightsList.get(position).getParPrice()) {
            MyText2Utils.formatTicketPrice(mContext, holder.tvFlightPrice, mFlightsList.get(position).getPrice() + "");
        }else {
            MyText2Utils.formatTicketPrice(mContext, holder.tvFlightPrice, mFlightsList.get(position).getParPrice() + "");
        }
        holder.tvFlightBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLogined = PrefUtils.getBoolean(mContext, "isLogined", false);
                if (isLogined) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, FillInFlightOrderActivity.class);
                    intent.putExtra("dep_date", mDepDate);
                    intent.putExtra("arr_date", mFlightBean.getParam1());
                    intent.putExtra("dep_city", mDepCity);
                    intent.putExtra("arr_city", mArrCity);
                    intent.putExtra("flight_bean", mFlightBean);
                    intent.putExtra("flight_seat", mFlightBean.getSeatItems().get(position));
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent();
                    intent.setClass(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_seat_msg)
        TextView tvSeatMsg;
        @Bind(R.id.tv_seat_status)
        TextView tvSeatStatus;
        @Bind(R.id.tv_flight_price)
        TextView tvFlightPrice;
        @Bind(R.id.tv_flight_book)
        Border2TextView tvFlightBook;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
