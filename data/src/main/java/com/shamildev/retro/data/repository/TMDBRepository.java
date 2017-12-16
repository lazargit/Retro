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

package com.shamildev.retro.data.repository;

import com.shamildev.retro.data.net.TMDBServices;
import com.shamildev.retro.data.net.UrlManager;
import com.shamildev.retro.data.responsemodels.Response;
import com.shamildev.retro.domain.repository.RemoteRepository;
import javax.inject.Inject;
import dagger.Reusable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * An implementation of {@link RemoteRepository}.
 */
@Reusable
final class TMDBRepository implements RemoteRepository{


    private final TMDBServices tmdbServices;

    @Inject
    TMDBRepository(TMDBServices tmdbServices) {
        this.tmdbServices = tmdbServices;
    }


    @Override
    public Observable<com.shamildev.retro.domain.responsemodels.Response> getTestService() {
        return Observable.create(new ObservableOnSubscribe<com.shamildev.retro.domain.responsemodels.Response>() {
            @Override
            public void subscribe(ObservableEmitter<com.shamildev.retro.domain.responsemodels.Response> e) throws Exception {
                com.shamildev.retro.domain.responsemodels.Response response = tmdbServices.fetchUpcomingMovies(UrlManager.PUBLIC_KEY_MDB, "1", "de-DE", "").blockingGet();
                e.onNext(response);
            }
        });
    }



}
