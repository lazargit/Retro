package com.shamildev.retro.ui.home.fragment.view;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.transition.TransitionSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.home.fragment.adapter.RecyclerViewPagerAdapter;
import com.shamildev.retro.ui.home.fragment.presenter.HomePagePresenter;
import com.shamildev.retro.ui.home.fragment.presenter.ImageViewPagerPresenter;
import com.shamildev.retro.ui.home.slideshowfragment.adapter.ParallaxPageTransformer;
import com.shamildev.retro.ui.home.slideshowfragment.adapter.SlideShowPageAdapter;
import com.shamildev.retro.ui.home.slideshowfragment.view.SlideShowDialogFragment;
import com.shamildev.retro.ui.layout.PreCachingGridLayoutManager;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;
import com.shamildev.retro.util.DeviceUtils;
import com.shamildev.retro.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.internal.Preconditions;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class ImageViewPagerFragment extends BaseViewFragmentV4<ImageViewPagerPresenter> implements ImageViewPagerView{


    private static final String PARAM_ITEMS = "param_items_slideshow";
    private static final String PARAM_POS = "param_items_slideshow_pos";
    private static final String KEY_IMAGE_RES = "com.shamildev.retro.ui.home.key.imageres";
    // private final RequestManager requestManager;
    SlideShowDialogFragment.OnHeadlineSelectedListener mCallback;



    ArrayList<Movie> movieArrayList = new ArrayList<>();
    ArrayList<DomainObject> itemArrayList = new ArrayList<>();

    private Unbinder butterKnifeUnbinder;
    private PreCachingGridLayoutManager layoutManager;
    private String tag;


    @Inject
    AppConfig appConfig;


    @Inject
    Application application;

    @Inject
    Navigator navigator;


    @BindView(R.id.viewpager_slideshow)
    ViewPager viewpager_slideshow;


    public ImageViewPagerFragment() {
            Log.e("TAG","ImageViewPagerFragment");
            setRetainInstance(true);



    }


    public static ImageViewPagerFragment with() {
        final ImageViewPagerFragment slideShowDialogFragment = new ImageViewPagerFragment();
        return slideShowDialogFragment;
    }
    public static ImageViewPagerFragment with(ResultWrapper resultWrapper, int adapterPosition) {
        final ImageViewPagerFragment fragment = new ImageViewPagerFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(PARAM_ITEMS, resultWrapper);
        arguments.putSerializable(PARAM_POS, adapterPosition);
       // arguments.putInt(KEY_IMAGE_RES, drawableRes);
        fragment.setArguments(arguments);
        return fragment;
    }
    private Pair<ResultWrapper,Integer> getBundleData() {
        final Bundle arguments = getArguments();
        ResultWrapper resultWrapper = (ResultWrapper) arguments.getSerializable(PARAM_ITEMS);
        Integer pos= (Integer) arguments.getSerializable(PARAM_POS);

        return new Pair<>(resultWrapper,pos);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_image_view_pager, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);


        presenter.init(getBundleData(),this,getActivity(),  getTag(),null);


        prepareSharedElementTransition();
//
//        // Avoid a postponeEnterTransition on orientation change, and postpone only of first creation.
        if (savedInstanceState == null) {
            postponeEnterTransition();
        }

        return fragmentView;



    }


    /**
     * Prepares the shared element transition from and back to the grid fragment.
     */
    private void prepareSharedElementTransition() {
        Transition transition =
                TransitionInflater.from(getContext())
                        .inflateTransition(R.transition.image_shared_element_transition);
        setSharedElementEnterTransition(transition);
        Log.e("SHARED","setSharedElementEnterTransition");
        // A similar mapping is set at the GridFragment with a setExitSharedElementCallback.
        setEnterSharedElementCallback(
                new SharedElementCallback() {


                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        Log.e("SHARED",names.get(0));
                        // Locate the image view at the primary fragment (the ImageFragment that is currently
                        // visible). To locate the fragment, call instantiateItem with the selection position.
                        // At this stage, the method will simply return the fragment at the position and will
                        // not create a new one.
                        Fragment currentFragment = (Fragment) viewpager_slideshow.getAdapter().instantiateItem(viewpager_slideshow,0);
//                        Fragment currentFragment = (Fragment) viewPager.getAdapter()
//                                .instantiateItem(viewPager, MainActivity.currentPosition);
                        View view = currentFragment.getView();
                        View viewById = view.findViewById(R.id.imageViewRectItem);

                        Log.e("SHARED",viewById.getTransitionName());


                        if (view == null) {
                            return;
                        }
// CustomImageView imageViewPreview = (CustomImageView) view.findViewById(R.id.imageViewRectItem);
//                        // Map the first shared element name to the child ImageView.
                        sharedElements.put(names.get(0), view.findViewById(R.id.imageViewRectItem));
                    }
                });
    }



    @Override
    public void makeToast(String message) {
        //showToastMessage(message);
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

        Log.e("parent",">");

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