package com.skyline.social.activity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skyline.social.R;
import com.skyline.social.base.BaseActivity;
import com.skyline.social.custom.CircleAvatar;
import com.skyline.social.entity.ActivityEntity;
import com.skyline.social.util.ActivityJump;
import com.skyline.social.util.DisplayUtils;
import com.skyline.social.util.GlobalFunction;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends BaseActivity {

    private CircleAvatar avatar;
    private TextView title;
    private RelativeLayout title_bar;
    private RecyclerView list_view;
    private MainActivity.activityAdapter socialAdapter;
    private ArrayList<ActivityEntity> data = new ArrayList<>();
    private int[] resIds = {R.mipmap.p1, R.mipmap.p1_1, R.mipmap.p1_2, R.mipmap.p1_3, R.mipmap.p2, R.mipmap.p2_1, R.mipmap.p2_2, R.mipmap.p2_3, R.mipmap.p3, R.mipmap.p4};
    private static int MARGIN = DisplayUtils.dp2px(12);
    private static int MARGIN_TOP = DisplayUtils.dp2px(7);
    private static int MARGIN_BOTTOM = DisplayUtils.dp2px(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void doResume() {

    }

    private void initView() {
        avatar = (CircleAvatar) findViewById(R.id.avatar);
        title = (TextView) findViewById(R.id.title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        list_view = (RecyclerView) findViewById(R.id.list_view);
        list_view.setLayoutManager(new LinearLayoutManager(mActivity));
        list_view.setItemAnimator(new DefaultItemAnimator());
        socialAdapter = new activityAdapter();
        list_view.setAdapter(socialAdapter);
        initData();
    }

    private void initData() {

        for (int i = 0; i < 15; i++) {
            ActivityEntity entity = new ActivityEntity();
            entity.setTime("2017-9-18 16:00:00");
            entity.setTitle("军安卫士花园打乒乓球");
            int length = resIds.length;
            Random random = new Random();
            int nextInt = random.nextInt(length - 1);
            entity.setUrl(resIds[nextInt]);
            data.add(entity);
        }
        socialAdapter.notifyDataSetChanged();
    }


    private class activityAdapter extends RecyclerView.Adapter<activityAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.listarray_activity_item, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final ActivityEntity item = data.get(position);
            GlobalFunction.display(mActivity, item.getUrl(), holder.img);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.item_root.getLayoutParams();
            if (position == 0) {
                layoutParams.topMargin = MARGIN;
                layoutParams.bottomMargin = MARGIN_BOTTOM;

            } else if (position == getItemCount() - 1) {
                layoutParams.topMargin = MARGIN_TOP;
                layoutParams.bottomMargin = MARGIN;
            } else {
                layoutParams.topMargin = MARGIN_TOP;
                layoutParams.bottomMargin = MARGIN_BOTTOM;
            }
            layoutParams.leftMargin = MARGIN;
            layoutParams.rightMargin = MARGIN;

            holder.time.setText(item.getTime());
            holder.title.setText(item.getTitle());
            holder.item_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle= ActivityOptions.makeSceneTransitionAnimation(mActivity,holder.img,"img").toBundle();
                    ActivityJump.startDetailActivity(mActivity,bundle,item);
                }
            });
        }


        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout item_root;
            ImageView img;
            TextView title;
            TextView time;

            public ViewHolder(View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.img);
                title = itemView.findViewById(R.id.title);
                time = itemView.findViewById(R.id.time);
                item_root = itemView.findViewById(R.id.item_root);
            }
        }
    }
}
