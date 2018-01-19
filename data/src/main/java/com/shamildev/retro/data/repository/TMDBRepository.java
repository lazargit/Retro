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


import com.shamildev.retro.data.config.DataConfig;



import com.shamildev.retro.data.entity.mapper.EntityMapperHolder;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.data.net.TMDBServices;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Reusable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;


/**
 * An implementation of {@link RemoteRepository}.
 */
@Reusable
final class TMDBRepository implements RemoteRepository{


    private final TMDBServices tmdbServices;
    private final EntityMapperHolder entityMapperHolder;
    private final DataConfig dataConfig;

    @Inject
    TMDBRepository(@Named("TMDBServices") TMDBServices tmdbServices, EntityMapperHolder entityMapperHolder, DataConfig dataConfig) {
        this.tmdbServices = tmdbServices;

        this.entityMapperHolder = entityMapperHolder;
        this.dataConfig = dataConfig;
    }

    @Override
    public Observable<MovieWrapper> getTestService() {
        return   Observable.create(e -> {

            Single<MovieWrapper> map = tmdbServices.fetchUpcomingMovies(dataConfig.authClientSecret(), "1", dataConfig.language())
                    .map(movieWrapperEntity -> {
                        Timber.d("getTestService",movieWrapperEntity);



                        return entityMapperHolder.movieWrapperEntityMapper().map(movieWrapperEntity);
                    });


            e.onNext(map.blockingGet());
        });
    }

    @Override
    public Flowable<Configuration> fetchConfiguration() {

        return  tmdbServices
                .fetchConfiguration(dataConfig.authClientSecret()).toFlowable()
                .map(entityMapperHolder.configurationEntityMapper()::map);

    }



    @Override
    public Flowable<List<Genre>> fetchGenre( Constants.MEDIA_TYPE mediaType) {

        return Flowable.create(emitter ->
                        tmdbServices
                                .fetchGenre(mediaType.toString(), dataConfig.authClientSecret(), dataConfig.language())  // fetch Genre from API
                                .map(responseGenre -> Observable.fromIterable(responseGenre.getGenres())                 // Observe Genre-List from HTTP Response
                                        .map(genreEntity -> {                                                            // Map GenreEntity to Genre Singlelist
                                            Genre genre = entityMapperHolder.genreEntityMapper().map(genreEntity);

                                            return Genre.add(genre,
                                                    mediaType.toString(),
                                                    dataConfig.language(),
                                                    0L);


                                        }).toList())

                                .subscribe(
                                        new SingleObserver<Single<List<Genre>>>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {
                                            }

                                            @Override
                                            public void onSuccess(Single<List<Genre>> listSingle) {

                                                emitter.onNext(listSingle.blockingGet());
                                                emitter.onComplete();

                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                emitter.onError(e);
                                            }
                                        }),
                BackpressureStrategy.LATEST );







//        return Flowable.create(emitter ->
//                        tmdbServices.getGenre(mediaType, dataConfig.authClientSecret(), language)  // fetch GenreEntity from API
//                                .map(responseGenre -> Observable.fromIterable(responseGenre.getGenres()) // Observe GenreEntity-List from HTTP ResponseEntity
//                                        .map(entityMapperHolder.genreEntityMapper()::map)
//
//                                       // .map(genre -> Mapper.Genre_TO_GenreModel(genre,language,mediaType)).toList()) // Map GenreEntity-List to GenreModel Singlelist
//
//                                .subscribe(new DisposableObserver<GenreEntity>() {
//                                    @Override
//                                    public void onNext(GenreEntity genre) {
//
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable e) {
//
//                                    }
//
//                                    @Override
//                                    public void onComplete() {
//
//                                    }
//                                }),
//                BackpressureStrategy.LATEST );

    }


    @Override
    public Flowable<Movie> fetchMovie(int id) {

        return tmdbServices.fetchMovie(String.valueOf(id),dataConfig.authClientSecret(), dataConfig.language(), null).toFlowable()
                .map(movie -> {
                    Timber.d("fetchMovie", movie);

                    return entityMapperHolder.movieDetailsEntityMapper().map(movie);
                });

    }


    @Override
    public Flowable<MovieWrapper> fetchUpcomingMovies(int page) {

        return tmdbServices.fetchUpcomingMovies(dataConfig.authClientSecret(), String.valueOf(page), dataConfig.language()).toFlowable()
                .map(movieWrapperEntity -> {
                    Timber.d("fetchUpcomingMovies", movieWrapperEntity);

                    return entityMapperHolder.movieWrapperEntityMapper().map(movieWrapperEntity);
                });

    }

    //    @Override
//    public Observable<ResponseEntity> getTestService() {
//        return Observable.create(new ObservableOnSubscribe<ResponseEntity>() {
//            @Override
//            public void subscribe(ObservableEmitter<ResponseEntity> e) throws Exception {
//                ResponseEntity movieWrapper = tmdbServices.fetchUpcomingMovies(UrlManager.PUBLIC_KEY_MDB, "1", "de-DE", "").blockingGet();
//                e.onNext(movieWrapper);
//            }
//        });
//    }




}
