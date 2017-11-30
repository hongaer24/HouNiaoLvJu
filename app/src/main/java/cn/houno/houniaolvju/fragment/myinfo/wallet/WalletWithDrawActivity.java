package cn.houno.houniaolvju.fragment.myinfo.wallet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.BankTypeBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.ClearableEditText;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：余额提现
 * 创建人：qzc
 * 创建时间：2016/12/9 10:25
 * 修改人：qzc
 * 修改时间：2016/12/9 10:25
 * 修改备注：
 */
public class WalletWithDrawActivity extends Activity {

    private ImageView ivBack;
    private TextView tvSelectBank;
    private ClearableEditText cetCardAccount;   //卡号
    private ClearableEditText cetUserName;  //姓名
    private ClearableEditText cetPhone; //手机
    private ClearableEditText cetWithdrawals;   //金额
    private ClearableEditText cetRemark;    //备注
    private TextView tvWithdrawalNow;   //立即提现

    // private int dataType;   //数据类型
    private final static int GETBANKINFO = 301;   //银行信息
    private final static int WITHDRAWAL = 302;   //立即提现


    private AlertDialog.Builder builder;

    private ArrayList<BankTypeBean.DataBean> bankInfo = new ArrayList<>();
    private String[] bankItem;
    private String userid;
    private String bankType;
    private int selectNum = 0;
    private String mItem;
    private String mBankId;
    private int finalSelectNum = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_wallet_withdraw);
        StatusBarUtils.setWindowStatusBarColor(WalletWithDrawActivity.this, R.color.app_theme_green);
        initFindView();
        initData();
        initEvent();
    }


    private void initFindView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvSelectBank = (TextView) findViewById(R.id.tv_select_card);
        cetCardAccount = (ClearableEditText) findViewById(R.id.cet_card_account);
        cetUserName = (ClearableEditText) findViewById(R.id.cet_username);
        cetPhone = (ClearableEditText) findViewById(R.id.cet_card_phone);
        cetWithdrawals = (ClearableEditText) findViewById(R.id.cet_withdrawals);
        cetRemark = (ClearableEditText) findViewById(R.id.cet_remark);
        tvWithdrawalNow = (TextView) findViewById(R.id.tv_withdrawal_now);
    }

    private void initData() {
        userid = PrefUtils.getString(WalletWithDrawActivity.this, "userid", "");
        getDataFromServer(GETBANKINFO, Constants.GET_BANK_TYPE);
    }


    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSelectBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectCardDialog();
            }
        });

        tvWithdrawalNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });
    }

    private void checkData() {
        if (finalSelectNum == -1) {
            Toast.makeText(WalletWithDrawActivity.this, "请选择银行", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cetCardAccount.getText())) {
            Toast.makeText(WalletWithDrawActivity.this, "银行卡号不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cetUserName.getText())) {
            Toast.makeText(WalletWithDrawActivity.this, "姓名不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(cetPhone.getText())) {
            Toast.makeText(WalletWithDrawActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
        } else if (Double.parseDouble(cetWithdrawals.getText().toString().trim()) < 100) {
            Toast.makeText(WalletWithDrawActivity.this, "提现金额不能小于100", Toast.LENGTH_SHORT).show();
        } else {
            getDataFromServer(WITHDRAWAL, Constants.WALLET_WITH_DRAW);
        }
    }

    private void showSelectCardDialog() {
        builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("请选择银行"); //设置标题
        builder.setSingleChoiceItems(bankItem, selectNum, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectNum = which;
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finalSelectNum = selectNum;
                mItem = bankItem[finalSelectNum];
                mBankId = bankInfo.get(finalSelectNum).getId();
                tvSelectBank.setText(mItem);
                tvSelectBank.setTextColor(Color.parseColor("#505050"));
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void getDataFromServer(final int dataType, String url) {
        RequestParams params = new RequestParams(url);

        params.addBodyParameter("userid", userid);
        if (dataType == WITHDRAWAL) {
            params.addBodyParameter("info[bank_type]", mBankId);
            params.addBodyParameter("info[card_account]", cetCardAccount.getText().toString().trim());
            params.addBodyParameter("info[user_name]", cetUserName.getText().toString().trim());
            params.addBodyParameter("info[phone]", cetPhone.getText().toString().trim());
            params.addBodyParameter("info[withdrawals]", cetWithdrawals.getText().toString().trim());
            params.addBodyParameter("info[remark]", cetRemark.getText().toString().trim());
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //  System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        if (dataType == WITHDRAWAL) {
                            showSuccessToastDialog(obj.getString("msg"));
                        } else if (dataType == GETBANKINFO) {
                            parseData(dataType, result);
                        }
                    } else {
                        Toast.makeText(WalletWithDrawActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("WalletWithDrawActivity_ERROR:" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void showSuccessToastDialog(String msg) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.view_dialog_textview,
                (ViewGroup) findViewById(R.id.dialog));
        ((TextView) layout.findViewById(R.id.tv)).setText(msg);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("提现成功").setView(layout);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                setResult(201);
                finish();
            }
        });
        builder.show();
    }

    private void parseData(int dataType, String result) {
        Gson gson = new Gson();
        if (dataType == GETBANKINFO) {
            BankTypeBean bankTypeBean = gson.fromJson(result, BankTypeBean.class);
            int size = bankTypeBean.getData().size();
            bankInfo = bankTypeBean.getData();
            bankItem = new String[size];
            for (int i = 0; i < size; i++) {
                bankItem[i] = bankTypeBean.getData().get(i).getName();
            }
        } else if (dataType == WITHDRAWAL) {

        }
    }
}
