package com.shamildev.retro.ui.widgets.youtubeplayer.presenter;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.ui.widgets.youtubeplayer.model.YoutubePlayerModelModule;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Shamil Lazar
 */


@Module(includes = {
        YoutubePlayerModelModule.class
})
public abstract class YoutubePlayerPresenterModule {

    @Binds
    @PerFragment
    abstract YoutubePlayerPresenter searchResultPresenter(YoutubePlayerPresenterImpl searchResultPresenter);
}