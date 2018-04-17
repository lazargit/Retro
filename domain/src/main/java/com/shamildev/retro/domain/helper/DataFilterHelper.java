package com.shamildev.retro.domain.helper;

import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.Person;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.domain.repository.CacheRepository;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Shamil Lazar on 02.04.2018.
 */

public class DataFilterHelper {







    public static List<DomainObject> prepareData2(List<DomainObject> results){

//        if( results.get(0) instanceof Movie){
//            Observable.fromIterable(results)
//                    .cast(Movie.class)
//                    .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
//                    .distinct()
//
//                    .filter(movie -> (movie.posterPath() != null))
//
//
//                    .cast(DomainObject.class)
//                    .toList().blockingGet();
//        }
//        if( results.get(0) instanceof TVShow){
//
//            List<DomainObject> list = Observable.fromIterable(appConfig.getWatchList())
//                    .filter(tv -> (tv instanceof TVShow))
//                    .toList().blockingGet();
//
//            return Observable.fromIterable(results)
//                    .cast(TVShow.class)
//                    .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
//                    .distinct()
//                    .filter(movie -> (movie.posterPath() != null))
//                    .flatMap(tvShow -> Observable.fromIterable(list)
//                            .cast(TVShow.class)
//                            .filter(tv -> (tv.id().equals(tvShow.id())))
//                            .firstElement()
//                            .map(tvShow2 -> tvShow.setInWatchList(true))
//                            .defaultIfEmpty(tvShow)
//                            .toObservable())
//                    .map(tvShow -> {
//                        System.out.println("##### "+tvShow.name()+" watch: "+tvShow.isInWatchList());
//                        return tvShow;
//                    })
//                    .cast(DomainObject.class)
//                    .toList().blockingGet();
//        }
//
//
//
//        if( results.get(0) instanceof Person){
//            return Observable.fromIterable(results)
//                    .cast(Person.class)
//                    .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
//                    .distinct()
//                    .cast(DomainObject.class)
//                    .toList().blockingGet();
//        }
       return results;
    }


    public static Flowable<ResultWrapper> prepareData(ResultWrapper resultWrapper, CacheRepository cache) {
        if(resultWrapper.results().size()>0) {
            if (resultWrapper.results().get(0) instanceof TVShow) {

                return Flowable.just(resultWrapper)
                        .flatMap(resultWrapper12 -> Observable.just(resultWrapper12)
                                .map(resultWrapper1 -> {

                                    List<DomainObject> list = Observable.fromIterable(resultWrapper1.results())
                                            .cast(TVShow.class)
                                            .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
                                            .distinct()
                                            .filter(movie -> (movie.posterPath() != null))
                                            .flatMap(tvShow -> Observable.fromIterable(cache.fetchWatchList().blockingSingle())
                                                    .cast(TVShow.class)
                                                    .filter(tv -> (tv.id().equals(tvShow.id())))
                                                    .firstElement()
                                                    .map(tvShow2 -> tvShow.setInWatchList(true))
                                                    .defaultIfEmpty(tvShow)
                                                    .toObservable())
                                            .map(tvShow -> {
                                                System.out.println("##### " + tvShow.name() + " watch: " + tvShow.isInWatchList());
                                                return tvShow;
                                            })
                                            .cast(DomainObject.class)
                                            .toList().blockingGet();


                                    resultWrapper12.results().clear();
                                    resultWrapper12.results().addAll(list);
                                    return resultWrapper1;

                                })


                                .toFlowable(BackpressureStrategy.BUFFER));
            }



        }


//                return
//
//
//                        Observable.fromIterable(resultWrapper.results())
//                        .cast(TVShow.class)
//                        .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
//                        .distinct()
//                        .filter(movie -> (movie.posterPath() != null))
//                        .flatMap(tvShow -> Observable.fromIterable(list)
//                                .cast(TVShow.class)
//                                .filter(tv -> (tv.id().equals(tvShow.id())))
//                                .firstElement()
//                                .map(tvShow2 -> tvShow.setInWatchList(true))
//                                .defaultIfEmpty(tvShow)
//                                .toObservable())
//                        .map(tvShow -> {
//                            System.out.println("##### " + tvShow.name() + " watch: " + tvShow.isInWatchList());
//                            return tvShow;
//                        })
//                        .cast(DomainObject.class)
//                        .toList().blockingGet();


        return Flowable.just(resultWrapper);


    }
}
