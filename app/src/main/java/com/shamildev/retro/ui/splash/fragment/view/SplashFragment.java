package com.shamildev.retro.ui.splash.fragment.view;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.shamildev.retro.R;
import com.shamildev.retro.ui.common.view.BaseViewFragment;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;


import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 13.12.2017.

 * A fragment implementation of {@link SplashView}.
 */
public final class SplashFragment extends BaseViewFragment<SplashPresenter> implements SplashView {


    private Unbinder butterKnifeUnbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.fragment_splash, container, false);
        butterKnifeUnbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;



    }

    @Override
    public void showSomething(String something) {
        //someText.setText(something);
    }


    @OnClick(R.id.button_save)
    void onStartBootstrap(View view) {
        Timber.d("onStartBootstrap");
        presenter.onDoSomething(R.id.button_save);

    }

    @OnClick(R.id.button)
    void onStartGetConfig(View view) {
        Timber.d("onStartGetConfig");
        presenter.onDoSomething(R.id.button);

    }

    @OnClick(R.id.button_genre)
    void onStartFetchGenre(View view) {
        Timber.d("onStartFetchGenre");
        presenter.onDoSomething(R.id.button_genre);

    }

    @OnClick(R.id.button_fetch_upcoming_movies)
    void onButton_fetch_upcoming_movies(View view) {

        presenter.onDoSomething(R.id.button_fetch_upcoming_movies);

    }

    @OnClick(R.id.button_fetch_popular)
    void onButton_fetch_popular(View view) {

        presenter.onDoSomething(R.id.button_fetch_popular);

    }

    @OnClick(R.id.button_fetch_now_playing)
    void onButton_fetch_now_playing(View view) {

        presenter.onDoSomething(R.id.button_fetch_now_playing);

    }

    @OnClick(R.id.button_fetch_movie)
    void onButton_fetch_movie(View view) {

        presenter.onDoSomething(R.id.button_fetch_movie);

    }
    @OnClick(R.id.button_fetch_movie2)
    void onButton_fetch_movie2(View view) {

        presenter.onDoSomething(R.id.button_fetch_movie2);

    }
    @OnClick(R.id.button_start_bootstrap)
    void onButton_start_bootstrap(View view) {

        presenter.onStartBootstrap();

    }


    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }
}