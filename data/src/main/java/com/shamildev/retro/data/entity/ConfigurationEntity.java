package com.shamildev.retro.data.entity;


import com.squareup.moshi.Json;


import java.util.List;

/**
 * Created by Schamil  on 03.11.2017.
 */

public class ConfigurationEntity implements Entity {


        @Json(name = "images")
        final ConfigurationImagesEntity images;


        @Json(name = "change_keys")
        final List<String> changeKeys;


    public ConfigurationEntity(ConfigurationImagesEntity images, List<String> changeKeys) {
        this.images = images;
        this.changeKeys = changeKeys;
    }

    public ConfigurationImagesEntity getImages() {
        return images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }
}
