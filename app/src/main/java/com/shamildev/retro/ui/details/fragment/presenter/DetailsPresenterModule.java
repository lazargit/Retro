package com.shamildev.retro.ui.details.fragment.presenter;

import com.shamildev.retro.di.scope.PerFragment;



import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module
public abstract class DetailsPresenterModule {

    @Binds
    @PerFragment
    abstract DetailsPresenter detailsPresenter(DetailsPresenterImpl presenter);



}