package cn.houno.houniaolvju.activity.scenic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.GetScenicPassengerBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.SPDialogUtils;

public class PersonsEditActivity extends AppCompatActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    @Bind(R.id.tv_edit_name)
    TextView tvEditName;
    @Bind(R.id.et_edit_name)
    EditText etEditName;
    @Bind(R.id.tv_edit_ID)
    TextView tvEditID;
    @Bind(R.id.tv_ID)
    TextView tvID;
    @Bind(R.id.tv_edit_IDcard)
    TextView tvEditIDcard;
    @Bind(R.id.et_edit_IDcard)
    EditText etEditIDcard;
    @Bind(R.id.tv_jd_phone)
    TextView tvJdPhone;
    @Bind(R.id.et_jd_phone)
    EditText etJdPhone;
    @Bind(R.id.add_person_btn)
    Button addPersonBtn;
    private GetScenicPassengerBean.DataBean getScenicBean;
    private String userid;
    private PersonsEditActivity mActivity;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_edit);
        ButterKnife.bind(this);
        mActivity = PersonsEditActivity.this;
        initData();
    }

    public void initData() {

        if (getIntent() != null && getIntent().getSerializableExtra("consignee") != null) {
            getScenicBean = (GetScenicPassengerBean.DataBean) getIntent().getSerializableExtra("consignee");
        }
            if(getScenicBean==null){
                getScenicBean=new GetScenicPassengerBean.DataBean();
            }else {
                etEditName.setText(getScenicBean.getName());
                etEditIDcard.setText(getScenicBean.getIdentityno());
                etJdPhone.setText(getScenicBean.getPhone());
            }



        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        userid = PrefUtils.getString(mActivity, "userid", "");


    }
    private void AddScenicPassenger(GetScenicPassengerBean.DataBean getScenicBean) {
        //userid = PrefUtils.getString(mActivity, "userid", "");
       /* RequestParams params = new RequestParams(Constants.ADD_TOURIST_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("info[name]","吴三");
        params.addBodyParameter("info[phone]", "18502502014");
        params.addBodyParameter("info[identityNo]", "440606197001010898");*/
        RequestParams params = new RequestParams(Constants.ADD_TOURIST_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("info[name]",getScenicBean.getId());
        params.addBodyParameter("info[phone]", getScenicBean.getPhone());
        params.addBodyParameter("info[identityNo]", getScenicBean.getIdentityno());
        //params.addBodyParameter("userid", userid);
       // Log.i("999", "id===" + params);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    Log.i("999", "id===" + result);
                    if (status == 0) {
                        showToast(obj.getString("msg"));
                    } else {

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
    private void EditScenicPassenger(GetScenicPassengerBean.DataBean getScenicBean) {

        RequestParams params = new RequestParams(Constants.EDIT_TOURIST_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("id",getScenicBean.getId());
        params.addBodyParameter("info[name]",getScenicBean.getName());
        params.addBodyParameter("info[phone]", getScenicBean.getPhone());
        params.addBodyParameter("info[identityNo]", getScenicBean.getIdentityno());
        //params.addBodyParameter("userid", userid);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    Log.i("333", "id===" + result);
                    if (status == 0) {
                        showToast(obj.getString("msg"));
                    } else {

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

    private boolean checkData() {
        if (TextUtils.isEmpty(etEditName.getText().toString())) {
            showToast("请输入姓名");
            return false;
        }
        getScenicBean.setName(etEditName.getText().toString());

        if (TextUtils.isEmpty(etJdPhone.getText().toString())) {
            showToast("请输入手机号");
            return false;
        }
       getScenicBean.setPhone(etJdPhone.getText().toString());


        if (TextUtils.isEmpty(etEditIDcard.getText().toString())) {
            showToast("请输入身份证号");
            return false;
        }
        getScenicBean.setIdentityno(etEditIDcard.getText().toString());
        return true;
    }

    @OnClick({R.id.iv_back,R.id.add_person_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.add_person_btn:

                if (checkData()) {
                    if (getIntent() != null && getIntent().getSerializableExtra("consignee") != null) {
                        EditScenicPassenger(getScenicBean);

                    } else {
                        AddScenicPassenger(getScenicBean);
                    }
                    finish();
                    break;
                }
        }
    }
    public void showToast(String msg) {
        SPDialogUtils.showToast(this, msg);
    }
    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

}

