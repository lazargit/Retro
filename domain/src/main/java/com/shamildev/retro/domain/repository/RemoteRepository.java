package com.shamildev.retro.domain.repository;

import com.shamildev.retro.domain.responsemodels.Response;

import io.reactivex.Observable;

/**
 * Represents repository for retrieving business data.
 */
public interface RemoteRepository {


    Observable<Response> getTestService();


}