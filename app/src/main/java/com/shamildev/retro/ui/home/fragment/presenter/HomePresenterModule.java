package com.shamildev.retro.ui.home.fragment.presenter;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.home.fragment.model.HomeModelModule;



import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar
 */


@Module(includes = {
       HomeModelModule.class
})
public abstract class HomePresenterModule {

    @Binds
    @PerFragment
    abstract HomePresenter watchListPresenter(HomePresenterImpl homePresenterImpl);
}