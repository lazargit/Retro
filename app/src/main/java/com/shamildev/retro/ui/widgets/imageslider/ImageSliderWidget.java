package com.shamildev.retro.ui.widgets.imageslider;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.shamildev.retro.R;

/**
 * Created by Shamil Lazar on 10.05.2018.
 */

public class ImageSliderWidget extends RelativeLayout {


    public ImageSliderWidget(Context context) {
        super(context);
        init();
    }

    public ImageSliderWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init() {

        inflate(getContext(), R.layout.view_image_slider,this);


    }

}
