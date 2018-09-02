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
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.BaseRepository;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Use case for getting a businesses with a given id.
 */
public final class SignInUser implements UseCaseFlowable<ParamsBasic, AppUser> {

    private final RemoteRepository repository;
    private final BaseRepository baseRepository;
    private final CacheRepository cache;
    private DataConfig dataConfig;

    @Inject
    AppConfig appConfig;

    @Inject
    AppUser appUser;


    @Inject
    SignInUser(RemoteRepository repository,
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
        String token = ((Params) params).token;
        if(token!=null){
            return baseRepository.signIn(token);
        }


        return baseRepository.signIn();

    }



    public static final class Params implements ParamsBasic {


        private  String token;

        public Params() {

        }
        public Params(String token) {
            this.token = token;
        }
        public static SignInUser.Params justVoid() {
            return new SignInUser.Params();
        }
        public static SignInUser.Params withFacebook(String token) {
            return new SignInUser.Params(token);
        }

    }
}
