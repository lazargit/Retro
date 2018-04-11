package com.shamildev.retro.domain.repository;

import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.util.Constants;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;


/**
 * Created by Shamil Lazar on 17.12.2017.
 */

public interface CacheRepository {

    void saveObj(DomainObject object);

    Completable saveItemWatchList(DomainObject item);
    Flowable<List<DomainObject>> fetchWatchList();



    Flowable<Configuration> fetchConfiguration();
    Completable saveTMDbConfiguration(Configuration configuration);




    Completable saveGenre(final Genre genreModel);
    Flowable<List<Genre>> fetchGenre(final Constants.MEDIA_TYPE mediaType, final String language);
}
