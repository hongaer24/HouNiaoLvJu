package cn.houno.houniaolvju.activity.flight;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.PassengersListBean;
import cn.houno.houniaolvju.bean.PassengersListBean.DataBean;

/**
 * 添加乘机人页面的乘机人列表适配器
 * Created by qzc on 2017/2/8.
 */

public class SelectPassengersAdapter extends BaseAdapter {
    private SelectPassengersActivity mContext;
    private LayoutInflater mInflater;
    private List<DataBean> mList;   //全部列表
    private List<DataBean> mSelectList = new ArrayList<>(); //选中列表
    public HashMap<Integer, Boolean> mIsChecked = new HashMap<>();   //选中数
    private int mSelectNum = 0;


    public SelectPassengersAdapter(SelectPassengersActivity context, List<DataBean> list) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = list;
        //初始化选中事件、全部默认为未选中
        for (int i = 0; i < list.size(); i++) {
            mIsChecked.put(i, false);
        }
    }


    public List<PassengersListBean.DataBean> getSelectList() {
        //获取选中的列表返回
        for (int i = 0; i < mIsChecked.size(); i++) {
            if (mIsChecked.get(i)) {
                if (!mList.get(i).getBirthday().isEmpty()&&!mList.get(i).getPhone().isEmpty()){
                    mSelectList.add(mList.get(i));
                }
            }
        }
        return mSelectList;
    }

    public void setData(List<DataBean> list) {
        mList = list;
        for (int i = 0; i < list.size(); i++) {
            if (mIsChecked.get(i) == null) {
                mIsChecked.put(i, false);
            }
        }
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
    public View getView(final int position,  View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_passengers_add, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvPassengersName.setText(mList.get(position).getName());
        holder.tvCertificateNum.setText(mList.get(position).getIdentityno());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转编辑页面
                Intent intent = new Intent();
                intent.setClass(mContext, EditPassengersActivity.class);
                intent.putExtra("type", "edit");
                intent.putExtra("data", mList.get(position));
                intent.putExtra("position", position);
                mContext.startActivityForResult(intent, 201);
            }
        });

        holder.cbPassengersCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("onChecked");
                if (mSelectNum < 9) {
                    if (isChecked) {
                        mSelectNum++;
                    } else {
                        mSelectNum--;
                    }
                    mIsChecked.put(position, isChecked);
                }else {
                    if (isChecked){
                        holder.cbPassengersCheck.setChecked(false);
                        Toast.makeText(mContext,"最多选择9个乘机人",Toast.LENGTH_SHORT).show();
                    }else {
                        mSelectNum--;
                        mIsChecked.put(position, false);
                    }
                }
            }
        });

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.cb_passengers_check)
        CheckBox cbPassengersCheck;
        @Bind(R.id.tv_passengers_name)
        TextView tvPassengersName;
        @Bind(R.id.tv_certificate)
        TextView tvCertificate;
        @Bind(R.id.tv_certificate_num)
        TextView tvCertificateNum;
        @Bind(R.id.iv_edit)
        ImageView ivEdit;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
