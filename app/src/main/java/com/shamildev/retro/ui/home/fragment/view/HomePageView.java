package com.shamildev.retro.ui.home.fragment.view;

import android.view.View;

import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.view.MVPView;
import com.shamildev.retro.ui.home.fragment.adapter.RecyclerViewPagerAdapter;
import com.shamildev.retro.retroimage.views.RetroImageView;

import java.util.List;

/**
 * Created by Shamil Lazar on 13.12.2017.
 * A view that contains a button that does something.
 */
public interface HomePageView extends MVPView {

    void showSomething(String something);
    void fillList(List<Movie> results);

    void setAdapter(RecyclerViewPagerAdapter recyclerViewPagerAdapter);
    void openSlideShow(ResultWrapper resultWrapper, int adapterPosition, String tag, RetroImageView transitioningView, View view);

}
