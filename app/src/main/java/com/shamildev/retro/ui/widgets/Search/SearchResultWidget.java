package com.shamildev.retro.ui.widgets.Search;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.models.ResultWrapper;

import java.util.ArrayList;
import java.util.List;


import butterknife.Unbinder;

/**
 * Created by Shamil Lazar on 24.02.2018.
 */

public class SearchResultWidget extends RelativeLayout {




    public interface Listener {
        void onNextPage();


    }




    private RecyclerView recyclerView;
    private SearchViewWidget.ClickListener clickListener;


    public RecyclerView getRecyclerView() {return recyclerView;}

    Glide glide;



    private Unbinder butterKnifeUnbinder;
    private SearchResultRecycleViewAdapter adapter;

    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 20;
    private int TOTAL_RESULT = 0;
    private int currentPage = PAGE_START;

    public SearchResultRecycleViewAdapter getAdapter() {
        return adapter;
    }

    public int getCurrentPage() { return currentPage;}

    public SearchResultWidget(Context context) {
        super(context);
        init();
    }



    public SearchResultWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init() {

        inflate(getContext(), R.layout.view_search_result,this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_search);


        //  butterKnifeUnbinder = ButterKnife.bind(this);



    }

    public void setClearStatus(){
        if(adapter!=null) {
            adapter.clear();

        }

    }

    public void setLoadStatus(){
        if(adapter!=null) {
            adapter.clear();
            adapter.addLoadingFooter();
        }
    }
    public void setLoadedStatus(ResultWrapper results){
     //  TOTAL_PAGES = results.totalPages();
        TOTAL_RESULT = results.totalResults();
        currentPage = 1;

        adapter.removeLoadingFooter();


        if(adapter!=null) {
            adapter.clear();
            adapter.addAll(results.results());

        }

    }

    public void addLoadedStatus(ResultWrapper resultWrapper) {
        adapter.removeLoadingFooter();
        isLoading = false;
        adapter.addAll(resultWrapper.results());
    }


    public void addOnItemTouchListener(SearchViewWidget.ClickListener listener) {
        clickListener = listener;

    }





    public void setUpRecyclerView(Context baseContext,   Listener listener) {

            adapter = new SearchResultRecycleViewAdapter( glide,baseContext);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL, false);


        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(baseContext, R.drawable.devider_gray));


            if(recyclerView != null) {
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.addItemDecoration(divider);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemViewCacheSize(20);
                recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
                recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
                    @Override
                    protected void loadMoreItems() {
                        isLoading = true;
                        currentPage += 1;
                        Log.e("Pagination","loadMoreItems");



                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                            else isLastPage = true;
                                 listener.onNextPage();
                              //  loadNextPage();
                            }
                        }, 1000);


                    }

                    @Override
                    public int getTotalPageCount() {
                        return TOTAL_PAGES;
                    }

                    @Override
                    public boolean isLastPage() {
                        return isLastPage;
                    }

                    @Override
                    public boolean isLoading() {
                        return isLoading;
                    }
                });
                recyclerView.addOnItemTouchListener(new SearchResultRecycleViewAdapter.RecyclerTouchListener(this, recyclerView, new SearchResultRecycleViewAdapter.ClickListener() {

                                @Override
                                public void onClick(View view, int position) {

                                    clickListener.onClick(view,adapter.getArray().get(position));
                                }

                                @Override
                                public void onLongClick(View view, int position) {

                                    clickListener.onLongClick(view,adapter.getArray().get(position));
                                }



                        })

                );



            } else {
                throw new IllegalStateException("No RecyclerView associated to SearchResultWidget");
            }


        }






}
