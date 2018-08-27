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
