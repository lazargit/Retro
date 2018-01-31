package com.shamildev.retro.ui.watchlist.fragment.view;

import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.ui.common.view.MVPView;

import java.util.List;

/**
 * Created by Shamil Lazar on 13.12.2017.
 * A view that contains a button that does something.
 */
public interface WatchListView extends MVPView {

    void showSomething(String something);
    void fillList(List<Movie> results);

}
