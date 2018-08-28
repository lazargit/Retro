package com.shamildev.retro.ui.splash.fragment.view;

import android.app.Application;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.retrovideo.view.RetroVideoView;
import com.shamildev.retro.ui.common.view.BaseViewFragment;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;
import com.shamildev.retro.util.DeviceUtils;
import com.shamildev.retro.retroimage.views.RetroImageView;
import com.shamildev.retro.views.retroslider.views.ImageSliderView;


import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Shamil Lazar.

 * A fragment implementation of {@link SplashView}.
 */
public final class SplashFragment extends BaseViewFragment<SplashPresenter> implements SplashView {


    @Inject
    Navigator navigator;

    @Inject
    Application application;

//
//    @BindView(R.id.img_slider)
//    ImageSliderView img_slider;
//
//    @BindView(R.id.img_slider2)
//    ImageSliderView img_slider2;
//
//    @BindView(R.id.customimageview_test)
//    RetroImageView customImageView;

    @BindView(R.id.splashbg)
    RetroImageView splashbg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }



//
//    @Override
//    public ImageSliderView getImageSliderView() {
//        return img_slider;
//    }
//    @Override
//    public ImageSliderView getImageSliderView2() {
//        return img_slider2;
//    }
//
//    @Override
//    public RetroImageView getCustomImageView() {
//        return customImageView;
//    }

    @Override
    public RetroImageView getSplashBg() {
        return splashbg;
    }

    @Override
    public void navigateToHome(HashMap<String, ResultWrapper> map) {
      navigator.navigateToHome(application,map);

    }






    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }
}