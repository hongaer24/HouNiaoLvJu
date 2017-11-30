package cn.houno.houniaolvju.fragment.myinfo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import cn.houno.houniaolvju.activity.hotel.HotelDetailActivity;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.CollectionAdapter;
import cn.houno.houniaolvju.bean.CollectionBean;
import cn.houno.houniaolvju.bean.CollectionBean.DataEntity;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 作 者：陈亮
 * <p/>
 * 版本1.0
 * <p/>
 * 描述：
 * <p/>
 * 修订历史:
 * qzc  2017年1月10日16:18:04
 */
public class CollectionActivity extends Activity {

    private int page;

    private ImageView ivBack;
    private TextView tvNoCollection;
    private XRefreshView rfvCoolection;
    private ListView lvcollction;
    private CollectionAdapter collectionAdapter;
    private ArrayList<DataEntity> dataList;
    private String userid;
    private CollectionBean collectionBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_collection);
        StatusBarUtils.setWindowStatusBarColor(CollectionActivity.this, R.color.app_theme_green);
        initView();
        initData();

    }


    private void initData() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rfvCoolection.setPullLoadEnable(true);
        rfvCoolection.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
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


        lvcollction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String hid = collectionBean.getData().get(position).getInfo().getId();
                String rid = collectionBean.getData().get(position).getInfo().getRid();
                Intent intent = new Intent();
                intent.putExtra("hid", hid);
                intent.setClass(CollectionActivity.this, HotelDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_wdsc_back);
        rfvCoolection = (XRefreshView) findViewById(R.id.rfv_collection);
        lvcollction = (ListView) findViewById(R.id.lv_collection);
        tvNoCollection = (TextView) findViewById(R.id.tv_no_collection);
        getDataFromServer();
    }

    /**
     * 下拉刷新数据
     */
    public void getDataFromServer() {
        page = 1;
        userid = PrefUtils.getString(getApplicationContext(), "userid", "");
        RequestParams params = new RequestParams(Constants.COLLECTION_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("page", String.valueOf(page));
        params.addBodyParameter("pagesize", String.valueOf(10));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    int status = json.getInt("status");
                    if (status == 1) {
                        rfvCoolection.setVisibility(View.GONE);
                        tvNoCollection.setVisibility(View.VISIBLE);
                        Toast.makeText(CollectionActivity.this, "快去收藏吧", Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        parserData(result, false);
                        if (!rfvCoolection.getPullLoadEnable()) {
                            rfvCoolection.setPullLoadEnable(true);
                        }
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
                rfvCoolection.stopRefresh();
            }
        });
    }

    /**
     * 上拉加载数据
     */
    public void getMoreDataFromServer() {
        page++;
        RequestParams params = new RequestParams(Constants.COLLECTION_URL);
        params.addBodyParameter("userid", userid);
        params.addBodyParameter("page", String.valueOf(page));
        params.addBodyParameter("pagesize", String.valueOf(10));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    int status = json.getInt("status");
                    if (status == 1) {
                        rfvCoolection.setPullLoadEnable(false);
                        Toast.makeText(CollectionActivity.this, "没有更多收藏了", Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        parserData(result, true);
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
                rfvCoolection.stopLoadMore();
            }
        });
    }

    private void parserData(String result, boolean isMore) {
        Gson gson = new Gson();
        collectionBean = gson.fromJson(result, CollectionBean.class);
        if (!isMore) {
            dataList = collectionBean.getData();
        } else {
            ArrayList<DataEntity> moreData = collectionBean.getData();
            dataList.addAll(moreData);
        }
        if (collectionAdapter == null) {
            collectionAdapter = new CollectionAdapter(CollectionActivity.this, dataList);
            lvcollction.setAdapter(collectionAdapter);
        } else {
            collectionAdapter.setData(dataList);
        }
    }


}
