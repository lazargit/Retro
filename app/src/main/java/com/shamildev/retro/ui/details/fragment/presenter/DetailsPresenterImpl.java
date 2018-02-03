package com.shamildev.retro.ui.details.fragment.presenter;

import android.support.annotation.IdRes;
import android.util.Log;

import com.shamildev.retro.R;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMovieById;
import com.shamildev.retro.domain.interactor.GetMyWatchList;
import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.details.fragment.view.DetailsView;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * An implementation of {@link WatchListPresenter}.
 */
@PerFragment
final class DetailsPresenterImpl extends BasePresenter<DetailsView> implements DetailsPresenter{






    private final UseCaseHandler useCaseHandler;
    private final GetMovieById getMovieById;




    @Inject
    DetailsPresenterImpl(DetailsView view,
                         UseCaseHandler useCaseHandler,
                         GetMovieById getMovieById ) {
        super(view);
        this.useCaseHandler = useCaseHandler;
        this.getMovieById = getMovieById;


    }




    @Override
    public void onDoSomething(@IdRes int id) {
        if (id == R.id.button_fetch_watchlist) {

            Log.d("getTMDBConfiguration", "getTMDBConfiguration");


        }
    }




}