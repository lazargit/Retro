package com.shamildev.retro.android.executor;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Shamil Lazar on 18.12.2017.
 */


@Reusable
public class CompositDisp {


    @Inject
    CompositDisp() {
    }


    public CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

}
