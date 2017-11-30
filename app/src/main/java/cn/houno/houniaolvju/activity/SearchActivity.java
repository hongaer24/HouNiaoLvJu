package cn.houno.houniaolvju.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.hotel.HotelDetailActivity;
import cn.houno.houniaolvju.activity.scenic.ScenicDetailActivity;
import cn.houno.houniaolvju.adapter.GetIndexSearchAdapter;
import cn.houno.houniaolvju.adapter.HistorySearchAdapter;
import cn.houno.houniaolvju.bean.GetIndexSearchBean;
import cn.houno.houniaolvju.db.RecordSQLiteOpenHelper;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.InnerListView;

/**
 * 实时搜索
 * Created by Administrator on 2017/1/12.
 */

public class SearchActivity extends Activity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.et_home_search)
    EditText etHomeSearch;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.ll_home_search)
    LinearLayout llHomeSearch;
    @Bind(R.id.tv_tip)
    TextView tvTip;
    @Bind(R.id.listView)
    InnerListView listView;
    @Bind(R.id.lv_auto)
    InnerListView lvAuto;

    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);
    private SQLiteDatabase db;
    private BaseAdapter adapter;

    private SearchActivity mActivity;
    private TextView tvClear;

    private List<GetIndexSearchBean.DataBean> searchList = new ArrayList<>();
    private GetIndexSearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_search);
        mActivity = SearchActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    private void initData() {

        tvClear = new TextView(this);
        tvClear.setText("清除搜索历史");
        tvClear.setPadding(10, 20, 10, 20);
        tvClear.setGravity(Gravity.CENTER);
        tvClear.setBackgroundColor(Color.parseColor("#f0f0f0"));
        listView.addFooterView(tvClear);


//        // 插入数据，便于测试，否则第一次进入没有数据怎么测试呀？
//        Date date = new Date();
//        long time = date.getTime();
//        insertData("test" + time);
//
//        // 第一次进入查询所有的历史记录
        queryData("");


    }

    private void initEvent() {
        // 清空搜索历史
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                queryData("");
            }
        });

        AutoCompleteTextView completeTextView = new AutoCompleteTextView(this);
        //completeTextView.addTextChangedListener();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etHomeSearch.getText().toString().trim())) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存

                    boolean hasData = hasData(etHomeSearch.getText().toString().trim());
                    if (!hasData) {
                        insertData(etHomeSearch.getText().toString().trim());
                        queryData("");
                    }
                }
            }
        });
        // 搜索框的键盘搜索键点击回调
        etHomeSearch.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
                    if (!TextUtils.isEmpty(etHomeSearch.getText().toString().trim())) {
                        // 先隐藏键盘
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存

                        boolean hasData = hasData(etHomeSearch.getText().toString().trim());
                        if (!hasData) {
                            insertData(etHomeSearch.getText().toString().trim());
                            queryData("");
                        }
                    }
                    // 根据输入的内容模糊查询商品，并跳转到另一个界面，由你自己去实现

                }
                return false;
            }
        });

        // 搜索框的文本变化实时监听
        etHomeSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (s.toString().trim().length() == 0) {
//                    tvTip.setText("搜索历史");
//                } else {
//                    tvTip.setText("搜索结果");
//                }
//                String tempName = etHomeSearch.getText().toString();
//                // 根据tempName去模糊查询数据库中有没有数据
//                queryData(tempName);
                if (s.toString().trim().length() == 0) {
                    tvTip.setText("搜索历史");
                    listView.setVisibility(View.VISIBLE);
                    lvAuto.setVisibility(View.GONE);
                } else {
                    tvTip.setText("搜索结果");
                    if (listView.getVisibility() == View.VISIBLE) {
                        listView.setVisibility(View.GONE);
                    }
                    if (lvAuto.getVisibility() == View.GONE) {
                        lvAuto.setVisibility(View.VISIBLE);
                    }
                    getDataFromServer();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.tv_history_title);
                String name = textView.getText().toString();
                etHomeSearch.setText(name);
                etHomeSearch.setSelection(name.length());
                //  Toast.makeText(mActivity, name, Toast.LENGTH_SHORT).show();
                // 获取到item上面的文字，根据该关键字跳转到另一个页面查询，由你自己去实现
            }
        });

        lvAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                String model = searchList.get(position).getModel();
                String id1 = searchList.get(position).getId();
                if ("hotel".equals(model)) {
                    intent.putExtra("from", "home");
                    intent.putExtra("hid", id1);
                    intent.setClass(mActivity, HotelDetailActivity.class);
                } else {
                    intent.putExtra("id", id1);
                    intent.setClass(mActivity, ScenicDetailActivity.class);
                }
                startActivity(intent);
            }
        });

    }

    private void getDataFromServer() {
        RequestParams params = new RequestParams(Constants.GET_INDEX_SEARCH);
        params.addBodyParameter("keyword", etHomeSearch.getText().toString().trim());

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 1) {
                        Toast.makeText(mActivity, "没有更多数据啦", Toast.LENGTH_SHORT).show();
                    } else if (status == 0) {
                        processData(result);
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

            }
        });
    }

    private void processData(String result) {
        Gson gson = new Gson();
        GetIndexSearchBean getIndexSearchBean = gson.fromJson(result, GetIndexSearchBean.class);
        if (getIndexSearchBean.getData().size() != 0) {
            searchList = getIndexSearchBean.getData();
        }

        if (searchAdapter == null) {
            searchAdapter = new GetIndexSearchAdapter(this, searchList);
            lvAuto.setAdapter(searchAdapter);
        } else {
            searchAdapter.setDatas(searchList);
        }
    }

    private void initView() {

    }


    /**
     * 插入数据
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 模糊查询数据
     */
    private void queryData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象
        adapter = new HistorySearchAdapter(this, R.layout.listitem_search_history, cursor, new String[]{"name"},
                new int[]{R.id.tv_history_title}, 0);
        // 设置适配器
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * 检查数据库中是否已经有该条记录
     */
    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }


}
