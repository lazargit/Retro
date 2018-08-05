package com.shamildev.retro.ui.mylist.fragment.presenter;

import android.content.Context;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.helper.DataReloading;
import com.shamildev.retro.domain.interactor.GetMyWatchList;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.home.fragment.adapter.ViewPagerAdapter;
import com.shamildev.retro.ui.mylist.fragment.view.MyListView;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * An implementation of {@link com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter}.
 */
@PerFragment
public class MyListPresenterImpl extends BasePresenter<MyListView> implements MyListPresenter {



    private ViewPagerAdapter mViewPagerAdapter;

    private final UseCaseHandler useCaseHandler;
    private final GetMyWatchList getMyWatchList;


    private final DataReloading dataReloading;
    private final AppConfig appConfig;
    private List<MediaItem> mediaItems;



    @Inject
    MyListPresenterImpl(MyListView view,
                        AppConfig appConfig,
                        UseCaseHandler useCaseHandler,
                        GetMyWatchList getMyWatchList ,
                        DataReloading dataReloading) {
        super(view);



        this.appConfig = appConfig;
        if (appConfig.getConfigurations() != null) {


            System.out.println("<DATACONFIGURATION" + appConfig.getConfigurations().baseUrl());
            //  this.processImageHelper.setConfigurations(appConfig.getConfigurations());

        }



        this.useCaseHandler = useCaseHandler;
        this.getMyWatchList = getMyWatchList;
        this.dataReloading = dataReloading;




    }

    @Override
    public void init(Context context) {

        this.dataReloading.loadMyWatchList();
      //  List<DomainObject> watchList = appConfig.getWatchList();

         mediaItems = Observable.fromIterable(appConfig.getWatchList())
                .cast(MediaItem.class)
                .toList().blockingGet();

        System.out.println("WATCH LIST " + appConfig.getWatchList());
        view.fillList(mediaItems);
    }

    @Override
    public List<MediaItem> mediaItems() {
        return mediaItems;
    }
}