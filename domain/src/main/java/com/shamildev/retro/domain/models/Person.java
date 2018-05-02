package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;


import java.util.List;



import io.reactivex.annotations.Nullable;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 */

@AutoValue
public abstract class Person implements DomainObject
{


    public abstract Long id();
    public abstract String name();

    public abstract Integer gender();
    @Nullable
    public abstract String profilePath();
    @Nullable
    public abstract Float popularity();


    @Nullable
    public abstract List<DomainObject> knownFor();






    public static Builder builder() {
        return new AutoValue_Person.Builder();
    }





    @Override
    public String toString() {
        return "Person{" +
                ", gender=" + gender() +
                ", id=" + id() +
                ", name='" + name() + '\'' +
                ", profilePath='" + profilePath() + '\'' +
                '}';
    }

    public static Person create( Long id, String name, Integer gender, String profilePath, Float popularity, List<DomainObject> knownFor) {
        return builder()
                .id(id)
                .name(name)
                .gender(gender)
                .profilePath(profilePath)
                .popularity(popularity)
                .knownFor(knownFor)
                .build();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder name(String name);

        public abstract Builder gender(Integer gender);

        public abstract Builder profilePath(String profilePath);

        public abstract Builder popularity(Float popularity);

        public abstract Builder knownFor(List<DomainObject> knownFor);



        public abstract Person build();
    }




}