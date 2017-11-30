package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.CouponListBean.DataBean;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：Houniaolvju
 * 类描述：优惠券列表列表
 * 创建人：qzc
 * 创建时间：2017/01/20 10:58
 * 修改人：qzc
 * 修改时间：2017/01/20 10:58
 * 修改备注：
 */
public class CouponListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<DataBean> mList;


    public CouponListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public CouponListAdapter(Context context, List<DataBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<DataBean> list) {
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


    //0已失效 1未使用 2已使用
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.listitem_coupon, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (mList.get(position).getStatus().equals("1")) {
            viewHolder.ivImg.setImageResource(R.drawable.icon_coupon_ing_img);
            viewHolder.labelCoupon.setBackgroundResource(R.drawable.label_coupon_ing);
        } else {
            viewHolder.ivImg.setImageResource(R.drawable.icon_coupon_end_img);
            viewHolder.labelCoupon.setBackgroundResource(R.drawable.label_coupon_end);
        }
        viewHolder.tvTitle.setText(mList.get(position).getCtitle());
        viewHolder.tvName.setText(mList.get(position).getHotelname());
        viewHolder.tvTime.setText( mList.get(position).getStartdate() + "~" + mList.get(position).getEnddate());
        MyText2Utils.formatCouponPrice(mContext, viewHolder.tvPrice, mList.get(position).getPrice());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.iv_img)
        ImageView ivImg;
        @Bind(R.id.tv_order_title_txt)
        TextView tvTitle;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.label_coupon)
        LinearLayout labelCoupon;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
