package cn.houno.houniaolvju.utils;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import cn.houno.houniaolvju.application.MyApplication;

/**
 * 百度地图定位工具类
 * Created by qzc on 2017-05-18.
 */

public class LocationHelper {


    private LocationCallBack callBack;
    private static LocationHelper helper;

    private LocationClient locationClient;
    private BDLocationListener locationListener = new MyBDLocationListener();

    private LocationHelper() {
        //第一步实例化定位核心类
        locationClient = new LocationClient(MyApplication.getInstance(), getLocOption());
        //第二步设置位置变化回调监听
        locationClient.registerLocationListener(locationListener);
    }

    public static LocationHelper getInstance() {
        if (helper == null) {
            helper = new LocationHelper();
        }
        return helper;
    }

    public void start() {
//        第三步开始定位
        locationClient.start();
    }

    //一般会在Activity的OnDestroy方法调用
    public void stop() {
        if (locationClient !=null) {
            locationClient.unRegisterLocationListener(locationListener);
            locationClient.stop();
            locationClient = null;
        }
        //这里置为null 不然第二次进入会报空
        helper=null;
    }

    private LocationClientOption getLocOption() {
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        //设置定位坐标系
        option.setCoorType("bd09ll");
        //重新定位时间间隔
        option.setScanSpan(2*1000);
        //设置是否打开gps
        option.setOpenGps(true);
        //设置定位模式
        option.setLocationNotify(true);
        //是否需要poi结果
        //option.setPoiDistance(1000);
        //option.setPoiExtraInfo(true);
        return option;
    }

    private class MyBDLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (callBack != null&&bdLocation!=null){
                if (bdLocation.getLatitude() == 4.9E-324 || bdLocation.getLongitude() == 4.9E-324) {
                    Log.i("维度bd.getLatitude()", bdLocation.getLatitude() + "");
                    Log.i("经度bd.getLongitude()", bdLocation.getLongitude() + "");
                    return;
                }
                //callBack.callBack(bdLocation.getAddrStr(),bdLocation.getStreetNumber(),bdLocation.getLatitude(),bdLocation.getLongitude(),bdLocation.getDistrict(),bdLocation.getStreet(),bdLocation.getCity(),bdLocation.getProvince());
                callBack.callBack(bdLocation);
            }
            //多次定位必须要调用stop方法
            locationClient.stop();
        }

    }

    public interface LocationCallBack {
        void callBack(BDLocation bdLocation);
    }

    public LocationCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(LocationCallBack callBack) {
        this.callBack = callBack;
    }
}
