package com.shamildev.retro.domain.repository;

import com.shamildev.retro.domain.DomainObjectStorable;
import com.shamildev.retro.domain.util.Constants;

import io.reactivex.Flowable;

/**
 * Created by Shamil Lazar on 14.08.2018.
 */

public interface FirebaseRepository {

    Flowable<DomainObjectStorable> streamJsonGenres(Constants.MEDIA_TYPE type, String language);

}
