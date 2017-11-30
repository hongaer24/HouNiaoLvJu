//package cn.houno.houniaolvju.adapter;
//
//import android.content.Context;
//import android.graphics.Paint;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.List;
//
//import cn.houno.houniaolvju.R;
//import cn.houno.houniaolvju.bean.YdDetailBean;
//
///**
// * Created by qzc on 2017-8-1.
// */
//
//public class YdDetailPriceAdapter extends RecyclerView.Adapter<YdDetailPriceAdapter.ViewHolder> {
//
//
//    private LayoutInflater mInflater;
//    private List<YdDetailBean.DataBean.RoomBean> mList;
//    private Context mContext;
//
//
//    public YdDetailPriceAdapter(Context context, List<YdDetailBean.DataBean.RoomBean> list) {
//        mInflater = LayoutInflater.from(context);
//        mContext = context;
//        mList = list;
//    }
//
//    public void setData(List<YdDetailBean.DataBean.RoomBean> list) {
//        mList = list;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getItemCount() {
//        return mList == null ? 0 : mList.size();
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.listitem_yddetail_price,
//                parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//
//        holder.tvTitle.setText(mList.get(position).getTitle()+" "+mList.get(position).getRztime_name().getName());
//
//        holder.tvPrice.setText("¥"+mList.get(position).getUserprice());
//
//        //判断是否设置了监听器
//        if (mOnItemClickListener != null) {
//            //为ItemView设置监听器
//            holder.btnBook.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getLayoutPosition(); // 1
//                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
//                }
//            });
//        }
//    }
//
//    private OnItemClickListener mOnItemClickListener;
//
//    public interface OnItemClickListener {
//        void onItemClick(View view, int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
//        this.mOnItemClickListener = mOnItemClickListener;
//    }
//
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView tvTitle;
//        private TextView tvPrice;
//        private Button btnBook;
//
//        private ViewHolder(final View itemView) {
//            super(itemView);
//
//            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
//            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
//            btnBook =  (Button) itemView.findViewById(R.id.btn_book);
//        }
//    }
//}
