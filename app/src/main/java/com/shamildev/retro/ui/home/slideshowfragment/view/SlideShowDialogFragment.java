package com.shamildev.retro.ui.home.slideshowfragment.view;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.view.BaseViewDialogFragment;
import com.shamildev.retro.ui.home.fragment.view.HomePageFragment;
import com.shamildev.retro.ui.home.slideshowfragment.adapter.ParallaxPageTransformer;
import com.shamildev.retro.ui.home.slideshowfragment.adapter.SlideShowPageAdapter;
import com.shamildev.retro.ui.home.slideshowfragment.presenter.SlideShowPresenter;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class SlideShowDialogFragment extends BaseViewDialogFragment<SlideShowPresenter> implements SlideShowView {

    private static final String PARAM_ITEMS = "param_items_slideshow";
    private static final String PARAM_POS = "param_items_slideshow_pos";
   // private final RequestManager requestManager;
    OnHeadlineSelectedListener mCallback;



    @Inject
    AppConfig appConfig;


    @Inject
    Application application;

    @Inject
    Navigator navigator;


    @BindView(R.id.viewpager_slideshow)
    ViewPager viewpager_slideshow;


    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }


    public SlideShowDialogFragment() {

            setRetainInstance(true);
           // this.requestManager = Glide.with(this);

            Timber.d("HomeFragment");


    }

    public void addCallBack(HomePageFragment homePageFragment){
        mCallback = homePageFragment;
    }

    public static SlideShowDialogFragment with() {
        final SlideShowDialogFragment slideShowDialogFragment = new SlideShowDialogFragment();
        return slideShowDialogFragment;
    }
    public static SlideShowDialogFragment withMovies(ResultWrapper resultWrapper, int adapterPosition) {
        final SlideShowDialogFragment homeFragment = new SlideShowDialogFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(PARAM_ITEMS, resultWrapper);
        arguments.putSerializable(PARAM_POS, adapterPosition);
        homeFragment.setArguments(arguments);
        return homeFragment;
    }
    private Pair<ResultWrapper,Integer> getBundleData() {
        final Bundle arguments = getArguments();
        ResultWrapper resultWrapper = (ResultWrapper) arguments.getSerializable(PARAM_ITEMS);
        Integer pos= (Integer) arguments.getSerializable(PARAM_POS);

        return new Pair<>(resultWrapper,pos);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

//        try {
//            mCallback = (OnHeadlineSelectedListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " must implement OnHeadlineSelectedListener");
//        }



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_slideshow, container, false);
        Unbinder butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);

//        ProcessImageHelper processImageHelper = new ProcessImageHelper(requestManager,
//                appConfig.getConfigurations(),
//                appConfig.getScreenSize());


        presenter.init(getBundleData(),this,getActivity(),  getTag(),null);
        return fragmentView;

    }





    @Override
    public void initViewPager(SlideShowPageAdapter myViewPagerAdapter, Integer pos) {


        viewpager_slideshow.setAdapter(myViewPagerAdapter);
        viewpager_slideshow.setCurrentItem(pos);

        viewpager_slideshow.addOnPageChangeListener(viewPagerPageChangeListener);

        ParallaxPageTransformer pageTransformer = new ParallaxPageTransformer()
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.fab_watchlist, 6.3f, 6.2f))
                .addViewToParallax(new ParallaxPageTransformer.ParallaxTransformInformation(R.id.txtView_title, 6.0f, 6.0f))

                ;

        viewpager_slideshow.setPageTransformer(true, pageTransformer);
        Log.e("parent",">"+getParentFragment().getClass().getName());
    }

    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCallback.onArticleSelected(5);

    }

    //  page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
           // displayMetaInfo(position);
            Log.e("Position:","# "+position);
            presenter.toPosition(position);


        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            Log.e("onPageScrolled:","# "+arg0+" # "+arg1+" # "+arg2);
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            Log.e("onPScrollStateChanged","# "+arg0);
        }
    };
}