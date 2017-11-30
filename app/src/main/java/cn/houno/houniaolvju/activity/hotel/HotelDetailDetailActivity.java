package cn.houno.houniaolvju.activity.hotel;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：Houniaolvju
 * 类描述：
 * 创建人：qzc
 * 创建时间：2016/12/26 11:36
 * 修改人：qzc
 * 修改时间：2016/12/26 11:36
 * 修改备注：
 */
public class HotelDetailDetailActivity extends Activity {
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.tv_hotel_title)
    TextView mTvHotelTitle;
    @Bind(R.id.tv_hotel_phone)
    TextView mTvHotelPhone;
    @Bind(R.id.tv_hotel_address)
    TextView mTvHotelAddress;
    @Bind(R.id.tv_hotel_peitao)
    TextView mTvHotelPeitao;
    @Bind(R.id.tv_hotel_info)
    TextView mTvHotelInfo;
    @Bind(R.id.tv_hotel_around)
    TextView mTvHotelAround;
    private HotelDetailDetailActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_hotel_detail_detail);
        ButterKnife.bind(this);
        mActivity = HotelDetailDetailActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mTvHotelTitle.setText(intent.getStringExtra("title"));
        mTvHotelPhone.setText(intent.getStringExtra("phone"));
        mTvHotelAddress.setText(intent.getStringExtra("address"));
        mTvHotelPeitao.setText(intent.getStringExtra("peitao"));
        mTvHotelInfo.setText(Html.fromHtml(intent.getStringExtra("info")));
        mTvHotelAround.setText(Html.fromHtml(intent.getStringExtra("around")));
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
