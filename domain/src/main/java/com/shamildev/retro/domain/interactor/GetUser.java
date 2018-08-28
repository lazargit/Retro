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
import com.shamildev.retro.domain.models.GuestSession;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.FirebaseRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Use case for getting a businesses with a given id.
 */
public final class GetUser implements UseCaseFlowable<ParamsBasic, String> {

    private final RemoteRepository repository;
    private final FirebaseRepository firebaseRepository;
    private final CacheRepository cache;
    private DataConfig dataConfig;

    @Inject
    AppConfig appConfig;


    @Inject
    GetUser(RemoteRepository repository,
            CacheRepository cache,
            FirebaseRepository firebaseRepository,
            DataConfig dataConfig) {
        this.repository = repository;
        this.firebaseRepository = firebaseRepository;
        this.cache = cache;
        this.dataConfig = dataConfig;
    }



    @Override
    public Flowable<String> execute(ParamsBasic params) {
        int cacheTime = ((Params) params).cacheTime;

         //  firebaseRepository.checkUser().blockingSingle();


        return firebaseRepository.checkUser();




               // .flatMap(this::saveToCache)



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


    public Flowable<User> saveToCache(User model ) {
        return   Flowable.defer(() -> {
            try {
                return Flowable.just(model)
                                .flatMap(userModel -> Flowable.just(userModel)
                                                                .flatMapCompletable(cache::saveUser)
                                                                .toFlowable()
                                                                .startWith(userModel)
                                ).cast(User.class);
            } catch (Exception e) {
                return Flowable.error(e);

            }
        });


    }

    private Flowable<User> initUser() {
       return Flowable.just(dataConfig.language())
                .map(User::create)
                .flatMap(u -> Flowable.just(u)
                                        .map(s -> {
                                            GuestSession guestSession = fetchGuestSession()
                                                    .blockingSingle();
                                            return  u.setSession(guestSession.getGuestSessionId(), guestSession.getExpiresAt());
                                        })

                );
    }

    private Flowable<GuestSession> fetchGuestSession() {

        return  repository.fetchGuestSession();

    }

    private Flowable<User> fetchUserFromCache() {
        return  cache.fetchUser()
                .subscribeOn(Schedulers.computation())
                ;

    }




    public static final class Params implements ParamsBasic {

        private Params() { }

        private int cacheTime = 0;

        public Params(int cacheTime) {
            this.cacheTime = cacheTime;
        }



        public static GetUser.Params withCacheTime(int cacheTime) {
            return new GetUser.Params(cacheTime);
        }



}
}
