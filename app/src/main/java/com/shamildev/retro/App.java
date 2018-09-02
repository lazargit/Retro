package com.shamildev.retro;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.shamildev.retro.data.net.NetworkManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.facebook.FacebookSdk.getApplicationContext;


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
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
//        Log.d("App","test"+BuildConfig.FACEBOOK_API_TOKEN);
//        FacebookSdk.setApplicationId(BuildConfig.FACEBOOK_API_TOKEN);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//
//        AppEventsLogger.activateApp(this);
        initialiseLogger();
        initCalligraphy();
        DaggerAppComponent.builder().create(this).inject(this);


       // boolean debug = BuildConfig.MOVIE_DB_API_TOKEN;
        networkManager.start();


    }

    private void initCalligraphy() {

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/ClanPro-Book.ttf")
                        .setFontAttrId(R.attr.fontPath)
                       // .addCustomViewWithSetTypeface(CustomViewWithTypefaceSupport.class)
                       // .addCustomStyle(TextField.class, R.attr.textFieldStyle)
                        .build());

//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//         //       .setDefaultFontPath("fonts/ClanPro-Book.ttf")
//                .setDefaultFontPath("fonts/Walkway_Bold.ttf")
//
//                .setFontAttrId(R.attr.fontPath)
//            //    .addCustomViewWithSetTypeface(CustomViewWithTypefaceSupport.class)
//             //   .addCustomStyle(TextField.class, R.attr.textFieldStyle)
//                .build()
      //  );
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
