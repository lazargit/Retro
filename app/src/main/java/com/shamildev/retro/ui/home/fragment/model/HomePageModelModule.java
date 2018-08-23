package com.shamildev.retro.ui.home.fragment.model;


import com.shamildev.retro.di.scope.PerChildFragment;
import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */


@Module
public abstract class HomePageModelModule {

    @Binds
    @PerChildFragment
    abstract HomePageModel model(HomePageModelImpl model);


}