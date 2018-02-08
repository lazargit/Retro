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


import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Inject;

import io.reactivex.Flowable;


/**
 * Use case for getting a businesses with a given id.
 */
public final class GetMovieById implements UseCaseFlowable<ParamsBasic,Movie> {

    private final RemoteRepository repository;

    @Inject
    GetMovieById(RemoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<Movie> execute(ParamsBasic params) {
        int movieId = ((Params) params).movieId;
        String append_to_response = ((Params) params).append_to_response;

        return this.repository.fetchMovie(movieId,append_to_response);

    }




    public static final class Params implements ParamsBasic {

        private Params() {

        }

        private int movieId = 0;
        private String language;
        private String append_to_response;


        private Params(int movieId) {
            this.movieId = movieId;
        }
        private Params(int movieId,String append_to_response ) {
            this.movieId = movieId;
            this.append_to_response = append_to_response;
        }
        private Params(int movieId,String language,String append_to_response ) {
            this.movieId = movieId;
            this.language = language;
            this.append_to_response = append_to_response;
        }


        public static Params with(int movieId) {
            return new Params(movieId);
        }
        public static Params with(int movieId, String append_to_response) {
            return new Params(movieId,append_to_response);
        }
        public static Params with(int movieId, String language, String append_to_response) {
            return new Params(movieId,language,append_to_response);
        }


    }
}

