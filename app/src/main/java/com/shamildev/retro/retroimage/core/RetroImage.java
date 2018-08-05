package com.shamildev.retro.retroimage.core;

import android.support.annotation.NonNull;

import com.bumptech.glide.RequestManager;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.util.Pair;
import com.shamildev.retro.retroimage.builder.GenericContainerImageType;
import com.shamildev.retro.retroimage.builder.RetroImageCloseVariant1;
import com.shamildev.retro.retroimage.builder.RetroImageCloseVariant2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shamil Lazar on 26.07.2018.
 */

public class RetroImage {



    private  RequestManager requestManager;
    private String imageUrl;
    private int screenWidth;
    private int screenHeight;
    private Configuration configurations;
    private RetroImageRequest imageRequest;


    public RetroImage(RequestManager requestManager, Pair<Integer, Integer> screenSize) {
        this.requestManager = requestManager;
        this.screenWidth =  screenSize.key;
        this.screenHeight =  screenSize.value;
    }
    public void setConfigurations(Configuration configurations){
        this.configurations = configurations;
    }
    protected Configuration getConfigurations() {
        return configurations;
    }


    /**************
     *
     * @param items
     * @return
     */
    public GenericContainerImageType<RetroImageCloseVariant2> load(@NonNull List<MediaItem> items){
        this.imageRequest = new RetroImageRequest(this,items,configurations,this.requestManager);
        return new GenericContainerImageType<>( new RetroImageCloseVariant2(this.imageRequest), this.imageRequest );

    }
    public GenericContainerImageType<RetroImageCloseVariant1> load(@NonNull MediaItem item){
        this.imageRequest = new RetroImageRequest(this,item,configurations,this.requestManager);
        return new GenericContainerImageType<>( new RetroImageCloseVariant1(this.imageRequest), this.imageRequest );
    }
    public RetroImageCloseVariant1 load(@NonNull String urlPath){
        this.imageRequest = new RetroImageRequest(this,urlPath,configurations,this.requestManager);
        return new RetroImageCloseVariant1(this.imageRequest);
    }
    public RetroImageCloseVariant2 load(@NonNull ArrayList<String> urlPath){
        this.imageRequest = new RetroImageRequest(this,urlPath,configurations,this.requestManager);
        return new RetroImageCloseVariant2(this.imageRequest);
    }
}
