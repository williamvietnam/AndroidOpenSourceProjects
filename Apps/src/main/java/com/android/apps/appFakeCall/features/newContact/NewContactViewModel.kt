package com.android.apps.appFakeCall.features.newContact

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.android.apps.appFakeCall.data.ContactDatabase
import com.android.apps.appFakeCall.data.entities.ContactEntity
import com.android.core.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewContactViewModel : BaseViewModel() {
    fun saveData(
        context: Context,
        contactIcon: String,
        contactName: String,
        contactNumber: String,
        contactVideo: String?
    ) {
        val contact = ContactEntity(
            contactIcon = contactIcon,
            contactName = contactName,
            contactNumber = contactNumber,
            contactVideo = contactVideo,
            isDataBase = true
        )
        viewModelScope.launch(Dispatchers.IO) {
            contact.let {
                ContactDatabase.getDatabase(context).dao().addNewContactToDatabase(contact)
            }
        }
    }
}