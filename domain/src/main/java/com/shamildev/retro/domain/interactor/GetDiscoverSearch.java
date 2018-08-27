package com.shamildev.retro.domain.interactor;


import com.shamildev.retro.domain.helper.ProcessData;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.params.Discover;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;


public final class GetDiscoverSearch implements UseCaseFlowable<ParamsBasic,ResultWrapper> {

    private final RemoteRepository repository;
    private final CacheRepository cache;

    @Inject
    GetDiscoverSearch(RemoteRepository repository, CacheRepository cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Flowable<ResultWrapper> execute(ParamsBasic params) {
        int page = ((Params) params).page;
        Map<String, Object> map = ((Params) params).map;
        return repository.fetchDiscover(
                map,
                page)
              //  .flatMap(resultWrapper -> ProcessData.prepareResultWrapper(resultWrapper,cache.fetchWatchList().blockingLast()))
//                .flatMapCompletable(movieWrapper -> Flowable.fromIterable(movieWrapper.results())
//                       .flatMapCompletable(movie -> cache.saveItemWatchList((Movie) movie))).toFlowable()
                ;




    }


    public static final class Params implements ParamsBasic {

        private int page = 1;
        private Map<String, Object> map;

        public Params(Map<String, Object> map,int page) {
            this.map = map;
            this.page = page;

        }


        public static GetDiscoverSearch.Params with(Map<String, Object> map, int page) {
            return new GetDiscoverSearch.Params(map,page);
        }


    }

}
