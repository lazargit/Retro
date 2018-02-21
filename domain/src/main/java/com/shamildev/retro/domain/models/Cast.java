package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

import io.reactivex.annotations.Nullable;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 */

@AutoValue
public abstract class Cast implements DomainObject
{

    @Nullable
    public abstract Integer castId();
    @Nullable
    public abstract String character();
    @Nullable
    public abstract Integer order();
    public abstract Person person();
    @Nullable
    public abstract String creditId();


    public static Builder builder() {
        return new AutoValue_Cast.Builder();
    }








    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder castId(Integer castId);

        public abstract Builder character(String character);

        public abstract Builder order(Integer order);

        public abstract Builder person(Person person);

        public abstract Builder creditId(String creditId);

        public abstract Cast build();
    }

    @Override
    public String toString() {
        return "Cast{" +
                "castId=" + castId() +
                ", character='" + character() + '\'' +
                ", creditId='" + creditId() + '\'' +
                ", gender=" + person().gender() +
                ", id=" + person().id() +
                ", name='" + person().name() + '\'' +
                ", order=" + order() +
                ", profilePath='" + person().profilePath() + '\'' +
                '}';
    }

}