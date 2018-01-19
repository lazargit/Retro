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

package com.shamildev.retro.data.cache;

import android.util.Log;

import com.shamildev.retro.data.cache.realm.mapper.RealmMapperHolder;
import com.shamildev.retro.data.cache.realm.models.GenreRealm;
import com.shamildev.retro.data.cache.realm.models.MovieRealm;
import com.shamildev.retro.data.cache.realm.models.TMDbConfigurationRealm;
import com.shamildev.retro.data.entity.mapper.EntityMapperHolder;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.data.cache.realm.models.ConfigurationRealm;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;
import com.shamildev.retro.domain.util.DateUtil;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Reusable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;


/**
 * An implementation of {@link RemoteRepository}.
 */
@Reusable
final class RealmCacheRepository implements CacheRepository {





    private final Provider<Realm> realmProvider;
    private final RealmMapperHolder realmMapperHolder;







    @Inject
    RealmCacheRepository(Provider<Realm> realmProvider,RealmMapperHolder realmMapperHolder) {
        this.realmProvider = realmProvider;
        this.realmMapperHolder = realmMapperHolder;
    }



    @Override
    public void saveObj(DomainObject object) {

        Log.d("saveObj",">>>> "+object.toString());
        Realm realm = this.realmProvider.get();



            realm.beginTransaction();
            ConfigurationRealm realmObj = new ConfigurationRealm();
            realmObj.setUser("Schamil");
            realm.copyToRealmOrUpdate(realmObj);
            realm.commitTransaction();
            realm.close();






    }


    @Override
    public Completable save(Movie movie) {
        Log.d("TAG", ">>>" + "save Movie"+movie);


        return Completable.create(e -> {

            try (Realm realm = realmProvider.get()) {
                realm.executeTransaction(realm1 -> {
                    Log.d("TAG", ">>>" + realm1.toString());
                    MovieRealm movieRealm = realmMapperHolder.movieRealmMapper().map(movie);
                    realm1.copyToRealmOrUpdate(movieRealm);
                    e.onComplete();
                });
            } //autoclose

        });
    }




    @Override
    public Completable saveGenre(final Genre genreModel) {
        final GenreRealm realmObj = realmMapperHolder.genreRealmMapper().map(genreModel);
                         realmObj.setLast_update(DateUtil.NOW());

        return Completable.create(e -> {

            try (Realm realm = realmProvider.get()) {
                realm.executeTransaction(realm1 -> {

                    realm1.insertOrUpdate(realmObj);
                    e.onComplete();
                });
            }

        });

//        return Completable.create(e -> {
//            Realm realm = realmProvider.get();
//            realm.beginTransaction();
//
//            realm.copyToRealmOrUpdate(realmObj);
//
//            realm.commitTransaction();
//            e.onComplete();
//            realm.close();
//
//        });

    }

    @Override
    public Flowable<List<Genre>> fetchGenre(final Constants.MEDIA_TYPE mediaType, final String language) {

        return Flowable.create(new FlowableOnSubscribe<List<Genre>>() {
            @Override
            public void subscribe(FlowableEmitter<List<Genre>> e) throws Exception {


                try (Realm realm = realmProvider.get()) {
                    realm.executeTransaction(realm1 -> {

                        RealmResults<GenreRealm> result = realm1.where(GenreRealm.class)
                                .equalTo(GenreRealm.FIELD_LANGUAGE,language)
                                .equalTo(GenreRealm.FIELD_MEDIATYPE,mediaType.toString())
                                .findAll();

                        if (result.size() == 0) {
                            e.onComplete();
                        }else {

                            List<Genre> genreResult = Observable
                                    .fromIterable(result)
                                    .map(realmMapperHolder.genreRealmMapper()::map)
                                    .toList()
                                    .blockingGet();

                            e.onNext(genreResult);
                            e.onComplete();
                        }

                    });
                } //autoclose






            }
        }, BackpressureStrategy.LATEST);
    }





    @Override
    public Flowable<Configuration> fetchConfiguration() {

        Log.d("TAG", "fetchConfigurationRealm");
        return Flowable.create(e -> {

            try (Realm realm = realmProvider.get()) {
                realm.executeTransaction(realm1 -> {

                    RealmResults<TMDbConfigurationRealm> result = realm1.where(TMDbConfigurationRealm.class)
                            .findAll();
                    Log.d("result.size","# "+ result.size());
                    if (result.size() == 0) {
                        e.onComplete();
                    }else {
                        TMDbConfigurationRealm tmDbConfigurationRealm = result.get(0);
                        Log.d("result.size","## "+ tmDbConfigurationRealm);
                        Configuration map = realmMapperHolder.configurationRealmMapper().map(tmDbConfigurationRealm);
                        Log.d("result.size","## "+ map);
                        e.onNext(map);
                        e.onComplete();
                    }

                });
            }
        }, BackpressureStrategy.LATEST);
    }

    @Override
    public Completable saveTMDbConfiguration(Configuration configuration) {
        Log.d("TAG", "saveTMDbConfiguration");
        return Completable.create(e -> {

            Realm realm = realmProvider.get();
            realm.beginTransaction();
            TMDbConfigurationRealm realmObj = realmMapperHolder.configurationRealmMapper().map(configuration);
            realmObj.setLast_update(DateUtil.NOW());
            realm.copyToRealmOrUpdate(realmObj);
            realm.commitTransaction();
            e.onComplete();
            realm.close();


//            try (Realm realm = realmProvider.get()) {
//                realm.executeTransaction(realm1 -> {
//                    Log.d("TAG", "saveTMDbConfiguration");
//                    TMDbConfigurationRealm realmObj = realmMapperHolder.configurationRealmMapper().map(configuration);
//                                           realmObj.setLast_update(DateUtil.NOW());
//                    realm1.insertOrUpdate(realmObj);
//                    realm1.commitTransaction();
//                    e.onComplete();
//                });
//
//            } //autoclose

        });
    }


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
//            MovieRealm movieRealm = transactionRealm..createObject(MovieRealm.class);
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
