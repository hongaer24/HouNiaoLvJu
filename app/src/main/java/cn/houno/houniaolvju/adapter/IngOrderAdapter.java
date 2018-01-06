package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.OrderListBean.DataBean;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class IngOrderAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<DataBean> mList;

    public IngOrderAdapter(Context context, List<DataBean> list) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mList = list;

    }

    public void setData(List<DataBean> list) {
        mList = list;
        notifyDataSetChanged();
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
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_order, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String type = mList.get(position).getType();
        holder.tvOrderno.setText(mList.get(position).getOrderno());
        holder.tvOrderPrice.setText("¥"+mList.get(position).getPrice());


        //判断是否是火车票
        if (TextUtils.equals(type, "Train")) {
            holder.llOrderProject.setVisibility(View.GONE);
            holder.llOrderNumber.setVisibility(View.GONE);
            holder.llAirlines.setVisibility(View.VISIBLE);
            holder.tvCheckDateTxt.setText("出发时间： ");
            holder.ivOrderType.setImageResource(R.drawable.label_order_train);
            holder.tvOrderType.setText("火车票");
            String payStatus = mList.get(position).getPay_status();
            if (TextUtils.equals(payStatus, "1")) {//已支付
                holder.tvPayStatus.setText("已支付");
            } else {
                holder.tvPayStatus.setText("待支付");
            }

            if (mList.get(position).getDetail() != null) {
                String depTime = mList.get(position).getDetail().getStart_time();
                holder.tvCheckDate.setText(depTime);
                String depName = mList.get(position).getDetail().getFrom_station_name();
                String arrName = mList.get(position).getDetail().getTo_station_name();
                holder.tvOrderTitle.setText(depName + " → " + arrName + " (" + mList.get(position).getDetail().getTrain_code() + ")");
                String arrTime = mList.get(position).getDetail().getArrive_time();
                holder.tvTrainArrTime.setText("到达时间： ");
                holder.tvAirlines.setText(arrTime);
            }
        }


        //先判断是否是机票

        else if (TextUtils.equals(type, "Flight")) {
            holder.llOrderProject.setVisibility(View.GONE);
            holder.llOrderNumber.setVisibility(View.GONE);
            holder.llAirlines.setVisibility(View.VISIBLE);
            holder.tvCheckDateTxt.setText("出发时间： ");
            holder.ivOrderType.setImageResource(R.drawable.label_order_flight);
            holder.tvOrderType.setText("机票");
            String payStatus = mList.get(position).getPay_status();
            if (TextUtils.equals(payStatus, "1")) {//已支付
                holder.tvPayStatus.setText("已支付");
            } else {
                holder.tvPayStatus.setText("待支付");
            }
            String depName = mList.get(position).getDetail().getDepname();
            String arrName = mList.get(position).getDetail().getArrname();
            holder.tvOrderTitle.setText(depName + " → " + arrName);
            String checkIn = mList.get(position).getCheckin();
            String checkOut = mList.get(position).getCheckout();
            String depTime = MyText2Utils.insertString(mList.get(position).getDetail().getDeptime(), ":", 2);
            String arrTime = MyText2Utils.insertString(mList.get(position).getDetail().getArrtime(), ":", 2);
            //出发日期
            if (TextUtils.equals(checkIn, checkOut)) {
                //当天到达
                holder.tvCheckDate.setText(checkIn + " " + depTime + " - " + arrTime);
            } else {
                //次日到达
                holder.tvCheckDate.setText(checkIn + " " + depTime + " - " + checkOut + " " + arrTime);
            }
            String airlines = mList.get(position).getDetail().getAirlines();
            String flightNo = mList.get(position).getDetail().getFlightno();
            holder.tvTrainArrTime.setText("航班信息： ");
            holder.tvAirlines.setText(airlines + " " + flightNo);
        } else if (TextUtils.equals(type, "Acti")) {
            //活动
            holder.llOrderProject.setVisibility(View.GONE);
            holder.llOrderNumber.setVisibility(View.VISIBLE);
            holder.llAirlines.setVisibility(View.GONE);
            holder.ivOrderType.setImageResource(R.drawable.label_order_activity);
            holder.tvOrderType.setText("活动");
            String payStatus = mList.get(position).getPay_status();
            if (TextUtils.equals(payStatus, "1")) {//已支付
                holder.tvPayStatus.setText("已支付");
            } else {
                holder.tvPayStatus.setText("待支付");
            }
            holder.tvOrderTitle.setText(mList.get(position).getDetail().getTitle());
            holder.tvOrderNumberTxt.setText("预订人数：");
            holder.tvCheckDateTxt.setText("预订日期：");
            holder.tvOrderNumber.setText(mList.get(position).getNum() + "人");
            String checkIn = mList.get(position).getCheckin();
            holder.tvCheckDate.setText(checkIn);
            holder.tvOrderno.setText(mList.get(position).getOrderno());
            if(mList.get(position).getPrice()!=null){
                holder.tvOrderPrice.setText("¥"+mList.get(position).getPrice().trim());
            }
        } else {
            holder.llOrderProject.setVisibility(View.VISIBLE);
            holder.llOrderNumber.setVisibility(View.VISIBLE);
            holder.llAirlines.setVisibility(View.GONE);

            if (TextUtils.equals(type, "toursscenic")) {
                //景点
                holder.tvCheckDateTxt.setText("预订日期：");
                holder.tvOrderProjectTxt.setText("预订项目：");
                holder.tvOrderNumberTxt.setText("预订人数：");
                holder.ivOrderType.setImageResource(R.drawable.label_order_scenic);
                holder.tvOrderType.setText("景点 |");
                String payStatus = mList.get(position).getPay_status();
                String  Status = mList.get(position).getStatus();

                if (TextUtils.equals(payStatus, "1")&&TextUtils.equals(Status, "3")) {//已支付
                    holder.tvPayStatus.setText("出票中");
                } else if(TextUtils.equals(payStatus, "0")&&TextUtils.equals(Status, "2")) {
                    holder.tvPayStatus.setText("待支付");
                }else if(TextUtils.equals(payStatus, "1")&&TextUtils.equals(Status, "1")||TextUtils.equals(payStatus, "1")&&TextUtils.equals(Status, "2")){
                    holder.tvPayStatus.setText("出票失败");
                }

                holder.tvOrderTitle.setText(mList.get(position).getDetail().getTitle());
                holder.tvOrderProject.setText(mList.get(position).getDetail().getRoomname());
                holder.tvOrderNumber.setText(mList.get(position).getNum() + "人");
                if(mList.get(position).getPrice()!=null){
                    holder.tvOrderPrice.setText("¥"+mList.get(position).getPrice().trim());
                }
                String checkIn = mList.get(position).getCheckin();
                holder.tvCheckDate.setText(checkIn);
            } else {
                //酒店、拼团、异地养老

                holder.tvOrderNumberTxt.setText("入住时长：");
                holder.tvCheckDateTxt.setText("入住时间：");
                if (TextUtils.equals(type, "hotel")) {
                    holder.tvOrderProjectTxt.setText("预订房型：");
                    holder.ivOrderType.setImageResource(R.drawable.label_order_hotel);
                    holder.tvOrderType.setText("酒店");
                } else if (TextUtils.equals(type, "groupon")) {
                    holder.tvOrderProjectTxt.setText("预订房型：");
                    holder.ivOrderType.setImageResource(R.drawable.label_order_pintuan);
                    holder.tvOrderType.setText("拼团");
                } else {
                    holder.tvOrderProjectTxt.setText("预订项目：");
                    holder.ivOrderType.setImageResource(R.drawable.label_order_yiyang);
                    holder.tvOrderType.setText("异养");
                }

                String payStatus = mList.get(position).getPay_status();
                if (TextUtils.equals(payStatus, "1")) {//已支付
                    holder.tvPayStatus.setText("已支付");
                } else if (TextUtils.equals(payStatus, "2")) {
                    holder.tvPayStatus.setText("前台现付");
                } else {
                    holder.tvPayStatus.setText("待支付");
                }

                holder.tvOrderNumber.setText(mList.get(position).getDays() + "晚");
                String checkIn = mList.get(position).getCheckin();
                String checkOut = mList.get(position).getCheckout();
                holder.tvCheckDate.setText(checkIn + " 至 " + checkOut);
                if (mList.get(position).getDetail() != null) {
                    holder.tvOrderTitle.setText(mList.get(position).getDetail().getTitle().trim());
                    holder.tvOrderProject.setText(mList.get(position).getDetail().getRoomname().trim());
                }
            }
        }
        //String price = Double.parseDouble(mList.get(position).getPrice()) + "";
       // MyText2Utils.formatTicketPrice(mContext, holder.tvOrderPrice, price);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_train_arrive_time)
        TextView tvTrainArrTime;
        @Bind(R.id.iv_order_type)
        ImageView ivOrderType;
        @Bind(R.id.tv_order_type)
        TextView tvOrderType;
        @Bind(R.id.tv_pay_status)
        TextView tvPayStatus;
        @Bind(R.id.tv_order_title)
        TextView tvOrderTitle;
        @Bind(R.id.tv_order_price)
        TextView tvOrderPrice;
        @Bind(R.id.tv_order_project_txt)
        TextView tvOrderProjectTxt;
        @Bind(R.id.tv_order_project)
        TextView tvOrderProject;
        @Bind(R.id.ll_order_project)
        LinearLayout llOrderProject;
        @Bind(R.id.tv_order_number_txt)
        TextView tvOrderNumberTxt;
        @Bind(R.id.tv_order_number)
        TextView tvOrderNumber;
        @Bind(R.id.tv_orderno)
        TextView tvOrderno;
        @Bind(R.id.ll_order_number)
        LinearLayout llOrderNumber;
        @Bind(R.id.tv_check_date_txt)
        TextView tvCheckDateTxt;
        @Bind(R.id.tv_check_date)
        TextView tvCheckDate;
        @Bind(R.id.ll_order_date)
        LinearLayout llOrderDate;
        @Bind(R.id.tv_airlines)
        TextView tvAirlines;
        @Bind(R.id.ll_airlines)
        LinearLayout llAirlines;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
