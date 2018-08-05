

/**
 * Created by Shamil Lazar on 24.05.2018.
 */


@file:JvmName("MathUtils")

package com.shamildev.retro.util
fun constrain(min: Float, max: Float, v: Float) = v.coerceIn(min, max)