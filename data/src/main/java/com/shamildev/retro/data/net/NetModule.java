package com.shamildev.retro.data.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.shamildev.retro.data.entity.AutoValueGsonTypeAdapterFactory;
import com.shamildev.retro.data.net.error.ErrorInterceptor;


import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 15.12.2017.
 */

@Module
public class NetModule {







    @Provides
    public RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }


    @Singleton
    @Provides
    public TMDBServices movieDbService(@Named("movieDbClient") Retrofit retrofit) {
        return retrofit.create(TMDBServices.class);
    }



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







    @Provides
    @Singleton
    @Named("movieDbClient")
    public Retrofit retrofit(OkHttpClient okHttpClient,RxJava2CallAdapterFactory callAdapterFactory) {

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
                new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonTypeAdapterFactory.create() )
                        .create());


        return new Retrofit.Builder()

                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .baseUrl(UrlManager.BASE_URL_MDB)
                .build();


    }



}
