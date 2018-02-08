package com.shamildev.retro.data.entity.tmdb.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;
import com.shamildev.retro.data.entity.tmdb.BackdropEntity;
import com.shamildev.retro.data.entity.tmdb.PosterEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shamil Lazar on 06.02.2018.
 */

public class ImagesResponse implements Serializable, Entity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("backdrops")
    @Expose
    private List<BackdropEntity> backdrops = null;
    @SerializedName("posters")
    @Expose
    private List<PosterEntity> posters = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BackdropEntity> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<BackdropEntity> backdrops) {
        this.backdrops = backdrops;
    }

    public List<PosterEntity> getPosters() {
        return posters;
    }

    public void setPosters(List<PosterEntity> posters) {
        this.posters = posters;
    }

}
