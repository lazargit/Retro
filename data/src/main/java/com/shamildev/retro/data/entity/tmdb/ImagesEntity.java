package com.shamildev.retro.data.entity.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Schamil Mischijew on 03.11.2017.
 */
/*


@SerializedName("backdrops")
@Expose
private List<BackdropEntity> backdrops = null;

@SerializedName("posters")
@Expose
private List<PosterEntity> posters = null;






 */

public class ImagesEntity implements Serializable , Entity
{

    @SerializedName("backdrops")
    @Expose
    private List<BackdropEntity> backdrops = null;
    @SerializedName("posters")
    @Expose
    private List<PosterEntity> posters = null;
    private final static long serialVersionUID = -832727765545783384L;

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
