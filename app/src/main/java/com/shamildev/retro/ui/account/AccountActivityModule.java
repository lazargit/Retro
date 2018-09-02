package com.shamildev.retro.ui.account;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.shamildev.retro.di.scope.PerActivity;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.account.fragment.modul.AccountFragmentModule;
import com.shamildev.retro.ui.account.fragment.view.AccountFragment;
import com.shamildev.retro.ui.common.BaseActivityModule;
import com.shamildev.retro.ui.home.HomeActivity;


import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Shamil Lazar.


 * Provides Splashscreen activity dependencies.
 */
@Module(includes = {BaseActivityModule.class})
public abstract class AccountActivityModule {


    @PerFragment
    @ContributesAndroidInjector(modules = AccountFragmentModule.class)
    abstract AccountFragment fragmentInjector();


    @Binds
    @PerActivity
    abstract AppCompatActivity activity(AccountActivity activity);

}
