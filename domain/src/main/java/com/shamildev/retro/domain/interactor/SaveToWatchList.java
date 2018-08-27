package com.shamildev.retro.domain.interactor;

import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Use case for getting movies from watchlist.
 */
public final class SaveToWatchList implements UseCaseCompletable<ParamsBasic> {


    private final CacheRepository cache;

    @Inject
    SaveToWatchList(CacheRepository cache) {

        this.cache = cache;
    }




    @Override
    public Completable execute(ParamsBasic params) {
        DomainObject item = ((Params) params).item;

        return cache.saveItemWatchList(item);
    }


    public static final class Params implements ParamsBasic {

        private DomainObject item;
        private Params() { }
        private Params(DomainObject item) {
            this.item = item;
        }

        public static SaveToWatchList.Params justVoid() {
            return new SaveToWatchList.Params();

        }
        public static SaveToWatchList.Params withItem(DomainObject item) {
            return new SaveToWatchList.Params(item);
        }

    }



}
