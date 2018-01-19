package com.shamildev.retro.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by Shamil Lazar on 27.12.2017.
 */

public class GenreEntity implements Entity {


    final Integer id;
    final String name;


    public GenreEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
