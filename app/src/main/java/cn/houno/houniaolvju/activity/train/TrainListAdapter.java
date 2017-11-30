package cn.houno.houniaolvju.activity.train;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.fragment.myinfo.LoginActivity;
import cn.houno.houniaolvju.utils.PrefUtils;



public class TrainListAdapter extends BaseAdapter {
    private Context context;
    private List<TrainTicketBean> list;
    private String date;
    public int currentItem = -1;//记录点击的item的position

    public TrainListAdapter(Context context, List<TrainTicketBean> list,String date) {
        super();
        this.context = context;
        this.list = list;
        this.date=date;
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
        ViewHolder holder ;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_train_list_item, parent, false);
            holder = new ViewHolder();
            holder.llShowArea = (LinearLayout) convertView.findViewById(R.id.ll_show_area);
            holder.tvDepTime = (TextView) convertView.findViewById(R.id.tv_dep_time);
            holder.tvArrTime = (TextView) convertView.findViewById(R.id.tv_arr_time);
            holder.tvDepStation = (TextView) convertView.findViewById(R.id.tv_dep_station);
            holder.tvArrStation = (TextView) convertView.findViewById(R.id.tv_arr_station);
            holder.tvTrainCode = (TextView) convertView.findViewById(R.id.tv_train_no);
            holder.tvTotalTime = (TextView) convertView.findViewById(R.id.tv_total_time);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            holder.llShowSeatArea= (LinearLayout) convertView.findViewById(R.id.ll_show_seat_area);
            holder.llHideArea = (LinearLayout) convertView.findViewById(R.id.ll_hide_area);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.llHideArea.removeAllViews();
            holder.llShowSeatArea.removeAllViews();
        }

        Log.e("TAG", "position:" + position);
        final TrainTicketBean ticket = list.get(position);
        holder.tvDepTime.setText(ticket.getStart_time());
        holder.tvArrTime.setText(ticket.getArrive_time());
        holder.tvDepStation.setText(ticket.getFrom_station_name());
        holder.tvArrStation.setText(ticket.getTo_station_name());
        holder.tvTrainCode.setText(ticket.getTrain_code());
        holder.tvTotalTime.setText(ticket.getRun_time());
        holder.tvPrice.setText(ticket.getMin_price() != "false"
                ? Html.fromHtml("<font color='red'>" + "¥" + "<strong>" + ticket.getMin_price() + "</strong>" + "</font>" + "起")
                : Html.fromHtml("<font color='red'>票已售完</font>"));


        for (int i = 0; i < ticket.getSeat().size(); i++) {

            final TrainTicketBean.Seat seat = ticket.getSeat().get(i);

            View hideView = View.inflate(context, R.layout.list_item_hide_area, null);
            ((TextView) hideView.findViewById(R.id.tv_seat_name)).setText(seat.getName());

            if ("0".equals(seat.getNum())) {
                ((TextView) hideView.findViewById(R.id.tv_seat_num)).setText("已售空");
                hideView.findViewById(R.id.tv_seat_reserve).setBackgroundResource(R.drawable.custom_corners_bg_gray);
                ((TextView) hideView.findViewById(R.id.tv_seat_price)).setText("---");
            }else{
                ((TextView) hideView.findViewById(R.id.tv_seat_num)).setText(seat.getNum() + "张");
                ((TextView) hideView.findViewById(R.id.tv_seat_price)).setText("¥"+seat.getPrice());
                hideView.findViewById(R.id.tv_seat_reserve).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(PrefUtils.getBoolean(context,"isLogined",false)){
                            Intent intent = new Intent(context, TrainOrderFillinActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("ticket", ticket);
                            intent.putExtras(bundle);
                            Log.e("wwww",date);
                            intent.putExtra("date",date);
                            intent.putExtra("seatName", seat.getName());
                            System.out.println(seat.getName());
                            intent.putExtra("seatPrice", seat.getPrice());
                            context.startActivity(intent);
                        }else{
                            context.startActivity(new Intent(context, LoginActivity.class));
                        }
                    }
                });
            }
            holder.llHideArea.addView(hideView);
            if(i==ticket.getSeat().size()-1){
                hideView.setPadding(0,DensityUtil.dip2px(context,10),0,DensityUtil.dip2px(context,10));
            }else{
                TextView textView=new TextView(context);
                textView.setHeight(5);
                textView.setPadding(DensityUtil.dip2px(context,10),0,DensityUtil.dip2px(context,10),0);
                textView.setBackgroundResource(R.drawable.dash_line);
                holder.llHideArea.addView(textView);
            }

            if (!"0".equals(seat.getNum())) {
                TextView tvSeat=new TextView(context);
                tvSeat.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1.0f));
                tvSeat.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                tvSeat.setText(Html.fromHtml(seat.getName() + "<font color='#FF6100'>" + seat.getNum() + "</font>" + "张"));
                holder.llShowSeatArea.addView(tvSeat);
            }
        }

        holder.llShowArea.setTag(position);

        if (currentItem == position) {
            holder.llHideArea.setVisibility(View.VISIBLE);
        } else {
            holder.llHideArea.setVisibility(View.GONE);
        }


        holder.llShowArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                if (tag == currentItem) {
                    currentItem = -1;
                } else {
                    currentItem = tag;
                }
                notifyDataSetChanged();

            }
        });

        return convertView;
    }


    class ViewHolder {
        private LinearLayout llShowSeatArea;


        private TextView tvDepTime;//开车时间
        private TextView tvArrTime;//到达时间
        private TextView tvTotalTime;//列车行程的总时间

        private TextView tvDepStation;//始发站
        private TextView tvArrStation;//终点站

        private TextView tvTrainCode;//火车列次

        private TextView tvPrice;//最低火车票价格

        private LinearLayout llShowArea;
        private LinearLayout llHideArea;
    }
}
