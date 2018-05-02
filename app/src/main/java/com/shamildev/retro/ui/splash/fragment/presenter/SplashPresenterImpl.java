package com.shamildev.retro.ui.splash.fragment.presenter;

import android.support.annotation.IdRes;
import android.util.Log;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.domain.bootstrap.Bootstrap;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.helper.DataReloading;
import com.shamildev.retro.domain.interactor.GetGenre;
import com.shamildev.retro.domain.interactor.GetMovieById;

import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetNowPlayingTVShows;
import com.shamildev.retro.domain.interactor.GetTMDBConfiguration;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.interactor.UseCaseFlowable;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.Person;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.util.Pair;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.domain.bootstrap.BootstrapImpl;
import com.shamildev.retro.ui.splash.fragment.view.SplashView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DisposableSubscriber;
import io.realm.Realm;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */



    /**
     * An implementation of {@link SplashPresenter}.
     */
    @PerFragment
    final class SplashPresenterImpl extends BasePresenter<SplashView> implements SplashPresenter,NetworkManager.NetworkListener,Bootstrap {



        private final NetworkManager networkManager;


        private final UseCaseHandler useCaseHandler;

        private final GetTMDBConfiguration getTMDBConfiguration;
        private final GetGenre getGenre;
        private final GetUpcomingMovies getUpcomingMovies;
        private final GetTopRatedMovies getTopRatedMovies;
        private final GetNowPlayingMovies getNowPlayingMovies;
        private final GetNowPlayingTVShows getNowPlayingTVShows;
        private final GetMovieById getMovieById;
        private final BootstrapImpl bootstrap;
        private final DataConfig dataConfig;



        @Inject
        AppConfig appConfig;
        @Inject
        DataReloading dataReloading;
        private ArrayList resultsNowPlaying ,resultsUpComming ;
        private HashMap<String,ResultWrapper> map = new HashMap<>();



        @Inject
        SplashPresenterImpl(SplashView view,
                            NetworkManager networkManager,
                            UseCaseHandler useCaseHandler,

                            GetTMDBConfiguration getTMDBConfiguration,
                            GetGenre getGenre,
                            GetUpcomingMovies getUpcomingMovies,
                            GetNowPlayingMovies getNowPlayingMovies,
                            GetTopRatedMovies getTopRatedMovies,
                            GetNowPlayingTVShows getNowPlayingTVShows,
                            GetMovieById getMovieById,
                            BootstrapImpl bootstrap,
                            DataConfig dataConfig

                           ) {
            super(view);


            this.useCaseHandler = useCaseHandler;


            this.getTMDBConfiguration = getTMDBConfiguration;
            this.getGenre = getGenre;
            this.getUpcomingMovies = getUpcomingMovies;
            this.getTopRatedMovies = getTopRatedMovies;
            this.getNowPlayingMovies = getNowPlayingMovies;
            this.getNowPlayingTVShows = getNowPlayingTVShows;
            this.getMovieById = getMovieById;

            this.networkManager = networkManager;
            this.networkManager.add(toString(), this::refreshData);
            this.bootstrap = bootstrap;
            this.bootstrap.setUp(this);
            this.dataConfig = dataConfig;


        }


        @Override
        public void onNetworkAvailable() {
            Log.d("test","onNetworkAvailable");
        }
        private void refreshData() {
            Log.d("test","refreshData");
        }

        @Override
        public void onDoSomething(@IdRes int id) {
            if(id == R.id.button){

                Log.d("getTMDBConfiguration","getTMDBConfiguration");
                useCaseHandler.execute(getTMDBConfiguration,GetTMDBConfiguration.Params.withCacheTime(1), new DisposableSubscriber<Configuration>() {
                    @Override
                    public void onNext(Configuration configuration) {
                        Log.d("onNext",configuration.toString());
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(t.getCause() instanceof TMDBError){
                            TMDBError error = (TMDBError) t.getCause();
                            Log.d("onError","<<<<< "+error.getResponseCode()+" : "+error.getMessage()+" : "+error.getStatusCode()+" : "+error.getSuccess());

                        }
                        Log.d("onError",t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("onComplete",">>");
                    }
                });
            }

            if(id == R.id.button_save){
//
//                useCaseHandler.execute(getMovieById, new DisposableObserver<MovieWrapper>() {
//                    @Override
//                    public void onNext(MovieWrapper response) {
//                        Log.d("useCaseHandler",">>>>>"+response.toString());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if(e.getCause() instanceof TMDBError){
//                            TMDBError error = (TMDBError) e.getCause();
//                            Log.d("onError","<<<<< "+error.getResponseCode()+" : "+error.getMessage()+" : "+error.getStatusCode()+" : "+error.getSuccess());
//
//                        }
//                        Log.d("onError","<<<<<"+e);
//                        //   TMDBError error = (TMDBError) e;
//                        //  Log.d("onError",">>>>>"+error.getMessage());
//
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

            }


            if(id == R.id.button_genre){
                GetGenre.Params with = GetGenre.Params.with("de_DE", 1);
                useCaseHandler.execute(getGenre,with,
                        new DisposableSubscriber<List<Genre>>() {
                            @Override
                            public void onNext(List<Genre> genres) {
                                Log.d("useCaseHandler","getGenre"+genres);
                            }

                            @Override
                            public void onError(Throwable t) {
                                Log.d("useCaseHandler","getGenre onError"+t.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                Log.d("useCaseHandler","getGenre onComplete");
                            }
                        }





               );
            }

            if(id == R.id.button_fetch_upcoming_movies){

            }
//            if(id == R.id.button_fetch_movie){
//                useCaseHandler.execute(getMovieById,155, new DisposableSubscriber<Movie>() {
//                    @Override
//                    public void onNext(Movie movie) {
//                        Log.d("useCaseHandler","getMovieById"+movie+">>>");
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        Log.d("useCaseHandler","getMovieById onError"+t.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d("useCaseHandler","getMovieById onComplete");
//                    }
//                });
//            }
//            if(id == R.id.button_fetch_movie2){
//                useCaseHandler.execute(getMovieById,354912, new DisposableSubscriber<Movie>() {
//                    @Override
//                    public void onNext(Movie movie) {
//                        Log.d("useCaseHandler","getMovieById"+movie+">>>");
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        Log.d("useCaseHandler","getMovieById onError"+t.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d("useCaseHandler","getMovieById onComplete");
//                    }
//                });
//            }


//     this.remoteRepository.getTestService()
//             .subscribeOn(Schedulers.io())
//             .observeOn(AndroidSchedulers.mainThread())
//             .subscribe(new DisposableObserver<MovieWrapper>() {
//         @Override
//         public void onNext(MovieWrapper entity) {
//             Log.d("test","onDoSomething"+entity.totalPages());
//
//
//            cacheRepository.saveObj(entity);
//
//
//         }
//
//         @Override
//         public void onError(Throwable e) {
//             Log.d("error","onError"+e.getMessage());
//         }
//
//         @Override
//         public void onComplete() {
//             Log.d(" onComplete"," onComplete");
//
//         }
//     });
//
//            view.showSomething("something");
        }


        @Override
        public void onStartBootstrap() {

            GetTMDBConfiguration.Params params = GetTMDBConfiguration.Params.withCacheTime(1);
            Pair<UseCaseFlowable, ParamsBasic> useCaseGetTMDBConfiguration = new Pair<>(getTMDBConfiguration,params);

            bootstrap.addUseCase(useCaseGetTMDBConfiguration);


            GetGenre.Params params_useCaseGetGenre = GetGenre.Params.with("de_DE", 1);

            Pair<UseCaseFlowable, ParamsBasic> useCaseGetGenre = new Pair<>(getGenre,params_useCaseGetGenre);

            bootstrap.addUseCase(useCaseGetGenre);

            bootstrap.executeUseCases();


//            useCaseHandler.execute(getTMDBConfiguration,GetTMDBConfiguration.Params.withCacheTime(1), new DisposableSubscriber<Configuration>() {
//                @Override
//                public void onNext(Configuration configuration) {
//                    Log.d("onNext",configuration.toString());
//                }
//
//                @Override
//                public void onError(Throwable t) {
//                    if(t.getCause() instanceof TMDBError){
//                        TMDBError error = (TMDBError) t.getCause();
//                        Log.d("onError","<<<<< "+error.getResponseCode()+" : "+error.getMessage()+" : "+error.getStatusCode()+" : "+error.getSuccess());
//
//                    }
//                    Log.d("onError",t.getMessage());
//                }
//
//                @Override
//                public void onComplete() {
//                    Log.d("onComplete",">>");
//                }
//            });

        }

        @Override
        public void onBootstrapNext(Class clazz) {
            view.makeToast(clazz.getSimpleName());

        }

        @Override
        public void onBootstrapComplete() {
            view.makeToast("Bootstrap Complete");
            System.out.println("DATACONFIG"+appConfig.getImageUrl());
            //appConfig.setImageUrl("override test");
            System.out.println("DATACONFIG"+appConfig.getImageUrl());

            if(appConfig.getConfigurations() != null){
                System.out.println("DATACONFIGURATION"+appConfig.getConfigurations().baseUrl());
            }
            if(appConfig.getGenres() != null){
                System.out.println("DATACONFIGENRES"+appConfig.getGenres().size()+" # "+appConfig.getGenres().get(0).name());
            }
            System.out.println("DATACONFIG"+dataConfig.baseUrl());


            //loadFirstPage_NowPlaying();
           // loadFirstPage_NowPlayingTVShows();
            loadWatchList();





        }

        private void loadWatchList(){
            dataReloading.loadMyWatchList();
            loadFirstPage_NowPlayingTVShows();



        }





        private void loadFirstPage_NowPlayingTVShows(){

            useCaseHandler.execute(getNowPlayingTVShows, GetNowPlayingTVShows.Params.withPage(1),

                    new DisposableSubscriber<ResultWrapper>() {
                        @Override
                        public void onNext(ResultWrapper movieWrapper) {
                            System.out.println("onNext1  :"+movieWrapper.results().size());
//                            List<DomainObject> list = prepareData(movieWrapper.results());
//                            System.out.println("onNext2  :"+list.size());
//                            movieWrapper.results().clear();
//                            movieWrapper.results().addAll(list);
                            //  resultsNowPlaying.addAll(movieWrapper.results());
                             map.put(AppConfig.NOWPLAYINGTVKEY,movieWrapper);



                        }

                        @Override
                        public void onError(Throwable t) {
                            System.out.println("onError"+t.getMessage());
                        }

                        @Override
                        public void onComplete() {
                           loadFirstPage_NowPlaying();
                            System.out.println("onComplete loadFirstPage_NowPlaying");
                        }
                    }


            );


        }
        private void loadFirstPage_NowPlaying(){
            resultsNowPlaying = new ArrayList<>();
            useCaseHandler.execute(getNowPlayingMovies, GetNowPlayingMovies.Params.withPage(1),

                    new DisposableSubscriber<ResultWrapper>() {
                        @Override
                        public void onNext(ResultWrapper movieWrapper) {
                            System.out.println("onNext"+movieWrapper.results().size());
                           // resultsNowPlaying.addAll(movieWrapper.results());
                           // map.put(AppConfig.NOWPLAYINGKEY,movieWrapper);
                            map.put(AppConfig.NOWPLAYINGKEY,movieWrapper);



                        }

                        @Override
                        public void onError(Throwable t) {
                            System.out.println("onError"+t.getMessage());
                        }

                        @Override
                        public void onComplete() {

                          loadFirstPage_UpComming();
                           // System.out.println("onComplete loadFirstPage_NowPlaying");
                        }
                    }


            );

        }
        private void loadFirstPage_UpComming(){
            resultsUpComming = new ArrayList<>();
            useCaseHandler.execute(getUpcomingMovies, GetUpcomingMovies.Params.withPage(1),

                    new DisposableSubscriber<ResultWrapper>() {
                        @Override
                        public void onNext(ResultWrapper movieWrapper) {
                            System.out.println("onNext"+movieWrapper.results().size());

                            map.put(AppConfig.UPCOMMINGKEY,movieWrapper);


                        }

                        @Override
                        public void onError(Throwable t) {
                            System.out.println("onError"+t.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            loadFirstPage_TopRated();

                        }
                    }


            );

        }
        private void loadFirstPage_TopRated(){
            resultsUpComming = new ArrayList<>();
            useCaseHandler.execute(getTopRatedMovies, GetTopRatedMovies.Params.withPage(1),

                    new DisposableSubscriber<ResultWrapper>() {
                        @Override
                        public void onNext(ResultWrapper movieWrapper) {


                            map.put(AppConfig.TOPRATEDKEY,movieWrapper);


                        }

                        @Override
                        public void onError(Throwable t) {
                            System.out.println("onError"+t.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            view.navigateToHome(map);
                        }
                    }


            );

        }

        @Override
        public void onBootstrapError(Throwable t) {
            view.makeToast("Error "+t);

        }
    }