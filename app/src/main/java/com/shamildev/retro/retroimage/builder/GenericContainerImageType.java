package com.shamildev.retro.retroimage.builder;

import com.shamildev.retro.retroimage.core.ImageType;
import com.shamildev.retro.retroimage.core.RetroImageRequest;

/**
 * Created by Shamil Lazar on 04.08.2018.
 */

public class GenericContainerImageType<T> {

    private final RetroImageRequest imageRequest;
    private T obj;



    // Pass type in as parameter to constructor
    public GenericContainerImageType(T t, RetroImageRequest imageRequest){
        obj = t;
        this.imageRequest = imageRequest;
    }

    /**
     * @return the obj
     */
    private T getObj() {
        return obj;
    }


    /**
     * @return the obj
     */
    public GenericContainerBackDrop<T> Backdrop() {
        this.imageRequest.setImageType(ImageType.BACKDROP);
        return new GenericContainerBackDrop<>(getObj(), this.imageRequest);
    }



    /**
     * @return the obj
     */
    public GenericContainerPoster<T> Poster() {
        this.imageRequest.setImageType(ImageType.POSTER);
        return  new GenericContainerPoster<>(getObj(), this.imageRequest);

    }


    /**
     * @return the obj
     */
    public GenericContainerProfile<T> Profile() {
        this.imageRequest.setImageType(ImageType.PROFILE);
        GenericContainerProfile<T> container = new GenericContainerProfile<>(getObj(), this.imageRequest);
        return container;

    }


    /**
     * @return the obj
     */
    public GenericContainerLogo<T> Logo() {
        this.imageRequest.setImageType(ImageType.LOGO);
        GenericContainerLogo<T> container = new GenericContainerLogo<>(getObj(), this.imageRequest);
        return (GenericContainerLogo<T>) container.getObj();

    }

    /**
     * @return the obj
     */
    public GenericContainerStill<T> Still() {
        this.imageRequest.setImageType(ImageType.STILL);
        GenericContainerStill<T> container = new GenericContainerStill<>(getObj(), this.imageRequest);
        return (GenericContainerStill<T>) container.getObj();

    }



}
