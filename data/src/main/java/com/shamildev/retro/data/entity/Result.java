package com.shamildev.retro.data.entity;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;


import java.util.List;

/**
 * Created by Shamil Lazar on 16.12.2017.
 */


public  class Result implements Entity {


    final   String posterPath;

    final   boolean adult;

    final   String overview;


    @Json(name = "release_date")
    final   String releaseDate;


    final  List<Integer> genreIds;

    final  int id;


    @Json(name = "original_title")
    final  String originalTitle;


    @Json(name = "original_language")
    final  String originalLanguage;

    final   String title;


    @Json(name = "backdrop_path")
    final  String backdropPath;

    final  float popularity;

    @Json(name = "vote_count")
    final  int voteCount;

    final  boolean video;

    @Json(name = "vote_average")
    final  float voteAverage;

    public Result(String posterPath, boolean adult, String overview, String releaseDate, List<Integer> genreIds, int id, String originalTitle, String originalLanguage, String title, String backdropPath, float popularity, int voteCount, boolean video, float voteAverage) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
    }


    public String getPosterPath() {
        return posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    @Override
    public String toString() {
        return "Result{" +
                "posterPath='" + posterPath + '\'' +
                ", title='" + title + '\'' +
                ", adult=" + adult +
                ", releaseDate='" + releaseDate + '\'' +
                ", genreIds=" + genreIds +
                ", id=" + id +
                ", originalTitle='" + originalTitle + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +

                ", backdropPath='" + backdropPath + '\'' +
                ", popularity=" + popularity +
                ", voteCount=" + voteCount +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                '}';
    }
}


//        12-18 20:36:15.887 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Coco - Lebendiger als das Leben', adult=false, releaseDate='2017-10-27', genreIds=null, id=354912, originalTitle='Coco', originalLanguage='en', backdropPath='/askg3SMvhqEl4OL52YuvdtY40Yb.jpg', popularity=581.8773, voteCount=505, video=false, voteAverage=7.5}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Bo und der Weihnachtsstern', adult=false, releaseDate='2017-11-15', genreIds=null, id=355547, originalTitle='The Star', originalLanguage='en', backdropPath='/iJ5dkwIHQnq8dfmwSLh7dpETNhi.jpg', popularity=344.75793, voteCount=61, video=false, voteAverage=4.5}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Disaster Artist', adult=false, releaseDate='2017-12-01', genreIds=null, id=371638, originalTitle='The Disaster Artist', originalLanguage='en', backdropPath='/bAI7aPHQcvSZXvt7L11kMJdS0Gm.jpg', popularity=254.25793, voteCount=69, video=false, voteAverage=7.4}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Jumanji: Willkommen im Dschungel', adult=false, releaseDate='2017-12-08', genreIds=null, id=353486, originalTitle='Jumanji: Welcome to the Jungle', originalLanguage='en', backdropPath='/rz3TAyd5kmiJmozp3GUbYeB5Kep.jpg', popularity=202.9752, voteCount=110, video=false, voteAverage=5.8}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Wonder', adult=false, releaseDate='2017-11-13', genreIds=null, id=406997, originalTitle='Wonder', originalLanguage='en', backdropPath='/f1Jv532UM9j6zrV2XVkHL7HyyIW.jpg', popularity=191.69968, voteCount=166, video=false, voteAverage=7.5}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Darkest Hour', adult=false, releaseDate='2017-11-22', genreIds=null, id=399404, originalTitle='Darkest Hour', originalLanguage='en', backdropPath='/mBd56RoS8HiiLT1u0ZZAWFWSU3g.jpg', popularity=122.82281, voteCount=35, video=false, voteAverage=3.6}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='November Criminals', adult=false, releaseDate='2017-12-08', genreIds=null, id=317091, originalTitle='November Criminals', originalLanguage='en', backdropPath='/lyX1uZMeMNql7HG2FzNeLAItK17.jpg', popularity=111.96987, voteCount=27, video=false, voteAverage=4.3}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Wonder Wheel', adult=false, releaseDate='2017-12-01', genreIds=null, id=429189, originalTitle='Wonder Wheel', originalLanguage='en', backdropPath='/jGYeZzcAG0df2nWRLJW2CiweyyG.jpg', popularity=92.849724, voteCount=35, video=false, voteAverage=4.8}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Greatest Showman', adult=false, releaseDate='2017-12-20', genreIds=null, id=316029, originalTitle='The Greatest Showman', originalLanguage='en', backdropPath='/oPqHO43MY9ahl4eTyitSdKt9b0D.jpg', popularity=70.015366, voteCount=2, video=false, voteAverage=2.0}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Pitch Perfect 3', adult=false, releaseDate='2017-12-21', genreIds=null, id=353616, originalTitle='Pitch Perfect 3', originalLanguage='en', backdropPath='/A95XgdRdwLrsvhO2AWc8qhRDh2i.jpg', popularity=53.21202, voteCount=3, video=false, voteAverage=0.0}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Downsizing', adult=false, releaseDate='2017-12-22', genreIds=null, id=301337, originalTitle='Downsizing', originalLanguage='en', backdropPath='/5RzW9i3vFsXCPLqYwgNJV1QuSgk.jpg', popularity=48.622097, voteCount=17, video=false, voteAverage=10.0}
//        12-18 20:36:15.888 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Post', adult=false, releaseDate='2017-12-22', genreIds=null, id=446354, originalTitle='The Post', originalLanguage='en', backdropPath='/8sb4aBST28vN3rBz704XJczS0Ld.jpg', popularity=44.793537, voteCount=0, video=false, voteAverage=0.0}
//        12-18 20:36:15.889 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='All the Money in the World', adult=false, releaseDate='2017-12-21', genreIds=null, id=446791, originalTitle='All the Money in the World', originalLanguage='en', backdropPath='/kDAIeve5RMx7iy2O5hvAIap2nH4.jpg', popularity=44.27094, voteCount=1, video=false, voteAverage=8.0}
//        12-18 20:36:15.889 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Loving Vincent', adult=false, releaseDate='2017-09-22', genreIds=null, id=339877, originalTitle='Loving Vincent', originalLanguage='en', backdropPath='/mffWnrVVK9hl6g3E0xPV0sE97HL.jpg', popularity=41.85535, voteCount=148, video=false, voteAverage=8.3}
//        12-18 20:36:15.889 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Killing of a Sacred Deer', adult=false, releaseDate='2017-10-20', genreIds=null, id=399057, originalTitle='The Killing of a Sacred Deer', originalLanguage='en', backdropPath='/854uDv6rzbwF82jOtFe931SMrRs.jpg', popularity=38.94021, voteCount=136, video=false, voteAverage=6.0}
//        12-18 20:36:15.893 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Phantom Thread', adult=false, releaseDate='2017-12-25', genreIds=null, id=400617, originalTitle='Phantom Thread', originalLanguage='en', backdropPath='/1YatKuQ0r7vKxRATwZR48PtiLOc.jpg', popularity=36.10915, voteCount=1, video=false, voteAverage=0.0}
//        12-18 20:36:15.893 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Crooked House', adult=false, releaseDate='2017-09-14', genreIds=null, id=419835, originalTitle='Crooked House', originalLanguage='en', backdropPath='/amTrzXX6ep7FYD7Wnpi70K0HnCh.jpg', popularity=34.814186, voteCount=40, video=false, voteAverage=6.7}
//        12-18 20:36:15.893 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Wind River', adult=false, releaseDate='2017-08-03', genreIds=null, id=395834, originalTitle='Wind River', originalLanguage='en', backdropPath='/iF9d73lbtDYeCsPhQmjtkEmlrYG.jpg', popularity=31.4752, voteCount=481, video=false, voteAverage=7.5}
//        12-18 20:36:15.893 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Current War', adult=false, releaseDate='2017-12-22', genreIds=null, id=418879, originalTitle='The Current War', originalLanguage='en', backdropPath='/15jjY9qU1ZohMSWyJrCy2DI5Nph.jpg', popularity=31.069195, voteCount=4, video=false, voteAverage=0.0}
//        12-18 20:36:15.894 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Ferdinand

//        12-18 20:35:54.186 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Coco - Lebendiger als das Leben', adult=false, releaseDate='2017-10-27', genreIds=null, id=354912, originalTitle='Coco', originalLanguage='en', backdropPath='/askg3SMvhqEl4OL52YuvdtY40Yb.jpg', popularity=581.8773, voteCount=505, video=false, voteAverage=7.5}
//        12-18 20:35:54.187 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Bo und der Weihnachtsstern', adult=false, releaseDate='2017-11-15', genreIds=null, id=355547, originalTitle='The Star', originalLanguage='en', backdropPath='/iJ5dkwIHQnq8dfmwSLh7dpETNhi.jpg', popularity=344.75793, voteCount=61, video=false, voteAverage=4.5}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Disaster Artist', adult=false, releaseDate='2017-12-01', genreIds=null, id=371638, originalTitle='The Disaster Artist', originalLanguage='en', backdropPath='/bAI7aPHQcvSZXvt7L11kMJdS0Gm.jpg', popularity=254.25793, voteCount=69, video=false, voteAverage=7.4}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Jumanji: Willkommen im Dschungel', adult=false, releaseDate='2017-12-08', genreIds=null, id=353486, originalTitle='Jumanji: Welcome to the Jungle', originalLanguage='en', backdropPath='/rz3TAyd5kmiJmozp3GUbYeB5Kep.jpg', popularity=202.9752, voteCount=110, video=false, voteAverage=5.8}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Wonder', adult=false, releaseDate='2017-11-13', genreIds=null, id=406997, originalTitle='Wonder', originalLanguage='en', backdropPath='/f1Jv532UM9j6zrV2XVkHL7HyyIW.jpg', popularity=191.69968, voteCount=166, video=false, voteAverage=7.5}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Shape of Water', adult=false, releaseDate='2017-12-01', genreIds=null, id=399055, originalTitle='The Shape of Water', originalLanguage='en', backdropPath='/6rhXCI4I6FRTWMka7hz39dQCiL7.jpg', popularity=136.12831, voteCount=39, video=false, voteAverage=7.2}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Darkest Hour', adult=false, releaseDate='2017-11-22', genreIds=null, id=399404, originalTitle='Darkest Hour', originalLanguage='en', backdropPath='/mBd56RoS8HiiLT1u0ZZAWFWSU3g.jpg', popularity=122.82281, voteCount=35, video=false, voteAverage=3.6}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='November Criminals', adult=false, releaseDate='2017-12-08', genreIds=null, id=317091, originalTitle='November Criminals', originalLanguage='en', backdropPath='/lyX1uZMeMNql7HG2FzNeLAItK17.jpg', popularity=111.96987, voteCount=27, video=false, voteAverage=4.3}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Wonder Wheel', adult=false, releaseDate='2017-12-01', genreIds=null, id=429189, originalTitle='Wonder Wheel', originalLanguage='en', backdropPath='/jGYeZzcAG0df2nWRLJW2CiweyyG.jpg', popularity=92.849724, voteCount=35, video=false, voteAverage=4.8}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Greatest Showman', adult=false, releaseDate='2017-12-20', genreIds=null, id=316029, originalTitle='The Greatest Showman', originalLanguage='en', backdropPath='/oPqHO43MY9ahl4eTyitSdKt9b0D.jpg', popularity=70.015366, voteCount=2, video=false, voteAverage=2.0}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Pitch Perfect 3', adult=false, releaseDate='2017-12-21', genreIds=null, id=353616, originalTitle='Pitch Perfect 3', originalLanguage='en', backdropPath='/A95XgdRdwLrsvhO2AWc8qhRDh2i.jpg', popularity=53.21202, voteCount=3, video=false, voteAverage=0.0}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Downsizing', adult=false, releaseDate='2017-12-22', genreIds=null, id=301337, originalTitle='Downsizing', originalLanguage='en', backdropPath='/5RzW9i3vFsXCPLqYwgNJV1QuSgk.jpg', popularity=48.622097, voteCount=17, video=false, voteAverage=10.0}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Post', adult=false, releaseDate='2017-12-22', genreIds=null, id=446354, originalTitle='The Post', originalLanguage='en', backdropPath='/8sb4aBST28vN3rBz704XJczS0Ld.jpg', popularity=44.793537, voteCount=0, video=false, voteAverage=0.0}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='All the Money in the World', adult=false, releaseDate='2017-12-21', genreIds=null, id=446791, originalTitle='All the Money in the World', originalLanguage='en', backdropPath='/kDAIeve5RMx7iy2O5hvAIap2nH4.jpg', popularity=44.27094, voteCount=1, video=false, voteAverage=8.0}
//        12-18 20:35:54.188 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Loving Vincent', adult=false, releaseDate='2017-09-22', genreIds=null, id=339877, originalTitle='Loving Vincent', originalLanguage='en', backdropPath='/mffWnrVVK9hl6g3E0xPV0sE97HL.jpg', popularity=41.85535, voteCount=148, video=false, voteAverage=8.3}
//        12-18 20:35:54.189 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Killing of a Sacred Deer', adult=false, releaseDate='2017-10-20', genreIds=null, id=399057, originalTitle='The Killing of a Sacred Deer', originalLanguage='en', backdropPath='/854uDv6rzbwF82jOtFe931SMrRs.jpg', popularity=38.94021, voteCount=136, video=false, voteAverage=6.0}
//        12-18 20:35:54.189 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Phantom Thread', adult=false, releaseDate='2017-12-25', genreIds=null, id=400617, originalTitle='Phantom Thread', originalLanguage='en', backdropPath='/1YatKuQ0r7vKxRATwZR48PtiLOc.jpg', popularity=36.10915, voteCount=1, video=false, voteAverage=0.0}
//        12-18 20:35:54.189 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Crooked House', adult=false, releaseDate='2017-09-14', genreIds=null, id=419835, originalTitle='Crooked House', originalLanguage='en', backdropPath='/amTrzXX6ep7FYD7Wnpi70K0HnCh.jpg', popularity=34.814186, voteCount=40, video=false, voteAverage=6.7}
//        12-18 20:35:54.189 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='Wind River', adult=false, releaseDate='2017-08-03', genreIds=null, id=395834, originalTitle='Wind River', originalLanguage='en', backdropPath='/iF9d73lbtDYeCsPhQmjtkEmlrYG.jpg', popularity=31.4752, voteCount=481, video=false, voteAverage=7.5}
//        12-18 20:35:54.189 8015-8041/com.shamildev.retro I/System.out: Result{posterPath='null', title='The Current War', adult=false, releaseDate='2017-12-22', genreIds=null, id=418879,