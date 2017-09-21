package com.skyline.social.custom;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.skyline.social.R;

/**
 * SwipeRefreshLayout 和 RecyclerView 的封装
 * Created by Administrator on 2017/9/21.
 */
public class SwipeRecyclerView extends FrameLayout {

    private SwipeRefreshLayout swipe_view;
    private RecyclerView recycler_view;

    public SwipeRecyclerView(Context context) {
        super(context);
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_layout, this);
        swipe_view = view.findViewById(R.id.swipe_view);
        recycler_view = view.findViewById(R.id.recycler_view);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipe_view;
    }

    public RecyclerView getRecyclerView() {
        return recycler_view;
    }
}
