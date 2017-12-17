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
import com.shamildev.retro.domain.repository.Movie;


import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link MovieEntity} to {@link Movie} .
 */
@Reusable
final class MovieEntityMapper implements EntityMapper<MovieEntity, Movie> {


   // private final EntityListMapper entityListMapper;

    @Inject
    MovieEntityMapper() {

      //  this.entityListMapper = entityListMapper;
    }

    @Override
    public Movie map(MovieEntity entity) {


        return Movie.builder()
                .id(entity.id())
                .title(entity.title())
                .overview(entity.overview())

                .originalTitle(entity.originalTitle())
                .originalLanguage(entity.originalLanguage())

                .posterPath(entity.posterPath())
                .backdropPath(entity.backdropPath())

                .genreIds(entity.genreIds())
                .adult(entity.adult())
                .video(entity.video())



                .releaseDate(entity.releaseDate())


                .popularity(entity.popularity())
                .voteAverage(entity.voteAverage())
                .voteCount(entity.voteCount())

                .build();
    }

    @Override
    public MovieEntity map(Movie entity) {


        return MovieEntity.builder()
                .id(entity.id())
                .title(entity.title())
                .overview(entity.overview())

                .originalTitle(entity.originalTitle())
                .originalLanguage(entity.originalLanguage())

                .posterPath(entity.posterPath())
                .backdropPath(entity.backdropPath())

                .genreIds(entity.genreIds())
                .adult(entity.adult())
                .video(entity.video())



                .releaseDate(entity.releaseDate())


                .popularity(entity.popularity())
                .voteAverage(entity.voteAverage())
                .voteCount(entity.voteCount())
                .build();
    }
}
