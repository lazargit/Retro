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

import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.models.Movie;


import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link Result} to {@link Movie} .
 */
@Reusable
final class MovieEntityMapper implements EntityMapper<Result, Movie> {


   // private final EntityListMapper entityListMapper;

    @Inject
    MovieEntityMapper() {

      //  this.entityListMapper = entityListMapper;
    }

    @Override
    public Movie map(Result entity) {


        System.out.println("Mapper>>>>>"+entity.getTitle());
        return Movie.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .overview(entity.getOverview())

                .originalTitle(entity.getOriginalTitle())
                .originalLanguage(entity.getOriginalLanguage())

                .posterPath(entity.getPosterPath())
                .backdropPath(entity.getBackdropPath())

                .genreIds(entity.getGenreIds())
                .adult(entity.getAdult())
                .video(entity.getVideo())





                .releaseDate(entity.getReleaseDate())


                .popularity(entity.getPopularity())
                .voteAverage(entity.getVoteAverage())
                .voteCount(entity.getVoteCount())

                .build();
    }

    @Override
    public Result map(Movie entity) {

        return null;

    }
}
