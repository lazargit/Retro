package com.shamildev.retro.navigation;

import android.content.Context;
import android.content.Intent;

import com.shamildev.retro.di.scope.ApplicationScope;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.details.DetailsActivity;
import com.shamildev.retro.ui.home.HomeActivity;
import com.shamildev.retro.ui.splash.SplashActivity;
import com.shamildev.retro.ui.watchlist.WatchListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            if (context != null) {
                Intent intent = new Intent(context, SplashActivity.class);
                context.startActivity(intent);
            }
        }

      public void navigateToHome(Context context, HashMap<String,ResultWrapper> map) {
        if (context != null) {
            Intent intent = HomeActivity.getCallingIntent(context, map);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }




        public void navigateToDetails(Context context, Movie movie) {
            if (context != null) {
                Intent intent = DetailsActivity.getCallingIntent(context, movie);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }


    }
