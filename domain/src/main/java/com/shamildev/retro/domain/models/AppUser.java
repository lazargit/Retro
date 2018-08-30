package com.shamildev.retro.domain.models;

import com.shamildev.retro.domain.DomainObject;

/**
 * Created by Shamil Lazar
 */
public class AppUser implements DomainObject {

    private static final String anonymus  = "anonymus";
    private String name;
    private String language;

    private  String user_id;
    private String tmdb_guest_session;
    private Long tmdb_expires_at;
    private String email;


    public AppUser(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTmdb_guest_session() {
        return tmdb_guest_session;
    }

    public void setTmdb_guest_session(String tmdb_guest_session) {
        this.tmdb_guest_session = tmdb_guest_session;
    }

    public Long getTmdb_expires_at() {
        return tmdb_expires_at;
    }

    public void setTmdb_expires_at(Long tmdb_expires_at) {
        this.tmdb_expires_at = tmdb_expires_at;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }



    public void setUser(User user) {
        setName(user.name());
        setLanguage(user.language());
        setTmdb_guest_session(user.tmdb_guest_session());
        setTmdb_expires_at(user.tmdb_expires_at());
    }


    @Override
    public String toString() {
        return "AppUser{" +
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", user_id='" + user_id + '\'' +
                ", tmdb_guest_session='" + tmdb_guest_session + '\'' +
                ", tmdb_expires_at=" + tmdb_expires_at +
                ", email='" + email + '\'' +
                '}';
    }
}
