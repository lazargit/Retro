package com.shamildev.retro.ui.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Shamil Lazar on 01.02.2018.
 */

public class DetailsActivity extends BaseActivitySupport {



    private static final String INTENT_EXTRA_PARAM_MOVIE = "com.shamildev.retro.INTENT_PARAM_MOVIE";



    @BindView(R.id.textView_details_title_header)
    TextView titleHeader;

    @BindView(R.id.textView_details_overview)
    TextView overview;


    @BindView(R.id.flexible_example_toolbar)
    Toolbar toolBar;

    @BindView(R.id.image_details_header)
    ImageView imageHeader;



    private Long movieId = 0L;
    private ImageView image_header;
    private Unbinder butterKnifeUnbinder;

    public static Intent getCallingIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, DetailsActivity.class);

        intent.putExtra(INTENT_EXTRA_PARAM_MOVIE, movie);


        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_details);

        butterKnifeUnbinder = ButterKnife.bind(this);


       // addFragment(R.id.fragmentContainer,new DetailsFragment());




        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        initializeActivity(savedInstanceState);
    }


    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {

        Movie model = (Movie) getIntent().getSerializableExtra(INTENT_EXTRA_PARAM_MOVIE);
        Log.e("TAG",model.posterPath()+" # ");

        if(model.posterPath()!= null) {

            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/"+model.posterPath())
                    .into(imageHeader);
        }

        titleHeader.setText(model.title());
        overview.setText(model.overview());

    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}