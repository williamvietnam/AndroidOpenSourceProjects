package com.android.container

import com.android.base.BaseViewModel
import com.android.container.appsMenu.App

class MainViewModel : BaseViewModel() {

    fun createApps(): List<App> {
        val apps = ArrayList<App>()
        return apps
    }
}