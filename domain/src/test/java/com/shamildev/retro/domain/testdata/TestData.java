package com.shamildev.retro.domain.testdata;

import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.utils.JsonFileResource;

import io.reactivex.Flowable;

/**
 * Created by Shamil Lazar on 16.01.2018.
 */

public  class TestData {

    public static final Flowable<Genre> GENRE_CACHE_SUCCESS = getTestList();


    @JsonFileResource(fileName = "MovieDetailsResponse.json", clazz = String.class)
    public static Flowable<Genre> getTestList() {



        return Flowable.empty();
    }
}
