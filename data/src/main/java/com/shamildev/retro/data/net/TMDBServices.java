package com.shamildev.retro.data.net;







import com.shamildev.retro.data.entity.tmdb.ConfigurationResponseEntity;
import com.shamildev.retro.data.entity.tmdb.GenresResponseEntity;
import com.shamildev.retro.data.entity.tmdb.response.CreditsResponse;
import com.shamildev.retro.data.entity.tmdb.response.ImagesResponse;
import com.shamildev.retro.data.entity.tmdb.response.MovieResponse;
import com.shamildev.retro.data.entity.tmdb.ResponseEntity;
import com.shamildev.retro.data.entity.tmdb.response.TVShowResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Schamil Mischijew on 30.10.2017.
 */

public interface TMDBServices {


   // @GET("test-mobile/iOS/json/test2.json")
   // Observable<Heroes> getHeroes();






    enum MEDIA_TYPE {

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

    String POPULARITYASC ="popularity.asc";
    String POPULARITYDESC ="popularity.desc";
    String RELEASE_DATEASC = "release_date.asc";
    String RELEASE_DATEDESC = "release_date.desc";

    enum SORTTYPE {

        POPULARITY_ASC {
            public String toString() {
                return POPULARITYASC;
            }
        },
        POPULARITY_DESC {
            public String toString() {
                return POPULARITYDESC;
            }
        },

        popularity_asc, popularity_desc


    }


    String API_KEY = "api_key";
    String QUERY = "query";
    String LANGUAGE = "language";
    String REGION = "region";
    String PAGE = "page";
    String MOVIE_ID ="movie_id";
    String TV_ID ="tv_id";
    String SORTBY ="sort_by";










    String INCLUDE_ADULT = "include_adult";
    String PRIMARY_RELEASE_DATE_GTE = "primary_release_date.gte";
    String APPEND_TO_RESPONSE = "append_to_response";
    String INCLUDE_IMAGE_LANGUAGE = "include_image_language";
    //**************************************************************************************************************************//
    /*
    *
    * MOVIE
    *
     */
    //**************************************************************************************************************************//

    //https://api.themoviedb.org/3/movie/284052?api_key=XXX
    //&append_to_response=videos%2Cimages%2Ctrailers%2Csimilar_movies%2Crelease_dates%2Cchanges%2Ccredits%2Creviews%2Ckeywords%2Clists%2Ctranslations%2Crecommendations
    @GET("3/movie/{movie_id}")
    Single<MovieResponse> fetchMovie(
            @Path(MOVIE_ID) String movieId,
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(APPEND_TO_RESPONSE) String appendToResponse,
            @Query(INCLUDE_IMAGE_LANGUAGE) String includeImageLanguage


    );

    //https://api.themoviedb.org/3/movie/155/images?api_key=XXX
    @GET("3/movie/{movie_id}/images")
    Single<ImagesResponse> fetchImages(
            @Path(MOVIE_ID) String movieId,
            @Query(API_KEY) String apikey

    );

    //https://api.themoviedb.org/3/movie/155/credits?api_key=XXX
    @GET("3/movie/{movie_id}/credits")
    Single<CreditsResponse> fetchCredits(
            @Path(MOVIE_ID) String movieId,
            @Query(API_KEY) String apikey

    );



    //https://api.themoviedb.org/3/movie/155/recommendations?api_key=XXX&language=de-DE&page=1
    @GET("3/movie/{movie_id}/recommendations")
    Single<ResponseEntity> fetchRecommendations(
            @Path(MOVIE_ID) String movieId,
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(PAGE) String page


    );


    //https://api.themoviedb.org/3/movie/155/similar?api_key=XXX&language=de-DE&page=1
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
    //**************************************************************************************************************************//
    /*
    *
    * TV
    *
    */
    //**************************************************************************************************************************//
    //https://api.themoviedb.org/3/tv/1418?api_key=XXX&language=de-DE
    @GET("3/tv/{tv_id}")
    Single<TVShowResponse> fetchTVShow(
            @Path(TV_ID) String id,
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(APPEND_TO_RESPONSE) String appendToResponse,
            @Query(INCLUDE_IMAGE_LANGUAGE) String includeImageLanguage


    );
    //https://api.themoviedb.org/3/tv/on_the_air?api_key=XXX&language=de-DE&page=1
    @GET("3/tv/on_the_air")
    Single<ResponseEntity> fetchTVonAir(
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(PAGE) String page
    );


    //https://api.themoviedb.org/3/tv/1418/images?api_key=XX
    @GET("3/tv/{tv_id}/images")
    Single<ImagesResponse> fetchTVImages(
            @Path(TV_ID) String id,
            @Query(API_KEY) String apikey

    );


    //https://api.themoviedb.org/3/tv/1418/credits?api_key=XXX
    @GET("3/tv/{tv_id}/credits")
    Single<CreditsResponse> fetchTVCredits(
            @Path(TV_ID) String id,
            @Query(API_KEY) String apikey

    );


    //https://api.themoviedb.org/3/tv/1418/recommendations?api_key=XXX&language=de-DE&page=1
    @GET("3/tv/{tv_id}/recommendations")
    Single<ResponseEntity> fetchTVRecommendations(
            @Path(TV_ID) String id,
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(PAGE) String page


    );


    //https://api.themoviedb.org/3/tv/1418/similar?api_key=XXX&language=de-DE&page=1
    @GET("3/tv/{tv_id}/similar")
    Single<ResponseEntity> fetchSimilarTVShows(
            @Path(TV_ID) String id,
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(PAGE) String page


    );
    //**************************************************************************************************************************//
    /*
    *SEARCH
    *
    */
    //**************************************************************************************************************************//
    //https://api.themoviedb.org/3/search/multi?api_key=XXX&language=de-DE&query=pitt&page=1&include_adult=false&region=de
    @GET("3/search/multi")
    Single<ResponseEntity> fetchMultiSearch(
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(QUERY) String quary,
            @Query(PAGE) String page,
            @Query(INCLUDE_ADULT) String adult,
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




   // https://api.themoviedb.org/3/person/popular?api_key=96306bc3cc12ed9ef756ba9a85628586&language=de-DE&page=1
   @GET("3/person/popular")
   Single<ResponseEntity> fetchPopularPerson(
           @Query(API_KEY) String apikey,
           @Query(LANGUAGE) String language,
           @Query(PAGE) String page


   );

//https://api.themoviedb.org/3/discover/movie?api_key=96306bc3cc12ed9ef756ba9a85628586&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte=1955-10-22&primary_release_date.lte=1966-10-22
   // https://api.themoviedb.org/3/discover/movie?api_key=96306bc3cc12ed9ef756ba9a85628586&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_people=7467
    @GET("3/discover/movie")
    Single<ResponseEntity> fetchDiscover(
            @Query(API_KEY) String apikey,
            @Query(LANGUAGE) String language,
            @Query(PAGE) String page,
            @QueryMap Map<String,Object> map




    );


}
