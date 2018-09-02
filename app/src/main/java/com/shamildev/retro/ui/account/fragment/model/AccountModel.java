package com.shamildev.retro.ui.account.fragment.model;

import com.shamildev.retro.ui.account.fragment.presenter.AccountPresenter;
import com.shamildev.retro.ui.common.model.BaseModel;

import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;


/**
 * Created by Shamil Lazar.
 */

public abstract class AccountModel extends BaseModel<AccountPresenter> {

     public abstract void initData();
     public abstract void logOut();
     public abstract void signInUser();
     public abstract void signInUser(String token);
}
