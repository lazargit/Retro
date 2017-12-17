package com.shamildev.retro.domain.repository;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.domain.DomainObject;

import java.util.List;

/**
 * Created by Shamil Lazar on 16.12.2017.
 */


@AutoValue
public abstract class MovieWrapper implements DomainObject {

    public abstract int page();







    public abstract int totalPages();


    public abstract int totalResults();

    public static Builder builder() {
        return new AutoValue_MovieWrapper.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder page(int page);





        public abstract Builder totalPages(int totalPages);

        public abstract Builder totalResults(int totalResults);

        public abstract MovieWrapper build();
    }
}
