package com.shamildev.retro.ui.home.fragment.presenter;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.home.fragment.model.ImageViewPagerModelModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.
 */


@Module(includes = {
        ImageViewPagerModelModule.class
})
public abstract class ImageViewPagerPresenterModule {

    @Binds
    @PerFragment
    abstract ImageViewPagerPresenter imageViewPagerPresenter(ImageViewPagerPresenterImpl imageViewPagerPresenter);
}