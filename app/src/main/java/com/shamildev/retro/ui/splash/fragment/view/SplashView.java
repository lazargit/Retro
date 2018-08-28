package com.shamildev.retro.ui.splash.fragment.view;

import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.retroimage.views.RetroImageView;
import com.shamildev.retro.ui.common.view.MVPView;

import java.util.HashMap;


/**
 * Created by Shamil Lazar.
 *
 */
public interface SplashView extends MVPView {






    RetroImageView getSplashBg();

    void navigateToHome(HashMap<String,ResultWrapper> map);

}
