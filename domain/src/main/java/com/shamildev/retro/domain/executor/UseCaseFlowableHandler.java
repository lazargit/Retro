/*
 * Copyright 2017 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shamildev.retro.domain.executor;



import com.shamildev.retro.domain.interactor.UseCase;

import javax.inject.Inject;

import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


/**
 * Provides default handling of use cases.
 * <p>
 * This handler executes a use cases in the given {@link ExecutionThread} and then publishes results
 * in the given {@link PostExecutionThread}.
 */
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
