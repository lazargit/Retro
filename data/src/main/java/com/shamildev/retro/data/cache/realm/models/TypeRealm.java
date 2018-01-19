package com.shamildev.retro.data.cache.realm.models;

import io.realm.RealmModel;

/**
 * Created by Schamil Mischijew on 09.12.2017.
 */

public class TypeRealm implements RealmModel {


    long id;
    String media_type;
    Double popularity;


    public TypeRealm(long id, String media_type, Double popularity) {
        this.id = id;
        this.media_type = media_type;
        this.popularity = popularity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }



}
