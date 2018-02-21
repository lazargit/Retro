package com.shamildev.retro.data.entity.tmdb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shamildev.retro.data.entity.Entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Schamil Mischijew on 04.11.2017.
 */

public class Result implements Entity {


   /* vote_count	773
    id	284053
    video	false
    vote_average	7.7
    title	"Thor: Ragnarok"
    popularity	622.276594
    poster_path	"/oSLd5GYGsiGgzDPKTwQh7wamO8t.jpg"
    original_language	"en"
    original_title	"Thor: Ragnarok"
    genre_ids	[…]
    backdrop_path	"/wBzMnQ01R9w58W6ucltdYfOyP4j.jpg"
    adult	false
    overview	"Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela."
    release_date	"2017-10-25"
*/
/*
"popularity": 15.215624,
      "media_type": "person",
      "id": 287,
      "profile_path": "/kU3B75TyRiCgE270EyZnHjfivoq.jpg",
      "name": "Brad Pitt",
      "known_for": [
        {
          "vote_average": 8.3,
          "vote_count": 11289,
          "id": 550,
          "video": false,
          "media_type": "movie",
          "title": "Fight Club",
          "popularity": 44.572965,
          "poster_path": "/k5w6XErKZn50S61JrztSYwUbg8K.jpg",
          "original_language": "en",
          "original_title": "Fight Club",
          "genre_ids": [
            18
          ],
          "backdrop_path": "/87hTDiay2N2qWyX4Ds7ybXi9h8I.jpg",
          "adult": false,
          "overview": "Ein Yuppie findet beim charismatischen Tyler Durden Unterschlupf, nachdem seine Wohnung in die Luft gejagt wird. Ein Gerangel zwischen den beiden entwickelt sich zu einer Schlägerei, die mit der Erkenntnis endet, dass man sich nach einer ordentlichen Portion Prügel einfach besser fühlt. Der \"Fight Club\" ist geboren. Immer mehr Männer versammeln sich, um sich zu schlagen - und gestärkt in den Alltag zu gehen. Wie ein Virus greift das Konzept um sich, doch für Tyler ist der Kampfverein nur die erste Stufe, um die USA in die Knie zu zwingen.",
          "release_date": "1999-10-15"
        },
        {
          "vote_average": 8,
          "vote_count": 7925,
          "id": 16869,
          "video": false,
          "media_type": "movie",
          "title": "Inglourious Basterds",
          "popularity": 37.814361,
          "poster_path": "/7hrfJGGkbgBO1Z7SDz3TdaB7idx.jpg",
          "original_language": "en",
          "original_title": "Inglourious Basterds",
          "genre_ids": [
            18,
            28,
            53,
            10752
          ],
          "backdrop_path": "/7nF6B9yCEq1ZCT82sGJVtNxOcl5.jpg",
          "adult": false,
          "overview": "Im von Deutschland besetzten Frankreich muss Shosanna ansehen wie ihre Familie durch Oberst Hans Landa brutal ermordet wird. Sie kann entkommen und flieht nach Paris. Gemeinsam mit seinen 8 Männern, einer Elitetruppe aus jüdisch-amerikanischen Soldaten, will Offizier Aldo Raine systematische Vergeltungsschläge gegen Nazis durchführen. Sie werden in Frankreich abgesetzt, um dort unterzutauchen. Von den Deutschen als ‚Die Bastarde' gefürchtet versuchen sie den Führer des III. Reichs zu töten.",
          "release_date": "2009-08-18"
        },
        {
          "vote_average": 8.2,
          "vote_count": 6850,
          "id": 807,
          "video": false,
          "media_type": "movie",
          "title": "Sieben",
          "popularity": 32.316065,
          "poster_path": "/fNuSBLoaWcCemNzZCsJVZ6MZgBc.jpg",
          "original_language": "en",
          "original_title": "Se7en",
          "genre_ids": [
            80,
            9648,
            53
          ],
          "backdrop_path": "/ba4CpvnaxvAgff2jHiaqJrVpZJ5.jpg",
          "adult": false,
          "overview": "Der besonnene Detective Summerset ist ein Kriminologe der “alten Schule”. Zusammen mit seinem neuen Kollegen, dem Heißsporn Mills, wird er auf den ungewöhnlichsten und erschreckendsten Fall seiner Laufbahn angesetzt. Ein unbekannter Serienkiller versucht scheinbar, die Stadt von allen Sünden zu befreien. Er tut dies, indem er auf bestialische Weise für jede der sieben Todsünden einen symbolischen Ritualmord begeht.",
          "release_date": "1995-09-22"
        }
      ],
      "adult": false
    },
 */

    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("video")
    @Expose
    private Boolean video;

    @SerializedName("media_type")
    @Expose
    private String mediaType;



    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("title")
    @Expose
    private String title;




    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("original_name")
    @Expose
    private String originalName;


    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry = null;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;

    // Person Result
    @SerializedName("known_for")
    @Expose
    private List<Result> knownFor = null;




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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }


    public List<Result> getKnownFor() {
        return knownFor;
    }
}
