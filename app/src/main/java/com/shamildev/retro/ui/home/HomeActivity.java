package com.shamildev.retro.ui.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TableLayout;


import com.bumptech.glide.Glide;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMultiSearch;
import com.shamildev.retro.domain.models.Movie;

import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.BaseActivitySupport;

import com.shamildev.retro.ui.home.fragment.adapter.ViewPagerAdapter;
import com.shamildev.retro.ui.home.fragment.view.HomeFragment;
import com.shamildev.retro.ui.home.fragment.view.HomePageFragment;
import com.shamildev.retro.ui.widgets.Search.SearchResultWidget;
import com.shamildev.retro.ui.widgets.Search.SearchViewWidget;
import com.shamildev.retro.ui.widgets.Search.view.SearchResultFragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Schamil on 30.10.2017.
 */

public class HomeActivity extends BaseActivitySupport {

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

    SearchViewWidget searchView;


    @Inject
    UseCaseHandler useCaseHandler;
    @Inject
    GetMultiSearch getMultiSearch;

    @BindView(R.id.toolbar)
    Toolbar toolBar;

    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;


    @BindView(R.id.tabs)
    TabLayout tabLayout;





    @Inject
    Glide glide;



    public static Intent getCallingIntent(Context context, HashMap<String, ResultWrapper> map) {
        Intent intent = new Intent(context, HomeActivity.class);


        intent.putExtra(INTENT_EXTRA_PARAM_MOVIE1, map);



        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("HomeActivity","onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        butterKnifeUnbinder = ButterKnife.bind(this);
        tabs = tabLayout;
        setSupportActionBar(toolBar);


        initializeActivity(savedInstanceState);

    }

    /**
     * Initializes this activity.
     */


    private void initializeActivity(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            HashMap<String, ResultWrapper> hashMap = (HashMap<String,ResultWrapper>)  getIntent().getSerializableExtra(INTENT_EXTRA_PARAM_MOVIE1);

        addFragment(R.id.fragmentContainer, HomeFragment.withMovies(hashMap,tabLayout));
        }

        Log.e("HomeActivity","onCreate");





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("HomeActivity","onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_items,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchViewWidget) MenuItemCompat.getActionView(menuItem);






        searchView.init(getBaseContext(),
                        new SearchViewWidget.ClickListener(){
                                            @Override
                                            public void onClick(View view, DomainObject domainObject) {


                                                Log.e("<CLICK ON>","#"+domainObject.getClass().getName());
                                            }

                                            @Override
                                            public void onLongClick(View view, DomainObject domainObject) {
                                                Log.e("<LONG CLICK ON>","#"+domainObject);
                                            }

                                            @Override
                                            public void onSearch(String txt) {
                                                Log.e("<ON SEARCH>","#"+txt);
                                                SearchResultFragment searchResultFragment = (SearchResultFragment) getFragmentByTag("SearchResultFragment");


                                                searchResultFragment.doSearch(txt);


                                            }

                                            @Override
                                            public void onStartSearchLoading() {
                                                SearchResultFragment searchResultFragment = (SearchResultFragment) getFragmentByTag("SearchResultFragment");


                                                    searchResultFragment.startSearchLoading();


                                            }

                                            @Override
                                            public void onEmpty() {
                                                SearchResultFragment searchResultFragment = (SearchResultFragment) getFragmentByTag("SearchResultFragment");

                                                if(searchResultFragment!=null) {
                                                    searchResultFragment.notSearch();
                                                }

                                            }

                                            @Override
                                            public void onHide(View view) {
                                                Log.e("<ON HIDE>","#"+view);

                                                removeFragment("SearchResultFragment", R.anim.enter_from_right,R.anim.exit_to_right);
                                                //toolBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();


                                                    appBarLayout.setExpanded(true);

                                              //  getSupportActionBar().show();
                                            }

                                            @Override
                                            public void onExpand(View view) {
                                                Log.e("<ON EXPAND>","#"+view);
                                                addFragment(R.id.fragmentContainer, SearchResultFragment.with(),R.anim.enter_from_right,R.anim.exit_to_right);
                                               searchView.setUpSearchObservable();

                                            }
                });









        return true;
    }


    private void toolbarSetElevation(float elevation) {
        // setElevation() only works on Lollipop

            toolBar.setElevation(elevation);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }









}
