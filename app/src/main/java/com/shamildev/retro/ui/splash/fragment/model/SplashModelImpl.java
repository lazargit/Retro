package com.shamildev.retro.ui.splash.fragment.model;

import android.util.Log;

import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.MediaItem;
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
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar.
 *             //https://media.giphy.com/media/87kcVw4PjxGr6/giphy.gif
 //            ArrayList<String> gifUrl = new ArrayList<>();
 //            gifUrl.add("https://i.gifer.com/2Gr0.gif");
 //            gifUrl.add("https://media.giphy.com/media/87kcVw4PjxGr6/giphy.gif");
 //            gifUrl.add("https://media.giphy.com/media/lR8eXSeYUF5vO/giphy.gif");
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
      //  List<MediaItem> personList = ProcessData.createHomePersonList(map);
        useCaseHandler.execute(initTables, InitTables.Params.with(dataConfig.language()), new DisposableSubscriber<String>() {
            @Override
            public void onNext(String item) {



            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {



            }
        });

    }

    @Override
    public void initConfiguration(){

        useCaseHandler.execute(getTMDBConfiguration,GetTMDBConfiguration.Params.withCacheTime(1), new DisposableSubscriber<Configuration>() {
            @Override
            public void onNext(Configuration configuration) {
                presenter.configRetroImage(configuration);

            }

            @Override
            public void onError(Throwable t) {
                presenter.onError(t);

            }

            @Override
            public void onComplete() {
              initGenres();
                System.out.println("initConfiguration onComplete "+appConfig.getConfigurations().posterSizes());
            }
        });


    }

    private void initGenres() {
        useCaseHandler.execute(getGenre, GetGenre.Params.with(dataConfig.language(), dataConfig.maxCacheTime()), new DisposableSubscriber<List<Genre>>() {
            @Override
            public void onNext(List<Genre> genres) {

            }

            @Override
            public void onError(Throwable t) {
                presenter.onError(t);
            }

            @Override
            public void onComplete() {
                Log.d("onComplete",">> genre init");
                 loadNowPlayingMovies();

            }
        });

    }



    private DisposableSubscriber loadNowPlayingMoviesSubscriber = new DisposableSubscriber<ResultWrapper>() {
        @Override
        public void onNext(ResultWrapper resultWrapper) {

            presenter.setBackgroundImage(resultWrapper.results());
        }

        @Override
        public void onError(Throwable t) {
            presenter.onError(t);
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

            map.put(AppConfig.UPCOMMINGKEY,resultWrapper);
        }

        @Override
        public void onError(Throwable t) {
            presenter.onError(t);

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
            presenter.onError(t);

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
            presenter.onError(t);

        }

        @Override
        public void onComplete() {
            presenter.finish(map);
        }
    };
    private void loadPopularPerson(){
        useCaseHandler.execute(getPopularPerson, GetPopularPerson.Params.withPage(1),loadPopularPersonSubscriber);

    }





}
