package com.shamildev.retro.ui.watchlist.fragment.presenter;

import android.support.annotation.IdRes;
import android.util.Log;

import com.shamildev.retro.R;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.bootstrap.Bootstrap;
import com.shamildev.retro.domain.bootstrap.BootstrapImpl;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetGenre;
import com.shamildev.retro.domain.interactor.GetMovieById;
import com.shamildev.retro.domain.interactor.GetMovieWithId;
import com.shamildev.retro.domain.interactor.GetMyWatchList;
import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetTMDBConfiguration;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.interactor.UseCaseFlowable;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.util.Pair;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * An implementation of {@link WatchListPresenter}.
 */
@PerFragment
final class WatchListPresenterImpl extends BasePresenter<WatchListView> implements WatchListPresenter{






    private final UseCaseHandler useCaseHandler;
    private final GetMyWatchList getMyWatchList;
    private final GetUpcomingMovies getUpcomingMovies;
    private final GetTopRatedMovies getTopRatedMovies;
    private final GetNowPlayingMovies getNowPlayingMovies;



    @Inject
    WatchListPresenterImpl(WatchListView view,
                           UseCaseHandler useCaseHandler,
                           GetMyWatchList getMyWatchList ,
                           GetUpcomingMovies getUpcomingMovies,
                           GetTopRatedMovies getTopRatedMovies,
                           GetNowPlayingMovies getNowPlayingMovies) {
        super(view);
        this.useCaseHandler = useCaseHandler;
        this.getMyWatchList = getMyWatchList;
        this.getUpcomingMovies = getUpcomingMovies;
        this.getTopRatedMovies = getTopRatedMovies;
        this.getNowPlayingMovies = getNowPlayingMovies;

    }




    @Override
    public void onDoSomething(@IdRes int id) {
        if (id == R.id.button_fetch_watchlist) {

            Log.d("getTMDBConfiguration", "getTMDBConfiguration");

            useCaseHandler.execute(getMyWatchList, GetMyWatchList.Params.justVoid(), new DisposableSubscriber<List<Movie>>() {
                @Override
                public void onNext(List<Movie> movieList) {

                    view.fillList(movieList);
                    Log.d("onNext", "totalPages "+movieList.size());
                }

                @Override
                public void onError(Throwable t) {
                    if (t.getCause() instanceof TMDBError) {
                        TMDBError error = (TMDBError) t.getCause();
                        Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());

                    }
                    Log.d("onError>>>>", t.getClass().getName());
                }

                @Override
                public void onComplete() {
                    Log.d("onComplete", ">>");
                }
            });



//            useCaseHandler.execute(getNowPlayingMovies, GetNowPlayingMovies.Params.withPage(1), new DisposableSubscriber<MovieWrapper>() {
//                @Override
//                public void onNext(MovieWrapper movieWrapper) {
//
//                    Log.d("onNext", "totalPages "+movieWrapper.totalPages());
//                }
//
//                @Override
//                public void onError(Throwable t) {
//                    if (t.getCause() instanceof TMDBError) {
//                        TMDBError error = (TMDBError) t.getCause();
//                        Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());
//
//                    }
//                    Log.d("onError>>>>", t.getClass().getName());
//                }
//
//                @Override
//                public void onComplete() {
//                    Log.d("onComplete", ">>");
//                }
//            });
//
//
//            useCaseHandler.execute(getTopRatedMovies, GetTopRatedMovies.Params.withPage(1), new DisposableSubscriber<MovieWrapper>() {
//                @Override
//                public void onNext(MovieWrapper movieWrapper) {
//
//                    Log.d("onNext", "totalPages "+movieWrapper.totalPages());
//                }
//
//                @Override
//                public void onError(Throwable t) {
//                    if (t.getCause() instanceof TMDBError) {
//                        TMDBError error = (TMDBError) t.getCause();
//                        Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());
//
//                    }
//                    Log.d("onError>>>>", t.getClass().getName());
//                }
//
//                @Override
//                public void onComplete() {
//                    Log.d("onComplete", ">>");
//                }
//            });
//
//
//            useCaseHandler.execute(getUpcomingMovies, GetUpcomingMovies.Params.withPage(1), new DisposableSubscriber<MovieWrapper>() {
//                @Override
//                public void onNext(MovieWrapper movieWrapper) {
//
//                    Log.d("onNext", "totalPages "+movieWrapper.totalPages());
//                }
//
//                @Override
//                public void onError(Throwable t) {
//                    if (t.getCause() instanceof TMDBError) {
//                        TMDBError error = (TMDBError) t.getCause();
//                        Log.d("onError", "<<<<< " + error.getResponseCode() + " : " + error.getMessage() + " : " + error.getStatusCode() + " : " + error.getSuccess());
//
//                    }
//                    Log.d("onError>>>>", t.getClass().getName());
//                }
//
//                @Override
//                public void onComplete() {
//                    Log.d("onComplete", ">>");
//                }
//            });
        }
    }




}