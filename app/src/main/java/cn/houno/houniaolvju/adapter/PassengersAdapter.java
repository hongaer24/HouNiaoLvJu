package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.PassengersListBean.DataBean;

/**
 * 下单页面乘机人列表适配器
 * Created by qzc on 2017/2/20.
 */

public class PassengersAdapter extends BaseAdapter {
    private List<DataBean> mList;
    private LayoutInflater mInflater;
    private Context mContext;
    HashMap<Integer, Boolean> mIsChecked;

    public PassengersAdapter(Context context, List<DataBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<DataBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setChecked(HashMap<Integer, Boolean> isChecked) {
        mIsChecked = isChecked;
    }

    public HashMap<Integer, Boolean> getChecked() {
        return mIsChecked;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_passengers, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvUsername.setText(mList.get(position).getName());
        holder.tvCertificate.setText(mList.get(position).getIdentityno());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.remove(position);
       //         mIsChecked.put(position, false);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.iv_delete)
        ImageView ivDelete;
        @Bind(R.id.tv_username)
        TextView tvUsername;
        @Bind(R.id.tv_certificate)
        TextView tvCertificate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
