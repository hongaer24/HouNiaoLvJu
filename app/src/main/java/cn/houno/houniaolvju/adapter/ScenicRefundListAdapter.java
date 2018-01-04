package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.scenic.ScenicRefundLIstActivity;
import cn.houno.houniaolvju.bean.GetScenicPassengerBean;
import cn.houno.houniaolvju.bean.ScenicListBean;

/**
 * Created by 123 on 2018/1/3.
 */

public class ScenicRefundListAdapter extends BaseAdapter {

    private  Context mContext;
    private LayoutInflater mInflater;
    private String[] mDatas;
    private int num=1;
    private CheckInterface checkInterface;

   /* public ScenicRefundListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }*/

    public ScenicRefundListAdapter(Context context, String[] refundType) {
               this.mContext=context;
               this.mDatas=refundType;
               mInflater = LayoutInflater.from(context);
    }

    public void setDatas(String[] datas) {
        mDatas = datas;
       // Log.i("777", "datas===== "+  mDatas.toString());
        notifyDataSetChanged();

    }
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }


    @Override
    public int getCount() {
        return mDatas == null ? 0 :mDatas.length;

    }

    @Override
    public Object getItem(int position) {
          return mDatas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_refund, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
            holder.cbPassengersCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        if(num>0){
                            checkInterface.CheckType(position,mDatas[position]);
                            if(holder.cbPassengersCheck.isChecked()){
                                num--;
                            }else {
                                num++;
                            }
                        }else if(num==0){

                            if(holder.cbPassengersCheck.isChecked()){
                                holder.cbPassengersCheck.setChecked(false);
                            }else {
                                num++;
                            }
                        }


                }
            });

        holder.tvPassengersName.setText(mDatas[position]);

        return convertView;
    }



   static  class ViewHolder {
        @Bind(R.id.cb_passengers_check)
        CheckBox cbPassengersCheck;
        @Bind(R.id.tv_passengers_name)
        TextView tvPassengersName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public interface CheckInterface {
        void CheckType(int position,String type);

    }
}
