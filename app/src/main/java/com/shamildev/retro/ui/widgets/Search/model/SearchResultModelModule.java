package com.shamildev.retro.ui.widgets.Search.model;


import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */


@Module
public abstract class SearchResultModelModule {

    @Binds
    @PerFragment
    abstract SearchResultModel model(SearchResultModelImpl model);


}