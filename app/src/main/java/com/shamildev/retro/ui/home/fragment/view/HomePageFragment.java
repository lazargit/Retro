package com.shamildev.retro.ui.home.fragment.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.home.fragment.adapter.RecyclerViewPagerAdapter;
import com.shamildev.retro.ui.home.fragment.presenter.HomePagePresenter;
import com.shamildev.retro.ui.home.slideshowfragment.view.SlideShowDialogFragment;
import com.shamildev.retro.ui.layout.PreCachingGridLayoutManager;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;
import com.shamildev.retro.util.DeviceUtils;
import com.shamildev.retro.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.internal.Preconditions;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class HomePageFragment extends BaseViewFragmentV4<HomePagePresenter> implements HomePageView,SlideShowDialogFragment.OnHeadlineSelectedListener {

    private static final String PARAM_MOVIE = "param_movie";
    private static final String PARAM_MOVIE_TAG = "param_movie_tag";







    @BindView(R.id.recyclerViewPage)
    RecyclerView mRecyclerView;



    ArrayList<Movie> movieArrayList = new ArrayList<>();
    ArrayList<DomainObject> itemArrayList = new ArrayList<>();

    private Unbinder butterKnifeUnbinder;
    private PreCachingGridLayoutManager layoutManager;
    private String tag;


    public HomePageFragment() {
            Log.e("TAG","HomePageFragment");
            setRetainInstance(true);



    }

    public static HomePageFragment withMovies(HashMap<String, ResultWrapper> map, String tag) {
        final HomePageFragment homeFragment = new HomePageFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(PARAM_MOVIE, map);
        arguments.putSerializable(PARAM_MOVIE_TAG, tag);
        homeFragment.setArguments(arguments);
        return homeFragment;
    }

    /**
     * Get current movie id from fragments arguments.
     */
    private ResultWrapper currentMovieWrapper() {
        final Bundle arguments = getArguments();
        Preconditions.checkNotNull(arguments, "Fragment arguments cannot be null");
        tag = (String) arguments.getSerializable(PARAM_MOVIE_TAG);
        HashMap<String, ResultWrapper> map = (HashMap<String, ResultWrapper>) arguments.getSerializable(PARAM_MOVIE);



        return map.get(tag);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_page, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);

        initVerticalRecyclerView(mRecyclerView);
        ResultWrapper movieWrapper = currentMovieWrapper();
        Log.e("HOME",">>>>"+tag);
         presenter.init(movieWrapper,getContext(),tag);



        prepareTransitions(mRecyclerView);
        postponeEnterTransition();


        return fragmentView;



    }



    @Override
    public void setAdapter(RecyclerViewPagerAdapter recyclerViewPagerAdapter) {
        mRecyclerView.setAdapter(recyclerViewPagerAdapter);
        EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.e("HomePageFragment", "Load more " + page);
                presenter.loadMore(page);
            }
        };
        endlessRecyclerViewScrollListener.setStartPage(1);
        mRecyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);


    }


    @Override
    public void openSlideShow(ResultWrapper resultWrapper, int adapterPosition, String tag) {

        Log.e("openSlideShow:","pages "+resultWrapper.totalPages()+" size:"+resultWrapper.results().size());
        FragmentTransaction ft = childFragmentManager.beginTransaction();
        SlideShowDialogFragment dialogFragment = SlideShowDialogFragment.withMovies(resultWrapper,adapterPosition);
                                dialogFragment.addCallBack(this);

                                dialogFragment.show(ft, tag);

    }

    @Override
    public void onArticleSelected(int position) {
        Log.e("onArticleSelected:","pos "+position);
    }

    private void prepareTransitions(RecyclerView mRecyclerView) {
        setExitTransition(TransitionInflater.from(getContext())
                .inflateTransition(R.transition.grid_exit_transition));

        // A similar mapping is set at the ImagePagerFragment with a setEnterSharedElementCallback.
//        setExitSharedElementCallback(
//                new SharedElementCallback() {
//                    @Override
//                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
//                        // Locate the ViewHolder for the clicked position.
//                        RecyclerView.ViewHolder selectedViewHolder = mRecyclerView
//                                .findViewHolderForAdapterPosition(MainActivity.currentPosition);
//                        if (selectedViewHolder == null || selectedViewHolder.itemView == null) {
//                            return;
//                        }
//
//                        // Map the first shared element name to the child ImageView.
//                        sharedElements
//                                .put(names.get(0), selectedViewHolder.itemView.findViewById(R.id.card_image));
//                    }
//                });

    }


    private void initVerticalRecyclerView(RecyclerView recyclerView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        layoutManager = new PreCachingGridLayoutManager(getActivity(),2);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(layoutManager);
        } else {
            layoutManager = new PreCachingGridLayoutManager(getActivity(),4);
            recyclerView.setLayoutManager(layoutManager);
        }

        layoutManager.setExtraLayoutSpace(DeviceUtils.getScreenHeight(getActivity()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerViewPagerAdapter.RecyclerTouchListener(getContext(),recyclerView,new RecyclerViewPagerAdapter.ViewHolderListener(){

                        @Override
                        public void onLoadCompleted(ImageView view, int adapterPosition) {


                        }

                        @Override
                        public void onItemClicked(View view, int adapterPosition) {
                            presenter.onItemClick(adapterPosition);

                        }
        }));


      //  new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));


//        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
//            @Override
//            protected void loadMoreItems() {
//                isLoading = true;
//                currentPage += 1;
//
//
//                if (currentPage <= TOTAL_PAGES){
//                    isLoading = true;
//                    presenter.loadNextPage(currentPage);
//                }else{
//                    isLastPage = true;
//                }
//
//
//            }
//
//            @Override
//            public int getTotalPageCount() {
//                return TOTAL_PAGES;
//            }
//
//            @Override
//            public boolean isLastPage() {
//                return isLastPage;
//            }
//
//            @Override
//            public boolean isLoading() {
//                return isLoading;
//            }
//        });


//
//
//        // Apply over-scroll in 'advanced form' - i.e. create an instance manually.
//        mVertOverScrollEffect = new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));
//
//        // Over-scroll listeners are applied here via the mVertOverScrollEffect explicitly.
//        mVertOverScrollEffect.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
//            @Override
//            public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
//                //  mVertScrollMeasure.setText(String.valueOf((int) offset));
//            }
//        });
//        mVertOverScrollEffect.setOverScrollStateListener(new IOverScrollStateListener() {
////            private final int mDragColorTop = getResources().getColor(android.R.color.holo_red_light);
////            private final int mBounceBackColorTop = getResources().getColor(android.R.color.holo_orange_dark);
////            private final int mDragColorBottom = getResources().getColor(android.R.color.holo_purple);
////            private final int mBounceBackColorBottom = getResources().getColor(android.R.color.holo_blue_light);
//            // private final int mClearColor = mHorizScrollMeasure.getCurrentTextColor();
//
//            @Override
//            public void onOverScrollStateChange(IOverScrollDecor decor, int oldState, int newState) {
//                if (newState == STATE_DRAG_START_SIDE) {
//                    Log.e("FRAGTAG","STATE_DRAG_START_SIDE");
//                    //  mVertScrollMeasure.setTextColor(mDragColorTop);
//                } else if (newState == STATE_DRAG_END_SIDE) {
//                    Log.e("FRAGTAG","STATE_DRAG_END_SIDE");
//                    // adapter.addLoadingFooter();
//                    // mVertScrollMeasure.setTextColor(mDragColorBottom);
//                } else if (newState == STATE_BOUNCE_BACK) {
//                    Log.e("FRAGTAG","STATE_BOUNCE_BACK");
//                    //  mVertScrollMeasure.setTextColor(oldState == STATE_DRAG_START_SIDE ? mBounceBackColorTop : mBounceBackColorBottom);
//                } else {
//                    Log.e("FRAGTAG","---------------------");
//                    //  mVertScrollMeasure.setTextColor(mClearColor);
//                }
//            }
//        });
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
    public void fillList(List<Movie> results) {
        for (Movie movie:results) {
             Timber.d(movie.title());

        }

    }



}