package com.shamildev.retro.ui.splash.fragment.presenter;


import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.data.net.error.TMDBError;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.MediaItem;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.helper.DataReloading;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.retrovideo.core.RetroVideo;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.splash.fragment.model.SplashModel;
import com.shamildev.retro.ui.splash.fragment.view.SplashView;
import java.util.HashMap;
import java.util.Timer;

import javax.inject.Inject;

import timber.log.Timber;


/**
     * Created by Shamil Lazar
     */

    /**
     * An implementation of {@link SplashPresenter}.
     */
    @PerFragment
    public final class SplashPresenterImpl extends BasePresenter<SplashView,SplashModel> implements SplashPresenter,NetworkManager.NetworkListener {



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

        @Inject
        SplashPresenterImpl(
                            AppConfig appConfig,
                            SplashView view,
                            SplashModel model,
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
            Log.e("BasePresenter","onStart!!!! "+this.appConfig.isFirstStart());
            if(this.appConfig.isFirstStart()){
                this.model.initData();
               // this.model.checkUser();
            }else{
                this.model.initUser();
               // this.model.initConfiguration();
            }

        }

        @Override
        public void configRetroImage(Configuration configuration) {
            retroImage.setConfigurations(configuration);
        }

        @Override
        public void setBackgroundImage(MediaItem mediaItem) {
            retroImage
                    .load(mediaItem)
                    .Poster()
                    .w780()
                    .into(view.getSplashBg(),new RetroImageRequestListener() {
                        @Override
                        public boolean onLoadFailed() {
                            Log.e("TAG","IMAGES LOAD FAILED.");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady() {
                            Log.e("TAG","ALL IMAGES PRELOADED...!");

                            return false;
                        }
                    });


        }






        @Override
        public void onError(Throwable t) {
            if(t.getCause() instanceof TMDBError){
                TMDBError error = (TMDBError) t.getCause();
                Log.d("onError","<<<<< "+error.getResponseCode()+" : "+error.getMessage()+" : "+error.getStatusCode()+" : "+error.getSuccess());

            }else{

            }
            Log.d("onError",t.getMessage());
        }



        @Override
        public void finish(HashMap<String, ResultWrapper> map) {
            Timber.d("FINISH","map",map);
            view.navigateToHome(map);


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