package com.shamildev.retro.ui.details.fragment.view;







import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.details.fragment.presenter.DetailsPresenterModule;

import com.shamildev.retro.ui.watchlist.fragment.view.WatchListFragment;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.DaggerFragment;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        DetailsPresenterModule.class
})
public abstract class DetailsFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}
     * and named {@link BaseFragmentModule#FRAGMENT}.
     *
     * @param fragment the SplashFragment
     * @return the fragment
     */
    @Binds
    @Named(BaseFragmentModule.FRAGMENT)
    @PerFragment
    abstract Fragment fragment(DetailsFragment fragment);

    @Binds
    @PerFragment
    abstract DetailsView detailsView(DetailsFragment fragment);
}