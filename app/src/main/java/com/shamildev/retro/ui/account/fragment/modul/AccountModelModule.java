package com.shamildev.retro.ui.account.fragment.modul;


import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.account.fragment.model.AccountModel;
import com.shamildev.retro.ui.account.fragment.model.AccountModelImpl;


import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */

 @Module
 public abstract class AccountModelModule {

        @Binds
        @PerFragment
        abstract AccountModel model(AccountModelImpl model);


 }