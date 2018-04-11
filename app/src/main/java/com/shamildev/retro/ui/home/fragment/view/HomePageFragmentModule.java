package com.shamildev.retro.ui.home.fragment.view;



import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerChildFragment;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseChildFragmentModule;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.home.fragment.presenter.HomePagePresenterModule;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenterModule;
import com.shamildev.retro.ui.home.slideshowfragment.modules.SlideShowDialogFragmentModule;
import com.shamildev.retro.ui.home.slideshowfragment.view.SlideShowDialogFragment;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * Created by Shamil Lazar on 13.12.2017.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseChildFragmentModule.class,
        HomePagePresenterModule.class
})
public abstract class HomePageFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}
     * and named {@link BaseFragmentModule#FRAGMENT}.
     *
     * @param fragment the SplashFragment
     * @return the fragment
     */








    @Binds
    @Named(BaseChildFragmentModule.CHILD_FRAGMENT)
    @PerChildFragment
    abstract Fragment fragment(HomePageFragment fragment);

    @Binds
    @PerChildFragment
    abstract HomePageView homePageView(HomePageFragment fragment);




}