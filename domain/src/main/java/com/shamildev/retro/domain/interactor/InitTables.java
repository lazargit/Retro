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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shamildev.retro.domain.DomainObjectStorable;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.LocalRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;
import com.shamildev.retro.domain.util.DateUtil;
import com.shamildev.retro.domain.util.StreamJsonFile;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Use case for getting a businesses with a given id.
 */
public final class InitTables implements UseCaseFlowable<ParamsBasic,DomainObjectStorable> {

    private final RemoteRepository repository;
    private final CacheRepository cache;
    private final LocalRepository local;
    private DataConfig dataConfig;
    private ClassLoader classLoader;

    @Inject
    AppConfig appConfig;


    @Inject
    InitTables(RemoteRepository repository, CacheRepository cache,LocalRepository localRepository, DataConfig dataConfig) {
        this.repository = repository;
        this.cache = cache;
        this.local = localRepository;
        this.dataConfig = dataConfig;
    }


    //return a single symbol from the list of symbols, or an error to catch if not.
    private Flowable<Configuration> lookupStockSymbol() {



        return   repository.fetchConfiguration()
                 .flatMap(this::saveToCache)

                ;
    }

    @Override
    public Flowable<DomainObjectStorable> execute(ParamsBasic params) {
        int cacheTime = ((Params) params).cacheTime;

//       saveToCache(this.local.streamJsonCongiguration().blockingSingle())
//
//       .subscribeOn();
      return this.local.streamJsonCongiguration()
                .cast(Configuration.class)
                .flatMap(this::saveToCache)
                .flatMap(s->local.streamJsonGenres().doOnNext(d->this.saveToCache((Genre) d)))





                ;

       // return this.local.streamJsonCongiguration();


//        return   fetchConfigurationFromCache()
//
//                .switchIfEmpty(fetchConfigurationFromNet())
//                .map(configuration -> {
//
//                    if(DateUtil.isCacheTimeExpired(configuration, cacheTime).blockingSingle()){
//                        return fetchConfigurationFromNet().blockingSingle();
//                    }
//                    return configuration;
//                })
//
//
//                .map(configuration -> fetchConfigurationFromCache().blockingSingle())
//                .map((Configuration configuration) -> {
//                    appConfig.setConfigurations(configuration);
//                    return configuration;
//                });



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


    public Flowable<Configuration> saveToCache(Configuration model ) {
        System.out.println("saveToCache"+model.baseUrl());
        return   Flowable.defer(() -> {
            try {
                return Flowable.just(model)
                                .flatMap(configModel -> Flowable.just(configModel)
                                                                .flatMapCompletable(configuration -> cache.insertTMDbConfiguration(model, DateUtil.NOW()))
                                                              //  .flatMapCompletable(cache::saveTMDbConfiguration)
                                                                .toFlowable()
                                                                .startWith(configModel)

                                ).cast(Configuration.class);

            } catch (Exception e) {

                return Flowable.error(e);

            }
        });


    }
    public Flowable<Configuration> fetchConfigurationFromNet() {
        System.out.println("fetchConfigurationFromNet");


        return    repository.fetchConfiguration()
                  .flatMap(this::saveToCache)

                ;


    }


    public Flowable<Configuration> fetchConfigurationFromCache() {
        System.out.println("fetchConfigurationFromCache");

        return  cache.fetchConfiguration()
                .subscribeOn(Schedulers.computation())
                ;

    }



//    <K extends Entity, V extends DomainObject> List<V> mapToV(EntityMapper<K, V> entityMapper,
//                                                              List<K> kList) throws MappingError {
//        List<V> vList = new ArrayList<>(kList.size());
//        for (K k : kList) {
//            vList.add(entityMapper.map(k));
//        }
//        return vList;
//    }
public Flowable<List<Genre>> streamData(ClassLoader classLoader, String language){

    InputStream inputStream = classLoader.getResourceAsStream("GenreMovieTestData.json");
    String s = StreamJsonFile.stream(inputStream);
    if(s.length()>0){
        JsonParser jsonParser = new JsonParser();
        JsonArray arrayFromString = jsonParser.parse(s).getAsJsonArray();
        List<Genre> genres = Observable.fromIterable(arrayFromString)
                .map(new Function<JsonElement, Genre>() {
                    @Override
                    public Genre apply(JsonElement jsonElement) throws Exception {

                        JsonObject provider = jsonElement.getAsJsonObject();
                        return Genre.builder()
                                .id(provider.get("id").getAsInt())
                                .name(provider.get("name").toString())
                                .type(Constants.MEDIA_TYPE.MOVIE.toString())
                                .lastUpdate(0L)
                                .language(language)
                                .build();

                    }
                }).toList().blockingGet();

        return Flowable.fromArray(genres);

    }

    return Flowable.empty();


}




    public static final class Params implements ParamsBasic {

        private Params() { }

        private int cacheTime = 0;

        public Params(int cacheTime) {
            this.cacheTime = cacheTime;
        }



        public static InitTables.Params withCacheTime(int cacheTime) {
            return new InitTables.Params(cacheTime);
        }



}
}
