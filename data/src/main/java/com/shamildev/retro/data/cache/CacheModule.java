package com.shamildev.retro.data.cache;




import com.shamildev.retro.data.cache.realm.RealmModule;
import com.shamildev.retro.data.cache.realm.mapper.RealmMapperModule;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;



@Module( includes = {
        RealmModule.class,
        RealmMapperModule.class
})
public abstract class CacheModule {

    @Binds
    @Reusable
    abstract CacheRepository cacheRepository(RealmCacheRepository repository);




}

