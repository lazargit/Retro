package com.shamildev.retro.ui.home.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.helper.TranslateItemAnimator;
import com.shamildev.retro.navigation.Navigator;

import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.home.HomeActivity;
import com.shamildev.retro.ui.home.fragment.adapter.RecyclerViewPagerAdapter;
import com.shamildev.retro.ui.home.fragment.adapter.ViewPagerAdapter;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenter;
import com.shamildev.retro.ui.watchlist.adapter.WatchListRecycleViewAdapter;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;
import com.shamildev.retro.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.IOverScrollStateListener;
import me.everything.android.ui.overscroll.IOverScrollUpdateListener;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;
import timber.log.Timber;

import static me.everything.android.ui.overscroll.IOverScrollState.STATE_BOUNCE_BACK;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_END_SIDE;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_START_SIDE;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class HomeFragment extends BaseViewFragmentV4<HomePresenter> implements HomeView {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private static final String PARAM_MOVIE = "param_movie";




    @Inject
    AppConfig appConfig;
    @Inject
    Glide glide;
    @Inject
    Application application;
    @Inject
    RetroImage retroImage;
    @Inject
    Navigator navigator;
    @Inject
    HomeActivity homeActivity;


//
//    @BindView(R.id.recyclerView_nowplaying)
//    RecyclerView mRecyclerView_nowplaying;
//
//    @BindView(R.id.recyclerView_nowplayingtv)
//    RecyclerView mRecyclerView_nowplayingtv;
//
//    @BindView(R.id.recyclerView_upcomming)
//    RecyclerView mRecyclerView_upcomming;


    private ArrayList<Movie> movieArrayList = new ArrayList<>();
    private WatchListRecycleViewAdapter watchListRecycleViewAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private Unbinder butterKnifeUnbinder;
    private ViewPagerAdapter mViewPagerAdapter;
    private ResultWrapper resultNowplaying;
    private ResultWrapper resultNowplayingTv;
    private HashMap<String, ResultWrapper> map;
    private ResultWrapper resultUpcomming;



    public HomeFragment() {

            setRetainInstance(true);
            Timber.d("HomeFragment");


    }

    public static HomeFragment with() {
        final HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    public static HomeFragment with(HashMap<String,ResultWrapper> items) {
        final HomeFragment homeFragment = new HomeFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(PARAM_MOVIE, items);
        homeFragment.setArguments(arguments);
        return homeFragment;
    }

    public static HomeFragment withMovies(HashMap<String,ResultWrapper> items, TabLayout tabLayout) {
        final HomeFragment homeFragment = new HomeFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(PARAM_MOVIE, items);
        homeFragment.setArguments(arguments);



        return homeFragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, view);



//        map = (HashMap<String, ResultWrapper> ) getArguments().getSerializable(PARAM_MOVIE);
//        resultNowplaying = map.get(AppConfig.NOWPLAYINGKEY);
//        resultNowplayingTv = map.get(AppConfig.NOWPLAYINGTVKEY);
//        resultUpcomming = map.get(AppConfig.UPCOMMINGKEY);
//
//
//
//
//
//
//        if(view.findViewById(R.id.recyclerViewTeaser) !=null){
//            RecyclerView recyclerView = view.findViewById(R.id.recyclerViewTeaser);
//            setupRecyclerView(recyclerView,resultNowplaying);
//        }
//
//        View nowplayingtv = view.findViewById(R.id.home_nowplayingtv);
//        RecyclerView recyclerView = nowplayingtv.findViewById(R.id.recyclerViewTeaser);
//
//        setupRecyclerView(recyclerView,resultNowplayingTv);




        return view;

    }



    private void setupRecyclerView(RecyclerView recyclerView, ResultWrapper resultWrapper) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);


        ArrayList<DomainObject> results = (ArrayList<DomainObject>) resultWrapper.results();

        RecyclerViewPagerAdapter recyclerViewPagerAdapter = new RecyclerViewPagerAdapter(results, getContext(), retroImage, this);
        recyclerView.setAdapter(recyclerViewPagerAdapter);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        recyclerView.setItemAnimator(new TranslateItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerViewPagerAdapter.RecyclerTouchListener(getContext(),recyclerView,new RecyclerViewPagerAdapter.ViewHolderListener(){

            @Override
            public void onLoadCompleted(ImageView view, int adapterPosition) {


            }

            @Override
            public void onItemClicked(View view, int adapterPosition) {

                //SearchActivity activity = (SearchActivity) getActivity();
             //   HomePageFragment fragment = HomePageFragment.with(resultWrapper, "tag", adapterPosition);

                //WatchListFragment instance = WatchListFragment.instance();

                //homeActivity.loadFragment(instance);
//                SearchActivity homeActivity = new SearchActivity();
//                homeActivity.

                // final View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
           // addChildFragment(R.id.fragmentContainer,HomePageFragment.with(resultWrapper,"tag",adapterPosition));

//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .setReorderingAllowed(true) // Optimize for shared element transition
//                      //  .addSharedElement(transitioningView, transitioningView.getTransitionName())
//                        .replace(R.id.fragmentContainer,HomePageFragment.with(resultWrapper,"tag",adapterPosition), HomePageFragment.class
//                                .getSimpleName())
//                        .addToBackStack(HomePageFragment.TAG)
//                        .commit();



               // ((TransitionSet) getExitTransition()).excludeTarget(view, true);

              //  CustomImageView transitioningView = view.findViewById(R.id.imageViewRectItem);




               // presenter.onItemClick(adapterPosition,transitioningView,view);

            }
        }));


        HorizontalOverScrollBounceEffectDecorator overScrollEffect;
        // Apply over-scroll in 'advanced form' - i.e. create an instance manually.

                overScrollEffect = new HorizontalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));

        // Over-scroll listeners are applied here via the mVertOverScrollEffect explicitly.
        overScrollEffect.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
            @Override
            public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
                //  mVertScrollMeasure.setText(String.valueOf((int) offset));
            }
        });
        overScrollEffect.setOverScrollStateListener(new IOverScrollStateListener() {
//            private final int mDragColorTop = getResources().getColor(android.R.color.holo_red_light);
//            private final int mBounceBackColorTop = getResources().getColor(android.R.color.holo_orange_dark);
//            private final int mDragColorBottom = getResources().getColor(android.R.color.holo_purple);
//            private final int mBounceBackColorBottom = getResources().getColor(android.R.color.holo_blue_light);
            // private final int mClearColor = mHorizScrollMeasure.getCurrentTextColor();

            @Override
            public void onOverScrollStateChange(IOverScrollDecor decor, int oldState, int newState) {
                if (newState == STATE_DRAG_START_SIDE) {
                    Log.e("FRAGTAG","STATE_DRAG_START_SIDE");
                    //  mVertScrollMeasure.setTextColor(mDragColorTop);
                } else if (newState == STATE_DRAG_END_SIDE) {
                    Log.e("FRAGTAG","STATE_DRAG_END_SIDE");
                    // adapter.addLoadingFooter();
                    // mVertScrollMeasure.setTextColor(mDragColorBottom);
                } else if (newState == STATE_BOUNCE_BACK) {
                    Log.e("FRAGTAG","STATE_BOUNCE_BACK");
                    //  mVertScrollMeasure.setTextColor(oldState == STATE_DRAG_START_SIDE ? mBounceBackColorTop : mBounceBackColorBottom);
                } else {
                    Log.e("FRAGTAG","---------------------");
                    //  mVertScrollMeasure.setTextColor(mClearColor);
                }
            }
        });


        //  baliAdapter = new MediaListAdapter(this, getActivity());
    }

    private void addDataToRecyclerView() {
      //  mRecyclerView_nowplaying.setItemAnimator(new TranslateItemAnimator());
      //  mRecyclerView_nowplaying.setAdapter(baliAdapter);
      //  baliAdapter.setPlacesList(baliPlaces);
       // recyclerView.addOnScrollListener(new HorizontalRecyclerViewScrollListener(this));
    }


    @Override
    public void initViewPager(ViewPagerAdapter viewPagerAdapter) {
     //   viewPager.setAdapter(viewPagerAdapter);
      //  mRecyclerView_nowplaying.setAdapter();




    }

    @Override
    public void addPageFragment(String title, MovieWrapper movieWrapper) {
     //   mViewPagerAdapter.addFragment(HomePageFragment.withMovies(movieWrapper),title);


    }

    public void sharedElementTransition(View view) {

//        Pair[] pair = new Pair[3];
//        pair[0] = new Pair<View, String>(imgLogo, "logo_shared");
//        pair[1] = new Pair<View, String>(txvShared, "smartherd_shared");
//        pair[2] = new Pair<View, String>(imgProfilePic, "profile_pic_shared");
//
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pair);
//        Intent i = new Intent(MainActivity.this, SharedElementActivity.class);
//        startActivity(i, options.toBundle());
    }


    @Override
    public void showSomething(String something) {
        //someText.setText(something);
    }




    @Override
    public void makeToast(String message) {
        //showToastMessage(message);
    }



    @Override
    public void fillList(List<Movie> results) {
        for (Movie movie:results) {
             Timber.d(movie.title());

        }

    }



}