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
import cn.houno.houniaolvju.bean.FenQuanListBean;
import cn.houno.houniaolvju.utils.DisplayUtil;

/**
 * 项目名称：Houniaolvju
 * 类描述：
 * 创建人：qzc
 * 创建时间：2016/12/21 16:41
 * 修改人：qzc
 * 修改时间：2016/12/21 16:41
 * 修改备注：
 */
public class FqHousesAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflate;
    private List<FenQuanListBean.DataBean> mList;

    public FqHousesAdapter(Context context) {
        mContext = context;
        mInflate = LayoutInflater.from(context);
    }

    public FqHousesAdapter(Context context, List<FenQuanListBean.DataBean> list) {
        mContext = context;
        mList = list;
        mInflate = LayoutInflater.from(context);
    }

    public void setData(List<FenQuanListBean.DataBean> list) {
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
            convertView = mInflate.inflate(R.layout.listitem_share_property, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.IvImg = (ImageView) convertView.findViewById(R.id.iv_share_img);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_share_title);
            viewHolder.tvProject = (TextView) convertView.findViewById(R.id.tv_share_project);
            viewHolder.tvTel = (TextView) convertView.findViewById(R.id.tv_share_tel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        x.image().bind(viewHolder.IvImg, mList.get(position).getImg(), DisplayUtil.getImageOptions());
        viewHolder.tvTitle.setText(mList.get(position).getTitle());
        viewHolder.tvProject.setText(mList.get(position).getProject());
        viewHolder.tvTel.setText(mList.get(position).getTel());
        return convertView;
    }

    static class ViewHolder {
        ImageView IvImg;
        TextView tvTitle;
        TextView tvProject;
        TextView tvTel;
    }
}
