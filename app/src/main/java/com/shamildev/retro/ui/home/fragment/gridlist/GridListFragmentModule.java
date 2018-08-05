package com.shamildev.retro.ui.home.fragment.gridlist;



import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerChildFragment;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenterModule;
import com.shamildev.retro.ui.home.fragment.view.HomeFragment;
import com.shamildev.retro.ui.home.fragment.view.HomePageFragment;
import com.shamildev.retro.ui.home.fragment.view.HomePageFragmentModule;
import com.shamildev.retro.ui.home.fragment.view.HomeView;
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
        BaseFragmentModule.class,
        GridListPresenterModule.class
})
public abstract class GridListFragmentModule {

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
    abstract Fragment fragment(GridListFragment fragment);



    @Binds
    @PerFragment
    abstract GridListView homeView(GridListFragment fragment);
}