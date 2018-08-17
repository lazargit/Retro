package com.shamildev.retro.data.local.load;

import android.app.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by Shamil Lazar on 16.08.2018.
 */


public class StreamFileFromLocal implements StreamFromLocal {



    private Application application;


    @Inject
    public StreamFileFromLocal(Application application) {
        this.application = application;
    }


    /***
     * load json file from local assets folder to a String
     * @param filename
     * @param local
     * @return String
     */
    public Flowable<String> streamJsonFile(String filename, String local){

        return Flowable.create(e -> {
                BufferedReader reader = null;
                Writer writer = new StringWriter();
                try {
                    reader = new BufferedReader(
                            new InputStreamReader(application.getApplicationContext().getAssets().open(filename)));
                    String mLine;
                    while ((mLine = reader.readLine()) != null) {
                        writer.write(mLine);
                    }
                } catch (IOException error) {
                    e.onError(error);
                } finally {
                    if (reader != null) {
                        try {
                            e.onNext(writer.toString());
                            reader.close();
                        } catch (IOException error) {
                            e.onError(error);

                        }
                    }
                }
            e.onComplete();
        }, BackpressureStrategy.BUFFER);
    }


}
