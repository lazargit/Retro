package com.shamildev.retro.data;

import com.google.auto.value.AutoValue;

/**
 * Created by Shamil Lazar on 15.12.2017.
 */

@AutoValue
public abstract class Model {
    abstract int id();

    public static Builder builder() {
        return new AutoValue_Model.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(int id);

        public abstract Model build();



    }
}
