package com.shamildev.retro;

import android.app.Application;


import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GlideModule {


    @Provides
    @Singleton
    public Glide glide(Application application) {

        return  Glide.get(application);

    }



}
