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
import com.shamildev.retro.data.cache.realm.models.WatchListRealm;
import com.shamildev.retro.data.cache.realm.models.TMDbConfigurationRealm;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.data.cache.realm.models.ConfigurationRealm;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;
import com.shamildev.retro.domain.util.DateUtil;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Reusable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmQuery;
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
    public Completable saveItemWatchList(DomainObject item) {
        Log.e("saveItemWatchList ", ">>>" + "save item in watchlist"+item);
        WatchListRealm realmObj = null;
        if(item instanceof Movie){

            Movie movie = (Movie) item;
            realmObj = realmMapperHolder.movieRealmMapper().map(movie);
            realmObj.setLast_update(DateUtil.NOW());
        }
        if(item instanceof TVShow){

            TVShow show = (TVShow) item;
            realmObj = realmMapperHolder.tvshowRealmMapper().map(show);
            realmObj.setLast_update(DateUtil.NOW());
        }


        final WatchListRealm finalRealmObj1 = realmObj;
        return Completable.create(e -> {

            try (Realm realm = realmProvider.get()) {

                realm.executeTransaction(realm1 -> {
                    Log.d("TAG", ">>>" + realm1.toString());

                    realm1.copyToRealmOrUpdate(finalRealmObj1);
                    e.onComplete();
                });
            } //autoclose

        });
    }

    @Override
    public Flowable<List<DomainObject>> fetchWatchList() {





        return Flowable.create(e -> {


            try (Realm realm = realmProvider.get()) {
                    realm.executeTransaction(realm1 -> {

                    RealmResults<WatchListRealm> result = realm1.where(WatchListRealm.class)
                            .findAll();

                    if (result.size() == 0) {
                        List<DomainObject> list1 = Observable.fromIterable(result)
                                .cast(DomainObject.class)
                                .toList()
                                .blockingGet();

                        e.onNext(list1);
                        e.onComplete();
                    }else {

                        List<DomainObject> list = Observable.fromIterable(result)
                                .map(movieRealm -> {

                                    if (movieRealm.getMedia_type().equals(Constants.MEDIA_TYPE.TV.toString())) {
                                        return realmMapperHolder.tvshowRealmMapper().map(movieRealm);
                                    }
                                        return realmMapperHolder.movieRealmMapper().map(movieRealm);


                                })
                                .cast(DomainObject.class)
                                .toList()
                                .blockingGet();

//                        List<Movie> movieList = Observable
//                                .fromIterable(result)
//                                .map(realmMapperHolder.movieRealmMapper()::map)
//                                .cast(Movie.class)
//                                .toList()
//                                .blockingGet();

                        e.onNext(list);
                        e.onComplete();
                    }

                });
            } //autoclose






        }, BackpressureStrategy.LATEST);
    }




    @Override
    public Completable saveGenre(final Genre genreModel) {

       Log.e(Thread.currentThread().getName()+" SAVE GENRE TO CACHE",">"+genreModel.name()+"><"+genreModel.type());

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
    }



    @Override
    public Flowable<List<Genre>> fetchGenre(final Constants.MEDIA_TYPE mediaType, final String language) {

        return Flowable.create(e -> {

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






        }, BackpressureStrategy.LATEST);
    }





    @Override
    public Flowable<Configuration> fetchConfiguration() {
        Log.d("TAG", "fetchConfigurationRealm");

        return Flowable.create(new FlowableOnSubscribe<Configuration>() {
            @Override
            public void subscribe(FlowableEmitter<Configuration> e) throws Exception {
                Realm realm = realmProvider.get();

                RealmQuery<TMDbConfigurationRealm> query = realm.where(TMDbConfigurationRealm.class);
                RealmResults<TMDbConfigurationRealm> result = query.findAll();

                if (result.size() == 0) {
                    e.onComplete();

                }else {
                    TMDbConfigurationRealm realmObj  = result.get(0);
                    Configuration map = realmMapperHolder.configurationRealmMapper().map(realmObj);
                    e.onNext(map);
                    e.onComplete();

                }
                realm.close();


            }
        }, BackpressureStrategy.LATEST);



//        return Flowable.create(e -> {
//            Log.d("TAG", "fetchConfigurationRealm>>>");
//            try (Realm realm = realmProvider.get()) {
//                realm.executeTransaction(realm1 -> {
//
//                    RealmResults<TMDbConfigurationRealm> result = realm1.where(TMDbConfigurationRealm.class)
//                            .findAll();
//                    Log.d("result.size","# "+ result.size());
//                    if (result.size() > 0) {
//
//
//
//                        TMDbConfigurationRealm tmDbConfigurationRealm = result.get(0);
//
//                        Configuration map = realmMapperHolder.configurationRealmMapper().map(tmDbConfigurationRealm);
//
//                        e.onNext(map);
//
//
//                    }
//
//                });
//
//            }
//            e.onComplete();
//        }, BackpressureStrategy.MISSING);
    }

    @Override
    public Completable saveTMDbConfiguration(Configuration configuration) {



        TMDbConfigurationRealm realmObj = realmMapperHolder.configurationRealmMapper().map(configuration);
        realmObj.setLast_update(DateUtil.NOW());


        return Completable.create(e -> {
            Realm realm = realmProvider.get();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(realmObj);
            realm.commitTransaction();
            e.onComplete();
            realm.close();

        });
    }

    @Override
    public Completable insertTMDbConfiguration(Configuration configuration,Long date) {
        TMDbConfigurationRealm realmObj = realmMapperHolder.configurationRealmMapper().map(configuration);
        realmObj.setLast_update(date);


        return Completable.create(e -> {
            Realm realm = realmProvider.get();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(realmObj);
            realm.commitTransaction();
            e.onComplete();
            realm.close();

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
