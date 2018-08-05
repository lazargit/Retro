package com.shamildev.retro.ui.home.fragment.gridlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shamildev.retro.R;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;
import com.shamildev.retro.ui.home.fragment.adapter.GridAdapter;


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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grid, container, false);
       // recyclerView.setAdapter(new GridAdapter(this));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    @Override
    public void makeToast(String message) {

    }
}
