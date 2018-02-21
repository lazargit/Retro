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

import com.shamildev.retro.data.entity.Result;
import com.shamildev.retro.data.entity.tmdb.BackdropEntity;
import com.shamildev.retro.data.entity.tmdb.CastEntity;
import com.shamildev.retro.domain.models.Cast;
import com.shamildev.retro.domain.models.ImageModel;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.Person;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link Result} to {@link Movie} .
 */
@Reusable
final class CastEntityMapper implements EntityMapper<CastEntity, Cast> {




    @Inject
    CastEntityMapper() {

    }


    @Override
    public Cast map(CastEntity entity) {
        Person person = Person.builder()
                .id(entity.getId())
                .name(entity.getName())
                .gender(entity.getGender())
                .profilePath(entity.getProfilePath())
                .build();

       return Cast.builder()
                .person(person)
                .castId(entity.getCastId())
                .creditId(entity.getCreditId())
                .character(entity.getCharacter())
                .order(entity.getOrder())
                .build();




    }

    @Override
    public CastEntity map(Cast model) {
        return null;
    }
}
