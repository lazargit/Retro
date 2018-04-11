package com.shamildev.retro.domain.helper;

import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetNowPlayingTVShows;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.Person;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.TVShow;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar on 02.04.2018.
 */

public class DataFilterHelper {







    public static List<DomainObject> filterData(List<DomainObject> results){

        if( results.get(0) instanceof Movie){
            Observable.fromIterable(results)
                    .cast(Movie.class)
                    .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
                    .distinct()
                    .filter(movie -> (movie.posterPath() != null))
                    .cast(DomainObject.class)
                    .toList().blockingGet();
        }
        if( results.get(0) instanceof TVShow){
            return Observable.fromIterable(results)
                    .cast(TVShow.class)
                    .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
                    .distinct()
                    .filter(movie -> (movie.posterPath() != null))
                    .cast(DomainObject.class)
                    .toList().blockingGet();
        }
        if( results.get(0) instanceof Person){
            return Observable.fromIterable(results)
                    .cast(Person.class)
                    .sorted((o1, o2) -> Float.compare(o2.popularity(), o1.popularity()))
                    .distinct()
                    .cast(DomainObject.class)
                    .toList().blockingGet();
        }
        return results;
    }



}
