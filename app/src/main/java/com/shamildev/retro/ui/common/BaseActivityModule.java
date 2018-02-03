package com.shamildev.retro.ui.common;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */
@Module
public abstract class BaseActivityModule {

    static final String ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager";

    static final String ACTIVITY_FRAGMENT_MANAGER2 = "BaseActivityModule.activityFragmentManager2";

    @Binds
   // @PerActivity
    abstract Context activityContext(Activity activity);

    @Provides
    @Named(ACTIVITY_FRAGMENT_MANAGER)
    @PerActivity
    static FragmentManager activityFragmentManager(Activity activity) {
        return activity.getFragmentManager();
    }


    @Provides
    @Named(ACTIVITY_FRAGMENT_MANAGER2)
    @PerActivity
    static android.support.v4.app.FragmentManager activityFragmentManager2(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();




    }
}
