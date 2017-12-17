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

import com.shamildev.retro.data.entity.MovieEntity;
import com.shamildev.retro.data.entity.MovieWrapperEntity;
import com.shamildev.retro.domain.repository.Movie;
import com.shamildev.retro.domain.repository.MovieWrapper;


import javax.inject.Inject;

import dagger.Reusable;

/**
 * Holds instances of {@link EntityMapper}s.
 */
@Reusable
public final class EntityMapperHolder {

    private final EntityMapper<MovieEntity, Movie> movieEntityMapper;
    private final EntityMapper<MovieWrapperEntity, MovieWrapper> movieWrapperEntityMapper;


    @Inject
    EntityMapperHolder( EntityMapper<MovieEntity, Movie> movieEntityMapper,
                        EntityMapper<MovieWrapperEntity, MovieWrapper> movieWrapperEntityMapper) {

        this.movieEntityMapper = movieEntityMapper;
        this.movieWrapperEntityMapper = movieWrapperEntityMapper;

    }

    public EntityMapper<MovieEntity, Movie> movieEntityMapper() {
        return movieEntityMapper;
    }

    public EntityMapper<MovieWrapperEntity, MovieWrapper> movieWrapperEntityMapper() {
        return movieWrapperEntityMapper;
    }


}
