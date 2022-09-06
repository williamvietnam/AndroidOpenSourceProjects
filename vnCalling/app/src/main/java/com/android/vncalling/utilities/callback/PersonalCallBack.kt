package com.android.vncalling.utilities.callback

import com.android.vncalling.data.database.entities.Setting

interface PersonalCallBack {
    fun onItemClicked(item: Setting?)
}