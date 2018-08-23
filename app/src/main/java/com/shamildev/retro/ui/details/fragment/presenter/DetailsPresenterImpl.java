package com.shamildev.retro.ui.details.fragment.presenter;

import android.support.annotation.IdRes;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMovieById;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.details.fragment.model.DetailsModel;
import com.shamildev.retro.ui.details.fragment.view.DetailsView;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;

import javax.inject.Inject;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */


/**
 * An implementation of {@link WatchListPresenter}.
 */
@PerFragment
final class DetailsPresenterImpl extends BasePresenter<DetailsView,DetailsModel> implements DetailsPresenter{






    private final UseCaseHandler useCaseHandler;
    private final GetMovieById getMovieById;




    @Inject
    DetailsPresenterImpl(DetailsView view,
                         DetailsModel model,
                         UseCaseHandler useCaseHandler,
                         GetMovieById getMovieById ) {
        super(view,model);
        this.useCaseHandler = useCaseHandler;
        this.getMovieById = getMovieById;


    }




    @Override
    public void onDoSomething(@IdRes int id) {
//        if (id == R.id.button_fetch_watchlist) {
//
//            Log.d("getTMDBConfiguration", "getTMDBConfiguration");
//
//
//        }
    }




}