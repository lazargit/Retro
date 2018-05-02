package com.shamildev.retro.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.shamildev.retro.util.DeviceUtils;


/**
 * Created by Shamil Lazar on 30.04.2018.
 */

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    private static final String TAG = "ScrollAwareFABBehavior";



    public boolean onStartNestedScroll(CoordinatorLayout parent, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    private int toolbarHeight;

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
        this.toolbarHeight = DeviceUtils.getToolbarHeight(context);
    }

//    @Override
//    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
//        return super.layoutDependsOn(parent, fab, dependency) || (dependency instanceof AppBarLayout);
//    }
//
//    @Override
//    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
//        boolean returnValue = super.onDependentViewChanged(parent, fab, dependency);
//        if (dependency instanceof AppBarLayout) {
//            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
//            int fabBottomMargin = lp.bottomMargin;
//            int distanceToScroll = fab.getHeight() + fabBottomMargin;
//            int totalScrollRange = ((AppBarLayout) dependency).getTotalScrollRange();
//
//            float ratio = (float)dependency.getY()/(((float)toolbarHeight));
//            Log.e("FAB",">>>>"+ratio+"--"+dependency+"::::"+dependency.getY()+"---"+dependency.getTranslationY());
////            if(dependency.getY()<0){
////                fab.hide();
////            }else{
////                fab.show();
////            }
//            //float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
//           // fab.setTranslationY(translationY);
//          // fab.setTranslationY(-distanceToScroll * ratio);
//        }
//        return returnValue;
//    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                dyUnconsumed);

        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            child.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                @Override
                public void onHidden(FloatingActionButton fab) {
                    super.onHidden(fab);
                    fab.setVisibility(View.INVISIBLE);
                }
            });
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            child.show();
        }
    }
}
