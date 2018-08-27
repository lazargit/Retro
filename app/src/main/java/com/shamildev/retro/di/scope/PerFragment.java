package com.shamildev.retro.di.scope;

/**
 * Created by Shamil Lazar.
 */


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
