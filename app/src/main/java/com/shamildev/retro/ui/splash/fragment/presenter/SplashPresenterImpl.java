package com.shamildev.retro.ui.splash.fragment.presenter;


import android.app.Application;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.bootstrap.Bootstrap;
import com.shamildev.retro.domain.bootstrap.BootstrapImpl;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.helper.DataReloading;
import com.shamildev.retro.domain.helper.ProcessData;
import com.shamildev.retro.domain.interactor.GetGenre;
import com.shamildev.retro.domain.interactor.GetMovieById;
import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetNowPlayingTVShows;
import com.shamildev.retro.domain.interactor.GetPopularPerson;
import com.shamildev.retro.domain.interactor.GetTMDBConfiguration;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.interactor.InitTables;
import com.shamildev.retro.domain.interactor.UseCaseFlowable;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.util.Pair;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retrovideo.core.RetroVideo;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.splash.fragment.model.SplashModel;
import com.shamildev.retro.ui.splash.fragment.view.SplashFragment;
import com.shamildev.retro.ui.splash.fragment.view.SplashView;
import com.shamildev.retro.views.retroslider.views.ImageSliderView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import io.reactivex.subscribers.DisposableSubscriber;



    /**
     * Created by Shamil Lazar
     */

    /**
     * An implementation of {@link SplashPresenter}.
     */
    @PerFragment
    public final class SplashPresenterImpl extends BasePresenter<SplashView,SplashModel> implements SplashPresenter,NetworkManager.NetworkListener,Bootstrap {



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
        private final RequestManager requestManager;
        private final AppConfig appConfig;
        private final GetPopularPerson getPopularPerson;
        private final ArrayList<String> mListTopic = new ArrayList<String>();
        private final InitTables initTables;



        @Inject
        Glide glide;
        @Inject
        Application application;
        @Inject
        DataReloading dataReloading;
        @Inject
        RetroImage retroImage;
        @Inject
        RetroVideo retroVideo;



        private ArrayList resultsNowPlaying ,resultsUpComming;
        private HashMap<String,ResultWrapper> map = new HashMap<>();
        private int screenWidth = 1080;
        private int imageCount = 0;


        @Inject
        SplashPresenterImpl(
                            AppConfig appConfig,
                            SplashView view,
                            SplashModel model,
                            SplashFragment fragment,
                            NetworkManager networkManager,
                            UseCaseHandler useCaseHandler,
                            InitTables initTables,
                            GetTMDBConfiguration getTMDBConfiguration,
                            GetGenre getGenre,
                            GetUpcomingMovies getUpcomingMovies,
                            GetNowPlayingMovies getNowPlayingMovies,
                            GetTopRatedMovies getTopRatedMovies,
                            GetNowPlayingTVShows getNowPlayingTVShows,
                            GetPopularPerson getPopularPerson,
                            GetMovieById getMovieById,
                            BootstrapImpl bootstrap,
                            DataConfig dataConfig


                           ) {
            super(view,model);
            this.useCaseHandler = useCaseHandler;
            this.initTables = initTables;
            this.getTMDBConfiguration = getTMDBConfiguration;
            this.getGenre = getGenre;
            this.getUpcomingMovies = getUpcomingMovies;
            this.getTopRatedMovies = getTopRatedMovies;
            this.getNowPlayingMovies = getNowPlayingMovies;
            this.getNowPlayingTVShows = getNowPlayingTVShows;
            this.getPopularPerson = getPopularPerson;
            this.getMovieById = getMovieById;
            this.networkManager = networkManager;
            this.networkManager.add(toString(), this::refreshData);
            this.bootstrap = bootstrap;
            this.bootstrap.setUp(this);
            this.dataConfig = dataConfig;
            this.requestManager = Glide.with(fragment);
            this.appConfig = appConfig;











            this.mListTopic.add(AppConfig.NOWPLAYINGKEY);
            this.mListTopic.add(AppConfig.NOWPLAYINGTVKEY);
            this.mListTopic.add(AppConfig.UPCOMMINGKEY);
            this.mListTopic.add(AppConfig.TOPRATEDKEY);
            this.mListTopic.add(AppConfig.POPULARPERSONKEY);
            this.mListTopic.add(AppConfig.HOMEHEADERKEY);

        }


        @Override
        public void onStart(@Nullable Bundle savedInstanceState) {
            Log.e("BasePresenter","onStart!!!!");
            if(this.appConfig.isFirstStart()){
                this.model.initData();
                this.model.checkUser();
            }else{
                this.model.initConfiguration();
            }

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

        }


        @Override
        public void startApp() {
            if(this.appConfig.isFirstStart()){
                initTables();
            }else{
                initConfiguration();
            }
        }



        @Override
        public void initTables() {
            model.initData();


        }

        @Override
        public void finish(HashMap<String, ResultWrapper> map) {
            Log.d("finish","finishfinishfinishfinishfinishfinish");
            view.navigateToHome(map);

        }


        private void initConfiguration(){
            useCaseHandler.execute(getTMDBConfiguration,GetTMDBConfiguration.Params.withCacheTime(1), new DisposableSubscriber<Configuration>() {
                @Override
                public void onNext(Configuration configuration) {

                    Log.d("onNext",configuration.backdropSizes().get(0));
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

                    retroImage.setConfigurations(appConfig.getConfigurations());
                    initGenres();
                    Log.d("onComplete",">> initConfiguration "+ appConfig.getConfigurations().baseUrl());

                }
            });


        }

        private void initGenres() {

            useCaseHandler.execute(getGenre, GetGenre.Params.with(dataConfig.language(), dataConfig.maxCacheTime()), new DisposableSubscriber<List<Genre>>() {
                @Override
                public void onNext(List<Genre> genres) {
                    Log.d("onNext genre",""+genres.size());

                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onComplete() {
                    Log.d("onComplete",">> genre init");
                   // loadNowPlayingMovies();
                    dataReloading.loadTopics(mListTopic,map,new DataReloading.LoadTopicsListener(){


                        @Override
                        public void onDataLoad() {
                            Log.e("SPLASH","TOPICS  LOADED");
                            //loadHomeHeaderGallery(map);
                          //  view.navigateToHome(map);
                        }

                        @Override
                        public void onDataError() {

                        }
                    });


                }
            });

        }




        private DisposableSubscriber loadNowPlayingMoviesSubscriber = new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper resultWrapper) {
                final int min = 0;
                final int max = resultWrapper.results().size()-1;
                final int random = new Random().nextInt((max - min) + 1) + min;
                Log.d("onComplete",random+" size: "+resultWrapper.results().size());
                MediaItem mediaItem = (MediaItem) resultWrapper.results().get(random);

                map.put(AppConfig.UPCOMMINGKEY,resultWrapper);

                if(mediaItem.itemPosterPath()==null){
                    loadUpcomingMovies();
                    return;
                }

                Log.d("onComplete",""+mediaItem.itemPosterPath());
                retroImage
                        .load(mediaItem)
                        .Poster()
                        .w780()
                        .into(view.getSplashBg(),new RetroImageRequestListener() {
                            @Override
                            public boolean onLoadFailed() {
                                Log.e("TAG","IMAGES LOAD FAILED.");
                                return false;
                            }

                            @Override
                            public boolean onResourceReady() {
                                Log.e("TAG","ALL IMAGES PRELOADED...!");
                                loadUpcomingMovies();
                                return false;
                            }
                        });



            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.d("onComplete",">> loadUpcomingMovies");
            }
        };
        private void loadNowPlayingMovies(){
            useCaseHandler.execute(getNowPlayingMovies, GetNowPlayingMovies.Params.withPage(1),loadNowPlayingMoviesSubscriber);

        }

        private DisposableSubscriber loadUpcomingMoviesSubscriber = new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper resultWrapper) {

                map.put(AppConfig.NOWPLAYINGKEY,resultWrapper);
            }

            @Override
            public void onError(Throwable t) {


            }

            @Override
            public void onComplete() {
                loadNowPlayingTVShows();
            }
        };
        private void loadUpcomingMovies(){
            useCaseHandler.execute(getUpcomingMovies, GetUpcomingMovies.Params.withPage(1),loadUpcomingMoviesSubscriber);

        }


        private DisposableSubscriber loadNowPlayingTVShowsSubscriber = new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper resultWrapper) {

                map.put(AppConfig.NOWPLAYINGTVKEY,resultWrapper);
            }

            @Override
            public void onError(Throwable t) {


            }

            @Override
            public void onComplete() {
                loadPopularPerson();
            }
        };
        private void loadNowPlayingTVShows(){
            useCaseHandler.execute(getNowPlayingTVShows, GetNowPlayingTVShows.Params.withPage(1),loadNowPlayingTVShowsSubscriber);

        }


        private DisposableSubscriber loadPopularPersonSubscriber = new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper resultWrapper) {

                map.put(AppConfig.POPULARPERSONKEY,resultWrapper);
            }

            @Override
            public void onError(Throwable t) {


            }

            @Override
            public void onComplete() {
                view.navigateToHome(map);
            }
        };
        private void loadPopularPerson(){
            useCaseHandler.execute(getPopularPerson, GetPopularPerson.Params.withPage(1),loadPopularPersonSubscriber);

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



        }

        @Override
        public void onBootstrapNext(Class clazz) {
            view.makeToast(clazz.getSimpleName());

        }

        @Override
        public void onBootstrapComplete() {
            view.makeToast("Bootstrap Complete");

            if(appConfig.getConfigurations() != null){
                this.retroImage.setConfigurations(appConfig.getConfigurations());

            }


            loadWatchList();





        }

        private void loadWatchList(){
            dataReloading.loadMyWatchList();



            //  loadHomeHeaderGallery(map);

          // loadFirstPage_NowPlayingTVShows();



            dataReloading.loadTopics(this.mListTopic,this.map,new DataReloading.LoadTopicsListener(){


                @Override
                public void onDataLoad() {
                Log.e("SPLASH","TOPICS  LOADED");

                         loadHomeHeaderGallery(map);
                }

                @Override
                public void onDataError() {

                }
            });





        }












        private void loadHomeHeaderGallery(HashMap<String, ResultWrapper> map){
        //    List<MediaItem> homeGalleryList = ProcessData.createHomeGalleryList(map);
            List<MediaItem> homeGalleryList = ProcessData.createTopRatedGalleryList(map);

            ImageSliderView imageSliderView = view.getImageSliderView();
            Log.e("TAG","homeGalleryList "+homeGalleryList.size());

            //https://media.giphy.com/media/87kcVw4PjxGr6/giphy.gif
//            ArrayList<String> gifUrl = new ArrayList<>();
//            gifUrl.add("https://i.gifer.com/2Gr0.gif");
//            gifUrl.add("https://media.giphy.com/media/87kcVw4PjxGr6/giphy.gif");
//            gifUrl.add("https://media.giphy.com/media/lR8eXSeYUF5vO/giphy.gif");
//
//
//            List<MediaItem> personList = ProcessData.createHomePersonList(map);
//
//            this.retroImage
//                     .load(gifUrl)
//
//                     .preload(new RetroImageRequestListener() {
//                         @Override
//                         public boolean onLoadFailed() {
//                         og.e("TAG","IMAGE LOAD FAILED");
//                             return false;
//                         }
//
//                         @Override
//                         public boolean onResourceReady() {
//                             Log.e("TAG","ALL IMAGES PRELOADED");
//                             return false;
//                         }
//                     });

//            List<MediaItem> personList = ProcessData.createHomePersonList(map);
//


         //view.navigateToHome(map);


        }


        @Override
        public void onBootstrapError(Throwable t) {
            view.makeToast("Error "+t);

        }

        @Override
        public void screenWidth(int screenWidth) {
            this.screenWidth = screenWidth;
        }


    }