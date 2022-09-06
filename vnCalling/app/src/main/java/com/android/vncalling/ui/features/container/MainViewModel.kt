package com.android.vncalling.ui.features.container

import com.android.vncalling.base.BaseViewModel
import com.android.vncalling.stringee.StringEeHelper
import com.android.vncalling.stringee.StringEeImplement

class MainViewModel : BaseViewModel() {

    private val stringEe: StringEeHelper = StringEeImplement()

    fun getStringEe(): StringEeHelper {
        return this.stringEe
    }

    fun removeAllDataPreferences() {
    }
}