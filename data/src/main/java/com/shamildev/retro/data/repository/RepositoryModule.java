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

package com.shamildev.retro.data.repository;

import com.google.gson.Gson;
import com.shamildev.retro.data.net.NetModule;
import com.shamildev.retro.data.net.TMDBServices;
import com.shamildev.retro.data.net.UrlManager;
import com.shamildev.retro.data.scope.ApplicationScope;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.squareup.moshi.Moshi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides repository dependencies.
 */
@Module( includes = {
        NetModule.class
})
public class RepositoryModule {

//    @Binds
//    @Reusable
//    abstract RemoteRepository businessRepository(TMDBRepository repository);

    @Provides
    @Singleton
    public TMDBServices movieDbService(@Named("movieDbClient") Retrofit retrofit) {
        return retrofit.create(TMDBServices.class);
    }




    @Provides
    public RemoteRepository movieDBRepoService(TMDBServices tmdbServices) {
        return new TMDBRepository(tmdbServices);
    }



    @Provides
    @Singleton
    @Named("movieDbClient")
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson, RxJava2CallAdapterFactory callAdapterFactory) {
        return new Retrofit.Builder()

                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(callAdapterFactory)
                .client(okHttpClient)
                .baseUrl(UrlManager.BASE_URL_MDB)
                .build();


    }

}
