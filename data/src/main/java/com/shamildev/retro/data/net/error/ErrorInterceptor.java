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



import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;



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



            throw TMDBError.getTMDBError(response.code(),response.body().string());

        }


        return response;
    }
}
