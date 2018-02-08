package com.shamildev.retro.domain.testdata;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.Images;
import com.shamildev.retro.domain.util.Constants;
import com.shamildev.retro.domain.util.DateUtil;
import com.shamildev.retro.domain.utils.JsonParsingRule;
import com.shamildev.retro.domain.utils.StreamJsonFile;

import org.junit.Rule;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Created by Shamil Lazar on 16.01.2018.
 */

//public abstract class TestData<REQUEST_DATA, RESPONSE_DATA, REPOSITORY extends Repository> {
public  class TestData {

    public static final Flowable<Genre> GENRE_CACHE_SUCCESS = getGenreResponseJsonData();

    @Rule
    public static JsonParsingRule jsonParsingRule = new JsonParsingRule(Constants.GSON);






    public static Flowable<Genre> getGenreResponseJsonData() {




        return Flowable.empty();
    }



    public static Flowable<List<Genre>> REPO_GENRE_MOVIE_DATA(ClassLoader classLoader, String language){
        InputStream inputStream = classLoader.getResourceAsStream("GenreMovieTestData.json");
        String s = StreamJsonFile.stream(inputStream);
        if(s.length()>0){
            JsonParser jsonParser = new JsonParser();
            JsonArray arrayFromString = jsonParser.parse(s).getAsJsonArray();
            List<Genre> genres = Observable.fromIterable(arrayFromString)
                    .map(new Function<JsonElement, Genre>() {
                        @Override
                        public Genre apply(JsonElement jsonElement) throws Exception {

                            JsonObject provider = jsonElement.getAsJsonObject();
                            return Genre.builder()
                                    .id(provider.get("id").getAsInt())
                                    .name(provider.get("name").toString())
                                    .type(Constants.MEDIA_TYPE.MOVIE.toString())
                                    .lastUpdate(0L)
                                    .language(language)
                                    .build();

                        }
                    }).toList().blockingGet();

            return Flowable.fromArray(genres);

        }

    return Flowable.empty();


    }


    public static Flowable<List<Genre>> CACHE_GENRE_MOVIE_DATA(ClassLoader classLoader, String language){
        InputStream inputStream = classLoader.getResourceAsStream("GenreMovieTestData.json");
        String s = StreamJsonFile.stream(inputStream);
        if(s.length()>0){
            JsonParser jsonParser = new JsonParser();
            JsonArray arrayFromString = jsonParser.parse(s).getAsJsonArray();
            List<Genre> genres = Observable.fromIterable(arrayFromString)
                    .map(new Function<JsonElement, Genre>() {
                        @Override
                        public Genre apply(JsonElement jsonElement) throws Exception {

                            JsonObject provider = jsonElement.getAsJsonObject();
                            return Genre.builder()
                                    .id(provider.get("id").getAsInt())
                                    .name(provider.get("name").toString())
                                    .type(Constants.MEDIA_TYPE.MOVIE.toString())
                                    .lastUpdate(DateUtil.NOW()-190000)
                                    .language(language)
                                    .build();

                        }
                    }).toList().blockingGet();

            return Flowable.fromArray(genres);

        }

        return Flowable.empty();


    }

    public static Flowable<List<Genre>> REPO_GENRE_TV_DATA(ClassLoader classLoader, String language){
        InputStream inputStream = classLoader.getResourceAsStream("GenreTvTestData.json");
        String s = StreamJsonFile.stream(inputStream);
        if(s.length()>0){
            JsonParser jsonParser = new JsonParser();
            JsonArray arrayFromString = jsonParser.parse(s).getAsJsonArray();
            List<Genre> genres = Observable.fromIterable(arrayFromString)
                    .map(new Function<JsonElement, Genre>() {
                        @Override
                        public Genre apply(JsonElement jsonElement) throws Exception {

                            JsonObject provider = jsonElement.getAsJsonObject();
                            return Genre.builder()
                                    .id(provider.get("id").getAsInt())
                                    .name(provider.get("name").toString())
                                    .type(Constants.MEDIA_TYPE.TV.toString())
                                    .lastUpdate(0L)
                                    .language(language)
                                    .build();

                        }
                    }).toList().blockingGet();

            return Flowable.fromArray(genres);

        }

        return Flowable.empty();


    }

    public static Flowable<List<Genre>> CACHE_GENRE_TV_DATA(ClassLoader classLoader, String language){
        InputStream inputStream = classLoader.getResourceAsStream("GenreTvTestData.json");
        String s = StreamJsonFile.stream(inputStream);
        if(s.length()>0){
            JsonParser jsonParser = new JsonParser();
            JsonArray arrayFromString = jsonParser.parse(s).getAsJsonArray();
            List<Genre> genres = Observable.fromIterable(arrayFromString)
                    .map(new Function<JsonElement, Genre>() {
                        @Override
                        public Genre apply(JsonElement jsonElement) throws Exception {

                            JsonObject provider = jsonElement.getAsJsonObject();
                            return Genre.builder()
                                    .id(provider.get("id").getAsInt())
                                    .name(provider.get("name").toString())
                                    .type(Constants.MEDIA_TYPE.TV.toString())
                                    .lastUpdate(DateUtil.NOW()-190500)
                                    .language(language)
                                    .build();

                        }
                    }).toList().blockingGet();

            return Flowable.fromArray(genres);

        }

        return Flowable.empty();


    }


    public static Flowable<Configuration> REPO_TMDB_CONFIGURATION_DATA(ClassLoader classLoader){
        InputStream inputStream = classLoader.getResourceAsStream("TMDBConfigurationTestData.json");
        String s = StreamJsonFile.stream(inputStream);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(s).getAsJsonObject();


        Configuration configuration = Observable.just(jsonObject)
                .map(jsonObject1 -> {


                    JsonArray change_keys = jsonObject1.get("change_keys").getAsJsonArray();
                    JsonArray logoSizes = jsonObject1.get("images").getAsJsonObject().get("logo_sizes").getAsJsonArray();
                    JsonArray posterSizes = jsonObject1.get("images").getAsJsonObject().get("poster_sizes").getAsJsonArray();
                    JsonArray backdropSizes = jsonObject1.get("images").getAsJsonObject().get("backdrop_sizes").getAsJsonArray();
                    JsonArray profileSizes = jsonObject1.get("images").getAsJsonObject().get("profile_sizes").getAsJsonArray();
                    JsonArray stillSizes = jsonObject1.get("images").getAsJsonObject().get("still_sizes").getAsJsonArray();




                    List<String> list_change_keys = new ArrayList<>();
                    change_keys.forEach(jsonElement -> list_change_keys.add(jsonElement.getAsString()));

                    List<String> list_logoSizes = new ArrayList<>();
                    logoSizes.forEach(jsonElement -> list_logoSizes.add(jsonElement.getAsString()));

                    List<String> list_posterSizes = new ArrayList<>();
                    posterSizes.forEach(jsonElement -> list_posterSizes.add(jsonElement.getAsString()));

                    List<String> list_backdropSizes = new ArrayList<>();
                    backdropSizes.forEach(jsonElement -> list_backdropSizes.add(jsonElement.getAsString()));

                    List<String> list_profileSizes = new ArrayList<>();
                    profileSizes.forEach(jsonElement -> list_profileSizes.add(jsonElement.getAsString()));

                    List<String> list_stillSizes = new ArrayList<>();
                    stillSizes.forEach(jsonElement -> list_stillSizes.add(jsonElement.getAsString()));






                    return Configuration.builder()
                            .changeKeys(list_change_keys)
                            .baseUrl(jsonObject1.get("images").getAsJsonObject().get("base_url").getAsString())
                            .secureBaseUrl(jsonObject1.get("images").getAsJsonObject().get("secure_base_url").getAsString())
                            .logoSizes(list_logoSizes)
                            .posterSizes(list_posterSizes)
                            .backdropSizes(list_backdropSizes)
                            .profileSizes(list_profileSizes)
                            .stillSizes(list_stillSizes)
                            .lastUpdate(0L)
                            .build();


                }).blockingSingle();

       return Flowable.just(configuration);
    }


    public static Flowable<Configuration> CACHE_TMDB_CONFIGURATION_DATA(ClassLoader classLoader,int div_m){

        long millis = div_m * 60 * 1000;
        long past_time = (DateUtil.NOW() - (millis));

        InputStream inputStream = classLoader.getResourceAsStream("TMDBConfigurationTestData.json");
        String s = StreamJsonFile.stream(inputStream);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(s).getAsJsonObject();


        Configuration configuration = Observable.just(jsonObject)
                .map(jsonObject1 -> {


                    JsonArray change_keys = jsonObject1.get("change_keys").getAsJsonArray();
                    JsonArray logoSizes = jsonObject1.get("images").getAsJsonObject().get("logo_sizes").getAsJsonArray();
                    JsonArray posterSizes = jsonObject1.get("images").getAsJsonObject().get("poster_sizes").getAsJsonArray();
                    JsonArray backdropSizes = jsonObject1.get("images").getAsJsonObject().get("backdrop_sizes").getAsJsonArray();
                    JsonArray profileSizes = jsonObject1.get("images").getAsJsonObject().get("profile_sizes").getAsJsonArray();
                    JsonArray stillSizes = jsonObject1.get("images").getAsJsonObject().get("still_sizes").getAsJsonArray();




                    List<String> list_change_keys = new ArrayList<>();
                    change_keys.forEach(jsonElement -> list_change_keys.add(jsonElement.getAsString()));

                    List<String> list_logoSizes = new ArrayList<>();
                    logoSizes.forEach(jsonElement -> list_logoSizes.add(jsonElement.getAsString()));

                    List<String> list_posterSizes = new ArrayList<>();
                    posterSizes.forEach(jsonElement -> list_posterSizes.add(jsonElement.getAsString()));

                    List<String> list_backdropSizes = new ArrayList<>();
                    backdropSizes.forEach(jsonElement -> list_backdropSizes.add(jsonElement.getAsString()));

                    List<String> list_profileSizes = new ArrayList<>();
                    profileSizes.forEach(jsonElement -> list_profileSizes.add(jsonElement.getAsString()));

                    List<String> list_stillSizes = new ArrayList<>();
                    stillSizes.forEach(jsonElement -> list_stillSizes.add(jsonElement.getAsString()));






                    return Configuration.builder()
                            .changeKeys(list_change_keys)
                            .baseUrl(jsonObject1.get("images").getAsJsonObject().get("base_url").getAsString())
                            .secureBaseUrl(jsonObject1.get("images").getAsJsonObject().get("secure_base_url").getAsString())
                            .logoSizes(list_logoSizes)
                            .posterSizes(list_posterSizes)
                            .backdropSizes(list_backdropSizes)
                            .profileSizes(list_profileSizes)
                            .stillSizes(list_stillSizes)
                            .lastUpdate(past_time)
                            .build();


                }).blockingSingle();

        return Flowable.just(configuration);
    }


    public static Flowable<Images> REPO_IMAGES_DATA(ClassLoader classLoader){
        InputStream inputStream = classLoader.getResourceAsStream("ImagesResponseTestData.json");
        String s = StreamJsonFile.stream(inputStream);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(s).getAsJsonObject();


        Configuration configuration = Observable.just(jsonObject)
                .map(jsonObject1 -> {


                    JsonArray change_keys = jsonObject1.get("change_keys").getAsJsonArray();
                    JsonArray logoSizes = jsonObject1.get("images").getAsJsonObject().get("logo_sizes").getAsJsonArray();
                    JsonArray posterSizes = jsonObject1.get("images").getAsJsonObject().get("poster_sizes").getAsJsonArray();
                    JsonArray backdropSizes = jsonObject1.get("images").getAsJsonObject().get("backdrop_sizes").getAsJsonArray();
                    JsonArray profileSizes = jsonObject1.get("images").getAsJsonObject().get("profile_sizes").getAsJsonArray();
                    JsonArray stillSizes = jsonObject1.get("images").getAsJsonObject().get("still_sizes").getAsJsonArray();




                    List<String> list_change_keys = new ArrayList<>();
                    change_keys.forEach(jsonElement -> list_change_keys.add(jsonElement.getAsString()));

                    List<String> list_logoSizes = new ArrayList<>();
                    logoSizes.forEach(jsonElement -> list_logoSizes.add(jsonElement.getAsString()));

                    List<String> list_posterSizes = new ArrayList<>();
                    posterSizes.forEach(jsonElement -> list_posterSizes.add(jsonElement.getAsString()));

                    List<String> list_backdropSizes = new ArrayList<>();
                    backdropSizes.forEach(jsonElement -> list_backdropSizes.add(jsonElement.getAsString()));

                    List<String> list_profileSizes = new ArrayList<>();
                    profileSizes.forEach(jsonElement -> list_profileSizes.add(jsonElement.getAsString()));

                    List<String> list_stillSizes = new ArrayList<>();
                    stillSizes.forEach(jsonElement -> list_stillSizes.add(jsonElement.getAsString()));






                    return Configuration.builder()
                            .changeKeys(list_change_keys)
                            .baseUrl(jsonObject1.get("images").getAsJsonObject().get("base_url").getAsString())
                            .secureBaseUrl(jsonObject1.get("images").getAsJsonObject().get("secure_base_url").getAsString())
                            .logoSizes(list_logoSizes)
                            .posterSizes(list_posterSizes)
                            .backdropSizes(list_backdropSizes)
                            .profileSizes(list_profileSizes)
                            .stillSizes(list_stillSizes)
                            .lastUpdate(0L)
                            .build();


                }).blockingSingle();

        return Flowable.empty();
    }



}
