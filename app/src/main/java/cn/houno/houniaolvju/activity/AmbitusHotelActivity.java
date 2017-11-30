package cn.houno.houniaolvju.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.navisdk.adapter.BNOuterTTSPlayerCallback;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNRoutePlanNode.CoordinateType;
import com.baidu.navisdk.adapter.BNaviSettingManager;
import com.baidu.navisdk.adapter.BaiduNaviManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.hotel.HotelDetailActivity;
import cn.houno.houniaolvju.bean.AmbitusHotelInfo;
import cn.houno.houniaolvju.global.Constants;
import cn.houno.houniaolvju.utils.ScreenUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.BorderTextView;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：周边酒店
 * 创建人：qzc
 * 创建时间：2016/10/9 14:26
 * 修改人：qzc
 * 修改时间：2016/10/9 14:26
 * 修改备注：
 */
public class AmbitusHotelActivity extends Activity {

    private Context context;
    private ImageView ivBack;
    private ImageView ivRefresh;
    private ImageView ivLoc;

    private ProgressDialog pd;

    private MapView mMapView;   //百度地图视图控件

    private BaiduMap mBaiduMap; //百度地图对象
    //定位相关
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;//定位监听器
    private boolean isFirstLoc = true;
    private double mLatitude;   //纬度
    private double mLongitude;  //经度
    //覆盖物相关
    private BitmapDescriptor mMarker;   //标记
    private LinearLayout lyWindow;
    private List<AmbitusHotelInfo> infoList = new ArrayList<>();
    //导航相关
    private LatLng mLastLocationData;   //起点
    private LatLng mDastLocationData;   //终点

    public static List<Activity> activityList = new LinkedList<Activity>();
    private static final String APP_FOLDER_NAME = "com_houno_houniaolvju";

    private Button mWgsNaviBtn = null;
    private Button mGcjNaviBtn = null;
    private Button mBdmcNaviBtn = null;
    private Button mDb06ll = null;
    private String mSDCardPath = null;

    public static final String ROUTE_PLAN_NODE = "routePlanNode";
    public static final String SHOW_CUSTOM_ITEM = "showCustomItem";
    public static final String RESET_END_NODE = "resetEndNode";
    public static final String VOID_MODE = "voidMode";

    private final static String authBaseArr[] =
            {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};
    private final static String authComArr[] = {Manifest.permission.READ_PHONE_STATE};
    private final static int authBaseRequestCode = 1;
    private final static int authComRequestCode = 2;

    private boolean hasInitSuccess = false;
    private boolean hasRequestComAuth = false;
    private String mAddrStr;
    private String mToAddress;

    private ImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityList.add(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_ambitus_hotel);
        StatusBarUtils.setWindowStatusBarColor(AmbitusHotelActivity.this, R.color.app_theme_green);
        this.context = this;
        initFindViewById();
        initData();
        initEvent();
    }

    private void initFindViewById() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivRefresh = (ImageView) findViewById(R.id.iv_fresh);
        ivLoc = (ImageView) findViewById(R.id.iv_loc);
        mMapView = (MapView) findViewById(R.id.bmapView);
        lyWindow = (LinearLayout) findViewById(R.id.ly_window);
    }

    private void initData() {
        options = new ImageOptions.Builder().setFadeIn(true)
                .setLoadingDrawableId(R.drawable.loading_default_img)
                .build();

        mBaiduMap = mMapView.getMap();  //获取地图对象
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f); //设置比例  1km/1cm
        mBaiduMap.setMapStatus(msu);
        //初始化定位
        mLocationClient = new LocationClient(this);
        mLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");   // 设置坐标类型
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
        //初始化覆盖物
        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_hotel_marker);
        //初始化导航相关
        if (initDirs()) {
            initNavi();
        }
    }


    private void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                centerToMyLocation();
            }
        });

        ivRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromServer();
            }
        });

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle extraInfo = marker.getExtraInfo();
                final AmbitusHotelInfo info = (AmbitusHotelInfo) extraInfo.getSerializable("info");
                TextView title = (TextView) lyWindow.findViewById(R.id.tv_window_title);
                TextView address = (TextView) lyWindow.findViewById(R.id.tv_window_address);
                TextView phone = (TextView) lyWindow.findViewById(R.id.tv_window_phone);
                ImageView img = (ImageView) lyWindow.findViewById(R.id.iv_window_img);
                Button btnAddOrder = (Button) lyWindow.findViewById(R.id.btn_add_order);    //立即预订
                Button btnGotoHotel = (Button) lyWindow.findViewById(R.id.btn_go_to);
                if (info != null) {
                    mToAddress = info.getAddress();
                    x.image().bind(img, info.getImgUrl(), options);
                    title.setText(info.getTitle());
                    phone.setText("电话:" + info.getTelphone());
                    address.setText("地址:" + mToAddress);
                }

                //立即预订
                btnAddOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("hid", info.getId());
                        intent.setClass(context, HotelDetailActivity.class);
                        startActivity(intent);
                    }
                });
                //到这里去
                btnGotoHotel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDastLocationData = new LatLng(info.getLatitude(), info.getLongitude());
                        routeplanToNavi();
                    }
                });


                lyWindow.setVisibility(View.VISIBLE);
                //这里再弹窗
                popUpTextWindow(info.getTitle(), info.getLatitude(), info.getLongitude());
                return true;
            }
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                lyWindow.setVisibility(View.GONE);
                mBaiduMap.hideInfoWindow();
                //   text.setVisibility(View.GONE);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    private BorderTextView text;

    private void popUpTextWindow(String title, double latitude, double longitude) {
        text = new BorderTextView(AmbitusHotelActivity.this, "#f00000");
        //  text.setPaintColor(Color.parseColor("#ff0000"));
        //创建InfoWindow展示的view
        text.setBackgroundColor(Color.WHITE);
        text.setGravity(Gravity.CENTER);
        text.setMaxWidth(ScreenUtils.getScreenWidth(AmbitusHotelActivity.this) / 3);
        text.setEllipsize(TextUtils.TruncateAt.END);
        text.setMaxLines(2);
        text.setPadding(2, 2, 2, 2);
        text.setTextSize(12);
        text.setTextColor(Color.parseColor("#505050"));
        text.setText(title);
        //定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(latitude, longitude);
        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(text, pt, -47);
        //显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);
    }


    private void getDataFromServer() {

        pd = ProgressDialog.show(AmbitusHotelActivity.this, null, "正在加载百度地图");
        RequestParams params = new RequestParams(Constants.AMBITUS_HOTEL);
        System.out.println("lat" + mLatitude + ",lng" + mLongitude);
        params.addBodyParameter("lat", mLatitude + "");
        params.addBodyParameter("lng", mLongitude + "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    System.out.println("result=======" + result);
                    JSONObject obj = new JSONObject(result);
                    if (obj.getInt("status") == 0) {
                        processData(result);
                    } else {
                        Toast.makeText(context, "没有数据", Toast.LENGTH_SHORT).show();
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
                if (pd != null) {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }
                }
            }
        });
    }

    private void processData(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            JSONArray array = obj.getJSONArray("data");
            infoList.clear();
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject json = (JSONObject) array.get(i);
                    String id = json.getString("id");
                    String title = json.getString("title");
                    String imgUrl = json.getString("wap_img");
                    String telphone = json.getString("telphone");
                    String address = json.getString("address");
                    double latitude = json.getDouble("lat");
                    double longitude = json.getDouble("lng");
                    String rid = json.getJSONObject("roomInfo").getString("id");
                    System.out.println("??????????" + title + "," + imgUrl + "," + telphone + "," + address + "," + latitude + "," + longitude);
                    AmbitusHotelInfo bean = new AmbitusHotelInfo(id, rid, title, imgUrl, telphone, address, latitude, longitude);
                    infoList.add(bean);
                } catch (Exception e) {
                    System.out.println("try for====================>这里出错," + e.getMessage());
                }

            }
            addOverlays(infoList);

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("try =======catch===========>这里出错");
        }
    }

    /*
    * 添加覆盖物
    * */
    private void addOverlays(List<AmbitusHotelInfo> infoList) {
        mBaiduMap.clear();
        LatLng latLng;
        Marker marker;
        OverlayOptions options;
        for (AmbitusHotelInfo info : infoList) {
            // 经纬度
            latLng = new LatLng(info.getLatitude(), info.getLongitude());
            //图标
            options = new MarkerOptions().position(latLng).icon(mMarker).zIndex(5);
            marker = (Marker) mBaiduMap.addOverlay(options);
            Bundle bundle = new Bundle();
            bundle.putSerializable("info", info);
            marker.setExtraInfo(bundle);
        }
    }

    /*
    * 定位监听器
    * */
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            // map view 销毁后不在处理新接收的位置
            if (bdLocation == null || mMapView == null)
                return;
            //定位回调
            mLatitude = bdLocation.getLatitude();   //经度
            mLongitude = bdLocation.getLongitude(); //维度

            mLastLocationData = new LatLng(mLatitude, mLongitude);

            System.out.println("维度========================" + mLatitude);
            System.out.println("经度========================" + mLongitude);

            MyLocationData data = new MyLocationData.Builder()//
                    .accuracy(bdLocation.getRadius())//
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();

            mBaiduMap.setMyLocationData(data);
            if (isFirstLoc) {
                centerToMyLocation();
                getDataFromServer();
                isFirstLoc = false;
                mAddrStr = bdLocation.getAddrStr();
                if (mAddrStr != null) {
                    Toast.makeText(context, "当前位置:\n" + bdLocation.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "请连接网络", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    /*
    * 定位到我的位置
    * */
    private void centerToMyLocation() {
        LatLng latLng = new LatLng(mLatitude, mLongitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }

    /*
    * 初始化文件
    * */
    private boolean initDirs() {
        mSDCardPath = getSdcardDir();
        if (mSDCardPath == null) {
            return false;
        }
        File f = new File(mSDCardPath, APP_FOLDER_NAME);
        if (!f.exists()) {
            try {
                f.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    String authinfo = null;

    /**
     * 内部TTS播报状态回传handler
     */
    private Handler ttsHandler = new Handler() {
        public void handleMessage(Message msg) {
            int type = msg.what;
            switch (type) {
                case BaiduNaviManager.TTSPlayMsgType.PLAY_START_MSG: {
                    // showToastMsg("Handler : TTS play start");
                    break;
                }
                case BaiduNaviManager.TTSPlayMsgType.PLAY_END_MSG: {
                    // showToastMsg("Handler : TTS play end");
                    break;
                }
                default:
                    break;
            }
        }
    };


    /**
     * 内部TTS播报状态回调接口
     */
    private BaiduNaviManager.TTSPlayStateListener ttsPlayStateListener = new BaiduNaviManager.TTSPlayStateListener() {

        @Override
        public void playEnd() {
            // showToastMsg("TTSPlayStateListener : TTS play end");
        }

        @Override
        public void playStart() {
            // showToastMsg("TTSPlayStateListener : TTS play start");
        }
    };

    public void showToastMsg(final String msg) {
        AmbitusHotelActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(AmbitusHotelActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /*
    * 初始化导航
    * */
    private void initNavi() {

        BNOuterTTSPlayerCallback ttsCallback = null;

        // 申请权限
        if (android.os.Build.VERSION.SDK_INT >= 23) {

            if (!hasBasePhoneAuth()) {
                this.requestPermissions(authBaseArr, authBaseRequestCode);
                return;
            }
        }

        BaiduNaviManager.getInstance().init(this, mSDCardPath, APP_FOLDER_NAME, new BaiduNaviManager.NaviInitListener() {
            @Override
            public void onAuthResult(int status, String msg) {
                if (0 == status) {
                    authinfo = "key校验成功!";
                } else {
                    authinfo = "key校验失败, " + msg;
                }
//                AmbitusHotelActivity.this.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        Toast.makeText(context, authinfo, Toast.LENGTH_LONG).show();
//                    }
//                });
            }

            public void initSuccess() {
                System.out.println("百度导航引擎初始化成功");
               // Toast.makeText(context, "百度导航引擎初始化成功", Toast.LENGTH_SHORT).show();
                hasInitSuccess = true;
                initSetting();
            }

            public void initStart() {
                System.out.println("百度导航引擎初始化开始");
            //    Toast.makeText(context, "百度导航引擎初始化开始", Toast.LENGTH_SHORT).show();
            }

            public void initFailed() {
                System.out.println("百度导航引擎初始化失败");
                Toast.makeText(context, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
            }

        }, null, ttsHandler, ttsPlayStateListener);

    }


    private boolean hasBasePhoneAuth() {
        PackageManager pm = this.getPackageManager();
        for (String auth : authBaseArr) {
            if (pm.checkPermission(auth, this.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    private boolean hasCompletePhoneAuth() {

        PackageManager pm = this.getPackageManager();
        for (String auth : authComArr) {
            if (pm.checkPermission(auth, this.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /*
    * 获取sdk卡路径
    * */
    private String getSdcardDir() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    public class DemoRoutePlanListener implements BaiduNaviManager.RoutePlanListener {

        private BNRoutePlanNode mBNRoutePlanNode = null;

        public DemoRoutePlanListener(BNRoutePlanNode node) {
            mBNRoutePlanNode = node;
        }

        @Override
        public void onJumpToNavigator() {
            /*
             * 设置途径点以及resetEndNode会回调该接口
             */

            for (Activity ac : activityList) {

                if (ac.getClass().getName().endsWith("BNDemoGuideActivity")) {

                    return;
                }
            }
            Intent intent = new Intent(AmbitusHotelActivity.this, BNDemoGuideActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ROUTE_PLAN_NODE, mBNRoutePlanNode);
            intent.putExtras(bundle);
            startActivity(intent);

        }

        @Override
        public void onRoutePlanFailed() {
            Toast.makeText(AmbitusHotelActivity.this, "算路失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void initSetting() {
        // BNaviSettingManager.setDayNightMode(BNaviSettingManager.DayNightMode.DAY_NIGHT_MODE_DAY);
        BNaviSettingManager
                .setShowTotalRoadConditionBar(BNaviSettingManager.PreViewRoadCondition.ROAD_CONDITION_BAR_SHOW_ON);
        BNaviSettingManager.setVoiceMode(BNaviSettingManager.VoiceMode.Veteran);
        // BNaviSettingManager.setPowerSaveMode(BNaviSettingManager.PowerSaveMode.DISABLE_MODE);
        BNaviSettingManager.setRealRoadCondition(BNaviSettingManager.RealRoadCondition.NAVI_ITS_ON);
    }

    private BNOuterTTSPlayerCallback mTTSCallback = new BNOuterTTSPlayerCallback() {

        @Override
        public void stopTTS() {
            Log.e("test_TTS", "stopTTS");
        }

        @Override
        public void resumeTTS() {
            Log.e("test_TTS", "resumeTTS");
        }

        @Override
        public void releaseTTSPlayer() {
            Log.e("test_TTS", "releaseTTSPlayer");
        }

        @Override
        public int playTTSText(String speech, int bPreempt) {
            Log.e("test_TTS", "playTTSText" + "_" + speech + "_" + bPreempt);

            return 1;
        }

        @Override
        public void phoneHangUp() {
            Log.e("test_TTS", "phoneHangUp");
        }

        @Override
        public void phoneCalling() {
            Log.e("test_TTS", "phoneCalling");
        }

        @Override
        public void pauseTTS() {
            Log.e("test_TTS", "pauseTTS");
        }

        @Override
        public void initTTSPlayer() {
            Log.e("test_TTS", "initTTSPlayer");
        }

        @Override
        public int getTTSState() {
            Log.e("test_TTS", "getTTSState");
            return 1;
        }
    };

    /*
    * 导航坐标等设置
    * */
    private void routeplanToNavi() {
        CoordinateType coType = CoordinateType.BD09LL;
        if (!hasInitSuccess) {
            Toast.makeText(AmbitusHotelActivity.this, "还未初始化!", Toast.LENGTH_SHORT).show();
        }
        // 权限申请
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            // 保证导航功能完备
            if (!hasCompletePhoneAuth()) {
                if (!hasRequestComAuth) {
                    hasRequestComAuth = true;
                    this.requestPermissions(authComArr, authComRequestCode);
                    return;
                } else {
                    Toast.makeText(AmbitusHotelActivity.this, "没有完备的权限!", Toast.LENGTH_SHORT).show();
                }
            }

        }
        BNRoutePlanNode sNode;
        BNRoutePlanNode eNode;


        sNode = new BNRoutePlanNode(mLastLocationData.longitude, mLastLocationData.latitude, mAddrStr, null, coType);
        eNode = new BNRoutePlanNode(mDastLocationData.longitude, mDastLocationData.latitude, mToAddress, null, coType);


        List<BNRoutePlanNode> list = new ArrayList<>();
        list.add(sNode);
        list.add(eNode);
        //这里参数true为真实导航,false为模拟导航
        BaiduNaviManager.getInstance().launchNavigator(this, list, 1, true, new DemoRoutePlanListener(sNode));

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == authBaseRequestCode) {
            for (int ret : grantResults) {
                if (ret == 0) {
                    continue;
                } else {
                    Toast.makeText(AmbitusHotelActivity.this, "缺少导航基本的权限!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            initNavi();
        } else if (requestCode == authComRequestCode) {
            for (int ret : grantResults) {
                if (ret == 0) {
                    continue;
                }
            }
            routeplanToNavi();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);   //地图开启定位的允许
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();    //开启定位
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);  //不允许定位
        mLocationClient.stop(); //停止定位
    }
}
