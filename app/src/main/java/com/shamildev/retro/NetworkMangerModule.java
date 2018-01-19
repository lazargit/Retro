package com.shamildev.retro;

import android.app.Application;

import com.shamildev.retro.di.scope.ApplicationScope;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.net.NetworkManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shamil Lazar on 18.12.2017.
 */


@Module
public class NetworkMangerModule {

    @Provides
    @Singleton
    NetworkManager providesNetworkManager(Application application) {
        return new NetworkManagerImpl(application);
    }


}
