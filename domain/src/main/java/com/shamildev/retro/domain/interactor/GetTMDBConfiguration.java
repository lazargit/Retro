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

import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.DateUtil;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Use case for getting a businesses with a given id.
 */
public final class GetTMDBConfiguration implements UseCaseFlowable<ParamsBasic, Configuration> {

    private final RemoteRepository repository;
    private final CacheRepository cache;
    private DataConfig dataConfig;

    @Inject
    AppConfig appConfig;


    @Inject
    GetTMDBConfiguration(RemoteRepository repository,CacheRepository cache,DataConfig dataConfig) {
        this.repository = repository;
        this.cache = cache;
        this.dataConfig = dataConfig;
    }


    //return a single symbol from the list of symbols, or an error to catch if not.
    private Flowable<Configuration> lookupStockSymbol() {

        System.out.println("lookupStockSymbol");

        return   repository.fetchConfiguration()
                 .flatMap(this::saveToCache)

                ;
    }

    @Override
    public Flowable<Configuration> execute(ParamsBasic params) {
        int cacheTime = ((Params) params).cacheTime;

        System.out.println("### "+appConfig.getScreenSize());

        return   fetchConfigurationFromCache()

                .switchIfEmpty(fetchConfigurationFromNet())
                .map(configuration -> {

                    if(DateUtil.isCacheTimeExpired(configuration, cacheTime).blockingSingle()){
                        return fetchConfigurationFromNet().blockingSingle();
                    }
                    return configuration;
                })


                .map(configuration -> fetchConfigurationFromCache().blockingSingle())
                .map((Configuration configuration) -> {
                    appConfig.setConfigurations(configuration);
                    return configuration;
                });



    }


    public Flowable<Configuration> saveToCache(Configuration model ) {

        System.out.println("saveToCache"+model.baseUrl());
        return   Flowable.defer(() -> {

            try {

                return Flowable.just(model)
                                .flatMap(configModel -> Flowable.just(configModel)
                                                                .flatMapCompletable(cache::saveTMDbConfiguration)
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




    public static final class Params implements ParamsBasic {

        private Params() { }

        private int cacheTime = 0;

        public Params(int cacheTime) {
            this.cacheTime = cacheTime;
        }



        public static GetTMDBConfiguration.Params withCacheTime(int cacheTime) {
            return new GetTMDBConfiguration.Params(cacheTime);
        }



}
}
