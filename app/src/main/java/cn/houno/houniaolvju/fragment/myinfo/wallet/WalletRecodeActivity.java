package cn.houno.houniaolvju.fragment.myinfo.wallet;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.RechargeListAdapter;
import cn.houno.houniaolvju.adapter.WalletBackListAdapter;
import cn.houno.houniaolvju.adapter.WalletDrawalsAdapter;
import cn.houno.houniaolvju.bean.CardAndWalletRechargeListBean;
import cn.houno.houniaolvju.bean.WalletBackRecordBean;
import cn.houno.houniaolvju.bean.WalletDrawRechargeBean;
import cn.houno.houniaolvju.fragment.myinfo.card.RecodeDetailActivity;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：钱包充值记录
 * 创建人：qzc
 * 创建时间：2016/12/07 18:41
 * 修改人：qzc
 * 修改时间：2016/12/07 18:41
 * 修改备注：
 */
public class WalletRecodeActivity extends Activity {

    private ImageView ivBack;
    private TextView tvTitle;
    private XRefreshView rfvWalletRecode;
    private ListView lvWalletRecode;
    private String from;
    private String userid;
    private String url;

    private int page;

    private ArrayList<CardAndWalletRechargeListBean.DataBean> rechargeList;
    private RechargeListAdapter rechargeAdapter;

    private ArrayList<WalletBackRecordBean.DataBean> backList;
    private WalletBackListAdapter backAdapter;

    private ArrayList<WalletDrawRechargeBean.DataBean> drawalsList;
    private WalletDrawalsAdapter drawalsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_card_recode);
        StatusBarUtils.setWindowStatusBarColor(WalletRecodeActivity.this, R.color.app_theme_green);
        initFindViewById();
        initData();
        initEvent();
    }

    private void initFindViewById() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_topbar_title);
        rfvWalletRecode = (XRefreshView) findViewById(R.id.rfv_card_recode);
        lvWalletRecode = (ListView) findViewById(R.id.lv_card_recode);
    }


    private void initData() {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        userid = PrefUtils.getString(WalletRecodeActivity.this, "userid", "");
        if ("recharge".equals(from)) {
            tvTitle.setText("充值记录");
            url = Constants.WALLET_RECHARGE_RECORD;
            rechargeList = new ArrayList<>();
            rechargeAdapter = new RechargeListAdapter(WalletRecodeActivity.this, rechargeList);
            lvWalletRecode.setAdapter(rechargeAdapter);
        } else if ("back".equals(from)) {
            tvTitle.setText("返现记录");
            url = Constants.WALLET_BACK_RECORD;
            backList = new ArrayList<>();
            backAdapter = new WalletBackListAdapter(WalletRecodeActivity.this, backList);
            lvWalletRecode.setAdapter(backAdapter);
        } else if ("drawals".equals(from)) {
            tvTitle.setText("提现记录");
            url = Constants.WALLET_DRAW_RECORD;
            drawalsList = new ArrayList<>();
            drawalsAdapter = new WalletDrawalsAdapter(WalletRecodeActivity.this, drawalsList);
            lvWalletRecode.setAdapter(drawalsAdapter);
        }
        getDataFromServer();
    }


    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rfvWalletRecode.setPullLoadEnable(true);
        rfvWalletRecode.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                getMoreDataFromServer();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });


        lvWalletRecode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                if ("recharge".equals(from)) {
                    intent.setClass(WalletRecodeActivity.this, RecodeDetailActivity.class);
                    bundle.putString("from", "recharge");
                    bundle.putString("tradeno", rechargeList.get(position).getTrade_no());
                    bundle.putString("time", rechargeList.get(position).getAdd_time());
                    bundle.putString("type", rechargeList.get(position).getPaytype() + "充值");
                    bundle.putString("moneylast", rechargeList.get(position).getLastmoney());
                    bundle.putString("moneychange", rechargeList.get(position).getMoney());
                } else if ("back".equals(from)) {
                    intent.setClass(WalletRecodeActivity.this, WalletBackDetailActivity.class);
                    bundle.putString("username", backList.get(position).getUsername());
                    bundle.putString("preamount", Double.valueOf(backList.get(position).getPre_amount()) + "");//原有金额
                    bundle.putString("time", backList.get(position).getAdd_time()); //返现日期
                    bundle.putString("type", backList.get(position).getAmout_type()); //返现类型
                    bundle.putString("amount", backList.get(position).getAmount());   //返现金额
                    bundle.putString("lastmoney", Double.valueOf(backList.get(position).getLast_amount()) + "");    //现有金额
                } else if ("drawals".equals(from)) {
                    intent.setClass(WalletRecodeActivity.this, WalletDrawalsDetailActivity.class);
                    bundle.putString("drawtype", drawalsList.get(position).getDraw_type());  //提现方式
                    bundle.putString("time", DateUtil.timeStamp2Date(drawalsList.get(position).getApply_time(), null)); //提现日期
                    bundle.putString("type", drawalsList.get(position).getAmout_type()); //提现类型
                    bundle.putString("amount", drawalsList.get(position).getWithdrawals());   //提现金额
                    bundle.putString("lastmoney", Double.valueOf(drawalsList.get(position).getBalance()) + "");    //现有金额
                }
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void getDataFromServer() {

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userid", userid);
       // params.addBodyParameter("p", String.valueOf(page));
       // params.addBodyParameter("pagesize", "10");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println(result);
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, false);
                        page = 2;
                    } else {
                        rfvWalletRecode.setPullLoadEnable(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("error," + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

                rfvWalletRecode.stopRefresh();

            }
        });
    }

    private void getMoreDataFromServer() {
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("p", page+"");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, true);
                    } else {
                        rfvWalletRecode.setPullLoadEnable(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("error," + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

                rfvWalletRecode.stopLoadMore();


            }
        });
    }

    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();
        CardAndWalletRechargeListBean recordListBean;
        WalletBackRecordBean backListBean;
        WalletDrawRechargeBean drawListBean;
        //充值
        if ("recharge".equals(from)) {
            recordListBean = gson.fromJson(result, CardAndWalletRechargeListBean.class);
            if (isMore) {
                //上拉加载
                if (recordListBean != null) {
                    rechargeList.addAll(recordListBean.getData());
                    page++;
                }
            } else {
                //下拉刷新
                if (rechargeList.size() != 0) {
                    rechargeList.clear();
                }
                if (recordListBean != null) {
                    rechargeList = recordListBean.getData();
                }
            }
            if (rechargeAdapter == null) {
                rechargeAdapter = new RechargeListAdapter(WalletRecodeActivity.this, rechargeList);
            } else {
                rechargeAdapter.setData(rechargeList);
            }

        }

        //返现
        else if ("back".equals(from)) {
            backListBean = gson.fromJson(result, WalletBackRecordBean.class);

            if (isMore) {
                //上拉加载
                if (backListBean != null) {
                    backList.addAll(backListBean.getData());
                    page++;
                }
            } else {
                //下拉刷新
                if (backList.size() != 0) {
                    backList.clear();
                }
                if (backListBean != null) {
                    backList = backListBean.getData();
                }
            }
            if (backAdapter == null) {
                backAdapter = new WalletBackListAdapter(WalletRecodeActivity.this, backList);
            } else {
                backAdapter.setData(backList);
            }
        }

        //提现
        else if ("drawals".equals(from)) {
            drawListBean = gson.fromJson(result, WalletDrawRechargeBean.class);

            if (isMore) {
                //上拉加载
                if (drawListBean != null) {
                    drawalsList.addAll(drawListBean.getData());
                }
            } else {
                //下拉刷新
                if (drawalsList.size() != 0) {
                    drawalsList.clear();
                }
                if (drawListBean != null) {
                    drawalsList = drawListBean.getData();
                }
            }
            if (drawalsAdapter == null) {
                drawalsAdapter = new WalletDrawalsAdapter(WalletRecodeActivity.this, drawalsList);
            } else {
                drawalsAdapter.setData(drawalsList);
            }
        }


    }


}
