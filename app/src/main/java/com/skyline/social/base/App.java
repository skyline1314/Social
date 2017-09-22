package com.skyline.social.base;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Administrator on 2017/9/18.
 */
public class App extends Application {
    public static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        init();
    }

    private void init() {
//        AVOSCloud.initialize(this, "y07iSxOplkEdiKD1ohQhlSyL-gzGzoHsz", "QmV19Ueljbbh2jqfK1lQ5fNr");
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}
