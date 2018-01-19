package com.shamildev.retro.domain.models;


import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

/**
 * Created by Schamil  on 10.12.2017.
 */

@AutoValue
public abstract class SpokenLanguage implements DomainObject {


    public abstract String iso6391();
    public abstract String name();

    public static Builder builder() {
        return new AutoValue_SpokenLanguage.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder iso6391(String iso6391);

        public abstract Builder name(String name);

        public abstract SpokenLanguage build();
    }
}
