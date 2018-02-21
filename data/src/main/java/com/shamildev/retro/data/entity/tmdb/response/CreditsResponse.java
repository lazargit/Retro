package com.shamildev.retro.data.entity.tmdb.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;
import com.shamildev.retro.data.entity.tmdb.CastEntity;
import com.shamildev.retro.data.entity.tmdb.CrewEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Shamil Lazar on 06.02.2018.
 */

public class CreditsResponse implements Serializable, Entity {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("cast")
    @Expose
    private List<CastEntity> cast = null;

    @SerializedName("crew")
    @Expose
    private List<CrewEntity> crew = null;

    public Integer getId() {
        return id;
    }


    public List<CastEntity> getCast() {
        return cast;

    }

    public List<CrewEntity> getCrew() {
        return crew;
    }
}
