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


/**
 * Creates concrete data services.
 */

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

//    <V extends DomainObject> List<V> mapToV(EntityMapper<K, V> entityMapper,
//                                                              List<K> kList) throws MappingError {
//        List<V> vList = new ArrayList<>(kList.size());
//        for (K k : kList) {
//            vList.add(entityMapper.map(k));
//        }
//        return vList;
//    }




//    <T> T createWithAuth(Class<T> serviceClass,
//                         AuthRequestInterceptor authRequestInterceptor) {
//        // AuthRequestInterceptor has to be passed in as a parameter to avoid dependency cycle
//        OkHttpClient.Builder okHttpClientBuilder = okHttpClientBuilder();
//        okHttpClientBuilder.addInterceptor(authRequestInterceptor);
//        return retrofit(okHttpClientBuilder).create(serviceClass);
//    }


}
