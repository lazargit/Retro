package com.shamildev.retro.ui.watchlist.fragment.model;


import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */


@Module
public abstract class WatchListModelModule {

    @Binds
    @PerFragment
    abstract WatchListModel model(WatchListModelImpl model);


}