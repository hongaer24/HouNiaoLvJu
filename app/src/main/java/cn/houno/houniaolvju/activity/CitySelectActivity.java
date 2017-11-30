package cn.houno.houniaolvju.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.CityListViewAdapter;
import cn.houno.houniaolvju.adapter.SearchAdapter;
import cn.houno.houniaolvju.bean.CityGridViewData;
import cn.houno.houniaolvju.bean.CityListViewData;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：城市选择
 * 创建人：qzc
 * 创建时间：2016/10/6 9:52
 * 修改人：qzc
 * 修改时间：2016/10/6 9:52
 * 修改备注：
 */
public class CitySelectActivity extends Activity {

    private ImageView ivBack;
    private TextView tvTopbarTitle;
    private ListView lvCity;
    private AutoCompleteTextView actvSearch;
    private LinearLayout llLoading;
    private ProgressBar pbLoading;
    private TextView tvLoading;

    private CitySelectActivity mActivity;

    private List<String> cityNameList = new ArrayList<>();
    private List<CityListViewData> listData = new ArrayList<>();
    private CityListViewAdapter cityAdapter;

    public SearchAdapter adapter ;//

    private HashMap<String, String> nameAndId = new HashMap<>();

    private String mFrom;
    private String mUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//绑定竖屏
        setContentView(R.layout.activity_home_select_city);
        mActivity = CitySelectActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initViewFindById();
        initData();
        initEvent();
    }

    private void initViewFindById() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTopbarTitle = (TextView) findViewById(R.id.tv_topbar_title);
        lvCity = (ListView) findViewById(R.id.lv_city);
        actvSearch = (AutoCompleteTextView) findViewById(R.id.search);
        tvLoading = (TextView) findViewById(R.id.tv_loading);
        llLoading = (LinearLayout) findViewById(R.id.ll_loading);
        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);
    }

    private void initData() {
        cityAdapter = new CityListViewAdapter(CitySelectActivity.this, listData);
        lvCity.setAdapter(cityAdapter);
        Intent intent = getIntent();

        mFrom = intent.getStringExtra("from");
        if ("home".equals(mFrom) || "yy".equals(mFrom)) {
            tvTopbarTitle.setText("国内");
            actvSearch.setHint("例:海口/hk");
            mUrl =  Constants.HOME_CITY_LIST;//获取城市列表
        } else if ("foreign".equals(mFrom)) {
            tvTopbarTitle.setText("国际");
            actvSearch.setHint("例:新加坡/xjp");
            mUrl = Constants.FOREIGN_CITY_LIST;//获取国外列表
        }else {
            mFrom = "home";
            tvTopbarTitle.setText("国内");
            actvSearch.setHint("例:海口/hk");
            mUrl =  Constants.HOME_CITY_LIST;//获取城市列表
        }

        getCityListFromServer(mUrl);
    }

    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        actvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city = parent.getItemAtPosition(position).toString();
                String cityId = nameAndId.get(city);
                actvSearch.setText(city);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("city", city);
                bundle.putString("cityId", cityId);
                intent.putExtra("bundle", bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    /*
   * 获取城市列表
   * */
    private void getCityListFromServer(String url) {
        RequestParams params = new RequestParams(url);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 1) {
                        pbLoading.setVisibility(View.GONE);
                        tvLoading.setText(obj.getString("msg"));
                    } else if (status == 0) {

                        parseData(result);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                pbLoading.setVisibility(View.GONE);
                tvLoading.setText("城市加载失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    /*
    * 解析处理数据
    * */
    private void parseData(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            JSONObject dataObj = obj.getJSONObject("data");
            JSONObject otherObj = dataObj.getJSONObject("other");
            int otherSize = otherObj.length();
            for (int i = -1; i < otherSize; i++) {
                if (i == -1) {
                    CityListViewData data = new CityListViewData();
                    data.setName("热门城市");
                    ArrayList<CityGridViewData> grid = new ArrayList<>();
                    int hotCityListSize = dataObj.getJSONArray("hot").length();
                    for (int k = 0; k < hotCityListSize; k++) {
                        CityGridViewData g = new CityGridViewData();
                        String name = dataObj.getJSONArray("hot").getJSONObject(k).get("name").toString();
                        String id = dataObj.getJSONArray("hot").getJSONObject(k).get("id").toString();
                        g.setName(name);
                        g.setId(id);
                        nameAndId.put(name, id);
                        grid.add(g);
                    }
                    data.setGridData(grid);
                    listData.add(data);
                } else {
                    Iterator<?> it = otherObj.keys();
                    while (it.hasNext()) {
                        CityListViewData data = new CityListViewData();
                        String otherKey = it.next().toString();
                        data.setName(otherKey);
                        ArrayList<CityGridViewData> grid = new ArrayList<>();
                        int letterSize = otherObj.getJSONArray(otherKey).length();
                        for (int j = 0; j < letterSize; j++) {
                            CityGridViewData g = new CityGridViewData();
                            String cityName = otherObj.getJSONArray(otherKey).getJSONObject(j).get("name").toString();
                            String cityId = otherObj.getJSONArray(otherKey).getJSONObject(j).get("id").toString();
                            nameAndId.put(cityName, cityId);
                            g.setName(cityName);
                            g.setId(cityId);
                            grid.add(g);
                            cityNameList.add(cityName);
                        }
                        data.setGridData(grid);
                        listData.add(data);
                        i++;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String[] cityListStr = cityNameList.toArray(new String[cityNameList.size()]);
        //自动搜索框
        adapter = new SearchAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, cityListStr, SearchAdapter.ALL);//速度优先
        actvSearch.setAdapter(adapter);//

        cityAdapter.setData(listData);
        llLoading.setVisibility(View.GONE);
        lvCity.setVisibility(View.VISIBLE);
    }

    public AutoCompleteTextView getActvSearch() {
        return actvSearch;
    }
}
