package com.shamildev.retro;
import com.shamildev.retro.data.DataModule;
import com.shamildev.retro.di.scope.ApplicationScope;


import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * Injects application dependencies.
 */
@Singleton
@Component(modules = AppModule.class)
interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}