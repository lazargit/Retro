package com.shamildev.retro.data.entity.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Schamil Mischijew on 02.11.2017.
 */

public class GenresResponseEntity implements Serializable, Entity {

    @SerializedName("genres")
    @Expose
    private List<GenreEntity> genres = null;
    private final static long serialVersionUID = 1391249529817738183L;

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }





}




