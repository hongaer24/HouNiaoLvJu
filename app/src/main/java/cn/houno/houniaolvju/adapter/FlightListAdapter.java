package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.FlightListBean.DataBean.FlightsBean;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 机票列表
 * Created by qzc on 2017/2/14.
 */

public class FlightListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<FlightsBean> mFlightsList = new ArrayList<>();

    public FlightListAdapter(Context context, List<FlightsBean> flightsList) {
        mContext = context;
        mFlightsList = flightsList;
        mInflater = LayoutInflater.from(context);
    }


    public void setData(List<FlightsBean> flightsList) {
        mFlightsList = flightsList;
        notifyDataSetChanged();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.listitem_flight_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvDeptime.setText(mFlightsList.get(position).getDepTime());
        holder.tvArritime.setText(mFlightsList.get(position).getArriTime());
        if (mFlightsList.get(position).getSeatItems().get(0).getStopNum() != 0) {
            holder.tvStop.setVisibility(View.VISIBLE);
        } else {
            holder.tvStop.setVisibility(View.INVISIBLE);
        }
        holder.tvDepName.setText(mFlightsList.get(position).getDepAirport()
                + mFlightsList.get(position).getOrgJetquay());
        holder.tvArrname.setText(mFlightsList.get(position).getArrAirport()
                + mFlightsList.get(position).getDstJetquay());
        holder.tvAviname.setText(mFlightsList.get(position).getAviName() + mFlightsList.get(position).getFlightNo());
        holder.tvPlaneType.setText("机型" + mFlightsList.get(position).getPlaneType());
        if (mFlightsList.get(position).getPrice() > mFlightsList.get(position).getParPrice()) {
            MyText2Utils.formatTicketPrice(mContext, holder.tvPrice, mFlightsList.get(position).getPrice() + "");
        }else {
            MyText2Utils.formatTicketPrice(mContext, holder.tvPrice, mFlightsList.get(position).getParPrice() + "");
        }
        String seatStatus = mFlightsList.get(position).getSeatItems().get(0).getSeatStatus();
        if (TextUtils.isDigitsOnly(seatStatus)) {
            holder.tvSeatStatus.setText("剩余" + seatStatus + "张");
        } else {
            holder.tvSeatStatus.setText("");
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_deptime)
        TextView tvDeptime;
        @Bind(R.id.tv_stop)
        TextView tvStop;
        @Bind(R.id.rl_flight_view)
        RelativeLayout rlFlightView;
        @Bind(R.id.tv_arritime)
        TextView tvArritime;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.tv_depName)
        TextView tvDepName;
        @Bind(R.id.tv_arrname)
        TextView tvArrname;
        @Bind(R.id.tv_aviname)
        TextView tvAviname;
        @Bind(R.id.tv_plane_type)
        TextView tvPlaneType;
        @Bind(R.id.tv_seat_status)
        TextView tvSeatStatus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}