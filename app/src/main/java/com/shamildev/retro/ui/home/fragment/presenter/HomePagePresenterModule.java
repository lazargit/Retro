package com.shamildev.retro.ui.home.fragment.presenter;

import com.shamildev.retro.di.scope.PerChildFragment;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.home.fragment.model.HomePageModelModule;
import com.shamildev.retro.ui.watchlist.fragment.model.WatchListModelModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        HomePageModelModule.class
})
public abstract class HomePagePresenterModule {

    @Binds
    @PerChildFragment
    abstract HomePagePresenter homePagePresenter(HomePagePresenterImpl homePresenterImpl);
}