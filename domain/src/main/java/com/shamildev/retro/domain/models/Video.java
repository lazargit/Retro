package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 */

@AutoValue
public abstract class Video implements DomainObject {




    public abstract  String id();
    public abstract  String iso6391();
    public abstract  String iso31661();
    public abstract  String key();
    public abstract  String name();
    public abstract  String site();
    public abstract  Integer size();
    public abstract  String type();

    public static Builder builder() {
        return new AutoValue_Video.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(String id);

        public abstract Builder iso6391(String iso6391);

        public abstract Builder iso31661(String iso31661);

        public abstract Builder key(String key);

        public abstract Builder name(String name);

        public abstract Builder site(String site);

        public abstract Builder size(Integer size);

        public abstract Builder type(String type);

        public abstract Video build();
    }
}



