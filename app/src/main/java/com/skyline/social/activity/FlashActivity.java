package com.skyline.social.activity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.skyline.social.R;
import com.skyline.social.base.BaseActivity;
import com.skyline.social.custom.CircleAvatar;
import com.skyline.social.custom.RadarView;
import com.skyline.social.custom.RadarViewGroup;
import com.skyline.social.entity.UserEntity;
import com.skyline.social.util.ActivityJump;
import com.skyline.social.util.GloablDefine;
import com.skyline.social.util.GlobalFunction;
import com.skyline.social.util.SharePreferenceHelper;

public class FlashActivity extends BaseActivity {
    private TextView title;
    private RadarView radar_view;
    private RadarViewGroup radar_viewgroup;
    private CircleAvatar avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        initView();
    }

    private void initView() {
        radar_view = (RadarView) findViewById(R.id.radar_view);
        avatar = (CircleAvatar) findViewById(R.id.avatar);
        String user = SharePreferenceHelper.get(GloablDefine.USER_KEY);
        final UserEntity entity;
        if (TextUtils.isEmpty(user)) {
            entity = null;
        } else {
            entity = (UserEntity) GlobalFunction.parse(user, UserEntity.class);
        }
        if (entity != null && !entity.getAvater_url().equals("")) {
            GlobalFunction.display(mActivity, entity.getAvater_url(), avatar);
        }

        avatar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (entity == null) {
                    ActivityJump.startMapActivity(mActivity);
                } else {
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(mActivity, avatar, "avatar").toBundle();
                    ActivityJump.startMainActivity(mActivity, bundle);
                    finish();
                }
            }
        }, 3000);


    }

    @Override
    public void doResume() {

    }

}
