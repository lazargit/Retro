package com.shamildev.retro.data.net;


import com.shamildev.retro.domain.config.DataConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * A network interceptor that re-writes response headers to force use of cache.
 */
public final class NetworkCacheInterceptor implements Interceptor {

    private final DataConfig config;

    @Inject
    NetworkCacheInterceptor(DataConfig config) {
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(config.networkCacheTimeSeconds(), TimeUnit.SECONDS)
                .build();

        return response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build();
    }
}
