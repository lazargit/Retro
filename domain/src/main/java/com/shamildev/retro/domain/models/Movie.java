package com.shamildev.retro.domain.models;



import com.google.auto.value.AutoValue;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.DomainObjectStorable;
import com.shamildev.retro.domain.MediaItem;


import java.io.Serializable;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;

/**
 * Created by Shamil Lazar on 16.12.2017.
 */

@AutoValue
public abstract class Movie  implements DomainObject,MediaItem, DomainObjectStorable, Serializable {



    public abstract Long id();
    public abstract String title();


    @Nullable
    public abstract String posterPath();

    @Nullable
    public abstract Boolean adult();

    @Nullable
    public abstract String overview();

    @Nullable
    public abstract String releaseDate();

    @Nullable
    public abstract List<Integer> genreIds();



    @Nullable
    public abstract String originalTitle();

    @Nullable
    public abstract String originalLanguage();



    @Nullable
    public abstract String backdropPath();
    @Nullable
    public abstract Float popularity();

    @Nullable
    public abstract Integer voteCount();
    @Nullable
    public abstract Boolean video();

    @Nullable
    public abstract Float voteAverage();





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
    public abstract  List<ProductionCompany> productionCompanies();
    @Nullable
    public abstract List<ProductionCountry> productionCountries();
    @Nullable
    public abstract List<SpokenLanguage> spokenLanguages();
    @Nullable
    public abstract List<Genre> genres();

    @Nullable
    public abstract Images images();

    @Nullable
    public abstract Credits credits();

    @Nullable
    public abstract List<Cast> casts();



    @Nullable
    public abstract Boolean isInWatchList();


    @Nullable
    public abstract Long lastUpdate();


    public static Builder builder() {

        return new AutoValue_Movie.Builder();
    }


    public  Movie setImages(Images images) {
        Builder builder = getBuilder();
        builder.images(images);
        return builder.build();
    }

    public  Movie setCredits(Credits credits) {
        Builder builder = getBuilder();
        builder.credits(credits);
        return builder.build();
    }

    public Movie setInWatchList(Boolean bool) {
        return getBuilder().isInWatchList(bool).build();
    }



    private Builder getBuilder() {

        return builder()
                .id(id())
                .title(title())
                .posterPath(posterPath())
                .adult(adult())
                .overview(overview())
                .releaseDate(releaseDate())
                .genreIds(genreIds())
                .originalTitle(originalTitle())
                .originalLanguage(originalLanguage())
                .backdropPath(backdropPath())
                .popularity(popularity())
                .voteCount(voteCount())
                .video(video())
                .voteAverage(voteAverage())
                .budget(budget())
                .revenue(revenue())
                .runtime(runtime())
                .status(status())
                .tagline(tagline())
                .homepage(homepage())
                .imdbId(imdbId())
                .productionCompanies(productionCompanies())
                .productionCountries(productionCountries())
                .spokenLanguages(spokenLanguages())
                .genres(genres())
                .images(images())
                .credits(credits())
                .casts(casts())
                .isInWatchList(isInWatchList())
                .lastUpdate(lastUpdate());
    }



//    "vote_average": 8.3,
//            "vote_count": 11289,

//            "video": false,
//            "media_type": "movie",

//            "popularity": 44.572965,
//            "poster_path": "/k5w6XErKZn50S61JrztSYwUbg8K.jpg",
//            "original_language": "en",
//            "original_title": "Fight Club",
//            "genre_ids": [
//            18
//            ],
//            "backdrop_path": "/87hTDiay2N2qWyX4Ds7ybXi9h8I.jpg",
//            "adult": false,
//            "overview": "Ein Yuppie findet beim charismatischen Tyler Durden Unterschlupf, nachdem seine Wohnung in die Luft gejagt wird. Ein Gerangel zwischen den beiden entwickelt sich zu einer Schlägerei, die mit der Erkenntnis endet, dass man sich nach einer ordentlichen Portion Prügel einfach besser fühlt. Der \"Fight Club\" ist geboren. Immer mehr Männer versammeln sich, um sich zu schlagen - und gestärkt in den Alltag zu gehen. Wie ein Virus greift das Konzept um sich, doch für Tyler ist der Kampfverein nur die erste Stufe, um die USA in die Knie zu zwingen.",
//            "release_date": "1999-10-15"




    public static Movie create(Long id,
                               String title,
                               String posterPath,
                               Boolean adult,
                               String overview,
                               String releaseDate,
                               List<Integer> genreIds,
                               String originalTitle,
                               String originalLanguage,
                               String backdropPath,
                               Float popularity,
                               Integer voteCount,
                               Boolean video,
                               Float voteAverage) {


        return builder()
                .id(id)
                .title(title)
                .posterPath(posterPath)
                .backdropPath(backdropPath)
                .adult(adult)
                .overview(overview)
                .releaseDate(releaseDate)
                .genreIds(genreIds)
                .originalTitle(originalTitle)
                .originalLanguage(originalLanguage)
                .popularity(popularity)
                .voteCount(voteCount)
                .video(video)
                .voteAverage(voteAverage)
                .build();
    }


    @Override
    public String toString() {
        return super.toString();
    }




    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder title(String title);

        public abstract Builder posterPath(String posterPath);

        public abstract Builder adult(Boolean adult);

        public abstract Builder overview(String overview);

        public abstract Builder releaseDate(String releaseDate);

        public abstract Builder genreIds(List<Integer> genreIds);

        public abstract Builder originalTitle(String originalTitle);

        public abstract Builder originalLanguage(String originalLanguage);

        public abstract Builder backdropPath(String backdropPath);

        public abstract Builder popularity(Float popularity);

        public abstract Builder voteCount(Integer voteCount);

        public abstract Builder video(Boolean video);

        public abstract Builder voteAverage(Float voteAverage);

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

        public abstract Builder images(Images images);

        public abstract Builder credits(Credits credits);

        public abstract Builder casts(List<Cast> casts);

        public abstract Builder isInWatchList(Boolean isInWatchList);



        public abstract Builder lastUpdate(Long lastUpdate);

        public abstract Movie build();
    }


    @Override
    public final Long itemId() {
        return id();
    }

    @Override
    public final String itemTitle() {
        return title();
    }

    @Override
    public final String itemPosterPath() {
        return posterPath();
    }

    @Override
    public String itemBackdropPath() {
        return backdropPath();
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
