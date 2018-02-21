package com.shamildev.retro.domain.models;


import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

import java.io.Serializable;
import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by Schamil Mischijew on 03.11.2017.
 */

@AutoValue
public abstract class Credits implements DomainObject,Serializable {


    public abstract int id();
    @Nullable
    public abstract List<Cast> casts();
    @Nullable
    public abstract List<Crew> crews();

    public static Credits create(int id, List<Cast> casts, List<Crew> crews) {
        return builder()
                .id(id)
                .casts(casts)
                .crews(crews)
                .build();
    }


    public static Builder builder() {
        return  new AutoValue_Credits.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(int id);

        public abstract Builder casts(List<Cast> casts);

        public abstract Builder crews(List<Crew> crews);

        public abstract Credits build();
    }
}
