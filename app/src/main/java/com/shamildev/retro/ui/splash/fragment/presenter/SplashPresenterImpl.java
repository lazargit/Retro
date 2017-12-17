package com.shamildev.retro.ui.splash.fragment.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.Entity;
import com.shamildev.retro.domain.repository.MovieWrapper;
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
            Log.d("test","onDoSomething");
     this.remoteRepository.getTestService()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new DisposableObserver<MovieWrapper>() {
         @Override
         public void onNext(MovieWrapper entity) {
             Log.d("test","onDoSomething"+entity.totalPages());
         }

         @Override
         public void onError(Throwable e) {
             Log.d("error","onError"+e.getMessage());
         }

         @Override
         public void onComplete() {
             Log.d(" onComplete"," onComplete");

         }
     });

            view.showSomething("something");
        }
    }