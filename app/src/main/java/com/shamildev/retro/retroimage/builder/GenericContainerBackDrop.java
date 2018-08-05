package com.shamildev.retro.retroimage.builder;

import com.shamildev.retro.retroimage.core.RetroImageRequest;

/**
 * Created by Shamil Lazar on 04.08.2018.
 */

public class GenericContainerBackDrop<T> {

     /*
     "backdrop_sizes": [
        "w300",
        "w780",
        "w1280",
        "original"
    ],
     */
    private static final int SMALL = 0;
    private static final int MEDIUM = 1;
    private static final int HIGH = 2;
    private static final int ORIG = 3;
    private RetroImageRequest imageRequest;
    private T obj;



    // Pass type in as parameter to constructor
    public GenericContainerBackDrop(T t,RetroImageRequest imageRequest){
        this.imageRequest = imageRequest;
        obj = t;
    }

    /**
     * @return the obj
     */
    protected T getObj() {
        return obj;
    }


    /**
     * @return the obj
     */
    public T w300() {
        this.imageRequest.setImageSizeSetting(SMALL);
        return obj;
    }

    /**
     * @return the obj
     */
    public T w780() {
        this.imageRequest.setImageSizeSetting(HIGH);
        return obj;
    }

    /**
     * @return the obj
     */
    public T w1280() {
        this.imageRequest.setImageSizeSetting(MEDIUM);
        return obj;
    }

    /**
     * @return the obj
     */
    public T original() {
        this.imageRequest.setImageSizeSetting(ORIG);
        return obj;
    }


}
