package com.shamildev.retro.domain.models;


import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.DomainObjectStorable;

import java.util.Optional;

import io.reactivex.annotations.Nullable;

/**
 * Created by Schamil Mischijew on 03.11.2017.
 */

@AutoValue
public abstract class Genre implements DomainObject,DomainObjectStorable {


    public abstract int id();
    public abstract String name();
    @Nullable
    public abstract String type();
    @Nullable
    public abstract String language();
    @Nullable
    public abstract Long lastUpdate();

    public static Genre add(Genre genre, String type, String language, Long lastUpdate) {
        return builder()
                .id(genre.id())
                .name(genre.name())
                .type(type)
                .language(language)
                .lastUpdate(lastUpdate)
                .build();
    }


    public static Builder builder() {
        return new AutoValue_Genre.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(int id);

        public abstract Builder name(String name);

        public abstract Builder type(String type);

        public abstract Builder language(String language);

        public abstract Builder lastUpdate(Long lastUpdate);

        public abstract Genre build();
    }
}
