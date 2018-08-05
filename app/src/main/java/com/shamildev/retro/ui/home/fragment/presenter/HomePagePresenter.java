package com.shamildev.retro.ui.home.fragment.presenter;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */

import android.content.Context;
import android.view.View;

import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.presenter.Presenter;
import com.shamildev.retro.ui.home.fragment.view.HomePageFragment;
import com.shamildev.retro.retroimage.views.RetroImageView;

/**
 * A {@link Presenter} that does some work and shows the results.
 */
public interface HomePagePresenter extends Presenter {




    void init(ResultWrapper movieWrapper, Context context, String tag, HomePageFragment homePageFragment);

    void loadMore(int page);

    void onItemClick(int position, RetroImageView transitioningView, View view);


}
