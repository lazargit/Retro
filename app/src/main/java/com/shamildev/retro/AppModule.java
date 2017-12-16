package com.shamildev.retro;

import android.app.Application;

import com.shamildev.retro.App;
import com.shamildev.retro.data.repository.RepositoryModule;
import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.ui.splash.SplashActivity;
import com.shamildev.retro.ui.splash.SplashActivityModule;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenterModule;
import com.shamildev.retro.data.DataModule;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;


/**
 * Created by Shamil Lazar on 13.12.2017.
 */

@Module(

        includes = {AndroidInjectionModule.class,RepositoryModule.class}

       )
public abstract class AppModule {



    @Binds
    @Singleton
    abstract Application application(App app);

    /**
     * Provides the injector for the {@link SplashActivity}, which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity mainActivityInjector();


}
