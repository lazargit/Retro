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
import com.shamildev.retro.data.entity.tmdb.PersonEntity;
import com.shamildev.retro.data.entity.tmdb.SeasonEntity;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.Person;
import com.shamildev.retro.domain.models.Season;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link Result} to {@link Movie} .
 */
@Reusable
final class SeasonEntityMapper implements EntityMapper<SeasonEntity, Season> {




    @Inject
    SeasonEntityMapper() {

    }


    @Override
    public Season map(SeasonEntity entity) {
       return Season.builder()
               .airDate(entity.getAirDate())
               .episodeCount(entity.getEpisodeCount())
               .id(entity.getId())
               .posterPath(entity.getPosterPath())
               .seasonNumber(entity.getSeasonNumber())
               .build();

    }

    @Override
    public SeasonEntity map(Season model) {
        return null;
    }
}
