package com.shamildev.retro.domain.bootstrap;

/**
 * Created by Schamil Mischijew on 30.11.2017.
 */

public interface Bootstrap {


    void onBootstrapNext(Class clazz);
    void onBootstrapComplete();
    void onBootstrapError(Throwable t);
}
