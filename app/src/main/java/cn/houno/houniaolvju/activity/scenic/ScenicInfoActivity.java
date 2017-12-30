package cn.houno.houniaolvju.activity.scenic;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import cn.houno.houniaolvju.bean.ScenicDetailBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.GradationScrollView;

public class ScenicInfoActivity extends AppCompatActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;
    /*  @Bind(R.id.tv_open_time)
      TextView tvOpenTime;
      @Bind(R.id.tv_special)
      TextView tvSpecial;*/
   /* @Bind(R.id.tv_book_info)
    TextView tvBookInfo;*/
    @Bind(R.id.tv_notice)
    TextView tvNotice;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;
    @Bind(R.id.sv_scenic)
    GradationScrollView svScenic;
    @Bind(R.id.wv_open_time)
    WebView wvOpenTime;
    @Bind(R.id.wv_special)
    WebView wvSpecial;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.tv_book_info)
    TextView tvBookInfo;
    private String id;
    private String userid;
    private ScenicInfoActivity mActivity;
    private List<ScenicDetailBean.DataBean.InfoBean.BooknoticeBean> mTicketList;
    private String opentime;
    private String special;
    private String notice;
    private String info;
    private int RESULT_CODE = 661;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideWindows();
        setContentView(R.layout.activity_scenic_info);
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        ButterKnife.bind(this);
        mActivity = ScenicInfoActivity.this;
        initData();
    }
    private void hideWindows(){
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }
    private void initData() {
        id = getIntent().getStringExtra("id");
        Log.i("00", "id===" + id);

        getDataFromServer();
    }

    private void getDataFromServer() {
        // userid = PrefUtils.getString(mActivity, "userid", "");
        RequestParams params = new RequestParams(Constants.SCENIC_DETAIL);
        // params.addBodyParameter("userid", userid);
        params.addBodyParameter("id", id);
        Log.i("99", "id===" + id);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {

                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        Log.i("99", "id===" + result);
                        parseData(result);
                        llLoading.setVisibility(View.GONE);
                        svScenic.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                pbLoading.setVisibility(View.GONE);
                tvLoading.setText("加载失败");
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
        ScenicDetailBean scenicDetailBean = gson.fromJson(result, ScenicDetailBean.class);
        mTicketList = scenicDetailBean.getData().getInfo().getBooknotice();
        opentime = mTicketList.get(0).getValue();
        wvOpenTime.loadDataWithBaseURL(null, opentime, "text/html", "utf-8", null);
        // tvOpenTime.setText(opentime);
        special = mTicketList.get(4).getValue();
        wvSpecial.loadDataWithBaseURL(null, special, "text/html", "utf-8", null);
        // tvSpecial.setText(special);
        notice = mTicketList.get(6).getValue();
        tvBookInfo.setText(notice);
        info = mTicketList.get(7).getValue();
        tvNotice.setText(info);

    }

    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
