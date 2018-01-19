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

package com.shamildev.retro.data.cache;




import com.shamildev.retro.data.cache.realm.RealmModule;
import com.shamildev.retro.data.cache.realm.mapper.RealmMapperModule;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;


/**
 * Provides entity dependencies.
 */


@Module( includes = {
        RealmModule.class,
        RealmMapperModule.class
})
public abstract class CacheModule {

    @Binds
    @Reusable
    abstract CacheRepository cacheRepository(RealmCacheRepository repository);




}

