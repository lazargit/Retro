package com.shamildev.retro.ui.widgets.Search;

import android.content.Context;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetMultiSearch;
import com.shamildev.retro.domain.interactor.UseCaseFlowable;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.responsemodels.Response;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;


/**
 * Created by Shamil Lazar on 23.02.2018.
 */

public class SearchViewWidget extends SearchView{



    private UseCaseHandler useCaseHandler;
    private UseCaseFlowable usecase;

    private RecyclerView recyclerView;
    private SearchResultWidget searchResultWidget;
    private SearchResultRecycleViewAdapter adapter;
    private String searchQuery="";




    private ImageView closeButton;
    private ClickListener clickListener;


    public SearchViewWidget(Context context) {
        super(context);



    }

    public SearchViewWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    public interface ClickListener {
        void onClick(View view, DomainObject domainObject);
        void onLongClick(View view, DomainObject domainObject);
        void onSearch(String txt);
        void onStartSearchLoading();
        void onEmpty();
        void onHide(View view);
        void onExpand(View view);

    }

    public void loadNexPage(){




        useCaseHandler.execute(usecase, GetMultiSearch.Params.with(searchQuery,searchResultWidget.getCurrentPage()), new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper resultWrapper) {

                searchResultWidget.addLoadedStatus(resultWrapper);
            }

            @Override
            public void onError(Throwable t) {
                Log.d("onError>>>>", t.getClass().getName());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void init(Context baseContext,ClickListener clickListener){
        this.useCaseHandler = useCaseHandler;


        closeButton = (ImageView)this.findViewById(R.id.search_close_btn);

        this.clickListener = clickListener;


        setUpSearchObservable();




        this.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                Log.d("MyTag","Search Expanded");
               clickListener.onExpand(v);
                setUpSearchObservable();
               // fragment_search = new Fragment_Search();
              //  replaceFragment(fragment_search);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                Log.d("MyTag","Search Hide");
               // onBackPressed();


                clickListener.onHide(v);

            }
        });




    }








    public void setUpSearchObservable() {

        Disposable subscribe = RxSearchView.queryTextChanges(this)

                .filter(charSequence -> {
                    Log.d("#############filter", "--> " + Thread.currentThread() + " || >>>filter: " + charSequence.toString() + " empty: " + charSequence.toString().isEmpty() + " length" + charSequence.length());
                    if (charSequence.toString().isEmpty() || charSequence.length() < 2) {


                        Log.d("#############", "--> " + Thread.currentThread() + " || filter: " + charSequence.toString());
                        clickListener.onEmpty();


                        return false;
                    } else {
                        Log.d("filter true", "--> " + Thread.currentThread() + " || filter: ");
                        clickListener.onStartSearchLoading();
                        return true;
                    }
                })


                .debounce(500, TimeUnit.MILLISECONDS)


                .distinctUntilChanged() // TODO some problem with search with 2 charkter lengh


               /* .switchMap(new Function<CharSequence, ObservableSource<CharactersResponse>>() {
                    @Override
                    public ObservableSource<CharactersResponse> apply(CharSequence charSequence) throws Exception {

                        Log.d("#############","--> "+Thread.currentThread()+" || switchMap: "+charSequence);
                        return dataFromNetwork(charSequence.toString());

                    }
                })*/
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(charSequence -> {
                    // Log.d("#############","--> "+Thread.currentThread()+" || doOnNext: "+charSequence);
                    //searchResultWidget.loaderHeader.setVisibility(VISIBLE);
                    //searchResultWidget.setLoadStatus();

                })


                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        //loadFirstPage(charSequence.toString());
                        clickListener.onSearch(charSequence.toString());
                    }
                });


    }

    private void startAni() {

        closeButton.animate().setDuration(2800).rotation(350).start();
    }

    private ObservableSource<Response> loadFirstPage(String s) {
        searchQuery = s;
        Log.d("#############","--> "+Thread.currentThread()+" || dataFromMdbNetwork: ");

        useCaseHandler.execute(usecase, GetMultiSearch.Params.with(searchQuery,1), new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper resultWrapper) {


                searchResultWidget.setLoadedStatus(resultWrapper);

//                SearchResultRecycleViewAdapter adapter = (SearchResultRecycleViewAdapter) recyclerView.getAdapter();
//                adapter.getArray().clear();
//                adapter.getArray().addAll(results);

                //recyclerView.getAdapter().notifyDataSetChanged();
             //   searchResultWidget.loaderHeader.setVisibility(GONE);
               // searchResultRecycleViewAdapter.notifyDataSetChanged();
                //   hideProgressBar(searchView);
                //setProgressBarIndeterminateVisibility(false);

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


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("MySearchView","onFinischinflate");
//        searchView = (SearchView) findViewById(R.id.mysearchview_searchview);
//        searchView.setFocusable(true);
//        searchView.setIconified(false);
//        searchView.requestFocusFromTouch();




    }


    public void showProgressBar(SearchView searchView, Context context)
    {
        int id = searchView.getContext().getResources().getIdentifier("android:id/app_bar_search", null, null);
        if (searchView.findViewById(id).findViewById(R.id.search_progress_bar) != null)
            searchView.findViewById(id).findViewById(R.id.search_progress_bar).animate().setDuration(200).alpha(1).start();

        else
        {
            View v = LayoutInflater.from(context).inflate(R.layout.loading_icon, null);
            ((ViewGroup) searchView.findViewById(id)).addView(v, 1);
        }
    }
    public void hideProgressBar(SearchView searchView)
    {
        int id = searchView.getContext().getResources().getIdentifier("android:id/app_bar_search", null, null);
        if (searchView.findViewById(id).findViewById(R.id.search_progress_bar) != null)
            searchView.findViewById(id).findViewById(R.id.search_progress_bar).animate().setDuration(200).alpha(0).start();
    }
}
