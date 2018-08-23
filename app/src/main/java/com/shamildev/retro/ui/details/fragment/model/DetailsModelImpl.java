package com.shamildev.retro.ui.details.fragment.model;

import android.util.Log;

import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.executor.UseCaseHandler;
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
import com.shamildev.retro.ui.common.presenter.Presenter;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar on 22.08.2018.
 */
@PerFragment
public class DetailsModelImpl extends DetailsModel {


    @Inject
    protected DataConfig dataConfig;
    @Inject
    protected AppConfig appConfig;

    private final UseCaseHandler useCaseHandler;
    private final GetTMDBConfiguration getTMDBConfiguration;
    private final GetGenre getGenre;
    private final GetUpcomingMovies getUpcomingMovies;
    private final GetTopRatedMovies getTopRatedMovies;
    private final GetNowPlayingMovies getNowPlayingMovies;
    private final GetNowPlayingTVShows getNowPlayingTVShows;
    private final GetMovieById getMovieById;
    private final InitTables initTables;
    private final GetPopularPerson getPopularPerson;
    private SplashPresenter presenter;


    @Inject
    public DetailsModelImpl(
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
                //initGenres();
              Log.d("onComplete",">> initConfiguration "+ appConfig.getConfigurations().baseUrl());

            }
        });


    }



}
