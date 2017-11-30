package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.CityListViewData;
import cn.houno.houniaolvju.view.InnerGridView;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：城市列表适配器
 * 创建人：qzc
 * 创建时间：2016/10/5 23:26
 * 修改人：qzc
 * 修改时间：2016/10/5 23:26
 * 修改备注：
 */
public class CityListViewAdapter extends BaseAdapter {

    private List<CityListViewData> listData;
    private Context mContext;

    public CityListViewAdapter(Context context, List<CityListViewData> listData) {
        mContext = context;
        this.listData = listData;
    }

    public void setData(List<CityListViewData> data) {
        listData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return listData.size();
    }

    @Override
    public Object getItem(int position) {

        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ListHolder holder;

        if (null == convertView) {
            holder = new ListHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem_city_item, parent, false);

            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.gridview = (InnerGridView) convertView.findViewById(R.id.gridview);

            convertView.setTag(holder);
        } else {
            holder = (ListHolder) convertView.getTag();
        }

        holder.name.setText(listData.get(position).getName());

        holder.gridview.setNumColumns(4);
        holder.gridview.setVerticalSpacing(10);
        holder.gridview.setHorizontalSpacing(10);
        holder.gridview.setPadding(10, 10, 10, 10);
        holder.gridview.setBackgroundColor(Color.parseColor("#00000000"));

        holder.gridview.setAdapter(new CityGridViewAdapter(mContext, listData.get(position).getGridData()));

        return convertView;
    }

    public static class ListHolder {
        TextView name;
        InnerGridView gridview;
    }
}
