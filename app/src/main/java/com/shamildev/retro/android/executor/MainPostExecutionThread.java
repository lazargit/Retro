package com.shamildev.retro.android.executor;



import com.shamildev.retro.domain.executor.PostExecutionThread;

import javax.inject.Inject;

import dagger.Reusable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * A {@link PostExecutionThread} that provides the {@link AndroidSchedulers#mainThread()} scheduler.
 */
@Reusable
public final class MainPostExecutionThread implements PostExecutionThread {

    @Inject
    MainPostExecutionThread() {
    }

    @Override
    public Scheduler scheduler() {
        return AndroidSchedulers.mainThread();
    }
}
