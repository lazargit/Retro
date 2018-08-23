package com.shamildev.retro.ui.home.fragment.view;



import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerChildFragment;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenterModule;
import com.shamildev.retro.ui.home.slideshowfragment.modules.SlideShowDialogFragmentModule;
import com.shamildev.retro.ui.home.slideshowfragment.view.SlideShowDialogFragment;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenterModule;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListFragment;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * Created by Shamil Lazar.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        HomePresenterModule.class
})
public abstract class HomeFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}
     * and named {@link BaseFragmentModule#FRAGMENT}.
     *
     *
     * @return the fragment
     *
     *
     */

    @PerChildFragment
    @ContributesAndroidInjector(modules = HomePageFragmentModule.class)
    abstract HomePageFragment homePageFragmentInjector();



    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(HomeFragment fragment);


    @Binds
    @Named(BaseFragmentModule.DIALOGFRAGMENT)
    @PerFragment
    abstract DialogFragment slideShowfragment(SlideShowDialogFragment fragment);


    @Binds
    @PerFragment
    abstract HomeView homeView(HomeFragment fragment);
}