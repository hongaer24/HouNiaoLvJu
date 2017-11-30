package cn.houno.houniaolvju.activity.ydhotel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.CitySelectActivity;
import cn.houno.houniaolvju.adapter.YdListAdapter;
import cn.houno.houniaolvju.bean.YdListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 异养列表
 * Created by Administrator on 2017/1/14.
 */

public class YdListActivity extends Activity {


    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_topbar_title)
    TextView tvTopbarTitle;
    @Bind(R.id.ll_city_select)
    LinearLayout llCitySelect;
    @Bind(R.id.et_yd_list_search)
    EditText etYdListSearch;
    @Bind(R.id.lv_yd_list)
    ListView lvYdList;
    @Bind(R.id.rfv_yd_list)
    XRefreshView rfvYdList;

    private YdListActivity mActivity;
    private String mKeyWord = "";
    private String mCityId = "";
    private String mCityName = "";
    private int page = 2;

    private List<YdListBean.DataBean> ydList = new ArrayList<>();
    private YdListAdapter mAdapter;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_yd_list);
        ButterKnife.bind(this);
        mActivity = YdListActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        initView();
        initData();
        initEvent();
    }

    private void initView() {

    }

    private void initData() {
        mIntent = getIntent();
        mCityId = getIntentValue("cityid");
        mCityName = getIntentValue("cityname");
        if (TextUtils.isEmpty(mCityName)) {
            mCityName = "选择城市";
        }
        tvTopbarTitle.setText(mCityName);
        mKeyWord = getIntentValue("keyword");
        etYdListSearch.setText(mKeyWord);
        etYdListSearch.clearFocus();
        rfvYdList.startRefresh();
    }

    private String getIntentValue(String key) {
        if (mIntent.getStringExtra(key) == null) {
            return "";
        } else {
            return mIntent.getStringExtra(key);
        }
    }

    private void initEvent() {
        rfvYdList.setPullLoadEnable(true);
        rfvYdList.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
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

        etYdListSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String input = etYdListSearch.getText().toString().trim();
                if (!mKeyWord.equals(input)) {
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etYdListSearch.getWindowToken(), 0);

                    getDataFromServer();
                }
                return true;
            }
        });

        lvYdList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(mActivity, YdDetailActivity.class);
                intent.putExtra("id", ydList.get(position).getId());
                startActivity(intent);
            }
        });
    }


    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.GET_YD_LIST);
        params.addBodyParameter("city", mCityId);
        params.addBodyParameter("keyword", etYdListSearch.getText().toString().trim());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, false);
                        page = 2;
                    } else {
                        rfvYdList.setPullLoadEnable(false);
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
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
                rfvYdList.stopRefresh();
            }
        });
    }

    private void getMoreDataFromServer() {
        RequestParams params = new RequestParams(Constants.GET_YD_LIST);

        params.addBodyParameter("city", mCityId);
        params.addBodyParameter("keyword", etYdListSearch.getText().toString().trim());
        params.addBodyParameter("p", page + "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, true);
                        page++;
                    } else {
                        rfvYdList.setPullLoadEnable(false);
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
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
                rfvYdList.stopLoadMore();
            }
        });
    }

    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();
        YdListBean ydListBean = gson.fromJson(result, YdListBean.class);
        if (!isMore) {  //下拉刷新数据
            ydList = ydListBean.getData();
        } else {
            ydList.addAll(ydListBean.getData());
        }
        if (mAdapter == null) {
            mAdapter = new YdListAdapter(this, ydList);
            lvYdList.setAdapter(mAdapter);
        } else {
            mAdapter.setDatas(ydList);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            Bundle bundle = data.getBundleExtra("bundle"); //city即为回传的值
            mCityName = bundle.getString("city");
            mCityId = bundle.getString("cityId");
            tvTopbarTitle.setText(mCityName);
            getDataFromServer();
        }
    }

    @OnClick({R.id.iv_back, R.id.ll_city_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_city_select:
                Intent intent = new Intent();
                intent.putExtra("from", "home");
                intent.setClass(mActivity, CitySelectActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
    }
}
