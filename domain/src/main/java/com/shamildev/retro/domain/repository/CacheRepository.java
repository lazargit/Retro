package com.shamildev.retro.domain.repository;

import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.util.Constants;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;


/**
 * Created by Shamil
 */

public interface CacheRepository {

    Flowable<User> fetchUser();

    Completable saveUser(DomainObject item);

    void saveObj(DomainObject object);

    Completable saveItemWatchList(DomainObject item);
    Flowable<List<DomainObject>> fetchWatchList();



    Flowable<Configuration> fetchConfiguration();
    Completable saveTMDbConfiguration(Configuration configuration);
    Completable insertTMDbConfiguration(Configuration configuration,Long date);




    Completable saveGenre(final Genre genreModel);
    Flowable<List<Genre>> fetchGenre(final Constants.MEDIA_TYPE mediaType, final String language);
}
