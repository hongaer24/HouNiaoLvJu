package cn.houno.houniaolvju.activity.hotel;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
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
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.navisdk.adapter.BNOuterTTSPlayerCallback;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNaviSettingManager;
import com.baidu.navisdk.adapter.BaiduNaviManager;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.houno.houniaolvju.R;
import cn.houno.houniaolvju.activity.BNDemoGuideActivity;
import cn.houno.houniaolvju.utils.ScreenUtils;
import cn.houno.houniaolvju.utils.StatusBarUtils;
import cn.houno.houniaolvju.view.BorderTextView;

/**
 * 项目名称：HouNiaoLvJu
 * 类描述：酒店地图
 * 创建人：qzc
 * 创建时间：2016/12/30 17:37
 * 修改人：qzc
 * 修改时间：2016/12/30 17:37
 * 修改备注：
 */
public class HotelMapActivity extends Activity {

    @Bind(R.id.iv_navi)
    ImageView mIvNavi;
    @Bind(R.id.iv_loc)
    ImageView mIvLoc;
    private HotelMapActivity mActivity;


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

    //目标地址经纬度
    private double eLatitude;
    private double eLongitude;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_hotel_map);
        ButterKnife.bind(this);
        mActivity = HotelMapActivity.this;
        StatusBarUtils.setWindowStatusBarColor(mActivity, R.color.app_theme_green);

        initView();
        initData();
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
    }

    private void initData() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mToAddress = intent.getStringExtra("address");
        eLatitude = Double.parseDouble(intent.getStringExtra("lat"));
        eLongitude = Double.parseDouble(intent.getStringExtra("lng"));

        mBaiduMap = mMapView.getMap();  //获取地图对象
        MapStatusUpdate msuZoom = MapStatusUpdateFactory.zoomTo(14.0f); //设置比例  1km/1cm
        mBaiduMap.setMapStatus(msuZoom);
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
        //定位到指定位置
        //LatLng latLng = new LatLng(eLatitude, eLongitude);
        mDastLocationData = new LatLng(eLatitude, eLongitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(mDastLocationData);
        mBaiduMap.animateMapStatus(msu);

        //初始化覆盖物
        mMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_hotel_marker);
        //初始化导航相关
        if (initDirs()) {
            initNavi();
        }
        OverlayOptions options = new MarkerOptions().position(mDastLocationData)
                .icon(mMarker).zIndex(5);
        // 在地图上添加Marker，并显示
        mBaiduMap.addOverlay(options);
        showTextWindow(mTitle, eLatitude, eLongitude);
    }

    private BorderTextView text;

    private void showTextWindow(String title, double eLatitude, double eLongitude) {
        text = new BorderTextView(mActivity, "#f00000");
        //  text.setPaintColor(Color.parseColor("#ff0000"));
        //创建InfoWindow展示的view
        text.setBackgroundColor(Color.WHITE);
        text.setGravity(Gravity.CENTER);
        text.setMaxWidth(ScreenUtils.getScreenWidth(mActivity) / 3);
        text.setEllipsize(TextUtils.TruncateAt.END);
        text.setMaxLines(2);
        text.setPadding(5, 5, 5, 5);
        text.setTextSize(12);
        text.setTextColor(Color.parseColor("#505050"));
        text.setText(title);
        //定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(eLatitude, eLongitude);
        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(text, pt, -47);
        //显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);
    }

    /*
     * 导航
     * */
    private void goToHotel() {
        routeplanToNavi();
    }

    private int TAG = HOTEL_LOCATION;
    private static final int MY_LOCATION = 1;
    private static final int HOTEL_LOCATION = 2;

    /*
     * 定位到我的位置
     * */
    private void centerToMyLocation() {
        LatLng latLng;
        if (TAG == HOTEL_LOCATION) {
            latLng = new LatLng(mLatitude, mLongitude);
            TAG = MY_LOCATION;
        } else {
            latLng = new LatLng(eLatitude, eLongitude);
            TAG = HOTEL_LOCATION;
        }
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }

    @OnClick({R.id.iv_back, R.id.iv_navi, R.id.iv_loc})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_navi:
                goToHotel();
                break;
            case R.id.iv_loc:
                centerToMyLocation();
                break;
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
                isFirstLoc = false;
                mAddrStr = bdLocation.getAddrStr();
                if (mAddrStr != null) {
                    //  Toast.makeText(mActivity, "当前位置:\n" + bdLocation.getAddrStr(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mActivity, "请连接网络", Toast.LENGTH_SHORT).show();
                }
                mLocationClient.stop();
            }
        }
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
        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /*
    * 初始化导航
    * */
    private void initNavi() {

        BNOuterTTSPlayerCallback ttsCallback = null;

        // 申请权限
        if (Build.VERSION.SDK_INT >= 23) {

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
                mActivity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //    Toast.makeText(mActivity, authinfo, Toast.LENGTH_LONG).show();
                    }
                });
            }

            public void initSuccess() {
                System.out.println("百度导航引擎初始化成功");
                // Toast.makeText(mActivity, "百度导航引擎初始化成功", Toast.LENGTH_SHORT).show();
                hasInitSuccess = true;
                initSetting();
            }

            public void initStart() {
                System.out.println("百度导航引擎初始化开始");
                //   Toast.makeText(mActivity, "百度导航引擎初始化开始", Toast.LENGTH_SHORT).show();
            }

            public void initFailed() {
                System.out.println("百度导航引擎初始化失败");
                //  Toast.makeText(mActivity, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
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
            Intent intent = new Intent(mActivity, BNDemoGuideActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ROUTE_PLAN_NODE, mBNRoutePlanNode);
            intent.putExtras(bundle);
            startActivity(intent);

        }

        @Override
        public void onRoutePlanFailed() {
            Toast.makeText(mActivity, "算路失败", Toast.LENGTH_SHORT).show();
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
        BNRoutePlanNode.CoordinateType coType = BNRoutePlanNode.CoordinateType.BD09LL;
        if (!hasInitSuccess) {
            Toast.makeText(mActivity, "还未初始化!", Toast.LENGTH_SHORT).show();
        }
        // 权限申请
        if (Build.VERSION.SDK_INT >= 23) {
            // 保证导航功能完备
            if (!hasCompletePhoneAuth()) {
                if (!hasRequestComAuth) {
                    hasRequestComAuth = true;
                    this.requestPermissions(authComArr, authComRequestCode);
                    return;
                } else {
                    Toast.makeText(mActivity, "没有完备的权限!", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(mActivity, "缺少导航基本的权限!", Toast.LENGTH_SHORT).show();
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
        //停止定位
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }
}
