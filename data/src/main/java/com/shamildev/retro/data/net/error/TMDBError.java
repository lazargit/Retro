package com.shamildev.retro.data.net.error;


import org.json.JSONObject;

import java.io.IOException;

import timber.log.Timber;

public class TMDBError extends IOException {

    private static final int ERROR_CODE_401 = 401;
    private static final int ERROR_CODE_404 = 404;
    private static final int ERROR_CODE_422 = 422;

    private int responseCode;
    private int statusCode;
    private String message;
    private Boolean success;


    public static TMDBError getTMDBError(int responseCode, String rawJson) {
         int statusCode = 0;
         String message = "";
         Boolean success = false;
        Timber.d( String.valueOf(responseCode));
        if(responseCode == ERROR_CODE_401){


            try {

                JSONObject obj = new JSONObject(rawJson);
                message = obj.getString("status_message");
                statusCode = obj.getInt("status_code");


            } catch (Throwable tx) {
                Timber.d( "Could not parse JSON: \"" + rawJson + "\""+"\n>>"+message);
            }

            return new TMDBError(responseCode,message,statusCode);

        }

        if(responseCode == ERROR_CODE_422){


            try {

                JSONObject obj = new JSONObject(rawJson);
                message = obj.getString("errors");


            } catch (Throwable tx) {
                Timber.d( "Could not parse JSON: \"" + rawJson + "\"");
            }

            return new TMDBError(responseCode,message);

        }

        if(responseCode == ERROR_CODE_404){

            try {
                JSONObject obj = new JSONObject(rawJson);
                message = obj.getString("status_message");
                statusCode = obj.getInt("status_code");



            } catch (Throwable tx) {
                Timber.d( "Could not parse JSON: \"" + rawJson + "\"");
            }

            return new  TMDBError(responseCode,message,statusCode);

        }



        return new TMDBError(responseCode,message);
    }


    public TMDBError(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public TMDBError(int responseCode, String message,  int statusCode) {
        this.message = message;
        this.responseCode = responseCode;
        this.statusCode = statusCode;

    }


    public TMDBError(int responseCode, String message,  int statusCode, Boolean success) {
        this.message = message;
        this.responseCode = responseCode;
        this.statusCode = statusCode;
        this.success = success;
    }




    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Boolean getSuccess() {
        return success;
    }


}
