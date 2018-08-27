package com.shamildev.retro.domain.executor;

import com.shamildev.retro.domain.interactor.UseCase;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;



public final class UseCaseFlowableHandler {



    private final CompositeDisposable disposables;
    private final ExecutionThread executionThread;
    private final PostExecutionThread postExecutionThread;

    @Nullable
    private UseCase previousUseCase;

    @Nullable
    private Object previousUseCaseParams;

    @Inject
    UseCaseFlowableHandler(
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

                .subscribeOn(executionThread.scheduler())
                .observeOn(postExecutionThread.scheduler())
                .subscribeWith(observer);
        disposables.add(disposable);
    }

    public <K, V> void execute(UseCase<K, V> useCase, DisposableObserver<V> observer) {
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
