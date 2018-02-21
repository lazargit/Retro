package com.shamildev.retro.domain.config;

import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.annotations.Nullable;

/**
 * Created by Shamil Lazar on 20.02.2018.
 */

@Singleton
public class AppConfig {

    String imageUrl;


    private Configuration configurations;

    private List<Genre> genres;

    public AppConfig(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Configuration getConfigurations() {
        return configurations;
    }

    public void setConfigurations(Configuration configurations) {
        this.configurations = configurations;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
