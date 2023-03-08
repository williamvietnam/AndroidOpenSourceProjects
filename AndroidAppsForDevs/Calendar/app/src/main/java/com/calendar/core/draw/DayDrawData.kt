package com.calendar.core.draw

import android.graphics.Rect

/**
 * Created by NguyenBangGiang on 8/3/2023
 */
class DayDrawData(
    var radius: Float = 0f,
    var cx: Float = 0f,
    var cy: Float = 0f,
    val rangeRect: Rect = Rect(),
    val firstRect: Rect = Rect(),
    val lastRect: Rect = Rect(),
    var bottomTopDayPadding: Int = 0
)