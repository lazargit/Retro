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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.views.RetroImageView;
import com.shamildev.retro.ui.widgets.Search.SearchResultRecycleViewAdapter;
import com.shamildev.retro.ui.widgets.Search.SearchViewWidget;
import com.shamildev.retro.views.retroslider.transformation.FadeOutTransformation;
import com.shamildev.retro.views.retroslider.transformation.PopTransformation;
import com.shamildev.retro.views.retroslider.transformation.ZoomOutTransformation;

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
    private long DURATION = 400;
    private long DELAY = 5000;
    private int oldDragPosition = 0;
    private long PERIOD = 5000;
    private List<MediaItem> itemList;
    private Handler handler;
    private Runnable Update;
    private Timer swipeTimer;

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

          this.itemList = itemList;
          this.retroImage = retroImage;
          this.mProgessBar.setVisibility(VISIBLE);
          retroImage.load(itemList)
                .Backdrop()
                .w1280()
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



        // Auto start of viewpager
        start();


    }


    private void setPager(List<MediaItem> itemList){



         this.mProgessBar.setVisibility(GONE);
         mPager.setClipToPadding(false);
         mPager.setOffscreenPageLimit(itemList.size()-1);
         mPager.setAdapter(new ImageSliderAdapter(getContext(),itemList));
         mCircleIndicator.setViewPager(mPager);

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
                     stop();
                     return false; //This is important, if you return TRUE the action of swipe will not take place.
                 case MotionEvent.ACTION_DOWN:
                     TOUCH = true;
                     Log.e("ACTION","ACTION_DOWN");
                     break;
                 case MotionEvent.ACTION_UP:
                     TOUCH = false;
                     start();
                     Log.e("ACTION","ACTION_UP");
                     break;
             }
             return false;


         });














    }


    private void start(){

        handler = new Handler();
        Update = new Runnable() {
            public void run() {
                if(!TOUCH) {
                    animatePagerTransition(DIRECTION, itemList.size());
                }

            }
        };
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY, PERIOD);
    }

    private void stop(){
        if(swipeTimer!=null){
            swipeTimer.cancel();
        }
        if(handler!=null) {
            handler = null;
        }
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

                if (mPager.isFakeDragging()) mPager.endFakeDrag();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                oldDragPosition = 0;
                if (mPager.isFakeDragging()) mPager.endFakeDrag();

            }
            @Override
            public void onAnimationRepeat(Animator animation) {
                mPager.endFakeDrag();
                oldDragPosition = 0;
                if (mPager.isFakeDragging()) mPager.beginFakeDrag();
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
        return animator;
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

            MediaItem mediaItem = images.get(position);



            View myImageLayout = inflater.inflate(R.layout.view_image_slider_page, view, false);
         //   RetroImageView myImage =  myImageLayout.findViewById(R.id.image_imgpageslider);

            RelativeLayout view_image_slider_page = myImageLayout.findViewById(R.id.view);

            TextView textView = myImageLayout.findViewById(R.id.title);


            RetroImageView retroImageView = new RetroImageView(getContext(),mShowImageFX);
            retroImageView.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT) );

            view_image_slider_page.addView(retroImageView);
            view.addView(myImageLayout, 0);

//            TextView textView = new TextView(getContext());
//            LayoutParams layoutParamsTextView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//
//            layoutParamsTextView.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            textView.setText(mediaItem.itemTitle());
//            textView.setShadowLayer(3, 1, 1,  getResources().getColor(R.color.grey_500));
//            textView.setLayoutParams(layoutParamsTextView);
//
////            textView.setTextSize(16);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                textView.setTextAppearance(R.style.WalkWayBold);
//            }else{
//                textView.setTextAppearance(getContext(),R.style.WalkWayBold);
//            }
//
//
//            view_image_slider_page.addView(textView);
            Log.e("TAG","instantiateItem");



                retroImage.load(images.get(position))
                        .Backdrop()
                        .w1280()
                        .into(retroImageView, new RetroImageRequestListener() {
                            @Override
                            public boolean onLoadFailed() {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady() {

                                return false;
                            }
                        });




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
