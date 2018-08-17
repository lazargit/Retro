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


import com.google.gson.JsonObject;
import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.domain.models.Configuration;

import javax.inject.Inject;

import dagger.Reusable;

/**
 * Maps {@link ConfigurationResponseEntity} to {@link Configuration} .
 */
@Reusable
final class ConfigurationEntityMapper implements EntityMapper<ConfigurationResponseEntity, Configuration>{




    @Inject
    ConfigurationEntityMapper() {

    }

    @Override
    public Configuration map(ConfigurationResponseEntity entity) {

        return Configuration.builder()
                .changeKeys(entity.getChangeKeys())
                .baseUrl(entity.getImages().getBaseUrl())
                .secureBaseUrl(entity.getImages().getSecureBaseUrl())
                .logoSizes(entity.getImages().getLogoSizes())
                .posterSizes(entity.getImages().getPosterSizes())
                .backdropSizes(entity.getImages().getBackdropSizes())
                .profileSizes(entity.getImages().getProfileSizes())
                .stillSizes(entity.getImages().getStillSizes())
                .lastUpdate(0L)
                .build();
    }

    @Override
    public ConfigurationResponseEntity map(Configuration entity) {

        return null;

    }


}
