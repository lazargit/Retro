package com.shamildev.retro.data.local.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.shamildev.retro.domain.DomainObjectStorable;
import com.shamildev.retro.domain.util.Constants;

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
    private static final String GENRES_TV = "genres_tv";



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

    public <T extends DomainObjectStorable> Flowable<T> mapJson(final String name, String language){

        String finalName;
        if(language!=null){
            finalName = name+"_"+language;
        }else finalName = name;

        return  Flowable.defer(() -> {
            try {
                return Flowable.just(json_string)
                        .filter(str -> str.length()>0)
                        .map(s -> {
                            JsonParser jsonParser = new JsonParser();
                            return jsonParser.parse(s).getAsJsonObject().getAsJsonArray(finalName);
                        })

                        .flatMap(ss-> parseStringToJson(ss,name,language) );

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
    private <T extends DomainObjectStorable> Flowable<T> parseStringToJson(JsonArray jsonArray, String name,String language){

      //  Log.e("PARSE",name+"##"+jsonArray.toString());

        switch (name) {
            case CONFIGURATION:
                return (Flowable<T>) new JsonMapConfiguration().
                        map(jsonArray,language).
                        toFlowable(BackpressureStrategy.LATEST);
            case GENRES_MOVIE:
                return (Flowable<T>) new JsonMapGenres(Constants.MEDIA_TYPE.MOVIE)
                        .map(jsonArray,language)
                        .toFlowable(BackpressureStrategy.LATEST);
            case GENRES_TV:
                return (Flowable<T>) new JsonMapGenres(Constants.MEDIA_TYPE.TV)
                        .map(jsonArray,language)
                        .toFlowable(BackpressureStrategy.LATEST);


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
