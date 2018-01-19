package com.shamildev.retro.util.rx;


import io.reactivex.Scheduler;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface RxSchedulerProvider {


    Scheduler backgroundThread();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet();

}
