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

import com.shamildev.retro.data.entity.tmdb.PersonEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.Person;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link Result} to {@link Movie} .
 */
@Reusable
final class PersonResultEntityMapper implements EntityMapper<Result, Person> {




//    private final EntityListMapper entityListMapper;
    private final  MovieEntityMapper movieEntityMapper;
    private final TVShowEntityMapper tvShowEntityMapper;



    @Inject
    PersonResultEntityMapper(MovieEntityMapper movieEntityMapper,TVShowEntityMapper tvShowEntityMapper) {

        this.movieEntityMapper = movieEntityMapper;
        this.tvShowEntityMapper = tvShowEntityMapper;


    }


    @Override
    public Person map(Result entity) {

        List<DomainObject> list= new ArrayList<>();

        if(entity.getKnownFor().size() > 0){

            for (Result resultItem : entity.getKnownFor()) {

                list.add(MediaTypeFactory.create(resultItem, movieEntityMapper, tvShowEntityMapper, this));

            }
        }


        return Person.create(entity.getId(),entity.getName(),0,entity.getProfilePath(),entity.getPopularity(),list);

    }

    @Override
    public Result map(Person model) {
        return null;
    }
}
