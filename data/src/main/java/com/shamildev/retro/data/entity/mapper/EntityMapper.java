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


import com.shamildev.retro.data.entity.Entity;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.ResultWrapper;

/**
 * Maps entity K to V and vice versa.
 *
 * @param <K> the type of the {@link Entity}
 * @param <V> the type of the {@link DomainObject}
 */
public interface EntityMapper<K extends Entity, V extends DomainObject> {

    V map(K k) throws MappingError;



    K map(V v);
}
