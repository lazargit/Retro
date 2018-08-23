package com.shamildev.retro.ui.gallery.gridlist;


import com.shamildev.retro.di.scope.PerFragment;

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