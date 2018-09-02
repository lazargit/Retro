package com.shamildev.retro.data;


import android.app.Application;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.shamildev.retro.data.cache.CacheModule;
import com.shamildev.retro.data.local.LocalModule;
import com.shamildev.retro.data.repository.RepositoryModule;
import com.shamildev.retro.domain.config.DataConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides data dependencies.
 */
@Module(includes = {
        RepositoryModule.class,
        CacheModule.class,
        LocalModule.class


})
public class DataModule {





}
