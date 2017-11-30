package cn.houno.houniaolvju.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import org.xutils.BuildConfig;
import org.xutils.x;

import cn.houno.houniaolvju.utils.Utils;

/**
 * 应用类
 */
public class MyApplication extends Application{


    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null) {
            instance = this;
        }

        x.Ext.init(this);
       // x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        //百度地图初始化
        SDKInitializer.initialize(this);

        Utils.init(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
