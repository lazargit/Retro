package com.shamildev.retro.domain.repository;



import io.reactivex.Flowable;

/**
 * Created by Shamil Lazar on 14.08.2018.
 */

public interface FirebaseRepository {

    Flowable<String> initUser();
    Flowable<String> checkUser();
}
