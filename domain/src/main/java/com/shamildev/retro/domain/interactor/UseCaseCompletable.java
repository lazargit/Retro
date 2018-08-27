package com.shamildev.retro.domain.interactor;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;


public interface UseCaseCompletable<K> {

    Completable execute(K params);
}
