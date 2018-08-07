package com.shamildev.retro.retroimage.builder;

import com.shamildev.retro.retroimage.core.RetroImageRequest;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.views.retroslider.views.ImageSliderView;

public class RetroImageCloseVariant2{


    private RetroImageRequest imageRequest;


    /**************VARINAT 2*****************
     * For image stack to preload and place into ImageSlider
     *
     * @param imageRequest
     */



    public RetroImageCloseVariant2(RetroImageRequest imageRequest) {
        this.imageRequest = imageRequest;

    }

    public void preload(RetroImageRequestListener imageRequestListener){
        imageRequest.loadImage(imageRequestListener,null);

    }

    public void into(ImageSliderView imageSliderView, RetroImageRequestListener imageRequestListener){
        imageRequest.loadImage(imageRequestListener,imageSliderView);
    }


}
