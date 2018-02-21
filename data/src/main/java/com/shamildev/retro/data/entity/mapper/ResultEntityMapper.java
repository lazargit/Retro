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

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.Person;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.TVShow;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link ResponseEntity} to {@link MovieWrapper} .
 */
@Reusable
final class ResultEntityMapper implements EntityMapper<ResponseEntity, ResultWrapper> {


    private final EntityListMapper entityListMapper;
    private final MovieEntityMapper movieEntityMapper;
    private final TVShowEntityMapper tvShowEntityMapper;
    private final PersonResultEntityMapper personResultEntityMapper;

    @Inject
    ResultEntityMapper(EntityListMapper entityListMapper,
                       MovieEntityMapper movieEntityMapper,
                       TVShowEntityMapper tvShowEntityMapper,
                       PersonResultEntityMapper personResultEntityMapper) {

      this.entityListMapper = entityListMapper;
      this.movieEntityMapper = movieEntityMapper;
      this.tvShowEntityMapper = tvShowEntityMapper;
      this.personResultEntityMapper = personResultEntityMapper;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public  ResultWrapper map(ResponseEntity entity) throws MappingError {


        Log.d("ResultEntityMapper", "results "+entity.getResults().size());


        List<DomainObject> list= new ArrayList<>();
        for (Result item : entity.getResults()){
           list.add(MediaTypeFactory.create(item,movieEntityMapper,tvShowEntityMapper,personResultEntityMapper ));
        }

        list.forEach(domainObject -> {
            if(domainObject instanceof Movie){
               // Log.d("##**",Movie.class.getName());
            }
            if(domainObject instanceof TVShow){
              //  Log.d("##**",TVShow.class.getName());
            }
            if(domainObject instanceof Person){
                Log.d("##**",Person.class.getName());
                Person person = (Person) domainObject;
                if(person.knownFor().size()>0){
                    Log.d("bekannt aus ",":");



                        for (DomainObject item :person.knownFor()) {
                                if(item instanceof Movie){
                                    Movie item1 = (Movie) item;
                                    Log.d("# ",item1.title());
                                }



                        }




                }
            }


           // Log.d("##",domainObject.getClass().getName());

        });





        return ResultWrapper.builder()
                .page(entity.getPage())
                .totalPages(entity.getTotalPages())
                .totalResults(entity.getTotalResults())
                .results(list)
                .build();

    }

    @Override
    public ResponseEntity map(ResultWrapper entity) {

        return null;


    }
}
