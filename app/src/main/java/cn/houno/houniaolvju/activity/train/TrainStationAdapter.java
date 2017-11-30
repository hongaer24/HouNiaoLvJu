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


public class TrainStationAdapter extends RecyclerView.Adapter<TrainStationAdapter.ViewHolder> {
    Context context;
    private LayoutInflater mlayoutInflater;
    private List<TrainCityBean> mList = new ArrayList<>();
    public String index;
    private OnItemClickListener mOnItemClickListener;

    public TrainStationAdapter(Context context) {
        this.context = context;
        this.mlayoutInflater = LayoutInflater.from(context);

    }

    public void fillData(List<TrainCityBean> list,String index) {
        this.mList = list;
        this.index=index;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.item_hotcity, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvCity.setText(mList.get(position).getName());

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

        public TextView tvCity;

        public ViewHolder(View itemView) {
            super(itemView);

            tvCity = (TextView) itemView.findViewById(R.id.tv_city);
        }
    }


    public interface OnItemClickListener{
        void omItemClick(ViewHolder holderView, View view, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener =onItemClickListener;
    }
}
