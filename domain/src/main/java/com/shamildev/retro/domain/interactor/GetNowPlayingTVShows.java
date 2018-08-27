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
