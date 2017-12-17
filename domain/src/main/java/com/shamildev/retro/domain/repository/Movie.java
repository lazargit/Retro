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
public abstract class Movie implements DomainObject{


    public abstract String posterPath();

    public abstract boolean adult();

    public abstract String overview();


    public abstract String releaseDate();


    public abstract List<Integer> genreIds();

    public abstract int id();


    public abstract String originalTitle();


    public abstract String originalLanguage();

    public abstract String title();


    public abstract String backdropPath();

    public abstract float popularity();


    public abstract int voteCount();

    public abstract boolean video();


    public abstract float voteAverage();

    public static Builder builder() {
        return new AutoValue_Movie.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder posterPath(String posterPath);

        public abstract Builder adult(boolean adult);

        public abstract Builder overview(String overview);

        public abstract Builder releaseDate(String releaseDate);

        public abstract Builder genreIds(List<Integer> genreIds);

        public abstract Builder id(int id);

        public abstract Builder originalTitle(String originalTitle);

        public abstract Builder originalLanguage(String originalLanguage);

        public abstract Builder title(String title);

        public abstract Builder backdropPath(String backdropPath);

        public abstract Builder popularity(float popularity);

        public abstract Builder voteCount(int voteCount);

        public abstract Builder video(boolean video);

        public abstract Builder voteAverage(float voteAverage);

        public abstract Movie build();
    }
}
