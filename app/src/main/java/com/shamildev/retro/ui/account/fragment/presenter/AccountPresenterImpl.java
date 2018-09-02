package com.shamildev.retro.ui.account.fragment.presenter;


import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.CallbackManager;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.BaseError;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.helper.DataReloading;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retrovideo.core.RetroVideo;
import com.shamildev.retro.ui.account.fragment.model.AccountModel;
import com.shamildev.retro.ui.account.fragment.view.AccountView;
import com.shamildev.retro.ui.common.presenter.BasePresenter;

import javax.inject.Inject;


/**
     * Created by Shamil Lazar
     */

/**
 * An implementation of {@link AccountPresenter}.
 */
@PerFragment
public final class AccountPresenterImpl extends BasePresenter<AccountView,AccountModel> implements AccountPresenter,NetworkManager.NetworkListener {



    private  NetworkManager networkManager;
    private  DataConfig dataConfig;
    private  AppConfig appConfig;


    @Inject
    Application application;
    @Inject
    DataReloading dataReloading;
    @Inject
    RetroImage retroImage;
    @Inject
    RetroVideo retroVideo;
    private CallbackManager callbackManager;


    @Inject
    AccountPresenterImpl(
                        AppConfig appConfig,
                        AccountView view,
                        AccountModel model,
                        NetworkManager networkManager,
                        DataConfig dataConfig ) {
        super(view,model);
        this.networkManager = networkManager;
        this.networkManager.add(toString(), this::refreshData);
//            this.bootstrap = bootstrap;
//            this.bootstrap.setUp(this);
        this.dataConfig = dataConfig;
        this.appConfig = appConfig;


    }


    @Override
    public void onStart(@Nullable Bundle savedInstanceState) {
            this.model.initData();



    }



    @Override
    public void login() {
           model.signInUser();
    }

    @Override
    public void fbLogin(String token) {

        model.signInUser(token);


        // Callback registration

//


//        view.getFbLoginButton()
//                .registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        Log.e("FACEBOOK#", "check user " + loginResult.getAccessToken().getToken());
//                        model.signInUser(loginResult.getAccessToken().getToken());
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        Log.e("FACEBOOK#", "onCancel ");
//                       // Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(FacebookException error) {
//                        Log.e("FACEBOOK#", "onError"+error.getMessage());
//                        // toast(R.string.error_login);
//                       // Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
//                    }
//                });
           // model.signInUser();
    }

    @Override
    public void logout() {
           model.logOut();
    }

    @Override
    public void logoutSuccesfull() {
        toast("Logout Succesfull !!");
    }

    @Override
    public void signSuccesfull() {
        toast("Sign Succesfull !!");
    }

    @Override
    public void setBackgroundImage(MediaItem mediaItem) {
//        retroImage
//                .load(mediaItem)
//                .Poster()
//                .w780()
//                .into(view.getSplashBg(),new RetroImageRequestListener() {
//                    @Override
//                    public boolean onLoadFailed() {
//                        Log.e("TAG","IMAGES LOAD FAILED.");
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady() {
//                        Log.e("TAG","ALL IMAGES PRELOADED...!");
//
//                        return false;
//                    }
//                });


    }






    @Override
    public void onError(Throwable t) {
        if(t.getCause() instanceof TMDBError){
            TMDBError error = (TMDBError) t.getCause();
            Log.d("onError","<<<<< "+error.getResponseCode()+" : "+error.getMessage()+" : "+error.getStatusCode()+" : "+error.getSuccess());

        }
        if (t instanceof BaseError){
            BaseError error = (BaseError) t;
            Log.d("onError","<<<<< "+error.getMessage());
        }
        Log.d("onError",t.getMessage()+"---"+t);
    }





    @Override
    public void toast(Object obj) {
        view.makeToast((String) obj);
    }


    @Override
    public void onNetworkAvailable() {

        Log.d("test","onNetworkAvailable");
    }
    private void refreshData() {
        Log.d("test","refreshData");
    }


}