package com.sky.smarttvandroid.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class FixFocusStandardGSYVideoPlayer extends StandardGSYVideoPlayer {

    public FixFocusStandardGSYVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public FixFocusStandardGSYVideoPlayer(Context context) {
        super(context);
    }

    public FixFocusStandardGSYVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);

        setFocusable(false);
        setFocusableInTouchMode(false);
        setDescendantFocusability(CustomRecyclerView.FOCUS_BLOCK_DESCENDANTS);
    }
}
