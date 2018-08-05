package com.shamildev.retro.ui.home.fragment.watchlist;

import android.content.Context;

import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.presenter.Presenter;
import com.shamildev.retro.ui.home.fragment.view.HomePageFragment;

import java.util.List;

/**
 * Created by Shamil Lazar on 25.05.2018.
 */

public interface WatchListPresenter extends Presenter {

    void init(Context context, WatchListFragment watchListFragment);
    List<MediaItem> mediaItems();
}
