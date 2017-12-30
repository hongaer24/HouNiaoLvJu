package cn.houno.houniaolvju.activity.scenic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.bean.ScenicDetailBean;
import cn.houno.houniaolvju.view.GradationScrollView;

public class ScenicBookInfoActivity extends AppCompatActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.ly_top_bar)
    RelativeLayout lyTopBar;

   /* @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_loading)
    TextView tvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout llLoading;*/
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_book_info)
    TextView tvBookInfo;
    @Bind(R.id.tv_ticket_address)
    TextView tvTicketAddress;
    @Bind(R.id.tv_refund_rule)
    TextView tvRefundRule;
    @Bind(R.id.tv_others)
    TextView tvOthers;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    @Bind(R.id.sv_scenic)
    GradationScrollView svScenic;
    private Intent intent;
    private List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean> mticketlist;
    private int position;
    private String title;
    private String bookInfo;
    private String othersInfo;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideWindows();
        setContentView(R.layout.activity_scenic_book_info);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mticketlist = (List<ScenicDetailBean.DataBean.InfoBean.TicketlistBean>) intent.getSerializableExtra("book");
        position = intent.getIntExtra("position", 0);

        bookInfo=mticketlist.get(position).getTicketlistinfo().getBooknotice();
        othersInfo= mticketlist.get(position).getTicketlistinfo().getInfo();
        title=mticketlist.get(position).getTicketlistinfo().getProductname();
        address=mticketlist.get(position).getTicketlistinfo().getDrawaddress();

        tvOthers.setText(othersInfo);
        tvBookInfo.setText(bookInfo);
        tvTitle.setText(title);
        if(address.isEmpty()){
            tvTicketAddress.setText("凭短信数字码前往换票入园");

        }else{
            tvTicketAddress.setText(address);
        }

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
    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
