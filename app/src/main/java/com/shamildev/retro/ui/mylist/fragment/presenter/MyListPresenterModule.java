package com.shamildev.retro.ui.mylist.fragment.presenter;

import com.shamildev.retro.di.scope.PerFragment;

import com.shamildev.retro.ui.mylist.fragment.presenter.MyListPresenter;
import com.shamildev.retro.ui.mylist.fragment.presenter.MyListPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module
public abstract class MyListPresenterModule {

    @Binds
    @PerFragment
    abstract MyListPresenter watchListPresenter(MyListPresenterImpl watchListPresenter);
}