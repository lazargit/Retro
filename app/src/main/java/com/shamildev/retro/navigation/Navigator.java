package com.shamildev.retro.navigation;

import android.content.Context;
import android.content.Intent;

import com.shamildev.retro.di.scope.ApplicationScope;
import com.shamildev.retro.ui.splash.SplashActivity;

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

//        public void toMovieDetails(Context context) {
//            Intent intent = new Intent(context, HomeActivity.class);
//            context.startActivity(intent);
//        }


    }
