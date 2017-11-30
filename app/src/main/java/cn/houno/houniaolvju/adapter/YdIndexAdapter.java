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
import cn.houno.houniaolvju.bean.YdIndexBean.DataBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 异地养老首页列表数据
 * Created by Administrator on 2017/1/14.
 */

public class YdIndexAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<DataBean> mDatas;

    public YdIndexAdapter(Context context, List<DataBean> datas) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<DataBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.listitem_yd_index, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_yd_index_img);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_yd_index_price);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_yd_index_title);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tv_yd_index_address);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        x.image().bind(viewHolder.ivImg, mDatas.get(position).getImg(), DisplayUtil.getImageOptions());
        viewHolder.tvTitle.setText(mDatas.get(position).getTitle());
        viewHolder.tvAddress.setText(mDatas.get(position).getAddress());
        MyText2Utils.formatYuanPrice(mContext, viewHolder.tvPrice, mDatas.get(position).getPrice());
        return convertView;
    }

    static class ViewHolder {
        ImageView ivImg;
        TextView tvPrice;
        TextView tvTitle;
        TextView tvAddress;
    }
}
