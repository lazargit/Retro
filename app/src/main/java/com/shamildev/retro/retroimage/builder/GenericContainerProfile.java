package com.shamildev.retro.retroimage.builder;

import com.shamildev.retro.retroimage.core.RetroImageRequest;

/**
 * Created by Shamil Lazar on 04.08.2018.
 */

public class GenericContainerProfile<T> {

    //w92, w154, w185, w342, w500, w780, original
    /*
    "profile_sizes": [
             "w45",
             "w185",
             "h632",
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
    public GenericContainerProfile(T t, RetroImageRequest imageRequest){
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
    public T w45() {
        this.imageRequest.setImageSizeSetting(SMALL);
        return obj;
    }

    /**
     * @return the obj
     */
    public T w185() {
        this.imageRequest.setImageSizeSetting(HIGH);
        return obj;
    }

    /**
     * @return the obj
     */
    public T w632() {
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
