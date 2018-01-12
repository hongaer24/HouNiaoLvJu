package cn.houno.houniaolvju.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XScrollView;
import com.baidu.location.BDLocation;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.ActivityListActivity;
import cn.houno.houniaolvju.activity.CitySelectActivity;
import cn.houno.houniaolvju.activity.FlashSaleDetailActivity;
import cn.houno.houniaolvju.activity.FlashSaleListActivity;
import cn.houno.houniaolvju.activity.SearchActivity;
import cn.houno.houniaolvju.activity.flight.FlightNewActivity;
import cn.houno.houniaolvju.activity.foreignhotel.ForeignHotelListActivity;
import cn.houno.houniaolvju.activity.hotel.HotelDetailActivity;
import cn.houno.houniaolvju.activity.hotel.HotelListActivity;
import cn.houno.houniaolvju.activity.hotel.SearchHotelActivity;
import cn.houno.houniaolvju.activity.scenic.ScenicDetailActivity;
import cn.houno.houniaolvju.activity.scenic.ScenicIndexActivity;
import cn.houno.houniaolvju.activity.train.TrainInquiryActivity;
import cn.houno.houniaolvju.activity.ydhotel.YdIndexActivity;
import cn.houno.houniaolvju.adapter.HomeHFAdapter;
import cn.houno.houniaolvju.adapter.HomeHSAdapter;
import cn.houno.houniaolvju.adapter.HomeScenicTicketAdapter;
import cn.houno.houniaolvju.adapter.HotRegionAdapter;
import cn.houno.houniaolvju.adapter.ScenicCateAdapter;
import cn.houno.houniaolvju.adapter.TopPicAdapter;
import cn.houno.houniaolvju.bean.FenQuanListBean;
import cn.houno.houniaolvju.bean.HomeIndexDataBean;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.MainHotelBean;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.SportsBean;
import cn.houno.houniaolvju.bean.HomeIndexDataBean.DataBean.TgHotelBean;
import cn.houno.houniaolvju.bean.HomeRcmdScenicBean;
import cn.houno.houniaolvju.bean.ScenicIndexBean;
import cn.houno.houniaolvju.bean.TopPicBean;
import cn.houno.houniaolvju.fragment.home.HotelBulkPage;
import cn.houno.houniaolvju.fragment.home.MainHotelFqHousesPage;
import cn.houno.houniaolvju.fragment.home.ScenicTicketPage;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.DateUtil;
import cn.houno.houniaolvju.utils.LocationHelper;
import cn.houno.houniaolvju.utils.MyText2Utils;
import cn.houno.houniaolvju.view.CustomViewPager;
import cn.houno.houniaolvju.view.InnerGridView;
import cn.houno.houniaolvju.view.InnerListView;
import cn.iwgang.countdownview.CountdownView;

/**
 * 项目名称：Houniaolvju
 * 类描述：
 * 创建人：qzc
 * 创建时间：2016/12/12 15:17
 * 修改人：qzc
 * 修改时间：2016/12/12 15:17
 * 修改备注：
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {


    private static final int LOC = 1;   //定位
    private static final int TOP_PICTURE = 2;   //首页轮播图
    private static final int INDEX = 3; //首页数据
    private static final int FENQUAN = 4; //分权数据
    private static final int RCMD_SCENIC = 5;   //景区门票
    @Bind(R.id.iv_more_scenic)
    ImageView ivMoreScenic;


    private boolean isFirstIn = true;

    private LinearLayout llMain;
    private ImageView ivToggle;
    private TextView tvSearch;
    private LinearLayout llSearch;
    private LinearLayout llLocation;
    private TextView tvLocationCity;
    private ImageView ivLocation;
    private LinearLayout llLoading;
    private ProgressBar pbLoading;
    private TextView tvLoading;
    private XRefreshView mRefreshView;
    private XScrollView mScrollView;
    private LinearLayout llContent;

    //==================轮播图===============
    private ArrayList<String> topImagesList = new ArrayList<>();    //图片列表
    private ArrayList<String> topUrl = new ArrayList<>();   //轮播图对应房间地址
    private RollPagerView mRollPagerView;
    private TopPicAdapter mTopPicAdapter;
    //==================分类列表===============
    private InnerGridView gvCategory;
    private List<Map<String, Object>> categoryList = new ArrayList<>();
    private SimpleAdapter categoryAdapter;

    // 图片封装为一个数组
    private int[] categoryIcon = {R.drawable.icon_hotel, R.drawable.icon_scenic, R.drawable.icon_activity, R.drawable.icon_ydhotel, R.drawable.icon_flight, R.drawable.icon_train};
    private String[] categoryName = {"酒店", "景点门票", "活动", "异养", "机票", "火车票"};
    //限时团购
    private String mSportsId;
    private TextView tvFlashSaleMore;
    private LinearLayout mLlFlashSale;
    private ImageView mIvFlashSaleImg;
    private TextView tvFnteTxt;
    private CountdownView mCdvFlashSale;
    private TextView mTvFlashSaleTitle;
    private TextView mTvFlashSaleSprice;    //市场价
    private TextView mTvFlashSalePrice;
    //酒店拼团
    private HomeHSAdapter mHsAdapter;
    private ViewPager vpHs;
    private IndicatorViewPager ivpHs;
    private ScrollIndicatorView sivHs;
    private ArrayList<Fragment> hsFragments;
    //热门地区
    private RecyclerView rcvHotRegion;
    private HotRegionAdapter mHotRegionAdapter;
    private List<Integer> mHotDatas;
    private List<String> mHotCityId;
    private ArrayList<String> mHotCityName;
    //主推酒店、分权度假
    private HomeHFAdapter mHfAdapter;
    private CustomViewPager vpHf;
    private IndicatorViewPager ivpHf;
    private ScrollIndicatorView sivHf;
    private ArrayList<MainHotelFqHousesPage> hfFragments;
    //景区门票
    private InnerListView lvScenicTicket;
    private ArrayList<HomeRcmdScenicBean.DataBean> mHomeScenicList;
    private HomeScenicTicketAdapter mScenicAdapter;
    private String mCityId;
    private String mCityName;
    private List<MainHotelBean> mainHotel;
    private List<FenQuanListBean.DataBean> fqHouses;
    private SlidingMenu slidingMenu;

    private boolean isFirstSetHs = true;
    private boolean isFirstSetMainHotel = true;
    private boolean isFirstSetFenQuan = true;
    private HotelBulkPage hotelBulkPage;
    private ScenicTicketPage scenicTicketPage;


    private LocationHelper locationHelper = LocationHelper.getInstance();
    private String mLongitude;
    private String mLatitude;
    private List<ScenicIndexBean.LocalBean> mLocalList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {
        slidingMenu = mActivity.getLeftMenu();
        llLoading = (LinearLayout) view.findViewById(R.id.ll_loading);
        pbLoading = (ProgressBar) view.findViewById(R.id.pb_loading);
        tvLoading = (TextView) view.findViewById(R.id.tv_loading);
        llMain = (LinearLayout) view.findViewById(R.id.ll_main);
        ivToggle = (ImageView) view.findViewById(R.id.iv_home_toggle);
        tvSearch = (TextView) view.findViewById(R.id.tv_home_search);
        llSearch = (LinearLayout) view.findViewById(R.id.ll_home_search);
        tvLocationCity = (TextView) view.findViewById(R.id.tv_loc_city);
        ivLocation = (ImageView) view.findViewById(R.id.iv_home_loc);
        llLocation = (LinearLayout) view.findViewById(R.id.ll_home_loc);
        mRefreshView = (XRefreshView) view.findViewById(R.id.rfv_home);
        mScrollView = (XScrollView) view.findViewById(R.id.xsv_main);
        llContent = (LinearLayout) view.findViewById(R.id.ll_main_content);
        mRollPagerView = (RollPagerView) view.findViewById(R.id.rpv_scenic);
        //分类列表
        gvCategory = (InnerGridView) view.findViewById(R.id.gv_category);
        //限时团购
        tvFlashSaleMore = (TextView) view.findViewById(R.id.tv_flash_sale_more);
        mLlFlashSale = (LinearLayout) view.findViewById(R.id.layout_home_flash_sale);
        mIvFlashSaleImg = (ImageView) mLlFlashSale.findViewById(R.id.iv_flash_sale_img);
        tvFnteTxt = (TextView) mLlFlashSale.findViewById(R.id.tv_fnte_txt);
        mCdvFlashSale = (CountdownView) mLlFlashSale.findViewById(R.id.cdv_flash_sale);
        mTvFlashSaleTitle = (TextView) mLlFlashSale.findViewById(R.id.tv_flash_sale_title);
        mTvFlashSaleSprice = (TextView) mLlFlashSale.findViewById(R.id.tv_flash_sale_sprice);
        mTvFlashSalePrice = (TextView) mLlFlashSale.findViewById(R.id.tv_flash_sale_price);
        //酒店拼团
        vpHs = (ViewPager) view.findViewById(R.id.vp_home_hs);
        sivHs = (ScrollIndicatorView) view.findViewById(R.id.siv_home_hs);

        rcvHotRegion = (RecyclerView) view.findViewById(R.id.rcv_hot_region);

        vpHf = (CustomViewPager) view.findViewById(R.id.vp_home_hf);
        sivHf = (ScrollIndicatorView) view.findViewById(R.id.siv_home_hf);

        lvScenicTicket = (InnerListView) view.findViewById(R.id.lv_home_scenic_ticket);

    }

    @Override
    public void initData() {

        //解决轮播图和下拉刷新的横向滑动时冲突
        mRefreshView.setMoveForHorizontal(true);
        //轮播图
        mRollPagerView.setHintView(new ColorPointHintView(mActivity, Color.parseColor("#009A44"), Color.WHITE));
        mTopPicAdapter = new TopPicAdapter(mRollPagerView);
        mRollPagerView.setAdapter(mTopPicAdapter);
        locationHelper.start();

        getDataFromServer(LOC);
        getDataFromServer(TOP_PICTURE);

        //分类列表
        categoryList = getCategoryData();
        String[] from = {"image", "text"};
        int[] to = {R.id.iv_gird_img, R.id.tv_grid_txt};
        categoryAdapter = new SimpleAdapter(mActivity, categoryList, R.layout.griditem_category, from, to);
        gvCategory.setAdapter(categoryAdapter);
        gvCategory.setSelector(new ColorDrawable(Color.TRANSPARENT));

        //限时抢购
        mTvFlashSaleSprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);    //文字删除线
        //酒店拼团、门票景点
        hsFragments = new ArrayList<>();
        hotelBulkPage = new HotelBulkPage();
        scenicTicketPage = new ScenicTicketPage();
        hsFragments.add(hotelBulkPage);
        hsFragments.add(scenicTicketPage);
        sivHs.setScrollBar(new ColorBar(mActivity, getResources().getColor(R.color.app_theme_green), 5));

        float unSelectSize = 18;
        //float selectSize = unSelectSize * 1.2f;
        float selectSize = unSelectSize;
        int selectColor = getResources().getColor(R.color.app_theme_green);
        int unSelectColor = getResources().getColor(R.color.black_txt);
        sivHs.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));
//        vpHs.setOffscreenPageLimit(2);
        ivpHs = new IndicatorViewPager(sivHs, vpHs);
        mHsAdapter = new HomeHSAdapter(mActivity, getChildFragmentManager(), hsFragments);
        ivpHs.setAdapter(mHsAdapter);
        //热门地区
        mHotDatas = new ArrayList<>
                (Arrays.asList(R.drawable.pic_bg_hn, R.drawable.pic_bg_xg, R.drawable.pic_bg_am));
        mHotCityId = new ArrayList<>(Arrays.asList("418", "341", "342"));
        mHotCityName = new ArrayList<>(Arrays.asList("河南", "香港", "澳门"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvHotRegion.setLayoutManager(linearLayoutManager);
        mHotRegionAdapter = new HotRegionAdapter(mActivity, mHotDatas);
        rcvHotRegion.setAdapter(mHotRegionAdapter);
        //特价酒店、精品线路
        hfFragments = new ArrayList<>();
        hfFragments.add(new MainHotelFqHousesPage());
        hfFragments.add(new MainHotelFqHousesPage());

        sivHf.setScrollBar(new ColorBar(mActivity, getResources().getColor(R.color.app_theme_green), 5));
        float unSelectSize2 = 18;
        //float selectSize = unSelectSize * 1.2f;
        float selectSize2 = unSelectSize2;
        int selectColor2 = getResources().getColor(R.color.app_theme_green);
        int unSelectColor2 = getResources().getColor(R.color.black_txt);
        sivHf.setOnTransitionListener(new OnTransitionTextListener()
                .setColor(selectColor2, unSelectColor2).setSize(selectSize2, unSelectSize2));
        ivpHf = new IndicatorViewPager(sivHf, vpHf);
        mHfAdapter = new HomeHFAdapter(mActivity, getChildFragmentManager(), hfFragments);
        ivpHf.setAdapter(mHfAdapter);

        //景区门票
        getDataFromServer(INDEX);
        getDataFromServer(RCMD_SCENIC);
        if (!mRefreshView.getPullLoadEnable()) {
            mRefreshView.setPullLoadEnable(true);//可以上拉加载
        }
       /* slidingMenu.addIgnoredView(mRefreshView);
        slidingMenu.addIgnoredView(mScrollView);*/

    }

    @Override
    protected void initEvent() {


        locationHelper.setCallBack(new LocationHelper.LocationCallBack() {
            @Override
            public void callBack(BDLocation bdLocation) {
                mLongitude = bdLocation.getLongitude() + "";
                mLatitude = bdLocation.getLatitude() + "";

                getDataFromServer(LOC);
            }
        });


        ivToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingMenu.toggle();
            }
        });

        llLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("from", "home");
                intent.setClass(mActivity, CitySelectActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        mRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                getDataFromServer(TOP_PICTURE);
                getDataFromServer(RCMD_SCENIC);
                getDataFromServer(INDEX);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                LoadMoreRcmdScenic();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });


            mRollPagerView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent();
                    if(position==0||position==2){
                        String sUrl = topUrl.get(position);
                        //url拆分出hid
                        //    String hid = sUrl.split("/hid/")[1].split("/rid/")[0];
                        String url_1 = sUrl.split("/hid/")[1];
                        StringBuilder hid = new StringBuilder();
                        for (int i = 0; i < url_1.length(); i++) {
                            char c = url_1.charAt(i);
                            if (!Character.isDigit(c)) {
                                break;//只要有一位不符合要求退出循环
                            }
                            hid.append(c);
                        }

                        intent.putExtra("hid", hid.toString());
                        if (sUrl.contains("Foreign")) {
                            intent.putExtra("from", "foreign");
                        } else {
                            intent.putExtra("from", "home");
                        }
                        if(hid!=null ){
                            intent.setClass(mActivity, HotelDetailActivity.class);
                            startActivity(intent);
                        }
                    }



                }
            });



        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0: //酒店
                        intent.putExtra("cityId", mCityId);
                        intent.putExtra("cityName", mCityName);
                        intent.setClass(mActivity, SearchHotelActivity.class);
                        startActivity(intent);
                        break;
                    case 1://景点门票
                        intent.setClass(mActivity, ScenicIndexActivity.class);
                        startActivity(intent);
                        break;
                    case 2: //活动
                        intent.setClass(mActivity, ActivityListActivity.class);
                        startActivity(intent);
                        break;
                    case 3: //异养
                        intent.setClass(mActivity, YdIndexActivity.class);
                        startActivity(intent);
                        break;
                    case 4: //机票
                        intent.setClass(mActivity, FlightNewActivity.class);
                        startActivity(intent);
                        break;
                    case 5: {
                        intent.setClass(mActivity, TrainInquiryActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });

        mHotRegionAdapter.setOnItemClickListener(new ScenicCateAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                if (position == 3) {
                    intent.setClass(mActivity, ForeignHotelListActivity.class);
                } else {
                    intent.setClass(mActivity, HotelListActivity.class);
                }
                intent.putExtra("cityId", mHotCityId.get(position));
                intent.putExtra("cityName", mHotCityName.get(position));
                startActivity(intent);
            }
        });

        tvSearch.setOnClickListener(this);
        tvFlashSaleMore.setOnClickListener(this);
        mLlFlashSale.setOnClickListener(this);

        lvScenicTicket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(mActivity, ScenicDetailActivity.class);
                intent.putExtra("scenicid", mLocalList.get(position).getScenicid());
                startActivity(intent);
            }
        });
    }

    /*
    * 加载更多数据
    * */

    private int page = 1;

    private void LoadMoreRcmdScenic() {
        page++;
        RequestParams params = new RequestParams(Constants.SCENIC_INDEX);
        params.addBodyParameter("p", page + "");
        params.addBodyParameter("cityid", mCityId);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(result);
                    int status = obj.getInt("status");
                    if (status == 0) {
                        Log.i("0111", "result===" + result);
                        Log.i("0111", "result===" + mCityId);

                        setRcmdScenic(result);
                    } else {
                        mRefreshView.setLoadComplete(true);
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
                mRefreshView.stopLoadMore();
            }
        });
    }

    /*
    * 加载成功设置数据
    * */
    private void setRcmdScenic(String result) {
        Gson gson = new Gson();
        // HomeRcmdScenicBean homeRSBean = gson.fromJson(result, HomeRcmdScenicBean.class);
        ScenicIndexBean scenicBean = gson.fromJson(result, ScenicIndexBean.class);
        mLocalList = scenicBean.getLocal();
        /*for (int i = 0; i < homeRSBean.getData().size(); i++) {
            mHomeScenicList.add(homeRSBean.getData().get(i));
        }*/
        if (mScenicAdapter == null) {
            mScenicAdapter = new HomeScenicTicketAdapter(mActivity, mLocalList);
            lvScenicTicket.setAdapter(mScenicAdapter);
        } else {
            mScenicAdapter.setDatas(mLocalList);
        }
    }


    /*
   * 初始化分类列表数据
   * */
    private List<Map<String, Object>> getCategoryData() {
        //icon和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < categoryIcon.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", categoryIcon[i]);
            map.put("text", categoryName[i]);
            categoryList.add(map);
        }

        return categoryList;
    }


    private void getDataFromServer(final int type) {
        mRefreshView.setLoadComplete(false);    //开启上拉加载
        RequestParams params = null;
        if (type == LOC) {
            params = new RequestParams(Constants.HOME_GET_CITY_ID);
            params.addBodyParameter("lat", mLatitude);
            params.addBodyParameter("lng", mLongitude);
        } else if (type == TOP_PICTURE) {
            params = new RequestParams(Constants.HOME_TOP_PICTURE);
        } else if (type == INDEX) {
            params = new RequestParams(Constants.HOME_INDEX);
            params.addBodyParameter("cityid", mCityId);
        } else if (type == FENQUAN) {
            params = new RequestParams(Constants.HOME_FENQUAN);
        } else if (type == RCMD_SCENIC) {
            //params = new RequestParams(Constants.HOME_RCMD_SCENIC);
            params = new RequestParams(Constants.SCENIC_INDEX);
            params.addBodyParameter("cityid", mCityId);
            Log.i("111", "cityId===== " + mCityId);

        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                parseData(type, result);
                if (type == INDEX) {
                    if (isFirstIn) {
                        isFirstIn = false;
                        mRefreshView.setVisibility(View.VISIBLE);
                        llLoading.setVisibility(View.GONE);
                    }
                }
                if (type == LOC) {
                    System.out.println("LOC:" + result);
                    getDataFromServer(RCMD_SCENIC);
                    getDataFromServer(INDEX);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (type == INDEX) {
                    pbLoading.setVisibility(View.GONE);
                    tvLoading.setText("加载失败,点击重新加载");
                    //Log.e("Error", ex.getMessage());
                    tvLoading.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getDataFromServer(LOC);
                            mRefreshView.startRefresh();
                            pbLoading.setVisibility(View.VISIBLE);
                            tvLoading.setText("正在加载");
                        }
                    });
                }
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

    private void parseData(int type, final String result) {

        try {
            JSONObject obj = new JSONObject(result);
            if (obj.getInt("status") == 0) {
                final Gson gson = new Gson();
                if (type == LOC) {
                    JSONObject data = obj.getJSONObject("data");
                    mCityName = data.getString("name");
                    tvLocationCity.setText(mCityName);
                    mCityId = data.getString("id");
                }
                //解析轮播图数据
                else if (type == TOP_PICTURE) {
                    TopPicBean topPicBean = gson.fromJson(result, TopPicBean.class);
                    int topDataSize = topPicBean.getData().size();
                    topUrl.clear();
                    topImagesList.clear();  //清除数据,重新添加
                    for (int i = 0; i < topDataSize; i++) {
                        topImagesList.add(topPicBean.getData().get(i).getImg());
                        topUrl.add(topPicBean.getData().get(i).getUrl());
                    }
                    if (mTopPicAdapter == null) {
                        mTopPicAdapter = new TopPicAdapter(mRollPagerView);
                        mRollPagerView.setAdapter(mTopPicAdapter);
                    } else {
                        mTopPicAdapter.setImgs(topImagesList);
                    }
                }
                //解析首页数据
                else if (type == INDEX) {
                    //设置限时抢购数据
                    final HomeIndexDataBean homeIndexDataBean = gson.fromJson(result, HomeIndexDataBean.class);
                    List<SportsBean> sports = homeIndexDataBean.getData().getSports();
                    if (sports != null && sports.size() != 0) {
                        setFlashSaleData(sports);
                        mLlFlashSale.setVisibility(View.VISIBLE);
                    } else {
                        mLlFlashSale.setVisibility(View.GONE);
                    }
                    //设置酒店拼团、景点门票数据
                    List<TgHotelBean> tgHotel = homeIndexDataBean.getData().getTgHotel();
                    TgHotelBean tgHotelBean;
                    if (tgHotel != null && tgHotel.size() != 0) {
                        tgHotelBean = homeIndexDataBean.getData().getTgHotel().get(0);
                    } else {
                        tgHotelBean = null;
                    }
                    List<HomeIndexDataBean.DataBean.ActiToursScenicBean> actiScenic = homeIndexDataBean.getData().getActiToursScenic();
                    HomeIndexDataBean.DataBean.ActiToursScenicBean actiToursScenicBean;
                    if (actiScenic != null && actiScenic.size() != 0) {
                        actiToursScenicBean = homeIndexDataBean.getData().getActiToursScenic().get(0);
                    } else {
                        actiToursScenicBean = null;
                    }
                    if (isFirstSetHs) {
                        setHotelScenicPageData(tgHotelBean, actiToursScenicBean);
                        isFirstSetHs = false;
                    } else {
                        hotelBulkPage.setPageData(tgHotelBean);
                        scenicTicketPage.setPageData(actiToursScenicBean);
                    }
                    //设置主推酒店
                    mainHotel = homeIndexDataBean.getData().getMainHotel();
                    if (mainHotel != null && mainHotel.size() != 0) {
                        if (isFirstSetMainHotel) {
                            mHfAdapter.setMainHotelData(mainHotel);
                            isFirstSetMainHotel = false;
                        } else {
                            hfFragments.get(0).setMainHotelData(mainHotel);
                        }
                    } else {
                        if (isFirstSetMainHotel) {
                            mHfAdapter.setMainHotelData(null);
                            isFirstSetMainHotel = false;
                        } else {
                            hfFragments.get(0).setMainHotelData(null);
                        }
                    }
                }
                //解析分权列表
                else if (type == FENQUAN) {
                    FenQuanListBean fenQuanListBean = gson.fromJson(result, FenQuanListBean.class);
                    fqHouses = fenQuanListBean.getData();
                    if (isFirstSetFenQuan) {
                        mHfAdapter.setFQHouseData(fqHouses);
                        isFirstSetFenQuan = false;
                    } else {
                        hfFragments.get(1).setFqData(fqHouses);
                    }
                }

                //解析景区门票数据
                else if (type == RCMD_SCENIC) {
                    //HomeRcmdScenicBean homeRSBean = gson.fromJson(result, HomeRcmdScenicBean.class);
                    //mHomeScenicList = homeRSBean.getData();
                    ScenicIndexBean scenicBean = gson.fromJson(result, ScenicIndexBean.class);
                    mLocalList = scenicBean.getLocal();
                    if (mScenicAdapter == null) {
                        mScenicAdapter = new HomeScenicTicketAdapter(mActivity, mLocalList);
                        lvScenicTicket.setAdapter(mScenicAdapter);
                    } else {
                        mScenicAdapter.setDatas(mLocalList);
                    }
                    page = 1;

                }
            } else {
                Log.e("HomeFragment", obj.getString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
    * 限时抢购
    * */

    private void setFlashSaleData(List<SportsBean> sports) throws JSONException {
        x.image().bind(mIvFlashSaleImg, sports.get(0).getImg());
        mSportsId = sports.get(0).getId();
        mTvFlashSaleTitle.setText(sports.get(0).getTitle());
        mTvFlashSaleSprice.setText(MyText2Utils.getHotelPrice(sports.get(0).getMarket_price(), MyText2Utils.PRICE_START));
        MyText2Utils.formatYuanPrice(mActivity, mTvFlashSalePrice, sports.get(0).getPrice());
        String jsDate = sports.get(0).getJsdate();
        String now_date = sports.get(0).getNow_date();
        boolean isNotEnd = DateUtil.compareDate(now_date, jsDate);

        if (isNotEnd) {
            if (mCdvFlashSale.getVisibility() == View.GONE) {
                mCdvFlashSale.setVisibility(View.VISIBLE);
            }
            tvFnteTxt.setText("距离结束");

            long milliSecond = DateUtil.getCompareMilliSecond
                    (now_date, jsDate);
            mCdvFlashSale.start(milliSecond);

        } else {
            tvFnteTxt.setText("活动已结束");
            mCdvFlashSale.setVisibility(View.GONE);
        }

        mCdvFlashSale.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                tvFnteTxt.setText("活动已结束");
                mCdvFlashSale.setVisibility(View.GONE);
            }
        });

    }

    /*
    * 酒店拼团、景点门票
    * */
    private void setHotelScenicPageData(TgHotelBean tgHotelBean, HomeIndexDataBean.DataBean.ActiToursScenicBean actiScenicBean) {
        mHsAdapter = new HomeHSAdapter(mActivity, getChildFragmentManager(), hsFragments, tgHotelBean, actiScenicBean);
        ivpHs.setAdapter(mHsAdapter);
        if (sivHs.getCurrentItem() != 0) {
            sivHs.setCurrentItem(0);
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_home_search:
                intent.setClass(mActivity, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_flash_sale_more:
                intent.setClass(mActivity, FlashSaleListActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_more_scenic:
                intent.setClass(mActivity, FlashSaleListActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_home_flash_sale:
                intent.setClass(mActivity, FlashSaleDetailActivity.class);
                intent.putExtra("id", mSportsId);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            Bundle bundle = data.getBundleExtra("bundle"); //city即为回传的值
            mCityName = bundle.getString("city");
            mCityId = bundle.getString("cityId");
            tvLocationCity.setText(mCityName);
            mRefreshView.startRefresh();

        }
    }


    @Override
    public void onStop() {
        super.onStop();
        locationHelper.stop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
