package com.shamildev.retro.domain.interactor;

import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;
import com.shamildev.retro.domain.util.DateUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Use case for getting a businesses with a given id.
 */
public final class GetGenre implements UseCaseFlowable<ParamsBasic,List<Genre>> {

    private final RemoteRepository repository;
    private final CacheRepository cache;

    @Inject
    AppConfig appConfig;

    @Inject
    GetGenre(RemoteRepository repository,
             CacheRepository cache) {
        this.repository = repository;
        this.cache = cache;

    }



    public Flowable<List<Genre>> executeTest(ParamsBasic params) {


        return Flowable.empty();
    }

    @Override
    public Flowable<List<Genre>> execute(ParamsBasic params) {

        final int cacheTime = ((GetGenre.Params) params).cacheTime;
        final String language = ((GetGenre.Params) params).language;

        return  fetchAllGenreFromCache(language)

                .switchIfEmpty(fetchAllGenreFromNet())

                .sorted((o1, o2) -> Long.compare(o2.lastUpdate(), o1.lastUpdate()))
                .take(1)
                .map(genreModel ->
                                DateUtil.isCacheTimeExpired(genreModel,cacheTime)

                                .map(aBoolean -> (aBoolean) ? fetchAllGenreFromNet() : fetchAllGenreFromCache(language))
                                .blockingLast()
                                .toList()
                                .blockingGet()
                )
                .map(genres -> {
                    appConfig.setGenres(genres);
                    return genres;
                });

    }



    public Flowable<Genre> saveToCache(Genre genreModel ) {
        return Flowable.defer(() -> {

            try {

                return Flowable.just(genreModel)
                        .flatMap(genreMod -> Flowable.just(genreMod)
                                .flatMapCompletable(cache::saveGenre)
                                .toFlowable()
                                .startWith(genreMod)

                        ).cast(Genre.class);

            } catch (Exception e) {

                return Flowable.error(e);

            }
        });
    }

    public Flowable<Genre> fetchAllGenreFromNet() {
        return Flowable.concat(
                this.repository.fetchGenre(Constants.MEDIA_TYPE.MOVIE),
                this.repository.fetchGenre(Constants.MEDIA_TYPE.TV))
                .flatMap(Flowable::fromIterable)
                .flatMap(this::saveToCache);
    }

    public Flowable<Genre> fetchAllGenreFromCache(String language) {
       return Flowable.fromIterable(
                       Flowable.concat(
                               this.cache.fetchGenre(Constants.MEDIA_TYPE.MOVIE, language),
                               this.cache.fetchGenre(Constants.MEDIA_TYPE.TV, language))
                               .flatMap(Flowable::fromIterable)
                               .subscribeOn(Schedulers.computation())
                               .toList().blockingGet() );
    }


    public static final class Params implements ParamsBasic {



        private int cacheTime = 0;
        private String language;

        public Params(int cacheTime) {
            this.cacheTime = cacheTime;
        }
        public Params(int cacheTime,String language ) {
            this.cacheTime = cacheTime;
            this.language = language;
        }


        public static Params with(int cacheTime) {
            return new Params(cacheTime);
        }
        public static Params with(String language, int cacheTime) {
            return new Params(cacheTime,language);
        }



    }
}
