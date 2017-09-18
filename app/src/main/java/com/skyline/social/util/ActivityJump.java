package com.skyline.social.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import com.skyline.social.activity.MainActivity;

/**
 * Created by Administrator on 2017/9/18.
 */
public class ActivityJump {

    public static void startMainActivity(Activity activity, Bundle bundle) {
        GlobalFunction.ExitActivity(activity);
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent, bundle);
    }

}
