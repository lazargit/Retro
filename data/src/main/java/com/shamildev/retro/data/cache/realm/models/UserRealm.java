package com.shamildev.retro.data.cache.realm.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Schamil Lazar.
 */

public class UserRealm extends RealmObject {




    @PrimaryKey
    private String user_id;
    private String name;
    private String language;
    private String tmdb_guest_session;
    private long expires_at;
    private long last_update;


    public UserRealm() {

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTmdb_guest_session() {
        return tmdb_guest_session;
    }

    public void setTmdb_guest_session(String tmdb_guest_session) {
        this.tmdb_guest_session = tmdb_guest_session;
    }



    public long getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(long expires_at) {
        this.expires_at = expires_at;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }
}
