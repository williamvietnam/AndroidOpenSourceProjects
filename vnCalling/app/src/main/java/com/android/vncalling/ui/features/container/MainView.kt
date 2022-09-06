package com.android.vncalling.ui.features.container

interface MainView {
    fun getMainViewModel(): MainViewModel

    fun hideBottomNavigationView(isHidden: Boolean)

    fun openLoginActivity()
}