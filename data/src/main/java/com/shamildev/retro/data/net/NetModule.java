package com.shamildev.retro.data.net;

import com.google.gson.Gson;
import com.shamildev.retro.data.net.error.ErrorInterceptor;
import com.shamildev.retro.data.scope.ApplicationScope;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 15.12.2017.
 */

@Module
public class NetModule {






    @Provides
    @Singleton
    public Gson gson() {

        return new Gson();
    }
    @Provides
    public RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {


         return  new OkHttpClient.Builder()
                        .addInterceptor(
                                new ErrorInterceptor()
                        )
                        .addNetworkInterceptor(httpLoggingInterceptor)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();




    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);

            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }




}
