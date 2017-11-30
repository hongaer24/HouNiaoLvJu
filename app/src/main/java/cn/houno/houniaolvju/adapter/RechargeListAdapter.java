package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.CardAndWalletRechargeListBean.DataBean;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述： 充值记录适配器
 * 创建人：qzc
 * 创建时间：2016/11/25 10:46
 * 修改人：qzc
 * 修改时间：2016/11/25 10:46
 * 修改备注：
 */
public class RechargeListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DataBean> dataList;
    private LayoutInflater mInflater;

    public RechargeListAdapter(Context context, ArrayList<DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<DataBean> dataList) {
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
            viewHolder.tvPayType = (TextView) convertView.findViewById(R.id.tv_pay_type);
            viewHolder.tvPayTime = (TextView) convertView.findViewById(R.id.tv_pay_time);
            viewHolder.tvPayMoney = (TextView) convertView.findViewById(R.id.tv_money);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (dataList.get(position).getPaytype() != null) {
            viewHolder.tvPayType.setText(dataList.get(position).getPaytype()+"充值");
        }else {
            viewHolder.tvPayType.setText("");
        }

        if (dataList.get(position).getAdd_time()!=null){
            viewHolder.tvPayTime.setText(DateUtil.timeStamp2Date(dataList.get(position).getAdd_time(),null));
        }else {
            viewHolder.tvPayTime.setText("");
        }

        if (dataList.get(position).getMoney()!=null){
            viewHolder.tvPayMoney.setText("+"+ MyText2Utils.getIntPrice(dataList.get(position).getMoney()));
        }else {
            viewHolder.tvPayMoney.setText("");
        }
        return convertView;
    }

    static class ViewHolder{
        TextView tvPayType;
        TextView tvPayTime;
        TextView tvPayMoney;
    }
}
