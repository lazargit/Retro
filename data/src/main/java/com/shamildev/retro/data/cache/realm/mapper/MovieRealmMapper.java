package com.shamildev.retro.data.cache.realm.mapper;

import android.util.Log;

import com.shamildev.retro.data.cache.realm.models.MovieRealm;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.util.Constants;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.realm.RealmList;

/**
 * Created by Shamil Lazar on 31.12.2017.
 */

@Reusable
final class  MovieRealmMapper implements RealmMapper<Movie, MovieRealm> {

    @Inject
    RealmListMapper realmListMapper;

    @Inject
    MovieRealmMapper() {

    }


    @Override
    public MovieRealm map(Movie movie) {

        MovieRealm movieRealm = new MovieRealm();
                    movieRealm.setId(movie.id());
                    movieRealm.setMedia_type(Constants.MEDIA_TYPE.MOVIE.toString());
                    movieRealm.setPopularity(movie.popularity());
                    movieRealm.setTitle(movie.title());
                    movieRealm.setOverview(movie.overview());
                    movieRealm.setOriginal_title(movie.originalTitle());
                    movieRealm.setRelease_date(movie.releaseDate());
                    movieRealm.setPoster_path(movie.posterPath());
                    movieRealm.setBackdrop_path(movie.backdropPath());
                    movieRealm.setOriginal_language(movie.originalLanguage());
                    movieRealm.setVote_average(movie.voteAverage());
                    movieRealm.setAdult(movie.adult());
                    movieRealm.setVideo(movie.video());
                    RealmList<Integer> integers = new RealmList<>();
                    integers.addAll(movie.genreIds());
                    movieRealm.setGenre_ids(integers);


        return movieRealm;
    }

    @Override
    public Movie map(MovieRealm entity) {








        return Movie.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .overview(entity.getOverview())
                .originalTitle(entity.getOriginal_title())
                .originalLanguage(entity.getOriginal_language())
                .posterPath(entity.getPoster_path())
                .backdropPath(entity.getBackdrop_path())
                .genreIds(RealmListMapper.mapToList(entity.getGenre_ids()))
                .adult(entity.getAdult())
                .video(entity.getVideo())
                .releaseDate(entity.getRelease_date())
                .popularity(entity.getPopularity())
                .voteAverage(entity.getVote_average())
                .build();



    }


}
