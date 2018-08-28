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


import android.os.Build;
import android.text.Html;
import android.util.Log;

import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.config.DataConfig;


import com.shamildev.retro.data.entity.mapper.EntityMapperHolder;
import com.shamildev.retro.data.net.TMDBServices;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Credits;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.GuestSession;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Reusable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.http.Url;
import timber.log.Timber;


/**
 * An implementation of {@link RemoteRepository}.
 */
@Reusable
public final class TMDBRepository implements RemoteRepository{


    private final TMDBServices tmdbServices;
    private final EntityMapperHolder entityMapperHolder;
    private final DataConfig dataConfig;
    private String decode;
    private String encode1;
    private String encode2;

    @Inject
    TMDBRepository(@Named("TMDBServices") TMDBServices tmdbServices, EntityMapperHolder entityMapperHolder, DataConfig dataConfig) {
        this.tmdbServices = tmdbServices;
        this.entityMapperHolder = entityMapperHolder;
        this.dataConfig = dataConfig;
    }

    @Override
    public Observable<MovieWrapper> getTestService() {
        return   Observable.create(e -> {

            Single<MovieWrapper> map = tmdbServices.fetchUpcomingMovies(dataConfig.authClientSecret(), "1", dataConfig.language(),dataConfig.country())
                    .map(movieWrapperEntity -> {
                        Timber.d("getTestService",movieWrapperEntity);



                        return entityMapperHolder.movieWrapperEntityMapper().map(movieWrapperEntity);
                    });


            e.onNext(map.blockingGet());
        });
    }

    @Override
    public Flowable<GuestSession> fetchGuestSession() {
        return  tmdbServices
                .guestSession(dataConfig.authClientSecret()).toFlowable()
                .flatMap(entity -> Flowable.just(new GuestSession(entity.getSuccess(), entity.getGuestSessionId(), entity.getExpiresAt())))
                ;
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
    public Flowable<Movie> fetchMovie(Long movieId, String appendToResponse,String includeImageLanguage) {

        return tmdbServices.fetchMovie(String.valueOf(movieId),dataConfig.authClientSecret(), dataConfig.language(), appendToResponse,includeImageLanguage).toFlowable()
                .map(movie -> {
                    Timber.d("fetchMovie", movie);

                    return entityMapperHolder.movieDetailsEntityMapper().map(movie);
                });

    }


    @Override
    public Flowable<Images> fetchImages(Long movieId) {

        return tmdbServices.fetchImages(String.valueOf(movieId),dataConfig.authClientSecret())
                .toFlowable()
                .map(imagesResponse -> {
                    Timber.d("fetchImages", imagesResponse.getId());

                    return entityMapperHolder.imagesResponseEntityMapper().map(imagesResponse);
                });

    }


    @Override
    public Flowable<Credits> fetchCredits(Long movieId) {

        return tmdbServices.fetchCredits(String.valueOf(movieId),dataConfig.authClientSecret())
                .toFlowable()
                .map(creditsResponse ->  {
                    Timber.d("fetchCredits", creditsResponse.getId());

                    return entityMapperHolder.creditsResponseEntityMapper().map(creditsResponse);
                });

    }


    @Override
    public Flowable<ResultWrapper> fetchUpcomingMovies(int page) {

        return tmdbServices.fetchUpcomingMovies(dataConfig.authClientSecret(), String.valueOf(page), dataConfig.language(),dataConfig.country())
                .toFlowable()
                .map(responseEntity1 -> {
                            Flowable.fromIterable(responseEntity1.getResults())
                                    .map(result -> {
                                        result.setMediaType(Constants.MEDIA_TYPE.MOVIE.toString());
                                        return result;

                                    }).subscribe();

                    return responseEntity1;

                })
                .map(movieWrapperEntity -> entityMapperHolder.resultWrapperEntityMapper().map(movieWrapperEntity));

    }

    @Override
    public Flowable<ResultWrapper> fetchNowPlayingMovies(int page) {

        return tmdbServices.fetchNowPlayingMovies(dataConfig.authClientSecret(), String.valueOf(page), dataConfig.language(),dataConfig.country())
                .toFlowable()
                .map(responseEntity1 -> {
                    Flowable.fromIterable(responseEntity1.getResults())
                            .map(result -> {
                                result.setMediaType(Constants.MEDIA_TYPE.MOVIE.toString());
                                return result;

                            }).subscribe();

                    return responseEntity1;

                })
                .map(movieWrapperEntity -> entityMapperHolder.resultWrapperEntityMapper().map(movieWrapperEntity));

    }

    @Override
    public Flowable<ResultWrapper> fetchTopRatedMovies(int page) {
         return tmdbServices.fetchTopRatedMovies(dataConfig.authClientSecret(), String.valueOf(page), dataConfig.language(),dataConfig.country())
                 .toFlowable()
                 .map(responseEntity1 -> {
                     Flowable.fromIterable(responseEntity1.getResults())
                             .map(result -> {
                                 result.setMediaType(Constants.MEDIA_TYPE.MOVIE.toString());
                                 return result;

                             }).subscribe();

                     return responseEntity1;

                 })
                .map(movieWrapperEntity -> entityMapperHolder.resultWrapperEntityMapper().map(movieWrapperEntity));
    }

    @Override
    public Flowable<MovieWrapper> fetchRecommendations(Long movieId, int page) {
        return tmdbServices.fetchRecommendations(String.valueOf(movieId),dataConfig.authClientSecret(),dataConfig.language(),String.valueOf(page)).toFlowable()
                .map(movieWrapperEntity -> {
                    Timber.d("fetchRecommendations", movieWrapperEntity);

                    return entityMapperHolder.movieWrapperEntityMapper().map(movieWrapperEntity);
                });
    }

    @Override
    public Flowable<MovieWrapper> fetchSimilarMovies(Long movieId, int page) {
        return tmdbServices.fetchSimilarMovies(String.valueOf(movieId),dataConfig.authClientSecret(),dataConfig.language(),String.valueOf(page)).toFlowable()
                .map(movieWrapperEntity -> {
                    Timber.d("fetchSimilarMovies", movieWrapperEntity);

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

    /*
    *
    *TVSHOWS
    */
    @Override
    public Flowable<TVShow> fetchTVShow(Long id, String appendToResponse, String includeImageLanguage) {

        return tmdbServices.fetchTVShow(String.valueOf(id),dataConfig.authClientSecret(), dataConfig.language(), appendToResponse,includeImageLanguage).toFlowable()
                .map(tvshow -> {
                    Timber.d("fetchTVShow", tvshow);

                    return entityMapperHolder.tvshowEntityMapper().map(tvshow);
                });

    }


    @Override
    public Flowable<ResultWrapper> fetchNowPlayingTVShow(int page) {

        return tmdbServices.fetchTVonAir(dataConfig.authClientSecret(), dataConfig.language(), String.valueOf(page))
                .toFlowable()
                .map(responseEntity1 -> {
                    Flowable.fromIterable(responseEntity1.getResults())
                            .map(result -> {
                                result.setMediaType(Constants.MEDIA_TYPE.TV.toString());
                                return result;

                            }).subscribe();

                    return responseEntity1;

                })
                .map(responseEntity -> entityMapperHolder.resultWrapperEntityMapper().map(responseEntity));


    }

    /*
       *
       *TSEARCH
       */
    @Override
    public Flowable<ResultWrapper> fetchMultiSearch(String quary, int page) {
     return tmdbServices.fetchMultiSearch(dataConfig.authClientSecret(),dataConfig.language(),quary,String.valueOf(page),"false",dataConfig.country()).toFlowable()

               .map(responseEntity ->  entityMapperHolder.resultWrapperEntityMapper().map(responseEntity));
    }


    /*
   *
   *TSEARCH
   */
    @Override
    public Flowable<ResultWrapper> fetchPopularPerson(int page) {
//        return tmdbServices.fetchPopularPerson(dataConfig.authClientSecret(),dataConfig.language(),String.valueOf(page)).toFlowable()
//
//                .map(responseEntity ->  entityMapperHolder.resultWrapperEntityMapper().map(responseEntity));


        return tmdbServices.fetchPopularPerson(dataConfig.authClientSecret(), dataConfig.language(), String.valueOf(page))
                .toFlowable()
                .map(responseEntity1 -> {
                    Flowable.fromIterable(responseEntity1.getResults())
                            .map(result -> {
                                result.setMediaType(Constants.MEDIA_TYPE.PERSON.toString());
                                return result;

                            }).subscribe();

                    return responseEntity1;

                })
                .map(responseEntity -> entityMapperHolder.resultWrapperEntityMapper().map(responseEntity));

    }


 /*
   *
   *DISCOVER
   */

    @Override
    public Flowable<ResultWrapper> fetchDiscover(Map<String, Object> map , int page) {




        return  tmdbServices
                .fetchDiscover(dataConfig.authClientSecret(), dataConfig.language(), String.valueOf(page),map)


                .toFlowable()
                .map(responseEntity1 -> {
                    Flowable.fromIterable(responseEntity1.getResults())
                            .map(result -> {
                                result.setMediaType(Constants.MEDIA_TYPE.MOVIE.toString());
                                return result;

                            })

                            .subscribe();

                    return responseEntity1;

                })
                .map(movieWrapperEntity -> entityMapperHolder.resultWrapperEntityMapper().map(movieWrapperEntity));

    }

}
