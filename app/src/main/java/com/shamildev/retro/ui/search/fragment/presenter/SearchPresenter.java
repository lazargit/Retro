package com.shamildev.retro.ui.search.fragment.presenter;

import android.content.Context;

import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.ui.common.presenter.Presenter;

import java.util.List;

/**
 * Created by Shamil Lazar on 25.05.2018.
 */

public interface SearchPresenter extends Presenter {

    void init(Context context);
    void getDiscover();

}
