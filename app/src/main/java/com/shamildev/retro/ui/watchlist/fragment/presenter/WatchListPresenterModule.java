package com.shamildev.retro.ui.watchlist.fragment.presenter;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.watchlist.fragment.model.WatchListModelModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        WatchListModelModule.class
})
public abstract class WatchListPresenterModule {

    @Binds
    @PerFragment
    abstract WatchListPresenter watchListPresenter(WatchListPresenterImpl watchListPresenterImpl);
}