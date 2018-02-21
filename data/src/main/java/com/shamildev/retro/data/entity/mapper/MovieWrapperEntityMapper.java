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

import android.util.Log;


import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.domain.models.MovieWrapper;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link ResponseEntity} to {@link MovieWrapper} .
 */
@Reusable
final class MovieWrapperEntityMapper implements EntityMapper<ResponseEntity, MovieWrapper> {


    private final EntityListMapper entityListMapper;
    private final MovieEntityMapper movieEntityMapper;

    @Inject
    MovieWrapperEntityMapper(EntityListMapper entityListMapper,
                             MovieEntityMapper movieEntityMapper) {

      this.entityListMapper = entityListMapper;
      this.movieEntityMapper = movieEntityMapper;
    }

    @Override
    public MovieWrapper map(ResponseEntity entity) throws MappingError {




        return MovieWrapper.builder()
                .page(entity.getPage())
                .totalPages(entity.getTotalPages())
                .totalResults(entity.getTotalResults())
                .results(entityListMapper.mapToV(movieEntityMapper,entity.getResults()))
                .build();
    }

    @Override
    public ResponseEntity map(MovieWrapper entity) {

        return null;


    }
}
