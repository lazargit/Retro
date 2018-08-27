package com.shamildev.retro.di.scope;

/**
 * Created by Shamil Lazar on 13.12.2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerChildFragment {
}
