package com.skyline.social.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import com.skyline.social.activity.DetailActivity;
import com.skyline.social.activity.MainActivity;
import com.skyline.social.activity.MapActivity;
import com.skyline.social.entity.ActivityEntity;

/**
 * Created by Administrator on 2017/9/18.
 */
public class ActivityJump {

    public static void startMainActivity(Activity activity, Bundle bundle) {
        GlobalFunction.ExitActivity(activity);
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent, bundle);
    }

    public static void startDetailActivity(Activity activity, Bundle bundle, ActivityEntity entity) {
        GlobalFunction.ExitActivity(activity);
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(GloablDefine.INTENT_DATA_KEY, entity);
        activity.startActivity(intent, bundle);
    }

    public static void startMapActivity(Activity activity) {
        GlobalFunction.ExitActivity(activity);
        Intent intent = new Intent(activity, MapActivity.class);
        activity.startActivity(intent);
    }

}
