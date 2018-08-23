package com.shamildev.retro.ui.mylist.fragment.presenter;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.mylist.fragment.model.MyListModelModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.
 */


@Module(includes = {
        MyListModelModule.class
})
public abstract class MyListPresenterModule {

    @Binds
    @PerFragment
    abstract MyListPresenter watchListPresenter(MyListPresenterImpl watchListPresenter);
}