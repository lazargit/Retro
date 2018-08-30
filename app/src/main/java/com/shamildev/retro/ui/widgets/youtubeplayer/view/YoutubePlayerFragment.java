package com.shamildev.retro.ui.widgets.youtubeplayer.view;

import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.watchlist.fragment.view.WatchListView;
import com.shamildev.retro.ui.widgets.Search.SearchResultRecycleViewAdapter;
import com.shamildev.retro.ui.widgets.youtubeplayer.presenter.YoutubePlayerPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class YoutubePlayerFragment extends BaseViewFragmentV4<YoutubePlayerPresenter> implements YoutubePlayerView {

    public static final String TAG = YoutubePlayerFragment.class.getSimpleName();
    private static final String PARAM_MOVIE = "param_movie";






    @Inject
    Glide glide;

    @Inject
    Application application;

    @Inject
    Navigator navigator;

    @Inject
    AppConfig appConfig;

    @Inject
    DataConfig dataConfig;




    private Unbinder butterKnifeUnbinder;
    private SearchResultRecycleViewAdapter adapter;
    private int TOTAL_PAGES = 20;
    private boolean isLastPage;
    private boolean isLoading;
    private VerticalOverScrollBounceEffectDecorator mVertOverScrollEffect;
    private int currentPage = 1;
    private YouTubePlayer YPlayer;
    private static final int RECOVERY_DIALOG_REQUEST = 1;


    public YoutubePlayerFragment() {
            setRetainInstance(true);
    }

    public static YoutubePlayerFragment instance() {
        final YoutubePlayerFragment fragment = new YoutubePlayerFragment();
        return fragment;
    }

//    public static YoutubePlayerFragment withMovies(ArrayList<Movie> movies) {
//        final YoutubePlayerFragment fragment = new YoutubePlayerFragment();
//        final Bundle arguments = new Bundle();
//        arguments.putSerializable(PARAM_MOVIE, movies);
//        fragment.setArguments(arguments);
//        return fragment;
//    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_youtubeplayer, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(dataConfig.youtubeKey(), new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    YPlayer = youTubePlayer;
                    YPlayer.setFullscreen(false);

                    YPlayer.loadVideo("-AvUavKYfcw");
                    YPlayer.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {


            }
        });

        return fragmentView;



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




    }



    @Override
    public void play() {

    }

    @Override
    public void makeToast(String message) {
        //showToastMessage(message);
    }





}