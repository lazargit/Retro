/*
 * *
 *  * Copyright (C) 2017 Ryan Kay Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.shamildev.retro.data.net.error;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shamildev.retro.data.entity.ErrorEntity;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 *
 * Interceptors allow us to grab Requests and Responses between Layers of Implementation. In simpler
 * terms:
 * - Checks Requests prior to being sent over the Network
 * - Checks Responses prior to when our GitHubRestAdapter calls back to the Repository.
 *
 * Among other useful things (see OkHttp Docs for more info), we can perform some global error
 * handling operations. Errors will still be propogated to onErrorReturn() in the Repository.
 * Created by R_KAY on 10/29/2017.
 */

public class ErrorInterceptor implements Interceptor {

    private static final int ERROR_CODE_401 = 401;
    private static final int ERROR_CODE_404 = 404;
    private static final int ERROR_CODE_422 = 422;



    @Inject
    public ErrorInterceptor() {


    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());


        if (!response.isSuccessful()) {

            String rawJson = response.body().string();

            throw TMDBError.getTMDBError(response.code(),rawJson);

        }


        return response;
    }
}
