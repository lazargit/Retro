package com.shamildev.retro.ui.search.fragment.model;

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
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar on 22.08.2018.
 */
@PerFragment
public class SearchModelImpl extends SearchModel {


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
    public SearchModelImpl(
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







}
