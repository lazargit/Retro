package com.shamildev.retro.data.net;

import com.shamildev.retro.data.net.auth.AuthRequestInterceptor;
import com.shamildev.retro.data.scope.ApplicationScope;


import java.io.File;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Shamil Lazar on 15.12.2017.
 */

@Module
public class NetModule {

    private static final String HTTP_CACHE_PATH = "http-cache";
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String PRAGMA = "Pragma";

    @Provides
    @Named(HTTP_CACHE_PATH)
    public Cache provideCache(@Named("cacheDir") File cacheDir, @Named("cacheSize") long cacheSize) {
        Cache cache = null;

        try {
            cache = new Cache(new File(cacheDir.getPath(), HTTP_CACHE_PATH), cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cache;
    }

    @Provides
    @Named("TMDBServicesWithAuth")
    public TMDBServices tmdbServiceWithAuth(DataServiceFactory dataServiceFactory,
                                                   AuthRequestInterceptor authRequestInterceptor) {

        return dataServiceFactory.createWithAuth(TMDBServices.class, authRequestInterceptor);
    }

    @Provides
    @Named("TMDBServices")
    public TMDBServices tmdbService(DataServiceFactory dataServiceFactory) {
        return dataServiceFactory.create(TMDBServices.class);
    }




    @Provides
    public MoshiConverterFactory provideMoshiConverterFactory() {
        return MoshiConverterFactory.create();
    }


    @Provides
    public RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }






}
