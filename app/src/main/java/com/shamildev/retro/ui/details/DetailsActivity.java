package com.shamildev.retro.ui.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.ui.common.BaseActivity;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.ui.details.fragment.view.DetailsFragment;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Shamil Lazar on 01.02.2018.
 */

public class DetailsActivity extends BaseActivitySupport {


    private static final String INTENT_EXTRA_PARAM_MOVIE_ID = "com.shamildev.retro.INTENT_PARAM_MOVIE_ID";
    private static final String INTENT_EXTRA_PARAM_TITLE = "com.shamildev.retro.INTENT_PARAM_TITLE";
    private static final String INTENT_EXTRA_PARAM_VOTEAVERAGE = "com.shamildev.retro.INTENT_PARAM_VOTEAVERAGE";
    private static final String INTENT_EXTRA_PARAM_POSTERPATH = "com.shamildev.retro.INTENT_PARAM_POSTERPATH";
    private static final String INSTANCE_STATE_PARAM_OVERVIEW = "com.shamildev.retro.INTENT_PARAM_OVERVIEW";
    private static final String INSTANCE_STATE_PARAM_GENREIDS = "com.shamildev.retro.INTENT_PARAM_GENREIDS";




    private Long movieId = 0L;

    public static Intent getCallingIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailsActivity.class);





        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {

            out = new ObjectOutputStream(bos);
            out.writeObject(movie);
            out.flush();
            byte[] yourBytes = bos.toByteArray();
            intent.putExtra(INTENT_EXTRA_PARAM_MOVIE_ID, yourBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                try {
                    bos.close();
                } catch (IOException ex) {
                    // ignore close exception
                }
        }

//        intent.putExtra(INTENT_EXTRA_PARAM_TITLE, movie.title());
//        intent.putExtra(INTENT_EXTRA_PARAM_VOTEAVERAGE, movie.voteAverage());
//        intent.putExtra(INTENT_EXTRA_PARAM_POSTERPATH, movie.posterPath());
//        intent.putExtra(INSTANCE_STATE_PARAM_OVERVIEW, movie.overview());
//        intent.putExtra(INTENT_EXTRA_PARAM_MOVIE_ID,movie.genreIds());

        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_details);
        initializeActivity(savedInstanceState);

       // addFragment(R.id.fragmentContainer,new DetailsFragment());

        Toolbar toolbar = (Toolbar) findViewById(R.id.flexible_example_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });


    }


    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {

    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}