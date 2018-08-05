package com.shamildev.retro.ui.gallery.gridlist;

import com.shamildev.retro.di.scope.PerFragment;

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