package com.shamildev.retro.ui.home.fragment.presenter;

import com.shamildev.retro.di.scope.PerChildFragment;
import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module
public abstract class HomePagePresenterModule {

    @Binds
    @PerChildFragment
    abstract HomePagePresenter homePagePresenter(HomePagePresenterImpl homePresenterImpl);
}