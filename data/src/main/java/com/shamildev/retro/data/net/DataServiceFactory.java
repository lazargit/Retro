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

package com.shamildev.retro.data.net;



import android.util.Log;

import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.data.net.auth.AuthRequestInterceptor;
import com.shamildev.retro.data.net.error.ErrorInterceptor;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Lazy;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;


/**
 * Creates concrete data services.
 */

public final class DataServiceFactory {

    private static final String HTTP_CACHE_PATH = "http-cache";

    private final Boolean isDebug;
    private final DataConfig config;
    private final Lazy<Cache> cache; // Lazy to only create the cache when needed
    private final OfflineCacheInterceptor offlineCacheInterceptor;
    private final NetworkCacheInterceptor networkCacheInterceptor;
    private final ErrorInterceptor errorInterceptor;
    private final MoshiConverterFactory moshiConverterFactory;
    private final RxJava2CallAdapterFactory rxJava2CallAdapterFactory;


    @Inject
    DataServiceFactory(
                       @Named("isDebug") Boolean isDebug,
                       DataConfig config,
                       @Named(HTTP_CACHE_PATH)  Lazy<Cache> cache,
                       OfflineCacheInterceptor offlineCacheInterceptor,
                       NetworkCacheInterceptor networkCacheInterceptor,
                       ErrorInterceptor errorInterceptor,
                       MoshiConverterFactory moshiConverterFactory,
                       RxJava2CallAdapterFactory rxJava2CallAdapterFactory ) {

        this.isDebug = isDebug;
        this.config = config;
        this.cache = cache;
        this.offlineCacheInterceptor = offlineCacheInterceptor;
        this.networkCacheInterceptor = networkCacheInterceptor;
        this.errorInterceptor = errorInterceptor;
        this.moshiConverterFactory = moshiConverterFactory;
        this.rxJava2CallAdapterFactory = rxJava2CallAdapterFactory;
    }

    public <T> T create(Class<T> serviceClass) {


        T t = retrofit(okHttpClientBuilder()).create(serviceClass);
        Log.d("retrofit","retrofit"+t);
        return t;
    }



    <T> T createWithAuth(Class<T> serviceClass,
                         AuthRequestInterceptor authRequestInterceptor) {
        // AuthRequestInterceptor has to be passed in as a parameter to avoid dependency cycle
        OkHttpClient.Builder okHttpClientBuilder = okHttpClientBuilder();
        okHttpClientBuilder.addInterceptor(authRequestInterceptor);
        return retrofit(okHttpClientBuilder).create(serviceClass);
    }

    private OkHttpClient.Builder okHttpClientBuilder() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if(isDebug){
            builder.addInterceptor(httpLoggingInterceptor());

        }

        builder.addInterceptor(errorInterceptor);
        builder.cache(cache.get())
                .addInterceptor(offlineCacheInterceptor)
                .addNetworkInterceptor(networkCacheInterceptor);

        return builder;
//        return new OkHttpClient.Builder()
//                .cache(cache.get())
//                .addInterceptor(offlineCacheInterceptor)
//                .addNetworkInterceptor(networkCacheInterceptor)
//                .addInterceptor( new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                    @Override
//                    public void log(String message) {
//                        Timber.i(message);
//
//                    }
//                }).setLevel(HttpLoggingInterceptor.Level.BASIC));
    }

    public Retrofit retrofit(OkHttpClient.Builder okHttpClientBuilder) {

        return new Retrofit.Builder()

                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClientBuilder.build())
                .baseUrl(config.baseUrl())
                .build();

    }




    private HttpLoggingInterceptor httpLoggingInterceptor(){
       return new HttpLoggingInterceptor(message -> Timber.i(message))
               .setLevel(HttpLoggingInterceptor.Level.BASIC);

    }
}
