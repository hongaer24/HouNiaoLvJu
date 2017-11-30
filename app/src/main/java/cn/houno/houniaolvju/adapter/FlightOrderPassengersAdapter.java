package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.PassengersListBean.DataBean;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 订单详情乘机人列表
 * Created by qzc on 2017/2/21.
 */

public class FlightOrderPassengersAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;

    private List<DataBean> mList;

    public FlightOrderPassengersAdapter(Context context, List<DataBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
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
        ViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.listitem_flight_order_detail_passengers, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvPassengersName.setText(mList.get(position).getName());
        int length = mList.get(position).getIdentityno().length();
        String iDentityNo = MyText2Utils.getStarString(mList.get(position).getIdentityno(), 4, length - 4);
        holder.tvPassengersCertificate.setText("身份证 " + iDentityNo);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_passengers_name)
        TextView tvPassengersName;
        @Bind(R.id.tv_passengers_certificate)
        TextView tvPassengersCertificate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
