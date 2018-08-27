package com.shamildev.retro.data.cache.realm.mapper;




import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.DomainObjectStorable;

import io.realm.RealmObject;


public interface RealmMapper<K extends DomainObject, V extends RealmObject> {

    V map(K k);

    K map(V v);
}
