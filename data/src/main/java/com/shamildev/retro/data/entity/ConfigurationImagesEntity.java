package com.shamildev.retro.data.entity;


import com.squareup.moshi.Json;


import java.util.List;

/**
 * Created by Schamil Mischijew on 14.11.2017.
 */

public class ConfigurationImagesEntity implements Entity{

    @Json(name = "base_url")
    final String baseUrl;

    @Json(name = "secure_base_url")
    final String secureBaseUrl;


    @Json(name = "backdrop_sizes")
    final List<String> backdropSizes;



    @Json(name = "logo_sizes")
    final List<String> logoSizes;


    @Json(name = "poster_sizes")
    final List<String> posterSizes;


    @Json(name = "profile_sizes")
    final List<String> profileSizes;


    @Json(name = "still_sizes")
    final List<String> stillSizes;

    public ConfigurationImagesEntity(String baseUrl, String secureBaseUrl, List<String> backdropSizes, List<String> logoSizes, List<String> posterSizes, List<String> profileSizes, List<String> stillSizes) {
        this.baseUrl = baseUrl;
        this.secureBaseUrl = secureBaseUrl;
        this.backdropSizes = backdropSizes;
        this.logoSizes = logoSizes;
        this.posterSizes = posterSizes;
        this.profileSizes = profileSizes;
        this.stillSizes = stillSizes;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getSecureBaseUrl() {
        return secureBaseUrl;
    }

    public List<String> getBackdropSizes() {
        return backdropSizes;
    }

    public List<String> getLogoSizes() {
        return logoSizes;
    }

    public List<String> getPosterSizes() {
        return posterSizes;
    }

    public List<String> getProfileSizes() {
        return profileSizes;
    }

    public List<String> getStillSizes() {
        return stillSizes;
    }
}
