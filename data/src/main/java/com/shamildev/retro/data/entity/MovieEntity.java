package com.shamildev.retro.data.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;


import java.util.List;

/**
 * Created by Shamil Lazar on 16.12.2017.
 */

@AutoValue
public abstract class MovieEntity implements Entity {

    @SerializedName("poster_path")
    public abstract String posterPath();

    public abstract boolean adult();

    public abstract String overview();

    @SerializedName("release_date")
    public abstract String releaseDate();

    @SerializedName("genre_ids")
    public abstract List<Integer> genreIds();

    public abstract int id();

    @SerializedName("original_title")
    public abstract String originalTitle();

    @SerializedName("original_language")
    public abstract String originalLanguage();

    public abstract String title();

    @SerializedName("backdrop_path")
    public abstract String backdropPath();

    public abstract float popularity();

    @SerializedName("vote_count")
    public abstract int voteCount();

    public abstract boolean video();

    @SerializedName("vote_average")
    public abstract float voteAverage();

    public static TypeAdapter<MovieEntity> typeAdapter(Gson gson) {
        return new AutoValue_MovieEntity.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_MovieEntity.Builder();
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

        public abstract MovieEntity build();
    }
}
