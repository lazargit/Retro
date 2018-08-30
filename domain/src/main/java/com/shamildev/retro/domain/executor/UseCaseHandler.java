package com.shamildev.retro.domain.executor;



import com.shamildev.retro.domain.interactor.UseCase;
import com.shamildev.retro.domain.interactor.UseCaseCompletable;
import com.shamildev.retro.domain.interactor.UseCaseFlowable;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;



public final class UseCaseHandler {



    private final CompositeDisposable disposables;
    private final ExecutionThread executionThread;
    private final PostExecutionThread postExecutionThread;

    @Nullable
    private UseCase previousUseCase;

    @Nullable
    private Object previousUseCaseParams;

    @Inject
    public UseCaseHandler(
            ExecutionThread threadScheduler,
            PostExecutionThread postExecutionScheduler,
                   CompositeDisposable disposables) {
        this.executionThread = threadScheduler;
        this.postExecutionThread = postExecutionScheduler;
        this.disposables = disposables;
    }

    public <K, V> void execute(UseCase<K, V> useCase, @Nullable K params,
                               DisposableObserver<V> observer) {
        setLastUseCase(useCase, params);
        Disposable disposable = useCase.execute(params)
                .subscribeOn(Schedulers.io())

                .observeOn(postExecutionThread.scheduler())
               // .subscribeOn(executionThread.scheduler())
                //.observeOn(postExecutionThread.scheduler())
                .subscribeWith(observer);
        disposables.add(disposable);
    }


    public <K, V> void execute(UseCaseFlowable<K, V> useCase, @Nullable K params,
                               DisposableSubscriber<V> observer) {
       // setLastUseCase(useCase, params);
        Disposable disposable = useCase.execute(params)

                //.subscribeOn(Schedulers.io())

                //  .observeOn(postExecutionThread.scheduler())
               .subscribeOn(executionThread.scheduler())
       .observeOn(postExecutionThread.scheduler())
        .subscribeWith(observer);
        if(disposables != null) {
            disposables.add(disposable);
        }
    }

    public <K> void execute(UseCaseCompletable<K> useCase, @Nullable K params,
                            DisposableCompletableObserver observer) {
        // setLastUseCase(useCase, params);
        Disposable disposable = useCase.execute(params)


                //.subscribeOn(Schedulers.io())

                //  .observeOn(postExecutionThread.scheduler())
                .subscribeOn(executionThread.scheduler())
                .observeOn(postExecutionThread.scheduler())
                .subscribeWith(observer);
        if(disposables != null) {
            disposables.add(disposable);
        }
    }

    public <K, V> void execute(UseCase<K, V> useCase, DisposableObserver<V> observer) {
        execute(useCase, null, observer);
    }


    public <K, V> void execute(UseCaseFlowable<K, V> useCase, DisposableSubscriber<V> observer) {
        execute(useCase, null, observer);
    }

    public <K> void execute(UseCaseCompletable<K> useCase, DisposableCompletableObserver observer) {
        execute(useCase, null, observer);
    }



    public void executePreviousUseCase(DisposableObserver observer) {
        if (previousUseCase != null) {
            execute(previousUseCase, previousUseCaseParams, observer);
        }
    }

    public void clear() {
        // clear only and not dispose the composite to enable composite reuse
        disposables.clear();
    }

    private void setLastUseCase(UseCase previousUseCase, @Nullable Object previousUseCaseParams) {
        this.previousUseCase = previousUseCase;
        this.previousUseCaseParams = previousUseCaseParams;
    }
}
