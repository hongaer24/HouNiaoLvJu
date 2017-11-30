package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.ForeignHotelDetailBean.DataBean.OtherHotelBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 国际酒店详情周边酒店
 * Created by Administrator on 2017/1/13.
 */

public class ForeignOtherHotelAdapter extends BaseAdapter {
    private List<OtherHotelBean> list;
    private LayoutInflater mInflater;
    private Context mContext;

    public ForeignOtherHotelAdapter(Context context, List<OtherHotelBean> list) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.list = list;
    }


    public void setData(List<OtherHotelBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_other_hotel, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_other_img);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_other_title);
            viewHolder.room = (TextView) convertView.findViewById(R.id.tv_other_room);
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_other_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        x.image().bind(viewHolder.image, list.get(position).getImg(), DisplayUtil.getImageOptions());
        viewHolder.title.setText(list.get(position).getTitle());
        viewHolder.room.setText(list.get(position).getRoomInfo().getTitle());
        MyText2Utils.formatQiPrice(mContext, viewHolder.price, list.get(position).getRoomInfo().getWebprice());
        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView title;
        TextView room;
        TextView price;
    }
}