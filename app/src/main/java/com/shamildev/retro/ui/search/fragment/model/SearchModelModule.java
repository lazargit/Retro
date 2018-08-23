package com.shamildev.retro.ui.search.fragment.model;
import com.shamildev.retro.di.scope.PerFragment;
import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

@Module
public abstract class SearchModelModule {

    @Binds
    @PerFragment
    abstract SearchModel model(SearchModelImpl model);


}