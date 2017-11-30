package cn.houno.houniaolvju.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.CityGridViewData;
import cn.houno.houniaolvju.activity.CitySelectActivity;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：字母下的城市适配器
 * 创建人：qzc
 * 创建时间：2016/10/6 1:19
 * 修改人：qzc
 * 修改时间：2016/10/6 1:19
 * 修改备注：
 */
public class CityGridViewAdapter extends BaseAdapter {

    private List<CityGridViewData> list;
    private Context mContext;

    public CityGridViewAdapter(Context context, List<CityGridViewData> data) {
        mContext = context;
        list = data;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        GridHolder holder;

        if (null == convertView) {
            holder = new GridHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.griditem_city_list, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.tv_start);
            convertView.setTag(holder);
        } else {
            holder = (GridHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        holder.name.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String city = list.get(position).getName();
                String id = list.get(position).getId();
                CitySelectActivity activity = (CitySelectActivity) mContext;
                activity.getActvSearch().setText(city);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("city", city);
                bundle.putString("cityId", id);
                intent.putExtra("bundle",bundle);
                activity.setResult(Activity.RESULT_OK, intent);
                activity.finish();
            }
        });

        return convertView;
    }

    public static class GridHolder {
        TextView name;
    }
}
