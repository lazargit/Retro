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

package com.shamildev.retro.domain.config;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.annotations.Nullable;


/**
 * Contains configuration values used throughout the data module.
 */
@AutoValue
public abstract class DataConfig {

    /**
     * @return a new {@link Builder}
     */
    public static Builder builder() {



        return new AutoValue_DataConfig.Builder();
    }

    public abstract String baseUrl();

    public abstract String authGrantType();

    public abstract String authClientId();

    public abstract String authClientSecret();

    public abstract String cacheRootDir();

    public abstract String cacheDir();

    public abstract int cacheMaxSizeMb();

    public abstract int offlineCacheTimeDays();

    public abstract int networkCacheTimeSeconds();

    public abstract boolean debug();

    public abstract String language();

    public abstract String country();

    @Nullable
    public abstract Configuration configurations();
    @Nullable
    public abstract List<Genre> genres();

    public DataConfig setBaseUrl(String arg) {
        Builder builder = getBuilder();
        builder.baseUrl(arg);
        return builder.build();
    }
    public  DataConfig setLanguage(String arg) {
        Builder builder = getBuilder();
        builder.language(arg);
        return builder.build();
    }

    public DataConfig addConfiguration(Configuration configuration) {
        Builder builder = getBuilder();
        builder.configurations(configuration);
        return builder.build();
    }
    public  DataConfig addGenres(List<Genre> genres) {
        Builder builder = getBuilder();
        builder.genres(genres);
        return builder.build();
    }



    private Builder getBuilder() {
        return builder()
                    .baseUrl(this.baseUrl())
                    .authGrantType(this.authGrantType())
                    .authClientId(this.authClientId())
                    .authClientSecret(this.authClientSecret())
                    .cacheRootDir(this.cacheRootDir())
                    .cacheDir(this.cacheDir())
                    .cacheMaxSizeMb(this.cacheMaxSizeMb())
                    .offlineCacheTimeDays(this.offlineCacheTimeDays())
                    .networkCacheTimeSeconds(this.networkCacheTimeSeconds())
                    .debug(this.debug())
                    .language(this.language())
                    .country(this.country())
                    .configurations(this.configurations())
                    .genres(this.genres());
    }

    public static DataConfig create(String baseUrl, String authGrantType, String authClientId, String authClientSecret, String cacheRootDir, String cacheDir, int cacheMaxSizeMb, int offlineCacheTimeDays, int networkCacheTimeSeconds, boolean debug, String language, String country, Configuration configurations, List<Genre> genres) {
        return builder()
                .baseUrl(baseUrl)
                .authGrantType(authGrantType)
                .authClientId(authClientId)
                .authClientSecret(authClientSecret)
                .cacheRootDir(cacheRootDir)
                .cacheDir(cacheDir)
                .cacheMaxSizeMb(cacheMaxSizeMb)
                .offlineCacheTimeDays(offlineCacheTimeDays)
                .networkCacheTimeSeconds(networkCacheTimeSeconds)
                .debug(debug)
                .language(language)
                .country(country)
                .configurations(configurations)
                .genres(genres)
                .build();
    }


    public DataConfig() {
    }

    /**
     * Builder used to create instances of {@link DataConfig}
     */

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder baseUrl(String baseUrl);

        public abstract Builder authGrantType(String authGrantType);

        public abstract Builder authClientId(String authClientId);

        public abstract Builder authClientSecret(String authClientSecret);

        public abstract Builder cacheRootDir(String cacheRootDir);

        public abstract Builder cacheDir(String cacheDir);

        public abstract Builder cacheMaxSizeMb(int cacheMaxSizeMb);

        public abstract Builder offlineCacheTimeDays(int offlineCacheTimeDays);

        public abstract Builder networkCacheTimeSeconds(int networkCacheTimeSeconds);

        public abstract Builder debug(boolean debug);

        public abstract Builder language(String language);

        public abstract Builder country(String country);

        public abstract Builder configurations(Configuration configurations);

        public abstract Builder genres(List<Genre> genres);

        public abstract DataConfig build();
    }









}