package cn.houno.houniaolvju.activity.train;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedHashTreeMap;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;


public class TrainCitySelectActivity extends Activity implements HotCityAdapter.OnItemClickListener, ZiMuAdapter.OnItemClickListener, TrainStationAdapter.OnItemClickListener {

    @Bind(R.id.rv_hot_city)
    RecyclerView rvHotCity;

    @Bind(R.id.rv_pinyin)
    RecyclerView rvPinyin;

    @Bind(R.id.rv_detail)
    RecyclerView rvDetail;

    @Bind(R.id.tv_text)
    TextView tvText;

    @Bind(R.id.ll_loading)
    LinearLayout llLoading;

    @Bind(R.id.ll_content)
    LinearLayout llContent;

    @Bind(R.id.search)
    AutoCompleteTextView autoTextView;

    public static final int RESULT_CODE = 4444;

    List<TrainCityBean> hotCity;
    List<String> allStationName=new ArrayList<>();
    Map<String, List<TrainCityBean>> map=new LinkedHashMap<>();
    Map<String, TrainCityBean> searchMap=new HashMap<>();
    MyHandler myHandler;

    private HotCityAdapter hotCityAdapter;

    private ZiMuAdapter ziMuAdapter;

    private TrainStationAdapter trainStationAdapter;

    private SearchAdapter<String> searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_city_select);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(TrainCitySelectActivity.this, R.color.app_theme_green);
        intView();
        initEvent();
        initData();
    }


    private void initEvent() {
        hotCityAdapter.setOnItemClickListener(this);
        ziMuAdapter.setOnItemClickListener(this);
        trainStationAdapter.setOnItemClickListener(this);
        autoTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TrainCityBean bean = searchMap.get(((TextView) view).getText().toString());
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("city", bean);
                intent.putExtras(bundle);
                setResult(RESULT_CODE, intent);
                finish();
            }
        });
    }


    private void initData() {
        map=PrefUtils.getObject(this,"othercity",null);
        hotCity=PrefUtils.getObject(this,"hotcity",null);
        if((map!=null&&map.size()>0) && (hotCity!=null && hotCity.size()>0)){
            Log.e("smq","从缓存读取");
            addDataToAdapter();
            autoTextView.setAdapter(searchAdapter);
            showView();
        }else{
            Log.e("smq","从网络读取");
            getDataFromServer();
        }
    }


    private void intView() {
        myHandler = new MyHandler(this);

        hotCityAdapter = new HotCityAdapter(this);
        rvHotCity.setAdapter(hotCityAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvHotCity.setLayoutManager(gridLayoutManager);
        rvHotCity.addItemDecoration(new DividerItemDecoration(this));


        ziMuAdapter = new ZiMuAdapter(this);
        rvPinyin.setAdapter(ziMuAdapter);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 8);
        rvPinyin.setLayoutManager(gridLayoutManager1);
        rvPinyin.addItemDecoration(new EmptyItemDecoration(4, 4, 4, 4));


        trainStationAdapter = new TrainStationAdapter(this);
        rvDetail.setAdapter(trainStationAdapter);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 3);
        rvDetail.setLayoutManager(gridLayoutManager2);
        rvDetail.addItemDecoration(new DividerItemDecoration(this));

    }

    private void showView() {
        llLoading.setVisibility(View.GONE);
        llContent.setVisibility(View.VISIBLE);
    }


    @Override
    public void omItemClick(ZiMuAdapter.ViewHolder viewHolder, View view, int pos) {
        ziMuAdapter.setSelectItemId(pos);
        String index = ((TextView) view).getText().toString();
        tvText.setText(index);
        trainStationAdapter.fillData(map.get(index), index);
    }

    @Override
    public void omItemClick(HotCityAdapter.ViewHolder viewHolder, View view, int pos) {
        view.setBackgroundResource(R.color.app_theme_green);
        Intent intent = new Intent(this, TrainInquiryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("city", hotCity.get(pos));
        intent.putExtras(bundle);
        setResult(RESULT_CODE, intent);
        finish();
    }

    @Override
    public void omItemClick(TrainStationAdapter.ViewHolder ViewHolder, View view, int pos) {
        view.setBackgroundResource(R.color.app_theme_green);
        Intent intent = new Intent(this, TrainCitySelectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("city", map.get(trainStationAdapter.index).get(pos));
        intent.putExtras(bundle);
        setResult(RESULT_CODE, intent);
        finish();
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }


    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.TRAIN_CITY_SELECT);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(final String result) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        parseData(result);
                        addDataToAdapter();
                        myHandler.sendEmptyMessage(0);
                    }
                }).start();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(TrainCitySelectActivity.this, "网络异常,请稍候重试", Toast.LENGTH_SHORT);
                llLoading.setVisibility(View.GONE);
                Log.e("Error", ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void addDataToAdapter() {
        if (hotCity != null && hotCity.size() > 0) {
            hotCityAdapter.fillData(hotCity);
        }

        if (map.keySet().size() > 0) {
            ziMuAdapter.fillData(new ArrayList<>(map.keySet()));
            ziMuAdapter.setSelectItemId(0);
        }

        trainStationAdapter.fillData(map.get("A"), "A");

        if (map.values().size() > 0) {
            Iterator it = map.values().iterator();
            while (it.hasNext()) {
                List<TrainCityBean> list = (List) it.next();
                for (TrainCityBean bean : list) {
                    allStationName.add(bean.getName());
                    searchMap.put(bean.getName(), bean);
                }
            }
            searchAdapter = new SearchAdapter<>(TrainCitySelectActivity.this,
                    android.R.layout.simple_list_item_1, allStationName, 3);
        }
    }

    private void parseData(String result) {
        System.out.println(result);
        JsonObject jsonObject;
        Gson gson = new Gson();
        try {
            jsonObject = new JsonParser().parse(result).getAsJsonObject().get("data").getAsJsonObject();
            String str = jsonObject.get("hot").toString();
            hotCity = gson.fromJson(str, new TypeToken<List<TrainCityBean>>() {
            }.getType());
            str = jsonObject.get("other").toString();
            map = gson.fromJson(str, new TypeToken<Map<String, List<TrainCityBean>>>() {
            }.getType());

            if (hotCity != null && hotCity.size() > 0) {
                hotCityAdapter.fillData(hotCity);
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PrefUtils.setObject(TrainCitySelectActivity.this,"othercity",map);
                    PrefUtils.setObject(TrainCitySelectActivity.this,"hotcity",hotCity);
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyHandler extends Handler {
        private final WeakReference<TrainCitySelectActivity> mActivity;

        public MyHandler(TrainCitySelectActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            autoTextView.setAdapter(searchAdapter);
            showView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
        myHandler=null;
    }


}
