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

package com.shamildev.retro.data.local;

import android.app.Application;

import com.shamildev.retro.data.cache.realm.mapper.RealmMapperHolder;
import com.shamildev.retro.data.local.json.JsonManager;
import com.shamildev.retro.data.local.load.StreamFileFromLocal;
import com.shamildev.retro.domain.DomainObjectStorable;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.repository.LocalRepository;
import com.shamildev.retro.domain.util.Constants;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Reusable;
import io.reactivex.Flowable;
import io.realm.Realm;



/**
 * An implementation of {@link LocalCacheRepository}.
 */

@Reusable
final class LocalCacheRepository implements LocalRepository {


    private static final String INITJSONFILE = "initdata.json";
    private static final String CONFIGURATION = "configuration";
    private static final String GENRES_MOVIE = "genres_movie";
    private static final String GENRES_TV = "genres_tv";


    @Inject
    Application application;


    StreamFileFromLocal streamJsonFromLocal;
    private final DataConfig dataConfig;
    private Map<String,DomainObjectStorable> map;
    private JsonManager jsonManager;

    @Inject
    LocalCacheRepository(StreamFileFromLocal streamJsonFromLocal,
                         DataConfig dataConfig,
                         JsonManager jsonManager) {

        this.streamJsonFromLocal = streamJsonFromLocal;
        this.dataConfig = dataConfig;
        this.jsonManager = jsonManager;
        this.map = new HashMap<>();



    }


    public Flowable<String> initString() {
        return streamJsonFromLocal
                .streamJsonFile(INITJSONFILE, dataConfig.language())
                .flatMap(jsonManager::addJson_string);
    }



    @Override
    public Flowable<DomainObjectStorable> streamJsonCongiguration() {
              return jsonManager.json_string()
                        .switchIfEmpty(initString())
                        .flatMap(s -> jsonManager.mapJson(CONFIGURATION, null));
    }


    @Override
    public Flowable<DomainObjectStorable> streamJsonGenres(Constants.MEDIA_TYPE type,String language) {


        String typ="";
        if(type.name() == Constants.MEDIA_TYPE.MOVIE.name()) {
           typ = GENRES_MOVIE;
        }
        if(type.name() == Constants.MEDIA_TYPE.TV.name()) {
            typ = GENRES_TV;
        }
        String finalTyp = typ;
        return   jsonManager.json_string()
                .switchIfEmpty(initString())
                .flatMap(s -> jsonManager.mapJson(finalTyp,language))

                ;
    }

}