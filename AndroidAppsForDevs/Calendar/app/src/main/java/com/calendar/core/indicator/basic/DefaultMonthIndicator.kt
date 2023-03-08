package com.calendar.core.indicator.basic

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.view.View
import com.calendar.core.format.TitleFormatter
import com.calendar.core.CalendarDay
import com.calendar.core.CalendarPager
import com.calendar.core.CalendarPagerAdapter
import com.calendar.core.MaterialCalendarView
import com.calendar.core.indicator.MonthIndicator

/**
 * Created by NguyenBangGiang on 8/3/2023
 */
@SuppressLint("ViewConstructor")
class DefaultMonthIndicator(context: Context) : MonthIndicator {
    override fun setOnTitleClickListener(listener: View.OnClickListener) {
        view.setOnTitleClickListener(listener)
    }

    override fun setTitleFormatter(titleFormatter: TitleFormatter) {
        view.setTitleFormatter(titleFormatter)
    }

    private val view: DefaultMonthIndicatorView = DefaultMonthIndicatorView(context)

    override fun onMonthChanged(previous: CalendarDay, current: CalendarDay) {
        view.setPreviousMonth(previous)
        updateUi(current)
    }

    override fun updateUi(currentMonth: CalendarDay) {
        view.updateUi(currentMonth)
    }

    override fun getView(
        mcv: MaterialCalendarView,
        pager: CalendarPager,
        adapter: CalendarPagerAdapter<*>
    ): View {
        view.init(pager, mcv)
        return view
    }

    override fun applyStyles(typedArray: TypedArray) {
        view.applyStyles(typedArray)
    }
}