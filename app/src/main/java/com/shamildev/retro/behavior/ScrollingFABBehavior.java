package com.shamildev.retro.behavior;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Shamil Lazar on 29.04.2018.
 */

public class ScrollingFABBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private static final String TAG = "ScrollingFABBehavior";
    Handler mHandler;

    public ScrollingFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout,
                                       final FloatingActionButton child,
                                       final View directTargetChild, final View target,
                                       final int nestedScrollAxes) {
        // Ensure we react to vertical scrolling
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
                               int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);
        Log.e("FAB",">>>>"+dyConsumed);
        if (dyConsumed > 0
                && child.getVisibility() == View.VISIBLE) {
            child.hide();
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            for (int i = 0; i < coordinatorLayout.getChildCount(); i++) {
                if (coordinatorLayout.getChildAt(i) instanceof Snackbar.SnackbarLayout) {
                    //child.show();
                    return;
                }
            }

            child.setTranslationY(0.0f);
            child.show();
        }
    }








//    @Override
//    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull final FloatingActionButton child, @NonNull View target, int type) {
//        super.onStopNestedScroll(coordinatorLayout, child, target, type);
//        if (mHandler == null)
//            mHandler = new Handler();
//
//
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                child.animate().translationY(0).setInterpolator(new LinearInterpolator()).start();
//                Log.d("FabAnim", "startHandler()");
//            }
//        }, 1000);
//    }
//
//    @Override
//    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
//        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
//        if (dyConsumed > 0) {
//            Log.d("Scrolling", "Up");
//            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//            int fab_bottomMargin = layoutParams.bottomMargin;
//            child.animate().translationY(child.getHeight() + fab_bottomMargin).setInterpolator(new LinearInterpolator()).start();
//        } else if (dyConsumed < 0) {
//            Log.d("Scrolling", "down");
//            child.animate().translationY(0).setInterpolator(new LinearInterpolator()).start();
//        }
//    }
//
//    @Override
//    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
//        if (mHandler != null) {
//            mHandler.removeMessages(0);
//            Log.d("Scrolling", "stopHandler()");
//        }
//        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
//    }

}
