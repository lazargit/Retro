package com.shamildev.retro.domain.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Shamil Lazar on 22.01.2018.
 */

public class StreamJsonFile {
    private static final String UTF_8_CODING = "UTF-8";

    public static String stream(InputStream resourceAsStream){
        String s = "";
        if (resourceAsStream != null) {
            try {
                InputStream inputStream =  resourceAsStream;
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                s = new String(buffer, UTF_8_CODING);

            } catch (IOException ignore) {
                ignore.printStackTrace();
            }
        }
        return s;
    }

}
