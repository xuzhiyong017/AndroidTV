package com.sky.smarttvandroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class CenterLayoutManager extends LinearLayoutManager {

    public CenterLayoutManager(Context context) {
        super(context);

    }

    public CenterLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CenterLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     *
     * @param recyclerView 目标recyclerView
     * @param state new RecyclerView.State()
     * @param position 需要滑动的item的position
     */
    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        CenterSmoothScroll centerSmoothScroll = new CenterSmoothScroll(recyclerView.getContext());
        centerSmoothScroll.setTargetPosition(position);
        startSmoothScroll(centerSmoothScroll);
    }

    private class CenterSmoothScroll extends LinearSmoothScroller {

        public CenterSmoothScroll(Context context) {
            super(context);
        }
        //RecyclerView的中心点和item的中心点的相差即item需要移动的距离和方向
        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
        }
        //计算每个像素滑动的速度
        @Override
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 100f/displayMetrics.densityDpi;
        }
    }


    @Override
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                Log.d("CenterLayout","onChildViewAttachedToWindow");
                view.requestFocus();
                recyclerView.removeOnChildAttachStateChangeListener(this);
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                Log.d("CenterLayout","onChildViewDetachedFromWindow");
            }
        });
    }
}
