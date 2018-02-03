package com.shamildev.retro.ui.common.view;

import android.support.v4.app.Fragment;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerFragment;
import dagger.internal.Beta;

/**
 * Created by Shamil Lazar on 02.02.2018.
 */
@Beta
public interface HasSupportFragmentInjector {


    /** Provides an {@link AndroidInjector} of {@link Fragment}s. */



        /** Returns an {@link AndroidInjector} of {@link Fragment}s. */
        AndroidInjector<DaggerFragment> fragmentInjector();


}
