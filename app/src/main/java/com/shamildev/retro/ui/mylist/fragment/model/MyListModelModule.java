package com.shamildev.retro.ui.mylist.fragment.model;
import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

@Module
public abstract class MyListModelModule {

    @Binds
    @PerFragment
    abstract MyListModel model(MyListModelImpl model);


}