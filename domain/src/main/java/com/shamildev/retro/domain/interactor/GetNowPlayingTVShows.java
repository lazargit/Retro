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
import com.shamildev.retro.domain.helper.ProcessData;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;


public final class GetNowPlayingTVShows implements UseCaseFlowable<ParamsBasic,ResultWrapper> {

    private final RemoteRepository repository;
    private final CacheRepository cache;

    @Inject
    GetNowPlayingTVShows(RemoteRepository repository, CacheRepository cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Flowable<ResultWrapper> execute(ParamsBasic params) {
        int page = ((Params) params).page;

         cache.fetchWatchList()
                .subscribe(new DisposableSubscriber<List<DomainObject>>() {
                    @Override
                    public void onNext(List<DomainObject> domainObjectList) {
                        System.out.println("LIST"+domainObjectList);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

        return repository.fetchNowPlayingTVShow(page)
               .flatMap(resultWrapper -> ProcessData.prepareResultWrapper(resultWrapper,cache.fetchWatchList().blockingLast()))




//                                .flatMapCompletable(movieWrapper -> Flowable.fromIterable(movieWrapper.results())
//                                        .flatMapCompletable(cache::saveItemWatchList)
//                                )
//                .toFlowable()

                ;

    }


    public static final class Params implements ParamsBasic {

        private int page = 1;

        public Params(int page) {
            this.page = page;
        }
        public static Params withPage(int page) {
            return new GetNowPlayingTVShows.Params(page);
        }

    }

}
