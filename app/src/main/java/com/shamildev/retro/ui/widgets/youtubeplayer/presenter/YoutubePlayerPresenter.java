package com.shamildev.retro.ui.widgets.youtubeplayer.presenter;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */

import android.support.annotation.IdRes;

import com.shamildev.retro.ui.common.presenter.Presenter;

/**
 * A {@link Presenter} that does some work and shows the results.
 */
public interface YoutubePlayerPresenter extends Presenter {
    void onDoSomething(@IdRes int id);
}
