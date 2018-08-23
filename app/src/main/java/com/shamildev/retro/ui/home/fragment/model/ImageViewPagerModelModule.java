package com.shamildev.retro.ui.home.fragment.model;


import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */


@Module
public abstract class ImageViewPagerModelModule {

    @Binds
    @PerFragment
    abstract ImageViewPagerModel model(ImageViewPagerModelImpl model);


}