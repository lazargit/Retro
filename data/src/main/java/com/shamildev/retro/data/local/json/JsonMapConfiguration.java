package com.shamildev.retro.data.local.json;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.shamildev.retro.domain.models.Configuration;
import java.util.ArrayList;

import io.reactivex.Observable;
import static com.shamildev.retro.data.local.util.Util.jsonToList;

/**
 * Created by Shamil  on 16.08.2018.
 */

public class JsonMapConfiguration extends JsonMapper {

    protected Observable<Configuration> map(JsonArray arrayFromString,String language) {
        return Observable.fromIterable(arrayFromString)
                .map(jsonElement -> {
                    JsonObject provider = jsonElement.getAsJsonObject();
                    String base_url = provider.get("images").getAsJsonObject().get("base_url").toString();
                    String secure_base_url = provider.get("images").getAsJsonObject().get("secure_base_url").toString();
                    ArrayList<String> backdrop_sizes = jsonToList(provider.get("images").getAsJsonObject().get("backdrop_sizes"));
                    ArrayList<String> poster_sizes = jsonToList(provider.get("images").getAsJsonObject().get("poster_sizes"));
                    ArrayList<String> profile_sizes = jsonToList(provider.get("images").getAsJsonObject().get("profile_sizes"));
                    ArrayList<String> logo_sizes = jsonToList(provider.get("images").getAsJsonObject().get("logo_sizes"));
                    ArrayList<String> still_sizes = jsonToList(provider.get("images").getAsJsonObject().get("still_sizes"));
                    ArrayList<String> change_keys = jsonToList(provider.get("change_keys"));


                    return Configuration.builder()
                            .changeKeys(change_keys)
                            .baseUrl(base_url.replace("\"", ""))
                            .secureBaseUrl(secure_base_url.replace("\"", ""))
                            .logoSizes(logo_sizes)
                            .posterSizes(poster_sizes)
                            .backdropSizes(backdrop_sizes)
                            .profileSizes(profile_sizes)
                            .stillSizes(still_sizes)
                            .lastUpdate(0L)
                            .build();
                });
    }
}
