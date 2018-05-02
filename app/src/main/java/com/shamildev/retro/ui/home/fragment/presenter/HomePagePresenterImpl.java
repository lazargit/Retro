package com.shamildev.retro.ui.home.fragment.presenter;

import android.content.Context;
import android.util.Log;

import com.shamildev.retro.di.scope.PerChildFragment;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.helper.DataReloading;
import com.shamildev.retro.domain.interactor.GetMultiSearch;
import com.shamildev.retro.domain.interactor.GetMyWatchList;
import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetNowPlayingTVShows;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.home.fragment.adapter.RecyclerViewPagerAdapter;
import com.shamildev.retro.ui.home.fragment.view.HomePageView;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * An implementation of {@link WatchListPresenter}.
 */
@PerChildFragment
final class HomePagePresenterImpl extends BasePresenter<HomePageView> implements HomePagePresenter {






    private final UseCaseHandler useCaseHandler;
    private final GetMyWatchList getMyWatchList;

    private final GetUpcomingMovies getUpcomingMovies;
    private final GetTopRatedMovies getTopRatedMovies;
    private final GetNowPlayingMovies getNowPlayingMovies;
    private final GetNowPlayingTVShows getNowPlayingTVShows;
    private final GetMultiSearch getMultiSearch;
    private final DataReloading dataReloading;

    private String tag;
    private int currentPage = 1;
    private ResultWrapper resultWrapper;
    private List<DomainObject> movieList=new ArrayList<>();
    private RecyclerViewPagerAdapter recyclerViewPagerAdapter;


    @Inject
    HomePagePresenterImpl(HomePageView view,
                          UseCaseHandler useCaseHandler,
                          GetMyWatchList getMyWatchList ,
                          GetUpcomingMovies getUpcomingMovies,
                          GetTopRatedMovies getTopRatedMovies,
                          GetNowPlayingMovies getNowPlayingMovies,
                          GetNowPlayingTVShows getNowPlayingTVShows,
                          GetMultiSearch getMultiSearch,
                          DataReloading dataReloading) {
        super(view);
        this.useCaseHandler = useCaseHandler;
        this.getMyWatchList = getMyWatchList;
        this.getUpcomingMovies = getUpcomingMovies;
        this.getTopRatedMovies = getTopRatedMovies;
        this.getNowPlayingMovies = getNowPlayingMovies;
        this.getNowPlayingTVShows = getNowPlayingTVShows;
        this.getMultiSearch = getMultiSearch;
        this.dataReloading = dataReloading;

    }

    @Override
    public void init(ResultWrapper resultWrapper, Context context, String tag) {

        this.resultWrapper = resultWrapper;
        this.movieList = resultWrapper.results();
        this.tag = tag;

        recyclerViewPagerAdapter = new RecyclerViewPagerAdapter((ArrayList<DomainObject>) this.movieList, context);
        view.setAdapter(recyclerViewPagerAdapter);





    }


//    @Override
//    public void onItemClicked(View view, int adapterPosition) {
//        Log.d("onItemClicked", "Pos "+adapterPosition);
//        resultWrapper.results().clear();
//        resultWrapper.results().addAll(movieList);
//        this.view.openSlideShow(resultWrapper,adapterPosition);
//    }


    @Override
    public void onItemClick(int position) {
        Log.d("onItemClick", "Pos "+position+" list:"+movieList.size());

      // resultWrapper.results().clear();

       resultWrapper = ResultWrapper.builder()
                .results(movieList)
                .totalResults(resultWrapper.totalResults())
                .totalPages(resultWrapper.totalPages())
                .page(currentPage).build();
        //resultWrapper.results().(movieList);
        Log.d("onItemClick", resultWrapper.results().size()+" > ");
       this.view.openSlideShow(resultWrapper,position,this.tag);
    }


    @Override
    public void loadMore(int page) {
        dataReloading.loadMore(page, this.tag, new DataReloading.DataReloadingListener() {
            @Override
            public void onDataLoad(ResultWrapper resultWrapper) {
                addMoreDataToList(resultWrapper);
            }

            @Override
            public void onDataError(Throwable t) {

            }

        });



    }



    private void addMoreDataToList(ResultWrapper movieWrapper){

       // List<DomainObject> list = prepareData(movieWrapper.results());
       // ProcessData.filterData(movieWrapper.results());
        //Log.e("addMoreDataToList","# "+movieList);

        if(movieWrapper.results().size()>0) {
            this.currentPage = movieWrapper.page();
            List<DomainObject> list = movieWrapper.results();
            movieList.addAll(list);
           // Log.e("addMoreDataToList","# "+list);
            recyclerViewPagerAdapter.notifyDataSetChanged();
        }




    }











}