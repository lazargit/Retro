package com.shamildev.retro;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.shamildev.retro.android.executor.ExecutorModule;
import com.shamildev.retro.android.executor.IOExecutionThread;
import com.shamildev.retro.android.executor.MainPostExecutionThread;
import com.shamildev.retro.config.ConfigModule;

import com.shamildev.retro.di.scope.ApplicationScope;
import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.domain.DomainModule;
import com.shamildev.retro.domain.executor.ExecutionThread;

import com.shamildev.retro.domain.executor.PostExecutionThread;
import com.shamildev.retro.net.NetworkManagerImpl;
import com.shamildev.retro.ui.splash.SplashActivity;
import com.shamildev.retro.ui.splash.SplashActivityModule;
import com.shamildev.retro.data.DataModule;
import com.shamildev.retro.ui.watchlist.WatchListActivity;
import com.shamildev.retro.ui.watchlist.WatchListActivityModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;


/**
 * Created by Shamil Lazar on 13.12.2017.
 */

@Module(

        includes = {AndroidInjectionModule.class,
                ConfigModule.class,
                GlideModule.class,
                NetworkMangerModule.class,
                DataModule.class,
                ExecutorModule.class,
                DomainModule.class}

       )
public abstract class AppModule {



    @Binds
    @Singleton
    abstract Application application(App app);
//
//    @Binds
//    @ApplicationScope
//    abstract NetworkManagerImpl networkManager(Application app);


    @Binds
    @Singleton
    abstract ExecutionThread providesThreadScheduler(IOExecutionThread schedulers);


    @Binds
    @Singleton
    abstract PostExecutionThread providesPostExecutionScheduler(MainPostExecutionThread mainPostExecutionThread);









    /**
     * Provides the injector for the {@link SplashActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity splashActivityInjector();

    /**
     * Provides the injector for the {@link WatchListActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = WatchListActivityModule.class)
    abstract WatchListActivity watchListActivityInjector();


}
