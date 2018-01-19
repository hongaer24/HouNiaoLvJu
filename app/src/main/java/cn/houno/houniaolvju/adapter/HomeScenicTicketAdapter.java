package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.houno.houniaolvju.MainActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.HomeRcmdScenicBean.DataBean;
import cn.houno.houniaolvju.bean.ScenicIndexBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：首页-景区门票
 * 创建人：qzc
 * 创建时间：2017/1/5 17:59
 * 修改人：qzc
 * 修改时间：2017/1/5 17:59
 * 修改备注：
 */
public class HomeScenicTicketAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ScenicIndexBean.LocalBean> mDatas;

    public HomeScenicTicketAdapter(Context context, List<ScenicIndexBean.LocalBean>  datas) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

   /* public HomeScenicTicketAdapter(Context context, ArrayList<DataBean> datas) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }*/

    public void setDatas(List<ScenicIndexBean.LocalBean> datas) {
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
            convertView = mInflater.inflate(R.layout.rclvitem_scenic_ticket, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_rcmd_scenic_img);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_rcmd_scenic_price);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_rcmd_scenic_title);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tv_rcmd_scenic_address);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        x.image().bind(viewHolder.ivImg, mDatas.get(position).getDefaultpic(), DisplayUtil.getImageOptions());
        viewHolder.tvTitle.setText(mDatas.get(position).getScenicname().trim());
        viewHolder.tvAddress.setText(mDatas.get(position).getScenicaddress().trim());
        MyText2Utils.formatYuanPrice(mContext, viewHolder.tvPrice, mDatas.get(position).getSaleprice());
        return convertView;
    }

    static class ViewHolder {
        ImageView ivImg;
        TextView tvPrice;
        TextView tvTitle;
        TextView tvAddress;
    }
}
