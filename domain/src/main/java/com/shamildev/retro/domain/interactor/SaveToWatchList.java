/*
 * Copyright 2017 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
