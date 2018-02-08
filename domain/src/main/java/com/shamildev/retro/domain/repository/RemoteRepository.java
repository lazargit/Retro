package com.shamildev.retro.domain.repository;

import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.util.Constants;

import java.util.List;



import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

/**
 * Represents repository for retrieving business data.
 */
public interface RemoteRepository {


    Observable<MovieWrapper> getTestService();

    Flowable<Movie> fetchMovie(@NonNull int movieId, String appendToResponse);

    Flowable<Images> fetchImages(@NonNull int movieId);


    Flowable<MovieWrapper> fetchRecommendations(@NonNull int movieId, int page);

    Flowable<MovieWrapper> fetchSimilarMovies(@NonNull int movieId, int page);

    Flowable<MovieWrapper> fetchUpcomingMovies(int page);

    Flowable<MovieWrapper> fetchNowPlayingMovies(int page);

    Flowable<MovieWrapper> fetchTopRatedMovies(int page);



    Flowable<Configuration> fetchConfiguration();

    Flowable<List<Genre>> fetchGenre(Constants.MEDIA_TYPE mediaType);

   // Single<ResponseMovieDetails> gethMovieWithId(@Nonnull Integer movieId, String language, String appendToResponse);


}