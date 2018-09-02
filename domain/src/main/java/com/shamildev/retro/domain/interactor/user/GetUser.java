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

package com.shamildev.retro.domain.interactor.user;

import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.interactor.UseCaseFlowable;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.GuestSession;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.BaseRepository;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import org.reactivestreams.Publisher;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Use case for getting a businesses with a given id.
 */
public final class GetUser implements UseCaseFlowable<ParamsBasic, AppUser> {

    private final RemoteRepository repository;
    private final BaseRepository baseRepository;
    private final CacheRepository cache;
    private DataConfig dataConfig;

    @Inject
    AppConfig appConfig;



    @Inject
    AppUser appUser;


    @Inject
    GetUser(RemoteRepository repository,
            CacheRepository cache,
            BaseRepository baseRepository,
            DataConfig dataConfig) {
        this.repository = repository;
        this.baseRepository = baseRepository;
        this.cache = cache;
        this.dataConfig = dataConfig;
    }



    @Override
    public Flowable<AppUser> execute(ParamsBasic params) {
        int cacheTime = ((Params) params).cacheTime;




        return  Flowable.just(appUser)
                .flatMap(appUser-> cache.fetchUser()
                                    .flatMap(user->{
                                        appUser.setUser(user);

                                        return Flowable.just(appUser);
                                    }))
                .flatMap( appUser -> baseRepository.checkUser()
                                        .map(appUser1 -> {
                                            if(appUser1.getUid()!=null) {
                                                appUser1.setLoggedIn(true);
                                            }
                                            return appUser1;
                                        }))

               // .switchIfEmpty(baseRepository.signIn())
                ;





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
//       return Flowable.just(dataConfig.language())
//                .map(User::create)
//                .flatMap(u -> Flowable.just(u)
//                                        .map(s -> {
//                                            GuestSession guestSession = fetchGuestSession()
//                                                    .blockingSingle();
//                                            return  u.setSession(guestSession.getGuestSessionId(), guestSession.getExpiresAt());
//                                        })
//
//                );
       return Flowable.empty();
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
