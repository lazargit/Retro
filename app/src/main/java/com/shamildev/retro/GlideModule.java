package com.shamildev.retro;

import android.app.Application;


import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.shamildev.retro.di.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GlideModule {


    @Provides
    @Singleton
    public Glide glide(Application application) {
        return new GlideBuilder().build(application);

    }



}
