package cn.houno.houniaolvju.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.GetScenicPassengerBean;
import cn.houno.houniaolvju.bean.PassengersListBean;
import cn.houno.houniaolvju.utils.PassengerStorage;
import cn.houno.houniaolvju.utils.PrefUtils;

/**
 * Created by 123 on 2017/11/28.
 */

public class PersonsListAdapter extends BaseAdapter implements View.OnClickListener {

    private final Context context;
    //private final TextView tvTitlePerson;
    private final int personNum;
    private int constantNum;
    private int variaNum;
    private List<GetScenicPassengerBean.DataBean> datas;
    private final LayoutInflater mInflate;
    private final GetScenicListener mGetScenicListener;
    @Bind(R.id.person_edit_btn)
    Button personEditBtn;
    @Bind(R.id.determine_chekbox)
    CheckBox checkBox;
    private GetScenicPassengerBean.DataBean touristsBean;
    private CheckInterface checkInterface;
    private List<GetScenicPassengerBean.DataBean>  mSelectList = new ArrayList<>(); //选中列表
    public HashMap<Integer, Boolean> mIsChecked = new HashMap<>();   //选中数
    private Boolean isChecked;
    //private PersonInfoInterface personInfoInterface;


    public PersonsListAdapter(Context context, GetScenicListener getScenicListener, List<GetScenicPassengerBean.DataBean> touristsMessageBeanList, int   personNum) {
        this.context = context;
        this.datas = touristsMessageBeanList;
        this.mGetScenicListener = getScenicListener;
        this.personNum =personNum;
        mInflate = LayoutInflater.from(context);
        variaNum = PrefUtils.getInt(context, "variaNum", 0);
        constantNum=variaNum;
        for (int i = 0; i < datas.size(); i++) {
            mIsChecked.put(i, false);
        }

    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }



    public void setData(List<GetScenicPassengerBean.DataBean> touristsMessageBeanList) {
        if (touristsMessageBeanList == null) return;
        this.datas = touristsMessageBeanList;
        for (int i = 0; i < datas.size(); i++) {
            if (mIsChecked.get(i) == null) {
                mIsChecked.put(i, false);
            }
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        if (datas == null) return null;
       // Log.i("2211", "result===" + datas.get(position));
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        if(datas == null) return -1;
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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
        final GetScenicPassengerBean.DataBean touristsBean = datas.get(position);


          holder.checkBox.setChecked(touristsBean.isChoosed());

          holder.checkBox.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(constantNum>0){
                      holder.checkBox.setChecked(((CheckBox) v).isChecked());
                      checkInterface.CheckPersonNum(position, ((CheckBox) v).isChecked(),touristsBean);

                      if(holder.checkBox.isChecked()){
                          constantNum--;
                      }else {
                          constantNum++;
                      }
                      mIsChecked.put(position, ((CheckBox) v).isChecked());
                  }else if(constantNum==0){
                      if (touristsBean.isChoosed()) {
                          holder.checkBox.setChecked(((CheckBox) v).isChecked());
                          checkInterface.CheckPersonNum(position, ((CheckBox) v).isChecked(),touristsBean);
                          if (holder.checkBox.isChecked()) {
                              constantNum--;
                          } else {
                              constantNum++;
                          }
                          mIsChecked.put(position, ((CheckBox) v).isChecked());
                      } else {
                          holder.checkBox.setChecked(false);
                          Toast.makeText(context,"您最多能选择"+personNum+"个出游人",Toast.LENGTH_SHORT).show();

                      }
                  }
                  touristsBean.setChoosed(((CheckBox) v).isChecked());

              }

        });



          holder.personEditBtn.setOnClickListener(this);
          holder.personEditBtn.setTag(position);

          holder.personEditTxtv.setText(touristsBean.getName());
          holder.personEditTxtv2.setText("手机号：" + touristsBean.getPhone());
          holder.personEditTxtv3.setText("身份证号：" + touristsBean.getIdentityno());

          holder.checkBox.setTag(position);
          notifyDataSetChanged();

        return convertView;
    }

    public List<GetScenicPassengerBean.DataBean>  getSelectList() {
        //获取选中的列表返回
        for (int i = 0; i < mIsChecked.size(); i++) {
            if (mIsChecked.get(i)) {
                    mSelectList.add(datas.get(i));
            }
        }
        return mSelectList;
    }

    @Override
    public void onClick(View view) {
        int position = Integer.valueOf(view.getTag().toString());
         touristsBean = datas.get(position);
        switch (view.getId()) {
            case R.id.person_edit_btn:
                if (mGetScenicListener != null)
                    mGetScenicListener.onItemEdit(touristsBean);

                break;

           /* case R.id.determine_chekbox:
                if (personInfoInterface != null)
                    personInfoInterface.CheckPersonIfno(touristsBean);
                break;*/

        }
    }
  /*  public List<PassengersListBean.DataBean> getSelectList() {
        //获取选中的列表返回
        for (int i = 0; i < mIsChecked.size(); i++) {
            if (mIsChecked.get(i)) {
                if (!mList.get(i).getBirthday().isEmpty()&&!mList.get(i).getPhone().isEmpty()){
                    mSelectList.add(mList.get(i));
                }
            }
        }
        return mSelectList;
    }*/


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
        /* @Bind(R.id.person_edit_btn2)
         Button personEditBtn2;*/
        @Bind(R.id.determine_chekbox)
        CheckBox checkBox;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface GetScenicListener {
        void onItemEdit(GetScenicPassengerBean.DataBean GetScenicBean);
        // void onItemCheck(GetScenicPassengerBean.DataBean GetScenicBean);
    }

   /* public interface CheckScenicListener {
        void onItemCheck(GetScenicPassengerBean.DataBean GetScenicBean);
        // void onItemCheck(GetScenicPassengerBean.DataBean GetScenicBean);
    }*/

    public interface CheckInterface {
        void CheckPersonNum(int position, boolean ischecked,GetScenicPassengerBean.DataBean GetScenicBean);
        //void CheckPersonIfno(GetScenicPassengerBean.DataBean GetScenicBean);
    }

    /*public interface PersonInfoInterface {

        void CheckPersonIfno(GetScenicPassengerBean.DataBean GetScenicBean);
    }*/


}
