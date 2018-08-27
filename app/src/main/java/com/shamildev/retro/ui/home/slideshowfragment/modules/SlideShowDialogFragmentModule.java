package com.shamildev.retro.ui.home.slideshowfragment.modules;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerChildFragment;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseChildFragmentModule;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.home.slideshowfragment.view.SlideShowDialogFragment;
import com.shamildev.retro.ui.home.slideshowfragment.view.SlideShowView;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        SlideShowPresenterModule.class
})
public abstract class SlideShowDialogFragmentModule {


//
//    @PerChildFragment
//    @ContributesAndroidInjector(modules = HomePageFragmentModule.class)
//    abstract HomePageFragment example3ChildFragmentInjector();
//
    @Binds
    @Named(BaseFragmentModule.DIALOGFRAGMENT)
    @PerFragment
    abstract DialogFragment dialogFragment(SlideShowDialogFragment fragment);


    @Binds
    @Named(BaseChildFragmentModule.CHILD_FRAGMENT)
    @PerChildFragment
    abstract Fragment fragment(SlideShowDialogFragment fragment);


    @Binds
    @PerFragment
    abstract SlideShowView slideShowView(SlideShowDialogFragment fragment);
}