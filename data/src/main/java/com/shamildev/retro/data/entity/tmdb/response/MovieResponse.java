package com.shamildev.retro.data.entity.tmdb.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;
import com.shamildev.retro.data.entity.tmdb.CreditsEntity;
import com.shamildev.retro.data.entity.tmdb.GenreEntity;
import com.shamildev.retro.data.entity.tmdb.ImagesEntity;
import com.shamildev.retro.data.entity.tmdb.KeywordsEntity;
import com.shamildev.retro.data.entity.tmdb.ProductionCompanyEntity;
import com.shamildev.retro.data.entity.tmdb.ProductionCountryEntity;

import com.shamildev.retro.data.entity.tmdb.Recommendations;
import com.shamildev.retro.data.entity.tmdb.SimilarMovies;
import com.shamildev.retro.data.entity.tmdb.SpokenLanguageEntity;
import com.shamildev.retro.data.entity.tmdb.Trailers;
import com.shamildev.retro.data.entity.tmdb.VideosEntity;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Schamil Mischijew on 10.12.2017.
 */

public class MovieResponse implements Serializable , Entity
{

    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
//    @SerializedName("belongs_to_collection")
//    @Expose
//    private Object belongsToCollection;
    @SerializedName("budget")
    @Expose
    private Integer budget;
    @SerializedName("genres")
    @Expose
    private List<GenreEntity> genres = null;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("production_companies")
    @Expose
    private List<ProductionCompanyEntity> productionCompanies = null;
    @SerializedName("production_countries")
    @Expose
    private List<ProductionCountryEntity> productionCountries = null;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("revenue")
    @Expose
    private Integer revenue;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("spoken_languages")
    @Expose
    private List<SpokenLanguageEntity> spokenLanguages = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("videos")
    @Expose
    private VideosEntity videos;
    @SerializedName("images")
    @Expose
    private ImagesEntity images;
    @SerializedName("trailers")
    @Expose
    private Trailers trailers;
    @SerializedName("similar_movies")
    @Expose
    private SimilarMovies similarMovies;
   // @SerializedName("changes")
//    @Expose
//    private Changes changes;
    @SerializedName("credits")
    @Expose
    private CreditsEntity credits;
  //  @SerializedName("reviews")
//    @Expose
//    private Reviews reviews;
    @SerializedName("keywords")
    @Expose
    private KeywordsEntity keywords;
    //@SerializedName("lists")
    //@Expose
   // private Lists lists;
    // @SerializedName("translations")
    //  @Expose
    //  private Translations translations;

    @SerializedName("recommendations")
    @Expose
    private Recommendations recommendations;

    private final static long serialVersionUID = -4344412520983951453L;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompanyEntity> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompanyEntity> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountryEntity> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountryEntity> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguageEntity> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguageEntity> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public VideosEntity getVideos() {
        return videos;
    }

    public void setVideos(VideosEntity videos) {
        this.videos = videos;
    }

    public ImagesEntity getImages() {
        return images;
    }

    public void setImages(ImagesEntity images) {
        this.images = images;
    }

    public Trailers getTrailers() {
        return trailers;
    }

    public void setTrailers(Trailers trailers) {
        this.trailers = trailers;
    }

    public SimilarMovies getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(SimilarMovies similarMovies) {
        this.similarMovies = similarMovies;
    }



    public CreditsEntity getCredits() {
        return credits;
    }

    public void setCredits(CreditsEntity credits) {
        this.credits = credits;
    }



    public KeywordsEntity getKeywords() {
        return keywords;
    }

    public void setKeywords(KeywordsEntity keywords) {
        this.keywords = keywords;
    }



    public Recommendations getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Recommendations recommendations) {
        this.recommendations = recommendations;
    }


    @Override
    public String toString() {
        return "MovieResponse{" +
                "adult=" + adult +
                ", backdropPath='" + backdropPath + '\'' +
                ", budget=" + budget +
                ", genres=" + genres +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", imdbId='" + imdbId + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", productionCompanies=" + productionCompanies +
                ", productionCountries=" + productionCountries +
                ", releaseDate='" + releaseDate + '\'' +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", spokenLanguages=" + spokenLanguages +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", videos=" + videos +
                ", images=" + images +
                ", trailers=" + trailers +
                ", similarMovies=" + similarMovies +
                ", credits=" + credits +
                ", keywords=" + keywords +
                ", recommendations=" + recommendations +
                '}';
    }
}