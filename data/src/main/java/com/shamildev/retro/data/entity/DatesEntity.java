package com.shamildev.retro.data.entity;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


/**
 * Created by Shamil Lazar on 16.12.2017.
 */

@AutoValue
public abstract class DatesEntity implements Entity  {

    abstract String maximum();

    abstract String minimum();

    public static TypeAdapter<DatesEntity> typeAdapter(Gson gson) {
        return new AutoValue_DatesEntity.GsonTypeAdapter(gson);
    }
}