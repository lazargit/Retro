package com.shamildev.retro.config;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.shamildev.retro.BuildConfig;
import com.shamildev.retro.data.config.DataConfig;
import com.shamildev.retro.di.scope.ApplicationScope;
import com.shamildev.retro.util.Constants;

import java.io.File;
import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shamil Lazar on 19.12.2017.
 */


@Module
public class ConfigModule extends BaseConfigModule {



    @Provides
    static DataConfig dataConfig(Application application) {

        //        Locale.getDefault().getLanguage()       ---> en
//        Locale.getDefault().getISO3Language()   ---> eng
//        Locale.getDefault().getCountry()        ---> US
//        Locale.getDefault().getISO3Country()    ---> USA
//        Locale.getDefault().getDisplayCountry() ---> United States
//        Locale.getDefault().getDisplayName()    ---> English (United States)
//        Locale.getDefault().toString()          ---> en_US
//        Locale.getDefault().getDisplayLanguage()---> English
      //  Log.d("COUNTRY-CODE", " "+ Locale.getDefault().toString());


        return BASE_CONFIG_BUILDER
                .debug(BuildConfig.DEBUG)
                .authClientSecret(BuildConfig.MOVIE_DB_API_TOKEN)
                .cacheRootDir(application.getExternalCacheDir().getPath())
                .language(Locale.getDefault().toString())
                .build();
    }


    @Provides
    @Named("isDebug")
    boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides

    @Named("cacheSize")
    long provideCacheSize() {
        return Constants.CACHE_SIZE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxAge")
    int provideCacheMaxAgeMinutes() {
        return Constants.CACHE_MAX_AGE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxStale")
    int provideCacheMaxStaleDays() {
        return Constants.CACHE_MAX_STALE;
    }

    @Provides
    @Singleton
    @Named("retryCount")
    public int provideApiRetryCount() {
        return Constants.API_RETRY_COUNT;
    }

    @Provides
    @Named("cacheDir")
    File provideCacheDir(Application context) {
        return context.getCacheDir();
    }


}
