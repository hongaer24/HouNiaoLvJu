package cn.houno.houniaolvju.activity.scenic;

import android.content.Intent;
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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.PersonsListAdapter;
import cn.houno.houniaolvju.bean.GetScenicPassengerBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;

public class PersonsListActivity extends AppCompatActivity implements PersonsListAdapter.GetScenicListener{

    @Bind(R.id.iv_back)
    ImageView ivBack;
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
   private List<GetScenicPassengerBean.DataBean> touristDataBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_numer_of_person);
        ButterKnife.bind(this);
        mActivity = PersonsListActivity.this;
        initData();
    }

    private void initData() {
        getDataFromServer();
    }

    private void getDataFromServer() {
        userid = PrefUtils.getString(mActivity, "userid", "");
        RequestParams params = new RequestParams(Constants.GET_TOURIST_URL);
        params.addBodyParameter("userid", userid);
        Log.i("888", "id===" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    Log.i("888", "id===" + status);
                    if (status == 0) {
                        // Log.i("999", "result==="+result);
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

        showData(touristDataBean);


    }

    private void showData(List<GetScenicPassengerBean.DataBean> touristsMessageBeanList) {
                       madapter=new PersonsListAdapter(this,this,touristsMessageBeanList);
                       addressListv.setAdapter(madapter);
    }

    @OnClick({R.id.iv_back, R.id.add_address_btn,R.id.tv_add_person})
    public void onViewClicked(View view) {
       // int position = Integer.valueOf(view.getTag().toString());

        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.add_address_btn:
                break;
            case R.id.tv_add_person:
                Intent intent = new Intent(this, PersonsEditActivity.class);
                //intent.putExtra("id",touristDataBean.get(position).getId() );
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemEdit(GetScenicPassengerBean.DataBean getScenicBean) {
        Intent intent = new Intent(this, PersonsEditActivity.class);
        intent.putExtra("consignee",getScenicBean);
        startActivity(intent);
    }

    @Override
    public void onItemCheck(GetScenicPassengerBean.DataBean GetScenicBean) {
             GetScenicBean.setChoosed(true);
    }
    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
