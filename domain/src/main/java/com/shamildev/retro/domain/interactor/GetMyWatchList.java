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

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
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

        // return cache.fetchWatchList().cast(Movie.class).toList().toFlowable();

        Movie build = Movie.builder()
                .id(1L)
                .title("schamil")
                .overview("nichts")
                .adult(false)
                .originalTitle("schamilo")
                .originalLanguage("de")
                .popularity(7.5F)
                .video(false)
                .voteAverage(7.7F)
                .build();


        Movie build2 = Movie.builder()
                .id(1L)
                .title("katja")
                .overview("nichts")
                .adult(false)
                .originalTitle("schamilo")
                .originalLanguage("de")
                .popularity(7.5F)
                .video(false)
                .voteAverage(7.7F)
                .build();
        ArrayList<Movie> list = new ArrayList<>();
                         list.add(build);
                         list.add(build2);


        //  return Flowable.fromArray(list);
      return cache.fetchWatchList();


    }






    public static final class Params implements ParamsBasic {

        private Params() { }

        public static GetMyWatchList.Params justVoid() {
            return new GetMyWatchList.Params();
        }

    }
}
