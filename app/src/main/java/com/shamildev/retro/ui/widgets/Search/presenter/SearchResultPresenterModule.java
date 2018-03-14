package com.shamildev.retro.ui.widgets.Search.presenter;

import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module
public abstract class SearchResultPresenterModule {

    @Binds
    @PerFragment
    abstract SearchResultPresenter searchResultPresenter(SearchResultPresenterImpl searchResultPresenter);
}