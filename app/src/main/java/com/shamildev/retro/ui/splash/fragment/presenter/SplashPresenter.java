package com.shamildev.retro.ui.splash.fragment.presenter;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */

import android.support.annotation.IdRes;

import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.presenter.Presenter;

import java.util.HashMap;

/**
 * A {@link Presenter} that does some work and shows the results.
 */
public interface SplashPresenter extends Presenter {

    void onDoSomething(@IdRes int id);
    void onStartBootstrap();

    void screenWidth(int screenWidth);
    void startApp();
    void initTables();
    void finish(HashMap<String, ResultWrapper> map);

}
