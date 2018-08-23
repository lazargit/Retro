package com.shamildev.retro.ui.home.fragment.model;


import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */


@Module
public abstract class HomeModelModule {

    @Binds
    @PerFragment
    abstract HomeModel model(HomeModelImpl model);


}