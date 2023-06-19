package com.android.remotes.lg.features

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import com.android.databinding.FragmentFivewayBinding
import com.remote.brands.lg.LGBaseFragment
import com.android.remotes.lg.util.TestResponseObject
import com.connectsdk.core.TextInputStatusInfo
import com.connectsdk.core.TextInputStatusInfo.TextInputType
import com.connectsdk.service.capability.KeyControl
import com.connectsdk.service.capability.TextInputControl
import com.connectsdk.service.capability.TextInputControl.TextInputStatusListener
import com.connectsdk.service.command.ServiceCommandError
import java.util.Timer
import java.util.TimerTask

class KeyControlFragment : LGBaseFragment {
    private lateinit var binding: FragmentFivewayBinding
    var testResponse: TestResponseObject? = null

    var isDown = false
    var isMoving = false
    var isScroll = false

    var startX = 0f
    var startY = 0f

    var lastX = Float.NaN
    var lastY = Float.NaN

    var scrollDx: Int = 0
    var scrollDy:Int = 0
    var eventStart: Long = 0
    var timer = Timer()
    var autoScrollTimerTask: TimerTask? = null

    var canReplaceText = false

    var filterTextWatcher: TextWatcher? = null


    constructor() : super()

    constructor(context: Context) : super(context) {
        testResponse = TestResponseObject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFivewayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.openKeyboardButton.isSelected = false
        buttons = arrayOf(
            binding.upButton,
            binding.leftButton,
            binding.clickButton,
            binding.rightButton,
            binding.backButton,
            binding.downButton,
            binding.homeButton,
            binding.openKeyboardButton
        )

        clearLocalText()
        binding.editField.inputType =
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS

        binding.editField.addTextChangedListener(object : TextWatcher {
            var lastString = ""

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (getTextInputControl() == null) {
                    System.err.println("Keyboard Control is null")
                    return
                }

                println("[DEBUG] appside: $s")
                println("[DEBUG] len: " + s!!.length)
                println("[DEBUG] lastString: $lastString")

                if (s.isEmpty()) {
                    // all characters including the sentinel were deleted
                    getTextInputControl()!!.sendDelete()
                } else {
                    val newString = s.toString().replace("\u200B", "") // nasty hack
                    println("[DEBUG] newString: $newString")
                    val matching: Int = getMatchingCharacterLength(lastString, newString)
                    if (matching == 0) {
                        getTextInputControl()!!.sendText("")
                    } else if (matching < lastString.length) {
                        // Delete old characters
                        for (i in 0 until lastString.length - matching) {
                            getTextInputControl()!!.sendDelete()
                        }
                    }
                    if (matching < newString.length) {
                        // Insert new characters
                        getTextInputControl()!!.sendText(newString.substring(matching))
                    }
                }
            }

            override fun afterTextChanged(editable: Editable?) {
                if (editable?.length == 0) {
                    // Nasty hack to give Android some deleteable charcters (zero-width space)
                    editable.append("\u200B")
                }

                lastString = editable.toString().replace("\u200B", "")
            }
        })

        binding.editField.setOnEditorActionListener(OnEditorActionListener { arg0, arg1, arg2 ->
            getTextInputControl()!!.sendEnter()
            false
        })


        binding.editField.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                getTextInputControl()!!.sendDelete()
            }
            false
        })
    }

    fun clearLocalText() {
        binding.editField.setText("\u200B")
    }

    fun getMatchingCharacterLength(oldString: String, newString: String): Int {
        val oldChars = oldString.toCharArray()
        val newChars = newString.toCharArray()
        val length = Math.min(oldChars.size, newChars.size)
        for (i in 0 until length) {
            if (oldChars[i] != newChars[i]) {
                return i
            }
        }
        return length
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun disableButtons() {
        binding.trackpadView.setOnTouchListener(null)
        super.disableButtons()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun enableButtons() {
        super.enableButtons()
        if (getMouseControl() != null) {
            getMouseControl()!!.connectMouse()
        }
        if (getTv()!!.hasCapability(KeyControl.Up)) {
            binding.upButton.setOnClickListener(View.OnClickListener {
                if (getKeyControl() != null) {
                    getKeyControl()!!.up(null)
                    testResponse = TestResponseObject(
                        true,
                        TestResponseObject.SuccessCode,
                        TestResponseObject.UpClicked
                    )
                }
            })
        } else {
            disableButton(binding.upButton)
        }
        if (getTv()!!.hasCapability(KeyControl.Left)) {
            binding.leftButton.setOnClickListener(View.OnClickListener {
                if (getKeyControl() != null) {
                    getKeyControl()!!.left(null)
                    testResponse = TestResponseObject(
                        true,
                        TestResponseObject.SuccessCode,
                        TestResponseObject.LeftClicked
                    )
                }
            })
        } else {
            disableButton(binding.leftButton)
        }
        if (getTv()!!.hasCapability(KeyControl.OK)) {
            binding.clickButton.setOnClickListener(View.OnClickListener {
                if (getKeyControl() != null) {
                    getKeyControl()!!.ok(null)
                    testResponse = TestResponseObject(
                        true,
                        TestResponseObject.SuccessCode,
                        TestResponseObject.Clicked
                    )
                }
            })
        } else {
            disableButton(binding.clickButton)
        }
        if (getTv()!!.hasCapability(KeyControl.Right)) {
            binding.rightButton.setOnClickListener(View.OnClickListener {
                if (getKeyControl() != null) {
                    getKeyControl()!!.right(null)
                    testResponse = TestResponseObject(
                        true,
                        TestResponseObject.SuccessCode,
                        TestResponseObject.RightClicked
                    )
                }
            })
        } else {
            disableButton(binding.rightButton)
        }
        if (getTv()!!.hasCapability(KeyControl.Back)) {
            binding.backButton.setOnClickListener(View.OnClickListener {
                if (getKeyControl() != null) {
                    getKeyControl()!!.back(null)
                }
            })
        } else {
            disableButton(binding.backButton)
        }
        if (getTv()!!.hasCapability(KeyControl.Down)) {
            binding.downButton.setOnClickListener(View.OnClickListener {
                if (getKeyControl() != null) {
                    getKeyControl()!!.down(null)
                    testResponse = TestResponseObject(
                        true,
                        TestResponseObject.SuccessCode,
                        TestResponseObject.DownClicked
                    )
                }
            })
        } else {
            disableButton(binding.downButton)
        }
        if (getTv()!!.hasCapability(KeyControl.Home)) {
            binding.homeButton.setOnClickListener(View.OnClickListener {
                if (getKeyControl() != null) {
                    getKeyControl()!!.home(null)
                    testResponse = TestResponseObject(
                        true,
                        TestResponseObject.SuccessCode,
                        TestResponseObject.HomeClicked
                    )
                }
            })
        } else {
            disableButton(binding.homeButton)
        }
        if (getTextInputControl() != null) {
            if (getTv()!!.hasCapability(TextInputControl.Subscribe)) {
                disableButton(binding.openKeyboardButton)
                getTextInputControl()!!.subscribeTextInputStatus(mTextStatusInputListener)
            } else {
                binding.openKeyboardButton.setOnClickListener(View.OnClickListener {
                    if (!binding.openKeyboardButton.isSelected) {
                        binding.openKeyboardButton.isSelected = true
                        binding.editField.requestFocus()
                        val mgr =
                            mContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        mgr.showSoftInput(
                            (mContext as Activity).currentFocus,
                            InputMethodManager.SHOW_FORCED
                        )
                    } else {
                        binding.openKeyboardButton.setSelected(false)
                        val mgr =
                            mContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        mgr.hideSoftInputFromWindow(binding.editField.windowToken, 0)
                    }
                })
            }
        } else {
            disableButton(binding.openKeyboardButton)
        }
        binding.trackpadView.setOnTouchListener(OnTouchListener { view, motionEvent ->
            var dx = 0f
            var dy = 0f
            val wasMoving: Boolean = isMoving
            val wasScroll: Boolean = isScroll
            isScroll = isScroll || motionEvent.pointerCount > 1
            when (motionEvent.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    isDown = true
                    eventStart = motionEvent.eventTime
                    startX = motionEvent.x
                    startY = motionEvent.y
                }

                MotionEvent.ACTION_UP -> {
                    isDown = false
                    isMoving = false
                    isScroll = false
                    lastX = Float.NaN
                    lastY = Float.NaN
                }
            }
            if (lastX != Float.NaN || lastY != Float.NaN) {
                dx = Math.round(motionEvent.x - lastX).toFloat()
                dy = Math.round(motionEvent.y - lastY).toFloat()
            }
            lastX = motionEvent.x
            lastY = motionEvent.y
            val xDistFromStart: Float = Math.abs(motionEvent.x - startX)
            val yDistFromStart: Float = Math.abs(motionEvent.y - startY)
            if (isDown && !isMoving) {
                if (xDistFromStart > 10 && yDistFromStart > 10) {
                    isMoving = true
                }
            }
            if (isDown && isMoving) {
                if (dx != 0f && dy != 0f) {
                    // Scale dx and dy to simulate acceleration
                    val dxSign = if (dx >= 0) 1 else -1
                    val dySign = if (dy >= 0) 1 else -1
                    dx = (dxSign * Math.round(Math.pow(Math.abs(dx).toDouble(), 1.1))).toFloat()
                    dy = (dySign * Math.round(Math.pow(Math.abs(dy).toDouble(), 1.1))).toFloat()
                    if (!isScroll) {
                        if (getMouseControl() != null) getMouseControl()!!.move(
                            dx.toDouble(),
                            dy.toDouble()
                        )
                    } else {
                        val now = SystemClock.uptimeMillis()
                        scrollDx = (motionEvent.x - startX).toInt()
                        scrollDy = (motionEvent.y - startY).toInt()
                        if (now - eventStart > 1000 && autoScrollTimerTask == null) {
                            Log.d("main", "starting autoscroll")
                            // start autoscrolling
                            autoScrollTimerTask = object : TimerTask() {
                                override fun run() {
                                    if (getMouseControl() != null) getMouseControl()!!.scroll(
                                        scrollDx.toDouble(),
                                        scrollDy.toDouble()
                                    )
                                }
                            }
                            timer.schedule(autoScrollTimerTask, 100, 750)
                        }
                    }
                }
            } else if (!isDown && !wasMoving) {
                if (getMouseControl() != null) getMouseControl()!!.click()
            } else if (!isDown && wasMoving && wasScroll) {
                // release two fingers
                dx = motionEvent.x - startX
                dy = motionEvent.y - startY
                if (getMouseControl() != null) getMouseControl()!!.scroll(
                    dx.toDouble(),
                    dy.toDouble()
                )
                Log.d("main", "sending scroll $dx ,$dx")
            }
            if (!isDown) {
                isMoving = false
                if (autoScrollTimerTask != null) {
                    autoScrollTimerTask?.cancel()
                    autoScrollTimerTask = null
                    Log.d("main", "ending autoscroll")
                }
            }
            true
        })
    }

    private val mTextStatusInputListener: TextInputStatusListener =
        object : TextInputStatusListener {
            override fun onSuccess(keyboard: TextInputStatusInfo) {
                val focused = keyboard.isFocused
                val textInputType = keyboard.textInputType
                val predictionEnabled = keyboard.isPredictionEnabled
                val correctionEnabled = keyboard.isCorrectionEnabled
                val autoCapitalization = keyboard.isAutoCapitalization
                val hiddenText = keyboard.isHiddenText
                val focusChanged = keyboard.isFocusChanged
                var type: Int
                if (textInputType != TextInputType.DEFAULT) {
                    if (textInputType == TextInputType.NUMBER) {
                        type = InputType.TYPE_CLASS_NUMBER
                    } else if (textInputType == TextInputType.PHONE_NUMBER) {
                        type = InputType.TYPE_CLASS_PHONE
                    } else {
                        type = InputType.TYPE_CLASS_TEXT
                        if (textInputType == TextInputType.URL) {
                            type = type or InputType.TYPE_TEXT_VARIATION_URI
                        } else if (textInputType == TextInputType.EMAIL) {
                            type = type or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        }
                        if (predictionEnabled) {
                            type = type or InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE
                        }
                        if (correctionEnabled) {
                            type = type or InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
                        }
                        if (autoCapitalization) {
                            type = type or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
                        }
                        if (hiddenText) {
                            type = type or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        }
                        if (!canReplaceText) {
                            type = type or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                        }
                    }
                    val fType = type
                    if (binding.editField.inputType != type) {
                        binding.editField.inputType = fType
                    }
                }
                if (focused) {
                    if (focusChanged) {
                        clearLocalText()
                    }
                    binding.editField.requestFocus()
                    val mgr =
                        mContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    mgr.showSoftInput(
                        (mContext as Activity).currentFocus,
                        InputMethodManager.SHOW_FORCED
                    )
                } else {
                    canReplaceText = false
                    val mgr =
                        mContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    mgr.hideSoftInputFromWindow(binding.editField.windowToken, 0)
                    clearLocalText()
                }
            }

            override fun onError(arg0: ServiceCommandError) {}
        }
}