package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 */

@AutoValue
public abstract class ProductionCountry implements DomainObject {

    public abstract String iso31661();
    public abstract String name();

    public static Builder builder() {
        return new AutoValue_ProductionCountry.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder iso31661(String iso31661);

        public abstract Builder name(String name);

        public abstract ProductionCountry build();
    }
}
