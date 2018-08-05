package com.shamildev.retro.ui.widgets.Search.view;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.home.fragment.gridlist.GridListFragment;
import com.shamildev.retro.ui.home.fragment.presenter.HomePresenter;
import com.shamildev.retro.ui.watchlist.adapter.WatchListRecycleViewAdapter;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;
import com.shamildev.retro.ui.widgets.Search.PaginationScrollListener;
import com.shamildev.retro.ui.widgets.Search.SearchResultRecycleViewAdapter;
import com.shamildev.retro.ui.widgets.Search.SearchResultWidget;
import com.shamildev.retro.ui.widgets.Search.SearchViewWidget;
import com.shamildev.retro.ui.widgets.Search.presenter.SearchResultPresenter;
import com.shamildev.retro.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.internal.Preconditions;
import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.IOverScrollStateListener;
import me.everything.android.ui.overscroll.IOverScrollUpdateListener;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;
import timber.log.Timber;

import static me.everything.android.ui.overscroll.IOverScrollState.STATE_BOUNCE_BACK;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_END_SIDE;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_START_SIDE;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class SearchResultFragment extends BaseViewFragmentV4<SearchResultPresenter> implements SearchResultView {

    public static final String TAG = SearchResultFragment.class.getSimpleName();
    private static final String PARAM_MOVIE = "param_movie";






    @Inject
    Glide glide;


    @Inject
    Application application;

    @Inject
    Navigator navigator;



//    @BindView(R.id.search_view)
//    SearchViewWidget searchViewWidget;

//    @BindView(R.id.recycler_view_searchresult)
//    RecyclerView recyclerView;


    private Unbinder butterKnifeUnbinder;
    private SearchResultRecycleViewAdapter adapter;
    private int TOTAL_PAGES = 20;
    private boolean isLastPage;
    private boolean isLoading;
    private VerticalOverScrollBounceEffectDecorator mVertOverScrollEffect;
    private int currentPage = 1;


    public SearchResultFragment() {

            setRetainInstance(true);





    }

    public void notSearch() {
        if(adapter!=null) {
            adapter.clear();
        }

    }

    public void startSearchLoading() {
        adapter.clear();
       // adapter.addLoadingFooter();
    }
    public void doSearch(String str){

        presenter.onSearch(str);
    }

    public static SearchResultFragment instance() {
        final SearchResultFragment fragment = new SearchResultFragment();
      //  Log.e("FRAGTAG",fragment.getTag());
             // fragment.getTag();

        return fragment;
    }

    public static SearchResultFragment withMovies(ArrayList<Movie> movies) {
        final SearchResultFragment fragment = new SearchResultFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(PARAM_MOVIE, movies);
        fragment.setArguments(arguments);
        return fragment;
    }

    /**
     * Get current movie id from fragments arguments.
     */
    private ArrayList<Movie> currentMovies() {
        final Bundle arguments = getArguments();
        Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
        ArrayList<Movie> movies = (ArrayList<Movie>) arguments.getSerializable(PARAM_MOVIE);
        return movies;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_searchresult, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);
        //setUpRecyclerView();
       // initVerticalRecyclerView(recyclerView);



    //    searchViewWidget.setIconifiedByDefault(false);
//        searchViewWidget.setFocusable(true);
//        searchViewWidget.setIconified(false);
//        searchViewWidget.clearFocus();
//        searchViewWidget.requestFocusFromTouch();
//        searchViewWidget.requestFocus();



//        ImageView mCloseButton = (ImageView) searchViewWidget.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
//        mCloseButton.setEnabled(false);
//        mCloseButton.setImageDrawable(null);


       // searchViewWidget.setUpSearchObservable();
        return fragmentView;



    }

    public void sharedElementTransition(View view) {

//        Pair[] pair = new Pair[3];
//        pair[0] = new Pair<View, String>(imgLogo, "logo_shared");
//        pair[1] = new Pair<View, String>(txvShared, "smartherd_shared");
//        pair[2] = new Pair<View, String>(imgProfilePic, "profile_pic_shared");
//
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pair);
//        Intent i = new Intent(MainActivity.this, SharedElementActivity.class);
//        startActivity(i, options.toBundle());
    }


    @Override
    public void showSomething(String something) {
        //someText.setText(something);
    }




    @Override
    public void makeToast(String message) {
        //showToastMessage(message);
    }



    @Override
    public void fillList(ResultWrapper results) {
    //    adapter.removeLoadingFooter();
        isLoading = false;
        adapter.addAll(results.results());

    }

    @Override
    public void addToList(List<DomainObject> result) {
      //   adapter.removeLoadingFooter();
        isLoading = false;
        adapter.addAll(result);
    }

    public void setUpRecyclerView() {

        adapter = new SearchResultRecycleViewAdapter( glide,getContext());

       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);


       // DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
       // divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.devider_gray));
        // Apply over-scroll in 'advanced form' - i.e. create an instance manually.





//        if(recyclerView != null) {
//          //  OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
//          //  recyclerView.setLayoutManager(linearLayoutManager);
//            recyclerView.addItemDecoration(divider);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setItemViewCacheSize(20);
//            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
//            recyclerView.setItemAnimator(new DefaultItemAnimator());
//            recyclerView.setAdapter(adapter);

           /* recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
                @Override
                protected void loadMoreItems() {
//                    isLoading = true;
//                    currentPage += 1;
//                    Log.e("Pagination","loadMoreItems");
//
//
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
//                            else isLastPage = true;
//                            listener.onNextPage();
//                            //  loadNextPage();
//                        }
//                    }, 1000);


                }

                @Override
                public int getTotalPageCount() {
                    return TOTAL_PAGES;
                }

                @Override
                public boolean isLastPage() {
                    return isLastPage;
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });*/






//        } else {
//            throw new IllegalStateException("No RecyclerView associated to SearchResultWidget");
//        }


    }



    private void initVerticalRecyclerView(RecyclerView recyclerView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());

        adapter = new SearchResultRecycleViewAdapter(glide, getContext());
       // final DemoRecyclerAdapterBase adapter = new DemoRecyclerAdapterVertical(new ArrayList<>(DemoContentHelper.getReverseSpectrumItems(getResources())), appInflater);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;


                if (currentPage <= TOTAL_PAGES){
                    isLoading = true;
                    presenter.loadNextPage(currentPage);
                }else{
                    isLastPage = true;
                }


            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });




        // Apply over-scroll in 'advanced form' - i.e. create an instance manually.
        mVertOverScrollEffect = new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));

        // Over-scroll listeners are applied here via the mVertOverScrollEffect explicitly.
        mVertOverScrollEffect.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
            @Override
            public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
              //  mVertScrollMeasure.setText(String.valueOf((int) offset));
            }
        });
        mVertOverScrollEffect.setOverScrollStateListener(new IOverScrollStateListener() {
//            private final int mDragColorTop = getResources().getColor(android.R.color.holo_red_light);
//            private final int mBounceBackColorTop = getResources().getColor(android.R.color.holo_orange_dark);
//            private final int mDragColorBottom = getResources().getColor(android.R.color.holo_purple);
//            private final int mBounceBackColorBottom = getResources().getColor(android.R.color.holo_blue_light);
           // private final int mClearColor = mHorizScrollMeasure.getCurrentTextColor();

            @Override
            public void onOverScrollStateChange(IOverScrollDecor decor, int oldState, int newState) {
                if (newState == STATE_DRAG_START_SIDE) {
                    Log.e("FRAGTAG","STATE_DRAG_START_SIDE");
                  //  mVertScrollMeasure.setTextColor(mDragColorTop);
                } else if (newState == STATE_DRAG_END_SIDE) {
                    Log.e("FRAGTAG","STATE_DRAG_END_SIDE");
                   // adapter.addLoadingFooter();
                   // mVertScrollMeasure.setTextColor(mDragColorBottom);
                } else if (newState == STATE_BOUNCE_BACK) {
                    Log.e("FRAGTAG","STATE_BOUNCE_BACK");
                  //  mVertScrollMeasure.setTextColor(oldState == STATE_DRAG_START_SIDE ? mBounceBackColorTop : mBounceBackColorBottom);
                } else {
                    Log.e("FRAGTAG","---------------------");
                  //  mVertScrollMeasure.setTextColor(mClearColor);
                }
            }
        });
    }



}