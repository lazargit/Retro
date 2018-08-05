package com.shamildev.retro.retroimage.core;

import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.RequestManager;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.retroimage.handler.RetroImageLoadingHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shamil Lazar on 26.07.2018.
 */



public class RetroImageRequest {



    private RetroImage retroImage;
    public  RequestManager requestManager;






    private  String urlPath;
    private  List<String> urlPathList = new ArrayList<>();
    private int age;

    private RetroImageRequestListener requestListener;
    private RetroImageLoadingHandler retroImageLoadingHandler;
    private int setting;
    private int cat;
    private List<Object> items = new ArrayList<>();
    private int imageLoadSuccessfulCount = 0;
    private ProgressBar progressBar;
    private ImageSizes typeSetting;
    private ImageType imageType;
    private ImageSizes imageSize;
    private int imageSizeSetting;
    private RetroImageRequestListener listener;



    private  Configuration configurations;
    public Configuration getConfigurations() {return configurations;}

    public RetroImageRequest(RetroImage retroImage, MediaItem item) {
        this.retroImage = retroImage;
        this.items.add(item);

    }

    public RetroImageRequest(RetroImage retroImage, List<MediaItem> items, Configuration configurations, RequestManager requestManager) {
        this.configurations = configurations;
        this.requestManager = requestManager;
        this.retroImage = retroImage;
        this.items.addAll(items);


    }

    public RetroImageRequest(RetroImage retroImage, String urlPath, Configuration configurations, RequestManager requestManager) {
        this.configurations = configurations;
        this.requestManager = requestManager;
        this.retroImage = retroImage;
        this.items.add(urlPath);
    }

    public RetroImageRequest(RetroImage retroImage, ArrayList<String> urlPath, Configuration configurations, RequestManager requestManager) {
        this.retroImage = retroImage;
        this.configurations = configurations;
        this.requestManager = requestManager;
        this.items.addAll(urlPath);
    }

    public RetroImageRequest(RetroImage retroImage, MediaItem item, Configuration configurations, RequestManager requestManager) {
        this.retroImage = retroImage;
        this.items.add(item);
        this.configurations = configurations;
        this.requestManager = requestManager;
    }


    protected int getSetting() {
        return setting;
    }

    protected void setSetting(int setting) {
        this.setting = setting;
    }

    private void setSetting(ImageSizes type) {
        this.typeSetting = type;
    }

    protected int getCat() {
        return cat;
    }

    protected void setCat(int cat) {
        this.cat = cat;
    }

    public List<Object> getItems() {return items;}

    public String getUrlPath() { return urlPath; }

    public void setImageType(ImageType type) {
        this.imageType = type;
    }
    public ImageType getImageType() {
        return this.imageType;
    }

    public void setImageSizeSetting(int imageSizeSetting) {
        this.imageSizeSetting = imageSizeSetting;
    }

    public int getImageSizeSetting() {
        return imageSizeSetting;
    }

    protected void setImageSize(ImageSizes size) {
        this.imageSize = size;
    }

    protected ImageSizes getImageSize() {
        return imageSize;
    }
    private void setListener(RetroImageRequestListener listener) {
        this.listener = listener;
    }





    public void loadImage(RetroImageRequestListener imageRequestListener, View imageView) {

            setListener(imageRequestListener);
            retroImageLoadingHandler = new RetroImageLoadingHandler(this,requestManager,imageRequestListener);
            retroImageLoadingHandler.startLoad(imageView);


    }


    public String getTMDBConfigurationSizes(ImageType type, int pos) {
        String size="";
        switch (type){
            case POSTER:
                if(pos < this.configurations.posterSizes().size()) {
                    size = this.configurations.posterSizes().get(pos);
                }else{
                    size = this.configurations.posterSizes().get(0);
                }

                break;
            case BACKDROP:
                if(pos < this.configurations.backdropSizes().size()) {
                    size = this.configurations.backdropSizes().get(pos);
                }else{
                    size = this.configurations.backdropSizes().get(0);
                }

                break;
            case PROFILE:
                if(pos < this.configurations.profileSizes().size()) {
                    size = this.configurations.profileSizes().get(pos);
                }else{
                    size = this.configurations.profileSizes().get(0);
                }
                break;
            case LOGO:
                if(pos < this.configurations.logoSizes().size()) {
                    size = this.configurations.logoSizes().get(pos);
                }else{
                    size = this.configurations.logoSizes().get(0);
                }
                break;
            case STILL:
                if(pos < this.configurations.stillSizes().size()) {
                    size = this.configurations.stillSizes().get(pos);
                }else{
                    size = this.configurations.stillSizes().get(0);
                }
                break;
            case GIF:
                break;
            default:
                break;

        }

        return size;
    }

}



