package com.shamildev.retro.domain.models;


import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.DomainObjectStorable;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.bootstrap.Bootstrap;

import java.io.Serializable;
import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by Shamil Lazar on 16.12.2017.
 */
/*
  #"created_by": [
    {
      "id": 160172,
      "name": "Chuck Lorre",
      "gender": 2,
      "profile_path": "/btpYlMV71sjQXrV142I9kogEINI.jpg"
    },
    {
      "id": 163528,
      "name": "Bill Prady",
      "gender": 0,
      "profile_path": "/duXUvo8JtivQR0BHiXHGQwoNYB4.jpg"
    }
  ],
  #  "production_companies": [
    {
      "name": "Warner Bros. Television",
      "id": 1957
    },
    {
      "name": "Chuck Lorre Productions",
      "id": 35504
    }
  ],
 # "episode_run_time": [
    22
  ],
 # "first_air_date": "2007-09-24"#
 #"homepage": "http://www.cbs.com/shows/big_bang_theory/",
  #"id": 1418,
 # "in_production": true,
 # "languages": [
    "en"
  ],
 # "last_air_date": "2018-03-02",
 # "name": "The Big Bang Theory",
 # "networks": [
    {
      "id": 16,
      "name": "CBS"
    }
  ],
 # "number_of_episodes": 259,
 # "number_of_seasons": 11,
 # "origin_country": [
    "US"
  ],
 # "backdrop_path": "/nGsNruW3W27V6r4gkyc3iiEGsKR.jpg",
 # "status": "Returning Series",
 # "type": "Scripted",
 # "vote_average": 6.9,
 # "vote_count": 2576
 # "original_language": "en",
 # "original_name": "The Big Bang Theory",
 # "overview": "Leonard und Sheldon sind brillante Physiker, die meist zusammen mit ihren Kumpels Howard und Rajesh in ihrem gemeinsamen Apartment abhängen und - natürlich nach einem festgelegten Plan - ihre Videogames pflegen. Einzig und allein Penny, die neue Nachbarin, schafft es hin und wieder, für Abwechslung zu sorgen. Sie hat zwar keine Ahnung von Physik, doch kennt sie die Welt da draußen - etwas, was den Physikern vollkommen fremd zu sein scheint...",
 #"popularity": 198.192965,
 # "poster_path": "/ooBGRQBdbGzBxAVfExiO8r7kloA.jpg",
 #"seasons": [
    {
      "air_date": null,
      "episode_count": 12,
      "id": 3732,
      "poster_path": "/cULNyA0aNTtA94NaXSscPSBNSzG.jpg",
      "season_number": 0
    },
 */
@AutoValue
public abstract class TVShow implements DomainObject, MediaItem, DomainObjectStorable, Serializable {


    public abstract Long id();
    public abstract String name();
    @Nullable
    public abstract String originalName();
    @Nullable
    public abstract String overview();
    @Nullable
    public abstract String posterPath();
    @Nullable
    public abstract String backdropPath();
    @Nullable
    public abstract String originalLanguage();
    @Nullable
    public abstract Float popularity();
    @Nullable
    public abstract Integer voteCount();
    @Nullable
    public abstract Float voteAverage();
    @Nullable
    public abstract String firstAirDate();
    @Nullable
    public abstract String lastAirDate();
    @Nullable
    public abstract String status();
    @Nullable
    public abstract String type();
    @Nullable
    public abstract String homepage();
    @Nullable
    public abstract Boolean inProduction();
    @Nullable
    public abstract Integer numberOfEpisodes();
    @Nullable
    public abstract Integer numberOfSeasons();
    @Nullable
    public abstract List<Integer> episodeRunTime();
    @Nullable
    public abstract List<String> originCountry();

    @Nullable
    public abstract List<String> languages();
    @Nullable
    public abstract List<Integer> genreIds(); // Not in TVSHOW-Details
    @Nullable
    public abstract List<Genre> genres();
    @Nullable
    public abstract  List<ProductionCompany> productionCompanies();
    @Nullable
    public abstract  List<TVShowNetwork> networks();
    @Nullable
    public abstract  List<Person> createdBy();
    @Nullable
    public abstract  List<Season> seasons();

    //add images or credits
    @Nullable
    public abstract Images images();
    @Nullable
    public abstract Credits credits();


    @Nullable
    public abstract Boolean isInWatchList();


    @Nullable
    public abstract Long lastUpdate();

    public TVShow addImages(Images images) {
        Builder builder = getBuilder();
        builder.images(images);
        return builder.build();
    }

    public TVShow addCredits(Credits credits) {
        Builder builder = getBuilder();
        builder.credits(credits);
        return builder.build();
    }



    public MediaItem setInWatchList(Boolean bool) {

       return getBuilder().isInWatchList(bool).build();

    }


public static TVShow create(Long id, String name, String originalName, String overview, String posterPath, String backdropPath, String originalLanguage, Float popularity, Integer voteCount, Float voteAverage, String firstAirDate, List<String> originCountry,  List<Integer> genreIds) {
    return builder()
            .id(id)
            .name(name)
            .originalName(originalName)
            .overview(overview)
            .posterPath(posterPath)
            .backdropPath(backdropPath)
            .originalLanguage(originalLanguage)
            .popularity(popularity)
            .voteCount(voteCount)
            .voteAverage(voteAverage)
            .firstAirDate(firstAirDate)
            .genreIds(genreIds)
            .originCountry(originCountry)
            .build();
}



    private Builder getBuilder() {
        return builder()
                .id(id())
                .name(name())
                .originalName(originalName())
                .overview(overview())
                .posterPath(posterPath())
                .backdropPath(backdropPath())
                .originalLanguage(originalLanguage())
                .popularity(popularity())
                .voteCount(voteCount())
                .voteAverage(voteAverage())
                .firstAirDate(firstAirDate())
                .lastAirDate(lastAirDate())
                .status(status())
                .type(type())
                .homepage(homepage())
                .inProduction(inProduction())
                .numberOfEpisodes(numberOfEpisodes())
                .numberOfSeasons(numberOfSeasons())
                .episodeRunTime(episodeRunTime())
                .originCountry(originCountry())
                .languages(languages())
                .genreIds(genreIds())
                .genres(genres())
                .productionCompanies(productionCompanies())
                .networks(networks())
                .createdBy(createdBy())
                .seasons(seasons())
                .images(images())
                .credits(credits())
                .isInWatchList(isInWatchList())
                .lastUpdate(lastUpdate());
    }




    public static Builder builder() {

        return new AutoValue_TVShow.Builder();
    }







    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder name(String name);

        public abstract Builder originalName(String originalName);

        public abstract Builder overview(String overview);

        public abstract Builder posterPath(String posterPath);

        public abstract Builder backdropPath(String backdropPath);

        public abstract Builder originalLanguage(String originalLanguage);

        public abstract Builder popularity(Float popularity);

        public abstract Builder voteCount(Integer voteCount);

        public abstract Builder voteAverage(Float voteAverage);

        public abstract Builder firstAirDate(String firstAirDate);

        public abstract Builder lastAirDate(String lastAirDate);

        public abstract Builder status(String status);

        public abstract Builder type(String type);

        public abstract Builder homepage(String homepage);

        public abstract Builder inProduction(Boolean inProduction);

        public abstract Builder numberOfEpisodes(Integer numberOfEpisodes);

        public abstract Builder numberOfSeasons(Integer numberOfSeasons);

        public abstract Builder episodeRunTime(List<Integer> episodeRunTime);

        public abstract Builder originCountry(List<String> originCountry);

        public abstract Builder languages(List<String> languages);

        public abstract Builder genreIds(List<Integer> genreIds);

        public abstract Builder genres(List<Genre> genres);

        public abstract Builder productionCompanies(List<ProductionCompany> productionCompanies);

        public abstract Builder networks(List<TVShowNetwork> networks);

        public abstract Builder createdBy(List<Person> createdBy);

        public abstract Builder seasons(List<Season> seasons);

        public abstract Builder images(Images images);

        public abstract Builder credits(Credits credits);

        public abstract Builder isInWatchList(Boolean isInWatchList);

        public abstract Builder lastUpdate(Long lastUpdate);

        public abstract TVShow build();
    }

    @Override
    public final Long itemId() {
        return id();
    }

    @Override
    public final String itemTitle() {
        return name();
    }

    @Override
    public final String itemPosterPath() {
        return posterPath();
    }
    @Override
    public final Float itemPopularity() {
        return popularity();
    }

    @Override
    public final Boolean itemIsInWatchList() {
        return isInWatchList();
    }
    @Override
    public final MediaItem itemAddToWatchList() {
        return setInWatchList(true);
    }
    @Override
    public final MediaItem itemRemoveFromWatchList() {
        return setInWatchList(false);
    }

}
