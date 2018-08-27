package com.shamildev.retro.android.executor;



import com.shamildev.retro.domain.executor.ExecutionThread;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


@Reusable
public final class IOExecutionThread implements ExecutionThread {

    @Inject
    IOExecutionThread() {
    }

    @Override
    public Scheduler scheduler() {
        return Schedulers.io();
    }
}
