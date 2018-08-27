package com.shamildev.retro.domain.interactor;


import com.shamildev.retro.domain.helper.ProcessData;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;


public final class GetTopRatedMovies implements UseCaseFlowable<ParamsBasic,ResultWrapper> {

    private final RemoteRepository repository;
    private final CacheRepository cache;

    @Inject
    GetTopRatedMovies(RemoteRepository repository, CacheRepository cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Flowable<ResultWrapper> execute(ParamsBasic params) {
        int page = ((Params) params).page;

        return  this.repository.fetchTopRatedMovies(page)
                .flatMap(resultWrapper -> ProcessData.prepareResultWrapper(resultWrapper,cache.fetchWatchList().blockingLast()));


//        return  this.repository.fetchTopRatedMovies(page)
//                .flatMap(movieWrapper -> repository.fetchTopRatedMovies(2))
//                .flatMapCompletable(movieWrapper -> Flowable.fromIterable(movieWrapper.results())
//                        .flatMapCompletable(movie -> cache.save((Movie) movie))).toFlowable();

    }


    public static final class Params implements ParamsBasic {

        private int page = 1;

        public Params(int page) {
            this.page = page;
        }
        public static GetTopRatedMovies.Params withPage(int page) {
            return new GetTopRatedMovies.Params(page);
        }

    }

}
