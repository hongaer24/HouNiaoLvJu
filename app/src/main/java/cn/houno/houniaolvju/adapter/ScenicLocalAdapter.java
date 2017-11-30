package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.ScenicIndexBean.LocalBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：Houniaolvju
 * 类描述：景点-当地景点
 * 创建人：qzc
 * 创建时间：2017/01/03 14:38
 * 修改人：qzc
 * 修改时间：2017/01/03 14:38
 * 修改备注：
 */
public class ScenicLocalAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<LocalBean> mList;

    public ScenicLocalAdapter(Context context, List<LocalBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<LocalBean> list) {
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
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.listitem_scenic, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_scenic_title);
            viewHolder.tvStar = (TextView) convertView.findViewById(R.id.tv_scenic_star);
            viewHolder.tvCate = (TextView) convertView.findViewById(R.id.tv_scenic_cate);
            viewHolder.tvHits = (TextView) convertView.findViewById(R.id.tv_scenic_hits);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_scenic_price);
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_scenic_img);
            viewHolder.flLabel = (FrameLayout) convertView.findViewById(R.id.fl_scenic_label);
            viewHolder.ivLabel = (ImageView) convertView.findViewById(R.id.iv_scenic_label);
            viewHolder.tvLabel = (TextView) convertView.findViewById(R.id.tv_scenic_label);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            viewHolder.flLabel.setVisibility(View.VISIBLE);
            viewHolder.ivLabel.setImageResource(R.drawable.ga_scenic_first);
            viewHolder.tvLabel.setText("1");
        } else if (position == 1) {
            viewHolder.flLabel.setVisibility(View.VISIBLE);
            viewHolder.ivLabel.setImageResource(R.drawable.ga_scenic_second);
            viewHolder.tvLabel.setText("2");
        } else if (position == 2) {
            viewHolder.flLabel.setVisibility(View.VISIBLE);
            viewHolder.ivLabel.setImageResource(R.drawable.ga_scenic_third);
            viewHolder.tvLabel.setText("3");
        } else {
            viewHolder.flLabel.setVisibility(View.GONE);
        }
        x.image().bind(viewHolder.ivImg, mList.get(position).getDefaultpic(), DisplayUtil.getImageOptions());
        viewHolder.tvTitle.setText(mList.get(position).getScenicname());
        //viewHolder.tvStar.setText(mList.get(position).getStar());
        //viewHolder.tvCate.setText(mList.get(position).getCate_name());
        viewHolder.tvHits.setText(mList.get(position).getHits());
        if (mList.get(position).getWebprice() != null && mList.get(position).getWebprice() != null
                && !mList.get(position).getWebprice().equals("") && !mList.get(position).getWebprice().equals("")) {

            MyText2Utils.formatYuanPrice(mContext, viewHolder.tvPrice, mList.get(position).getWebprice());
        }else {
            viewHolder.tvPrice.setText("暂无价格");
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView ivImg;
        TextView tvTitle;
        TextView tvStar;
        TextView tvCate;
        TextView tvHits;
        TextView tvPrice;

        FrameLayout flLabel;
        ImageView ivLabel;
        TextView tvLabel;
    }

}
