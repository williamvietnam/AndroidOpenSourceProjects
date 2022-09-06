package com.android.vncalling.base

import androidx.annotation.StringRes
import com.android.vncalling.ui.features.container.MainView

interface BaseView {
    fun initialize()

    fun getMainInstance(): MainView

    fun hideBottomNavigationView(isHidden: Boolean)

    fun showToast(message: String?)

    fun showToast(@StringRes message: Int?)
}