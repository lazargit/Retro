package com.shamildev.retro.ui.details.fragment.model;


import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */


@Module
public abstract class DetailsModelModule {

    @Binds
    @PerFragment
    abstract DetailsModel splashModel(DetailsModelImpl model);


}