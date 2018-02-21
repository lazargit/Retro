package com.shamildev.retro.ui.home.fragment.view;



import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenterModule;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenterModule;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListFragment;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;



/**
 * Created by Shamil Lazar on 13.12.2017.

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
     * @param fragment the SplashFragment
     * @return the fragment
     */
    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(HomeFragment fragment);

    @Binds
    @PerFragment
    abstract HomeView watchListView(HomeFragment fragment);
}