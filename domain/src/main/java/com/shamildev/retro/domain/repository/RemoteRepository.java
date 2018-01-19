package com.shamildev.retro.domain.repository;

import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.util.Constants;

import java.util.List;



import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Represents repository for retrieving business data.
 */
public interface RemoteRepository {


    Observable<MovieWrapper> getTestService();


    Flowable<MovieWrapper> fetchUpcomingMovies(int page);

    Flowable<Configuration> fetchConfiguration();

    Flowable<Movie> fetchMovie(int id);


    Flowable<List<Genre>> fetchGenre(Constants.MEDIA_TYPE mediaType);

   // Single<ResponseMovieDetails> gethMovieWithId(@Nonnull Integer movieId, String language, String appendToResponse);


}