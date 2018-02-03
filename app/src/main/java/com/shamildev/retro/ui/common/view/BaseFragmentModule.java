package com.shamildev.retro.ui.common.view;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;

import com.shamildev.retro.di.scope.PerFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.android.support.DaggerFragment;

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of {@link Fragment} and named {@link #FRAGMENT}.
 */
@Module
public class BaseFragmentModule {

    /**
     * See {@link BaseChildFragmentModule} class documentation regarding the need for this name.
     */
    public static final String FRAGMENT = "BaseFragmentModule.fragment";
    public static final String FRAGMENT_V4 = "BaseFragmentV4Module.fragment";

    static final String CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager";
    static final String CHILD_FRAGMENT_V4_MANAGER = "BaseFragmentV4Module.childFragmentManager";

    @Provides
    @Named(CHILD_FRAGMENT_MANAGER)
    @PerFragment
    static FragmentManager childFragmentManager(@Named(FRAGMENT) Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return fragment.getChildFragmentManager();
        }
        return fragment.getFragmentManager();
    }

    @Provides
    @Named(CHILD_FRAGMENT_V4_MANAGER)
    @PerFragment
    static android.support.v4.app.FragmentManager childFragmentV4Manager(@Named(FRAGMENT_V4) DaggerFragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return fragment.getChildFragmentManager();
        }
        return fragment.getChildFragmentManager();
    }

}
