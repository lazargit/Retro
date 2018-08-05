package com.shamildev.retro.retroimage.builder;

import com.shamildev.retro.retroimage.core.RetroImageRequest;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.views.RetroImageView;


//VARINAT 1 FOR CLOSE LOADEXPRESSION
//

public class RetroImageCloseVariant1 {


    private RetroImageRequest imageRequest;


    /**************VARINAT 1******************
     * Just for single image to preload and place into
     *
     * @param imageRequest
     */


    public RetroImageCloseVariant1(RetroImageRequest imageRequest) {

        this.imageRequest = imageRequest;

    }


    public void into(RetroImageView customImageView, RetroImageRequestListener imageRequestListener){
        imageRequest.loadImage(imageRequestListener,customImageView);

    }

    public void preload(RetroImageRequestListener imageRequestListener){
        imageRequest.loadImage(imageRequestListener,null);
    }

}
