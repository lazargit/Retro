package com.shamildev.retro.domain.repository;

import com.shamildev.retro.domain.DomainObjectStorable;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Type;
import com.shamildev.retro.domain.util.Constants;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Shamil Lazar on 14.08.2018.
 */

public interface LocalRepository {

    Flowable<DomainObjectStorable> streamJsonGenres(Constants.MEDIA_TYPE type,String language);
    Flowable<DomainObjectStorable> streamJsonCongiguration();


}
