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
import com.shamildev.retro.data.entity.tmdb.PosterEntity;
import com.shamildev.retro.data.entity.tmdb.Result;
import com.shamildev.retro.data.entity.tmdb.response.ImagesResponse;
import com.shamildev.retro.domain.models.ImageModel;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;

import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Maps {@link Result} to {@link Movie} .
 */
@Reusable
final class ImagesResponseEntityMapper implements EntityMapper<ImagesResponse, Images> {


    private final PosterEntityMapper posterEntityMapper;
    private final BackdropEntityMapper backdropEntityMapper;

    @Inject
    ImagesResponseEntityMapper(PosterEntityMapper posterEntityMapper,BackdropEntityMapper backdropEntity) {
         this.posterEntityMapper = posterEntityMapper;
         this.backdropEntityMapper = backdropEntity;


    }

    @Override
    public Images map(ImagesResponse imagesResponse) {
        List<PosterEntity> posters = imagesResponse.getPosters();

        List<ImageModel> imageModels = Observable.just(imagesResponse.getPosters())
                .map(posterEntities -> Flowable.fromIterable(posterEntities)
                        .map(posterEntityMapper::map)
                        .toList()
                        .blockingGet())
                .blockingSingle();

        List<ImageModel> imageModelBackdrops = Observable.just(imagesResponse.getBackdrops())
                .map(posterEntities -> Flowable.fromIterable(posterEntities)
                        .map(backdropEntityMapper::map)
                        .toList()
                        .blockingGet())
                .blockingSingle();


        return   Images.builder()
                .id(imagesResponse.getId())
                .posters(imageModels)
                .backdrops(imageModelBackdrops)
                .build();
    }

    @Override
    public ImagesResponse map(Images images) {
        return null;
    }
}
