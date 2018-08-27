package com.shamildev.retro.ui.search.fragment.presenter;

import android.content.Context;
import android.util.Log;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.helper.DataReloading;
import com.shamildev.retro.domain.interactor.GetDiscoverSearch;
import com.shamildev.retro.domain.interactor.GetMyWatchList;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.params.Discover;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.home.fragment.adapter.ViewPagerAdapter;
import com.shamildev.retro.ui.search.fragment.model.SearchModel;
import com.shamildev.retro.ui.search.fragment.view.SearchView;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * An implementation of {@link com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter}.
 */
@PerFragment
public class SearchPresenterImpl extends BasePresenter<SearchView, SearchModel> implements SearchPresenter {



    private final GetDiscoverSearch getDiscoverSearch;
    private ViewPagerAdapter mViewPagerAdapter;

    private final UseCaseHandler useCaseHandler;
    private final GetMyWatchList getMyWatchList;


    private final DataReloading dataReloading;
    private final AppConfig appConfig;
    private List<MediaItem> mediaItems;



    @Inject
    SearchPresenterImpl(SearchView view,
                        SearchModel model,
                        AppConfig appConfig,
                        UseCaseHandler useCaseHandler,
                        GetMyWatchList getMyWatchList ,
                        GetDiscoverSearch getDiscoverSearch,
                        DataReloading dataReloading) {
        super(view,model);



        this.appConfig = appConfig;
        if (appConfig.getConfigurations() != null) {


            System.out.println("<DATACONFIGURATION" + appConfig.getConfigurations().baseUrl());
            //  this.processImageHelper.setConfigurations(appConfig.getConfigurations());

        }



        this.useCaseHandler = useCaseHandler;
        this.getMyWatchList = getMyWatchList;
        this.getDiscoverSearch = getDiscoverSearch;
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
    public void getDiscover() {
        Map<String, Object> map = new Discover.Builder()
                .with_people(3896)
                //   .or_withGenres(35)
                //.year(2015)
                .sortBy(Discover.SORT_TYPE.RELEASEDATE_ASC)
                .build().getMap();

        useCaseHandler.execute(this.getDiscoverSearch,
                GetDiscoverSearch.Params.with(map, 1),
                new DisposableSubscriber<ResultWrapper>() {
                    @Override
                    public void onNext(ResultWrapper resultWrapper) {


                        for (DomainObject item : resultWrapper.results()) {
                            MediaItem mediaItem = (MediaItem) item;
                            Log.e(" <DISCOVER> ",  mediaItem.itemTitle() +">"+mediaItem.itemTitle());
                        }


                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}