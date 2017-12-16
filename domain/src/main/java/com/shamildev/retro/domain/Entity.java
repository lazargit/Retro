package com.shamildev.retro.domain;








import com.google.auto.value.AutoValue;

import java.util.List;


/**
 * Created by Shamil Lazar on 15.12.2017.
 */


@AutoValue
public abstract class Entity implements DomainObject {

    public abstract String id();

    public abstract String name();

    public static Builder builder() {
        return new AutoValue_Entity.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(String id);

        public abstract Builder name(String name);

        public abstract Entity build();
    }
}
