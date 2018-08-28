package com.shamildev.retro.ui.splash.fragment.presenter;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */

import android.support.annotation.IdRes;

import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.presenter.Presenter;

import java.util.HashMap;
import java.util.List;

/**
 * A {@link Presenter} that does some work and shows the results.
 */
public interface SplashPresenter extends Presenter {

    void startApp();
    void configRetroImage(Configuration configuration);
    void finish(HashMap<String, ResultWrapper> map);

    void onError(Throwable t);

    void setBackgroundImage(List<DomainObject> results);
}
