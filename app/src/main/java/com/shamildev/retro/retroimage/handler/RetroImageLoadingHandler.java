package com.shamildev.retro.retroimage.handler;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.retroimage.core.RetroImageRequest;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retroimage.views.RetroImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shamil Lazar on 28.07.2018.
 */

public class RetroImageLoadingHandler {




    private final RetroImageRequestListener imageRequestListener;
    private  Boolean preload = false;
    private  int loadCount = 0;
    private RetroImageView imageView;
    private RequestManager requestManager;

    private RetroImageRequest imageRequest;
    private RetroImageView customImageView;
    private Map<Object,String> map;



    public RetroImageLoadingHandler(RetroImageRequest imageRequest, RequestManager requestManager, RetroImageRequestListener imageRequestListener) {
        this.imageRequestListener = imageRequestListener;
        this.imageRequest = imageRequest;
        this.requestManager = requestManager;
        this.map = new HashMap<>();

    }

    public void startLoad(View imageView) {




        if(!imageRequest.getItems().isEmpty()){
            for (Object obj:imageRequest.getItems()){
                if(!prepareRequest(obj).isEmpty()) {
                    this.map.put(obj, prepareRequest(obj));
                }

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.forEach((o, s) -> {

                 if(imageView instanceof RetroImageView){

                       this.imageView = (RetroImageView) imageView;
                       this.imageView .getProgressBar().setVisibility(View.VISIBLE);
                       loadFile(o).into(this.imageView .getImageView());

                 }else{
                       loadFile(o).preload();
                 }

            });
        }




    }






    private void imageLoadSuccessful(Drawable resource, Object obj) {
        Log.e("TAG","imageLoadSuccessful "+map.size()+" # "+obj.hashCode());

        if(this.imageView != null) {
            this.imageView.getProgressBar().setVisibility(View.GONE);
        }
        removeFromMap(obj);
        if(map.isEmpty()){
            imageRequestListener.onResourceReady();
        }
    }
    private void imageLoadFailed(GlideException e, Object obj) {
        if(this.imageView != null) {
            this.imageView.getProgressBar().setVisibility(View.GONE);
        }
        removeFromMap(obj);
        imageRequestListener.onLoadFailed();
    }

    private void removeFromMap(Object obj) {
        map.remove(obj);
    }




    private String prepareRequest(Object obj) {
        if(obj instanceof MediaItem){
            MediaItem item = (MediaItem) obj;
            String tmdbConfigurationSizes = this.imageRequest.getTMDBConfigurationSizes(this.imageRequest.getImageType(),this.imageRequest.getImageSizeSetting());
            String path="";


            Log.e("TAG","MEDIAITEM.....TYPE"+this.imageRequest.getImageType()+" ,,,,,,,,,, "+this.imageRequest.getImageSizeSetting()+" ");
                switch (this.imageRequest.getImageType()){
                    case POSTER:
                        if(item.itemPosterPath()!=null)
                            path = this.imageRequest.getConfigurations().baseUrl() + tmdbConfigurationSizes + item.itemPosterPath();
                        break;
                    case BACKDROP:
                        if(item.itemBackdropPath()!=null)
                            path = this.imageRequest.getConfigurations().baseUrl() + tmdbConfigurationSizes + item.itemBackdropPath();
                        break;
                    case PROFILE:
                        if(item.itemPosterPath()!=null)
                         path = this.imageRequest.getConfigurations().baseUrl() + tmdbConfigurationSizes + item.itemPosterPath();
                        break;
                    case LOGO:

                        break;
                    case STILL:

                        break;
                    case GIF:
                        break;
                    default:
                        break;

                }




          return path;

        }else if(obj instanceof String){
            String urlPath = (String) obj;
            Pattern p = Pattern.compile("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)");//. represents single character
            Matcher match = p.matcher(obj.toString());
            if(match.matches()){
                return urlPath;
            }else{
                throw new IllegalStateException("illegal path for gif file");
            }

        }
        return null;
    }


    public RequestBuilder<Drawable> loadFile( Object obj) {

        Log.e("TAG","LOAD FILE.....> "+prepareRequest(obj)+" "+obj.hashCode());


        return
                 imageRequest.requestManager
                .load(prepareRequest(obj))
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Drawable>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.e("TAG","onLoadFailed.."+obj.hashCode());
                        imageLoadFailed(e,obj);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.e("TAG","glide load file>>>>.."+isFirstResource+"##"+resource.getMinimumWidth());
                        imageLoadSuccessful(resource,obj);
                        return false;
                    }


                });

    }



}
