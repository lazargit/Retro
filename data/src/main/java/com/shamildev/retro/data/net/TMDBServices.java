package com.shamildev.retro.data.net;







import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.data.entity.tmdb.GenresResponseEntity;
import com.shamildev.retro.data.entity.tmdb.MovieDetailsResponseEntity;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Schamil Mischijew on 30.10.2017.
 */

public interface TMDBServices {


   // @GET("test-mobile/iOS/json/test2.json")
   // Observable<Heroes> getHeroes();






    public static  enum MEDIA_TYPE {

        MOVIE {
            public String toString() {
                return "movie";
            }
        },

        TV {
            public String toString() {
                return "tv";
            }
        },
        PERSON {
            public String toString() {
                return "person";
            }
        },


        movie , person, tv
    }



    public String API_KEY = "api_key";
    String QUERY = "query";
    String LANGUAGE = "language";
    String REGION = "region";
    String PAGE = "page";
    String MOVIE_ID ="movie_id";
    String INCLUDE_ADULT = "include_adult";
    String PRIMARY_RELEASE_DATE_GTE = "primary_release_date.gte";
    final String APPEND_TO_RESPONSE = "append_to_response";



    //https://api.themoviedb.org/3/movie/284052?api_key=XXX
    //&append_to_response=videos%2Cimages%2Ctrailers%2Csimilar_movies%2Crelease_dates%2Cchanges%2Ccredits%2Creviews%2Ckeywords%2Clists%2Ctranslations%2Crecommendations
    @GET("3/movie/{movie_id}")
    Single<MovieDetailsResponseEntity> fetchMovie(
            @Path(MOVIE_ID) String movieId,
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(APPEND_TO_RESPONSE) String appendToResponse

    );


    //https://api.themoviedb.org/3/movie/238/recommendations?api_key=XXX&language=de-DE&page=1
    @GET("3/movie/{movie_id}/recommendations")
    Single<ResponseEntity> fetchRecommendations(
            @Path(MOVIE_ID) String movieId,
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(PAGE) String page


    );


    //https://api.themoviedb.org/3/movie/238/similar?api_key=XXX&language=de-DE&page=1
    @GET("3/movie/{movie_id}/similar")
    Single<ResponseEntity> fetchSimilarMovies(
            @Path(MOVIE_ID) String movieId,
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(PAGE) String page


    );




    ///https://api.themoviedb.org/3/movie/upcoming?api_key=XXX&language=de-DE&page=1&region=de
    @GET("3/movie/upcoming")
    Single<ResponseEntity> fetchUpcomingMovies(
            @Query(API_KEY) String apikey,
            @Query(PAGE) String page,
            @Query(LANGUAGE) String language,
            @Query(REGION) String region


    );


    //https://api.themoviedb.org/3/movie/now_playing?api_key=XXX&language=de-DE&page=1&region=de
    @GET("3/movie/now_playing")
    Single<ResponseEntity> fetchNowPlayingMovies(
            @Query(API_KEY) String apikey,
            @Query(PAGE) String page,
            @Query(LANGUAGE) String language,
            @Query(REGION) String region
    );

    //https://api.themoviedb.org/3/movie/top_rated?api_key=XXX&language=de-DE&page=1&region=de
    @GET("3/movie/top_rated")
    Single<ResponseEntity> fetchTopRatedMovies(
            @Query(API_KEY) String apikey,
            @Query(PAGE) String page,
            @Query(LANGUAGE) String language,
            @Query(REGION) String region
    );



    //https://api.themoviedb.org/3/configuration?api_key=96306bc3cc12ed9ef756ba9a85628586"
    @GET("3/configuration")
    Single<ConfigurationResponseEntity> fetchConfiguration(
            @Query(API_KEY) String apikey

    );

    //https://api.themoviedb.org/3/genre/movie/list?api_key=96306bc3cc12ed9ef756ba9a85628586&language=en-US
    //https://api.themoviedb.org/3/genre/tv/list?api_key=96306bc3cc12ed9ef756ba9a85628586&language=en-US
    @GET("3/genre/{media}/list")
    Single<GenresResponseEntity> fetchGenre(
            @Path("media") String media,
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language
    );











}
