package com.shamildev.retro.domain.repository;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.shamildev.retro.domain.DomainObject;

/**
 * Created by Shamil Lazar on 16.12.2017.
 */

@AutoValue
public abstract class Dates implements DomainObject {

    abstract String maximum();

    abstract String minimum();



    public static Builder builder() {
        return new AutoValue_Dates.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder maximum(String maximum);

        public abstract Builder minimum(String minimum);

        public abstract Dates build();
    }
}