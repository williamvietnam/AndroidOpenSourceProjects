package com.calendar.core.indicator.basic

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.calendar.R
import com.calendar.core.format.TitleFormatter
import com.calendar.core.CalendarDay
import com.calendar.core.CalendarPager
import com.calendar.core.MaterialCalendarView

/**
 * Created by NguyenBangGiang on 8/3/2023
 */
class DefaultMonthIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val title = TextView(context)
    private val buttonPast = DirectionButton(context)
    private val buttonFuture = DirectionButton(context)
    private val titleChanger = TitleChanger(title)
    private var mcv: MaterialCalendarView? = null

    var arrowColor = Color.BLACK
        set(color) {
            if (color == 0) {
                return
            }
            field = color
            buttonPast.setColor(color)
            buttonFuture.setColor(color)
            invalidate()
        }
    var leftArrowMask: Drawable? = null
        set(icon) {
            field = icon
            buttonPast.setImageDrawable(icon)
        }
    var rightArrowMask: Drawable? = null
        set(icon) {
            field = icon
            buttonFuture.setImageDrawable(icon)
        }

    fun init(pager: CalendarPager, mcv: MaterialCalendarView) {
        this.mcv = mcv
        orientation = LinearLayout.HORIZONTAL
        clipChildren = false
        clipToPadding = false

        buttonPast.contentDescription = context.getString(R.string.previous)
        buttonFuture.contentDescription = context.getString(R.string.next)

        val onClickListener = OnClickListener { v ->
            if (v === buttonFuture) {
                pager.setCurrentItem(pager.currentItem + 1, true)
            } else if (v === buttonPast) {
                pager.setCurrentItem(pager.currentItem - 1, true)
            }
        }
        buttonPast.setOnClickListener(onClickListener)
        buttonFuture.setOnClickListener(onClickListener)

        titleChanger.titleFormatter = MaterialCalendarView.DEFAULT_TITLE_FORMATTER

        buttonPast.scaleType = ImageView.ScaleType.CENTER_INSIDE
        addView(buttonPast, LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f))

        title.gravity = Gravity.CENTER
        addView(
            title, LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                (MaterialCalendarView.DEFAULT_DAYS_IN_WEEK - 2).toFloat()
            )
        )

        buttonFuture.scaleType = ImageView.ScaleType.CENTER_INSIDE
        addView(buttonFuture, LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f))
    }

    fun applyStyles(typedArray: TypedArray) {
        titleChanger.orientation = typedArray.getInteger(
            R.styleable.MaterialCalendarView_mcv_titleAnimationOrientation,
            MaterialCalendarView.VERTICAL
        )
        arrowColor = typedArray.getColor(
            R.styleable.MaterialCalendarView_mcv_arrowColor,
            Color.BLACK
        )
        var leftMask = typedArray.getDrawable(
            R.styleable.MaterialCalendarView_mcv_leftArrowMask
        )
        if (leftMask == null) {
            leftMask = ContextCompat.getDrawable(context, R.drawable.mcv_action_previous)
        }
        leftArrowMask = leftMask
        var rightMask = typedArray.getDrawable(
            R.styleable.MaterialCalendarView_mcv_rightArrowMask
        )
        if (rightMask == null) {
            rightMask = ContextCompat.getDrawable(context, R.drawable.mcv_action_next)
        }
        rightArrowMask = rightMask
        title.setTextAppearance(
            context, typedArray.getResourceId(
                R.styleable.MaterialCalendarView_mcv_headerTextAppearance,
                R.style.TextAppearance_MaterialCalendarWidget_Header
            )
        )
    }

    fun updateUi(currentMonth: CalendarDay) {
        titleChanger.change(currentMonth)
        buttonPast.isEnabled = mcv?.canGoBack() == true
        buttonFuture.isEnabled = mcv?.canGoForward() == true
    }

    fun setTitleFormatter(titleFormatter: TitleFormatter) {
        titleChanger.titleFormatter = titleFormatter
    }

    fun setOnTitleClickListener(listener: OnClickListener) {
        title.setOnClickListener(listener)
    }

    fun setPreviousMonth(previous: CalendarDay) {
        titleChanger.setPreviousMonth(previous)
    }
}