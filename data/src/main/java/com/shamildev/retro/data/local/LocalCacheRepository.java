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
import android.util.Log;

import com.shamildev.retro.data.cache.realm.mapper.RealmMapperHolder;
import com.shamildev.retro.data.local.json.JsonManager;
import com.shamildev.retro.data.local.load.StreamFileFromLocal;
import com.shamildev.retro.domain.DomainObjectStorable;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.repository.LocalRepository;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import dagger.Reusable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import io.realm.Realm;



/**
 * An implementation of {@link LocalCacheRepository}.
 */

@Reusable
final class LocalCacheRepository implements LocalRepository {


    private final Provider<Realm> realmProvider;
    private final RealmMapperHolder realmMapperHolder;
    private ClassLoader classLoader;
    private static final String INITJSONFILE = "initdata.json";
    private static final String CONFIGURATION = "configuration";
    private static final String GENRES_MOVIE = "genres_movie";
    private static final String GENRES_TV = "tv";


    @Inject
    Application application;


    StreamFileFromLocal streamJsonFromLocal;
    private final DataConfig dataConfig;
    private Map<String,DomainObjectStorable> map;
    private JsonManager jsonManager;

    @Inject
    LocalCacheRepository(Provider<Realm> realmProvider,
                         RealmMapperHolder realmMapperHolder,
                         StreamFileFromLocal streamJsonFromLocal,
                         DataConfig dataConfig,
                         JsonManager jsonManager) {

        this.realmProvider = realmProvider;
        this.realmMapperHolder = realmMapperHolder;
        this.streamJsonFromLocal = streamJsonFromLocal;
        this.dataConfig = dataConfig;
        this.jsonManager = jsonManager;
        this.map = new HashMap<>();



    }


    public Flowable<String> initString() {
        Log.e("initString", "initString");
        return streamJsonFromLocal
                .streamJsonFile(INITJSONFILE, dataConfig.language())
                .flatMap(jsonManager::addJson_string);
    }

    @Override
    public Flowable<DomainObjectStorable> streamJsonCongiguration() {

              return    jsonManager.json_string()
                        .switchIfEmpty(initString())
                        .flatMap(s -> jsonManager.mapJson(CONFIGURATION));




//                        .subscribe(n-> Log.e("A",""+n),
//                                  e-> Log.e("error",""+e)
//
//
//                        );

//        return streamJsonFromLocal
//
//                .streamJsonFile(INITJSONFILE, dataConfig.language())
//                .map(jsonManager::addJson_string)
//                .flatMap(s -> jsonManager.mapJson(CONFIGURATION));

//        return jsonManager.json_string()
//
//
//
//                .switchIfEmpty(s ->
//                                 streamJsonFromLocal.streamJsonFile(INITJSONFILE, dataConfig.language())
//                                 .map(jsonManager::addJson_string)
//                )
//
//
//                .flatMap(s -> jsonManager.mapJson(CONFIGURATION));
//
//        return streamJsonFromLocal
//                .streamJsonFile(INITJSONFILE, dataConfig.language())
//                .map(jsonManager::addJson_string)
//                .flatMap(s -> jsonManager.mapJson(CONFIGURATION));

//        return Flowable.just(jsonManager.getJson_string())
//                .switchIfEmpty(s -> streamJsonFromLocal
//                        .streamJsonFile(INITJSONFILE, dataConfig.language())
//
//                )
//                .flatMap(s -> jsonManager.mapJson(CONFIGURATION));
    }

    @Override
    public Flowable<DomainObjectStorable> streamJsonGenres() {

        return    jsonManager.json_string()
                .switchIfEmpty(initString())
                .flatMap(s -> jsonManager.mapJson(GENRES_MOVIE));
//
//        streamJsonFromLocal
//                .streamJsonFile(INITJSONFILE, dataConfig.language())
//                .map(jsonManager::addJson_string)
//                .flatMap(s -> jsonManager.mapJson(GENRES_MOVIE))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSubscriber<Object>() {
//
//                    @Override
//                    public void onNext(Object o) {
//                        if(o instanceof Configuration){
//                            Log.e("TConfiguration",  ((Configuration) o).baseUrl());
//                        }
//                        if(o instanceof Genre){
//                            Log.e("TGenre",  ((Genre) o).name());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        Log.e("onError",  t.getMessage());
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e("onComplete","configuration");
//                    }
//                });
//
//
//        return Flowable.empty();







//
//        streamJsonFromLocal
//                .streamJsonFile(INITJSONFILE, this.dataConfig.language())
//
//                .doOnNext(jsonManager::setJson_string)
//                .flatMap(s -> jsonManager.mapJson(GENRES_MOVIE))
//                .cast(Genre.class)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<Genre>() {
//                    @Override
//                    public void accept(Genre genre) throws Exception {
//                        Log.e("---",genre.name());
//                    }
//                })
//
//
//                .flatMap(o ->Flowable.just(jsonManager.mapJson(CONFIGURATION)))
//
//
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableSubscriber<Object>() {
//
//                    @Override
//                    public void onNext(Object o) {
//                        if(o instanceof Configuration){
//
//                            Log.e("TConfiguration",  ((Configuration) o).baseUrl());
//                        }
//                        if(o instanceof Genre){
//
//                            Log.e("TGenre",  ((Genre) o).name());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        Log.e("onError",  t.getMessage());
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e("onComplete","configuration");
//                    }
//                });
//







    }



//        return Completable.create(e -> {
//            Log.d("TAG", "saveTMDbConfiguration RX");
//            try (Realm realm = realmProvider.get()) {
//                if(!realm.isInTransaction()) {
//                    realm.executeTransaction(realm1 -> {
//
//                        realm1.insertOrUpdate(realmObj);
//
//
//                    });
//                }
//
//            }
//            e.onComplete();
//        });


    //  }


//    public <T extends DomainObjectStorable> Observable<T> save(T object, Class<T> clazz) {
//        Realm realm = this.realmProvider.get();
//
//        long id;
//
//        try {
//            id = realm.where(clazz).max("id").intValue() + 1;
//        } catch (Exception e) {
//            id = 0L;
//        }
//
//        ((TaskRealModel) object).setId(id);
//
//        return Observable.just(object)
//                .flatMap(t -> Observable.just(t)
//                        .doOnSubscribe(realm::beginTransaction)
//                        .doOnUnsubscribe(() -> {
//                            realm.commitTransaction();
//                            realm.close();
//                        })
//                        .doOnNext(realm::copyToRealm)
//                );
//    }


//    private String writeToRealm(Movie movie) {
//        Realm realm = this.realmProvider.get();
//        realm.executeTransaction(transactionRealm -> {
//
//            WatchListRealm movieRealm = transactionRealm..createObject(WatchListRealm.class);
//            movieRealm.
//            weatherRealm.setTemp(weatherResponse.getMain().getTemp());
//        });
//        realm.close();
//        return weatherResponse.getName();
//    }
//
//
//    private WeatherRealm findInRealm(Realm realm, String name) {
//        return realm.where(WeatherRealm.class).equalTo("name", name).findFirst();
//    }


}