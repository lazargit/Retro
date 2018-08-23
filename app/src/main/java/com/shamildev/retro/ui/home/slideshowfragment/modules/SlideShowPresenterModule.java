package com.shamildev.retro.ui.home.slideshowfragment.modules;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.home.slideshowfragment.presenter.SlideShowPresenter;
import com.shamildev.retro.ui.home.slideshowfragment.presenter.SlideShowPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        SlideShowModelModule.class
})
public abstract class SlideShowPresenterModule {

    @Binds
    @PerFragment
    abstract SlideShowPresenter slideShowPresenter(SlideShowPresenterImpl slideShowPresenter);
}