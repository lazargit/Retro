package com.shamildev.retro.data.cache.realm.mapper;


import com.shamildev.retro.data.cache.realm.models.GenreRealm;
import com.shamildev.retro.data.cache.realm.models.WatchListRealm;
import com.shamildev.retro.data.cache.realm.models.TMDbConfigurationRealm;
import com.shamildev.retro.data.entity.mapper.EntityMapper;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.TVShow;

import javax.inject.Inject;

import dagger.Reusable;


@Reusable
public final class RealmMapperHolder {

    private final RealmMapper<Movie, WatchListRealm> movieRealmMapper;
    private final RealmMapper<TVShow, WatchListRealm> tvshowRealmMapper;
    private final RealmMapper<Configuration, TMDbConfigurationRealm> configurationRealmMapper;
    private final RealmMapper<Genre, GenreRealm> genreRealmMapper;




    @Inject
    RealmMapperHolder(RealmMapper<Movie, WatchListRealm> movieRealmMapper,
                      RealmMapper<TVShow, WatchListRealm> tvshowRealmMapper,
                      RealmMapper<Configuration, TMDbConfigurationRealm> configurationRealmMapper,
                      RealmMapper<Genre, GenreRealm> genreRealmMapper) {

        this.movieRealmMapper = movieRealmMapper;
        this.tvshowRealmMapper = tvshowRealmMapper;
        this.configurationRealmMapper = configurationRealmMapper;
        this.genreRealmMapper = genreRealmMapper;


    }

    public RealmMapper<Movie, WatchListRealm> movieRealmMapper() {
        return movieRealmMapper;
    }
    public RealmMapper<TVShow, WatchListRealm> tvshowRealmMapper() {
        return tvshowRealmMapper;
    }

    public RealmMapper<Configuration, TMDbConfigurationRealm> configurationRealmMapper() {
        return configurationRealmMapper;
    }
    public RealmMapper<Genre, GenreRealm> genreRealmMapper() {
        return genreRealmMapper;
    }




}
