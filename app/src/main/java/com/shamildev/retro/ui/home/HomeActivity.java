package com.shamildev.retro.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMovieById;
import com.shamildev.retro.domain.interactor.GetMultiSearch;
import com.shamildev.retro.domain.models.Movie;

import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.responsemodels.Response;
import com.shamildev.retro.ui.common.BaseActivitySupport;

import com.shamildev.retro.ui.home.fragment.view.HomeFragment;


import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Schamil on 30.10.2017.
 */

public class HomeActivity extends BaseActivitySupport {

    private static final String INTENT_EXTRA_PARAM_MOVIE = "com.shamildev.retro.INTENT_PARAM_MOVIES";
    private Unbinder butterKnifeUnbinder;


    SearchView searchView;


    @Inject
    UseCaseHandler useCaseHandler;
    @Inject
    GetMultiSearch getMultiSearch;

    @BindView(R.id.toolbar)
    Toolbar toolBar;

    public static Intent getCallingIntent(Context context, ArrayList<Movie> movies) {
        Intent intent = new Intent(context, HomeActivity.class);

        intent.putExtra(INTENT_EXTRA_PARAM_MOVIE, movies);


        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        butterKnifeUnbinder = ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        initializeActivity(savedInstanceState);

    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {
        ArrayList<Movie> movies = (ArrayList<Movie>) getIntent().getSerializableExtra(INTENT_EXTRA_PARAM_MOVIE);
        if (savedInstanceState == null) {

           addFragment(R.id.fragmentContainer, HomeFragment.withMovies(movies));
        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        if(searchView!=null) {
            setUpSearchObservable();
        }
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }


    private ObservableSource<Response> dataFromMdbNetwork(String s) {


        useCaseHandler.execute(getMultiSearch, GetMultiSearch.Params.with(s), new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper resultWrapper) {
                Log.d("onNext", "results "+resultWrapper.totalResults());
                Log.d("onNext", "results "+resultWrapper.results().size());

            }

            @Override
            public void onError(Throwable t) {
                Log.d("onError>>>>", t.getClass().getName());
            }

            @Override
            public void onComplete() {

            }
        });

        return Observable.empty();//  presenter.searchMulti(s);
    }

    private void setUpSearchObservable() {


        RxSearchView.queryTextChanges(searchView)
                .observeOn(Schedulers.computation())
                .filter(new Predicate<CharSequence>() {



                    @Override
                    public boolean test(CharSequence charSequence) throws Exception {
                        if (charSequence.toString().isEmpty() || charSequence.length() <3 ) {


                            Log.d("#############","--> "+Thread.currentThread()+" || filter: "+charSequence.toString());

                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()

               /* .switchMap(new Function<CharSequence, ObservableSource<CharactersResponse>>() {
                    @Override
                    public ObservableSource<CharactersResponse> apply(CharSequence charSequence) throws Exception {

                        Log.d("#############","--> "+Thread.currentThread()+" || switchMap: "+charSequence);
                        return dataFromNetwork(charSequence.toString());

                    }
                })*/
                .switchMap(new Function<CharSequence, ObservableSource<Response>>() {
                    @Override
                    public ObservableSource<Response> apply(CharSequence charSequence) throws Exception {

                        Log.d("#############","--> "+Thread.currentThread()+" || switchMap: "+charSequence);
                        return dataFromMdbNetwork(charSequence.toString());

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response charactersResponse) throws Exception {

                        Log.d("#############","--> "+Thread.currentThread()+" || Total_pages: "+charactersResponse.getTotalPages()+" Total_results "+charactersResponse.getTotalResults());
                    }


                });
    }
}
