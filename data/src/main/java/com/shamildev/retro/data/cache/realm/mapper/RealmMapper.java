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




import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.DomainObjectStorable;

import io.realm.RealmObject;

/**
 * Maps entity K to V and vice versa.
 *
 * @param <K> the type of the {@link DomainObjectStorable}
 * @param <V> the type of the {@link RealmObject}
 */
public interface RealmMapper<K extends DomainObject, V extends RealmObject> {

    V map(K k);

    K map(V v);
}
