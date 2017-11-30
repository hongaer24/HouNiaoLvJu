package cn.houno.houniaolvju.activity.flight;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.PassengersListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.NameEditText;

/**
 * 编辑乘机人
 * Created by qzc on 2017/2/17.
 */

public class EditPassengersActivity extends Activity {

    public static final int SAVE = 211;
    public static final int EDIT = 212;
    public static final int DELETE = 213;

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    //    @Bind(R.id.tv_delete)
//    TextView tvDelete;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.et_username)
    NameEditText etUsername;
    @Bind(R.id.iv_prompt_name)
    ImageView ivPromptName;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_certificate_type)
    TextView tvCertificateType;
    @Bind(R.id.tv_certificate)
    TextView tvCertificate;
    @Bind(R.id.tv_certificate_num)
    TextView tvCertificateNum;
    @Bind(R.id.et_certificate_num)
    EditText etCertificateNum;
    @Bind(R.id.tv_save)
    TextView tvSave;
    private EditPassengersActivity mActivity;
    private String userid;

    private static final String[] certificateStr = {"身份证", "护  照", "其  他"};
    private ArrayAdapter<String> sAdapter;
    private String mType;
    private PassengersListBean.DataBean mPassengers;

    private AlertDialog.Builder builder;    //信息提示

    private String mDialogTitle = "姓名填写说明";
    private String mDialogContent = "1.中文姓名：\n" +
            "（1）乘机人姓名需与登机证件的姓名一致。\n" +
            "（2）姓名中的生僻字或繁体字可使用中文，无需用拼音替代，建议您直接输入证件上的姓名。姓名中有特殊符号如“·”等，可不输入，例如“阿里木·库尔班”可直接输入“阿里木库尔班”。\n" +
            "2.英文姓名：\n" +
            "（1）请按照证件上的姓名顺序填写．姓与名中间加“／”，如“Smith/Black” ，不区分大小写\n" +
            "（2）英文姓名不超过26个字符，如姓名过长请使用缩写，乘客的姓氏不能缩写 ，名可以缩写";
    private Intent mIntent;
    private int editPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtils.setWindowStatusBarColor(EditPassengersActivity.this, R.color.app_theme_green);
        setContentView(R.layout.activity_flight_edit_passengers);
        ButterKnife.bind(this);
        mActivity = EditPassengersActivity.this;
        initData();
    }

    private void initData() {
        userid = PrefUtils.getString(mActivity,"userid","");
        mIntent = getIntent();
        mType = mIntent.getStringExtra("type");
        if (TextUtils.equals(mType, "edit")) {
            tvTopbarTitle.setText("编辑乘机人");
            mPassengers = (PassengersListBean.DataBean) mIntent.getSerializableExtra("data");
            editPosition = mIntent.getIntExtra("position", 0);
            etUsername.setText(mPassengers.getName());
            tvBirthday.setText(mPassengers.getBirthday());
            etPhone.setText(mPassengers.getPhone());
            etCertificateNum.setText(mPassengers.getIdentityno());
        }
        else {
            tvTopbarTitle.setText("添加乘机人");
        }

        initPromptDialog(); //初始化弹窗
    }

    @OnClick({R.id.iv_back, R.id.tv_save, R.id.iv_prompt_name,R.id.tv_birthday})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_prompt_name:
                builder.show();
                break;
            case R.id.tv_birthday:
                showBirthdayWindow();
                break;
            case R.id.tv_save:
                if (etUsername.getText().toString().isEmpty()){
                    Toast.makeText(mActivity,"姓名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tvBirthday.getText().toString().isEmpty()||tvBirthday.getText().toString().contains("选择")){
                    Toast.makeText(mActivity,"请选择出生日期",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etPhone.getText().toString().isEmpty()){
                    Toast.makeText(mActivity,"手机号码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                addPassengersToServer();
                break;
        }
    }

    private void savePassengersInfo() {
        if (mPassengers == null) {
            mPassengers = new PassengersListBean.DataBean();
        }
        mPassengers.setName(etUsername.getText().toString().trim());
        mPassengers.setIdentitytype("1");
        mPassengers.setIdentityno(etCertificateNum.getText().toString().trim());
        mIntent.putExtra("data", mPassengers);
        if (TextUtils.equals(mType, "edit")) {
            mIntent.putExtra("position", editPosition);
            setResult(EDIT, mIntent);
        }else {
            setResult(SAVE, mIntent);
        }


    }

    private void addPassengersToServer() {

        RequestParams params;
        if (TextUtils.equals(mType, "edit")) {
            params = new RequestParams(Constants.EDIT_PASSENGER);
            params.addBodyParameter("id", mPassengers.getId());
        } else {
            params = new RequestParams(Constants.ADD_PASSENGER);
            params.addBodyParameter("userid", userid);
        }
        params.addBodyParameter("info[name]", etUsername.getText().toString().trim());
        params.addBodyParameter("info[identityType]", "1");
        params.addBodyParameter("info[identityNo]", etCertificateNum.getText().toString().trim());
        params.addBodyParameter("info[birthday]", tvBirthday.getText().toString().trim());
        params.addBodyParameter("info[phone]", etPhone.getText().toString().trim());

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject object = new JSONObject(result);
                    Log.e("ZZZZZZZZZZZZZZZ",result);
                    int status = object.getInt("status");
                    if (status == 0) {
                        savePassengersInfo();
                        finish();
                    } else {
                        Toast.makeText(mActivity, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("RequestError:"+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /*
    * 初始化填写姓名提示
    * */
    private void initPromptDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle(mDialogTitle);
        builder.setMessage(mDialogContent);
        builder.setCancelable(true);

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
    }


    private void showBirthdayWindow() {
        Calendar cal = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePicker_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear += 1;
                //System.out.println(year + "," + monthOfYear + "," + dayOfMonth);
                tvBirthday.setText(year + "-" + DateUtil.addZero(monthOfYear) + "-" + DateUtil.addZero(dayOfMonth));
            }
        },  cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(DateUtil.dateToUnixTimestamp());
        datePickerDialog.getDatePicker().setMinDate(DateUtil.dateToUnixTimestamp("1900-01-01 00:00:01"));
        datePickerDialog.show();
    }


    /*
    * 删除乘机人
    * */
//    private void deletePassengers() {
//        CustomDialog.Builder deleteDialog = new CustomDialog.Builder(mActivity);
//        deleteDialog.setMessage("确定要删除该乘机人？");
//        deleteDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                setResult(DELETE, mIntent);
//                finish();
//            }
//        });
//
//        deleteDialog.setNegativeButton("取消",
//                new android.content.DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//        deleteDialog.create().show();
//    }
}
