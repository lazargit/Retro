package com.shamildev.retro.retroimage.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.shamildev.retro.R;

/**
 * Created by Shamil Lazar on 08.05.2018.
 */

public class RetroMediaView extends RelativeLayout {

    private ImageView imageView;
    private ProgressBar progressBar;
    private boolean mShowFX;
    private int mFXValue;

    public RetroMediaView(Context context) {
        super(context);
        init(null);
    }

    public RetroMediaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init(AttributeSet attrs) {

        inflate(getContext(), R.layout.view_custom_image,this);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RetroImageView);




        this.imageView =  findViewById(R.id.image_custom);
        this.progressBar = findViewById(R.id.progressbar_image);


        if(attrs!= null){
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.RetroImageView,  0, 0);

            try {
                mShowFX = a.getBoolean(R.styleable.RetroImageView_showFX, false);
                mFXValue = a.getInteger(R.styleable.RetroImageView_labelPosition, 0);
            } finally {
                a.recycle();
            }

        }

    }

    public ImageView getImageView() {
        return imageView;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}

