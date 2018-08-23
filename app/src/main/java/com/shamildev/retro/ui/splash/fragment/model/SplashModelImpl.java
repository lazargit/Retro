package com.shamildev.retro.ui.splash.fragment.model;

import android.util.Log;

import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.helper.DataReloading;
import com.shamildev.retro.domain.interactor.GetGenre;
import com.shamildev.retro.domain.interactor.GetMovieById;
import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetNowPlayingTVShows;
import com.shamildev.retro.domain.interactor.GetPopularPerson;
import com.shamildev.retro.domain.interactor.GetTMDBConfiguration;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.interactor.InitTables;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar.
 */
@PerFragment
public class SplashModelImpl extends SplashModel {


    @Inject
    protected DataConfig dataConfig;
    @Inject
    protected AppConfig appConfig;
    @Inject
    DataReloading dataReloading;

    private  UseCaseHandler useCaseHandler;
    private  GetTMDBConfiguration getTMDBConfiguration;
    private  GetGenre getGenre;
    private  GetUpcomingMovies getUpcomingMovies;
    private  GetTopRatedMovies getTopRatedMovies;
    private  GetNowPlayingMovies getNowPlayingMovies;
    private  GetNowPlayingTVShows getNowPlayingTVShows;
    private  GetMovieById getMovieById;
    private  InitTables initTables;
    private  GetPopularPerson getPopularPerson;
    private  SplashPresenter pres;
    private HashMap<String,ResultWrapper> map = new HashMap<>();
    private final ArrayList<String> mListTopic = new ArrayList<String>();



    @Inject
    public SplashModelImpl(
                           UseCaseHandler useCaseHandler,
                           InitTables initTables,
                           GetTMDBConfiguration getTMDBConfiguration,
                           GetGenre getGenre,
                           GetUpcomingMovies getUpcomingMovies,
                           GetNowPlayingMovies getNowPlayingMovies,
                           GetTopRatedMovies getTopRatedMovies,
                           GetNowPlayingTVShows getNowPlayingTVShows,
                           GetPopularPerson getPopularPerson,
                           GetMovieById getMovieById) {



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
        this.mListTopic.add(AppConfig.NOWPLAYINGKEY);
        this.mListTopic.add(AppConfig.NOWPLAYINGTVKEY);
        this.mListTopic.add(AppConfig.UPCOMMINGKEY);
        this.mListTopic.add(AppConfig.TOPRATEDKEY);
        this.mListTopic.add(AppConfig.POPULARPERSONKEY);
        this.mListTopic.add(AppConfig.HOMEHEADERKEY);
    }




    @Override
    public Boolean checkUser() {
        Log.e("HomePageModelImpl","CHECK USER");


        return true;
    }

    @Override
    public void initData() {

        Log.e("TAG","INITTABLES "+dataConfig.language()+" ");
        useCaseHandler.execute(initTables, InitTables.Params.with(dataConfig.language()), new DisposableSubscriber<String>() {
            @Override
            public void onNext(String item) {

                Log.e("TAG", "INITTABLES " + item);
               // view.makeToast(item);

            }

            @Override
            public void onError(Throwable t) {
                Log.e("TAG","ERROR"+t);
            }

            @Override
            public void onComplete() {

                Log.e("TAG","onComplete"+ Thread.currentThread().getName());
               // initConfiguration();
            }
        });

    }

    @Override
    public void initConfiguration(){

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

                //retroImage.setConfigurations(appConfig.getConfigurations());
              initGenres();

              Log.e("onComplete",">> initConfiguration "+ appConfig.getConfigurations().baseUrl());


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
                        presenter.finish(map);
                    }

                    @Override
                    public void onDataError() {

                    }
                });


            }
        });

    }



}
