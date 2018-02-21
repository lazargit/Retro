package com.shamildev.retro.domain.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Mohsen on 20/10/2016.
 */

public abstract class Constants {

    public static final String BASE_URL = "http://gateway.marvel.com:80/";
    public static final String BASE_URL_MDB = "https://api.themoviedb.org/";
    public static final String PUBLIC_KEY_MDB = "96306bc3cc12ed9ef756ba9a85628586";

    public static final String PUBLIC_KEY = "785eccfcbab3a76d09c8f02f12bd5ef3";
    public static final String PRIVATE_KEY = "989690d40838c28ff3c435acbdf30777ce785424";



    public static final int NETWORK_CONNECTION_TIMEOUT = 30; // 30 sec
    public static final long CACHE_SIZE = 10 * 1024 * 1024; // 10 MB
    public static final int CACHE_MAX_AGE = 2; // 2 min
    public static final int CACHE_MAX_STALE = 7; // 7 day

    public static final int CODE_OK = 200;

    public static final String PORTRAIT_XLARGE = "portrait_xlarge";
    public static final String STANDARD_XLARGE = "standard_xlarge";


    public static final int SPLASH_TIMEOUT_SEC = 3 * 1000; //3 sec

    public static final int RECYCLER_VIEW_ITEM_SPACE = 48;

    public static final int API_RETRY_COUNT = 3;


    public static final Gson GSON;

    public static final String INCLUDE_IMAGE_LANGUAGE_VALUE = "en,xx,null";


    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        GSON = gsonBuilder.create();
    }

    public static  enum MEDIA_TYPE {

        MOVIE {
            public String toString() {
                return "movie";
            }
        },

        TV {
            public String toString() {
                return "tv";
            }
        },
        PERSON {
            public String toString() {
                return "person";
            }
        },


        movie , person, tv
    }


    public static  enum APPEND_TO_RESPONSE {
//videos,images,trailers,similar_movies,release_dates,changes,credits,reviews,keywords,lists,translations,recommendations
        IMAGES {
            public String toString() {
                return "images";
            }
        },

        VIDEOS {
            public String toString() {
                return "videos";
            }
        },
        TRAILERS {
            public String toString() {
                return "trailers";
            }
        },
        SIMILAR_MOVIES {
            public String toString() {
                return "similar_movies";
            }
        },
        RELEASE_DATES {
            public String toString() {
                return "release_dates";
            }
        },
        CHANGES {
            public String toString() {
                return "changes";
            }
        },
        KEYWORDS {
            public String toString() {
                return "keywords";
            }
        },
        REVIEWS {
            public String toString() {
                return "reviews";
            }
        },
        CREDITS {
            public String toString() {
                return "credits";
            }
        },
        LISTS {
            public String toString() {
                return "lists";
            }
        },
        TRANSLATION {
            public String toString() {
                return "translations";
            }
        },
        RECOMMENDATIONS {
            public String toString() {
                return "recommendations";
            }
        }



    }



}
