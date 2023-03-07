package com.calendar.core.draw

/**
 * Created by NguyenBangGiang on 8/3/2023
 */
interface DayDrawDataProvider {
    fun calculateBounds(width: Int, height: Int)
    fun setBottomTopDayPadding(padding: Int)
    fun getDayDrawData(): DayDrawData
    fun copy(): DayDrawDataProvider
}