package com.shamildev.retro.ui.widgets.youtubeplayer.model;


import com.shamildev.retro.di.scope.PerFragment;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Shamil Lazar.
 */


@Module
public abstract class YoutubePlayerModelModule {

    @Binds
    @PerFragment
    abstract YoutubePlayerModel model(YoutubePlayerModelImpl model);


}