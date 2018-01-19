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

import com.shamildev.retro.data.entity.tmdb.GenreEntity;
import com.shamildev.retro.data.entity.tmdb.MovieDetailsResponseEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link Result} to {@link Movie} .
 */
@Reusable
final class MovieDetailsEntityMapper implements EntityMapper<MovieDetailsResponseEntity, Movie> {


    private final EntityListMapper entityListMapper;

    private final GenreEntityMapper genreEntityMapper;

    @Inject
    MovieDetailsEntityMapper(GenreEntityMapper genreEntityMapper,EntityListMapper entityListMapper) {

    this.genreEntityMapper = genreEntityMapper;
    this.entityListMapper = entityListMapper;
    }

    @Override
    public Movie map(MovieDetailsResponseEntity entity) {
        List<GenreEntity> genres = entity.getGenres();

        List<Genre> genres1 = entityListMapper.mapToV(genreEntityMapper, entity.getGenres());


        return Movie.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .overview(entity.getOverview())

                .originalTitle(entity.getTitle())
                .originalLanguage(entity.getOriginalLanguage())

                .posterPath(entity.getPosterPath())
                .backdropPath(entity.getBackdropPath())


                .adult(entity.getAdult())
                .video(entity.getVideo())





                .releaseDate(entity.getReleaseDate())
                .popularity(entity.getPopularity())
                .voteAverage(entity.getVoteAverage())


                .budget(entity.getBudget())
                .revenue(entity.getRevenue())
                .status(entity.getStatus())
                .tagline(entity.getTagline())
                .homepage(entity.getHomepage())
                .imdbId(entity.getImdbId())
                .voteCount(entity.getVoteCount())


                .genres(entityListMapper.mapToV(genreEntityMapper, entity.getGenres()))
              //  .productionCountries(entity.getProductionCountries())

                .build();



//        (Integer budget,
//                Integer revenue,
//                Integer runtime,
//                String status,
//                String tagline,
//                String homepage,
//                String imdbId,
//                Integer voteCount
    }

    @Override
    public MovieDetailsResponseEntity map(Movie entity) {

        return null;

    }
}
