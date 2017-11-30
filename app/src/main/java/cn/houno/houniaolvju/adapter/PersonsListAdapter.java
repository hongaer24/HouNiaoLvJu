package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.scenic.PersonsEditActivity;
import cn.houno.houniaolvju.bean.GetScenicPassengerBean;

/**
 * Created by 123 on 2017/11/28.
 */

public class PersonsListAdapter extends BaseAdapter implements View.OnClickListener {

    private final Context context;
    private final List<GetScenicPassengerBean.DataBean> datas;
    private final LayoutInflater mInflate;
    private final GetScenicListener mGetScenicListener;
    @Bind(R.id.person_edit_btn)
    Button personEditBtn;
    @Bind(R.id.person_edit_btn2)
    Button personEditBtn2;
    private Context mContext;
    private GetScenicPassengerBean.DataBean touristsBean;
    //private LayoutInflater mInflater;

    public PersonsListAdapter(Context context, GetScenicListener getScenicListener, List<GetScenicPassengerBean.DataBean> touristsMessageBeanList) {
        this.context = context;
        this.datas = touristsMessageBeanList;
        this.mGetScenicListener=getScenicListener;
        mInflate = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        if (datas == null) return null;
        Log.i("2211", "result===" + datas.get(position));
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (datas == null) return -1;
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            //使用自定义的list_items作为Layout
            convertView = mInflate.inflate(R.layout.person_list_item, parent, false);
            //使用减少findView的次数
            holder = new ViewHolder(convertView);

            //设置标记
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.personEditBtn.setOnClickListener(this);
        holder.personEditBtn.setTag(position);

        holder.personEditBtn2.setOnClickListener(this);
        holder.personEditBtn2.setTag(position);
       /* holder.personEditBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {
                touristsBean.setChoosed(true);}
        });*/



        GetScenicPassengerBean.DataBean touristsBean = datas.get(position);
        holder.personEditTxtv.setText(touristsBean.getName());
        holder.personEditTxtv2.setText("手机号：" + touristsBean.getPhone());
        holder.personEditTxtv3.setText("身份证号：" + touristsBean.getIdentityno());

        if((touristsBean.isChoosed())){
            holder.personEditBtn2.setBackgroundResource(R.drawable.icon_checked);

        }else{
            holder.personEditBtn2.setBackgroundResource(R.drawable.icon_checked);

        }
        return convertView;
    }
/*
    @OnClick({R.id.person_edit_btn, R.id.person_edit_btn2})
    public void onViewClicked(View view) {
        int position = Integer.valueOf(view.getTag().toString());
        touristsBean = datas.get(position);
        switch (view.getId()) {
            case R.id.person_edit_btn:
                if(mGetScenicListener!=null)
                   mGetScenicListener.onItemEdit(touristsBean);

                break;
            case R.id.person_edit_btn2:
                break;
        }
    }*/

    @Override
    public void onClick(View view) {
        int position = Integer.valueOf(view.getTag().toString());
        touristsBean = datas.get(position);
        switch (view.getId()) {
            case R.id.person_edit_btn:
                if(mGetScenicListener!=null)
                    mGetScenicListener.onItemEdit(touristsBean);
                break;
            case R.id.person_edit_btn2:
                if(mGetScenicListener!=null)
                    mGetScenicListener.onItemCheck(touristsBean);
                break;
        }
    }


    static class ViewHolder {
        @Bind(R.id.person_edit_btn)
        Button personEditBtn;
        @Bind(R.id.person_edit_txtv)
        TextView personEditTxtv;
        @Bind(R.id.person_edit_txtv2)
        TextView personEditTxtv2;
        @Bind(R.id.person_edit_txtv3)
        TextView personEditTxtv3;
        @Bind(R.id.ll_count_down)
        LinearLayout llCountDown;
        @Bind(R.id.person_edit_btn2)
        Button personEditBtn2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    public interface GetScenicListener{
        void onItemEdit(GetScenicPassengerBean.DataBean GetScenicBean );
        void onItemCheck(GetScenicPassengerBean.DataBean GetScenicBean );

    }

}
