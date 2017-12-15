package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.GetScenicPassengerBean;
import cn.houno.houniaolvju.bean.PassengersListBean;

/**
 * Created by 123 on 2017/12/8.
 */

public class PersonInfoAdapter extends BaseAdapter {

    private final int personNum;
    private Context context;
    private List<GetScenicPassengerBean.DataBean> datas;
    private LayoutInflater mInflate;


    public PersonInfoAdapter(Context context, List<GetScenicPassengerBean.DataBean> touristDataBean,int personNum) {
        this.context = context;
        this.datas = touristDataBean;
        this.personNum=personNum;
        mInflate = LayoutInflater.from(context);

    }
   /* public void setPersonInfoInterface(PersonsListAdapter.PersonInfoInterface personInfoInterface) {
        this.personInfoInterface = personInfoInterface;
    }*/
   public void setData(List<GetScenicPassengerBean.DataBean> list) {
       this.datas= list;
       notifyDataSetChanged();
   }

    @Override
    public int getCount() {

       return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {

        if (datas == null) return null;
        // Log.i("2211", "result===" + datas.get(position));
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            //使用自定义的list_items作为Layout
            convertView = mInflate.inflate(R.layout.person_info_item, parent, false);
            //使用减少findView的次数
            holder = new ViewHolder(convertView);

            //设置标记
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

                final GetScenicPassengerBean.DataBean touristsBean = datas.get(position);

                holder.tvPersonId.setText("身份证："+touristsBean.getIdentityno());
                holder.tvPersoninfo.setText(touristsBean.getName());


        return convertView;

    }

    static class ViewHolder {
        @Bind(R.id.tv_personinfo)
        TextView tvPersoninfo;
        @Bind(R.id.tv_person_id)
        TextView tvPersonId;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
