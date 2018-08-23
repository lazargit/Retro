package com.shamildev.retro.ui.details.fragment.presenter;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.details.fragment.model.DetailsModelModule;
import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar.
 */


@Module(includes = {
        DetailsModelModule.class
})
public abstract class DetailsPresenterModule {

    @Binds
    @PerFragment
    abstract DetailsPresenter detailsPresenter(DetailsPresenterImpl presenter);



}