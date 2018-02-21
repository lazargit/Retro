package com.shamildev.retro.ui.home.fragment.view;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.navigation.Navigator;

import com.shamildev.retro.ui.common.view.BaseFragmentV4;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.details.fragment.view.DetailsFragment;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenter;
import com.shamildev.retro.ui.layout.PreCachingGridLayoutManager;
import com.shamildev.retro.ui.watchlist.adapter.WatchListRecycleViewAdapter;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;
import com.shamildev.retro.util.DeviceUtils;
import com.shamildev.retro.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import dagger.internal.Preconditions;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class HomeFragment extends BaseViewFragmentV4<HomePresenter> implements HomeView {

    private static final String PARAM_MOVIE = "param_movie";






    @Inject
    Glide glide;


    @Inject
    Application application;

    @Inject
    Navigator navigator;

    ArrayList<Movie> movieArrayList = new ArrayList<>();
    private WatchListRecycleViewAdapter watchListRecycleViewAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private Unbinder butterKnifeUnbinder;


    public HomeFragment() {

            setRetainInstance(true);
            Timber.d("WatchList-Fragment");


    }

    public static HomeFragment withMovies(ArrayList<Movie> movies) {
        final HomeFragment homeFragment = new HomeFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(PARAM_MOVIE, movies);
        homeFragment.setArguments(arguments);
        return homeFragment;
    }

    /**
     * Get current movie id from fragments arguments.
     */
    private ArrayList<Movie> currentMovies() {
        final Bundle arguments = getArguments();
        Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
        ArrayList<Movie> movies = (ArrayList<Movie>) arguments.getSerializable(PARAM_MOVIE);
        return movies;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);
        currentMovies();
        return fragmentView;



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