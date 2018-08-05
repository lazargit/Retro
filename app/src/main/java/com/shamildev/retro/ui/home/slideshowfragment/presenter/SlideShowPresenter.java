package com.shamildev.retro.ui.home.slideshowfragment.presenter;

import android.support.v4.app.FragmentActivity;
import android.util.Pair;

import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.models.ResultWrapper;

import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.presenter.Presenter;
import com.shamildev.retro.ui.home.slideshowfragment.view.SlideShowDialogFragment;

/**
 * Created by Shamil Lazar on 23.03.2018.
 */

public interface SlideShowPresenter extends Presenter {



    void init(Pair<ResultWrapper, Integer> bundleData, SlideShowDialogFragment slideShowDialogFragment, FragmentActivity activity, String tag, RetroImage retroImage);
    void toPosition(int position);
    void loadMore(int page);
    void addItemToWatchList(MediaItem item);

}
