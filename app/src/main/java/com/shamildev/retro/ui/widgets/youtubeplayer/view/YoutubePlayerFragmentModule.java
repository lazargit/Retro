package com.shamildev.retro.ui.widgets.youtubeplayer.view;



import android.support.v4.app.Fragment;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.common.view.BaseFragmentModule;
import com.shamildev.retro.ui.widgets.Search.presenter.SearchResultPresenterModule;
import com.shamildev.retro.ui.widgets.youtubeplayer.presenter.YoutubePlayerPresenterModule;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar on 13.12.2017.

 * Provides Splashfragment dependencies.
 */
@Module(includes = {
        BaseFragmentModule.class,
        YoutubePlayerPresenterModule.class
})
public abstract class YoutubePlayerFragmentModule {

    /**
     * As per the contract specified in {@link BaseFragmentModule}; "This must be included in all
     * fragment modules, which must provide a concrete implementation of {@link Fragment}
     * and named {@link BaseFragmentModule#FRAGMENT}.
     *
     * @param fragment the {@link YoutubePlayerFragment}
     * @return the fragment
     */
    @Binds
    @Named(BaseFragmentModule.FRAGMENT_V4)
    @PerFragment
    abstract Fragment fragment(YoutubePlayerFragment fragment);

    @Binds
    @PerFragment
    abstract YoutubePlayerView watchListView(YoutubePlayerFragment fragment);
}