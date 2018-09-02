package com.shamildev.retro.config;

import android.app.Application;
import android.util.Log;


import com.shamildev.retro.BuildConfig;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.util.Pair;
import com.shamildev.retro.util.Constants;
import com.shamildev.retro.util.DeviceUtils;

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
    @Singleton
    static DataConfig dataConfig(Application application) {

//                Locale.getDefault().getLanguage()       ---> en
//        Locale.getDefault().getISO3Language()   ---> eng
//        Locale.getDefault().getCountry()        ---> US
//        Locale.getDefault().getISO3Country()    ---> USA
//        Locale.getDefault().getDisplayCountry() ---> United States
//        Locale.getDefault().getDisplayName()    ---> English (United States)
//        Locale.getDefault().toString()          ---> en_US
//        Locale.getDefault().getDisplayLanguage()---> English
        Log.d("COUNTRY-CODE", " "+ Locale.getDefault().toString());


        return  BASE_CONFIG_BUILDER
                .debug(BuildConfig.DEBUG)
                .authClientSecret(BuildConfig.MOVIE_DB_API_TOKEN)
                .youtubeKey(BuildConfig.YOUTUBE_API_TOKEN)
                .facebookApiToken(BuildConfig.FACEBOOK_API_TOKEN)
                .language( Locale.getDefault().toString().replace('_','-'))
                .country(Locale.getDefault().getCountry())
                .maxCacheTime(1440)
                .build();
    }



    @Provides
    @Singleton
    static AppConfig appConfig(Application application,DataConfig dataConfig) {

        Pair<Integer, Integer> screenSizes = new Pair<>(DeviceUtils.getScreenWidth(application), DeviceUtils.getScreenHeight(application));
        boolean firstLaunch = DeviceUtils.isFirstLaunch(application);
        Log.e("TAG","appConfig isFirstLaunch"+firstLaunch);
        return new AppConfig(screenSizes,firstLaunch);

    }

    @Provides
    @Singleton
    static AppUser appUser(DataConfig dataConfig) {


        Log.e("TAG","appUser"+dataConfig.language());
        return new AppUser(User.anonymus,dataConfig.language());

    }





    @Provides
    @Named("isDebug")
    boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Named("isFirstStart")
    boolean provideIsFirstStart(Application context) {

        return DeviceUtils.isFirstLaunch(context);
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
