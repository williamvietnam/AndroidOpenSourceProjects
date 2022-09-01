package com.android.vncalling.ui.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import com.android.vncalling.R
import com.android.vncalling.databinding.CustomTextFieldViewBinding

class TextFieldCustom : FrameLayout {
    private lateinit var binding: CustomTextFieldViewBinding

    constructor(context: Context) : super(context) {
        initialize(context = context, attributeSet = null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initialize(context = context, attributeSet = attributeSet)
    }

    private fun initialize(context: Context, attributeSet: AttributeSet?) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        this.binding = CustomTextFieldViewBinding.inflate(inflater, this, true)

        if (attributeSet != null) {
            val typedArray: TypedArray = context.theme.obtainStyledAttributes(
                attributeSet,
                R.styleable.TextFieldCustom,
                0,
                0
            )

            val textHint: String? = typedArray.getString(R.styleable.TextFieldCustom_textHint)
            val hintRes: Int = typedArray.getInt(R.styleable.TextFieldCustom_hintRes, 0)
            if (textHint != null) {
                this.binding.inputLayout.hint = textHint
            } else if (hintRes != 0) {
                this.binding.inputLayout.setHint(hintRes)
            }

            val startIconDraw: Drawable? =
                typedArray.getDrawable(R.styleable.TextFieldCustom_startIcon)
            if (startIconDraw != null) {
                this.binding.inputLayout.startIconDrawable = startIconDraw
            }

            val text: String? = typedArray.getString(R.styleable.TextFieldCustom_text)
            val textRes: Int = typedArray.getInt(R.styleable.TextFieldCustom_textRes, 0)
            if (text != null) {
                this.binding.inputEdt.setText(text)
            } else if (textRes != 0) {
                this.binding.inputEdt.setText(textRes)
            }

            when (typedArray.getString(R.styleable.TextFieldCustom_imeOption)) {
                ACTION_NEXT -> this.binding.inputEdt.imeOptions = EditorInfo.IME_ACTION_NEXT
                ACTION_DONE -> this.binding.inputEdt.imeOptions = EditorInfo.IME_ACTION_DONE
                ACTION_GO -> this.binding.inputEdt.imeOptions = EditorInfo.IME_ACTION_GO
            }

            when (typedArray.getString(R.styleable.TextFieldCustom_inputTypes)) {
                TYPE_TEXT -> {
                    this.binding.inputEdt.inputType = InputType.TYPE_CLASS_TEXT
                }
                TYPE_TEXT_PASSWORD -> {
                    this.binding.inputEdt.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                TYPE_NUMBER_PASSWORD -> {
                    this.binding.inputEdt.inputType = InputType.TYPE_NUMBER_VARIATION_PASSWORD
                }
                TYPE_EMAIL -> {
                    this.binding.inputEdt.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
                TYPE_NUMBER_PHONE -> {
                    this.binding.inputEdt.inputType = InputType.TYPE_CLASS_PHONE
                }
                TYPE_NUMBER -> {
                    this.binding.inputEdt.inputType = InputType.TYPE_CLASS_NUMBER
                }
            }

            val endDrawable: Drawable? =
                typedArray.getDrawable(R.styleable.TextFieldCustom_drawableEnd)
            if (endDrawable != null) {
                this.binding.endIcon.visibility = VISIBLE
                this.binding.endIcon.setImageDrawable(endDrawable)
            } else {
                this.binding.endIcon.visibility = GONE
            }

            this.binding.inputEdt.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(editable: Editable?) {
                    if (editable.toString().isNotEmpty()) {
                        binding.endIcon.visibility = VISIBLE
                    } else {
                        binding.endIcon.visibility = GONE
                    }
                }
            })

            this.binding.endIcon.setOnClickListener {
                this.binding.inputEdt.setText("")
            }
        }
    }

    companion object {
        const val ACTION_NEXT = "next"
        const val ACTION_DONE = "done"
        const val ACTION_GO = "go"

        const val TYPE_TEXT = "text"
        const val TYPE_TEXT_PASSWORD = "textPassword"
        const val TYPE_NUMBER_PASSWORD = "numberPassword"
        const val TYPE_EMAIL = "textEmailAddress"
        const val TYPE_NUMBER_PHONE = "phone"
        const val TYPE_NUMBER = "number"
    }
}