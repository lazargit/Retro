package com.shamildev.retro.ui.splash.fragment.modul;

import android.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.splash.fragment.view.SplashFragment;
import com.shamildev.retro.ui.splash.fragment.view.SplashView;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SplashPresenterModule.class
})
public abstract class SplashFragmentModule {


    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    abstract Fragment fragment(SplashFragment fragment);

    @Binds
    @PerFragment
    abstract SplashView splashView(SplashFragment fragment);
}