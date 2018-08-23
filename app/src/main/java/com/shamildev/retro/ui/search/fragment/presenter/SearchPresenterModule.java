package com.shamildev.retro.ui.search.fragment.presenter;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.search.fragment.model.SearchModelModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.
 */


@Module(includes = {
        SearchModelModule.class
})
public abstract class SearchPresenterModule {

    @Binds
    @PerFragment
    abstract SearchPresenter watchListPresenter(SearchPresenterImpl watchListPresenter);
}