package com.shamildev.retro.ui.home.fragment.view;

import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.ui.common.view.MVPView;
import com.shamildev.retro.ui.home.fragment.adapter.ViewPagerAdapter;

import java.util.List;

/**
 * Created by Shamil Lazar on 13.12.2017.
 * A view that contains a button that does something.
 */
public interface HomeView extends MVPView {

    void showSomething(String something);
    void fillList(List<Movie> results);
    void initViewPager(ViewPagerAdapter mViewPagerAdapter);
    void addPageFragment(String s, MovieWrapper movieWrapper);

}
