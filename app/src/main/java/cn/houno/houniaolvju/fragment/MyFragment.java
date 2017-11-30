package cn.houno.houniaolvju.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.fragment.myinfo.AboutUsActivity;
import cn.houno.houniaolvju.fragment.myinfo.CollectionActivity;
import cn.houno.houniaolvju.fragment.myinfo.EditActivity;
import cn.houno.houniaolvju.fragment.myinfo.HeadImageActivity;
import cn.houno.houniaolvju.fragment.myinfo.LoginActivity;
import cn.houno.houniaolvju.fragment.myinfo.ModifyPasswordActivity;
import cn.houno.houniaolvju.fragment.myinfo.card.BindCardActivity;
import cn.houno.houniaolvju.fragment.myinfo.card.MemberCardActivity;
import cn.houno.houniaolvju.fragment.myinfo.coupon.CouponActivity;
import cn.houno.houniaolvju.fragment.myinfo.coupon.CouponListActivity;
import cn.houno.houniaolvju.fragment.myinfo.wallet.MyWalletActivity;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.view.CustomDialog;

/**
 * 我的
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {


    private static final int LOGIN = 104;

    private ScrollView svMy;
    private ImageView ivmytoggle;
    private SlidingMenu slidingMenu;
    private RadioButton rbHome;
    private RadioButton rbMy;
    private RadioButton rborder;
    private ImageView civheadview;
    private TextView tvye;
    private TextView tvsc;
    private TextView tvyhq;
    private TextView tvdd;
    private RelativeLayout rlorder;
    private RelativeLayout rlcollection;
    private RelativeLayout rlCard;
    private RelativeLayout rlMyBurse;
    private RelativeLayout rlpassword;
    private RelativeLayout rledit;
    private RelativeLayout rlabout;
    private RelativeLayout rlcontact;
    private Button btnzx;
    private LinearLayout llye;
    private LinearLayout llsc;
    private LinearLayout llyhq;
    private LinearLayout lldd;
    private TextView tvnick;
    private String img;
    private String nick;

    public static int HEADIMAGECODE = 300;

    public static int EDITIMAGECODE = 400;


    ImageOptions options;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        svMy = (ScrollView) view.findViewById(R.id.sv_my);
        ivmytoggle = (ImageView) view.findViewById(R.id.iv_my_toggle);
        civheadview = (ImageView) view.findViewById(R.id.civ_headview);
        tvnick = (TextView) view.findViewById(R.id.tv_my_nick);

        options = new ImageOptions.Builder()
                .setCircular(true).setImageScaleType(ImageView.ScaleType.FIT_XY)
                .build();

        tvye = (TextView) view.findViewById(R.id.tv_ye);
        tvsc = (TextView) view.findViewById(R.id.tv_sc);
        tvyhq = (TextView) view.findViewById(R.id.tv_yhq);
        tvdd = (TextView) view.findViewById(R.id.tv_dd);

        llye = (LinearLayout) view.findViewById(R.id.ll_ye);
        llsc = (LinearLayout) view.findViewById(R.id.ll_sc);
        llyhq = (LinearLayout) view.findViewById(R.id.ll_yhq);
        lldd = (LinearLayout) view.findViewById(R.id.ll_dd);

        rlorder = (RelativeLayout) view.findViewById(R.id.rl_my_order);
        rlcollection = (RelativeLayout) view.findViewById(R.id.rl_my_collection);
        rlCard = (RelativeLayout) view.findViewById(R.id.rl_my_vip);
        rlMyBurse = (RelativeLayout) view.findViewById(R.id.rl_my_burse);
        rlpassword = (RelativeLayout) view.findViewById(R.id.rl_my_password);
        rledit = (RelativeLayout) view.findViewById(R.id.rl_my_edit);
        rlabout = (RelativeLayout) view.findViewById(R.id.rl_my_about);
        rlcontact = (RelativeLayout) view.findViewById(R.id.rl_my_contact);
        btnzx = (Button) view.findViewById(R.id.btn_my_zx);

        motifyUI();


        nick = PrefUtils.getString(mActivity, "nick", "");
        img = PrefUtils.getString(mActivity, "headimg", "");

        tvnick.setText(nick);
        x.image().bind(civheadview, img, options);


    }

    private void motifyUI() {
        String userid = PrefUtils.getString(mActivity, "userid", "");
        RequestParams params = new RequestParams(Constants.PERSONAL_URL);
        params.addBodyParameter("userid", userid);
        System.out.println("userid="+userid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    String favorites = json.getJSONObject("data").getString("favorites");
                    String order = json.getJSONObject("data").getString("order");
                    Double b = Double.parseDouble(json.getJSONObject("data").getString("money"));
                    String yhq = json.getJSONObject("data").getString("coupon");
                    String money = b.toString();
                    tvsc.setText(favorites);
                    tvdd.setText(order);
                    tvye.setText(money);
                    tvyhq.setText(yhq);
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


    @Override
    public void initData() {
        //获取activity的侧边栏
        this.slidingMenu = mActivity.getLeftMenu();
        this.rbHome = mActivity.getRBtn();
        this.rbMy = mActivity.getRadioButton();
        this.rborder = mActivity.getRbOrder();
    }

    @Override
    protected void initEvent() {
        ivmytoggle.setOnClickListener(this);
        civheadview.setOnClickListener(this);
        llye.setOnClickListener(this);
        llsc.setOnClickListener(this);
        llyhq.setOnClickListener(this);
        lldd.setOnClickListener(this);
        rlorder.setOnClickListener(this);
        rlcollection.setOnClickListener(this);
        rlCard.setOnClickListener(this);
        rlMyBurse.setOnClickListener(this);
        rlpassword.setOnClickListener(this);
        rledit.setOnClickListener(this);
        rlabout.setOnClickListener(this);
        rlcontact.setOnClickListener(this);
        btnzx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.iv_my_toggle:
                slidingMenu.toggle();
                break;
            case R.id.civ_headview:
                intent.setClass(mActivity, HeadImageActivity.class);
                startActivityForResult(intent, HEADIMAGECODE);
                break;
            case R.id.ll_ye:
                intent.setClass(mActivity, MyWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_sc:
                intent.setClass(mActivity, CollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_yhq:
                intent.setClass(mActivity, CouponListActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_dd:
                rborder.setChecked(true);
                break;
            case R.id.rl_my_order:
                rborder.setChecked(true);
                break;
            case R.id.rl_my_collection:
                intent.setClass(mActivity, CollectionActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_my_vip:
                if (PrefUtils.getBoolean(mActivity, "isCard", false)) {
                    intent.setClass(mActivity, MemberCardActivity.class);
                } else {
                    intent.setClass(mActivity, BindCardActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.rl_my_burse:
                intent.setClass(mActivity, MyWalletActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_my_password:
                intent.setClass(mActivity, ModifyPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_my_edit:
                intent.setClass(mActivity, EditActivity.class);
                startActivityForResult(intent, EDITIMAGECODE);
                break;
            case R.id.rl_my_about:
                intent.setClass(mActivity, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_my_contact:
                CustomDialog.Builder callDialog = new CustomDialog.Builder(mActivity);
                callDialog.setMessage("是否拨打客服电话 0371-55328666");
                callDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4000301679"));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                callDialog.setNegativeButton("取消",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                callDialog.create().show();


                break;
            case R.id.btn_my_zx:
                CustomDialog.Builder exitDialog = new CustomDialog.Builder(mActivity);
                exitDialog.setMessage("是否确定退出当前账号");
                exitDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        civheadview.setImageResource(R.drawable.icon_app);
                        tvye.setText("0");
                        tvsc.setText("0");
                        tvdd.setText("0");
                        tvnick.setText("");
                        PrefUtils.setString(mActivity, "nick", "");
                        PrefUtils.setString(mActivity, "headimg", "");
                        PrefUtils.setString(mActivity, "userid", "");
                        PrefUtils.setString(mActivity, "mobile","");
                        PrefUtils.setBoolean(mActivity, "isLogined", false);
                        PrefUtils.setBoolean(mActivity, "isCard", false);
                        PrefUtils.setBoolean(mActivity, "settab", true);
                        PrefUtils.setString(mActivity, "userid", "");
                        Intent intt = new Intent();
                        intt.setClass(mActivity, LoginActivity.class);
                        startActivity(intt);
                        rbHome.setChecked(true);
                    }
                });

                exitDialog.setNegativeButton("取消",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                exitDialog.create().show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HEADIMAGECODE && resultCode == HeadImageActivity.HEADER_RESULT_CODE) {
            boolean isReset = data.getBooleanExtra("isReset", false);
            if (isReset) {
                String url = data.getStringExtra("url");
                System.out.println(url + "");
                x.image().bind(civheadview, url, options);
            }
        } else if (requestCode == EDITIMAGECODE && resultCode == 401) {
            String addurl = data.getStringExtra("addheadimg");
            x.image().bind(civheadview, addurl, options);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (PrefUtils.getBoolean(mActivity, "myfragmentreset", false)) {
            svMy.scrollTo(0, 0);
            svMy.smoothScrollTo(0, 0);
            PrefUtils.setBoolean(mActivity, "myfragmentreset", false);
        }
        String username = PrefUtils.getString(mActivity, "nick", "");
        tvnick.setText(username);
        x.image().bind(civheadview, PrefUtils.getString(mActivity, "headimg", ""), options);
        motifyUI();
    }
}
