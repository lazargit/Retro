package com.shamildev.retro.data.entity.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 */

public class CreditsEntity implements Serializable , Entity
{


    @SerializedName("cast")
    @Expose
    private List<CastEntity> cast = null;

    @SerializedName("crew")
    @Expose
    private List<CrewEntity> crew = null;


    public List<CastEntity> getCast() {
        return cast;
    }

    public void setCast(List<CastEntity> cast) {
        this.cast = cast;
    }



    public List<CrewEntity> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewEntity> crew) {
        this.crew = crew;
    }
}