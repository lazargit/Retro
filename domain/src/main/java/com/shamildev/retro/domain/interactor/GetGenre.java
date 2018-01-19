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

import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;
import com.shamildev.retro.domain.util.DateUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;


/**
 * Use case for getting a businesses with a given id.
 */
public final class GetGenre implements UseCaseFlowable<ParamsBasic,List<Genre>> {

    private final RemoteRepository repository;
    private final CacheRepository cache;


    @Inject
    GetGenre(RemoteRepository repository,
             CacheRepository cache) {
        this.repository = repository;
        this.cache = cache;

    }



    public Flowable<List<Genre>> executeTest(ParamsBasic params) {


        return Flowable.empty();
    }

    @Override
    public Flowable<List<Genre>> execute(ParamsBasic params) {

        final int cacheTime = ((GetGenre.Params) params).cacheTime;
        final String language = ((GetGenre.Params) params).language;

        return  fetchAllGenreFromCache(language)
                .switchIfEmpty(fetchAllGenreFromNet())
                .sorted((o1, o2) -> Long.compare(o2.lastUpdate(), o1.lastUpdate()))
                .take(1)
                .map(genreModel ->
                                DateUtil.isCacheTimeExpired(genreModel,cacheTime)

                                .map(aBoolean -> (aBoolean) ? fetchAllGenreFromNet() : fetchAllGenreFromCache(language))
                                .blockingLast()
                                .toList()
                                .blockingGet()
                );

    }



    public Flowable<Genre> saveToCache(Genre genreModel ) {

        return Flowable.defer(() -> {

            try {

                return Flowable.just(genreModel)
                        .flatMap(genreMod -> Flowable.just(genreMod)
                                .flatMapCompletable(cache::saveGenre)
                                .toFlowable()
                                .startWith(genreMod)

                        ).cast(Genre.class);

            } catch (Exception e) {

                return Flowable.error(e);

            }
        });
    }

    public Flowable<Genre> fetchAllGenreFromNet() {

        Flowable<List<Genre>> listFlowable1 = this.repository.fetchGenre(Constants.MEDIA_TYPE.MOVIE);
        Flowable<List<Genre>> listFlowable2 = this.repository.fetchGenre(Constants.MEDIA_TYPE.TV);
        return Flowable.concat(listFlowable1,listFlowable2)
                .flatMap(Flowable::fromIterable)
                .flatMap(this::saveToCache)
                ;

    }

    public Flowable<Genre> fetchAllGenreFromCache(String language) {

        System.out.println("fetchAllGenreFromCache"+language);
        Flowable<List<Genre>> listFlowableMovie = this.cache.fetchGenre(Constants.MEDIA_TYPE.MOVIE, language);
        Flowable<List<Genre>> listFlowableTv = this.cache.fetchGenre(Constants.MEDIA_TYPE.TV, language);
       return Flowable.fromIterable(
                       Flowable.concat(listFlowableMovie, listFlowableTv).flatMap(Flowable::fromIterable)
                               .subscribeOn(Schedulers.computation())
                               .toList().blockingGet()
              );

    }


    public static final class Params implements ParamsBasic {



        private int cacheTime = 0;
        private String language;

        public Params(int cacheTime) {
            this.cacheTime = cacheTime;
        }
        public Params(int cacheTime,String language ) {
            this.cacheTime = cacheTime;
            this.language = language;
        }


        public static Params with(int cacheTime) {
            return new Params(cacheTime);
        }
        public static Params with(String language, int cacheTime) {
            return new Params(cacheTime,language);
        }



    }
}
