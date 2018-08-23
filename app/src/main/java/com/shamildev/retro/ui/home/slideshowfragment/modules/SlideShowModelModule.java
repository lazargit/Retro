package com.shamildev.retro.ui.home.slideshowfragment.modules;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.home.slideshowfragment.model.SlideShowModel;
import com.shamildev.retro.ui.home.slideshowfragment.model.SlideShowModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */


@Module
public abstract class SlideShowModelModule {

    @Binds
    @PerFragment
    abstract SlideShowModel model(SlideShowModelImpl model);


}