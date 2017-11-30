package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.FlashSaleListBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：限时抢购列表
 * 创建人：qzc
 * 创建时间：2016/12/29 10:40
 * 修改人：qzc
 * 修改时间：2016/12/29 10:40
 * 修改备注：
 */
public class FlashSaleListAdapter extends BaseAdapter {

    private List<FlashSaleListBean.DataBean> list;
    private LayoutInflater mInflater;

    private Context mContext;

    public FlashSaleListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public FlashSaleListAdapter(Context context, List<FlashSaleListBean.DataBean> list) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.list = list;

    }

    public void setData(List<FlashSaleListBean.DataBean> list) {
        this.list = list;
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
            convertView = mInflater.inflate(R.layout.listitem_flash_sale, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.iv_img);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.tvActTitle = (TextView) convertView.findViewById(R.id.tv_order_title_txt);
            viewHolder.tvDateLength = (TextView) convertView.findViewById(R.id.tv_length);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        x.image().bind(viewHolder.img, list.get(position).getImg(), DisplayUtil.getImageOptions());   //图片

        viewHolder.tvActTitle.setText(list.get(position).getTitle());   //标题
        String ksDate = list.get(position).getKsdate();
        String jsDate = list.get(position).getJsdate();
        viewHolder.tvDateLength.setText(ksDate + "~" + jsDate);  //活动时间
        //价格
        MyText2Utils.formatQiPrice(mContext, viewHolder.tvPrice
                , list.get(position).getPrice());
        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView tvActTitle;
        TextView tvPrice;
        TextView tvDateLength;
    }
}
