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

package com.shamildev.retro.data.entity.mapper;


import com.shamildev.retro.data.entity.tmdb.MovieDetailsResponseEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.data.entity.tmdb.GenreEntity;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

/**
 * Provides entity mapper dependencies.
 */
@Module
public abstract class EntityMapperModule {

    @Binds
    @Reusable
    abstract EntityMapper<Result, Movie>  movieEntityMapper(MovieEntityMapper movieEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<MovieDetailsResponseEntity, Movie>  movieDetailsEntityMapper(MovieDetailsEntityMapper movieDetailsEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<ResponseEntity, MovieWrapper>  movieEntityMapperMapper(MovieWrapperEntityMapper movieWrapperEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<ConfigurationResponseEntity, Configuration>  configurationEntityMapper(ConfigurationEntityMapper configurationEntityMapper);

    @Binds
    @Reusable
    abstract EntityMapper<GenreEntity, Genre>  genrenEntityMapper(GenreEntityMapper genreEntityMapper);


}
