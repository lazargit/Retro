package com.shamildev.retro.ui.account.fragment.modul;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.account.fragment.presenter.AccountPresenter;
import com.shamildev.retro.ui.account.fragment.presenter.AccountPresenterImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


@Module(includes = {
        AccountModelModule.class
})
public abstract class AccountPresenterModule {

    @Binds
    @PerFragment
    abstract AccountPresenter presenter(AccountPresenterImpl presenter);


}