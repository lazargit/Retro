package com.shamildev.retro.domain.repository;

import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Credits;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.GuestSession;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.TVShow;
import com.shamildev.retro.domain.util.Constants;

import java.util.List;
import java.util.Map;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * Represents repository for retrieving business data.
 */
public interface RemoteRepository {


    Observable<MovieWrapper> getTestService();

    Flowable<Configuration> fetchConfiguration();
    Flowable<List<Genre>> fetchGenre(Constants.MEDIA_TYPE mediaType);
    Flowable<GuestSession> fetchGuestSession();


    //MOVIE
    Flowable<Movie> fetchMovie(@NonNull Long movieId, String appendToResponse,String includeImageLanguage);

    Flowable<Images> fetchImages(@NonNull Long movieId);

    Flowable<Credits> fetchCredits(@NonNull Long movieId);

    Flowable<MovieWrapper> fetchRecommendations(@NonNull Long movieId, int page);

    Flowable<MovieWrapper> fetchSimilarMovies(@NonNull Long movieId, int page);

    Flowable<ResultWrapper> fetchUpcomingMovies(int page);

    Flowable<ResultWrapper> fetchNowPlayingMovies(int page);

    Flowable<ResultWrapper> fetchTopRatedMovies(int page);

    //TVSHOW
    Flowable<TVShow> fetchTVShow(Long id, String appendToResponse, String includeImageLanguage);
    Flowable<ResultWrapper> fetchNowPlayingTVShow(int page);



    /*
     *
     *SEARCH
     */
   // <V extends DomainObject> Flowable<V> fetchMultiSearch(String quary);
    Flowable<ResultWrapper> fetchMultiSearch(String quary,int page);

    Flowable<ResultWrapper> fetchPopularPerson(int page);

    Flowable<ResultWrapper> fetchDiscover(@NonNull Map<String, Object> map , int page);


    // Single<ResponseMovieDetails> gethMovieWithId(@Nonnull Integer movieId, String language, String appendToResponse);


}
