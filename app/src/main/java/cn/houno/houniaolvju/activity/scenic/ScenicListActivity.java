package cn.houno.houniaolvju.activity.scenic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.zxl.library.DropDownMenu;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.adapter.ScenicListAdapter;
import cn.houno.houniaolvju.bean.ScenicListBean;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.PrefUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：景点列表
 * 创建人：qzc
 * 创建时间：2017/1/4 9:46
 * 修改人：qzc
 * 修改时间：2017/1/4 9:46
 * 修改备注：
 */
public class ScenicListActivity extends Activity {

    private ScenicListActivity mActivity;

    private String mCityId = "";
    private String mType = "";
    private String mKeyword = "";
    private ImageView ivBack;
    private EditText etSearch;

    private DropDownMenu ddmScenicList;
    private String headers[] = {"分类", "星级", "排序"};

    private int[] types = new int[]{DropDownMenu.TYPE_LIST_CITY
            , DropDownMenu.TYPE_LIST_CITY, DropDownMenu.TYPE_LIST_CITY};
    private String[] cates = new String[]{};  //分类
    private String[] stars = {"全部", "5A", "4A", "3A", "其他"};  //星级
    private String[] sorts = {"默认排序", "价格由低到高", "价格由高到低"};  //排序
    //选中结果
    private int cate = 0;
    private int star = 0;
    private int sort = 0;

    //列表页数
    private int page = 1;
    private XRefreshView mRefreshView;
    private ListView mListView;
    private ScenicListAdapter mAdapter;
    private List<ScenicListBean.DataBean> mList = new ArrayList<>();
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_list);
        ButterKnife.bind(this);
        mActivity = ScenicListActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);
        userid = PrefUtils.getString(mActivity, "userid", "");
        Log.i("666", "id===== "+userid);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        etSearch = (EditText) findViewById(R.id.et_search);
        ddmScenicList = (DropDownMenu) findViewById(R.id.ddm_scenic_list);
    }

    private void initData() {
        Intent intent = getIntent();
        mKeyword = intent.getStringExtra("keyword");
        cate = intent.getIntExtra("cate", 0);
        cates = intent.getStringArrayExtra("cates");
        if (TextUtils.isEmpty(mKeyword)) {
            mCityId = intent.getStringExtra("cityid");
            mType = intent.getStringExtra("type");
        } else {
            etSearch.setText(mKeyword);
        }
        initContentView();
        mRefreshView.startRefresh();
    }

    private void initEvent() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
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

           /*
        * 点击搜索事件监听
        * */
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String input = etSearch.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(mActivity, "请输入关键字", Toast.LENGTH_SHORT).show();
                } else {
                    etSearch.clearFocus();
                    getDataFromServer();
                }
                return true;
            }
        });

        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //编辑框获得焦点

                } else {
                    //编辑框取消焦点

                    //收起键盘
                    InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                }
            }
        });

        /*
        * 取消编辑框焦点
        * */
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (etSearch.isFocused()) {
                    etSearch.clearFocus();
                }
                return false;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(mActivity, ScenicDetailActivity.class);
                intent.putExtra("scenicid", mList.get(position).getScenicid());
                startActivity(intent);
            }
        });
    }

    private void initContentView() {
        View contentView = getLayoutInflater().inflate(R.layout.layout_sceniclist_contentview, null);
        mRefreshView = (XRefreshView) contentView.findViewById(R.id.rfv_scenic_list);
        mRefreshView.setPullLoadEnable(true);
        mListView = (ListView) contentView.findViewById(R.id.lv_content);
        ddmScenicList.setDropDownMenu(Arrays.asList(headers), initViewData(), contentView);

        mAdapter = new ScenicListAdapter(this);
        mListView.setAdapter(mAdapter);

        //该监听回调只监听默认类型，如果是自定义view请自行设置，参照demo
        ddmScenicList.addMenuSelectListener(new DropDownMenu.OnDefultMenuSelectListener() {
            @Override
            public void onSelectDefaultMenu(int index, int pos, String clickstr) {
                //index:点击的tab索引，pos：单项菜单中点击的位置索引，clickstr：点击位置的字符串
                if (etSearch.isFocused()) {
                    etSearch.clearFocus();
                }
                switch (index) {
                    case 0: //分类
                        cate = pos;
                        break;
                    case 1: //等级
                        if (pos == 0) {
                            star = 0;
                        } else if (pos == 1) {
                            star = 5;
                        } else if (pos == 2) {
                            star = 4;
                        } else if (pos == 3) {
                            star = 3;
                        } else if (pos == 4) {
                            star = 1;
                        }
                        break;
                    case 2: //排序
                        sort = pos;
                        break;
                }

                getDataFromServer();
            }
        });
    }

    private void getDataFromServer() {
        mRefreshView.setPullLoadEnable(true);
        RequestParams params = new RequestParams(Constants.SCENIC_LIST);
        params.addBodyParameter("city", mCityId);
        params.addBodyParameter("keyword", etSearch.getText().toString().trim());
       // params.addBodyParameter("cate", cate + "");
        //params.addBodyParameter("xji", star + "");
        params.addBodyParameter("UserID",userid );
        params.addBodyParameter("sort", sort + "");
        params.addBodyParameter("p", page + "");
        //params.addBodyParameter("type", mType);



        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                page = 1;
                try {
                    Log.i("666", "id===== "+result);
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, false);
                    } else {
                        mRefreshView.setPullLoadEnable(false);
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
                mRefreshView.stopRefresh();
            }
        });
    }

    private void getMoreDataFromServer() {
        page++;
        RequestParams params = new RequestParams(Constants.SCENIC_LIST);
        params.addBodyParameter("city", mCityId);
        params.addBodyParameter("keyword", etSearch.getText().toString().trim());
        params.addBodyParameter("cate", cate + "");
        params.addBodyParameter("xji", star + "");
        params.addBodyParameter("sort", sort + "");
        params.addBodyParameter("p", page + "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        parseData(result, true);
                    } else {
                        mRefreshView.setPullLoadEnable(false);
                        Toast.makeText(mActivity, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                page--;
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                mRefreshView.stopLoadMore();
            }
        });
    }

    /*
 * 解析处理数据
 * */
    private void parseData(String result, boolean isMore) {
        Gson gson = new Gson();
        ScenicListBean scenicListBean = gson.fromJson(result, ScenicListBean.class);
        if (!isMore) {  //下拉刷新数据
            mList = scenicListBean.getData();
        } else {
            mList.addAll(scenicListBean.getData());
        }
        if (mAdapter == null) {
            mAdapter = new ScenicListAdapter(this);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.setDatas(mList);
        }
    }


    private List<HashMap<String, Object>> initViewData() {
        List<HashMap<String, Object>> viewDatas = new ArrayList<>();
        HashMap<String, Object> map;
        for (int i = 0; i < headers.length; i++) {
            map = new HashMap<>();
            map.put(DropDownMenu.KEY, types[i]);
            switch (i) {
                case 0:
                    map.put(DropDownMenu.VALUE, cates);
                    if (cate != 0) {
                        map.put(DropDownMenu.SELECT_POSITION, cate);
                    }
                    break;
                case 1:
                    map.put(DropDownMenu.VALUE, stars);
                    // map.put(DropDownMenu.SELECT_POSITION, 0);
                    break;
                case 2:
                    map.put(DropDownMenu.VALUE, sorts);
                    //   map.put(DropDownMenu.SELECT_POSITION, 0);
                    break;
                default:
                    //
                    break;
            }
            viewDatas.add(map);
        }
        return viewDatas;
    }


    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (ddmScenicList.isShowing()) {
            ddmScenicList.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}
