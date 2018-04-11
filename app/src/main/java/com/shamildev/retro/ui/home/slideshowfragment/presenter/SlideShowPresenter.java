package com.shamildev.retro.ui.home.slideshowfragment.presenter;

import android.support.v4.app.FragmentActivity;
import android.util.Pair;

import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.presenter.Presenter;

/**
 * Created by Shamil Lazar on 23.03.2018.
 */

public interface SlideShowPresenter extends Presenter {



    void init(Pair<ResultWrapper, Integer> bundleData, FragmentActivity activity, String tag);
    void toPosition(int position);
    void loadMore(int page);

}
