package com.skyline.social.activity;

import android.app.ActivityOptions;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skyline.social.R;
import com.skyline.social.base.BaseActivity;
import com.skyline.social.custom.CircleAvatar;
import com.skyline.social.custom.swiperecyclerview.SwipeRecyclerView;
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
    //    private RecyclerView list_view;
//    private SwipeRefreshLayout swipe_view;
    private MainActivity.activityAdapter socialAdapter;
    private ArrayList<ActivityEntity> data = new ArrayList<>();
    private int[] resIds = {R.mipmap.p1, R.mipmap.p1_1, R.mipmap.p1_2, R.mipmap.p1_3, R.mipmap.p2, R.mipmap.p2_1, R.mipmap.p2_2, R.mipmap.p2_3, R.mipmap.p3, R.mipmap.p4};
    private static int MARGIN = DisplayUtils.dp2px(12);
    private static int MARGIN_TOP = DisplayUtils.dp2px(7);
    private static int MARGIN_BOTTOM = DisplayUtils.dp2px(5);
    private SwipeRecyclerView list_view;

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

        list_view = (SwipeRecyclerView) findViewById(R.id.swipe_recycler_view);
        list_view.getSwipeRefreshLayout().setProgressBackgroundColorSchemeResource(R.color.main);
        list_view.getSwipeRefreshLayout().setColorSchemeResources(android.R.color.white);
        list_view.getRecyclerView().setLayoutManager(new LinearLayoutManager(mActivity));
        list_view.getRecyclerView().setItemAnimator(new DefaultItemAnimator());
        list_view.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData(false);
                        list_view.complete();
                        socialAdapter.notifyDataSetChanged();
                        Toast.makeText(mActivity, "刷新成功", Toast.LENGTH_LONG);
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData(true);
                        if (data.size() > 60) {
                            list_view.onNoMore("------------------------我是有底线的------------------------");
                        } else {
                            list_view.stopLoadingMore();
                            socialAdapter.notifyDataSetChanged();
                        }
                    }
                }, 1000);

            }
        });
        socialAdapter = new activityAdapter();
        list_view.setAdapter(socialAdapter);
        initData(false);
    }

    private void initData(boolean bApendData) {
        if (!bApendData) {
            data.clear();
        }
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

                    Pair<View, String> img = Pair.create(holder.root.findViewById(R.id.img), "img");
                    Pair<View, String> title = Pair.create(holder.root.findViewById(R.id.info_root), "title");
                    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(mActivity, img, title).toBundle();
//
//                    Bundle bundle= ActivityOptions.makeSceneTransitionAnimation(mActivity,holder.img, "img").toBundle();
                    ActivityJump.startDetailActivity(mActivity, bundle, item);
                }
            });
        }


        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            LinearLayout info_root;
            RelativeLayout item_root;
            ImageView img;
            TextView title;
            TextView time;
            View root;

            public ViewHolder(View itemView) {
                super(itemView);
                root = itemView;
                img = itemView.findViewById(R.id.img);
                title = itemView.findViewById(R.id.title);
                time = itemView.findViewById(R.id.time);
                item_root = itemView.findViewById(R.id.item_root);
                info_root = itemView.findViewById(R.id.info_root);
            }
        }
    }
}
