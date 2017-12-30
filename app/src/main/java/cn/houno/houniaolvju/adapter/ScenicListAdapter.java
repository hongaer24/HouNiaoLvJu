package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.ScenicListBean;
import cn.houno.houniaolvju.bean.ScenicListBean.DataBean;
import cn.houno.houniaolvju.utils.DisplayUtil;
import cn.houno.houniaolvju.utils.MyText2Utils;

/**
 * 项目名称：Houniaolvju
 * 类描述：景点列表
 * 创建人：qzc
 * 创建时间：2016/01/04 10:19
 * 修改人：qzc
 * 修改时间：2016/01/04 10:19
 * 修改备注：
 */
public class ScenicListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<ScenicListBean.DataBean> mDatas;

    public ScenicListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<ScenicListBean.DataBean> datas) {
        mDatas = datas;
        Log.i("777", "datas===== "+  mDatas.toString());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {

        return mDatas.get(position);

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
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_scenic_price);
            viewHolder.tvCate = (TextView) convertView.findViewById(R.id.tv_scenic_cate);
            viewHolder.tvStar = (TextView) convertView.findViewById(R.id.tv_scenic_star);
            viewHolder.tvHits = (TextView) convertView.findViewById(R.id.tv_scenic_hits);
            viewHolder.ivImg = (ImageView) convertView.findViewById(R.id.iv_scenic_img);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitle.setText(mDatas.get(position).getScenicname());
       // Log.i("777", "datas===== "+  mDatas.get(position).getTitle());
        //viewHolder.tvCate.setText(mDatas.get(position).getOpentime());
        viewHolder.tvHits.setText(mDatas.get(position).getScenicaddress());
        viewHolder.tvStar.setText(mDatas.get(position).getOpentime());
        if (mDatas.get(position).getSaleprice() != null && !TextUtils.isEmpty(mDatas.get(position).getSaleprice() )) {
            MyText2Utils.formatQiPrice(mContext, viewHolder.tvPrice, mDatas.get(position).getSaleprice() );
        } else {
            viewHolder.tvPrice.setText("暂无价格");
        }
        x.image().bind(viewHolder.ivImg, mDatas.get(position).getDefaultpic(), DisplayUtil.getImageOptions());

        return convertView;
    }

    static class ViewHolder {

        ImageView ivImg;
        TextView tvTitle;
        TextView tvPrice;
        TextView tvCate;
        TextView tvHits;
        TextView tvStar;
    }

}
