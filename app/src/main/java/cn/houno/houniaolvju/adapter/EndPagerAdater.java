package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.ArrayList;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.EndBean.DataEntity;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class EndPagerAdater extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<DataEntity> endlist;

    public EndPagerAdater(Context context, ArrayList<DataEntity> endlist) {
        mInflater = LayoutInflater.from(context);
        this.endlist = endlist;
    }

    public void setdata(ArrayList<DataEntity> endlist) {
        this.endlist = endlist;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return endlist == null ? 0 : endlist.size();
    }

    @Override
    public Object getItem(int position) {
        return endlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_order_end, parent, false);
            holder = new ViewHolder();
            holder.tvendjd = (TextView) convertView.findViewById(R.id.tv_order_end_jd);
            holder.tvendroom = (TextView) convertView.findViewById(R.id.tv_order_end_room);
            holder.ivendimg = (ImageView) convertView.findViewById(R.id.iv_order_end_img);
            holder.tvendrmb = (TextView) convertView.findViewById(R.id.tv_order_end_rmb);
            holder.tvendday = (TextView) convertView.findViewById(R.id.tv_order_end_day);
            holder.tvendksrz = (TextView) convertView.findViewById(R.id.tv_order_end_ksrz);
            holder.tvendldsj = (TextView) convertView.findViewById(R.id.tv_end_ldsj);
            holder.tvendrzsj = (TextView) convertView.findViewById(R.id.tv_end_rzsc);
            holder.tvendwan = (TextView) convertView.findViewById(R.id.tv_end_wan);
            holder.tvendzhi = (TextView) convertView.findViewById(R.id.tv_end_zhi);
            holder.tvenddai = (TextView) convertView.findViewById(R.id.tv_order_end_dai);
            holder.ivendhotel = (ImageView) convertView.findViewById(R.id.iv_type_end_hotel);
            holder.ivendacti = (ImageView) convertView.findViewById(R.id.iv_type_end_acti);
            holder.ivendscenic = (ImageView) convertView.findViewById(R.id.iv_type_end_scenic);
            holder.ivendpt = (ImageView) convertView.findViewById(R.id.iv_type_end_pt);
            holder.ivendyy = (ImageView) convertView.findViewById(R.id.iv_type_end_yy);
            holder.tvenddetail = (TextView) convertView.findViewById(R.id.tv_type_end_detail);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String type = endlist.get(position).getType().trim();
        if ("hotel".equals(type)) {
            if (endlist.get(position).getDetail() != null && !"".equals(endlist.get(position).getDetail())) {
                holder.tvendrzsj.setText("入住时长：");
                holder.tvendwan.setText("晚");
                holder.tvendjd.setText(endlist.get(position).getDetail().getTitle());
                holder.tvendroom.setText(endlist.get(position).getDetail().getRoomname());
                x.image().bind(holder.ivendimg, endlist.get(position).getDetail().getImg());
                holder.tvendrmb.setText(Double.parseDouble(endlist.get(position).getPrice())+"");
                holder.tvendday.setText(endlist.get(position).getDays());
                holder.tvendksrz.setText(endlist.get(position).getCheckin());
                holder.tvendldsj.setText(endlist.get(position).getCheckout());

                holder.ivendhotel.setVisibility(View.VISIBLE);
                holder.ivendacti.setVisibility(View.GONE);
                holder.ivendscenic.setVisibility(View.GONE);
                holder.ivendpt.setVisibility(View.GONE);
                holder.tvenddetail.setText("酒店");
            }
        } else if ("Acti".equals(type)) {
            holder.tvendrzsj.setText("预订人数：");
            holder.tvendwan.setText("人");

            holder.tvendjd.setText(endlist.get(position).getDetail().getTitle());
            holder.tvendroom.setText(endlist.get(position).getDetail().getRoomname());
            x.image().bind(holder.ivendimg, endlist.get(position).getDetail().getImg());
            holder.tvendrmb.setText(Double.parseDouble(endlist.get(position).getPrice())+"");
            holder.tvendday.setText(endlist.get(position).getNum());
            holder.tvendksrz.setText(endlist.get(position).getCheckin());
            holder.tvendzhi.setVisibility(View.GONE);
            holder.tvendldsj.setVisibility(View.GONE);

            holder.ivendhotel.setVisibility(View.GONE);
            holder.ivendpt.setVisibility(View.GONE);
            holder.ivendacti.setVisibility(View.VISIBLE);
            holder.ivendscenic.setVisibility(View.GONE);
            holder.tvenddetail.setText("活动");
        } else if ("Scenic".equals(type)) {
            holder.tvendrzsj.setText("预订人数：");
            holder.tvendwan.setText("人");

            holder.tvendjd.setText(endlist.get(position).getDetail().getTitle());
            holder.tvendroom.setText(endlist.get(position).getDetail().getRoomname());
            x.image().bind(holder.ivendimg, endlist.get(position).getDetail().getImg());
            holder.tvendrmb.setText(Double.parseDouble(endlist.get(position).getPrice())+"");
            holder.tvendday.setText(endlist.get(position).getNum());
            holder.tvendksrz.setText(endlist.get(position).getCheckin());
            holder.tvendzhi.setVisibility(View.GONE);
            holder.tvendldsj.setVisibility(View.GONE);

            holder.ivendhotel.setVisibility(View.GONE);
            holder.ivendpt.setVisibility(View.GONE);
            holder.ivendacti.setVisibility(View.GONE);
            holder.ivendscenic.setVisibility(View.VISIBLE);
            holder.tvenddetail.setText("景点");
        } else if ("groupon".equals(type)) {
            holder.tvendrzsj.setText("入住时长：");
            holder.tvendwan.setText("晚");

            holder.tvendjd.setText(endlist.get(position).getDetail().getTitle());
            holder.tvendroom.setText(endlist.get(position).getDetail().getRoomname());
            x.image().bind(holder.ivendimg, endlist.get(position).getDetail().getImg());
            holder.tvendrmb.setText(Double.parseDouble(endlist.get(position).getPrice())+"");
            holder.tvendday.setText(endlist.get(position).getDays());
            holder.tvendksrz.setText(endlist.get(position).getCheckin());
            holder.tvendldsj.setText(endlist.get(position).getCheckout());
            holder.ivendhotel.setVisibility(View.GONE);
            holder.ivendpt.setVisibility(View.VISIBLE);
            holder.ivendacti.setVisibility(View.GONE);
            holder.ivendscenic.setVisibility(View.GONE);
            holder.tvenddetail.setText("拼团");
        }else
        if ("ydhotel".equals(type)) {
            if (endlist.get(position).getDetail() != null && !"".equals(endlist.get(position).getDetail())) {
                holder.tvendrzsj.setText("入住时长：");
                holder.tvendwan.setText("晚");
                holder.tvendjd.setText(endlist.get(position).getDetail().getTitle());
                holder.tvendroom.setText(endlist.get(position).getDetail().getRoomname());
                x.image().bind(holder.ivendimg, endlist.get(position).getDetail().getImg());
                holder.tvendrmb.setText(Double.parseDouble(endlist.get(position).getPrice())+"");
                holder.tvendday.setText(endlist.get(position).getDays());
                holder.tvendksrz.setText(endlist.get(position).getCheckin());
                holder.tvendldsj.setText(endlist.get(position).getCheckout());

                holder.ivendyy.setVisibility(View.VISIBLE);
                holder.ivendhotel.setVisibility(View.GONE);
                holder.ivendacti.setVisibility(View.GONE);
                holder.ivendscenic.setVisibility(View.GONE);
                holder.ivendpt.setVisibility(View.GONE);
                holder.tvenddetail.setText("异养");
            }
        }


        String pay_status = endlist.get(position).getPay_status();
        int pay = Integer.parseInt(pay_status);

        String str1 = null;
        switch (pay) {
            case 0:
                str1 = "待支付";
                break;
            case 1:
                str1 = "已支付";
                break;
            case 2:
                str1 = "前台支付";
                break;
        }
        String status = endlist.get(position).getStatus();
        int sta = Integer.parseInt(status);
        String str2 = null;
        switch (sta) {
            case 0:
                str2 = "待确认";
                break;
            case 1:
                str2 = "已确认";
                break;
            case 2:
                str2 = "已取消";
                break;
            case 3:
                str2 = "已完成";
                break;
            case 4:
                str2 = "已退款";
                break;
        }
        String strzf = str1 + "/" + str2;
        holder.tvenddai.setText(strzf);


        return convertView;
    }

    static class ViewHolder {
        TextView tvendjd;
        TextView tvendroom;
        ImageView ivendimg;
        TextView tvendrmb;

        TextView tvendrzsj;
        TextView tvendwan;
        TextView tvendzhi;

        TextView tvendday;
        TextView tvendksrz;
        TextView tvendldsj;
        TextView tvenddai;
        ImageView ivendhotel;
        ImageView ivendacti;
        ImageView ivendscenic;
        ImageView ivendpt;
        ImageView ivendyy;
        TextView tvenddetail;
    }
}
