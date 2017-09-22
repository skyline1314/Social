package com.skyline.social.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.skyline.social.base.App;

/**
 * Created by Administrator on 2017/9/21.
 */
public class SharePreferenceHelper {
    private final static String KEY = "SOCIAL";

    public static void set(String key, Object content) {
        String target = "";
        if (content instanceof String) {
            target = (String) content;
        } else {
            target = JSON.toJSONString(content);
        }
        SharedPreferences preferences = App.app.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, target);
        edit.commit();
    }

    public static String get(String key) {
        SharedPreferences preferences = App.app.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        String result = preferences.getString(key, "");
        return result;
    }

    public static void clearAll() {
        SharedPreferences preferences = App.app.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.clear();
        edit.commit();
    }

}
