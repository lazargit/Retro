package com.shamildev.retro.views;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.retroimage.core.RetroImage;
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


    private ViewPager mPager;
    private ArrayList<MediaItem> mediaItemArrayList = new ArrayList<>();
    private ProgressBar mProgessBar;
    private CircleIndicator mCircleIndicator;
    private RetroImage retroImage;

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

    public SearchResultRecycleViewAdapter getAdapter() {
        return adapter;
    }

    public int getCurrentPage() { return currentPage;}

    public ImageSliderView(Context context) {
        super(context);
        init();
    }



    public ImageSliderView(Context context, @android.support.annotation.Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init() {

        inflate(getContext(), R.layout.view_image_slider,this);

        mPager =  findViewById(R.id.pager_image_slider);
        mCircleIndicator =  findViewById(R.id.indicator);
        mProgessBar =  findViewById(R.id.progressBar);
    }




    public void startSlide(List<MediaItem> itemList, RetroImage retroImage) {


          this.retroImage = retroImage;
          mPager.setAdapter(new ImageSliderAdapter(getContext(),itemList));
          mPager.addOnPageChangeListener(viewPagerPageChangeListener);
          mCircleIndicator.setViewPager(mPager);
//        ParallaxPageTransformer pageTransformer = new ParallaxPageTransformer()
//                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.fab_watchlist, 6.3f, 6.2f))
//                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.txtView_title, 6.0f, 6.0f))
//
//                ;

      //  viewpager_slideshow.setPageTransformer(true, pageTransformer);



//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == itemList.size()) {
                    currentPage = 0;
                }

                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);


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
            Log.e("IMAGESLIDER","instantiateItem");
            View myImageLayout = inflater.inflate(R.layout.view_image_slider_page, view, false);
            ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image_imgpageslider);
            view.addView(myImageLayout, 0);
            Log.e("IMAGESLIDER",">> "+myImage.getTag());



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
          //  myImage.setImageResource(images.get(position));

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
           // Log.e("Position:","# "+position);
            //currentPage = position;
           // presenter.toPosition(position);


        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
         //   Log.e("onPageScrolled:","# "+arg0+" # "+arg1+" # "+arg2);
            currentPage=arg0;
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {


            //currentPage=arg0;
           // Log.e("onPScrollStateChanged",currentPage+"# "+arg0);
        }
    };




}
