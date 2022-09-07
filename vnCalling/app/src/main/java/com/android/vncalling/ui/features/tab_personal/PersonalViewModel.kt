package com.android.vncalling.ui.features.tab_personal

import com.android.vncalling.R
import com.android.vncalling.base.BaseViewModel
import com.android.vncalling.data.database.entities.Setting
import com.android.vncalling.ui.features.tab_personal.PersonalFragment.Companion.LANGUAGE_SETTING
import com.android.vncalling.ui.features.tab_personal.PersonalFragment.Companion.PERSONAL_INFORMATION
import com.android.vncalling.ui.features.tab_personal.PersonalFragment.Companion.SECURITY_PRIVACY

class PersonalViewModel : BaseViewModel() {

    private val settingList: MutableList<Setting> = mutableListOf()

    fun getSettingList(): MutableList<Setting> {
        this.settingList.clear()
        settingList.add(
            Setting(
                PERSONAL_INFORMATION, R.drawable.ic_person,
                "PERSONAL INFORMATION", "Update personal information"
            )
        )
        settingList.add(
            Setting(
                SECURITY_PRIVACY, R.drawable.ic_privacy,
                "SECURITY & PRIVACY", "Adjust security and privacy of you"
            )
        )
        settingList.add(
            Setting(
                LANGUAGE_SETTING, R.drawable.ic_language,
                "LANGUAGE SETTING", "Change language between Vi & En"
            )
        )
        return this.settingList
    }

    fun isLogoutSuccess(): Boolean {
        //todo
        return true
    }
}