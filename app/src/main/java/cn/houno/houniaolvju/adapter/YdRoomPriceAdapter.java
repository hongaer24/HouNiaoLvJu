package cn.houno.houniaolvju.adapter;

import android.content.Intent;
import android.widget.BaseAdapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.ydhotel.FillInYdOrderActivity;
import cn.houno.houniaolvju.bean.YdDetailBean;
import cn.houno.houniaolvju.fragment.myinfo.LoginActivity;
import cn.houno.houniaolvju.utils.PrefUtils;

/**
 * Created by Administrator on 2017-8-14.
 */

public class YdRoomPriceAdapter extends BaseAdapter{


    private LayoutInflater mInflater;
    private List<YdDetailBean.DataBean.RoomBean> mList;
    private Context mContext;
    private String mTitle;
    private String mAddress;


    public YdRoomPriceAdapter(Context context, List<YdDetailBean.DataBean.RoomBean> list) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mList = list;
    }

    public void setData(List<YdDetailBean.DataBean.RoomBean> list,String title,String address) {
        mList = list;
        mTitle = title;
        mAddress = address;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_yddetail_price, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.btnBook = (Button) convertView.findViewById(R.id.btn_book);
             convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitle.setText(mList.get(position).getTitle()+" "+mList.get(position).getRztime_name().getName());

        viewHolder.tvPrice.setText("¥"+mList.get(position).getUserprice());

        if (Double.valueOf(mList.get(position).getUserprice())==0){
            viewHolder.btnBook.setText("无　房");
            viewHolder.btnBook.setClickable(false);
        }else {
            viewHolder.btnBook.setClickable(true);
            viewHolder.btnBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isLogined = PrefUtils.getBoolean(mContext, "isLogined", false);
                    if (isLogined) {
                        Intent intent = new Intent();
                        intent.setClass(mContext, FillInYdOrderActivity.class);
                        intent.putExtra("title", mTitle);
                        intent.putExtra("address", mAddress);
                        intent.putExtra("roomtitle", mList.get(position).getTitle()
                                + " - " + mList.get(position).getRztime_name().getName());
                        intent.putExtra("id", mList.get(position).getYdid());
                        intent.putExtra("rid", mList.get(position).getId());
                        intent.putExtra("price", Double.parseDouble(mList.get(position).getUserprice()));
                        intent.putExtra("rzmin", mList.get(position).getRzdatemin());
                        intent.putExtra("rzmax", mList.get(position).getRzdate());
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                    }
                }
            });
        }

        return convertView;
    }





    static class ViewHolder  {

        private TextView tvTitle;
        private TextView tvPrice;
        private Button btnBook;

    }
}