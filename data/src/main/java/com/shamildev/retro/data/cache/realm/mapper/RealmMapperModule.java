package com.shamildev.retro.data.cache.realm.mapper;


import com.shamildev.retro.data.cache.realm.models.GenreRealm;
import com.shamildev.retro.data.cache.realm.models.WatchListRealm;

import com.shamildev.retro.data.cache.realm.models.TMDbConfigurationRealm;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.TVShow;


import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

/**
 * Provides entity mapper dependencies.
 */
@Module
public abstract class RealmMapperModule {

    @Binds
    @Reusable
    abstract RealmMapper<Movie, WatchListRealm>  movieRealmMapper(MovieRealmMapper movieRealmMapper);

    @Binds
    @Reusable
    abstract RealmMapper<TVShow, WatchListRealm>  tvshowRealmMapper(TvShowRealmMapper tvShowRealmMapper);

    @Binds
    @Reusable
    abstract RealmMapper<Configuration, TMDbConfigurationRealm>  configRealmMapper(ConfigurationRealmMapper configurationRealmMapper);

    @Binds
    @Reusable
    abstract RealmMapper<Genre, GenreRealm>  genreRealmMapper(GenreRealmMapper genreRealmMapper);




}
