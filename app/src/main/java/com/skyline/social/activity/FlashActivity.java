package com.skyline.social.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.skyline.social.R;
import com.skyline.social.custom.RadarView;
import com.skyline.social.custom.RadarViewGroup;

public class FlashActivity extends AppCompatActivity {
    private TextView title;
    private RadarView radar_view;
    private RadarViewGroup radar_viewgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        initView();
    }

    private void initView() {
        radar_view = (RadarView) findViewById(R.id.radar_view);
        radar_view.setScanningListener(new RadarView.IScanningListener() {
            @Override
            public void onScanning(int position, float scanAngle) {
                if (position == 2 && scanAngle == 36.0) {
                    Log.e("smart","res");
                }
            }

            @Override
            public void onScanSuccess() {

            }
        });
    }
}
