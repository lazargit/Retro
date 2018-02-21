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
import com.shamildev.retro.data.entity.tmdb.CastEntity;
import com.shamildev.retro.data.entity.tmdb.CrewEntity;
import com.shamildev.retro.data.entity.tmdb.NetworkEntity;
import com.shamildev.retro.data.entity.tmdb.PersonEntity;
import com.shamildev.retro.data.entity.tmdb.PosterEntity;
import com.shamildev.retro.data.entity.tmdb.SeasonEntity;
import com.shamildev.retro.data.entity.tmdb.response.CreditsResponse;
import com.shamildev.retro.data.entity.tmdb.response.ImagesResponse;
import com.shamildev.retro.data.entity.tmdb.response.MovieResponse;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.data.entity.tmdb.GenreEntity;
import com.shamildev.retro.data.entity.tmdb.response.TVShowResponse;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Cast;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Credits;
import com.shamildev.retro.domain.models.Crew;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.ImageModel;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.Person;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.Season;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.domain.models.TVShowNetwork;


import javax.inject.Inject;

import dagger.Reusable;

/**
 * Holds instances of {@link EntityMapper}s.
 */
@Reusable
public  class EntityMapperHolder {





    private final EntityMapper<Result, Movie>  movieEntityMapper;




    private final EntityMapper<MovieResponse, Movie> movieDetailsEntityMapper;
    private final EntityMapper<ImagesResponse, Images> imagesResponseEntityMapper;

    private final EntityMapper<PosterEntity,ImageModel > posterEntityMapper;



    @Inject EntityMapper<BackdropEntity,ImageModel > backdropEntityMapper;

    private final EntityMapper<ResponseEntity, MovieWrapper> movieWrapperEntityMapper;
    private final EntityMapper<ResponseEntity, ResultWrapper> resultWrapperEntityMapper;


    private final EntityMapper<ConfigurationResponseEntity, Configuration> configurationEntityMapper;
    private final EntityMapper<GenreEntity, Genre> genreEntityMapper;


    private final EntityMapper<CreditsResponse, Credits> creditsResponseEntityMapper;
    private final  EntityMapper<CastEntity, Cast> castEntityMapper;
    private final  EntityMapper<CrewEntity, Crew> crewEntityMapper;


    private final  EntityMapper<TVShowResponse, TVShow> tvshowEntityMapper;
    private final  EntityMapper<NetworkEntity, TVShowNetwork> tvshowNetworkEntityMapper;
    private final  EntityMapper<SeasonEntity, Season> seasonEntityMapper;
    private final  EntityMapper<PersonEntity, Person> personEntityMapper;



    @Inject
    EntityMapperHolder( EntityMapper<Result, Movie> movieEntityMapper,
                        EntityMapper<MovieResponse, Movie> movieDetailsEntityMapper,
                        EntityMapper<ResponseEntity, MovieWrapper> movieWrapperEntityMapper,
                        EntityMapper<ResponseEntity, ResultWrapper> resultWrapperEntityMapper,
                        EntityMapper<ConfigurationResponseEntity, Configuration> configurationEntityMapper,
                        EntityMapper<GenreEntity, Genre> genreEntityMapper,
                        EntityMapper<ImagesResponse, Images> imagesResponseEntityMapper,
                        EntityMapper<PosterEntity,ImageModel > posterEntityMapper,
                        EntityMapper<CreditsResponse, Credits> creditsResponseEntityMapper,
                        EntityMapper<CastEntity, Cast> castEntityMapper,
                        EntityMapper<CrewEntity, Crew> crewEntityMapper,
                        EntityMapper<TVShowResponse, TVShow> tvshowEntityMapper,
                        EntityMapper<NetworkEntity, TVShowNetwork> tvshowNetworkEntityMapper,
                        EntityMapper<SeasonEntity, Season> seasonEntityMapper,
                        EntityMapper<PersonEntity, Person> personEntityMapper
                         ) {

        this.movieEntityMapper = movieEntityMapper;


        this.movieDetailsEntityMapper = movieDetailsEntityMapper;
        this.movieWrapperEntityMapper = movieWrapperEntityMapper;
        this.resultWrapperEntityMapper = resultWrapperEntityMapper;
        this.configurationEntityMapper = configurationEntityMapper;
        this.genreEntityMapper = genreEntityMapper;
        this.imagesResponseEntityMapper = imagesResponseEntityMapper;
        this.posterEntityMapper = posterEntityMapper;
        this.creditsResponseEntityMapper = creditsResponseEntityMapper;
        this.castEntityMapper = castEntityMapper;
        this.crewEntityMapper = crewEntityMapper;
        this.tvshowEntityMapper = tvshowEntityMapper;
        this.tvshowNetworkEntityMapper = tvshowNetworkEntityMapper;
        this.seasonEntityMapper = seasonEntityMapper;
        this.personEntityMapper = personEntityMapper;






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

    public EntityMapper<ResponseEntity, ResultWrapper> resultWrapperEntityMapper() {
        return resultWrapperEntityMapper;
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




    public EntityMapper<CreditsResponse, Credits> creditsResponseEntityMapper() {
        return creditsResponseEntityMapper;
    }
    public  EntityMapper<CastEntity, Cast> castEntityMapper() {

        return castEntityMapper;
    }

    public  EntityMapper<CrewEntity, Crew> crewEntityMapper() {

        return crewEntityMapper;
    }


    public  EntityMapper<TVShowResponse, TVShow> tvshowEntityMapper() {

        return tvshowEntityMapper;
    }

    public EntityMapper<NetworkEntity, TVShowNetwork> tvshowNetworkEntityMapper() {
        return tvshowNetworkEntityMapper;
    }

    public  EntityMapper<SeasonEntity, Season> seasonEntityMapper() {

        return seasonEntityMapper;
    }

    public  EntityMapper<PersonEntity, Person> personEntityMapper() {

        return personEntityMapper;
    }

}
