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



import com.shamildev.retro.data.entity.Entity;
import com.shamildev.retro.data.entity.mapper.EntityMapper;
import com.shamildev.retro.domain.DomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Observable;
import io.realm.RealmList;

/**
 * Maps lists of entities.
 */
@Reusable
final class RealmListMapper {

    @Inject
    RealmListMapper() {
    }


    public static RealmList listToRealmList(List<String> list) {
        return Observable.fromArray(new RealmList<>())
                .flatMap(item -> Observable.fromArray(list))
                .collect(RealmList::new, RealmList::addAll).blockingGet();

    }


    public static RealmList mapToList(List<String> list) {
        return Observable.fromArray(new RealmList<>())
                .flatMap(item -> Observable.fromArray(list))
                .collect(RealmList::new, RealmList::addAll).blockingGet();

    }

    <K extends Entity, V extends DomainObject> List<V> mapToV(EntityMapper<K, V> entityMapper,
                                                              List<K> kList) {
        List<V> vList = new ArrayList<>(kList.size());
        for (K k : kList) {
            vList.add(entityMapper.map(k));
        }
        return vList;
    }

    <K extends Entity, V extends DomainObject> List<K> mapToK(EntityMapper<K, V> entityMapper,
                                                              List<V> vList) {
        List<K> kList = new ArrayList<>(vList.size());
        for (V v : vList) {
            kList.add(entityMapper.map(v));
        }
        return kList;
    }
}
