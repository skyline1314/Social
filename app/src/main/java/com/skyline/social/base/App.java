package com.skyline.social.base;

import android.app.Application;

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
    }
}
