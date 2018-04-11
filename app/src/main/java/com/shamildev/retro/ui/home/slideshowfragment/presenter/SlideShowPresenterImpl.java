package com.shamildev.retro.ui.home.slideshowfragment.presenter;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.Pair;

import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.config.AppConfig;
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
import com.shamildev.retro.ui.home.slideshowfragment.adapter.SlideShowPageAdapter;
import com.shamildev.retro.ui.home.slideshowfragment.view.SlideShowView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar on 23.03.2018.
 */


@PerFragment
public class SlideShowPresenterImpl extends BasePresenter<SlideShowView> implements SlideShowPresenter {


    private SlideShowPageAdapter myViewPagerAdapter;
    private int mTotalPages;
    private int mTotalResults;
    private List<DomainObject> mResults;
    private Integer mPosition;
    private String mTag;
    private int mCurrentPage;
    private boolean mOnLoad = false;
    private final UseCaseHandler useCaseHandler;
    private final GetMyWatchList getMyWatchList;
    private final GetUpcomingMovies getUpcomingMovies;
    private final GetTopRatedMovies getTopRatedMovies;
    private final GetNowPlayingMovies getNowPlayingMovies;
    private final GetNowPlayingTVShows getNowPlayingTVShows;
    private final GetMultiSearch getMultiSearch;
    private final DataReloading dataReloading;


    @Inject
    SlideShowPresenterImpl(SlideShowView view,
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
    public void init(Pair<ResultWrapper, Integer> bundleData, FragmentActivity activity, String tag) {



        ResultWrapper resultWrapper = bundleData.first;
        mTotalPages = resultWrapper.totalPages();
        mTotalResults = resultWrapper.totalResults();
        mCurrentPage = resultWrapper.page();
        mResults = resultWrapper.results();
        mPosition = bundleData.second;
        mTag = tag;

        myViewPagerAdapter = new SlideShowPageAdapter(activity,mResults);
        view.initViewPager(myViewPagerAdapter,mPosition);



    }

    @Override
    public void toPosition(int position) {
        Log.e("SlideShowPresenterImpl","PAGE "+mCurrentPage+" mTotalPages: "+mTotalPages+" positon: "+position+" / "+mResults.size());
        if(position>(mResults.size()/2) && mCurrentPage<mTotalPages && mOnLoad == false){
            Log.e("SlideShowPresenterImpl","LOAD NEW PAGE:  "+(mCurrentPage+1));
            mOnLoad = true;
            loadMore((mCurrentPage+1));
        }

    }

    @Override
    public void loadMore(int page) {
        dataReloading.loadMore(page, this.mTag, new DataReloading.DataReloadingListener() {
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

        List<DomainObject> list = movieWrapper.results();

        mResults.addAll(list);
        myViewPagerAdapter.notifyDataSetChanged();
        //recyclerViewPagerAdapter.notifyDataSetChanged();
    }
}
