package com.shamildev.retro.ui.home.fragment.gridlist;

import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.
 */


@Module(includes = {
        GridListModelModule.class
})
public abstract class GridListPresenterModule {

    @Binds
    @PerFragment
    abstract GridListPresenter gridListPresenter(GridListPresenterImpl gridListPresenter);
}