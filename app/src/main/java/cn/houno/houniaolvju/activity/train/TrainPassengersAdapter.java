package cn.houno.houniaolvju.activity.train;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.utils.MyText2Utils;


public class TrainPassengersAdapter extends BaseAdapter {

    List<Boolean> mChecked;
    private List<TrainPassengersBean> list;
    private Context context;


    public static final int FROM_ORDER_PAY=0;
    public static final int FROM_ORDER_DETAIL=1;
    public static final int FROM_ORDER_FILLIN=2;
    public static final int FROM_PASSENGERS_SELECT=3;

    private int which;

    //which表示哪个页面调用的，谁调用就传入上面对应的常量
    public TrainPassengersAdapter(Context context, List<TrainPassengersBean> list,int which) {
        super();
        this.context = context;
        this.list = list;
        this.which=which;
        if(which==FROM_PASSENGERS_SELECT){
            mChecked=new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                mChecked.add(false);
            }
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       ViewHolder holder=null;
        if (convertView == null) {
            holder = new ViewHolder();

            if(which==FROM_ORDER_PAY || which==FROM_ORDER_DETAIL) {
                convertView = LayoutInflater.from(context).inflate(R.layout.listitem_train_order_passengers, parent, false);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_passengers_name);
                holder.tvCertNo = (TextView) convertView.findViewById(R.id.tv_passengers_certificate);
                if (which == FROM_ORDER_DETAIL) {
                    holder.tvSeatDetail = (TextView) convertView.findViewById(R.id.tv_seat_detail);
                    holder.tvSeatDetail.setVisibility(View.VISIBLE);
                    holder.tvSeatDetail.setText(list.get(position).getZwname()+" "
                            +(list.get(position).getCxin()!=null?list.get(position).getCxin():""));
                }

                int length = list.get(position).getPassportseno().length();
                String iDentityNo = MyText2Utils.getStarString(list.get(position).getPassportseno(), 4, length - 4);
                holder.tvCertNo.setText("身份证 " + iDentityNo);

            }else if(which==FROM_ORDER_FILLIN){
                convertView= LayoutInflater.from(context).inflate(R.layout.listitem_train_order_fillin,parent,false);
                holder.tvName= (TextView) convertView.findViewById(R.id.tv_username);
                holder.tvCertNo= (TextView) convertView.findViewById(R.id.tv_certificate);
                holder.tvDelete= (ImageView) convertView.findViewById(R.id.iv_delete);

                holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(position);
                        notifyDataSetChanged();
                        context.sendBroadcast(new Intent().setAction("price.change"));
                    }
                });
                holder.tvCertNo.setText(list.get(position).getPassportseno());

            }else if(which==FROM_PASSENGERS_SELECT){
                convertView= LayoutInflater.from(context).inflate(R.layout.listitem_train_passengers_select,parent,false);
                holder.checkBox= (CheckBox) convertView.findViewById(R.id.cb_passengers_check);
                holder.tvName= (TextView) convertView.findViewById(R.id.tv_passengers_name);
                holder.tvCertNo= (TextView) convertView.findViewById(R.id.tv_certificate_num);
                holder.tvEdit= (ImageView) convertView.findViewById(R.id.iv_edit);

                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox cb= (CheckBox) v;
                        mChecked.set(position,cb.isChecked());
                    }
                });

                holder.tvCertNo.setText(list.get(position).getPassportseno());

                holder.checkBox.setChecked(mChecked.get(position));

                holder.tvEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,TrainPassengersEditActivity.class);
                        intent.putExtra("edit",true);
                        intent.putExtra("id",list.get(position).getId());
                        intent.putExtra("name",list.get(position).getPassengersename());
                        intent.putExtra("certNo",list.get(position).getPassportseno());
                        ((TrainPassengersSelectActivity)context).startActivityForResult(intent,0);
                    }
                });
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvName.setText(list.get(position).getPassengersename());

        return convertView;
    }

    class ViewHolder{
        private ImageView tvEdit;
        private CheckBox checkBox;
        private ImageView tvDelete;
        private TextView tvName;
        private TextView tvCertNo;
        private TextView tvSeatDetail;
    }
}
