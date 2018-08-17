package com.shamildev.retro.ui.splash.fragment.presenter;



import android.app.Application;
import android.support.annotation.IdRes;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import com.shamildev.retro.R;

import com.shamildev.retro.domain.DomainObjectStorable;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.domain.bootstrap.Bootstrap;
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
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.util.Pair;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.domain.bootstrap.BootstrapImpl;
import com.shamildev.retro.ui.splash.fragment.view.SplashFragment;
import com.shamildev.retro.ui.splash.fragment.view.SplashView;
import com.shamildev.retro.views.retroslider.views.ImageSliderView;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;


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

        private ArrayList resultsNowPlaying ,resultsUpComming ;
        private HashMap<String,ResultWrapper> map = new HashMap<>();
        private int screenWidth = 1080;


        @Inject
        SplashPresenterImpl(
                            AppConfig appConfig,
                            SplashView view,
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
            super(view);


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




            Log.e("TAG","appconfig "+this.appConfig.getConfigurations());



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
        public void initTables() {



            Log.e("TAG","INITTABLES ");
            useCaseHandler.execute(initTables, InitTables.Params.withCacheTime(1), new DisposableSubscriber<DomainObjectStorable>() {
                @Override
                public void onNext(DomainObjectStorable item) {

                    if(item instanceof Genre) {
                        Log.e("TAG", "INITTABLES " + ((Genre) item).name());
                    }
                    if(item instanceof Configuration) {
                        Log.e("TAG", "INITTABLES " + ((Configuration) item).baseUrl());
                    }

                }

                @Override
                public void onError(Throwable t) {
                    Log.e("TAG","ERROR"+t);
                }

                @Override
                public void onComplete() {
                    Log.e("TAG","onComplete");
                }
            });
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


//            this.processImageHelper
//                    .load(homeGalleryList)
//                    .listener(new ProcessImageHelper.ProcessImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","GALLERY LIST IMAGES LOADED");
//                        //    view.navigateToHome(map);
//
//                            return false;
//                        }
//                    })
//                    .backdrop()
//                    .high()
//                    .preload();

        //    loadHomePersonrGallery(map);

//            this.processImageHelper
//                    .load(homeGalleryList.get(0))
//                    .listener(new ProcessImageHelper.ProcessImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            return false;
//                        }
//                    })
//                    .backdrop()
//                    .high()
//                    .into(view.getImageView());

            List<MediaItem> nowplayingList = ProcessData.createNowPlayingGalleryList(map);
//            this.retroImage
//                    .load(nowplayingList)
//                    .poster()
//                    .huge()
//                    .preload(new RetroImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            Log.e("TAG","IMAGE LOAD FAILED.");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","IMAGE LOADED..");
//                            return false;
//                        }
//                    });
            List<MediaItem> nowplayingList2 = ProcessData.createTopRatedGalleryList(map);
            this.retroImage
                    .load(nowplayingList2.get(1))
                    .Backdrop()
                    .original()
                    .into(view.getCustomImageView(),new RetroImageRequestListener() {
                        @Override
                        public boolean onLoadFailed() {
                            Log.e("TAG","IMAGES LOAD FAILED.");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady() {
                            Log.e("TAG","ALL IMAGES PRELOADED...!");
                            return false;
                        }
                    });


            view.getImageSliderView().startSlide(nowplayingList,this.retroImage);
            view.getImageSliderView2().startSlide(nowplayingList2,this.retroImage);

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
//                             Log.e("TAG","IMAGE LOAD FAILED");
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
//            this.retroImage
//                    .load(personList)
//                    .Profile()
//                    .w632()
//
//
//                    .preload(new RetroImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            Log.e("TAG","IMAGE LOAD FAILED");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","IMAGE  LOAD");
//                            return false;
//                        }
//                    });

//            this.retroImage
//                    .load(nowplayingList)
//                    .Poster().w342()
//                    .preload( new RetroImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            Log.e("TAG","IMAGE LOAD FAILED");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","ALL IMAGES PRELOAD");
//                            return false;
//                        }
//                    });

//            this.retroImage
//                    .load("https://i.gifer.com/2Gr0.gif")
//                    .Poster().original()
//                    .preload( new RetroImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            Log.e("TAG","IMAGE LOAD FAILED");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","ALL IMAGES PRELOAD");
//                            return false;
//                        }
//                    });










//            this.retroImage
//                    .load("https://i.gifer.com/2Gr0.gif")
//                    .into(view.getImageView(),new RetroImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            Log.e("TAG","IMAGE LOAD FAILED");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","IMAGE LOADED");
//                            return false;
//                        }
//                    });





//            this.retroImage
//                    .load(homeGalleryList)
//                    .backdrop()
//                    .medium()
//                    .preload(new RetroImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            Log.e("TAG","IMAGE LOAD FAILED");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","IMAGE LOADED");
//                            return false;
//                        }
//                    });





//
//                    .load(gifUrl)
//                    .into(view.getImageView(),new RetroImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                                      Log.e("TAG","STRING....."+obj+" ");
//                            return false;
//                        }
//                    });

            //                    .load("https://i.gifer.com/2Gr0.gif")












//            this.processImageHelper
//                    .load(homeGalleryList.get(10))
//                    .listener(new ProcessImageHelper.ProcessImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            return false;
//                        }
//                    })
//                    .profile()
//                    .high()
//                    .into(imageView);
//
//                    ;
//            this.processImageHelper
//                    .load(homeGalleryList)
//                    .listener(new ProcessImageHelper.ProcessImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            Log.e("TAG","IMAGES LOADING FAILED");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","ALL IMAGES PRELOADED");
//                            return false;
//
//                        }
//                    })
//                    .backdrop()
//                    .high()
//                    .preload();



//            List<MediaItem> nowPlayingGalleryList = ProcessData.createNowPlayingGalleryList(map);
//
//            CustomImageView customImageView = view.getCustomImageView();
//
//            this.processImageHelper
//                    .load(nowPlayingGalleryList.get(9))
//                    .listener(new ProcessImageHelper.ProcessImageRequestListener() {
//
//
//
//                        @Override
//                        public boolean onLoadFailed() {
//                            Log.e("TAG","CUSOM IMAGES LOADING FAILED");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","CUSTOM IMAGES LOADED");
//                            return false;
//
//                        }
//                    })
//                    .poster()
//                    .high()
//                    .into(customImageView);



//            this.processImageHelper
//                    .load("https://i.gifer.com/2Gr0.gif")
//                    .listener(new ProcessImageHelper.ProcessImageRequestListener() {
//                        @Override
//                        public boolean onLoadFailed() {
//                            Log.e("TAG","IMAGES LOADING FAILED");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady() {
//                            Log.e("TAG","ALL IMAGES LOADED");
//                            return false;
//
//                        }
//                    })
//                    .gif()
//                    .into(imageView);













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