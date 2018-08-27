package com.shamildev.retro.data;


import com.shamildev.retro.data.cache.CacheModule;
import com.shamildev.retro.data.local.LocalModule;
import com.shamildev.retro.data.repository.RepositoryModule;

import dagger.Module;

/**
 * Provides data dependencies.
 */
@Module(includes = {
        RepositoryModule.class,
        CacheModule.class,
        LocalModule.class


})
public abstract class DataModule {




}
