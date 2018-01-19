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

package com.shamildev.retro.data.cache.realm.mapper;


import com.shamildev.retro.data.cache.realm.models.ConfigurationRealm;
import com.shamildev.retro.data.cache.realm.models.GenreRealm;
import com.shamildev.retro.data.cache.realm.models.MovieRealm;
import com.shamildev.retro.data.cache.realm.models.TMDbConfigurationRealm;
import com.shamildev.retro.data.entity.mapper.EntityMapper;
import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.data.entity.tmdb.GenreEntity;
import com.shamildev.retro.data.entity.tmdb.MovieDetailsResponseEntity;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Holds instances of {@link EntityMapper}s.
 */
@Reusable
public final class RealmMapperHolder {

    private final RealmMapper<Movie, MovieRealm> movieRealmMapper;
    private final RealmMapper<Configuration, TMDbConfigurationRealm> configurationRealmMapper;
    private final RealmMapper<Genre, GenreRealm> genreRealmMapper;



    @Inject
    RealmMapperHolder(RealmMapper<Movie, MovieRealm> movieRealmMapper,
                      RealmMapper<Configuration, TMDbConfigurationRealm> configurationRealmMapper,
                      RealmMapper<Genre, GenreRealm> genreRealmMapper) {

        this.movieRealmMapper = movieRealmMapper;
        this.configurationRealmMapper = configurationRealmMapper;
        this.genreRealmMapper = genreRealmMapper;


    }

    public RealmMapper<Movie, MovieRealm> movieRealmMapper() {
        return movieRealmMapper;
    }

    public RealmMapper<Configuration, TMDbConfigurationRealm> configurationRealmMapper() {
        return configurationRealmMapper;
    }
    public RealmMapper<Genre, GenreRealm> genreRealmMapper() {
        return genreRealmMapper;
    }




}
