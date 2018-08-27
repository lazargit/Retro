

package com.shamildev.retro.domain.interactor;


import com.shamildev.retro.domain.helper.ProcessData;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import javax.inject.Inject;

import io.reactivex.Flowable;



public final class GetUpcomingMovies implements UseCaseFlowable<ParamsBasic,ResultWrapper> {

    private final RemoteRepository repository;
    private final CacheRepository cache;

    @Inject
    GetUpcomingMovies(RemoteRepository repository, CacheRepository cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Flowable<ResultWrapper> execute(ParamsBasic params) {
        int page = ((Params) params).page;

         return  this.repository.fetchUpcomingMovies(page)
                   .flatMap(resultWrapper -> ProcessData.prepareResultWrapper(resultWrapper,cache.fetchWatchList().blockingLast()));


//        return  this.repository.fetchUpcomingMovies(page)
//                .flatMap(movieWrapper -> repository.fetchUpcomingMovies(2))
//                .flatMapCompletable(new Function<MovieWrapper, CompletableSource>() {
//                    @Override
//                    public CompletableSource apply(MovieWrapper movieWrapper) throws Exception {
//                      return Flowable.fromIterable(movieWrapper.results())
//                                .flatMapCompletable(new Function<Movie, CompletableSource>() {
//                                    @Override
//                                    public CompletableSource apply(Movie movie) throws Exception {
//                                        return cache.save(movie);
//                                    }
//                                });
//                    }
//                }).toFlowable();


    }


    public static final class Params implements ParamsBasic {

        private int page = 1;

        public Params(int page) {
            this.page = page;
        }
        public static GetUpcomingMovies.Params withPage(int page) {
            return new GetUpcomingMovies.Params(page);
        }

    }

}
