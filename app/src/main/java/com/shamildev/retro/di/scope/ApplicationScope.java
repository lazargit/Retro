package com.shamildev.retro.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

/**
 * Created by Schamil Mischijew on 30.10.2017.
 */

@Scope
@Singleton
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}
