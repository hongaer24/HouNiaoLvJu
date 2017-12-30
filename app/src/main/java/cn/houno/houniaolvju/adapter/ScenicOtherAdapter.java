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
import cn.houno.houniaolvju.bean.ScenicDetailBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：Houniaolvju
 * 类描述：景点详情-周边
 * 创建人：qzc
 * 创建时间：2017/01/05 14:51
 * 修改人：qzc
 * 修改时间：2017/01/05 14:51
 * 修改备注：
 */
public class ScenicOtherAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ScenicDetailBean.DataBean.OtherBean> mList;

    public ScenicOtherAdapter(Context context,List<ScenicDetailBean.DataBean.OtherBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<ScenicDetailBean.DataBean.OtherBean> list) {
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
        ViewHolder viewHolder ;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.griditem_scenic_like, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_like_title);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_like_price);
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_like_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        x.image().bind(viewHolder.ivImg, mList.get(position).getDefaultpic(), DisplayUtil.getImageOptions());
        viewHolder.tvTitle.setText(mList.get(position).getScenicname());
        MyText2Utils.formatYuanPrice(mContext, viewHolder.tvPrice, mList.get(position).getWebprice());
        return convertView;
    }

    static class ViewHolder {
        ImageView ivImg;
        TextView tvTitle;
        TextView tvPrice;
    }

}
