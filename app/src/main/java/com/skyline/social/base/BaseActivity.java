package com.skyline.social.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by Administrator on 2017/9/15.
 */
public abstract class BaseActivity extends Activity {

    public static Activity mActivity;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mActivity = this;
    }

    public abstract void doResume();

    @Override
    protected void onResume() {
        super.onResume();
        doResume();
        mActivity = this;
    }
}
