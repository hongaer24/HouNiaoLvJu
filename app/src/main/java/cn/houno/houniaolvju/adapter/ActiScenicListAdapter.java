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
import cn.houno.houniaolvju.bean.ActiScenicListBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：活动-景点门票
 * 创建人：qzc
 * 创建时间：2016/12/28 14:57
 * 修改人：qzc
 * 修改时间：2016/12/28 14:57
 * 修改备注：
 */
public class ActiScenicListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflate;
    private List<ActiScenicListBean.DataBean> mList;

    public ActiScenicListAdapter(Context context) {
        mContext = context;
        mInflate = LayoutInflater.from(context);
    }

    public ActiScenicListAdapter(Context context, List<ActiScenicListBean.DataBean> list) {
        mContext = context;
        mList = list;
        mInflate = LayoutInflater.from(context);
    }

    public void setData(List<ActiScenicListBean.DataBean> list) {
        mList = list;
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
        if (convertView == null) {
            convertView = mInflate.inflate(R.layout.listitem_scenic, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_scenic_title);
            viewHolder.tvStar = (TextView) convertView.findViewById(R.id.tv_scenic_star);
            viewHolder.tvCate = (TextView) convertView.findViewById(R.id.tv_scenic_cate);
            viewHolder.tvHits = (TextView) convertView.findViewById(R.id.tv_scenic_hits);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_scenic_price);
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_scenic_img);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        x.image().bind(viewHolder.ivImg, mList.get(position).getImg(), DisplayUtil.getImageOptions());
        viewHolder.tvTitle.setText(mList.get(position).getTitle());
        viewHolder.tvStar.setText(mList.get(position).getStar());
        viewHolder.tvCate.setText(mList.get(position).getCate_name());
        viewHolder.tvHits.setText(mList.get(position).getHits());
        MyText2Utils.formatYuanPrice(mContext, viewHolder.tvPrice, mList.get(position).getPrice().getWebprice());
        return convertView;
    }

    static class ViewHolder {
        ImageView ivImg;
        TextView tvTitle;
        TextView tvStar;
        TextView tvCate;
        TextView tvHits;
        TextView tvPrice;
    }
}
