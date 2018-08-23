package com.shamildev.retro.ui.home.fragment.watchlist;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.home.fragment.model.HomePageModel;
import com.shamildev.retro.ui.home.fragment.model.HomePageModelImpl;

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