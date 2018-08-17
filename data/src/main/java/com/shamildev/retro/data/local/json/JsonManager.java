package com.shamildev.retro.data.local.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.shamildev.retro.domain.DomainObjectStorable;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by Shamil Lazar on 16.08.2018.
 */

public class JsonManager {

    private static final String INITJSONFILE = "initdata.json";
    private static final String CONFIGURATION = "configuration";
    private static final String GENRES_MOVIE = "genres_movie";
    private static final String GENRES_TV = "tv";



    private String json_string = "";


    @Inject
    public JsonManager() {

    }
    public String getJson_string() { return json_string; }
    public void setJson_string(String json_string) {this.json_string = json_string;}
    public Flowable<String> json_string() {
        if(this.json_string.isEmpty()) {
            return Flowable.empty();
        }else{
            return Flowable.just(this.json_string);
        }


       // return Flowable.just(this.json_string);
    }
    public Flowable<String> addJson_string(String json_string) {
        this.json_string = json_string;
        return Flowable.just(this.json_string);
    }

    public <T extends DomainObjectStorable> Flowable<T> mapJson(final String name){

        return  Flowable.defer(() -> {
            try {

                return Flowable.just(json_string)
                        .filter(str -> str.length()>0)
                        .map(s -> {
                            JsonParser jsonParser = new JsonParser();
                            return jsonParser.parse(s).getAsJsonObject().getAsJsonArray(name);
                        })

                        .flatMap(ss-> parseStringToJson(ss,name) );

            } catch (Exception e) {

                return Flowable.error(e);

            }
        });
    }

    /***
     *
     * @param <T>
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T extends DomainObjectStorable> Flowable<T> parseStringToJson(JsonArray jsonArray, String name){
        switch (name) {
            case CONFIGURATION:
                return (Flowable<T>) new JsonMapConfiguration().
                        map(jsonArray).
                        toFlowable(BackpressureStrategy.BUFFER);
            case GENRES_MOVIE:
                return (Flowable<T>) new JsonMapGenres()
                        .map(jsonArray)
                        .toFlowable(BackpressureStrategy.BUFFER);


        }
        return Flowable.empty();
    }


    public Flowable<String> flowJson_string() {
        if(getJson_string().isEmpty()){
            return Flowable.empty();
        }else{
            return Flowable.just(getJson_string());
        }

    }
}
