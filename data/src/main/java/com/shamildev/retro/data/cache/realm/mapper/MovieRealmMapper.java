package com.shamildev.retro.data.cache.realm.mapper;

import com.shamildev.retro.data.cache.realm.models.WatchListRealm;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.util.Constants;


import javax.inject.Inject;

import dagger.Reusable;
import io.realm.RealmList;

/**
 * Created by Shamil Lazar on 31.12.2017.
 */

@Reusable
final class  MovieRealmMapper implements RealmMapper<Movie, WatchListRealm> {

    @Inject
    RealmListMapper realmListMapper;

    @Inject
    MovieRealmMapper() {

    }


    @Override
    public WatchListRealm map(Movie movie) {

        WatchListRealm movieRealm = new WatchListRealm();
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
    public Movie map(WatchListRealm entity) {








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
