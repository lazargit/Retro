package com.shamildev.retro.ui.watchlist.fragment.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.net.NetworkManagerImpl;
import com.shamildev.retro.ui.common.view.BaseViewFragment;
import com.shamildev.retro.ui.watchlist.adapter.PreCachingLayoutManager;
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

    ArrayList<Movie> movieArrayList = new ArrayList<>();
    private WatchListRecycleViewAdapter watchListRecycleViewAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private Unbinder butterKnifeUnbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_watchlist, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);
        setUpRecyclerView();
        return fragmentView;



    }

    @Override
    public void showSomething(String something) {
        //someText.setText(something);
    }



    @OnClick(R.id.button_fetch_watchlist)
    void onButton_start_bootstrap(View view) {

        System.out.println(">>>###>>>"+view.getId());

        presenter.onDoSomething(R.id.button_fetch_watchlist);

    }


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


    private void setUpRecyclerView() {
        watchListRecycleViewAdapter = new WatchListRecycleViewAdapter(movieArrayList, glide, getActivity());
       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        // mRecyclerViewHome.setLayoutManager(linearLayoutManager);
        // mRecyclerViewHome.setHasFixedSize(true);
        //  mRecyclerViewHome.setItemViewCacheSize(20);
        // mRecyclerViewHome.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        //Setup layout manager
        PreCachingLayoutManager layoutManager = new PreCachingLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(getActivity()));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(watchListRecycleViewAdapter);



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
        recyclerView.addOnScrollListener(scrollListener);

    }
}