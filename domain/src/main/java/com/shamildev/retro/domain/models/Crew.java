package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

import io.reactivex.annotations.Nullable;

/**
 * Created by Shamil Lazar on 13.02.2018.
 */


@AutoValue
public abstract class Crew implements DomainObject {



    @Nullable
    public abstract String department();
    @Nullable
    public abstract String job();

    public abstract Person person();
    @Nullable
    public abstract String creditId();

    public static Builder builder() {
        return new AutoValue_Crew.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder department(String department);

        public abstract Builder job(String job);

        public abstract Builder person(Person person);

        public abstract Builder creditId(String creditId);

        public abstract Crew build();
    }
}
