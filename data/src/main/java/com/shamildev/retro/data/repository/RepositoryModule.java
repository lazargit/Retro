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

package com.shamildev.retro.data.repository;

import com.shamildev.retro.data.entity.EntityModule;
import com.shamildev.retro.data.net.NetModule;
import com.shamildev.retro.domain.repository.FirebaseRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

/**
 * Provides repository dependencies.
 */
@Module( includes = {
        EntityModule.class,
        NetModule.class
})
public abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract RemoteRepository businessRepository(TMDBRepository repository);

    @Binds
    @Reusable
    abstract FirebaseRepository firebaseRepository(FirebaseRepositoryImpl repository);


}
