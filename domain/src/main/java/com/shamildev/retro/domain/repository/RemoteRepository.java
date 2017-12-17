package com.shamildev.retro.domain.repository;

import io.reactivex.Observable;

/**
 * Represents repository for retrieving business data.
 */
public interface RemoteRepository {


    Observable<MovieWrapper> getTestService();

   // Single<ResponseMovieDetails> gethMovieWithId(@Nonnull Integer movieId, String language, String appendToResponse);


}