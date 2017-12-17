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

package com.shamildev.retro.data.repository;

import com.shamildev.retro.data.entity.MovieWrapperEntity;
import com.shamildev.retro.data.entity.mapper.EntityMapperHolder;
import com.shamildev.retro.data.net.TMDBServices;
import com.shamildev.retro.data.net.UrlManager;
import com.shamildev.retro.domain.repository.MovieWrapper;
import com.shamildev.retro.domain.repository.RemoteRepository;
import javax.inject.Inject;
import dagger.Reusable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import timber.log.Timber;


/**
 * An implementation of {@link RemoteRepository}.
 */
@Reusable
final class TMDBRepository implements RemoteRepository{


    private final TMDBServices tmdbServices;
    private final EntityMapperHolder entityMapperHolder;

    @Inject
    TMDBRepository(TMDBServices tmdbServices,EntityMapperHolder entityMapperHolder) {
        this.tmdbServices = tmdbServices;
        this.entityMapperHolder = entityMapperHolder;
    }

    @Override
    public Observable<MovieWrapper> getTestService() {
        return   Observable.create(new ObservableOnSubscribe<MovieWrapper>() {
            @Override
            public void subscribe(ObservableEmitter<MovieWrapper> e) throws Exception {

                Single<MovieWrapper> map = tmdbServices.fetchUpcomingMovies(UrlManager.PUBLIC_KEY_MDB, "1", "de-DE")
                        .map(new Function<MovieWrapperEntity, MovieWrapper>() {
                            @Override
                            public MovieWrapper apply(MovieWrapperEntity movieWrapperEntity) throws Exception {
                                Timber.d("getTestService",movieWrapperEntity);

                                return entityMapperHolder.movieWrapperEntityMapper().map(movieWrapperEntity);
                            }
                        });


                e.onNext(map.blockingGet());
            }
        });
    }


//    @Override
//    public Observable<MovieWrapperEntity> getTestService() {
//        return Observable.create(new ObservableOnSubscribe<MovieWrapperEntity>() {
//            @Override
//            public void subscribe(ObservableEmitter<MovieWrapperEntity> e) throws Exception {
//                MovieWrapperEntity movieWrapper = tmdbServices.fetchUpcomingMovies(UrlManager.PUBLIC_KEY_MDB, "1", "de-DE", "").blockingGet();
//                e.onNext(movieWrapper);
//            }
//        });
//    }




}
