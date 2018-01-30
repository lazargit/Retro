package com.shamildev.retro.navigation;

import android.content.Context;
import android.content.Intent;

import com.shamildev.retro.di.scope.ApplicationScope;
import com.shamildev.retro.ui.splash.SplashActivity;
import com.shamildev.retro.ui.watchlist.WatchListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */

public final class Navigator {


     /**
     * Provides methods to navigate to the different activities in the application.
     */

        @Inject
        Navigator() {
        }

        public void toHome(Context context) {
            Intent intent = new Intent(context, SplashActivity.class);
            context.startActivity(intent);
        }


        /**
         * Goes to the user details screen.
         *
         * @param context A Context needed to open the destiny activity.
         */
//        public void navigateToWatchList(Context context, int movieId) {
//            if (context != null) {
//                Intent intentToLaunch = WatchListActivity.getCallingIntent(context, movieId);
//                context.startActivity(intentToLaunch);
//            }
//        }

//        public void toMovieDetails(Context context) {
//            Intent intent = new Intent(context, HomeActivity.class);
//            context.startActivity(intent);
//        }


    }
