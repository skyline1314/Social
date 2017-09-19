package com.skyline.social.activity;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skyline.social.R;
import com.skyline.social.base.BaseActivity;
import com.skyline.social.entity.ActivityEntity;
import com.skyline.social.util.GloablDefine;
import com.skyline.social.util.GlobalFunction;

import java.io.Serializable;

public class DetailActivity extends BaseActivity {

    private RelativeLayout back_btn;
    private TextView title_text;
    private ImageView img;
    private ImageView fav_btn;
    private LinearLayout line_root;
    private ActivityEntity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setEnterTransition(new Fade().setDuration(1000));
        getWindow().setExitTransition(null);
        setContentView(R.layout.activity_detail);
        data = (ActivityEntity) getIntent().getSerializableExtra(GloablDefine.INTENT_DATA_KEY);
        if (data == null) {
            finish();
        }
        initView();
    }

    @Override
    public void doResume() {

    }

    private void initView() {
        back_btn = (RelativeLayout) findViewById(R.id.back_btn);
        title_text = (TextView) findViewById(R.id.title_text);
        img = (ImageView) findViewById(R.id.img);
        fav_btn = (ImageView) findViewById(R.id.fav_btn);
        line_root = (LinearLayout) findViewById(R.id.line_root);

        title_text.setText("活动详情");
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAfterTransition();
            }
        });
        GlobalFunction.display(mActivity, data.getUrl(), img);
        initChildView();
    }

    private void initChildView() {
        line_root.removeAllViews();
        for (int i = 0; i <9; i++) {
            View view = getLayoutInflater().inflate(R.layout.listarray_info_item, null);
            TextView header_text = view.findViewById(R.id.header_text);
            TextView content_text = view.findViewById(R.id.content_text);
            View divider = view.findViewById(R.id.divider);
            if (i == 3) {
                divider.setVisibility(View.GONE);
            } else {
                divider.setVisibility(View.VISIBLE);
            }
            line_root.addView(view);
        }
    }
}
