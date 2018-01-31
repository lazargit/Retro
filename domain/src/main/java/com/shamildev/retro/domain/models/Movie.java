package com.shamildev.retro.domain.models;



import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.DomainObjectStorable;


import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by Shamil Lazar on 16.12.2017.
 */

@AutoValue
public abstract class Movie  implements DomainObject ,DomainObjectStorable {

    @Nullable
    public abstract Long id();
    public abstract String title();


    @Nullable
    public abstract String posterPath();

    public abstract boolean adult();

    @Nullable
    public abstract String overview();

    @Nullable
    public abstract String releaseDate();

    @Nullable
    public abstract List<Integer> genreIds();




    public abstract String originalTitle();


    public abstract String originalLanguage();



    @Nullable
    public abstract String backdropPath();

    public abstract float popularity();

    @Nullable
    public abstract Integer voteCount();

    public abstract boolean video();


    public abstract float voteAverage();





    //Details
    @Nullable
    public abstract Integer budget();
    @Nullable
    public abstract Integer revenue();
    @Nullable
    public abstract Integer runtime();
    @Nullable
    public abstract String status();
    @Nullable
    public abstract String tagline();
    @Nullable
    public abstract String homepage();
    @Nullable
    public abstract String imdbId();

    @Nullable
    public abstract List<ProductionCompany> productionCompanies();
    @Nullable
    public abstract List<ProductionCountry> productionCountries();
    @Nullable
    public abstract List<SpokenLanguage> spokenLanguages();
    @Nullable
    public abstract List<Genre> genres();



    @Nullable
    public abstract Long lastUpdate();


    public static Builder builder() {

        return new AutoValue_Movie.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder title(String title);

        public abstract Builder posterPath(String posterPath);

        public abstract Builder adult(boolean adult);

        public abstract Builder overview(String overview);

        public abstract Builder releaseDate(String releaseDate);

        public abstract Builder genreIds(List<Integer> genreIds);

        public abstract Builder originalTitle(String originalTitle);

        public abstract Builder originalLanguage(String originalLanguage);

        public abstract Builder backdropPath(String backdropPath);

        public abstract Builder popularity(float popularity);

        public abstract Builder voteCount(Integer voteCount);

        public abstract Builder video(boolean video);

        public abstract Builder voteAverage(float voteAverage);

        public abstract Builder budget(Integer budget);

        public abstract Builder revenue(Integer revenue);

        public abstract Builder runtime(Integer runtime);

        public abstract Builder status(String status);

        public abstract Builder tagline(String tagline);

        public abstract Builder homepage(String homepage);

        public abstract Builder imdbId(String imdbId);

        public abstract Builder productionCompanies(List<ProductionCompany> productionCompanies);

        public abstract Builder productionCountries(List<ProductionCountry> productionCountries);

        public abstract Builder spokenLanguages(List<SpokenLanguage> spokenLanguages);

        public abstract Builder genres(List<Genre> genres);

        public abstract Builder lastUpdate(Long lastUpdate);

        public abstract Movie build();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
