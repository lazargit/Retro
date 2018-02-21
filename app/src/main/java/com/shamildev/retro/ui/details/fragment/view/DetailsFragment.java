package com.shamildev.retro.ui.details.fragment.view;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMovieById;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.ui.common.view.BaseDialogFragment;
import com.shamildev.retro.ui.common.view.BaseViewDialogFragment;
import com.shamildev.retro.ui.common.view.BaseViewFragment;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.details.DetailsActivity;
import com.shamildev.retro.ui.details.fragment.presenter.DetailsPresenter;
import com.shamildev.retro.ui.layout.PreCachingGridLayoutManager;
import com.shamildev.retro.ui.watchlist.adapter.WatchListRecycleViewAdapter;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;
import com.shamildev.retro.util.DeviceUtils;
import com.shamildev.retro.util.EndlessRecyclerViewScrollListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.internal.Preconditions;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class DetailsFragment extends android.support.v4.app.Fragment implements DetailsView {


    private static final String PARAM_MOVIE = "param_movie";



    @Inject
    Glide glide;
    private Unbinder butterKnifeUnbinder;





    public DetailsFragment() {
            Timber.d("Details-Fragment");

    }



    public static DetailsFragment forMovie(Movie movie) {
        final DetailsFragment detailsFragment = new DetailsFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(PARAM_MOVIE, movie);
        detailsFragment.setArguments(arguments);
        return detailsFragment;
    }

    private void loadMovieDetails() {

    }

    /**
     * Get current movie id from fragments arguments.
     */
    private Movie currentMovieId() {
        final Bundle arguments = getArguments();
        Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
        Movie movie = (Movie) arguments.getSerializable(PARAM_MOVIE);
        return movie;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_details, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);


        Movie movie = currentMovieId();


        return fragmentView;



    }



    @Override
    public void showSomething(String something) {
        //someText.setText(something);
    }









    @Override
    public void fillList(Movie results) {

    }


    @Override
    public void makeToast(String message) {

    }
}