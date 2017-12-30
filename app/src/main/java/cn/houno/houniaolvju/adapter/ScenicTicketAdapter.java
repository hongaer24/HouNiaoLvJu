package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.scenic.FillInScenicOrderActivity;
import cn.houno.houniaolvju.activity.scenic.ScenicBookInfoActivity;
import cn.houno.houniaolvju.bean.ScenicDetailBean;
import cn.houno.houniaolvju.utils.MyText2Utils;

//import cn.houno.houniaolvju.bean.ScenicDetailBean.DataBean.TicketBean.TicketDataBean;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：景点详情-门票预订
 * 创建人：qzc
 * 创建时间：2017/1/5 9:30
 * 修改人：qzc
 * 修改时间：2017/1/5 9:30
 * 修改备注：
 */
public class ScenicTicketAdapter extends BaseAdapter {

    private List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean> list;
    private LayoutInflater mInflater;
    private Context mContext;
    private AlertDialog.Builder builder;

    private String scenicTitle;
    private String scenicAddress;
    private String inpolicy;


    public ScenicTicketAdapter(Context context, List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean> list
            , String scenicTitle, String scenicAddress) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.list = list;
        this.scenicTitle = scenicTitle;
        this.scenicAddress = scenicAddress;
        builder = new AlertDialog.Builder(
                mContext);
    }


    public void setData(List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean> list, String scenicTitle, String scenicAddress) {
        this.list = list;
        this.scenicTitle = scenicTitle;
        this.scenicAddress = scenicAddress;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_scenic_ticket, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_ticket_title);
           // viewHolder.ivHead = (ImageView) convertView.findViewById(R.id.iv_ticket_head);
            viewHolder.know = (TextView) convertView.findViewById(R.id.tv_ticket_know);
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_ticket_price);
            viewHolder.llBook = (LinearLayout) convertView.findViewById(R.id.ll_ticket_book);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String title = list.get(position).getProductName().trim();
        viewHolder.title.setText(title);
       /* if (!TextUtils.isEmpty(title)) {
            if (viewHolder.ivHead.getVisibility() == View.GONE) {
                viewHolder.ivHead.setVisibility(View.VISIBLE);
            }
            if (title.contains("成人")) {
                viewHolder.ivHead.setImageResource(R.drawable.icon_ticket_adult);
            } else if (title.contains("儿童")) {
                viewHolder.ivHead.setImageResource(R.drawable.icon_ticket_children);
            } else if (title.contains("老人")) {
                viewHolder.ivHead.setImageResource(R.drawable.icon_ticket_oldman);
            } else {
                viewHolder.ivHead.setVisibility(View.GONE);
            }
        }*/
        final int price;
        if (list.get(position).getWebPrice() != null
                && !list.get(position).getWebPrice().equals("")
                && list.get(position).getWebPrice()!= null
                && !TextUtils.isEmpty(list.get(position).getWebPrice())) {
            price = Integer.parseInt(MyText2Utils.getIntPrice(list.get(position).getWebPrice()));
            MyText2Utils.formatYuanPrice(mContext, viewHolder.price, list.get(position).getWebPrice());
            //点击预定跳转
            viewHolder.llBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // PrefUtils.deleteString(MyApplication.getContex());
                   /* Intent intent = new Intent();
                    intent.setClass(mContext, FillInScenicOrderActivity.class);*/
                    Intent intent = new Intent(mContext, FillInScenicOrderActivity .class);
                    // intent.putExtra("sid", list.get(position).);
                    intent.putExtra("tid", list.get(position).getProductId());
                    intent.putExtra("scenicTitle", scenicTitle.trim());
                    intent.putExtra("scenicAddress", scenicAddress.trim());
                    intent.putExtra("ticketTitle", list.get(position).getProductName().trim());
                    intent.putExtra("price", price);
                    intent.putExtra("custInfoLimit", list.get(position).getTicketlistinfo().getCustinfolimit());
                    //Log.i("0102", "result===" + list.get(position).getTicketlistinfo().getCustinfolimit());
                    intent.putExtra("position", position);

                   // Log.i("0102", "result===" + list.get(position).getTicketlistinfo().getCustinfolimit());
                    mContext.startActivity(intent);

                }
            });
        } else {
            viewHolder.price.setText("暂无价格");
            viewHolder.llBook.setClickable(false);
        }
        //inpolicy = list.get(position).getTicketlistinfo().getInfo();
        viewHolder.know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ScenicBookInfoActivity.class);
                intent.putExtra("book", (Serializable) list);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
           /*     builder.setTitle("预订须知");
                builder.setMessage(inpolicy);
                builder.setCancelable(true);

                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();*/

            }
        });

        return convertView;
    }

    static class ViewHolder {
       // ImageView ivHead;
        TextView title;
        TextView know;
        TextView price;
        LinearLayout llBook;

    }
}