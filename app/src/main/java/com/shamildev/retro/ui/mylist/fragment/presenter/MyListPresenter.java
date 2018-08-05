package com.shamildev.retro.ui.mylist.fragment.presenter;

import android.content.Context;

import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.ui.common.presenter.Presenter;

import java.util.List;

/**
 * Created by Shamil Lazar on 25.05.2018.
 */

public interface MyListPresenter extends Presenter {

    void init(Context context);
    List<MediaItem> mediaItems();
}
