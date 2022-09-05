package com.android.vncalling.ui.features.tab_personal

import com.android.vncalling.base.BaseViewModel
import com.android.vncalling.data.database.entities.Setting

class PersonalViewModel : BaseViewModel() {

    private val settingList: MutableList<Setting> = mutableListOf()

    fun getSettingList(): MutableList<Setting> {
        this.settingList.clear()
        //todo("add data in here")
        return this.settingList
    }

    fun isLogoutSuccess(): Boolean {
        //todo
        return true
    }
}