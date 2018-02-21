package com.shamildev.retro.domain.models;

/**
 * Created by Schamil Lazar on 04.11.2017.
 */

public class Type {

    long id;
    String media_type;
    Double popularity;


    public Type(long id, String media_type, Double popularity) {
        setId(id);
        setMedia_type(media_type);
        setPopularity(popularity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedia_type() {
        return this.media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Double getPopularity() {
        return this.popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }
}
