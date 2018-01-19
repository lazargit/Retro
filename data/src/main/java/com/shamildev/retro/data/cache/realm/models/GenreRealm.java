package com.shamildev.retro.data.cache.realm.models;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Schamil Mischijew on 21.11.2017.
 */

public class GenreRealm extends RealmObject {




    @PrimaryKey
    private String key;

    private int genreId;
    private String language;

    private String name;
    private String mediaType;

    private long last_update;



    @Ignore
    public static final String FIELD_LANGUAGE = "language";
    @Ignore
    public static final String FIELD_ID = "genreId";
    @Ignore
    public static final String FIELD_NAME = "name";
    @Ignore
    public static final String FIELD_MEDIATYPE= "mediaType";
    @Ignore
    public static final String FIELD_LAST_UPDATE = "last_update";

    public GenreRealm() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }
}
