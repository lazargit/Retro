

package com.shamildev.retro.domain.executor;

import io.reactivex.Scheduler;

public interface PostExecutionThread {

    Scheduler scheduler();
}
