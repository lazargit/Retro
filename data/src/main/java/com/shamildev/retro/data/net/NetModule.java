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


//    @ApplicationScope
//    @Provides
//    public TMDBServices movieDbService(@Named("movieDbClient") Retrofit retrofit) {
//        return retrofit.create(TMDBServices.class);
//    }
//
//
//
//    @Provides
//    public OkHttpClient provideOkHttpClient(
//                                            @Named("isDebug") Boolean isDebug,
//                                            DataConfig dataConfig,
//                                            @Named(HTTP_CACHE_PATH) Cache cache,
//                                            HttpLoggingInterceptor httpLoggingInterceptor) {
//
//        Log.d("provideOkHttpClient",">>"+dataConfig.authClientSecret());
//
//        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
//                .addNetworkInterceptor(
//                        new ErrorInterceptor()
//                )
//
//                .cache(cache)
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true);
//
//                if(isDebug) {
//                    okHttpClient.addInterceptor( new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                        @Override
//                        public void log(String message) {
//                            Timber.i(message);
//
//                        }
//                    }).setLevel(HttpLoggingInterceptor.Level.BASIC));
//                }
//
//
//        return okHttpClient.build();
//
////
////        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
////                .addNetworkInterceptor(cacheInterceptor)
////                .addInterceptor(offlineCacheInterceptor)
////                .addInterceptor(retryInterceptor)
////                .cache(cache)
////                .connectTimeout(networkTimeoutInSeconds, TimeUnit.SECONDS);
//
//
//    }




//    @Provides
//    @Named("errorInterceptor")
//    public ResponseEntity intercept(Interceptor.Chain chain) throws IOException {
//        ResponseEntity response = chain.proceed(chain.request());
//        if (response.isSuccessful()) {
//            Log.d("ErrorInterceptor",">>>>"+response.body().contentLength());
//
//        }
//
//
//        if (!response.isSuccessful()) {
//            throw new TMDBError(
//                    response.code(),
//                    response.message()
//            );
//        }
//        return response;
//    }


//    @ApplicationScope
//    @Provides
//    @Named("cacheInterceptor")
//    public Interceptor provideCacheInterceptor(NetworkManager networkManager,@Named("cacheMaxAge") int maxAgeMin) {
//        Log.d("NetworkModule", " cacheInterceptor");
//        return chain -> {
//            ResponseEntity response = chain.proceed(chain.request());
//            Log.d("NetworkModule", "provideCacheInterceptor::cacheInterceptor");
//            CacheControl cacheControl = new CacheControl.Builder()
//                    .maxAge(maxAgeMin, TimeUnit.MINUTES)
//                    .build();
//
//            return response.newBuilder()
//                    .removeHeader(PRAGMA)
//                    .removeHeader(CACHE_CONTROL)
//                    .header(CACHE_CONTROL, cacheControl.toString())
//                    .build();
//
//        };
//    }




//    @Provides
//    @ApplicationScope
//    @Named("movieDbClient")
//    public Retrofit retrofit(OkHttpClient okHttpClient,RxJava2CallAdapterFactory callAdapterFactory) {
//
//        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(
//                new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonTypeAdapterFactory.create() )
//                        .create());
//
//
//        return new Retrofit.Builder()
//
//                //.addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(MoshiConverterFactory.create())
//                .addCallAdapterFactory(callAdapterFactory)
//                .client(okHttpClient)
//                .baseUrl(UrlManager.BASE_URL_MDB)
//                .build();
//
//
//
//
//
//    }



}
