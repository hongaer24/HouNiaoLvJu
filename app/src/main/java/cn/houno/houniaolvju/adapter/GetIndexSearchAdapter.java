package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.GetIndexSearchBean.DataBean;

/**
 * 项目名称：Houniaolvju
 * 类描述：酒店列表
 * 创建人：qzc
 * 创建时间：2017/1/12 18:01
 * 修改人：qzc
 * 修改时间：2017/1/12 18:01
 * 修改备注：
 */
public class GetIndexSearchAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<DataBean> mList;


    public GetIndexSearchAdapter(Context context, List<DataBean> datas) {
        mContext = context;
        mList = datas;
        mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<DataBean> datas) {
        mList = datas;
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
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.listitem_search, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_search_title);
            viewHolder.tvCate = (TextView) convertView.findViewById(R.id.tv_search_cate);
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_search_img);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(mList.get(position).getTitle());
        String model = mList.get(position).getModel();
        if (model != null && !TextUtils.isEmpty(model)) {
            if ("hotel".equals(model)) {
                viewHolder.tvCate.setText("酒店");
                viewHolder.ivImg.setImageResource(R.drawable.label_order_hotel);
            } else {
                viewHolder.tvCate.setText("景点");
                viewHolder.ivImg.setImageResource(R.drawable.label_order_scenic);
            }
        }
        return convertView;
    }

    static class ViewHolder {

        ImageView ivImg;
        TextView tvTitle;
        TextView tvCate;
    }

}
