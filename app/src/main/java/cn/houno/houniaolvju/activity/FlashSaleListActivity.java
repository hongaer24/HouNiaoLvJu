package cn.houno.houniaolvju.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.FlashSaleListAdapter;
import cn.houno.houniaolvju.bean.FlashSaleListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：限时抢购列表
 * 创建人：qzc
 * 创建时间：2016/12/29 10:12
 * 修改人：qzc
 * 修改时间：2016/12/29 10:12
 * 修改备注：
 */
public class FlashSaleListActivity extends Activity {

    FlashSaleListActivity mActivity;
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.lv_flash_sale)
    ListView mLvFlashSale;
    @Bind(R.id.rfv_flash_sale)
    XRefreshView mRfvFlashSale;

    private FlashSaleListAdapter mFlashSaleListAdapter;
    private List<FlashSaleListBean.DataBean> flashSaleList = new ArrayList<>();
    private int page = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_flash_sale_list);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(FlashSaleListActivity.this, R.color.app_theme_green);
        mActivity = FlashSaleListActivity.this;
        initData();
        initEvent();
    }


    private void initData() {
        getDataFromServer();
    }


    private void initEvent() {
        mRfvFlashSale.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
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

        mLvFlashSale.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String flashSaleId = flashSaleList.get(position).getId();
                Intent intent = new Intent();
                intent.setClass(mActivity, FlashSaleDetailActivity.class);
                intent.putExtra("id", flashSaleId);
                startActivity(intent);
            }
        });
    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.FLASH_SALE_LIST);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 1) {
                        Toast.makeText(mActivity, "没有数据", Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        processData(result, false);
                    }
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
                mRfvFlashSale.stopRefresh();
            }
        });
    }


    private void getMoreDataFromServer() {
        RequestParams params = new RequestParams(Constants.FLASH_SALE_LIST);
        params.addBodyParameter("p", page + "");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 1) {
                        Toast.makeText(mActivity, "没有更多数据啦", Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        processData(result, true);
                    }
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
                mRfvFlashSale.stopLoadMore();
            }
        });
    }

    private void processData(String result, boolean isLoadMore) {
        Gson gson = new Gson();
        FlashSaleListBean flashSaleListBean = gson.fromJson(result, FlashSaleListBean.class);
        if (isLoadMore) {
            flashSaleList.addAll(flashSaleListBean.getData());
            page++;
        } else {
            flashSaleList = flashSaleListBean.getData();
            page = 1;
        }
        if (flashSaleList.size() >= 10) {
            mRfvFlashSale.setPullLoadEnable(true);
        }
        if (mFlashSaleListAdapter == null) {
            mFlashSaleListAdapter = new FlashSaleListAdapter(mActivity, flashSaleList);
            mLvFlashSale.setAdapter(mFlashSaleListAdapter);
        } else {
            mFlashSaleListAdapter.setData(flashSaleList);
        }
    }


    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
