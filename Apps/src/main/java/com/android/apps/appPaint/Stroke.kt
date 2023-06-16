package com.android.apps.appPaint

import android.graphics.Path


data class Stroke(
    // color of the stroke
    var color: Int,

    // width of the stroke
    var strokeWidth: Float,

    // a Path object to
    // represent the path drawn
    var path: Path,
)