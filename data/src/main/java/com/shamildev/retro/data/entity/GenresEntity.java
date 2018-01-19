package com.shamildev.retro.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by Shamil Lazar on 27.12.2017.
 */

public class GenresEntity implements Entity {


    @Json(name = "genres")
    final List<GenreEntity> genres;

    public GenresEntity(List<GenreEntity> genres) {
        this.genres = genres;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "GenresEntity{" +
                "genres=" + genres +
                '}';
    }
}
