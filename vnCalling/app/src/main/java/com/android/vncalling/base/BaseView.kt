package com.android.vncalling.base

import com.android.vncalling.ui.features.container.MainView

interface BaseView {
    fun initialize()

    fun getMainInstance(): MainView

    fun hideBottomNavigationView(isHidden: Boolean)
}