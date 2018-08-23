package com.shamildev.retro.ui.widgets.youtubeplayer.presenter;

import android.support.annotation.IdRes;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMultiSearch;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;
import com.shamildev.retro.ui.widgets.youtubeplayer.model.YoutubePlayerModel;
import com.shamildev.retro.ui.widgets.youtubeplayer.view.YoutubePlayerView;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * An implementation of {@link WatchListPresenter}.
 */
@PerFragment
final class YoutubePlayerPresenterImpl extends BasePresenter<YoutubePlayerView, YoutubePlayerModel> implements YoutubePlayerPresenter {



    private final UseCaseHandler useCaseHandler;
    private final GetMultiSearch getMultiSearch;
    private String searchStr = "";


    @Inject
    YoutubePlayerPresenterImpl(YoutubePlayerView view,
                               YoutubePlayerModel model,
                               UseCaseHandler useCaseHandler,
                               GetMultiSearch getMultiSearch) {
        super(view,model);
        this.useCaseHandler = useCaseHandler;
        this.getMultiSearch = getMultiSearch;
    }

    @Override
    public void onDoSomething(@IdRes int id) {


    }




}