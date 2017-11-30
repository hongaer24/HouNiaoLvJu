package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.PolicySpi;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.scenic.ScenicListActivity;
import cn.houno.houniaolvju.bean.ScenicCateBean.DataBean;

/**
 * 项目名称：Houniaolvju
 * 类描述：景点分类
 * 创建人：qzc
 * 创建时间：2017/01/03 15:02
 * 修改人：qzc
 * 修改时间：2017/01/03 15:02
 * 修改备注：
 */
public class ScenicCateAdapter extends RecyclerView.Adapter<ScenicCateAdapter.ViewHolder>  {

    private LayoutInflater mInflater;
    private List<DataBean> mDatas;
    private Context mContext;



    public ScenicCateAdapter(Context context, List<DataBean> datas) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDatas = datas;
    }

    public void setData(List<DataBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rclvitem_scenic_cate,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvCate.setText(mDatas.get(position).getName());
        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCate;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvCate = (TextView) itemView
                    .findViewById(R.id.tv_cate_title);

        }


    }
}
