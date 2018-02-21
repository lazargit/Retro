package com.shamildev.retro.domain.models;

import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;

import io.reactivex.annotations.Nullable;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 ###### "seasons" #######
 "air_date": null,
 "episode_count": 12,
 "id": 3732,
 "poster_path": "/cULNyA0aNTtA94NaXSscPSBNSzG.jpg",
 "season_number": 0

 */

@AutoValue
public abstract class Season implements DomainObject
{


    public abstract Integer id();

    @Nullable
    public abstract String airDate();
    @Nullable
    public abstract Integer episodeCount();
    @Nullable
    public abstract Integer seasonNumber();
    @Nullable
    public abstract String posterPath();






    public static Builder builder() {
        return new AutoValue_Season.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Integer id);

        public abstract Builder airDate(String airDate);

        public abstract Builder episodeCount(Integer episodeCount);

        public abstract Builder seasonNumber(Integer seasonNumber);

        public abstract Builder posterPath(String posterPath);

        public abstract Season build();
    }


}