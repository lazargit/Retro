package com.shamildev.retro.ui.home.fragment.presenter;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.models.ResultWrapper;

import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.home.fragment.adapter.ViewPagerAdapter;
import com.shamildev.retro.ui.home.fragment.view.GridFragment;
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


    private final RetroImage retroImage;
    private ViewPagerAdapter mViewPagerAdapter;


    @Inject
    HomePresenterImpl(HomeView view,RetroImage retroImage) {
        super(view);
        this.retroImage = retroImage;



    }



    @Override
    public void init(Serializable arguments, FragmentManager childFragmentManager) {


        HashMap<String, ResultWrapper> map = (HashMap<String, ResultWrapper> )arguments;


        ResultWrapper resultWrapper = map.get(AppConfig.NOWPLAYINGTVKEY);



        Log.d("getTMDBConfiguration", "getTMDBConfiguration"+map.size());






        if(map.size()>0) {
            mViewPagerAdapter = new ViewPagerAdapter(childFragmentManager);




        mViewPagerAdapter.addFragment(new GridFragment(), "Test");
        mViewPagerAdapter.notifyDataSetChanged();

            if (map.get(AppConfig.NOWPLAYINGKEY) != null) {
          //      mViewPagerAdapter.addFragment(HomePageFragment.withMovies(map, AppConfig.NOWPLAYINGKEY), "Kino");
            }
//
//
//            if (map.get(AppConfig.NOWPLAYINGTVKEY) != null) {
//                mViewPagerAdapter.addFragment(HomePageFragment.withMovies(map, AppConfig.NOWPLAYINGTVKEY), "TV");
//            }
//
//            if (map.get(AppConfig.UPCOMMINGKEY) != null) {
//                mViewPagerAdapter.addFragment(HomePageFragment.withMovies(map, AppConfig.UPCOMMINGKEY), "Vorschau");
//            }
//            if (map.get(AppConfig.TOPRATEDKEY) != null) {
//                mViewPagerAdapter.addFragment(HomePageFragment.withMovies(map, AppConfig.TOPRATEDKEY), "Beliebt");
//            }

        }

        //mViewPagerAdapter.addFragment(HomePageFragment.withMovies(map,AppConfig.UPCOMMINGKEY),"Bald im Kino");


        view.initViewPager(mViewPagerAdapter);


    }









}