package com.shamildev.retro.views.retroslider.views;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.views.RetroImageView;
import com.shamildev.retro.ui.widgets.Search.SearchResultRecycleViewAdapter;
import com.shamildev.retro.ui.widgets.Search.SearchViewWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Unbinder;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Shamil Lazar .
 */

public class ImageSliderView extends RelativeLayout {


    private static final int FX_SLIDE = 0;
    private static final int FX_FADEOUT = 1;
    private static final int FX_ZOOMOUT = 2;
    private static final int FX_POP = 3;
    private ViewPager mPager;
    private ArrayList<MediaItem> mediaItemArrayList = new ArrayList<>();
    private ProgressBar mProgessBar;
    private CircleIndicator mCircleIndicator;
    private RetroImage retroImage;
    private Animator pagerAnimation;
    private boolean DIRECTION = true;
    private boolean TOUCH = false;
    private boolean mShowImageFX;
    private boolean mShowTitle;
    private boolean mShowCircleIndicator;
    private int mPagefx;
    private long DURATION = 800;

    public interface Listener {
        void onNextPage();


    }




    private RecyclerView recyclerView;
    private SearchViewWidget.ClickListener clickListener;


    public RecyclerView getRecyclerView() {return recyclerView;}





    private Unbinder butterKnifeUnbinder;
    private SearchResultRecycleViewAdapter adapter;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 20;
    private int TOTAL_RESULT = 0;
    private int currentPage = PAGE_START;

    public int getCurrentPage() { return currentPage;}




    public ImageSliderView(Context context) {
        super(context);
        init(null);
    }

    public ImageSliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ImageSliderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ImageSliderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }





    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init(AttributeSet attrs) {
        handleTypedArray(getContext(), attrs);
        inflate(getContext(), R.layout.view_image_slider,this);

        mPager =  findViewById(R.id.pager_image_slider);
        mCircleIndicator =  findViewById(R.id.indicator);
        mProgessBar =  findViewById(R.id.progressBar);
        if(!mShowCircleIndicator){
            mCircleIndicator.setVisibility(GONE);
        }
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        else{
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.ImageSliderView,  0, 0);
            try {

                mShowImageFX =
                        typedArray.getBoolean(R.styleable.ImageSliderView_showImageFX, true);
                mShowTitle =
                        typedArray.getBoolean(R.styleable.ImageSliderView_showTitle, true);
                mShowCircleIndicator =
                        typedArray.getBoolean(R.styleable.ImageSliderView_showCircleIndicator, true);
                mPagefx = typedArray.getInt(R.styleable.ImageSliderView_pagefx, 0);

            } finally {
                typedArray.recycle();
            }

        }

    }


    public void startSlide(List<MediaItem> itemList, RetroImage retroImage) {


          this.retroImage = retroImage;

        retroImage.load(itemList)
                .Backdrop()
                .w780()
                .preload(new RetroImageRequestListener() {
                    @Override
                    public boolean onLoadFailed() {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady() {
                        setPager(itemList);
                        return false;
                    }
                });



//        ParallaxPageTransformer pageTransformer = new ParallaxPageTransformer()
//                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.fab_watchlist, 6.3f, 6.2f))
//                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.txtView_title, 6.0f, 6.0f))
//
//                ;

      //  viewpager_slideshow.setPageTransformer(true, pageTransformer);



//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(mPager);




    }
    private int oldDragPosition = 0;

    private void setPager(List<MediaItem> itemList){
         mPager.setAdapter(new ImageSliderAdapter(getContext(),itemList));


        if(mPagefx == FX_FADEOUT){
            mPager.setPageTransformer(false, new FadeOutTransformation());
        }
        if(mPagefx == FX_ZOOMOUT){
            mPager.setPageTransformer(false, new ZoomOutTransformation());
        }
        if(mPagefx == FX_POP){
            mPager.setPageTransformer(false, new PopTransformation());
        }


        // mPager.setPageTransformer(false, new FadeOutTransformation());

         mPager.addOnPageChangeListener(viewPagerPageChangeListener);




         mPager.setOnTouchListener((view, motionEvent) -> {
             switch(motionEvent.getAction()){
                 case MotionEvent.ACTION_MOVE:
                     Log.e("ACTION","ACTION_MOVE");
                     return false; //This is important, if you return TRUE the action of swipe will not take place.
                 case MotionEvent.ACTION_DOWN:
                     TOUCH = true;
                     Log.e("ACTION","ACTION_DOWN");
                     break;
                 case MotionEvent.ACTION_UP:
                     TOUCH = false;
                     Log.e("ACTION","ACTION_UP");
                     break;
             }
             return false;


         });








         mCircleIndicator.setViewPager(mPager);


        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if(!TOUCH) {
                    animatePagerTransition(DIRECTION, itemList.size());
                }

            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(Update);
            }
        }, 5000, 5000);



    }




    private void animatePagerTransition(final boolean forward, int pageCount) {
        // if previous animation have not finished we can get exception
        if (pagerAnimation != null) {
            pagerAnimation.cancel();
        }
        pagerAnimation = getPagerTransitionAnimation(forward, pageCount);
        if (mPager.beginFakeDrag()) {    // checking that started drag correctly
            pagerAnimation.start();
        }
    }

    private Animator getPagerTransitionAnimation(final boolean forward, int pageCount) {
        ValueAnimator animator = ValueAnimator.ofInt(0, mPager.getWidth() - 1);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("TAG","onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                oldDragPosition = 0;
                if(((mPager.getCurrentItem()+1)>=pageCount) && DIRECTION)  {
                    DIRECTION = !DIRECTION;
                }
                if((mPager.getCurrentItem()==0) && !DIRECTION)  {
                    DIRECTION = !DIRECTION;
                }
                Log.e("TAG",DIRECTION+" onAnimationEnd "+mPager.getCurrentItem());
                mPager.endFakeDrag();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                oldDragPosition = 0;


                mPager.endFakeDrag();

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                mPager.endFakeDrag();
                oldDragPosition = 0;
                mPager.beginFakeDrag();
            }
        });

        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dragPosition = (Integer) animation.getAnimatedValue();
                int dragOffset = dragPosition - oldDragPosition;
                oldDragPosition = dragPosition;
                mPager.fakeDragBy(dragOffset * (forward ? -1 : 1));
            }
        });

        animator.setDuration(DURATION); // remove divider if you want to make each transition have the same speed as single page transition
        //animator.setRepeatMode(0);
        Log.e("TAG","oldDragPosition "+oldDragPosition);
        return animator;
    }


    public class FadeOutTransformation implements ViewPager.PageTransformer{
        @Override
        public void transformPage(View page, float position) {

            page.setTranslationX(-position*page.getWidth());

            page.setAlpha(1-Math.abs(position));


        }
    }
    public class ZoomOutTransformation implements ViewPager.PageTransformer {

        private static final float MIN_SCALE = 0.65f;
        private static final float MIN_ALPHA = 0.3f;

        @Override
        public void transformPage(View page, float position) {

            if (position <-1){  // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setAlpha(0);

            }
            else if (position <=1){ // [-1,1]

                page.setScaleX(Math.max(MIN_SCALE,1-Math.abs(position)));
                page.setScaleY(Math.max(MIN_SCALE,1-Math.abs(position)));
                page.setAlpha(Math.max(MIN_ALPHA,1-Math.abs(position)));

            }
            else {  // (1,+Infinity]
                // This page is way off-screen to the right.
                page.setAlpha(0);

            }


        }
    }
    public class PopTransformation implements ViewPager.PageTransformer {
        @Override
        public void transformPage(View page, float position) {

            page.setTranslationX(-position * page.getWidth());

            if (Math.abs(position) < 0.5) {
                page.setVisibility(View.VISIBLE);
                page.setScaleX(1 - Math.abs(position));
                page.setScaleY(1 - Math.abs(position));
            } else if (Math.abs(position) > 0.5) {
                page.setVisibility(View.GONE);
            }


        }
    }

    public class ImageSliderAdapter extends PagerAdapter {

        private List<MediaItem> images;
        private LayoutInflater inflater;
        private Context context;

        public ImageSliderAdapter(Context context, List<MediaItem> images) {
            this.context = context;
            this.images = images;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return images.size();
        }



        @Override
        public Object instantiateItem(ViewGroup view, int position) {

            View myImageLayout = inflater.inflate(R.layout.view_image_slider_page, view, false);
         //   RetroImageView myImage =  myImageLayout.findViewById(R.id.image_imgpageslider);

            FrameLayout view_image_slider_page = myImageLayout.findViewById(R.id.view_image_slider_page);


            RetroImageView retroImageView = new RetroImageView(getContext(),mShowImageFX);
            retroImageView.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT) );

            view_image_slider_page.addView(retroImageView);
            view.addView(myImageLayout, 0);



            retroImage.load(images.get(position))
                    .Backdrop()
                    .w780()
                    .into(retroImageView,new RetroImageRequestListener() {
                        @Override
                        public boolean onLoadFailed() {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady() {

                            return false;
                        }
                    });

//            retroImage
//                    .load(images.get(position))
//                    .listener(new ProcessImageHelper.ProcessImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            Log.e("TAG","IMAGES LOADING FAILED");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","IMAGE 4 LOADED");
//                            myImage.setTag("load");
//
//                            return false;
//
//                        }
//                    })
//                    .backdrop()
//                    .high()
//                    .into(myImage);

         // myImage.setImageResource(images.get(position));

            return myImageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

    }




    //  page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            // displayMetaInfo(position);
         // Log.e("PageChangeListener","< "+position);
            //currentPage = position;
           // presenter.toPosition(position);


        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
           //Log.e("onPageScrolled:","# "+arg0+" # "+arg1+" # "+arg2);
            currentPage=arg0;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {


            //currentPage=arg0;
           // Log.e("PageChangeListener","> "+arg0);
        }
    };




}
