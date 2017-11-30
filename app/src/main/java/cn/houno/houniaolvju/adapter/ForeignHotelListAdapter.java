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
import cn.houno.houniaolvju.bean.ForeignHotelListBean.DataBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：Houniaolvju
 * 类描述：国际酒店列表
 * 创建人：qzc
 * 创建时间：2016/12/19 18:58
 * 修改人：qzc
 * 修改时间：2016/12/19 18:58
 * 修改备注：
 */
public class ForeignHotelListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<DataBean> mList;


    public ForeignHotelListAdapter(Context context,List<DataBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<DataBean> datas) {
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
            convertView = mInflater.inflate(R.layout.listitem_hotel_foreign, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_foreign_hotel_title);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_foreign_hotel_price);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tv_foreign_hotel_address);
            viewHolder.tvScenic = (TextView) convertView.findViewById(R.id.tv_foreign_hotel_scenic);
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_foreign_hotel_img);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(mList.get(position).getTitle().trim());
        viewHolder.tvAddress.setText(mList.get(position).getAddress().trim());
        viewHolder.tvScenic.setText(mList.get(position).getScenic().trim());

        MyText2Utils.formatQiPrice(mContext, viewHolder.tvPrice, mList.get(position).getRoomInfo().getWebprice());
        x.image().bind(viewHolder.ivImg, mList.get(position).getImg(), DisplayUtil.getImageOptions());

        return convertView;
    }

    static class ViewHolder {

        ImageView ivImg;
        TextView tvTitle;
        TextView tvPrice;
        TextView tvAddress;
        TextView tvScenic;

    }

}
