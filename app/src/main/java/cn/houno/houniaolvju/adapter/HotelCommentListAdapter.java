package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.HotelCommentListBean;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.DisplayUtil;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：
 * 创建人：qzc
 * 创建时间：2016/12/27 19:30
 * 修改人：qzc
 * 修改时间：2016/12/27 19:30
 * 修改备注：
 */
public class HotelCommentListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<HotelCommentListBean.DataBean> mDatas;

    public HotelCommentListAdapter(Context context, List<HotelCommentListBean.DataBean> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<HotelCommentListBean.DataBean> datas) {
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
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_hotel_comment, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_hotel_comment_img);
            viewHolder.tvUserName = (TextView) convertView.findViewById(R.id.tv_hotel_comment_username);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_hotel_comment_time);
            viewHolder.rtbAverage = (RatingBar) convertView.findViewById(R.id.rtb_hotel_comment);
            viewHolder.tvContents = (TextView) convertView.findViewById(R.id.tv_hotel_comment_contents);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        x.image().bind(viewHolder.ivImg, mDatas.get(position).getHead_img(), DisplayUtil.getCircularHeaderOptions());
        viewHolder.tvUserName.setText(mDatas.get(position).getUsername());
        viewHolder.tvTime.setText(DateUtil.timeStamp2Date(mDatas.get(position).getAdd_time(), DateUtil.DATE_SMALL_STR));
        viewHolder.rtbAverage.setRating(Float.parseFloat(mDatas.get(position).getAverage()));
        viewHolder.tvContents.setText(mDatas.get(position).getContents());
        return convertView;
    }

    static class ViewHolder {
        ImageView ivImg;
        RatingBar rtbAverage;
        TextView tvContents;
        TextView tvUserName;
        TextView tvTime;
    }
}
