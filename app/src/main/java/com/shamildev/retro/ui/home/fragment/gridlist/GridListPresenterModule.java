package com.shamildev.retro.ui.home.fragment.gridlist;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.home.fragment.gridlist.GridListPresenter;


import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module
public abstract class GridListPresenterModule {

    @Binds
    @PerFragment
    abstract GridListPresenter gridListPresenter(GridListPresenterImpl gridListPresenter);
}