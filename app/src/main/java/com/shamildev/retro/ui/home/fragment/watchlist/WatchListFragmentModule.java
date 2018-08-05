package com.shamildev.retro.ui.home.fragment.watchlist;



import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar on 13.12.2017.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        WatchListPresenterModule.class
})
public abstract class WatchListFragmentModule {

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
//
//    @PerChildFragment
//    @ContributesAndroidInjector(modules = HomePageFragmentModule.class)
//    abstract HomePageFragment homePageFragmentInjector();



    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(WatchListFragment fragment);



    @Binds
    @PerFragment
    abstract WatchListView watchListView(WatchListFragment fragment);
}