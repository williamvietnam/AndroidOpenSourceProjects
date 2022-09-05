package com.android.vncalling.ui.features.tab_recent_contacts.call

import com.android.vncalling.base.BaseViewModel
import com.android.vncalling.data.remote.models.UserInformation

class CallListViewModel : BaseViewModel() {
    private val userInformationList: MutableList<UserInformation> = mutableListOf()

    fun getCallListSuccess() {
        //todo("call api from firebase or server backend")
    }

    fun getUserInformationListFailure(): MutableList<UserInformation> {
        this.userInformationList.clear()
        return this.userInformationList
    }
}