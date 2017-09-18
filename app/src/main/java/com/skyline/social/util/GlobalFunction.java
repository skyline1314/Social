package com.skyline.social.util;

import android.app.Activity;
import android.content.Context;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.skyline.social.R;

/**
 * Created by Administrator on 2017/9/18.
 */
public class GlobalFunction {

    public static void display(Context context, Object url, ImageView view){
        Glide.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade())
                .apply(new RequestOptions().placeholder(R.mipmap.picdefault))
                .into(view);
    }

    public static void ExitActivity(Activity activity){
        Transition transition = new ChangeTransform();
        transition.setDuration(2000);
        activity.getWindow().setExitTransition(transition);
    }

}
