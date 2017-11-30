package cn.houno.houniaolvju.activity.train;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.houno.houniaolvju.R;


public class ZiMuAdapter extends RecyclerView.Adapter<ZiMuAdapter.ViewHolder> {
    Context context;
    private LayoutInflater mlayoutInflater;
    private List<String> mList = new ArrayList<>();

    private int selectItemId=-1;

    private OnItemClickListener mOnItemClickListener;

    public ZiMuAdapter(Context context) {
        this.context = context;
        this.mlayoutInflater = LayoutInflater.from(context);

    }

    public void setSelectItemId(int position){
        this.selectItemId=position;
        notifyDataSetChanged();
    }

    public void fillData(List<String> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.item_zimu, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvZimu.setText(mList.get(position));
        if(selectItemId==position) {
            holder.tvZimu.setBackgroundResource(R.drawable.custom_corners_bg_green);
        }else{
            holder.tvZimu.setBackgroundResource(R.drawable.custom_corners_bg_white);
        }
        if (mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.omItemClick(holder,holder.itemView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (mList != null)
            return mList.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvZimu;

        public ViewHolder(View itemView) {
            super(itemView);

            tvZimu = (TextView) itemView.findViewById(R.id.tv_zimu);
        }
    }


    public interface OnItemClickListener{
        void omItemClick(ViewHolder holder, View view, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){

        this.mOnItemClickListener =onItemClickListener;
    }
}
