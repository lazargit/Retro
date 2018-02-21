package com.shamildev.retro.data.entity.tmdb.response;

import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;

import java.io.Serializable;

/**
 * Created by Schamil Mischijew on 04.11.2017.
 */

class ResultResponse implements Serializable , Entity {

    @SerializedName("media_type")
    private String media_type;


    @SerializedName("media_type")
    private String mediaType;


    @SerializedName("poster_path")
    private String posterPath;


    @SerializedName("release_date")
    private String releaseDate;




    public ResultResponse(String media_type, String mediaType, String posterPath, String releaseDate) {
        this.media_type = media_type;
        this.mediaType = mediaType;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
