package com.shamildev.retro.data.cache.realm.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Schamil  on 16.11.2017.
 */

public class TMDbConfigurationRealm extends RealmObject {


    @PrimaryKey
    private String baseUrl;
    private String secureBaseUrl;

    private RealmList<String> changeKeys = new RealmList<>();
    private RealmList<String> backdropSizes = new RealmList<>();
    private RealmList<String> logoSizes = new RealmList<>();
    private RealmList<String> posterSizes = new RealmList<>();
    private RealmList<String> profileSizes = new RealmList<>();
    private RealmList<String> stillSizes = new RealmList<>();
    private long last_update;


    public TMDbConfigurationRealm() {

    }



    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public void setSecureBaseUrl(String secureBaseUrl) {
        this.secureBaseUrl = secureBaseUrl;
    }


    public RealmList<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(RealmList<String> changeKeys) {
        this.changeKeys = changeKeys;
    }

    public RealmList<String> getBackdropSizes() {
        return backdropSizes;
    }

    public void setBackdropSizes(RealmList<String> backdropSizes) {
        this.backdropSizes = backdropSizes;
    }

    public RealmList<String> getLogoSizes() {
        return logoSizes;
    }

    public void setLogoSizes(RealmList<String> logoSizes) {
        this.logoSizes = logoSizes;
    }

    public RealmList<String> getPosterSizes() {
        return posterSizes;
    }

    public void setPosterSizes(RealmList<String> posterSizes) {
        this.posterSizes = posterSizes;
    }

    public RealmList<String> getProfileSizes() {
        return profileSizes;
    }

    public void setProfileSizes(RealmList<String> profileSizes) {
        this.profileSizes = profileSizes;
    }

    public RealmList<String> getStillSizes() {
        return stillSizes;
    }

    public void setStillSizes(RealmList<String> stillSizes) {
        this.stillSizes = stillSizes;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }


    @Override
    public String toString() {
        return "TMDbConfigurationRealm{" +
                "baseUrl='" + baseUrl + '\'' +
                ", secureBaseUrl='" + secureBaseUrl + '\'' +
                ", changeKeys=" + changeKeys +
                ", backdropSizes=" + backdropSizes +
                ", logoSizes=" + logoSizes +
                ", posterSizes=" + posterSizes +
                ", profileSizes=" + profileSizes +
                ", stillSizes=" + stillSizes +
                ", last_update=" + last_update +
                '}';
    }
}
