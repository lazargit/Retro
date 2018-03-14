package com.shamildev.retro.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.bumptech.glide.Glide;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMultiSearch;
import com.shamildev.retro.domain.models.Movie;

import com.shamildev.retro.ui.common.BaseActivitySupport;

import com.shamildev.retro.ui.home.fragment.view.HomeFragment;
import com.shamildev.retro.ui.widgets.Search.SearchResultWidget;
import com.shamildev.retro.ui.widgets.Search.SearchViewWidget;
import com.shamildev.retro.ui.widgets.Search.view.SearchResultFragment;


import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Schamil on 30.10.2017.
 */

public class HomeActivity extends BaseActivitySupport {

    private static final String INTENT_EXTRA_PARAM_MOVIE = "com.shamildev.retro.INTENT_PARAM_MOVIES";
    private Unbinder butterKnifeUnbinder;


//    @BindView(R.id.recycler_view_search)
//    RecyclerView recyclerView;



    SearchViewWidget searchView;


    @Inject
    UseCaseHandler useCaseHandler;
    @Inject
    GetMultiSearch getMultiSearch;

    @BindView(R.id.toolbar)
    Toolbar toolBar;


    @Inject
    Glide glide;



    public static Intent getCallingIntent(Context context, ArrayList<Movie> movies) {
        Intent intent = new Intent(context, HomeActivity.class);

        intent.putExtra(INTENT_EXTRA_PARAM_MOVIE, movies);


        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("HomeActivity","onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        butterKnifeUnbinder = ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        initializeActivity(savedInstanceState);

    }

    /**
     * Initializes this activity.
     */


    private void initializeActivity(Bundle savedInstanceState) {
//        ArrayList<Movie> movies = (ArrayList<Movie>) getIntent().getSerializableExtra(INTENT_EXTRA_PARAM_MOVIE);
//        if (savedInstanceState == null) {
//
//           addFragment(R.id.fragmentContainer, HomeFragment.withMovies(movies));
//        }





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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }









}
