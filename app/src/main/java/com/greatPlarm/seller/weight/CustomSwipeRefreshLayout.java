package com.greatPlarm.seller.weight;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/12/23 0023.
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {

    private Runnable action;

    public CustomSwipeRefreshLayout(Context context) {
        super(context);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setRefreshing(final boolean refreshing) {
        action = new Runnable() {
            @Override
            public void run() {
                doRefresh(refreshing);
            }
        };
        if (!refreshing) {
            postDelayed(action,500);
        } else {
            doRefresh(refreshing);
        }
    }

    private void doRefresh(boolean refreshing) {
        super.setRefreshing(refreshing);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(action);
    }
}
