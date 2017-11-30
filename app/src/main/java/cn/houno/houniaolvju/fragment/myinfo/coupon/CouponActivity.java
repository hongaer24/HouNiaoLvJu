package cn.houno.houniaolvju.fragment.myinfo.coupon;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.utils.CodeUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 优惠券
 * Created by Administrator on 2017/1/19.
 */

public class CouponActivity extends Activity {

    @Bind(R.id.tv_coupon_txt)
    TextView tvCouponTxt;
    @Bind(R.id.et_input)
    EditText etInput;
    @Bind(R.id.iv_code)
    ImageView ivCode;
    @Bind(R.id.tv_get_coupon)
    TextView tvGetCoupon;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_list)
    ImageView ivList;

    private CouponActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtils.setWindowStatusBarColor(CouponActivity.this, R.color.app_theme_green);
        initView();
        initData();
    }

    private void initView() {
    }

    private void initData() {
        mActivity = CouponActivity.this;
        changeCode();
    }

    private void changeCode() {
        Bitmap bitmap = CodeUtils.getInstance().createBitmap();
        ivCode.setImageBitmap(bitmap);
    }


    @OnClick({R.id.iv_back, R.id.iv_list,R.id.iv_code, R.id.tv_get_coupon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_list:
                startActivity(new Intent(mActivity,CouponListActivity.class));
                break;
            case R.id.iv_code:
                changeCode();
                break;
            case R.id.tv_get_coupon:
                String code = CodeUtils.getInstance().getCode();
                if (etInput.getText().toString().equalsIgnoreCase(code)) {
                    tvGetCoupon.setText("验证成功");
                } else {
                    tvGetCoupon.setText("验证失败");
                }
                break;
        }
    }

}
