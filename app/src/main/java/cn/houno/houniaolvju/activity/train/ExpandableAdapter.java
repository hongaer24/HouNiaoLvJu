package cn.houno.houniaolvju.activity.train;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.fragment.myinfo.LoginActivity;
import cn.houno.houniaolvju.utils.PrefUtils;


public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<TrainTicketBean> list;
    private String date;

    public ExpandableAdapter(Context context, List<TrainTicketBean> list, String date) {
        this.context = context;
        this.list = list;
        this.date = date;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getSeat().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getSeat().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_train_list_item1, null);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
            holder.llShowSeatArea.removeAllViews();
        }
        final TrainTicketBean ticket = list.get(groupPosition);
        holder.tvDepTime.setText(ticket.getStart_time());
        holder.tvArrTime.setText(ticket.getArrive_time());
        holder.tvDepStation.setText(ticket.getFrom_station_name());
        holder.tvArrStation.setText(ticket.getTo_station_name());
        holder.tvTrainCode.setText(ticket.getTrain_code());
        holder.tvTotalTime.setText(ticket.getRun_time());
        holder.tvPrice.setText(ticket.getMin_price() != "false"
                ? Html.fromHtml("<font color='red'>" + "¥" + "<strong>" + ticket.getMin_price() + "</strong>" + "</font>" + "起")
                : Html.fromHtml("<font color='red'>票已售完</font>"));

        byte j = 0;
        for (int i = 0; i < list.get(groupPosition).getSeat().size(); i++) {
            TrainTicketBean.Seat seat = list.get(groupPosition).getSeat().get(i);
            //最多显示四种座位类型
            if (!"0".equals(seat.getNum()) && j < 4) {
                j++;
                TextView tvSeat = new TextView(context);
                tvSeat.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                tvSeat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                tvSeat.setText(Html.fromHtml(seat.getName() + "<font color='#FF6100'>" + seat.getNum() + "</font>" + "张"));
                holder.llShowSeatArea.addView(tvSeat);

            }
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_hide_area, null);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        final TrainTicketBean.Seat seat = list.get(groupPosition).getSeat().get(childPosition);

        if ("0".equals(seat.getNum())) {
            holder.seatNum.setText("已售空");
            holder.seatReserve.setBackgroundResource(R.drawable.custom_corners_bg_gray);
            holder.seatReserve.setEnabled(false);
            holder.seatPrice.setText("---");
        } else {
            holder.seatNum.setText(seat.getNum() + "张");
            holder.seatPrice.setText("¥" + seat.getPrice());
            holder.seatReserve.setBackgroundResource(R.drawable.custom_corners_bg_green);
            holder.seatReserve.setEnabled(true);
            holder.seatReserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PrefUtils.getBoolean(context, "isLogined", false)) {
                        Intent intent = new Intent(context, TrainOrderFillinActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("ticket", list.get(groupPosition));
                        intent.putExtras(bundle);
                        intent.putExtra("date", date);
                        intent.putExtra("seatName", seat.getName());
                        System.out.println(seat.getName());
                        intent.putExtra("seatPrice", seat.getPrice());
                        context.startActivity(intent);
                    } else {
                        context.startActivity(new Intent(context, LoginActivity.class));
                    }
                }
            });
        }
        holder.seatName.setText(seat.getName());
        return convertView;

    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    class GroupHolder {
        public GroupHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @Bind(R.id.tv_dep_time)
        TextView tvDepTime;//开车时间
        @Bind(R.id.tv_arr_time)
        TextView tvArrTime;//到达时间
        @Bind(R.id.tv_total_time)
        TextView tvTotalTime;//列车行程的总时间
        @Bind(R.id.tv_dep_station)
        TextView tvDepStation;//始发站
        @Bind(R.id.tv_arr_station)
        TextView tvArrStation;//终点站
        @Bind(R.id.tv_train_code)
        TextView tvTrainCode;//火车列次
        @Bind(R.id.tv_price)
        TextView tvPrice;//最低火车票价格
        @Bind(R.id.ll_show_seat_area)
        LinearLayout llShowSeatArea;
    }

    class ChildHolder {
        public ChildHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @Bind(R.id.tv_seat_name)
        TextView seatName;
        @Bind(R.id.tv_seat_price)
        TextView seatPrice;
        @Bind(R.id.tv_seat_num)
        TextView seatNum;
        @Bind(R.id.tv_seat_reserve)
        TextView seatReserve;
    }
}
