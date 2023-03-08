package com.calendar.core.format

import com.calendar.core.CalendarDay

/**
 * Used to format a [com.shuhart.materialcalendarview.CalendarDay] to a string for the month/year title
 */
interface TitleFormatter {

    /**
     * Converts the supplied day to a suitable month/year title
     *
     * @param day the day containing relevant month and year information
     * @return a label to display for the given month/year
     */
    fun format(day: CalendarDay): CharSequence
}
