package com.shamildev.retro.ui.home.fragment.gridlist;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.home.fragment.model.HomeModel;
import com.shamildev.retro.ui.home.fragment.model.HomeModelImpl;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */


@Module
public abstract class GridListModelModule {

    @Binds
    @PerFragment
    abstract GridListModel model(GridListModelImpl model);


}