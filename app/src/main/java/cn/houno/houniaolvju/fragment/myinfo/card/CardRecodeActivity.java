package cn.houno.houniaolvju.fragment.myinfo.card;

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
import cn.houno.houniaolvju.adapter.ConsumeListAdapter;
import cn.houno.houniaolvju.adapter.RechargeListAdapter;
import cn.houno.houniaolvju.bean.CardAndWalletRechargeListBean;
import cn.houno.houniaolvju.bean.ConsumeListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：卡的记录
 * 创建人：qzc
 * 创建时间：2016/11/18 10:41
 * 修改人：qzc
 * 修改时间：2016/11/18 10:41
 * 修改备注：
 */
public class CardRecodeActivity extends Activity {

    private ImageView ivBack;
    private TextView tvTitle;
    private XRefreshView rfvCardRecode;
    private ListView lvCardRecode;
    private String from;
    private String userid;
    private String url;

    private int page = 2;

    private ArrayList<CardAndWalletRechargeListBean.DataBean> rechargeList;
    private RechargeListAdapter rechargeAdapter;

    private ArrayList<ConsumeListBean.DataBean> consumeList;
    private ConsumeListAdapter consumeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_card_recode);
        StatusBarUtils.setWindowStatusBarColor(CardRecodeActivity.this, R.color.app_theme_green);
        initFindViewById();
        initData();
        initEvent();
    }

    private void initFindViewById() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_topbar_title);
        rfvCardRecode = (XRefreshView) findViewById(R.id.rfv_card_recode);
        lvCardRecode = (ListView) findViewById(R.id.lv_card_recode);
    }


    private void initData() {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        if ("recharge".equals(from)) {
            tvTitle.setText("充值记录");
            url = Constants.CARD_RECHARGE_RECORD;
            rechargeList = new ArrayList<>();
            rechargeAdapter = new RechargeListAdapter(CardRecodeActivity.this, rechargeList);
            lvCardRecode.setAdapter(rechargeAdapter);
        } else if ("consume".equals(from)) {
            tvTitle.setText("消费记录");
            url = Constants.CARD_CONSUME_RECORD;
            consumeList = new ArrayList<>();
            consumeAdapter = new ConsumeListAdapter(CardRecodeActivity.this, consumeList);
            lvCardRecode.setAdapter(consumeAdapter);
        }
        userid = PrefUtils.getString(CardRecodeActivity.this, "userid", "");
        getDataFromServer();
    }


    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rfvCardRecode.setPullLoadEnable(true);
        rfvCardRecode.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
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


        lvCardRecode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(CardRecodeActivity.this, RecodeDetailActivity.class);
                Bundle bundle = new Bundle();
                if ("recharge".equals(from)) {
                    bundle.putString("from", "recharge");
                    bundle.putString("tradeno", rechargeList.get(position).getTrade_no());
                    bundle.putString("time", rechargeList.get(position).getAdd_time());
                    bundle.putString("type", rechargeList.get(position).getPaytype() + "充值");
                    bundle.putString("moneylast", rechargeList.get(position).getLastmoney());
                    bundle.putString("moneychange", rechargeList.get(position).getMoney());
                } else if ("consume".equals(from)) {
                    bundle.putString("from", "consume");
                    bundle.putString("tradeno", consumeList.get(position).getL_id());
                    bundle.putString("time", consumeList.get(position).getL_createdate());
                    bundle.putString("type", consumeList.get(position).getL_consumeproject());
                    bundle.putString("moneylast", consumeList.get(position).getL_moneyin());
                    bundle.putString("moneychange", consumeList.get(position).getL_moneyout());
                }
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void getDataFromServer() {

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("userid", userid);
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
                        rfvCardRecode.setPullLoadEnable(false);
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
                rfvCardRecode.stopRefresh();
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
                        rfvCardRecode.setPullLoadEnable(false);
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
                rfvCardRecode.stopLoadMore();

            }
        });
    }

    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();
        CardAndWalletRechargeListBean recordListBean;
        ConsumeListBean consumeListBean;
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
                rechargeAdapter = new RechargeListAdapter(CardRecodeActivity.this, rechargeList);
            } else {
                rechargeAdapter.setData(rechargeList);
            }

        } else if ("consume".equals(from)) {
            consumeListBean = gson.fromJson(result, ConsumeListBean.class);
            if (isMore) {
                //上拉加载
                if (consumeListBean != null) {
                    consumeList.addAll(consumeListBean.getData());
                    page++;
                }
            } else {
                //下拉刷新
                if (consumeList.size() != 0) {
                    consumeList.clear();
                }
                if (consumeListBean != null) {
                    consumeList = consumeListBean.getData();
                }
            }
            if (consumeAdapter == null) {
                consumeAdapter = new ConsumeListAdapter(CardRecodeActivity.this, consumeList);
            } else {
                consumeAdapter.setData(consumeList);
            }

        }


    }


}
