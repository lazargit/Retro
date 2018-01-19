package com.shamildev.retro;

import android.app.Activity;
import android.app.Application;
import android.util.Log;


import com.shamildev.retro.data.DataModule;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.domain.DomainModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;


/**
 * Created by Shamil Lazar on 13.12.2017.
 */


public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Inject
    NetworkManager networkManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseLogger();
       DaggerAppComponent.builder().create(this).inject(this);

        Log.d("App","test");
       // boolean debug = BuildConfig.MOVIE_DB_API_TOKEN;
        networkManager.start();

    }



    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }


    private void initialiseLogger() {

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree(){
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element)+" Methodname: "+element.getMethodName()+" | Line:"+element.getLineNumber();
                }
            });
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO  decide what to log in release version
                }
            });
        }
    }
}
