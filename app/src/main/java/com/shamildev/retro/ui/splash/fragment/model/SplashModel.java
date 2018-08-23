package com.shamildev.retro.ui.splash.fragment.model;

import com.shamildev.retro.ui.common.model.BaseModel;

import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;



/**
 * Created by Shamil Lazar.
 */

public abstract class SplashModel extends BaseModel<SplashPresenter> {



     public abstract Boolean checkUser();
     public abstract void initData();
     public abstract void initConfiguration();

}
