package com.shamildev.retro.ui.mylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMultiSearch;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.BaseActivitySupport;
import com.shamildev.retro.ui.home.fragment.gridlist.GridListFragment;
import com.shamildev.retro.ui.home.fragment.view.HomeFragment;
import com.shamildev.retro.ui.home.fragment.watchlist.WatchListFragment;
import com.shamildev.retro.ui.mylist.fragment.view.MyListFragment;
import com.shamildev.retro.ui.widgets.Search.view.SearchResultFragment;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Schamil on 30.10.2017.
 */

public class MyListActivity extends BaseActivitySupport {

    private static final String INTENT_EXTRA_PARAM_MOVIE1 = "com.shamildev.retro.INTENT_PARAM_MOVIES1";
    private static final String INTENT_EXTRA_PARAM_MOVIE2 = "com.shamildev.retro.INTENT_PARAM_MOVIES2";
    public static TabLayout tabs;

    private Unbinder butterKnifeUnbinder;




    @Inject
    UseCaseHandler useCaseHandler;

    @Inject
    GetMultiSearch getMultiSearch;




    @Inject
    RetroImage processImageHelper;


//    @BindView(R.id.toolbar)
//    Toolbar toolBar;




//    @BindView(R.id.bottomBar)
//    BottomBar bottomNavigation;






    private HashMap<String, ResultWrapper> map;


    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MyListActivity.class);
        return intent;
    }

    public static Intent getCallingIntent(Context context, HashMap<String, ResultWrapper> map) {
        Intent intent = new Intent(context, MyListActivity.class);


        intent.putExtra(INTENT_EXTRA_PARAM_MOVIE1, map);



        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("SearchActivity","onCreate");
        super.onCreate(savedInstanceState);
        //ActivityCompat.postponeEnterTransition(this);
        setContentView(R.layout.activity_mylist);
        butterKnifeUnbinder = ButterKnife.bind(this);






        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, MyListFragment.instance(),MyListFragment.TAG)
                .addToBackStack(MyListFragment.TAG)
                .commit();

       // this.requestManager = Glide.with( this);
       //setSupportActionBar(toolBar);




     //   initializeActivity(savedInstanceState);

    }

    /**
     * Initializes this activity.
     */


    private void initializeActivity(Bundle savedInstanceState) {

        if (savedInstanceState == null) {

            this.map = (HashMap<String,ResultWrapper>)  getIntent().getSerializableExtra(INTENT_EXTRA_PARAM_MOVIE1);

//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager
//                    .beginTransaction()
//                    .add(R.id.mainFragmentContainer, new GridFragment(), GridFragment.class.getSimpleName())
//                    .commit();

          // addFragment(R.id.fragmentContainer, HomeFragment.with(map));
        // replaceFragment(R.id.fragmentContainer,HomeFragment.with(map),HomeFragment.TAG);



           // initImghHeader(map);




//        bottomNavigation.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelected(@IdRes int tabId) {
//                if (tabId == R.id.nav_home) {
//
//                    loadFragment(HomeFragment.with(map));
//                    // The tab with id R.id.tab_favorites was selected,
//                    // change your content accordingly.
//                }
//                if (tabId == R.id.nav_watchlist) {
//
//                    loadFragment(WatchListFragment.instance());
//                    // The tab with id R.id.tab_favorites was selected,
//                    // change your content accordingly.
//                }
//                if (tabId == R.id.nav_search) {
//
//                    loadFragment(SearchResultFragment.instance());
//                    // The tab with id R.id.tab_favorites was selected,
//                    // change your content accordingly.
//                }
//            }
//        });

        }
//         bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//             @Override
//             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//
//                 switch (item){
//                     case: R.string.nav_home {
//
//                     }
//                 }
//                 Log.e("ITEM",">"+item);
//                 return false;
//             }
//         });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }






    public void loadFragment(Fragment fragment){

       // appBarLayout.setExpanded(false);

        if(fragment instanceof HomeFragment){
            replaceFragment(R.id.fragmentContainer,fragment,HomeFragment.TAG);
            //replaceFragment(R.id.fragmentContainer,fragment, WatchListFragment.TAG);

        }


        if(fragment instanceof WatchListFragment){

            replaceFragment(R.id.fragmentContainer,fragment, WatchListFragment.TAG);

        }

        if(fragment instanceof GridListFragment){

            replaceFragment(R.id.fragmentContainer,fragment, GridListFragment.TAG);

        }

        if(fragment instanceof SearchResultFragment){

            replaceFragment(R.id.fragmentContainer,fragment, SearchResultFragment.TAG);

        }





    }

    private void loadImage(String imagePath, ImageView imageView){


        //this.requestManager.load("https://i.gifer.com/2Gr0.gif").into(imageView);




//        this.requestManager.
//              //  load("https://image.tmdb.org/t/p/w780/"+imagePath) //TODO dyn. image Path
//                load("http://unrealitymag.com/wp-content/uploads/2010/11/good-bad-ugly.gif")
//
//                .apply(new RequestOptions()
//
//                                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//                                .dontAnimate()
//
//
//
////                            .placeholder(R.drawable.movie1)
////                            .dontAnimate()
////
////
//                                // .override(viewHolder.getWidth(), viewHolder.getHeight()) // set exact size
//                                .centerCrop()
//                )
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        Log.e("TAG","onLoadFailed..");
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//
//                        Log.e("TAG","onResourceReady"+isFirstResource);
//                        return false;
//                    }
//                })
//
//
//
//                .into(imageView);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.menu_items,menu);
        //MenuItem menuItem = menu.findItem(R.id.app_bar_search);
       // searchView = (SearchViewWidget) MenuItemCompat.getActionView(menuItem);






//        searchView.init(getBaseContext(),
//                        new SearchViewWidget.ClickListener(){
//                                            @Override
//                                            public void onClick(View view, DomainObject domainObject) {
//
//
//                                                Log.e("<CLICK ON>","#"+domainObject.getClass().getName());
//                                            }
//
//                                            @Override
//                                            public void onLongClick(View view, DomainObject domainObject) {
//                                                Log.e("<LONG CLICK ON>","#"+domainObject);
//                                            }
//
//                                            @Override
//                                            public void onSearch(String txt) {
//                                                Log.e("<ON SEARCH>","#"+txt);
//                                                SearchResultFragment searchResultFragment = (SearchResultFragment) getFragmentByTag("SearchResultFragment");
//
//
//                                                searchResultFragment.doSearch(txt);
//
//
//                                            }
//
//                                            @Override
//                                            public void onStartSearchLoading() {
//                                                   SearchResultFragment searchResultFragment = (SearchResultFragment) getFragmentByTag("SearchResultFragment");
//
//
//                                                    searchResultFragment.startSearchLoading();
//
//
//                                            }
//
//                                            @Override
//                                            public void onEmpty() {
//                                                SearchResultFragment searchResultFragment = (SearchResultFragment) getFragmentByTag("SearchResultFragment");
//
//                                                if(searchResultFragment!=null) {
//                                                    searchResultFragment.notSearch();
//                                                }
//
//                                            }
//
//                                            @Override
//                                            public void onHide(View view) {
//                                                Log.e("<ON HIDE>","#"+view);
//
//                                                removeFragment("SearchResultFragment", R.anim.enter_from_right,R.anim.exit_to_right);
//                                                //toolBar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//
//
//                                                 //   appBarLayout.setExpanded(true);
//
//                                              //  getSupportActionBar().show();
//                                            }
//
//                                            @Override
//                                            public void onExpand(View view) {
//                                                Log.e("<ON EXPAND>","#"+view);
//                                                addFragment(R.id.fragmentContainer, SearchResultFragment.instance(),R.anim.enter_from_right,R.anim.exit_to_right);
//                                                searchView.setUpSearchObservable();
//
//                                            }
//                });









        return true;
    }


    private void toolbarSetElevation(float elevation) {
        // setElevation() only works on Lollipop

          //  toolBar.setElevation(elevation);

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
       // ((BaseViewFragmentV4)getSupportFragmentManager().findFragmentByTag(getSupportFragmentManager().getBackStackEntryAt(count - 1).getName())).onBackPressed();
    }

    public void superOnBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }
}
