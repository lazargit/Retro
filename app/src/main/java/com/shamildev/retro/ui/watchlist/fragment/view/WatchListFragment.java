package com.shamildev.retro.ui.watchlist.fragment.view;

import android.app.ActivityOptions;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.helper.RecyclerItemTouchHelper;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.view.BaseViewFragment;
import com.shamildev.retro.ui.layout.PreCachingGridLayoutManager;
import com.shamildev.retro.ui.watchlist.adapter.WatchListRecycleViewAdapter;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;
import com.shamildev.retro.util.DeviceUtils;
import com.shamildev.retro.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class WatchListFragment extends BaseViewFragment<WatchListPresenter> implements WatchListView {


    @BindView(R.id.recycler_view_watchlist)
    RecyclerView recyclerView;



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


    public WatchListFragment() {

            setRetainInstance(true);
            Timber.d("WatchList-Fragment");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_watchlist, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);
        setUpRecyclerView(recyclerView);
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



//    @OnClick(R.id.button_fetch_watchlist)
//    void onButton_start_bootstrap(View view) {
//
//        System.out.println(">>>###>>>"+view.getId());
//
//        presenter.onDoSomething(R.id.button_fetch_watchlist);
//
//    }


    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }



    @Override
    public void fillList(List<Movie> results) {
        for (Movie movie:results) {
            // Timber.d(movie.getTitle());
            movieArrayList.add(movie);
            watchListRecycleViewAdapter.notifyDataSetChanged();
        }

    }


    private void setUpRecyclerView(RecyclerView recyclView) {

        watchListRecycleViewAdapter = new WatchListRecycleViewAdapter(movieArrayList, glide, getActivity());
       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        // mRecyclerViewHome.setLayoutManager(linearLayoutManager);
        recyclView.setHasFixedSize(true);
        recyclView.setNestedScrollingEnabled(false);
        //  mRecyclerViewHome.setItemViewCacheSize(20);
        // mRecyclerViewHome.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        //Setup layout manager
       // PreCachingLayoutManager layoutManager = new PreCachingLayoutManager(getActivity());
      //  layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
      //  layoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(getActivity()));
      //  recyclerView.setLayoutManager(layoutManager);
        //Setup GridLayout manager
        PreCachingGridLayoutManager layoutManager = new PreCachingGridLayoutManager(getActivity(), 2);




        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

            recyclView.setLayoutManager(layoutManager);
        } else {
            layoutManager = new PreCachingGridLayoutManager(getActivity(),4);
            recyclView.setLayoutManager(layoutManager);
        }

        layoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(getActivity()));
        recyclView.setAdapter(watchListRecycleViewAdapter);
        recyclView.setItemAnimator(new DefaultItemAnimator());




        // making http call and fetching menu json


        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                Log.d(this.toString()," totalItemsCount"+totalItemsCount);
               // loadNextDataFromApi(page+1);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclView.addOnScrollListener(scrollListener);
        //presenter.onDoSomething(R.id.button_fetch_watchlist);
        recyclView.addOnItemTouchListener(
                        new WatchListRecycleViewAdapter.
                        RecyclerTouchListener(application.getApplicationContext(),
                                this.recyclerView,
                                                new WatchListRecycleViewAdapter.ClickListener(){

                                                    @Override
                                                    public void onClick(View view, int position) {





                                                        Movie movie = movieArrayList.get(position);
                                                        Log.e("CLICK",movie.title());
                                                        navigator.navigateToDetails(application,movie);

                                                    }

                                                    @Override
                                                    public void onLongClick(View view, int position) {
                                                        Log.e("onLongClick",movieArrayList.get(position).id().toString());

                                                    }
                                                })
                         );

    }
}