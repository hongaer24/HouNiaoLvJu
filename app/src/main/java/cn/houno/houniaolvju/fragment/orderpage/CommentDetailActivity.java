package cn.houno.houniaolvju.fragment.orderpage;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.OkHttpClientManager;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerGridView;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 */
public class CommentDetailActivity extends Activity {

    private RatingBar ratingBars;
    private EditText etpl;
    //   private ImageView ivselector;
    private Button btnfb;
    //private Map<String, Bitmap> fileMap = new HashMap<>();
    private InnerGridView igvimg;
    // private CommentAdapter commentAdapter;
    private ImageView ivback;
    //    private AlertView mAlertView;
//    private ArrayList<String> imgPhotoPathList = new ArrayList<>();
//
//    private final int REQUEST_CODE_CAMERA = 1000;
//    private final int REQUEST_CODE_GALLERY = 1001;
    private float ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_comment_detail);
        StatusBarUtils.setWindowStatusBarColor(CommentDetailActivity.this, R.color.app_theme_green);

        initView();
        initData();

    }

    private void initData() {
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        ratingBars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBars.setRating(rating);
                //当前星级
                ratings = ratingBars.getRating();
            }
        });

        btnfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratings == 0) {
                    Toast.makeText(CommentDetailActivity.this, "请设置评星", Toast.LENGTH_SHORT).show();
                } else {
                    upDataToServer();

                }
            }
        });

    }

    private void upDataToServer() {
        String pjnr = etpl.getText().toString().trim();
        Intent intent = getIntent();
        String userid = PrefUtils.getString(CommentDetailActivity.this, "userid", "");
        String orderno = intent.getStringExtra("orderno");
        String type = intent.getStringExtra("type");


        Map<String, String> params = new HashMap<>();
        params.put("userid", userid);
        params.put("orderno", orderno);
        params.put("type", type);
        params.put("score", String.valueOf(ratings));
        params.put("contents", pjnr);

        System.out.println("userid:" + userid + ",orderno:" + orderno
                + ",type:" + type + ",score:" + String.valueOf(ratings) + ",contents:" + pjnr);

        OkHttpClientManager.postAsync(Constants.RELEASE_COMMENT_URL, params
                , null, new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case R.id.doSucceed:
                                String succeedResult = msg.obj.toString();
                                try {
                                    JSONObject obj = new JSONObject(succeedResult);
                                    Toast.makeText(CommentDetailActivity.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                setResult(Activity.RESULT_OK);
                                finish();
                                break;
                            case R.id.doFail:
                                String failResult = msg.obj.toString();
                                System.out.println(failResult);
                                break;
                        }
                    }
                }, R.id.doSucceed, R.id.doFail);


    }


    private void initView() {
        ivback = (ImageView) findViewById(R.id.iv_comment_back);
        ratingBars = (RatingBar) findViewById(R.id.rtb_scenic_detail);
        etpl = (EditText) findViewById(R.id.et_comment_order);

        btnfb = (Button) findViewById(R.id.btn_fabu);
        igvimg = (InnerGridView) findViewById(R.id.igv_img);


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
