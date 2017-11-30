package cn.houno.houniaolvju.fragment.myinfo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.AboutWeBean;
import cn.houno.houniaolvju.global.Constants;
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
public class AboutUsActivity extends Activity {

    private ImageView ivweback;
    private WebView wbaboutwe;
    private String info="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //全屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//绑定竖屏
        setContentView(R.layout.activity_about);
        StatusBarUtils.setWindowStatusBarColor(AboutUsActivity.this, R.color.app_theme_green);
        initView();
        getDataFromServer();

        initData();

    }

    private void initData() {
        ivweback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        ivweback = (ImageView) findViewById(R.id.iv_gywm_back);
        wbaboutwe = (WebView) findViewById(R.id.wb_about_we);


    }

    public void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.ABOUTUS_URL);
        params.addBodyParameter("id", "4");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        info = obj.getJSONObject("data").getString("info");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                wbaboutwe.loadDataWithBaseURL(null, info, "text/html", "utf-8", null);

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
}
