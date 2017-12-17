package com.shamildev.retro.data.net;



import com.shamildev.retro.data.entity.MovieWrapperEntity;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Schamil Mischijew on 30.10.2017.
 */

public interface TMDBServices {


   // @GET("test-mobile/iOS/json/test2.json")
   // Observable<Heroes> getHeroes();



    String API_KEY = "api_key";
    String QUERY = "query";
    String LANGUAGE = "language";
    String REGION = "region";
    String PAGE = "page";
    String MOVIE_ID ="movie_id";
    String INCLUDE_ADULT = "include_adult";
    String PRIMARY_RELEASE_DATE_GTE = "primary_release_date.gte";
    final String APPEND_TO_RESPONSE = "append_to_response";





    ///https://api.themoviedb.org/3/movie/upcoming?api_key=96306bc3cc12ed9ef756ba9a85628586&language=de-DE&page=1&region=de
    @GET("3/movie/upcoming")
    Single<MovieWrapperEntity> fetchUpcomingMovies(
            @Query(API_KEY) String apikey,
            @Query(PAGE) String page,
            @Query(LANGUAGE) String language

    );




}
