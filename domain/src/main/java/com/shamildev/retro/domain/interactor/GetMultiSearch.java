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


import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;

import javax.inject.Inject;

import io.reactivex.Flowable;


/**
 * Use case for getting a businesses with a given id.
 */
public final class GetMultiSearch implements UseCaseFlowable<ParamsBasic,ResultWrapper> {

    private final RemoteRepository repository;

    @Inject
    GetMultiSearch(RemoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<ResultWrapper> execute(ParamsBasic params) {
        String quary = ((Params) params).quary;






        return this.repository.fetchMultiSearch(quary);

    }




    public static final class Params implements ParamsBasic {

        private Params() {

        }

        private String quary = "";



        private Params(String quary) {
            this.quary = quary;
        }



        public static Params with(String quary) {
            return new Params(quary);
        }



    }
}

