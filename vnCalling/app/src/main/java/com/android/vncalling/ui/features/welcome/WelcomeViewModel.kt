package com.android.vncalling.ui.features.welcome

import com.android.vncalling.R
import com.android.vncalling.base.BaseViewModel
import com.android.vncalling.data.database.entities.Welcome
import com.android.vncalling.utils.Constants

class WelcomeViewModel constructor(
    private val welcomeList: MutableList<Welcome> = mutableListOf()
) : BaseViewModel() {

    fun getWelcomeList(): List<Welcome> {
        this.welcomeList.clear()
        this.welcomeList.add(
            Welcome(
                R.drawable.ic_call_wifi,
                R.string.headlineWelcome1,
                R.string.descriptionWelcome1
            )
        )
        this.welcomeList.add(
            Welcome(
                R.drawable.ic_video_call,
                R.string.headlineWelcome2,
                R.string.descriptionWelcome2
            )
        )
        this.welcomeList.add(
            Welcome(
                R.drawable.ic_message,
                R.string.headlineWelcome3,
                R.string.descriptionWelcome3
            )
        )
        return this.welcomeList
    }

    fun handlerNextActivity(): String {
        getDataManger().setIsShowWelcomeScreen(false)
        return if (!getDataManger().isShowWelcomeScreen()) {
            Constants.LOGIN_ACTIVITY
        } else {
            Constants.MAIN_ACTIVITY
        }
    }
}