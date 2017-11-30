package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.FlightSpecialSaleBean;
import cn.houno.houniaolvju.bean.FlightSpecialSaleBean.DataBean;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 特价机票适配器
 * Created by qzc on 2017/2/22.
 */

public class FlightSpecialSaleAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<DataBean> mList;

    public FlightSpecialSaleAdapter(Context context, List<DataBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<DataBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public DataBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.griditem_flight_special, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvDepCity.setText(mList.get(position).getDepName());
        holder.tvArrCity.setText(mList.get(position).getArrName());
        MyText2Utils.formatTicketPrice(mContext, holder.tvPrice, mList.get(position).getTicketPrice() + "");
        holder.tvDepDate.setText(mList.get(position).getDepDate());
        return convertView;
    }



    static class ViewHolder {
        @Bind(R.id.tv_dep_city)
        TextView tvDepCity;
        @Bind(R.id.tv_arr_city)
        TextView tvArrCity;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.tv_dep_date)
        TextView tvDepDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
