package com.shamildev.retro.ui.gallery.gridlist;

import com.shamildev.retro.di.scope.PerFragment;

import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.home.fragment.adapter.ViewPagerAdapter;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * An implementation of {@link WatchListPresenter}.
 */
@PerFragment
final class GridListPresenterImpl extends BasePresenter<GridListView> implements GridListPresenter {


    private final RetroImage retroImage;
    private ViewPagerAdapter mViewPagerAdapter;


    @Inject
    GridListPresenterImpl(GridListView view,
                          RetroImage retroImage) {
        super(view);

        this.retroImage = retroImage;



    }


}