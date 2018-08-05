package com.shamildev.retro.retroimage.core;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.shamildev.retro.domain.util.Pair;
import com.shamildev.retro.util.DeviceUtils;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ProcessImageModule {


    @Provides
    @Singleton
    static RetroImage processRetroImage(Application application) {
        Pair<Integer, Integer> screenSizes = new Pair<>(DeviceUtils.getScreenWidth(application), DeviceUtils.getScreenHeight(application));
        return new RetroImage(Glide.with(application.getBaseContext()),screenSizes);
    }



}
