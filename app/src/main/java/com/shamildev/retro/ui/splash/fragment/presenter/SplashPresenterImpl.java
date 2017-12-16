package com.shamildev.retro.ui.splash.fragment.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.Entity;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.responsemodels.Response;
import com.shamildev.retro.ui.common.presenter.BasePresenter;
import com.shamildev.retro.ui.splash.fragment.view.SplashView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */



    /**
     * An implementation of {@link SplashPresenter}.
     */
    @PerFragment
    final class SplashPresenterImpl extends BasePresenter<SplashView> implements SplashPresenter {


        private final RemoteRepository remoteRepository;

        @Inject
        SplashPresenterImpl(SplashView view, RemoteRepository remoteRepository) {
            super(view);
            this.remoteRepository = remoteRepository;

        }

        @Override
        public void onDoSomething() {
            Timber.d("test","onDoSomething");
     this.remoteRepository.getTestService()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new DisposableObserver<Response>() {
         @Override
         public void onNext(Response entity) {
             Timber.d("test","onDoSomething"+entity.getTotalResults());
         }

         @Override
         public void onError(Throwable e) {

         }

         @Override
         public void onComplete() {
             Timber.d("test","");

         }
     });

            view.showSomething("something");
        }
    }