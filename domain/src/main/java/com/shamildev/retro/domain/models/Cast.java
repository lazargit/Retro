package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 */

@AutoValue
public abstract class Cast implements DomainObject
{


    public abstract Integer castId();
    public abstract String character();
    public abstract String creditId();
    public abstract Integer gender();
    public abstract Integer id();
    public abstract String name();
    public abstract Integer order();
    public abstract String profilePath();






    public static Builder builder() {
        return new AutoValue_Cast.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder castId(Integer castId);

        public abstract Builder character(String character);

        public abstract Builder creditId(String creditId);

        public abstract Builder gender(Integer gender);

        public abstract Builder id(Integer id);

        public abstract Builder name(String name);

        public abstract Builder order(Integer order);

        public abstract Builder profilePath(String profilePath);

        public abstract Cast build();
    }


    @Override
    public String toString() {
        return "Cast{" +
                "castId=" + castId() +
                ", character='" + character() + '\'' +
                ", creditId='" + creditId() + '\'' +
                ", gender=" + gender() +
                ", id=" + id() +
                ", name='" + name() + '\'' +
                ", order=" + order() +
                ", profilePath='" + profilePath() + '\'' +
                '}';
    }
}