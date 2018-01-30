package com.shamildev.retro.ui.watchlist.fragment.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shamildev.retro.R;
import com.shamildev.retro.ui.common.view.BaseViewFragment;
import com.shamildev.retro.ui.watchlist.fragment.presenter.WatchListPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link WatchListView}.
 */
public final class WatchListFragment extends BaseViewFragment<WatchListPresenter> implements WatchListView {


    private Unbinder butterKnifeUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_watchlist, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;



    }

    @Override
    public void showSomething(String something) {
        //someText.setText(something);
    }



    @OnClick(R.id.button_fetch_watchlist)
    void onButton_start_bootstrap(View view) {

        System.out.println(">>>###>>>"+view.getId());

        presenter.onDoSomething(R.id.button_fetch_watchlist);

    }


    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }
}