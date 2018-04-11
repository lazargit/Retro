package com.shamildev.retro.ui.splash.fragment.view;

import com.shamildev.retro.domain.models.Movie;
import com.shamildev.retro.domain.models.MovieWrapper;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.ui.common.view.MVPView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Shamil Lazar on 13.12.2017.
 * A view that contains a button that does something.
 */
public interface SplashView extends MVPView {

    void showSomething(String something);

    void navigateToHome(HashMap<String,ResultWrapper> map);

}
