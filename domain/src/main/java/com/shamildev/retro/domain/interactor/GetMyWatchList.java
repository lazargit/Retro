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

import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.DateUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Use case for getting movies in my watchlist.
 */
public final class GetMyWatchList implements UseCaseFlowable<ParamsBasic, List<Movie>> {


    private final CacheRepository cache;

    @Inject
    GetMyWatchList(CacheRepository cache) {

        this.cache = cache;
    }




    @Override
    public Flowable<List<Movie>> execute(ParamsBasic params) {




        return   Flowable.empty();


    }






    public static final class Params implements ParamsBasic {

        private Params() { }

        public static GetMyWatchList.Params justVoid() {
            return new GetMyWatchList.Params();
        }

    }
}
