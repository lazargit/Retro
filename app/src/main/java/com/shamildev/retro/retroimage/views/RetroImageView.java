package com.shamildev.retro.retroimage.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.shamildev.retro.R;

/**
 * Created by Shamil Lazar on 08.05.2018.
 */

public class RetroImageView extends RelativeLayout {

    private ImageView imageView;
    private ProgressBar progressBar;



    private boolean mShowFX;
    private int mFXValue;
    private float mFXduartion;
    private float mFXinterpolator;
    private int scaleType;



    private  boolean mShowProgressBar;


    public RetroImageView(Context context) {
        super(context);
        init(null);
    }

    public RetroImageView(Context context, Boolean mShowFX) {
        super(context);
        this.mShowFX = mShowFX;
        init(null);
    }
    public RetroImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.view_custom_image,this);

        if(attrs!= null){
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.RetroImageView,  0, 0);

            try {
                mShowFX = a.getBoolean(R.styleable.RetroImageView_showFX, false);
                mShowProgressBar = a.getBoolean(R.styleable.RetroImageView_showProgressBar, true);
                scaleType = a.getInteger(R.styleable.RetroImageView_scaleType,0);





            } finally {
                a.recycle();
            }


            Log.e("mShowProgressBar","mShowProgressBar "+mShowProgressBar);
            if(mShowFX){
                this.imageView =  findViewById(R.id.image_customFX);
                this.imageView.setVisibility(VISIBLE);
                findViewById(R.id.image_custom).setVisibility(GONE);
            }else{
                this.imageView =  findViewById(R.id.image_custom);
            }




            this.imageView.setScaleType(this.scaleType(scaleType));


        }

    }


    /*
      <enum name="CENTER" value="0"/>
            <enum name="CENTER_CROP" value="1"/>
            <enum name="CENTER_INSIDE" value="2"/>
            <enum name="FIT_CENTER" value="3"/>
            <enum name="FIT_END" value="4"/>
            <enum name="FIT_START" value="5"/>
            <enum name="FIT_XY" value="6"/>
            <enum name="MATRIY" value="7"/>
     */
    private ImageView.ScaleType scaleType(int scaleType) {

        switch (scaleType){
            case 0: return ImageView.ScaleType.CENTER;
            case 1: return ImageView.ScaleType.CENTER_CROP;
            case 2: return ImageView.ScaleType.CENTER_INSIDE;
            case 3: return ImageView.ScaleType.FIT_CENTER;
            case 4: return ImageView.ScaleType.FIT_END;
            case 5: return ImageView.ScaleType.FIT_START;
            case 6: return ImageView.ScaleType.FIT_XY;
            case 7: return ImageView.ScaleType.MATRIX;
            default: return ImageView.ScaleType.CENTER;

        }

    }

    public ImageView getImageView() {
        return imageView;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
    public void setmShowFX(boolean mShowFX) {
        this.mShowFX = mShowFX;
    }
    public boolean isShowProgressBar() {
        return mShowProgressBar;
    }

}

