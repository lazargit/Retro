package com.shamildev.retro.ui.home.fragment.watchlist;

import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module
public abstract class WatchListPresenterModule {

    @Binds
    @PerFragment
    abstract WatchListPresenter watchListPresenter(WatchListPresenterImpl watchListPresenter);
}