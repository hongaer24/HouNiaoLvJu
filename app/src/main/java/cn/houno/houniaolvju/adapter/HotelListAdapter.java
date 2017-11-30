package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.ArrayList;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.HotelListBean.DataBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店列表
 * 创建人：qzc
 * 创建时间：2016/12/19 18:58
 * 修改人：qzc
 * 修改时间：2016/12/19 18:58
 * 修改备注：
 */
public class HotelListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<DataBean> mList;


    public HotelListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setDatas(ArrayList<DataBean> datas) {
        mList = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.listitem_hotel, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_hotel_address);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_hotel_price);
            viewHolder.tvStreet = (TextView) convertView.findViewById(R.id.tv_hotel_street);
            viewHolder.tvAverage = (TextView) convertView.findViewById(R.id.tv_hotel_average);
            viewHolder.tvStar = (TextView) convertView.findViewById(R.id.tv_hotel_star);
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_hotel_img);

            viewHolder.ivWifi = (ImageView) convertView.findViewById(R.id.iv_hotel_wifi);
            viewHolder.ivPark = (ImageView) convertView.findViewById(R.id.iv_hotel_park);
            viewHolder.ivBreakfast = (ImageView) convertView.findViewById(R.id.iv_hotel_breakfast);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tvTitle.setText(mList.get(position).getTitle());
        viewHolder.tvStreet.setText(mList.get(position).getStreet());
        String average = mList.get(position).getAverage();
        if (average != null && !TextUtils.isEmpty(average)) {
            int averageInt = (int)(Double.parseDouble(average) / 5.0*100);
            viewHolder.tvAverage.setText(averageInt + "%好评");
        }
        viewHolder.tvStar.setText(MyText2Utils.getStar(mList.get(position).getStar()));
        MyText2Utils.formatQiPrice(mContext, viewHolder.tvPrice, mList.get(position).getWebprice());
        x.image().bind(viewHolder.ivImg, mList.get(position).getImg(), DisplayUtil.getImageOptions());
        if (mList.get(position).getIs_wifi() != null && mList.get(position).getIs_wifi().equals("1")) {
            viewHolder.ivWifi.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivWifi.setVisibility(View.GONE);
        }
        if (mList.get(position).getIs_park() != null && mList.get(position).getIs_park().equals("1")) {
            viewHolder.ivPark.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivPark.setVisibility(View.GONE);
        }
        if (mList.get(position).getIs_breakfast() != null && mList.get(position).getIs_breakfast().equals("1")) {
            viewHolder.ivBreakfast.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivBreakfast.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ViewHolder {

        ImageView ivImg;
        TextView tvTitle;
        TextView tvPrice;
        TextView tvAverage;
        TextView tvStreet;
        TextView tvStar;

        ImageView ivWifi;
        ImageView ivPark;
        ImageView ivBreakfast;
    }

}
