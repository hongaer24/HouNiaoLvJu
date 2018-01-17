package cn.houno.houniaolvju.activity.scenic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.PersonInfoAdapter;
import cn.houno.houniaolvju.adapter.PersonsListAdapter;
import cn.houno.houniaolvju.bean.GetScenicPassengerBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PassengerStorage;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

public class PersonsListActivity extends AppCompatActivity implements PersonsListAdapter.GetScenicListener, PersonsListAdapter.CheckInterface {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_title_person)
    TextView tvTitlePerson;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.address_listv)
    ListView addressListv;
    @Bind(R.id.ll_scenic_order_price)
    LinearLayout llScenicOrderPrice;
    @Bind(R.id.add_address_btn)
    Button addAddressBtn;
    private String userid;
    private PersonsListActivity mActivity;

    private PersonsListAdapter madapter;
   // private PersonInfoAdapter mPersonInfoAdapter;
    private List<GetScenicPassengerBean.DataBean> touristDataBean;
    private int personNum;
    private int variaNum;
    private int checkdeNum=0;
    private List<GetScenicPassengerBean.DataBean> GetScenicBeanList=new ArrayList<>();
    private Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_numer_of_person);
        ButterKnife.bind(this);
        mActivity = PersonsListActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initData();
        initEvent();
    }

    private void initData() {
        Intent intent = getIntent();
        mIntent=getIntent();
        personNum = intent.getIntExtra("persons", 0);
        variaNum=personNum;
        PrefUtils.setInt(mActivity, "num", personNum);
        PrefUtils.setInt(mActivity, "variaNum", variaNum);
        tvTitlePerson.setText("您还需选择" + personNum + "个出游人（0/" + personNum + "）");
        addAddressBtn.setClickable(false);
        addAddressBtn.setBackgroundColor(Color.parseColor("#dddddd"));
        getDataFromServer();

    }
    private void initEvent() {


    }

    private void getDataFromServer() {
        userid = PrefUtils.getString(mActivity, "userid", "");
        RequestParams params = new RequestParams(Constants.GET_TOURIST_URL);
        params.addBodyParameter("userid", userid);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");

                    if (status == 0) {

                        parseData(result);

                    } else {
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
               /* pbLoading.setVisibility(View.GONE);
                tvLoading.setText("加载失败");*/
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                //rfvScenicDetail.stopRefresh();
            }
        });
    }

    private void parseData(String result) {

        Gson gson = new Gson();
        GetScenicPassengerBean getScenicPassengerBean = gson.fromJson(result, GetScenicPassengerBean.class);
        touristDataBean = getScenicPassengerBean.getData();
        Log.i("7878", "result==="+ touristDataBean);


        if (madapter == null) {
            madapter = new PersonsListAdapter(this, this, touristDataBean,  personNum);
            madapter.setCheckInterface(this);
            addressListv.setAdapter(madapter);
        } else {
            madapter.setData(touristDataBean);
        }



        // showData(touristDataBean);


    }

    /*private void getSelectPassengers(){
        GetScenicBeanList.clear();
        for(int i=0;i<GetScenicBeanList.size();i++){
            if(GetScenicBeanList.get(i).isChoosed()){
                PassengerStorage.getInstance().addData(GetScenicBeanList.get(i));
            }
        }

    }*/

    /*private void showData(List<GetScenicPassengerBean.DataBean> touristsMessageBeanList) {
        madapter = new PersonsListAdapter(this, this, touristsMessageBeanList);
        addressListv.setAdapter(madapter);
    }*/

    @OnClick({R.id.iv_back, R.id.add_address_btn, R.id.tv_add_person})
    public void onViewClicked(View view) {
        // int position = Integer.valueOf(view.getTag().toString());

        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.add_address_btn:
                mIntent.putExtra("list", (Serializable) GetScenicBeanList);
                setResult(RESULT_OK, mIntent);
                finish();
                break;
            case R.id.tv_add_person:
                Intent intent = new Intent(this, PersonsEditActivity.class);
                //intent.putExtra("consignee",getScenicBean);
                //intent.putExtra("id",touristDataBean.get(position).getId() );
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemEdit(GetScenicPassengerBean.DataBean getScenicBean) {
        Intent intent = new Intent(this, PersonsEditActivity.class);
        intent.putExtra("consignee", getScenicBean);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void CheckPersonNum(int position, boolean ischecked,GetScenicPassengerBean.DataBean GetScenicBean) {

        int newNum = personNum;

        if (ischecked) {
            if(variaNum>0){
                variaNum--;
                checkdeNum++;
                tvTitlePerson.setText("您还需选择" + variaNum + "个出游人（"+checkdeNum+"/" + newNum + "）");
                GetScenicBean.setChoosed(ischecked);
                GetScenicBeanList.add(GetScenicBean);

            }


        } else {
            if(variaNum<newNum){
                variaNum++;
                checkdeNum--;
                tvTitlePerson.setText("您还需选择" + variaNum + "个出游人（"+checkdeNum+"/" + newNum + "）");
                GetScenicBean.setChoosed(ischecked);
                PassengerStorage.getInstance().updataData(GetScenicBean);
            }

        }
        if(variaNum==0){
            addAddressBtn.setBackgroundResource(R.drawable.selector_orgs_btn);
            addAddressBtn.setClickable(true);
        }else {
            addAddressBtn.setClickable(false);
            addAddressBtn.setBackgroundColor(Color.parseColor("#dddddd"));
        }
        PrefUtils.setInt(mActivity, "variaNum", variaNum);
        //touristDataBean.get(position).setChoosed(ischecked);


        // tvTitlePerson.setText("您还需选择"+personNum+"个出游人（0/"+newNum+"）");
    }


}
