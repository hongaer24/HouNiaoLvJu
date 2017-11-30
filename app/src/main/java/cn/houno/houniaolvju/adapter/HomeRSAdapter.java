package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.HomeRcmdScenicBean.DataBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：Houniaolvju
 * 类描述：HomeRcmdScenicAdapter 首页-景区门票
 * 创建人：qzc
 * 创建时间：2016/12/17 10:07
 * 修改人：qzc
 * 修改时间：2016/12/17 10:07
 * 修改备注：
 */
public class HomeRSAdapter extends RecyclerView.Adapter<HomeRSAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    public ArrayList<DataBean> mDatas;

    public HomeRSAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setDatas(ArrayList<DataBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rclvitem_scenic_ticket, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageOptions options = DisplayUtil.getImageOptions();
        x.image().bind(holder.ivImg, mDatas.get(position).getImg(), options);
        int txtLength = MyText2Utils.getHotelPrice(mDatas.get(position).getPrice().getWebprice()).length();
        SpannableString styledText = new SpannableString(MyText2Utils.getHotelPrice(mDatas.get(position).getPrice().getWebprice()));
        styledText.setSpan(new TextAppearanceSpan(mContext, R.style.textStyle00), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(mContext, R.style.textStyle01), 1, txtLength - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(mContext, R.style.textStyle00), txtLength - 1, txtLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tvPrice.setText(styledText, TextView.BufferType.SPANNABLE);
        holder.tvTitle.setText(mDatas.get(position).getTitle().trim());
        holder.tvAddress.setText(mDatas.get(position).getAddress().trim());
    }


    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

         ImageView ivImg;
        TextView tvPrice;
        TextView tvTitle;
        TextView tvAddress;


        public ViewHolder(final View itemView) {
            super(itemView);

            ivImg = (ImageView) itemView.findViewById(R.id.iv_rcmd_scenic_img);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_rcmd_scenic_price);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_rcmd_scenic_title);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_rcmd_scenic_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }


    }
}
