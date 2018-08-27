package com.shamildev.retro.data.cache.realm.mapper;



import com.shamildev.retro.data.entity.Entity;
import com.shamildev.retro.data.entity.mapper.EntityMapper;
import com.shamildev.retro.data.entity.mapper.error.MappingError;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
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


    public static <V extends Number> List<V> mapToList(RealmList<V> list) {
        
        List<V> vList = new ArrayList<>();
        for (V id : list) {
            vList.add(id);
        }
        return vList;
        
    }

    public static <V extends String> List<V> mapToStringList(RealmList<V> list) {
        
        List<V> vList = new ArrayList<>();
        for (V id : list) {
            vList.add(id);
        }
        return vList;
   }

    <K extends Entity, V extends DomainObject> List<V> mapToV(EntityMapper<K, V> entityMapper,
                                                              List<K> kList) throws MappingError {
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
