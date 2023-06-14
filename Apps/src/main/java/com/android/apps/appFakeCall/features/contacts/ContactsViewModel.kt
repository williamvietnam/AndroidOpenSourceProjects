package com.android.apps.appFakeCall.features.contacts

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.apps.appFakeCall.data.ContactDatabase
import com.android.apps.appFakeCall.data.entities.ContactEntity
import com.android.commons.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel : BaseViewModel() {
    private val _allContactsData = MutableLiveData<MutableList<ContactEntity>>()
    val allContactsData: LiveData<MutableList<ContactEntity>> get() = _allContactsData
    fun getAllContacts(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _allContactsData.postValue(ContactDatabase.getDatabase(context).dao().getAllContacts())
        }
    }
}