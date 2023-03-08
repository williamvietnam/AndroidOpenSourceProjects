package com.calendar.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.calendar.R
import com.calendar.core.CalendarDay
import com.calendar.core.MaterialCalendarView
import com.calendar.core.OnDateSelectedListener
import com.calendar.core.OnMonthChangedListener
import java.text.SimpleDateFormat

/**
 * Shows off the most basic usage
 */
class BasicActivity : AppCompatActivity(), OnDateSelectedListener, OnMonthChangedListener {
    private lateinit var widget: MaterialCalendarView
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
        widget = findViewById(R.id.calendarView)

        widget.addOnDateChangedListener(this)
        widget.addOnMonthChangedListener(this)
        widget.selectionMode = MaterialCalendarView.SELECTION_MODE_RANGE
        textView = findViewById(R.id.textView)
        //Setup initial text
        textView.text = selectedDatesString
    }

    override fun onDateSelected(
        widget: MaterialCalendarView,
        date: CalendarDay,
        selected: Boolean
    ) {
        textView.text = selectedDatesString
    }

    override fun onMonthChanged(widget: MaterialCalendarView, date: CalendarDay) {
        supportActionBar?.title = FORMATTER.format(date.date)
    }

    private val selectedDatesString: String
        get() {
            val date = widget.selectedDate ?: return "No Selection"
            return FORMATTER.format(date.date)
        }

    companion object {

        private val FORMATTER = SimpleDateFormat.getDateInstance()
    }
}
