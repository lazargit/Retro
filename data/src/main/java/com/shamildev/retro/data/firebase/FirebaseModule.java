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

package com.shamildev.retro.data.firebase;
import com.shamildev.retro.domain.repository.FirebaseRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Reusable;


/**
 * Provides entity dependencies.
 */


@Module
public abstract class FirebaseModule {

    @Binds
    @Reusable
    abstract FirebaseRepository firebaseRepository(FirebaseRepository repository);

}
