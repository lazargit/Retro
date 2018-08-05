package com.shamildev.retro.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.BaseActivitySupport;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Schamil on 30.10.2017.
 */

public class GalleryActivity extends BaseActivitySupport {

    private static final String INTENT_EXTRA_PARAM_MOVIE1 = "com.shamildev.retro.INTENT_PARAM_MOVIES1";
    private static final String INTENT_EXTRA_PARAM_MOVIE2 = "com.shamildev.retro.INTENT_PARAM_MOVIES2";
    public static TabLayout tabs;

    private Unbinder butterKnifeUnbinder;


//    @BindView(R.id.recycler_view_search)
//    RecyclerView recyclerView;

    // The elevation of the toolbar when content is scrolled behind
    private static final float TOOLBAR_ELEVATION = 14f;
    // To save/restore recyclerview state on configuration changes
    private static final String STATE_RECYCLER_VIEW = "state-recycler-view";
    private static final String STATE_VERTICAL_OFFSET = "state-vertical-offset";
    private static final String STATE_SCROLLING_OFFSET = "state-scrolling-direction";
    private static final String STATE_TOOLBAR_ELEVATION = "state-toolbar-elevation";
    private static final String STATE_TOOLBAR_TRANSLATION_Y = "state-toolbar-translation-y";
    // Keeps track of the overall vertical offset in the list
    private int verticalOffset;
    // Determines the scroll UP/DOWN offset
    private int scrollingOffset;







    @Inject
    RetroImage retroImage;






    public static Intent getCallingIntent(Context context, HashMap<String, ResultWrapper> map) {
        Intent intent = new Intent(context, GalleryActivity.class);


        intent.putExtra(INTENT_EXTRA_PARAM_MOVIE1, map);



        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MyListActivity","onCreate");
        super.onCreate(savedInstanceState);
        ActivityCompat.postponeEnterTransition(this);
        setContentView(R.layout.activity_gallery);
        butterKnifeUnbinder = ButterKnife.bind(this);

       // this.requestManager = Glide.with( this);
       // setSupportActionBar(toolBar);


        initializeActivity(savedInstanceState);

    }

    /**
     * Initializes this activity.
     */


    private void initializeActivity(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            @SuppressWarnings("unchecked")
            HashMap<String, ResultWrapper> map = (HashMap<String,ResultWrapper>)  getIntent().getSerializableExtra(INTENT_EXTRA_PARAM_MOVIE1);


        }
    }



    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void onBackPressed() {
        Log.e("HOME",">"+fragmentManager.getBackStackEntryCount());
        Log.e("HOME",">"+getSupportFragmentManager().getBackStackEntryCount());
        if(getSupportFragmentManager().getBackStackEntryCount() > 1) {

            triggerFragmentBackPress(getSupportFragmentManager().getBackStackEntryCount());
        } else {
            finish();
        }
    }
    private void triggerFragmentBackPress(final int count) {
        Log.e("HOME",">"+getSupportFragmentManager().getBackStackEntryAt(count - 1).getName());

       // replaceFragment(R.id.fragmentContainer,HomeFragment.with(map),HomeFragment.TAG);

       // replaceFragment(R.id.fragmentContainer,fragment,fragment.TAG);
        //((MvpFragment)getSupportFragmentManager().findFragmentByTag(getSupportFragmentManager().getBackStackEntryAt(count - 1).getName())).onBackPressed();
    }

    public void superOnBackPressed() {
        super.onBackPressed();
    }



}
