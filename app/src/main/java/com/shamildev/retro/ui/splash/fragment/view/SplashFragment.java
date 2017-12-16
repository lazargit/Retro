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
public final class SplashFragment extends BaseViewFragment<SplashPresenter>
        implements SplashView {


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
        presenter.onDoSomething();

    }


}