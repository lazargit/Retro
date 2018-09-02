package com.shamildev.retro.ui.account.fragment.model;

import android.util.Log;

import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.executor.UseCaseHandler;
import com.shamildev.retro.domain.interactor.GetGenre;
import com.shamildev.retro.domain.interactor.GetMovieById;
import com.shamildev.retro.domain.interactor.GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.GetNowPlayingTVShows;
import com.shamildev.retro.domain.interactor.GetPopularPerson;
import com.shamildev.retro.domain.interactor.GetTMDBConfiguration;
import com.shamildev.retro.domain.interactor.GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.GetUpcomingMovies;
import com.shamildev.retro.domain.interactor.InitTables;
import com.shamildev.retro.domain.interactor.user.GetUser;
import com.shamildev.retro.domain.interactor.user.LogoutUser;
import com.shamildev.retro.domain.interactor.user.SignInUser;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.ResultWrapper;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar.
 *
 */
@PerFragment
public class AccountModelImpl extends AccountModel  {


    private final String TAG = getClass().getName().toString();
    private final SignInUser signInUser;
    @Inject
    protected DataConfig dataConfig;
    @Inject
    protected AppConfig appConfig;



    private  UseCaseHandler useCaseHandler;
    private final LogoutUser logoutUser;


    @Inject
    AppUser appUser;

    @Inject
    public AccountModelImpl(
            UseCaseHandler useCaseHandler,
            LogoutUser logoutUser,
            SignInUser signInUser) {

        this.useCaseHandler = useCaseHandler;
        this.logoutUser = logoutUser;
        this.signInUser = signInUser;
    }

    @Override
    public void initData() {
           Log.e(TAG, ""+appUser.getEmail());
           presenter.toast(appUser.getEmail());
    }

    @Override
    public void logOut() {
        useCaseHandler.execute(logoutUser,LogoutUser.Params.justVoid(), new DisposableSubscriber<AppUser>() {
            @Override
            public void onNext(AppUser appUser) {
                Log.e(TAG,"USER  "+appUser.toString());
                presenter.toast("User is :"+appUser.getLoggedIn());

            }

            @Override
            public void onError(Throwable t) {
                presenter.onError(t);

            }

            @Override
            public void onComplete() {
                 presenter.logoutSuccesfull();

            }
        });
    }

    @Override
    public void signInUser() {
        useCaseHandler.execute(signInUser,SignInUser.Params.justVoid(), new DisposableSubscriber<AppUser>() {
            @Override
            public void onNext(AppUser appUser) {
                Log.e(TAG,"USER sign in  "+appUser.toString());
                presenter.toast("User after  :"+appUser.getLoggedIn());

            }

            @Override
            public void onError(Throwable t) {
                presenter.onError(t);

            }

            @Override
            public void onComplete() {
                presenter.signSuccesfull();
            }
        });

    }

    @Override
    public void signInUser(String token) {
        Log.e("facebook#","USER sign  "+token);
        useCaseHandler.execute(signInUser,SignInUser.Params.withFacebook(token), new DisposableSubscriber<AppUser>() {
            @Override
            public void onNext(AppUser appUser) {
                Log.e(TAG,"USER sign in  "+appUser.toString());
                presenter.toast("User after  :"+appUser.getPhotoUrl());


            }

            @Override
            public void onError(Throwable t) {
                presenter.onError(t);

            }

            @Override
            public void onComplete() {
                presenter.signSuccesfull();
            }
        });

    }


}
