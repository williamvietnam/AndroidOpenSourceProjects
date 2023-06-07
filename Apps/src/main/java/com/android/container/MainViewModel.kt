package com.android.container

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.android.apps.appFakeCall.data.ContactDatabase
import com.android.apps.appFakeCall.data.entities.ContactEntity
import com.android.apps.appFakeCall.data.entities.ContactsResponse
import com.android.base.BaseViewModel
import com.android.common.KEY
import com.android.common.Preferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    //-----------------------------appPrankSound start-------------------------------
    //todo
    //-----------------------------appPrankSound end-------------------------------

    //-----------------------------appFakeCall start-----------------------------
    fun saveContactsListFromJsonToDatabase(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!(Preferences.instance.get(
                    KEY.PREFS_IS_SAVE_CONTACTS_DATABASE_FROM_JSON,
                    false
                ) as Boolean)
            ) {
                val contactJson = getJsonFromAssets("app_fake_call/contacts.json", context)
                val contacts: MutableList<ContactEntity> = ArrayList()
                contacts.addAll(Gson().fromJson(contactJson, ContactsResponse::class.java).contacts)
                ContactDatabase.getDatabase(context).dao().createContactsListToDatabase(contacts)
                Preferences.instance.set(KEY.PREFS_IS_SAVE_CONTACTS_DATABASE_FROM_JSON, true)
            }
        }
    }
    //-----------------------------appFakeCall end-----------------------------


    //-----------------------------appLieDetector start-----------------------------
    //todo
    //-----------------------------appLieDetector end-----------------------------


    //-----------------------------appAIChat start-----------------------------
    //todo
    //-----------------------------appAIChat end-----------------------------

}