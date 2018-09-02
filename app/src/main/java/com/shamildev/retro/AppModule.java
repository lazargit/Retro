package com.shamildev.retro;

import android.app.Application;

import com.bumptech.glide.module.AppGlideModule;
import com.shamildev.retro.android.executor.ExecutorModule;
import com.shamildev.retro.android.executor.IOExecutionThread;
import com.shamildev.retro.android.executor.MainPostExecutionThread;
import com.shamildev.retro.config.ConfigModule;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.domain.DomainModule;
import com.shamildev.retro.domain.executor.ExecutionThread;

import com.shamildev.retro.domain.executor.PostExecutionThread;
import com.shamildev.retro.retroimage.core.ProcessImageModule;
import com.shamildev.retro.retrovideo.core.VideoPlayerModule;
import com.shamildev.retro.ui.account.AccountActivity;
import com.shamildev.retro.ui.account.AccountActivityModule;
import com.shamildev.retro.ui.details.DetailsActivity;
import com.shamildev.retro.ui.details.DetailsActivityModule;
import com.shamildev.retro.ui.home.HomeActivity;
import com.shamildev.retro.ui.home.HomeActivityModule;
import com.shamildev.retro.ui.mylist.MyListActivity;
import com.shamildev.retro.ui.mylist.MyListActivityModule;
import com.shamildev.retro.ui.search.SearchActivity;
import com.shamildev.retro.ui.search.SearchActivityModule;
import com.shamildev.retro.ui.splash.SplashActivity;
import com.shamildev.retro.ui.splash.SplashActivityModule;
import com.shamildev.retro.data.DataModule;
import com.shamildev.retro.ui.watchlist.WatchListActivity;
import com.shamildev.retro.ui.watchlist.WatchListActivityModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


/**
 * Created by Shamil Lazar on 13.12.2017.
 */

@Module(

        includes = {
                AndroidSupportInjectionModule.class,
                ConfigModule.class,
                GlideModule.class,
                ProcessImageModule.class,
                VideoPlayerModule.class,
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
     * Provides the injector for the {@link AccountActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = AccountActivityModule.class)
    abstract AccountActivity accounthActivityInjector();


    /**
     * Provides the injector for the {@link MyListActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = MyListActivityModule.class)
    abstract MyListActivity mylistActivityInjector();


    /**
     * Provides the injector for the {@link MyListActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    abstract SearchActivity searchActivityInjector();

    /**
     * Provides the injector for the {@link WatchListActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = WatchListActivityModule.class)
    abstract WatchListActivity watchListActivityInjector();


    /**
     * Provides the injector for the {@link DetailsActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = DetailsActivityModule.class)
    abstract DetailsActivity detailsActivityInjector();

    /**
     * Provides the injector for the {@link HomeActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity homeActivityInjector();

}
