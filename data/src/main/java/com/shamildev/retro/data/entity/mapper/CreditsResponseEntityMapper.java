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

import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.CastEntity;
import com.shamildev.retro.data.entity.tmdb.PosterEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.data.entity.tmdb.response.CreditsResponse;
import com.shamildev.retro.data.entity.tmdb.response.ImagesResponse;
import com.shamildev.retro.domain.models.Cast;
import com.shamildev.retro.domain.models.Credits;
import com.shamildev.retro.domain.models.Crew;
import com.shamildev.retro.domain.models.ImageModel;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Maps {@link Result} to {@link Movie} .
 */
@Reusable
final class CreditsResponseEntityMapper implements EntityMapper<CreditsResponse, Credits> {


    private final CastEntityMapper castEntityMapper;
    private final CrewEntityMapper crewEntityMapper;

    @Inject
    CreditsResponseEntityMapper(CastEntityMapper castEntityMapper,CrewEntityMapper crewEntityMapper) {
         this.castEntityMapper = castEntityMapper;
         this.crewEntityMapper = crewEntityMapper;


    }

    @Override
    public Credits map(CreditsResponse creditsResponse) throws MappingError {
        Credits build = null;
        Timber.d( "mapping CreditsResponseEntityMapper"+creditsResponse.getId());




        try {


            List<Cast> castList = Observable.just(creditsResponse.getCast())
                    .map(posterEntities -> Flowable.fromIterable(posterEntities)
                            .map(castEntityMapper::map)
                            .toList()
                            .blockingGet())
                    .blockingSingle();



            List<Crew> crewList = Observable.just(creditsResponse.getCrew())
                    .map(crewEntities ->  Flowable.fromIterable(crewEntities)
                            .map(crewEntityMapper::map)
                            .toList()
                            .blockingGet())
                    .blockingSingle();



            build = Credits.create(creditsResponse.getId(),castList,crewList);





        }catch (NullPointerException e){


                throw MappingError.getError(e.getMessage(),this.getClass());

        }

        return build;
    }

    @Override
    public CreditsResponse map(Credits images) {
        return null;
    }
}
