package com.shamildev.retro.retrovideo.core;

import android.app.Application;
import android.support.annotation.NonNull;

import com.bumptech.glide.RequestManager;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.util.Pair;
import com.shamildev.retro.retroimage.builder.GenericContainerImageType;
import com.shamildev.retro.retroimage.builder.RetroImageCloseVariant1;
import com.shamildev.retro.retroimage.builder.RetroImageCloseVariant2;
import com.shamildev.retro.retroimage.core.RetroImageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shamil Lazar on 26.07.2018.
 */

public class RetroVideo {



    private  RequestManager requestManager;
    private String imageUrl;
    private int screenWidth;
    private int screenHeight;
    private Configuration configurations;
    private RetroImageRequest imageRequest;


    public RetroVideo(Application application) {



    }

}
