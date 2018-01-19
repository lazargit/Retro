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

package com.shamildev.retro.data.cache.realm.mapper;


import com.shamildev.retro.data.cache.realm.models.ConfigurationRealm;
import com.shamildev.retro.data.cache.realm.models.GenreRealm;
import com.shamildev.retro.data.cache.realm.models.MovieRealm;

import com.shamildev.retro.data.cache.realm.models.TMDbConfigurationRealm;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;


import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

/**
 * Provides entity mapper dependencies.
 */
@Module
public abstract class RealmMapperModule {

    @Binds
    @Reusable
    abstract RealmMapper<Movie, MovieRealm>  movieRealmMapper(MovieRealmMapper movieRealmMapper);

    @Binds
    @Reusable
    abstract RealmMapper<Configuration, TMDbConfigurationRealm>  configRealmMapper(ConfigurationRealmMapper configurationRealmMapper);

    @Binds
    @Reusable
    abstract RealmMapper<Genre, GenreRealm>  genreRealmMapper(GenreRealmMapper genreRealmMapper);




}
