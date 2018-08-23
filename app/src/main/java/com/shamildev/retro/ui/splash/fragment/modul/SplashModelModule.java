package com.shamildev.retro.ui.splash.fragment.modul;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.splash.fragment.model.SplashModel;
import com.shamildev.retro.ui.splash.fragment.model.SplashModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

 @Module
 public abstract class SplashModelModule {

        @Binds
        @PerFragment
        abstract SplashModel model(SplashModelImpl model);


 }