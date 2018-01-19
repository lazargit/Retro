package com.shamildev.retro.domain.models;


import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

/**
 * Created by Schamil  on 10.12.2017.
 */

@AutoValue
public abstract class ProductionCompany implements DomainObject {

    public abstract String name();
    public abstract Integer id();

    public static Builder builder() {
        return new AutoValue_ProductionCompany.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);

        public abstract Builder id(Integer id);

        public abstract ProductionCompany build();
    }
}
