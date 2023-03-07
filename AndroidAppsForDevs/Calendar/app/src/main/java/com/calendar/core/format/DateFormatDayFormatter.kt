package com.calendar.core.format

import com.calendar.core.CalendarDay
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Format using a [DateFormat] instance.
 */
class DateFormatDayFormatter : DayFormatter {

    private val dateFormat: DateFormat

    /**
     * Format using a default format
     */
    constructor() {
        this.dateFormat = SimpleDateFormat("d", Locale.getDefault())
    }

    /**
     * Format using a specific [DateFormat]
     *
     * @param format the format to use
     */
    constructor(format: DateFormat) {
        this.dateFormat = format
    }

    override fun format(day: CalendarDay): String = dateFormat.format(day.date)
}
