package com.shamildev.retro.ui.splash.fragment.modul;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.splash.fragment.modul.SplashModelModule;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        SplashModelModule.class
})
public abstract class SplashPresenterModule {

    @Binds
    @PerFragment
    abstract SplashPresenter presenter(SplashPresenterImpl splashPresenterImpl);


}