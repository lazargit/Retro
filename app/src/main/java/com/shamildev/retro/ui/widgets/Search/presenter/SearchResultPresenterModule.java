package com.shamildev.retro.ui.widgets.Search.presenter;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.widgets.Search.model.SearchResultModelModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.
 */


@Module(includes = {
        SearchResultModelModule.class
})
public abstract class SearchResultPresenterModule {

    @Binds
    @PerFragment
    abstract SearchResultPresenter searchResultPresenter(SearchResultPresenterImpl searchResultPresenter);
}