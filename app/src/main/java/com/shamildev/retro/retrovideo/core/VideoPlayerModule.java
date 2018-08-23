package com.shamildev.retro.retrovideo.core;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.shamildev.retro.domain.util.Pair;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.util.DeviceUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class VideoPlayerModule {


    @Provides
    @Singleton
    static RetroVideo processVideoPlayer(Application application) {

        return new RetroVideo(application);
    }



}
