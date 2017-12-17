package com.shamildev.retro.data.entity;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.DataModule;
import com.squareup.moshi.Json;


import java.util.List;

/**
 * Created by Shamil Lazar on 16.12.2017.
 */



public class MovieWrapperEntity implements Entity {


    final  int page;

//    @SerializedName("results")
//    public abstract List<MovieEntity> results();

//    @SerializedName("dates")
//    public abstract DatesEntity dates();


    @Json(name = "total_pages")
    final  int totalPages;

    @Json(name = "total_results")
    final  int totalResults;


    public MovieWrapperEntity(int page, int totalPages, int totalResults) {
        this.page = page;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }





}
