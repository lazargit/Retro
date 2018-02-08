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





import com.shamildev.retro.data.entity.tmdb.BackdropEntity;
import com.shamildev.retro.data.entity.tmdb.PosterEntity;
import com.shamildev.retro.data.entity.tmdb.response.ImagesResponse;
import com.shamildev.retro.data.entity.tmdb.response.MovieResponse;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.data.entity.tmdb.GenreEntity;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.ImageModel;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;


import javax.inject.Inject;

import dagger.Reusable;

/**
 * Holds instances of {@link EntityMapper}s.
 */
@Reusable
public  class EntityMapperHolder {

    private final EntityMapper<Result, Movie> movieEntityMapper;
    private final EntityMapper<MovieResponse, Movie> movieDetailsEntityMapper;
    private final EntityMapper<ImagesResponse, Images> imagesResponseEntityMapper;

    private final EntityMapper<PosterEntity,ImageModel > posterEntityMapper;



    @Inject EntityMapper<BackdropEntity,ImageModel > backdropEntityMapper;

    private final EntityMapper<ResponseEntity, MovieWrapper> movieWrapperEntityMapper;
    private final EntityMapper<ConfigurationResponseEntity, Configuration> configurationEntityMapper;
    private final EntityMapper<GenreEntity, Genre> genreEntityMapper;


    @Inject
    EntityMapperHolder( EntityMapper<Result, Movie> movieEntityMapper,
                        EntityMapper<MovieResponse, Movie> movieDetailsEntityMapper,
                        EntityMapper<ResponseEntity, MovieWrapper> movieWrapperEntityMapper,
                        EntityMapper<ConfigurationResponseEntity, Configuration> configurationEntityMapper,
                        EntityMapper<GenreEntity, Genre> genreEntityMapper,
                        EntityMapper<ImagesResponse, Images> imagesResponseEntityMapper,
                        EntityMapper<PosterEntity,ImageModel > posterEntityMapper
                         ) {

        this.movieEntityMapper = movieEntityMapper;
        this.movieDetailsEntityMapper = movieDetailsEntityMapper;
        this.movieWrapperEntityMapper = movieWrapperEntityMapper;
        this.configurationEntityMapper = configurationEntityMapper;
        this.genreEntityMapper = genreEntityMapper;
        this.imagesResponseEntityMapper = imagesResponseEntityMapper;
        this.posterEntityMapper = posterEntityMapper;



    }

    public EntityMapper<Result, Movie> movieEntityMapper() {
        return movieEntityMapper;
    }

    public EntityMapper<MovieResponse, Movie> movieDetailsEntityMapper() {
        return movieDetailsEntityMapper;
    }

    public EntityMapper<ResponseEntity, MovieWrapper> movieWrapperEntityMapper() {
        return movieWrapperEntityMapper;
    }

    public EntityMapper<ConfigurationResponseEntity, Configuration> configurationEntityMapper() {
        return configurationEntityMapper;
    }


    public EntityMapper<GenreEntity, Genre> genreEntityMapper() {
        return genreEntityMapper;
    }

    public EntityMapper<ImagesResponse, Images> imagesResponseEntityMapper() {
        return imagesResponseEntityMapper;
    }

    public EntityMapper<PosterEntity, ImageModel> posterEntityMapper() {
        return posterEntityMapper;
    }

    public EntityMapper<BackdropEntity, ImageModel> backdropEntityMapper() {
        return backdropEntityMapper;
    }

}
