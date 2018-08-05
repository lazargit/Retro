package com.shamildev.retro.ui.mylist.fragment.view;

import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.ui.common.view.MVPView;

import java.util.List;

/**
 * Created by Shamil Lazar on 25.05.2018.
 */

public interface MyListView extends MVPView {

    void fillList(List<MediaItem> watchList);

}
