package com.shamildev.retro.ui.home.fragment.watchlist;

import com.shamildev.retro.domain.DomainObject;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.ui.common.view.MVPView;

import java.util.List;

/**
 * Created by Shamil Lazar on 25.05.2018.
 */

interface WatchListView extends MVPView {

    void fillWatchList(List<MediaItem> watchList);

}
