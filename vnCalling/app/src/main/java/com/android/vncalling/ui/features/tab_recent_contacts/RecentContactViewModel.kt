package com.android.vncalling.ui.features.tab_recent_contacts

import android.util.Log
import com.android.vncalling.base.BaseViewModel

class RecentContactViewModel : BaseViewModel() {

    fun showViewModelName(viewModelName: String): String {
        Log.d(RecentContactsFragment::class.java.simpleName, viewModelName)
        return viewModelName
    }
}