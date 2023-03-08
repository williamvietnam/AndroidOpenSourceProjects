package com.calendar.core.draw

import android.graphics.Canvas
import com.calendar.core.DayView

/**
 * Created by NguyenBangGiang on 8/3/2023
 */
interface DayDrawDelegate {
    fun onDraw(canvas: Canvas, dayDrawData: DayDrawData, dayView: DayView)
    fun setSelectionColor(color: Int)
    fun setSelectionRangeColor(color: Int)
}