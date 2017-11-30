package cn.houno.houniaolvju.activity.train;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;



public class TrainPassengersSelectActivity extends Activity {


    @Bind(R.id.rl_add_passengers)
    RelativeLayout rlAddPassengers;

    @Bind(R.id.lv_passengers)
    ListView listView;

    private List<TrainPassengersBean> list;
    private List<TrainPassengersBean> toOrderList;

    List<Integer> listItemID=new ArrayList<>();

    TrainPassengersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("SelectP","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_select_passengers);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(TrainPassengersSelectActivity.this, R.color.app_theme_green);

        initView();
        initData();
        initEvent();
    }

    private void initView(){

    }
    private void initData(){
        getDataFromServer();
    }

    private void initEvent() {

    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.TRAIN_PASSENGERS_SELECT);
        params.addBodyParameter("userid", PrefUtils.getString(this,"userid",PrefUtils.getString(this,"userid","")));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result);
                    } else {
                        Toast.makeText(TrainPassengersSelectActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled");
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished");
            }
        });
    }

    private void parseData(String result) {
        Log.e("TAG", "进入parseData");
        Gson gson = new Gson();
        try {
            Log.e("TAG", "开始解析json");
            String jsonStr = new JsonParser().parse(result).getAsJsonObject().get("data").toString();
            list = gson.fromJson(jsonStr, new TypeToken<List<TrainPassengersBean>>() {}.getType());

            if(list!=null&&list.size()>0){
                adapter=new TrainPassengersAdapter(this,list,TrainPassengersAdapter.FROM_PASSENGERS_SELECT);
                listView.setAdapter(adapter);
            }
            Log.e("TAG", list.toString());
            Log.e("TAG", "解析json完毕");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "解析json出错");
        }
    }


    @OnClick({R.id.iv_back,R.id.tv_confirm,R.id.rl_add_passengers})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.iv_back:{
                finish();
                break;
            }

            case R.id.rl_add_passengers:{
                Intent intent=new Intent(this,TrainPassengersEditActivity.class);
                startActivityForResult(intent,0);
                break;
            }

            case R.id.tv_confirm:{
                getSelectPassengers();
                Intent intent=new Intent(this,TrainOrderFillinActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("passengersList",(Serializable)toOrderList);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }

            default:{
                break;
            }
        }
    }

    private void getSelectPassengers(){
        listItemID.clear();
        for (int i=0;i<adapter.mChecked.size();i++){
            if(adapter.mChecked.get(i)){
                listItemID.add(i);
            }
        }
        toOrderList=new ArrayList<>();
        for(int j=0;j<listItemID.size();j++){
            toOrderList.add(list.get(listItemID.get(j)));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getDataFromServer();
    }
}
