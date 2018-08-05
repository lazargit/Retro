package com.shamildev.retro.ui.gallery.gridlist;

import android.util.Log;

import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;


/**
 * Created by Shamil Lazar on 25.05.2018.
 */

public class GridListFragment extends BaseViewFragmentV4<GridListPresenter> implements GridListView {

    public static final String TAG = GridListFragment.class.getSimpleName();




    public GridListFragment() {
        Log.e("TAG","WatchListFragment");
        setRetainInstance(true);



    }


    public static GridListFragment instance() {
        final GridListFragment fragment = new GridListFragment();
        return fragment;
    }



    @Override
    public void makeToast(String message) {

    }
}
