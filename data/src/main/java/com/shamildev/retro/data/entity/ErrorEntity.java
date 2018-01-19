package com.shamildev.retro.data.entity;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by Shamil Lazar on 16.12.2017.
 */


public  class ErrorEntity implements Entity {


    @Json(name = "status_code")
    final Integer statusCode;

    @Json(name = "status_message")
    final String statusMessage;

    final boolean success;


    final String[] errors;





    public ErrorEntity(Integer statusCode, String statusMessage, boolean success, String[] errors) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.success = success;
        this.errors = errors;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String[] getErrors() {
        return errors;
    }
}



