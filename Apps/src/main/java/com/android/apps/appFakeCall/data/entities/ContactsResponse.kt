package com.android.apps.appFakeCall.data.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContactsResponse(
    @Expose
    @SerializedName("contacts")
    val contacts: MutableList<ContactEntity>
)