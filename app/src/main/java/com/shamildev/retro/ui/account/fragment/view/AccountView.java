package com.shamildev.retro.ui.account.fragment.view;

import com.facebook.login.widget.LoginButton;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.retroimage.views.RetroImageView;
import com.shamildev.retro.ui.common.view.MVPView;

import java.util.HashMap;


/**
 * Created by Shamil Lazar.
 *
 */
public interface AccountView extends MVPView {


    RetroImageView getSplashBg();
    LoginButton getFbLoginButton();



}
