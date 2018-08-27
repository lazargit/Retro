

package com.shamildev.retro.data.net;



import com.shamildev.retro.domain.config.DataConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * An interceptor that uses stale responses if offline.
 */
public final class OfflineCacheInterceptor implements Interceptor {

    private final DataConfig config;
    private final NetworkManager networkManager;

    @Inject
    OfflineCacheInterceptor(DataConfig config, NetworkManager networkManager) {
        this.config = config;
        this.networkManager = networkManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!networkManager.isNetworkAvailable()) {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxStale(config.offlineCacheTimeDays(), TimeUnit.DAYS)
                    .build();
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }

        return chain.proceed(request);
    }
}
