package com.shamildev.retro.domain.helper;

import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.TVShow;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;

import io.reactivex.ObservableTransformer;

/**
 * Created by Shamil Lazar on 02.04.2018.
 */

public class ProcessData {







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

    private static ObservableSource<? extends DomainObject> castDomainObject(DomainObject domainObject) {
                if(domainObject instanceof TVShow){
                   return Observable.just(domainObject).cast(TVShow.class);
                }else{
                    return Observable.just(domainObject).cast(TVShow.class);
                }



    }





    private static ObservableTransformer<DomainObject, Movie> transformObjectToMovie() {

        return observable -> observable.cast(Movie.class);

    }
    private static ObservableTransformer<DomainObject, TVShow> transformObjectToTVSHOW() {

        return observable -> observable.cast(TVShow.class);

    }


    private static Observable<DomainObject> matchDataResultWithWatchList(Observable<DomainObject> results, List<DomainObject> wList){

        return   results
                .cast(MediaItem.class)
                .sorted((o1, o2) -> Float.compare(o2.itemPopularity(), o1.itemPopularity()))

                .filter(item -> (item.itemPosterPath() != null))
                .flatMap(item -> Observable.fromIterable(wList)

                        .cast(MediaItem.class)
                        .filter(cItem -> (cItem.itemId().equals(item.itemId())))
                        .firstElement()
                        .map(cItem -> item.itemAddToWatchList())
                        .defaultIfEmpty(item)
                        .toObservable()
                )
                .distinct()
                .map(item -> {
                    System.out.println("##### " + item.itemTitle() + " watch: " + item.itemIsInWatchList());
                    return item;
                })
                .cast(DomainObject.class)
                ;

    }



    public static Flowable<ResultWrapper> prepareResultWrapper(ResultWrapper resultWrapper, List<DomainObject> wList) {


        if(resultWrapper.results().size()>0 && wList!=null) {

                return Flowable.just(resultWrapper)
                        .flatMap(wrapper -> Observable.just(wrapper)

                                        .map(data -> {



                                            List<DomainObject> list = matchDataResultWithWatchList(Observable.fromIterable(data.results()) , wList)
                                                    .toList().blockingGet();
                                            data.results().clear();
                                            data.results().addAll(list);



                                            return data;

                                        })
//                                        .map(item -> {
//                                            System.out.println("##### " + item.itemTitle() + " watch: " + item.itemIsInWatchList());
//                                            return item;
//                                        })
                                        .toFlowable(BackpressureStrategy.BUFFER)
                        );

        }

        return Flowable.just(resultWrapper);


    }

    private CompletableSource isInWatchList(DomainObject item) {

        return Completable.create(e -> {


            e.onComplete();


        });


    }




}
