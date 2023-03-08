package com.calendar.core.indicator

import android.content.res.TypedArray
import android.view.View
import android.view.ViewGroup
import com.calendar.core.format.TitleFormatter
import com.calendar.core.CalendarDay
import com.calendar.core.CalendarPager
import com.calendar.core.CalendarPagerAdapter
import com.calendar.core.MaterialCalendarView

/**
 * Created by NguyenBangGiang on 8/3/2023
 */
interface MonthIndicator {

    fun onMonthChanged(previous: CalendarDay, current: CalendarDay)

    fun updateUi(currentMonth: CalendarDay)

    fun getView(
        mcv: MaterialCalendarView,
        pager: CalendarPager, adapter: CalendarPagerAdapter<*>
    ): View

    /**
     * @param typedArray do not call recycle() on it.
     */
    fun applyStyles(typedArray: TypedArray)

    fun setTitleFormatter(titleFormatter: TitleFormatter)

    fun setOnTitleClickListener(listener: View.OnClickListener)

    fun desiredHeightTileNumber(): Int = 1

    fun getDesiredLayoutParamsWidth(): Int = ViewGroup.LayoutParams.WRAP_CONTENT
}