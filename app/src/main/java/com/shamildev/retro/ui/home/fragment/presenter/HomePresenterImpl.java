package com.shamildev.retro.ui.home.fragment.presenter;

import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.shamildev.retro.R;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMultiSearch;
import com.shamildev.retro.domain.interactor.GetMyWatchList;
import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.home.fragment.adapter.ViewPagerAdapter;
import com.shamildev.retro.ui.home.fragment.view.HomePageFragment;
import com.shamildev.retro.ui.home.fragment.view.HomeView;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;

import java.io.Serializable;
import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * An implementation of {@link WatchListPresenter}.
 */
@PerFragment
final class HomePresenterImpl extends BasePresenter<HomeView> implements HomePresenter{





    private ViewPagerAdapter mViewPagerAdapter;


    @Inject
    HomePresenterImpl(HomeView view
                      ) {
        super(view);


    }



    @Override
    public void init(Serializable arguments, FragmentManager childFragmentManager) {


        HashMap<String, ResultWrapper> map = (HashMap<String, ResultWrapper> )arguments;


        ResultWrapper resultWrapper = map.get(AppConfig.NOWPLAYINGTVKEY);



        Log.d("getTMDBConfiguration", "getTMDBConfiguration"+map.size());



        if(map.size()>0) {
            mViewPagerAdapter = new ViewPagerAdapter(childFragmentManager);

            if (map.get(AppConfig.NOWPLAYINGKEY) != null) {
                mViewPagerAdapter.addFragment(HomePageFragment.withMovies(map, AppConfig.NOWPLAYINGKEY), "Kino");
            }


            if (map.get(AppConfig.NOWPLAYINGTVKEY) != null) {
                mViewPagerAdapter.addFragment(HomePageFragment.withMovies(map, AppConfig.NOWPLAYINGTVKEY), "TV");
            }

            if (map.get(AppConfig.UPCOMMINGKEY) != null) {
                mViewPagerAdapter.addFragment(HomePageFragment.withMovies(map, AppConfig.UPCOMMINGKEY), "Vorschau");
            }
            if (map.get(AppConfig.TOPRATEDKEY) != null) {
                mViewPagerAdapter.addFragment(HomePageFragment.withMovies(map, AppConfig.TOPRATEDKEY), "Beliebt");
            }

        }

        //mViewPagerAdapter.addFragment(HomePageFragment.withMovies(map,AppConfig.UPCOMMINGKEY),"Bald im Kino");


        view.initViewPager(mViewPagerAdapter);


    }









}