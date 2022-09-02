package com.android.vncalling.ui.custom.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.android.vncalling.R
import com.android.vncalling.databinding.CustomCircleImageViewBinding

class CircleImageCustom : FrameLayout {
    private lateinit var binding: CustomCircleImageViewBinding

    constructor(context: Context) : super(context) {
        initialize(context = context, attributeSet = null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initialize(context = context, attributeSet = attributeSet)
    }

    private fun initialize(context: Context, attributeSet: AttributeSet?) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        this.binding = CustomCircleImageViewBinding.inflate(inflater, this, true)

        if (attributeSet != null) {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(
                attributeSet,
                R.styleable.CircleImageCustom,
                0, 0
            )

            //todo("custom in here")
        }
    }
}