package com.shamildev.retro.ui.account.fragment.view;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.shamildev.retro.R;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.navigation.Navigator;
import com.shamildev.retro.retroimage.views.RetroImageView;
import com.shamildev.retro.ui.account.AccountActivity;
import com.shamildev.retro.ui.account.fragment.presenter.AccountPresenter;
import com.shamildev.retro.ui.common.view.BaseViewFragmentV4;

import java.util.Arrays;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Shamil Lazar.

 * A fragment implementation of {@link AccountView}.
 */
public final class AccountFragment extends BaseViewFragmentV4<AccountPresenter> implements AccountView {


    @Inject
    Navigator navigator;

    @Inject
    Application application;


    @BindView(R.id.button_fblogin)
    LoginButton loginButtonFb;

    private CallbackManager callbackManager;
    private Unbinder butterKnifeUnbinder;
    private CallbackManager mCallbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        CallbackManager callbackManager = ((AccountActivity) getActivity()).getmCallbackManager();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("facebook#", "facebook:onSuccess:## " + loginResult.getAccessToken().getToken());
                //
                presenter.fbLogin(loginResult.getAccessToken().getToken());

            }

            @Override
            public void onCancel() {
                Log.d("facebook#", "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("facebook#", "facebook:onError"+error.getMessage(), error);
                // ...
            }
        });

        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();


//        callbackManager = CallbackManager.Factory.create();
//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        Log.e("FACEBOOK#", "onSuccess " + loginResult.getAccessToken().getToken());
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Log.e("FACEBOOK#", "onCancel ");
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        Log.e("FACEBOOK#", "onError"+exception.getMessage());
//                    }
//                });

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
        return null;
    }

    @Override
    public LoginButton getFbLoginButton() {
        return loginButtonFb;
    }

    @OnClick(R.id.button_logout)
    public void onClickLogout(Button button) {
        presenter.logout();
    }
    @OnClick(R.id.button_login)
    public void onClickLogin(Button button) {
        presenter.login();
    }

    @Override
    public void makeToast(String message) {
        showToastMessage(message);
    }
}