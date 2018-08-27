package com.shamildev.retro.data.net.error;

import java.io.IOException;



public class EmptyDatasetException extends IOException {

    private final String message;

    public EmptyDatasetException(){
        this.message = "No Data";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
