package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.FlightCity;

/**
 * author zaaach on 2016/1/26.
 */
public class HotCityGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<FlightCity> mHotCities;

    public HotCityGridAdapter(Context context,List<FlightCity> hotCitys) {
        this.mContext = context;
        this.mHotCities = hotCitys;
    }

    @Override
    public int getCount() {
        return mHotCities == null ? 0 : mHotCities.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotCities == null ? null : mHotCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        HotCityViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.cp_item_hot_city_gridview, parent, false);
            holder = new HotCityViewHolder();
            holder.name = (TextView) view.findViewById(R.id.tv_hot_city_name);
            view.setTag(holder);
        }else{
            holder = (HotCityViewHolder) view.getTag();
        }
        holder.name.setText(mHotCities.get(position).getAreaname());
        return view;
    }

    public static class HotCityViewHolder{
        TextView name;
    }
}
