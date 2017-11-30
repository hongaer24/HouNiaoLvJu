package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.WalletDrawRechargeBean;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：提现列表适配器
 * 创建人：qzc
 * 创建时间：2016/12/9 20:48
 * 修改人：qzc
 * 修改时间：2016/12/9 20:48
 * 修改备注：
 */
public class WalletDrawalsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<WalletDrawRechargeBean.DataBean> dataList;
    private LayoutInflater mInflater;

    public WalletDrawalsAdapter(Context context, ArrayList<WalletDrawRechargeBean.DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<WalletDrawRechargeBean.DataBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.listitem_rechargerecord, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvDrawalsType = (TextView) convertView.findViewById(R.id.tv_pay_type);
            viewHolder.tvDrawalsTime = (TextView) convertView.findViewById(R.id.tv_pay_time);
            viewHolder.tvDrawalsMoney = (TextView) convertView.findViewById(R.id.tv_money);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (dataList.get(position).getAmout_type() != null) {
            viewHolder.tvDrawalsType.setText(dataList.get(position).getAmout_type());
        }else {
            viewHolder.tvDrawalsType.setText("");
        }

        if (dataList.get(position).getApply_time()!=null){
            viewHolder.tvDrawalsTime.setText(DateUtil.timeStamp2Date(dataList.get(position).getApply_time(),null));
        }else {
            viewHolder.tvDrawalsTime.setText("");
        }

        if (dataList.get(position).getWithdrawals()!=null){
            viewHolder.tvDrawalsMoney.setText("-"+ MyText2Utils.getIntPrice(dataList.get(position).getWithdrawals()));
            viewHolder.tvDrawalsMoney.setTextColor(ContextCompat.getColor(context,R.color.red));
        }else {
            viewHolder.tvDrawalsMoney.setText("");
        }
        return convertView;
    }

    static class ViewHolder{
        TextView tvDrawalsType;
        TextView tvDrawalsTime;
        TextView tvDrawalsMoney;
    }
}
