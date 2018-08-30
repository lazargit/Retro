package com.shamildev.retro.domain.interactor;

import com.shamildev.retro.domain.config.AppConfig;
import com.shamildev.retro.domain.config.DataConfig;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.domain.params.ParamsBasic;
import com.shamildev.retro.domain.repository.CacheRepository;
import com.shamildev.retro.domain.repository.LocalRepository;
import com.shamildev.retro.domain.repository.RemoteRepository;
import com.shamildev.retro.domain.util.Constants;
import com.shamildev.retro.domain.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;



public final class InitTables implements UseCaseCompletable<ParamsBasic> {

    private final RemoteRepository repository;
    private final CacheRepository cache;
    private final LocalRepository local;
    private DataConfig dataConfig;
    private String[] taskArray = new String[]{"init configuration","init genres"};
    private String[] anArray = new String[]{"en-US","de-DE","fr-FR"};


    @Inject
    AppConfig appConfig;

    @Inject
    AppUser user;




    @Inject
    InitTables(RemoteRepository repository,
               CacheRepository cache,
               LocalRepository localRepository,
               DataConfig dataConfig) {
        this.repository = repository;
        this.cache = cache;
        this.local = localRepository;
        this.dataConfig = dataConfig;
    }




    @Override
    public Completable execute(ParamsBasic params) {
        int cacheTime = ((Params) params).cacheTime;
         String pLanguage = ((Params) params).language;

        String language = !Arrays.asList(anArray).contains(pLanguage) ? anArray[0] : pLanguage;

        System.out.println("execute: " + Thread.currentThread().getName());

//        Calendar currentDate = Calendar.getInstance();
//        currentDate.set(Calendar.MONTH, 8); // Months are 0-based!
//        currentDate.set(Calendar.DAY_OF_MONTH, 16); // Clearer than DATE
//        currentDate.set(Calendar.YEAR, 2018); // Clearer than DATE
//
//        java.text.DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z", Locale.GERMANY);







      return Completable.create(e -> repository.fetchGuestSession()
              .flatMapCompletable(guestSession ->
                      cache.saveUser(User.builder()
                              .language(dataConfig.language())
                              .name(User.anonymus)
                              .tmdb_guest_session(guestSession.getGuestSessionId())
                              .tmdb_expires_at(DateUtil.convertStringUtcToMilSec(guestSession.getExpiresAt()))
                              .build()))
              .andThen(local.streamJsonCongiguration()
                      .cast(Configuration.class)
                      .flatMapCompletable(cache::saveTMDbConfiguration))
              .andThen(local.streamJsonGenres(Constants.MEDIA_TYPE.MOVIE, language)
                      .cast(Genre.class)
                      .flatMapCompletable(cache::saveGenre))
              .andThen(local.streamJsonGenres(Constants.MEDIA_TYPE.TV, language)
                      .cast(Genre.class)
                      .flatMapCompletable(cache::saveGenre))
              .doOnComplete(() ->{
                  User user = cache.fetchUser().blockingSingle();
                   this.user.setUser(user);
                   e.onComplete();
              })
              .doOnError(e::onError)
              .subscribeOn(Schedulers.io())
              .subscribe());
    }







    public static final class Params implements ParamsBasic {



        private int cacheTime = 0;
        private String language;

        public Params(String language) {
            this.language = language;
        }
        public Params(int cacheTime,String language ) {
            this.cacheTime = cacheTime;
            this.language = language;
        }


        public static InitTables.Params with(String language) {
            return new InitTables.Params(language);
        }
        public static InitTables.Params with(String language, int cacheTime) {
            return new InitTables.Params(cacheTime,language);
        }



    }
}
