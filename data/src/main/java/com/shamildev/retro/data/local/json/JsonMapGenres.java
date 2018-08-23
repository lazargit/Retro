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


    private final Constants.MEDIA_TYPE type;

    public JsonMapGenres(Constants.MEDIA_TYPE type) {
        this.type = type;
    }

    protected Observable<Genre> map(JsonArray arrayFromString,String language) {
         return Observable.fromIterable(arrayFromString)
                 .map(jsonElement -> {
                     JsonObject provider = jsonElement.getAsJsonObject();
                     return Genre.builder()
                             .id(provider.get("id").getAsInt())
                             .name(provider.get("name").toString().replace("\"", ""))
                             .type(this.type.toString())
                             .lastUpdate(0L)
                             .language(language)
                             .build();
                 });

    }

}
