package com.shamildev.retro.ui.splash.fragment.view;

import android.widget.ImageView;


import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.view.MVPView;
import com.shamildev.retro.retroimage.views.RetroImageView;
import com.shamildev.retro.views.retroslider.views.ImageSliderView;


import java.util.HashMap;


/**
 * Created by Shamil Lazar.
 *
 */
public interface SplashView extends MVPView {

    void showSomething(String something);


    ImageSliderView getImageSliderView();

    ImageSliderView getImageSliderView2();

    RetroImageView getCustomImageView();

    RetroImageView getSplashBg();

    void navigateToHome(HashMap<String,ResultWrapper> map);

}
