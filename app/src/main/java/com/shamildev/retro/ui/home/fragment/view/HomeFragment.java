package com.shamildev.retro.ui.home.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.navigation.Navigator;

import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.home.HomeActivity;
import com.shamildev.retro.ui.home.fragment.adapter.ViewPagerAdapter;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenter;
import com.shamildev.retro.ui.home.slideshowfragment.view.SlideShowDialogFragment;
import com.shamildev.retro.ui.watchlist.adapter.WatchListRecycleViewAdapter;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;
import com.shamildev.retro.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class HomeFragment extends BaseViewFragmentV4<HomePresenter> implements HomeView {

    private static final String PARAM_MOVIE = "param_movie";




    @Inject
    AppConfig appConfig;


    @Inject
    Glide glide;


    @Inject
    Application application;

    @Inject
    Navigator navigator;

    @BindView(R.id.viewpager)
    ViewPager viewPager;




    ArrayList<Movie> movieArrayList = new ArrayList<>();
    private WatchListRecycleViewAdapter watchListRecycleViewAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private Unbinder butterKnifeUnbinder;
    private ViewPagerAdapter mViewPagerAdapter;


    public HomeFragment() {

            setRetainInstance(true);
            Timber.d("HomeFragment");


    }

    public static HomeFragment with() {
        final HomeFragment homeFragment = new HomeFragment();
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

        final View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);
        HomeActivity.tabs.setupWithViewPager(viewPager);
        presenter.init(getArguments().getSerializable(PARAM_MOVIE),childFragmentManager);



        return fragmentView;

    }


    @Override
    public void initViewPager(ViewPagerAdapter viewPagerAdapter) {
        viewPager.setAdapter(viewPagerAdapter);




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