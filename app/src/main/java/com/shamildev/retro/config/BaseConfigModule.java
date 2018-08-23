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

package com.shamildev.retro.config;


import com.shamildev.retro.BuildConfig;
import com.shamildev.retro.domain.config.DataConfig;

/**
 * Provides an abstract config module for other config modules to extend.
 */
public abstract class BaseConfigModule {

    /**
     * The base data config builder to be optionally modified by subclasses.
     * <p>
     * Note that we could retrieve the config values here from a resource/config file or from a
     * remote config endpoint. The values are statically defined here for simplicity and brevity.
     */
    protected static final DataConfig.Builder BASE_CONFIG_BUILDER = DataConfig.builder()
            .baseUrl("https://api.themoviedb.org/")
            .youtubeKey(BuildConfig.YOUTUBE_API_TOKEN)
            .authGrantType("client_credentials")
            .authClientId("pTnZUGJqLM_Q0pQt-jsCXw") // Replace this with your own client id
            .authClientSecret("zRs7F1e2B3Dc4OBNsp7yF0Jfh5eksGRXroaLE59JCxxwZUDO" + "ppzjn6bQaVPB3bop") // Replace this with your own client secret
            .cacheRootDir("business-search")
            .cacheDir("data-cache")
            .cacheMaxSizeMb(10)
            .offlineCacheTimeDays(3)
            .networkCacheTimeSeconds(60);
}
