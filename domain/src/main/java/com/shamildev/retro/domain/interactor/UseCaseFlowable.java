package com.shamildev.retro.domain.interactor;

import io.reactivex.Flowable;
import io.reactivex.Observable;


public interface UseCaseFlowable<K, V> {

    Flowable<V> execute(K params);
}
