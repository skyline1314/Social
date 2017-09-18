package com.skyline.social.activity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.widget.TextView;

import com.skyline.social.R;
import com.skyline.social.base.BaseActivity;
import com.skyline.social.custom.CircleAvatar;
import com.skyline.social.custom.RadarView;
import com.skyline.social.custom.RadarViewGroup;
import com.skyline.social.util.ActivityJump;

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
        radar_view.setScanningListener(new RadarView.IScanningListener() {
            @Override
            public void onScanning(int position, float scanAngle) {
                if (position == 385) {
                    Bundle bundle= ActivityOptions.makeSceneTransitionAnimation(mActivity,avatar,"avatar").toBundle();
                    ActivityJump.startMainActivity(mActivity,bundle);
                    finish();
                }
            }

            @Override
            public void onScanSuccess() {

            }
        });
    }

    @Override
    public void doResume() {

    }


}
