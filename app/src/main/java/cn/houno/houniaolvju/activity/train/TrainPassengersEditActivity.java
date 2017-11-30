package cn.houno.houniaolvju.activity.train;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

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
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

public class TrainPassengersEditActivity extends Activity {


    @Bind(R.id.tv_topbar_title)
    TextView tvTitle;

    @Bind(R.id.et_username)
    EditText etUsername;

    @Bind(R.id.et_certificate_num)
    EditText etCertNum;


    private String mUsername;
    private String mCertNum;


    private List<TrainPassengersBean> list;


    private String id;//所编辑乘车人的id；

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_edit_passengers);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(TrainPassengersEditActivity.this, R.color.app_theme_green);

        initView();
        initData();
        initEvent();
    }

    private void initView() {

    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getBooleanExtra("edit", false)) {
                tvTitle.setText("编辑乘车人");
                mUsername = intent.getStringExtra("name");
                if (!TextUtils.isEmpty(mUsername)) {
                    etUsername.setText(mUsername);
                }
                mCertNum = intent.getStringExtra("certNo");
                if (!TextUtils.isEmpty(mCertNum)) {
                    etCertNum.setText(mCertNum);
                }
                id=intent.getStringExtra("id");
            } else {
                tvTitle.setText("添加乘车人");
            }
        }
    }

    private void initEvent() {



    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.TRAIN_PASSENGERS_SELECT);
        params.addBodyParameter("userid", "357");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                parseData(result);
                            }
                        }).start();
                    } else {
                        Toast.makeText(TrainPassengersEditActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
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
            list = gson.fromJson(jsonStr, new TypeToken<List<TrainPassengersBean>>() {
            }.getType());
            Log.e("TAG", list.toString());
            Log.e("TAG", "解析json完毕");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "解析json出错");
        }
    }

    @OnClick({R.id.iv_prompt_name, R.id.iv_back, R.id.tv_save})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_prompt_name: {
                showDialog();
                break;
            }
            case R.id.iv_back: {
                finish();
                break;
            }

            case R.id.tv_save: {
                if(!InputCheckUtil.isValidName(etUsername.getText().toString())){
                    etUsername.requestFocus();
                    Toast.makeText(this,"名字不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!InputCheckUtil.verifyIdCard(etCertNum.getText().toString())){
                    etCertNum.requestFocus();
                    Toast.makeText(this,"无效的身份证号",Toast.LENGTH_SHORT).show();
                    return;
                }
                pushDataToServer();
                break;
            }
            default: {
                break;
            }
        }
    }

    private void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_name_format_illustrate,null);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                .setView(view);

        final AlertDialog dialog=builder.show();

        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void pushDataToServer() {
        RequestParams params;
        mUsername=etUsername.getText().toString();
        mCertNum=etCertNum.getText().toString();
        if(!TextUtils.isEmpty(id)){
            params = new RequestParams(Constants.TRAIN_PASSENGERS_EDIT);
            params.addBodyParameter("id",id);
        }else {
            params = new RequestParams(Constants.TRAIN_PASSENGERS_ADD);
        }
        params.addBodyParameter("userid", PrefUtils.getString(this, "userid", ""));
        params.addBodyParameter("info[passengersename]",mUsername);
        params.addBodyParameter("info[piaotype]", "1");
        params.addBodyParameter("info[piaotypename]", "成人票");
        params.addBodyParameter("info[passporttypeseid]", "1");
        params.addBodyParameter("info[passporttypeseidname]", "二代身份证");
        params.addBodyParameter("info[passportseno]", mCertNum);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    Toast.makeText(TrainPassengersEditActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
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
                finish();
                Log.e("TAG", "onFinished");
            }
        });
    }

}

