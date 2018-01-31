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


import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;


public final class GetUpcomingMovies implements UseCaseFlowable<ParamsBasic,MovieWrapper> {

    private final RemoteRepository repository;
    private final CacheRepository cache;

    @Inject
    GetUpcomingMovies(RemoteRepository repository, CacheRepository cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Flowable<MovieWrapper> execute(ParamsBasic params) {
        int page = ((Params) params).page;




        return  this.repository.fetchUpcomingMovies(page)
                .flatMap(movieWrapper -> repository.fetchUpcomingMovies(2))
                .flatMapCompletable(new Function<MovieWrapper, CompletableSource>() {
                    @Override
                    public CompletableSource apply(MovieWrapper movieWrapper) throws Exception {
                      return Flowable.fromIterable(movieWrapper.results())
                                .flatMapCompletable(new Function<Movie, CompletableSource>() {
                                    @Override
                                    public CompletableSource apply(Movie movie) throws Exception {
                                        return cache.save(movie);
                                    }
                                });
                    }
                }).toFlowable();


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
