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
 * Maps {@link MovieEntity} to {@link Movie} .
 */
@Reusable
final class MovieWrapperEntityMapper implements EntityMapper<MovieWrapperEntity, MovieWrapper> {


    private final EntityListMapper entityListMapper;
    private final MovieEntityMapper movieEntityMapper;

    @Inject
    MovieWrapperEntityMapper(EntityListMapper entityListMapper,
                             MovieEntityMapper movieEntityMapper) {

      this.entityListMapper = entityListMapper;
      this.movieEntityMapper = movieEntityMapper;
    }

    @Override
    public MovieWrapper map(MovieWrapperEntity entity) {




        return MovieWrapper.builder()
                .page(entity.getPage())
               // .results(entityListMapper.mapToV(movieEntityMapper,entity.results()))
                .totalPages(entity.getTotalPages())
                .totalResults(entity.getTotalResults())


                .build();
    }

    @Override
    public MovieWrapperEntity map(MovieWrapper entity) {

return null;
//        return MovieWrapperEntity.builder()
//                .page(entity.page())
//                //.results(entityListMapper.mapToK(movieEntityMapper,entity.results()))
//                .totalPages(entity.totalPages())
//                .totalResults(entity.totalResults())
//                .build();

    }
}
