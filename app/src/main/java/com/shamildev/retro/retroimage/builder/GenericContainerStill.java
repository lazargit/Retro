package com.shamildev.retro.retroimage.builder;

import com.shamildev.retro.retroimage.core.RetroImageRequest;

/**
 * Created by Shamil Lazar on 04.08.2018.
 */

public class GenericContainerStill<T> {


   /*
    "still_sizes": [
                "w92",
                "w185",
                "w300",
                "original"
       ]
    */
    private static final int THUMBNAIL = 0;
    private static final int MEDIUM = 1;
    private static final int HIGH = 2;
    private static final int ORIG = 3;






    private final RetroImageRequest imageRequest;

    private T obj;

    // Pass type in as parameter to constructor
    public GenericContainerStill(T t, RetroImageRequest imageRequest){
        obj = t;
        this.imageRequest = imageRequest;
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
    public T w92() {
        this.imageRequest.setImageSizeSetting(THUMBNAIL);
        return obj;
    }



    /**
     * @return the obj
     */
    public T w185() {
        this.imageRequest.setImageSizeSetting(MEDIUM);
        return obj;
    }

    /**
     * @return the obj
     */
    public T w300() {
        this.imageRequest.setImageSizeSetting(HIGH);
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
