package com.shamildev.retro.data.entity.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 */

public class VideosEntity implements Serializable , Entity {
    @SerializedName("results")
    @Expose
    private List<VideosResultEntity> results = null;
    private final static long serialVersionUID = 6605727430781842527L;

    public List<VideosResultEntity> getResults() {
        return results;
    }

    public void setResults(List<VideosResultEntity> results) {
        this.results = results;
    }
}
