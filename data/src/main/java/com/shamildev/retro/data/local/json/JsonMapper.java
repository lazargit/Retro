package com.shamildev.retro.data.local.json;

import com.google.gson.JsonArray;
import com.shamildev.retro.domain.DomainObjectStorable;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by Shamil Lazar on 16.08.2018.
 */

abstract class JsonMapper {

   protected abstract <T extends DomainObjectStorable>Observable<T> map(JsonArray arrayFromString);

}
