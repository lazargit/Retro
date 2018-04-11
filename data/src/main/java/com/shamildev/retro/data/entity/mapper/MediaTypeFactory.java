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
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Person;
import com.shamildev.retro.domain.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;




public final class MediaTypeFactory {




    @SuppressWarnings("unchecked")
    public static  <T extends DomainObject> T create(Result item, MovieEntityMapper movieEntityMapper, TVShowEntityMapper tvShowEntityMapper, PersonResultEntityMapper personResultEntityMapper) {





        T mediaType = null;

        if(item.getMediaType().equals(Constants.MEDIA_TYPE.MOVIE.toString())){

            mediaType = (T) movieEntityMapper.map(item);


        }
        if(item.getMediaType().equals(Constants.MEDIA_TYPE.TV.toString())){

            mediaType = (T) tvShowEntityMapper.map(item);
        }
        if(item.getMediaType().equals(Constants.MEDIA_TYPE.PERSON.toString())){


            mediaType = (T) personResultEntityMapper.map(item);

        }




        return mediaType;

    }



}
