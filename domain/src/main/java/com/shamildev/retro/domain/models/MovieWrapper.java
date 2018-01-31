package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;


/**
 * Created by Shamil Lazar on 16.12.2017.
 */


@AutoValue
public abstract class MovieWrapper implements DomainObject {

    public abstract int page();

    public abstract int totalPages();

    public abstract int totalResults();

    @Nullable
    public abstract List<Movie> results();

    public static Builder builder() {
        return new AutoValue_MovieWrapper.Builder();
    }




    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder page(int page);

        public abstract Builder totalPages(int totalPages);

        public abstract Builder results(List<Movie> results);

        public abstract Builder totalResults(int totalResults);


        public abstract MovieWrapper build();
    }
}
