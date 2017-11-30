package cn.houno.houniaolvju.fragment.myinfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class EditActivity extends Activity implements View.OnClickListener{

    private ImageView ivback;
    private RelativeLayout rlhead;
    private ImageView civheadimg;
    private RelativeLayout rlnick;
    private TextView tvnick;
    private RelativeLayout rlphone;
    private TextView tvphone;
    private RelativeLayout rlsex;
    private TextView tvsex;
    private RelativeLayout rlcsrq;
    private TextView tvcsrq;
    private RelativeLayout rlemail;
    private TextView tvemail;
    private RelativeLayout rlqq;
    private TextView tvqq;
    private String userid;
    private String birthday;
    private TimePickerView tptime;
    private String birth;

    private AlertDialog.Builder builder;
    private String nick;
    private String nickname;

    private String sex;
    private String sexnn;
    private String email;
    private String emails;
    private String qie;
    private String qq;


    ImageOptions options;
    private String addheadimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);
        StatusBarUtils.setWindowStatusBarColor(EditActivity.this, R.color.app_theme_green);
        initView();
        initData();

        getPersonalData();

    }

    private void getPersonalData() {
        RequestParams rparams = new RequestParams(Constants.GETPERSONALDATA_URL);
        rparams.addBodyParameter("userid", userid);
        x.http().post(rparams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                parseData(result);
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

    private void initData() {
        options=new ImageOptions.Builder()
                .setCircular(true)
                .build();
        ivback.setOnClickListener(this);
        rlhead.setOnClickListener(this);
        rlnick.setOnClickListener(this);
        rlphone.setOnClickListener(this);
        rlsex.setOnClickListener(this);
        rlcsrq.setOnClickListener(this);
        rlemail.setOnClickListener(this);
        rlqq.setOnClickListener(this);

    }

    private void initView() {
        ivback = (ImageView) findViewById(R.id.iv_bjzl_back);
        //头像
        rlhead = (RelativeLayout) findViewById(R.id.rl_bjzl_head);
        civheadimg = (ImageView) findViewById(R.id.civ_head_img);
        //昵称
        rlnick = (RelativeLayout) findViewById(R.id.rl_bjzl_nick);
        tvnick = (TextView) findViewById(R.id.tv_bjzl_nick);
        //手机号
        rlphone = (RelativeLayout) findViewById(R.id.rl_bjzl_phone);
        tvphone = (TextView) findViewById(R.id.tv_bjzl_phone);
        //性别
        rlsex = (RelativeLayout) findViewById(R.id.rl_bjzl_sex);
        tvsex = (TextView) findViewById(R.id.tv_bjzl_sex);
        //出生日期
        rlcsrq = (RelativeLayout) findViewById(R.id.rl_bjzl_csrq);
        tvcsrq = (TextView) findViewById(R.id.tv_bjrq_csrq);
        //邮箱
        rlemail = (RelativeLayout) findViewById(R.id.rl_bjzl_email);
        tvemail = (TextView) findViewById(R.id.tv_bjzl_email);
        //qq
        rlqq = (RelativeLayout) findViewById(R.id.rl_bjzl_qq);
        tvqq = (TextView) findViewById(R.id.tv_bjzl_qq);

        userid = PrefUtils.getString(EditActivity.this, "userid", "");
        //时间选择器
        tptime = new TimePickerView(EditActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
        //控制时间范围
        tptime.setRange(1900, 2100);

        tptime.setTime(new Date());
        tptime.setCyclic(false);
        tptime.setCancelable(true);

        //时间选择后回调
        tptime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                tvcsrq.setText(getTime(date));
                birth = "info[birthday]";
                birthday = tvcsrq.getText().toString().trim();
                sendHttpRequest(birth, birthday);
            }
        });

    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_bjzl_back:
                Intent hintent = new Intent();
                hintent.putExtra("addheadimg", addheadimg);
                EditActivity.this.setResult(401, hintent);
                finish();
                break;
            case R.id.rl_bjzl_head:
                Intent intent = new Intent();
                intent.setClass(EditActivity.this, HeadImageActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_bjzl_nick:
                final EditText etnc = new EditText(this);
                builder = new AlertDialog.Builder(this);
                builder.setTitle("请输入").setView(etnc).setNegativeButton("取消", null);
                builder.setPositiveButton("确定保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ettvnc = etnc.getText().toString();
                        tvnick.setText(ettvnc);
                        nick = "base[username]";
                        nickname = tvnick.getText().toString().trim();
                        PrefUtils.setString(EditActivity.this, "nick", nickname);
                        sendHttpRequest(nick, nickname);
                    }
                });
                builder.show();
                break;
            case R.id.rl_bjzl_phone:

                break;
            case R.id.rl_bjzl_sex:
                final String items[] = {"男", "女"};
                sex = "info[sex]";
                sexnn = items[0];
                String selected = tvsex.getText().toString().trim();
                int selectNum;
                if ("男".equals(selected)) {
                    selectNum = 0;
                } else {
                    selectNum = 1;
                }
                builder = new AlertDialog.Builder(this);  //先得到构造器
                builder.setTitle("请选择"); //设置标题
                builder.setSingleChoiceItems(items, selectNum, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = items[which];
                        tvsex.setText(item);
                        sexnn = item;
                    }
                });
                builder.setPositiveButton("确定保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendHttpRequest(sex, sexnn);
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.rl_bjzl_csrq:
                tptime.show();
                break;
            case R.id.rl_bjzl_email:
                final EditText etyx = new EditText(this);
                builder = new AlertDialog.Builder(this);
                builder.setTitle("请输入").setView(etyx).setNegativeButton("取消", null);
                builder.setPositiveButton("确定保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ettvyx = etyx.getText().toString();
                        tvemail.setText(ettvyx);
                        email = "base[email]";
                        emails = tvemail.getText().toString().trim();
                        sendHttpRequest(email, emails);
                    }
                });
                builder.show();
                break;
            case R.id.rl_bjzl_qq:
                final EditText etqq = new EditText(this);
                builder = new AlertDialog.Builder(this);
                builder.setTitle("请输入").setView(etqq).setNegativeButton("取消", null);
                builder.setPositiveButton("确定保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ettvqq = etqq.getText().toString();
                        tvqq.setText(ettvqq);
                        qie = "info[qq]";
                        qq = tvqq.getText().toString().trim();
                        sendHttpRequest(qie, qq);
                    }
                });
                builder.show();
                break;
        }
    }

    private void sendHttpRequest(String key, String value) {
        RequestParams params = new RequestParams(Constants.SETPERSONAL_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter(key, value);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(tptime.isShowing()){
                tptime.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    private boolean isreset = false;

    private void parseData(String result) {
        try {
            JSONObject json = new JSONObject(result);
            String headimg = json.getJSONObject("data").getString("head_img");
            addheadimg =  headimg;
            String username = json.getJSONObject("data").getString("username");
            String phone = json.getJSONObject("data").getString("mobile");
            String sex = json.getJSONObject("data").getString("sex");
            String csrq = json.getJSONObject("data").getString("birthday");
            String yxqz = json.getJSONObject("data").getString("email");
            String qe = json.getJSONObject("data").getString("qq");

            tvnick.setText(username);
            tvphone.setText(phone);
            tvsex.setText(sex);
            tvcsrq.setText(csrq);
            tvemail.setText(yxqz);
            tvqq.setText(qe);
            x.image().bind(civheadimg, addheadimg, options);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPersonalData();
    }
}
