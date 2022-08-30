package com.android.vncalling.ui.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.vncalling.R
import com.android.vncalling.databinding.LayoutToolbarCustomViewBinding

class ToolbarCustom : FrameLayout {
    private lateinit var binding: LayoutToolbarCustomViewBinding
    private var toolbarLeftCallBack: ToolbarLeftCallBack? = null
    private var toolbarRightCallBack: ToolbarRightCallBack? = null

    fun setToolbarLeftCallBack(toolbarLeftCallBack: ToolbarLeftCallBack) {
        this.toolbarLeftCallBack = toolbarLeftCallBack
    }

    fun setToolbarRightCallBack(toolbarRightCallBack: ToolbarRightCallBack) {
        this.toolbarRightCallBack = toolbarRightCallBack
    }

    constructor(context: Context) : super(context) {
        initialize(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initialize(context, attributeSet)
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet?,
        defStyleAttr: Int
    ) : super(
        context, attributeSet, defStyleAttr
    ) {
        initialize(context, attributeSet)
    }

    private fun initialize(context: Context, attributeSet: AttributeSet?) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        this.binding = LayoutToolbarCustomViewBinding.inflate(inflater, this, true)

        this.binding.leftToolbar.setOnClickListener {
            toolbarLeftCallBack?.onLeftClicked()
        }

        this.binding.rightToolbar.setOnClickListener {
            toolbarRightCallBack?.onRightClicked()
        }

        if (attributeSet != null) {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(
                attributeSet,
                R.styleable.ToolbarCustom,
                0,
                0
            )

            val leftToolbar: Drawable? =
                typedArray.getDrawable(R.styleable.ToolbarCustom_leftToolbar)
            if (leftToolbar != null) {
                this.binding.leftToolbar.setImageDrawable(leftToolbar)
                this.binding.leftToolbar.visibility = VISIBLE
            } else {
                this.binding.leftToolbar.visibility = GONE
            }

            val rightToolbar: Drawable? =
                typedArray.getDrawable(R.styleable.ToolbarCustom_rightToolbar)
            if (rightToolbar != null) {
                this.binding.rightToolbar.setImageDrawable(rightToolbar)
                this.binding.rightToolbar.visibility = VISIBLE
            } else {
                this.binding.rightToolbar.visibility = GONE
            }

            val toolbarName: String? =
                typedArray.getString(R.styleable.ToolbarCustom_toolbarName)
            if (toolbarName != null) {
                this.binding.toolbarName.text = toolbarName
                this.binding.toolbarName.visibility = VISIBLE
            } else {
                this.binding.toolbarName.visibility = GONE
            }
        }
    }

    fun setLeftIconDrawable(@DrawableRes drawableRes: Int) {
        this.binding.leftToolbar.setImageResource(drawableRes)
    }

    fun setRightIconDrawable(@DrawableRes drawableRes: Int) {
        this.binding.rightToolbar.setImageResource(drawableRes)
    }

    fun setToolbarName(@StringRes stringRes: Int) {
        this.binding.toolbarName.setText(stringRes)
    }

    fun setToolbarName(text: String) {
        this.binding.toolbarName.text = text
    }

    interface ToolbarLeftCallBack {
        fun onLeftClicked()
    }

    interface ToolbarRightCallBack {
        fun onRightClicked()
    }
}