package com.android.apps.appFakeCall.utils

import android.os.CountDownTimer

abstract class CountUpTimer protected constructor(
    private val duration: Long
) : CountDownTimer(duration, INTERVAL_MS) {
    abstract fun onTick(totalSecond: Int)
    override fun onTick(msUntilFinished: Long) {
        val second = ((duration - msUntilFinished) / 1000).toInt()
        onTick(second)
    }

    override fun onFinish() {
        onTick(duration / 1000)
    }

    companion object {
        private const val INTERVAL_MS: Long = 1000
    }
}