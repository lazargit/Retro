package com.shamildev.retro.retroimage.builder;

import com.shamildev.retro.retroimage.core.RetroImageRequest;

/**
 * Created by Shamil Lazar on 04.08.2018.
 */

public class GenericContainerLogo<T> {

     /*"logo_sizes": [
             "w45",
             "w92",
             "w154",
             "w185",
             "w300",
             "w500",
             "original"
             ],
             */
    private static final int THUMBNAIL = 0;
    private static final int SMALL = 1;
    private static final int LOW = 2;
    private static final int MEDIUM = 3;
    private static final int HIGH = 4;
    private static final int HUGE = 5;
    private static final int ORIG = 6;
    private final RetroImageRequest imageRequest;
    private T obj;

    // Pass type in as parameter to constructor
    public GenericContainerLogo(T t, RetroImageRequest imageRequest){
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
    public T w45() {
        this.imageRequest.setImageSizeSetting(THUMBNAIL);
        return obj;
    }



    /**
     * @return the obj
     */
    public T w92() {
        this.imageRequest.setImageSizeSetting(SMALL);
        return obj;
    }

    /**
     * @return the obj
     */
    public T w154() {
        this.imageRequest.setImageSizeSetting(LOW);
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
    public T w500() {
        this.imageRequest.setImageSizeSetting(HUGE);
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
