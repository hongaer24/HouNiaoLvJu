package cn.houno.houniaolvju.activity.flight;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.PassengersListBean;
import cn.houno.houniaolvju.bean.PassengersListBean.DataBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerListView;

/**
 * 添加乘机人页面
 * Created by qzc on 2017/2/16.
 */

public class SelectPassengersActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_confirm)
    TextView tvConfirm;
    @Bind(R.id.rl_add_passengers)
    RelativeLayout rlAddPassengers;
    @Bind(R.id.lv_passengers)
    InnerListView lvPassengers;
    private SelectPassengersActivity mActivity;

    private String userid;

    private List<DataBean> mPassengersList = new ArrayList<>();

    private List<DataBean> toOrderPassengersLList = new ArrayList<>();

    private SelectPassengersAdapter mPassengersAdapter;

    private Intent mIntent;

    private List<Integer> listItemID = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtils.setWindowStatusBarColor(SelectPassengersActivity.this, R.color.app_theme_green);
        setContentView(R.layout.activity_flight_select_passengers);
        ButterKnife.bind(this);
        mActivity = SelectPassengersActivity.this;
        initData();
    }

    private void initData() {
        userid = PrefUtils.getString(mActivity, "userid", "");
        mIntent = getIntent();
        mPassengersAdapter = new SelectPassengersAdapter(mActivity, mPassengersList);
        lvPassengers.setAdapter(mPassengersAdapter);
        getDataFromServer();
    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.PASSENGER_LIST);
        params.addBodyParameter("userid", userid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getInt("status") == 0) {
                        parseData(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void parseData(String result) {
        Gson gson = new Gson();
        PassengersListBean passengersListBean = gson.fromJson(result, PassengersListBean.class);
        mPassengersList = passengersListBean.getData();
        mPassengersAdapter.setData(mPassengersList);
    }

    @OnClick({R.id.iv_back, R.id.tv_confirm, R.id.rl_add_passengers})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                getSelectPassengers();
                break;
            case R.id.rl_add_passengers:
                startActivityForResult(new Intent(mActivity, EditPassengersActivity.class), 201);
                break;
        }
    }

    private void getSelectPassengers() {
        listItemID.clear();
        for (int i = 0; i < mPassengersAdapter.mIsChecked.size(); i++) {
            if (mPassengersAdapter.mIsChecked.get(i)) {
                listItemID.add(i);
            }
        }
        toOrderPassengersLList.clear();
        for (int j = 0; j < listItemID.size(); j++) {
            if (mPassengersList.get(listItemID.get(j)).getBirthday().isEmpty() || mPassengersList.get(listItemID.get(j)).getPhone().isEmpty()) {
                Toast.makeText(mActivity,"请重新编辑所选乘机人",Toast.LENGTH_SHORT).show();
            } else {
                toOrderPassengersLList.add(mPassengersList.get(listItemID.get(j)));
            }
        }

        if (listItemID.size()==toOrderPassengersLList.size()){
            mIntent.putExtra("list", (Serializable) mPassengersAdapter.getSelectList());
            setResult(RESULT_OK, mIntent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 201) {
                //添加成功保存
                if (resultCode == EditPassengersActivity.SAVE) {
                    DataBean passengers = (DataBean) data.getSerializableExtra("data");
                    mPassengersList.add(passengers);
                    mPassengersAdapter.setData(mPassengersList); //本地更新乘机人列表
                    getDataFromServer();    //请求数据后再更新一次
                } else if (resultCode == EditPassengersActivity.EDIT) {
                    DataBean passengers = (DataBean) data.getSerializableExtra("data");
                    int position = data.getIntExtra("position", 0);
                    mPassengersList.set(position, passengers);
                    mPassengersAdapter.setData(mPassengersList); //本地更新乘机人列表
                    getDataFromServer();    //请求数据后再更新一次
                }
            }
        }
    }
}
