package com.shamildev.retro.ui.widgets.Search.presenter;

import android.support.annotation.IdRes;
import android.util.Log;

import com.shamildev.retro.R;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMultiSearch;
import com.shamildev.retro.domain.interactor.GetMyWatchList;
import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.responsemodels.Response;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.home.fragment.view.HomeView;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;
import com.shamildev.retro.ui.widgets.Search.view.SearchResultView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * An implementation of {@link WatchListPresenter}.
 */
@PerFragment
final class SearchResultPresenterImpl extends BasePresenter<SearchResultView> implements SearchResultPresenter {






    private final UseCaseHandler useCaseHandler;
    private final GetMultiSearch getMultiSearch;
    private String searchStr = "";


    @Inject
    SearchResultPresenterImpl(SearchResultView view,
                              UseCaseHandler useCaseHandler,
                              GetMultiSearch getMultiSearch) {
        super(view);
        this.useCaseHandler = useCaseHandler;
        this.getMultiSearch = getMultiSearch;

    }




    @Override
    public void onDoSomething(@IdRes int id) {
        if (id == R.id.button_fetch_watchlist) {

            Log.d("getTMDBConfiguration", "getTMDBConfiguration");




//            useCaseHandler.execute(getMyWatchList, GetMyWatchList.Params.justVoid(), new DisposableSubscriber<List<Movie>>() {
//                @Override
//                public void onNext(List<Movie> movieList) {
//
//                    view.fillList(movieList);
//                    Log.d("onNext", "totalPages "+movieList.size());
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

    @Override
    public void onSearch(String str) {
        searchStr = str;

        loadFirstPage(str);
    }

    @Override
    public void loadNextPage(int page) {
        useCaseHandler.execute(   this.getMultiSearch, GetMultiSearch.Params.with(searchStr,page), new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper resultWrapper) {

                Log.d("onNext>>>>","total results"+resultWrapper.totalResults());
                view.addToList(resultWrapper.results());


            }

            @Override
            public void onError(Throwable t) {
                Log.d("onError>>>>", t.getClass().getName());
            }

            @Override
            public void onComplete() {

            }
        });

    }


    private void loadFirstPage(String s) {

        Log.d("#############","--> "+Thread.currentThread()+" || dataFromMdbNetwork: ");

        useCaseHandler.execute(   this.getMultiSearch, GetMultiSearch.Params.with(s,1), new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper resultWrapper) {

                Log.d("onNext>>>>","total results"+resultWrapper.totalResults());
                view.fillList(resultWrapper);


            }

            @Override
            public void onError(Throwable t) {
                Log.d("onError>>>>", t.getClass().getName());
            }

            @Override
            public void onComplete() {

            }
        });


    }


}