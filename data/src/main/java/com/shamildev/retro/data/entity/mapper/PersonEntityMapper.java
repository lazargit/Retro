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


import com.shamildev.retro.data.entity.tmdb.CrewEntity;
import com.shamildev.retro.data.entity.tmdb.PersonEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.models.Crew;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.Person;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link Result} to {@link Movie} .
 */
@Reusable
final class PersonEntityMapper implements EntityMapper<PersonEntity, Person> {




    @Inject
    PersonEntityMapper() {

    }


    @Override
    public Person map(PersonEntity entity) {

       return Person.builder()
                .id(entity.getId())
                .name(entity.getName())
                .gender(entity.getGender())
                .profilePath(entity.getProfilePath())
                .build();

    }

    @Override
    public PersonEntity map(Person model) {
        return null;
    }
}
