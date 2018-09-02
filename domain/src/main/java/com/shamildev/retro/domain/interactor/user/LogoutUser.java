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

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Use case for getting a businesses with a given id.
 */
public final class LogoutUser implements UseCaseFlowable<ParamsBasic, AppUser> {

    private final RemoteRepository repository;
    private final BaseRepository baseRepository;
    private final CacheRepository cache;
    private DataConfig dataConfig;

    @Inject
    AppConfig appConfig;

    @Inject
    AppUser appUser;


    @Inject
    LogoutUser(RemoteRepository repository,
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


        return baseRepository.signOut().andThen(Flowable.just(appUser));





    }



    public static final class Params implements ParamsBasic {



        public Params() {

        }
        public static LogoutUser.Params justVoid() {
            return new LogoutUser.Params();
        }
    }
}
