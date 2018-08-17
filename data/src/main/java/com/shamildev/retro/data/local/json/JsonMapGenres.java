package com.shamildev.retro.data.local.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.util.Constants;

import io.reactivex.Observable;

/**
 * Created by Shamil  on 16.08.2018.
 */

public class JsonMapGenres extends JsonMapper {

    protected Observable<Genre> map(JsonArray arrayFromString) {
         return Observable.fromIterable(arrayFromString)
                 .map(jsonElement -> {
                     JsonObject provider = jsonElement.getAsJsonObject();
                     return Genre.builder()
                             .id(provider.get("id").getAsInt())
                             .name(provider.get("name").toString())
                             .type(Constants.MEDIA_TYPE.MOVIE.toString())
                             .lastUpdate(0L)
                             .language("DE")
                             .build();
                 });

    }

}
